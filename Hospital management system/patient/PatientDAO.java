package com.hms.patient;

import java.sql.*;
import com.hms.db.DBConnection;

public class PatientDAO {

    public void create(Patient p) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
              con.prepareStatement(
               "INSERT INTO patient(patient_name,gender,age,phone,address) VALUES(?,?,?,?,?)")) {
            ps.setString(1, p.getPatientName());
            ps.setString(2, p.getGender());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getAddress());
            ps.executeUpdate();
            System.out.println("Patient added");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void getAll() {
        try (Connection con = DBConnection.getConnection();
             ResultSet rs =
              con.prepareStatement("SELECT * FROM patient").executeQuery()) {
            while (rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(int id, String phone) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
              con.prepareStatement("UPDATE patient SET phone=? WHERE patient_id=?")) {
            ps.setString(1, phone);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Patient updated");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
              con.prepareStatement("DELETE FROM patient WHERE patient_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Patient deleted");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
