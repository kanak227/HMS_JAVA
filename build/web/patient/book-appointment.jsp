<%@ page import="java.util.*" %>
<%
   
    Integer userId = (Integer) session.getAttribute("userId");
    String role = (String) session.getAttribute("role");

    if (userId == null || !"PATIENT".equals(role)) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }


    int doctorId = Integer.parseInt(request.getParameter("doctorId"));
%>

<html>
<head>
    <title>Book Appointment</title>
</head>
<body>

<h2>Book Appointment</h2>

<form action="bookAppointment" method="post">

    <input type="hidden" name="doctor_id" value="<%= doctorId %>">

    <label>Date:</label>
    <input type="date" name="appointment_date" required><br><br>

    <label>Time:</label>
    <input type="time" name="appointment_time" required><br><br>

    <label>Reason:</label><br>
    <textarea name="reason" required></textarea><br><br>

    <button type="submit">Book</button>

</form>

</body>
</html>
