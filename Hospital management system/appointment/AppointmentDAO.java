package com.hms.appointment;

import java.sql.*;
import com.hms.db.DBConnection;

public class AppointmentDAO {

    public void create(Appointment a) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO appointment(patient_id,doctor_id,appointment_date,appointment_time) VALUES(?,?,?,?)")) {

            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setString(3, a.getAppointmentDate());
            ps.setString(4, a.getAppointmentTime());
            ps.executeUpdate();
            System.out.println("Appointment created");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void getAll() {
        try (Connection con = DBConnection.getConnection();
             ResultSet rs = con.prepareStatement("SELECT * FROM appointment").executeQuery()) {

            while (rs.next())
                System.out.println(rs.getInt(1)+" P:"+rs.getInt(2)+" D:"+rs.getInt(3));

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void update(int id, String date, String time) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "UPDATE appointment SET appointment_date=?, appointment_time=? WHERE appointment_id=?")) {

            ps.setString(1, date);
            ps.setString(2, time);
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Appointment updated");

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM appointment WHERE appointment_id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Appointment deleted");

        } catch (Exception e) { e.printStackTrace(); }
    }
}
