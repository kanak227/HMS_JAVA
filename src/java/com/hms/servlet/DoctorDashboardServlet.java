package com.hms.servlet;

import com.hms.dao.DoctorDAO;
import com.hms.model.Patient;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.sql.SQLException;


@WebServlet("/doctor/home")
public class DoctorDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null || !"DOCTOR".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        DoctorDAO dao = new DoctorDAO();
        try {
    List<Patient> patients = dao.getPatientsForDoctor(userId);
    request.setAttribute("patients", patients);
} catch (SQLException e) {
    e.printStackTrace();
    request.setAttribute("patients", null);
}
        RequestDispatcher rd = request.getRequestDispatcher("/doctor/home.jsp");
        rd.forward(request, response);
    }
}
