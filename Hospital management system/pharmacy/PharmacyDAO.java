package com.hms.pharmacy;

import java.sql.*;
import com.hms.db.DBConnection;

public class PharmacyDAO {

    public void create(Medicine m) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO medical_store(medicine_name,category,expiry_date,quantity,price) VALUES(?,?,?,?,?)")) {

            ps.setString(1, m.getMedicineName());
            ps.setString(2, m.getCategory());
            ps.setString(3, m.getExpiryDate());
            ps.setInt(4, m.getQuantity());
            ps.setDouble(5, m.getPrice());
            ps.executeUpdate();
            System.out.println("Medicine added");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void getAll() {
        try (Connection con = DBConnection.getConnection();
             ResultSet rs = con.prepareStatement("SELECT * FROM medical_store").executeQuery()) {

            while (rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" Qty:"+rs.getInt(5));

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(int id, int qty) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "UPDATE medical_store SET quantity=? WHERE medicine_id=?")) {

            ps.setInt(1, qty);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Medicine updated");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM medical_store WHERE medicine_id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Medicine deleted");

        } catch (Exception e) { e.printStackTrace(); }
    }
}
