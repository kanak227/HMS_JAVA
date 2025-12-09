package com.hms.servlet;

import com.hms.dao.UserDAO;
import com.hms.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.authenticate(username, password);

        if (user == null) {
            request.setAttribute("errorMessage", "Invalid Username or Password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        if ("PATIENT".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/patient/home.jsp");
        } else if ("DOCTOR".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/doctor/home.jsp");
        }
    }
}
