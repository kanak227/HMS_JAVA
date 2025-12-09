package com.hms.servlet;

import com.hms.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        String role = request.getParameter("role");
        String fullName = request.getParameter("full_name");
        String phone = request.getParameter("phone");
        
        try (Connection con = DBConnection.getConnection()) {

            // Insert into users
            String sqlUser = "INSERT INTO users (username, password_hash, role) VALUES (?, ?, ?)";
            PreparedStatement pstUser = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            pstUser.setString(1, username);
            pstUser.setString(2, password);
            pstUser.setString(3, role);
            pstUser.executeUpdate();

            ResultSet rs = pstUser.getGeneratedKeys();
            rs.next();
            int userId = rs.getInt(1);

            if ("PATIENT".equals(role)) {

                String sqlPat = "INSERT INTO patients (user_id, full_name, phone) VALUES (?, ?, ?)";
                PreparedStatement pstPat = con.prepareStatement(sqlPat);
                pstPat.setInt(1, userId);
                pstPat.setString(2, fullName);
                pstPat.setString(3, phone);
                pstPat.executeUpdate();

            } else if ("DOCTOR".equals(role)) {

                int specId = Integer.parseInt(request.getParameter("spec_id"));

                String sqlDoc = "INSERT INTO doctors (user_id, full_name, specialization_id, phone) VALUES (?, ?, ?, ?)";
                PreparedStatement pstDoc = con.prepareStatement(sqlDoc);
                pstDoc.setInt(1, userId);
                pstDoc.setString(2, fullName);
                pstDoc.setInt(3, specId);
                pstDoc.setString(4, phone);
                pstDoc.executeUpdate();
            }

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp");
        }
    }
}
