package com.hms.dao;

import com.hms.model.User;
import com.hms.util.DBConnection;
import java.sql.*;

public class UserDAO {

    public User authenticate(String username, String password) {
        User user = null;

        try (Connection con = DBConnection.getConnection()) {
            // DEV HACK: ignore password for now, only username
            String sql = "SELECT id, username, role FROM users WHERE username = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            // pst.setString(2, password); // not used now

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
