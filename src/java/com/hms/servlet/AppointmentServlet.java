package com.hms.servlet;

import com.hms.model.User;
import com.hms.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/book")
public class AppointmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"PATIENT".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        int patientUserId = user.getId();
        int patientId = 0;

        try (Connection con = DBConnection.getConnection()) {

            // Convert user_id -> patient.id
            String patQuery = "SELECT id FROM patients WHERE user_id=?";
            PreparedStatement pstp = con.prepareStatement(patQuery);
            pstp.setInt(1, patientUserId);
            ResultSet rsp = pstp.executeQuery();
            if (rsp.next()) {
                patientId = rsp.getInt(1);
            }

            String sql = "INSERT INTO appointments (doctor_id, patient_id, appointment_date, appointment_time, status) " +
                    "VALUES (?, ?, CURDATE(), CURTIME(), 'SCHEDULED')";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, doctorId);
            pst.setInt(2, patientId);
            pst.executeUpdate();

            // Set flag for toast popup
            session.setAttribute("bookingSuccess", "Appointment Booked Successfully!");

            response.sendRedirect(request.getContextPath() + "/patient/home.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("bookingSuccess", "Failed to book appointment!");
            response.sendRedirect(request.getContextPath() + "/patient/home.jsp");
        }
    }
}
