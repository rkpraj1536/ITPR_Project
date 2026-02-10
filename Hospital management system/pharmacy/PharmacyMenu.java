package com.hms.pharmacy;

import java.util.Scanner;

public class PharmacyMenu {
    private static final PharmacyDAO dao = new PharmacyDAO();

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Pharmacy ---");
            System.out.println("1.Create 2.View 3.Update 4.Delete 0.Back");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    Medicine m = new Medicine();
                    System.out.print("Name: "); m.setMedicineName(sc.nextLine());
                    System.out.print("Category: "); m.setCategory(sc.nextLine());
                    System.out.print("Expiry Date: "); m.setExpiryDate(sc.nextLine());
                    System.out.print("Quantity: "); m.setQuantity(sc.nextInt());
                    System.out.print("Price: "); m.setPrice(sc.nextDouble());
                    dao.create(m);
                }
                case 2 -> dao.getAll();
                case 3 -> {
                    System.out.print("Medicine ID: "); int id=sc.nextInt();
                    System.out.print("New Quantity: "); int q=sc.nextInt();
                    dao.update(id, q);
                }
                case 4 -> {
                    System.out.print("Medicine ID: ");
                    dao.delete(sc.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }
}
