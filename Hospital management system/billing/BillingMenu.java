package com.hms.billing;

import java.util.Scanner;

public class BillingMenu {
    private static final BillingDAO dao = new BillingDAO();

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Billing ---");
            System.out.println("1.Create 2.View 3.Update 4.Delete 0.Back");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    Bill b = new Bill();
                    System.out.print("Patient ID: "); b.setPatientId(sc.nextInt());
                    System.out.print("Amount: "); b.setAmount(sc.nextDouble());
                    b.setStatus("PAID");
                    dao.create(b);
                }
                case 2 -> dao.getAll();
                case 3 -> {
                    System.out.print("Bill ID: "); int id=sc.nextInt(); sc.nextLine();
                    System.out.print("New Status: ");
                    dao.update(id, sc.nextLine());
                }
                case 4 -> {
                    System.out.print("Bill ID: ");
                    dao.delete(sc.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }
}
