package com.hms.billing;

import java.sql.*;
import com.hms.db.DBConnection;

public class BillingDAO {

    public void create(Bill b) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO billing(patient_id,bill_date,amount,status) VALUES(?,CURDATE(),?,?)")) {

            ps.setInt(1, b.getPatientId());
            ps.setDouble(2, b.getAmount());
            ps.setString(3, b.getStatus());
            ps.executeUpdate();
            System.out.println("Bill generated");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void getAll() {
        try (Connection con = DBConnection.getConnection();
             ResultSet rs = con.prepareStatement("SELECT * FROM billing").executeQuery()) {

            while (rs.next())
                System.out.println(rs.getInt(1)+" Patient:"+rs.getInt(2)+" Amount:"+rs.getDouble(4));

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(int id, String status) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "UPDATE billing SET status=? WHERE bill_id=?")) {

            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Bill updated");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM billing WHERE bill_id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Bill deleted");

        } catch (Exception e) { e.printStackTrace(); }
    }
}
