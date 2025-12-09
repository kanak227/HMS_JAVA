<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - HMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card shadow p-4" style="width: 350px;">
        <h3 class="text-center mb-3 text-primary">HMS Login</h3>

        <form action="<%= request.getContextPath() %>/login" method="post">
            <div class="mb-3">
                <label class="form-label">Username</label>
                <input class="form-control" type="text" name="username" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Password</label>
                <input class="form-control" type="password" name="password" required>
            </div>

            <button class="btn btn-primary w-100" type="submit">Login</button>

            <p class="text-danger text-center mt-2"><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %></p>
        </form>
    </div>
</div>

</body>
</html>
