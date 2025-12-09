<!DOCTYPE html>
<html>
<head>
    <title>HMS</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-dark bg-primary px-3">
    <a class="navbar-brand" href="#">HMS</a>
    <span class="navbar-text text-white">
        Logged in as: <%= ((com.hms.model.User) session.getAttribute("user")).getUsername() %>
    </span>
    <a href="<%= request.getContextPath() %>/login.jsp" class="btn btn-light btn-sm ms-3">Logout</a>
</nav>
<div class="container mt-4">
