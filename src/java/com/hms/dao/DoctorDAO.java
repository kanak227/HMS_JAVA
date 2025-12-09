package com.hms.dao;

import com.hms.model.Doctor;
import com.hms.model.Specialization;
import com.hms.model.Patient;
import com.hms.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> getDoctors(Integer specializationId) {
        List<Doctor> list = new ArrayList<>();
        
        String sql = "SELECT d.id, d.full_name, d.phone, s.name AS specialization " +
                     "FROM doctors d JOIN specializations s ON d.specialization_id = s.id";

        if (specializationId != null) {
            sql += " WHERE s.id = ?";
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            if (specializationId != null) {
                pst.setInt(1, specializationId);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Doctor(
                    rs.getInt("id"),
                    rs.getString("full_name"),
                    rs.getString("specialization"),
                    rs.getString("phone")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<String> getSpecializations() {
        List<String> list = new ArrayList<>();

        String sql = "SELECT name FROM specializations";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Specialization> getAllSpecializations() {
    List<Specialization> list = new ArrayList<>();

    String sql = "SELECT id, name FROM specializations";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Specialization(
                rs.getInt("id"),
                rs.getString("name")
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
   

    return list;
}
    public List<Patient> getPatientsForDoctor(int doctorUserId) throws SQLException {
    List<Patient> patients = new ArrayList<>();

    String sql = "SELECT p.full_name, p.gender, p.phone, " +
                 "a.appointment_date, a.appointment_time, a.reason " +
                 "FROM appointments a " +
                 "JOIN patients p ON a.patient_id = p.id " +
                 "JOIN doctors d ON a.doctor_id = d.id " +
                 "WHERE d.user_id = ?";

    Connection conn = DBConnection.getConnection();
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, doctorUserId);

    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
        Patient p = new Patient();
        p.setFullName(rs.getString("full_name"));
        p.setGender(rs.getString("gender"));
        p.setPhone(rs.getString("phone"));
        p.setAppointmentDate(rs.getDate("appointment_date"));
        p.setAppointmentTime(rs.getTime("appointment_time"));
        p.setReason(rs.getString("reason"));
        patients.add(p);
    }

    rs.close();
    stmt.close();
    conn.close();

    return patients;
}


    

}
