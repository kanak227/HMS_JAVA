<%@ page import="com.hms.model.User" %>
<%@ page import="java.sql.*, com.hms.util.DBConnection" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"DOCTOR".equals(user.getRole())) {
        response.sendRedirect("../login.jsp");
        return;
    }

    int doctorId = 1; // Static test doctor
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
%>

<!DOCTYPE html>
<html>
<head>
    <title>Doctor Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-primary px-3">
    <span class="navbar-brand mb-0 h1">HMS</span>
    <span class="text-white">Doctor: <%= user.getUsername() %></span>
    <a class="btn btn-light btn-sm ms-3" href="../login.jsp">Logout</a>
</nav>

<div class="container mt-4">

    <h2 class="text-primary mb-3">Doctor Dashboard</h2>
    <h4 class="mb-3">Today's Appointments</h4>

    <table class="table table-bordered table-striped bg-white">
        <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Patient ID</th>
            <th>Date</th>
            <th>Time</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                con = DBConnection.getConnection();
                String sql = "SELECT id, patient_id, appointment_date, appointment_time, status " +
                             "FROM appointments WHERE doctor_id = ? AND appointment_date = CURDATE()";
                pst = con.prepareStatement(sql);
                pst.setInt(1, doctorId);
                rs = pst.executeQuery();

                boolean any = false;
                while (rs.next()) {
                    any = true;
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getInt("patient_id") %></td>
            <td><%= rs.getDate("appointment_date") %></td>
            <td><%= rs.getTime("appointment_time") %></td>
            <td><%= rs.getString("status") %></td>
        </tr>
        <%
                }
                if (!any) {
        %>
        <tr><td colspan="5" class="text-center">No appointments today</td></tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception ex) {}
                try { if (pst != null) pst.close(); } catch (Exception ex) {}
                try { if (con != null) con.close(); } catch (Exception ex) {}
            }
        %>
        </tbody>
    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
