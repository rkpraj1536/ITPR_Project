package com.hms.patient;

import java.util.Scanner;

public class PatientMenu {
    private static final PatientDAO dao = new PatientDAO();

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Patient ---");
            System.out.println("1.Create 2.View 3.Update 4.Delete 0.Back");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    Patient p = new Patient();
                    System.out.print("Name: "); p.setPatientName(sc.nextLine());
                    System.out.print("Gender: "); p.setGender(sc.nextLine());
                    System.out.print("Age: "); p.setAge(sc.nextInt()); sc.nextLine();
                    System.out.print("Phone: "); p.setPhone(sc.nextLine());
                    System.out.print("Address: "); p.setAddress(sc.nextLine());
                    dao.create(p);
                }
                case 2 -> dao.getAll();
                case 3 -> {
                    System.out.print("ID: "); int id=sc.nextInt(); sc.nextLine();
                    System.out.print("New Phone: ");
                    dao.update(id, sc.nextLine());
                }
                case 4 -> {
                    System.out.print("ID: ");
                    dao.delete(sc.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }
}
