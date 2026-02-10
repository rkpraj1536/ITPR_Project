package com.hms.department;

import java.util.Scanner;

public class DepartmentMenu {
    private static final DepartmentDAO dao = new DepartmentDAO();

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Department ---");
            System.out.println("1.Create 2.View 3.Update 4.Delete 0.Back");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Name: ");
                    Department d = new Department();
                    d.setDepartmentName(sc.nextLine());
                    dao.create(d);
                }
                case 2 -> dao.getAll();
                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: ");
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
