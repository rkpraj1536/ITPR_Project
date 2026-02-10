package com.hms.main;

import java.util.Scanner;
import com.hms.department.DepartmentMenu;
import com.hms.doctor.DoctorMenu;
import com.hms.patient.PatientMenu;
import com.hms.appointment.AppointmentMenu;
import com.hms.lab.LabTestMenu;
import com.hms.pharmacy.PharmacyMenu;
import com.hms.billing.BillingMenu;

public class MainApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1.Department");
            System.out.println("2.Doctor");
            System.out.println("3.Patient");
            System.out.println("4.Appointment");
            System.out.println("5.Lab Test");
            System.out.println("6.Pharmacy");
            System.out.println("7.Billing");
            System.out.println("0.Exit");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> DepartmentMenu.showMenu();
                case 2 -> DoctorMenu.showMenu();
                case 3 -> PatientMenu.showMenu();
                case 4 -> AppointmentMenu.showMenu();
                case 5 -> LabTestMenu.showMenu();
                case 6 -> PharmacyMenu.showMenu();
                case 7 -> BillingMenu.showMenu();
                case 0 -> System.exit(0);
            }
        }
    }
}
