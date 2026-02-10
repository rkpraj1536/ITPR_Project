package com.hms.lab;

import java.sql.*;
import com.hms.db.DBConnection;

public class LabTestDAO {

    public void create(LabTest l) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO lab_test(patient_id,test_name,test_date,result) VALUES(?,?,?,?)")) {

            ps.setInt(1, l.getPatientId());
            ps.setString(2, l.getTestName());
            ps.setString(3, l.getTestDate());
            ps.setString(4, l.getResult());
            ps.executeUpdate();
            System.out.println("Lab test added");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void getAll() {
        try (Connection con = DBConnection.getConnection();
             ResultSet rs = con.prepareStatement("SELECT * FROM lab_test").executeQuery()) {

            while (rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(3)+" "+rs.getString(5));

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(int id, String result) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "UPDATE lab_test SET result=? WHERE test_id=?")) {

            ps.setString(1, result);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Lab test updated");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM lab_test WHERE test_id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Lab test deleted");

        } catch (Exception e) { e.printStackTrace(); }
    }
}
