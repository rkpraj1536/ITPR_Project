package com.hms.appointment;

import java.util.Scanner;

public class AppointmentMenu {
    private static final AppointmentDAO dao = new AppointmentDAO();

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Appointment ---");
            System.out.println("1.Create 2.View 3.Update 4.Delete 0.Back");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    Appointment a = new Appointment();
                    System.out.print("Patient ID: "); a.setPatientId(sc.nextInt());
                    System.out.print("Doctor ID: "); a.setDoctorId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Date (YYYY-MM-DD): "); a.setAppointmentDate(sc.nextLine());
                    System.out.print("Time (HH:MM): "); a.setAppointmentTime(sc.nextLine());
                    dao.create(a);
                }
                case 2 -> dao.getAll();
                case 3 -> {
                    System.out.print("Appointment ID: "); int id=sc.nextInt(); sc.nextLine();
                    System.out.print("New Date: "); String d=sc.nextLine();
                    System.out.print("New Time: "); String t=sc.nextLine();
                    dao.update(id, d, t);
                }
                case 4 -> {
                    System.out.print("Appointment ID: ");
                    dao.delete(sc.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }
}
