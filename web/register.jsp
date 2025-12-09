<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>

    <form action="register" method="post">
        <label>Username: </label>
        <input type="text" name="username" required/><br><br>

        <label>Password: </label>
        <input type="password" name="password" required/><br><br>

        <label>Role: </label>
        <select name="role" required>
            <option value="PATIENT">Patient</option>
            <option value="DOCTOR">Doctor</option>
        </select><br><br>

        <label>Full Name: </label>
        <input type="text" name="full_name" required/><br><br>

        <label>Phone: </label>
        <input type="text" name="phone"/><br><br>

        <label>(Doctor Only) Specialization: </label>
        <input type="number" name="spec_id"/><br><br>

        <button type="submit">Register</button>
    </form>

    <p><a href="login.jsp">Already have an account? Login</a></p>

</body>
</html>
