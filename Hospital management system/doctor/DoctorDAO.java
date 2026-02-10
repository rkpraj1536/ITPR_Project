package com.hms.doctor;

import java.sql.*;
import com.hms.db.DBConnection;

public class DoctorDAO {

    // CREATE
    public void create(Doctor d) {
        String sql = "INSERT INTO doctor(name, specialization, phone, department_id) VALUES (?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, d.getName());
            ps.setString(2, d.getSpecialization());
            ps.setString(3, d.getPhone());
            ps.setInt(4, d.getDepartmentId());

            ps.executeUpdate();
            System.out.println("Doctor added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ
    public void getAll() {
        String sql = "SELECT * FROM doctor";

        try (Connection con = DBConnection.getConnection();
             ResultSet rs = con.prepareStatement(sql).executeQuery()) {

            while (rs.next()) {
                System.out.println(
                    rs.getInt("doctor_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("specialization") + " | " +
                    rs.getString("phone") + " | Dept ID: " +
                    rs.getInt("department_id")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void update(int doctorId, String newPhone) {
        String sql = "UPDATE doctor SET phone=? WHERE doctor_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPhone);
            ps.setInt(2, doctorId);
            ps.executeUpdate();
            System.out.println("Doctor updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int doctorId) {
        String sql = "DELETE FROM doctor WHERE doctor_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.executeUpdate();
            System.out.println("Doctor deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
