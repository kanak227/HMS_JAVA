<%-- 
    Document   : dbTest
    Created on : Dec 8, 2025, 11:19:19?AM
    Author     : kanak_19
--%>

<%@ page import="java.sql.*, com.hms.util.DBConnection" %>
<%
    try (Connection con = DBConnection.getConnection()) {
        out.println("DB Connected Successfully: " + con);
    } catch (Exception e) {
        out.println("DB ERROR: " + e.getMessage());
    }
%>

