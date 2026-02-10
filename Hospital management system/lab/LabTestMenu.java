package com.hms.lab;

import java.util.Scanner;

public class LabTestMenu {
    private static final LabTestDAO dao = new LabTestDAO();

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Lab Test ---");
            System.out.println("1.Create 2.View 3.Update 4.Delete 0.Back");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    LabTest l = new LabTest();
                    System.out.print("Patient ID: "); l.setPatientId(sc.nextInt()); sc.nextLine();
                    System.out.print("Test Name: "); l.setTestName(sc.nextLine());
                    System.out.print("Test Date: "); l.setTestDate(sc.nextLine());
                    System.out.print("Result: "); l.setResult(sc.nextLine());
                    dao.create(l);
                }
                case 2 -> dao.getAll();
                case 3 -> {
                    System.out.print("Test ID: "); int id=sc.nextInt(); sc.nextLine();
                    System.out.print("New Result: ");
                    dao.update(id, sc.nextLine());
                }
                case 4 -> {
                    System.out.print("Test ID: ");
                    dao.delete(sc.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }
}
