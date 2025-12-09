package com.hms.dao;

import com.hms.model.Appointment;
import com.hms.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public List<Appointment> getTodayAppointments(int doctorId) {
        List<Appointment> list = new ArrayList<>();

        String sql = "SELECT a.id, a.appointment_time, a.reason, a.status, " +
                     "p.full_name AS patient_name " +
                     "FROM appointments a " +
                     "JOIN patients p ON a.patient_id = p.id " +
                     "WHERE a.doctor_id = ? AND a.appointment_date = CURDATE()";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, doctorId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                list.add(new Appointment(
                    rs.getInt("id"),
                    rs.getString("patient_name"),
                    rs.getString("appointment_time"),
                    rs.getString("reason"),
                    rs.getString("status")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public void bookAppointment(int doctorId, int patientId, Date date, Time time, String reason) throws SQLException {
    Connection conn = DBConnection.getConnection();
    String sql = "INSERT INTO appointments (doctor_id, patient_id, appointment_date, appointment_time, reason) VALUES (?, ?, ?, ?, ?)";

    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setInt(1, doctorId);
    ps.setInt(2, patientId);
    ps.setDate(3, date);
    ps.setTime(4, time);
    ps.setString(5, reason);
    ps.executeUpdate();

    ps.close();
    conn.close();
}

}
