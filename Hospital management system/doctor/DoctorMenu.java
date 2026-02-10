package com.hms.doctor;

import java.util.Scanner;

public class DoctorMenu {

    private static final DoctorDAO dao = new DoctorDAO();

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Doctor Management ---");
            System.out.println("1. Create Doctor");
            System.out.println("2. View Doctors");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("0. Back");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    Doctor d = new Doctor();

                    System.out.print("Doctor Name: ");
                    d.setName(sc.nextLine());   // ✅ fixed

                    System.out.print("Specialization: ");
                    d.setSpecialization(sc.nextLine());

                    System.out.print("Phone: ");
                    d.setPhone(sc.nextLine());

                    System.out.print("Department ID: ");
                    d.setDepartmentId(sc.nextInt());

                    dao.create(d);
                }

                case 2 -> dao.getAll();

                case 3 -> {
                    System.out.print("Doctor ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Phone: ");
                    dao.update(id, sc.nextLine());
                }

                case 4 -> {
                    System.out.print("Doctor ID: ");
                    dao.delete(sc.nextInt());
                }

                case 0 -> {
                    return;
                }
            }
        }
    }
}
