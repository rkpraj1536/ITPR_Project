package com.hms.department;

import java.sql.*;
import com.hms.db.DBConnection;

public class DepartmentDAO {

    public void create(Department d) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
              con.prepareStatement("INSERT INTO department(department_name) VALUES(?)")) {
            ps.setString(1, d.getDepartmentName());
            ps.executeUpdate();
            System.out.println("Department added");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void getAll() {
        try (Connection con = DBConnection.getConnection();
             ResultSet rs =
              con.prepareStatement("SELECT * FROM department").executeQuery()) {
            while (rs.next())
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(int id, String name) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
              con.prepareStatement("UPDATE department SET department_name=? WHERE department_id=?")) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Department updated");
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
              con.prepareStatement("DELETE FROM department WHERE department_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Department deleted");
        } catch (Exception e) { e.printStackTrace(); }
    }
}
