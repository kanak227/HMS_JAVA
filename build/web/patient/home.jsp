<%@ page import="java.util.List" %>
<%@ page import="com.hms.dao.DoctorDAO" %>
<%@ page import="com.hms.model.Doctor" %>
<%@ page import="com.hms.model.Specialization" %>
<%@ page import="com.hms.model.User" %>

<%
    User user = (User) session.getAttribute("user");

    if (user == null || !"PATIENT".equals(user.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    DoctorDAO dao = new DoctorDAO();
    String spec = request.getParameter("spec");
    Integer specId = (spec != null && !spec.isEmpty()) ? Integer.parseInt(spec) : null;

    List<Specialization> specs = dao.getAllSpecializations();
    List<Doctor> doctors = dao.getDoctors(specId);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Patient Dashboard | HMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-3">
    <a class="navbar-brand" href="#">HMS</a>
    <span class="navbar-text text-white">
        Logged in as: <%= user.getUsername() %>
    </span>
    <a href="<%= request.getContextPath() %>/login.jsp" class="btn btn-light btn-sm ms-3">Logout</a>
</nav>

<div class="container mt-4">

    <%  // SUCCESS TOAST
        String successMsg = (String) session.getAttribute("bookingSuccess");
        if (successMsg != null) {
    %>
    <div class="position-fixed top-0 end-0 p-3" style="z-index: 9999;">
        <div class="toast show align-items-center text-bg-success border-0" role="alert">
            <div class="d-flex">
                <div class="toast-body">
                    <%= successMsg %>
                </div>
                <button type="button" class="btn-close btn-close-white me-2 m-auto" 
                        data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </div>
    <% session.removeAttribute("bookingSuccess"); } %>

    <h3 class="text-primary fw-bold text-center">Find a Doctor</h3>

    <form class="row g-2 mb-4 justify-content-center" method="get" action="home.jsp">
        <div class="col-md-4">
            <select name="spec" class="form-select">
                <option value="">All Specializations</option>
                <% for (Specialization s : specs) { %>
                <option value="<%= s.getId() %>"
                        <%= (spec != null && spec.equals(String.valueOf(s.getId()))) ? "selected" : "" %>>
                    <%= s.getName() %>
                </option>
                <% } %>
            </select>
        </div>
        <div class="col-md-2">
            <button class="btn btn-primary w-100">Filter</button>
        </div>
    </form>

    <table class="table table-bordered table-hover shadow bg-white">
        <thead class="table-primary text-center">
            <tr>
                <th>Name</th>
                <th>Specialization</th>
                <th>Phone</th>
                <th>Book</th>
            </tr>
        </thead>
        <tbody>
        <% for (Doctor d : doctors) { %>
            <tr class="text-center">
                <td><%= d.getName() %></td>
                <td><%= d.getSpecialization() %></td>
                <td><%= d.getPhone() %></td>
                <td>
                    <form action="<%= request.getContextPath() %>/book" method="post">
                        <input type="hidden" name="doctorId" value="<%= d.getId() %>" />
                        <button class="btn btn-success btn-sm">Book</button>
                    </form>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
