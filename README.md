# Hospital Management System

A console-based Hospital Management System built with **Java**, **JDBC**, and **MySQL**. It manages departments, doctors, patients, appointments, lab tests, pharmacy stock, and billing through a simple menu-driven interface with full CRUD (Create, Read, Update, Delete) support.

## Features

| Module | What you can do |
|---|---|
| **Department** | Add, view, rename, and delete hospital departments |
| **Doctor** | Register doctors with specialization, phone, and department; update phone; delete |
| **Patient** | Register patients (name, gender, age, phone, address); update phone; delete |
| **Appointment** | Book appointments between a patient and a doctor; reschedule date/time; cancel |
| **Lab Test** | Record lab tests for a patient; update results; delete |
| **Pharmacy** | Manage medicine stock (category, expiry date, quantity, price); update quantity; delete |
| **Billing** | Generate bills for patients (date set automatically); update payment status; delete |

## Tech Stack

- **Language:** Java 17
- **Database:** MySQL 8
- **Connectivity:** JDBC (`mysql-connector-java` 8.0.33)
- **Build tool:** Maven
- **Testing:** JUnit 5 (dependency configured)

## Project Structure

```
Hospital management system/
├── main/          MainApp.java          # Entry point and main menu
├── db/            DBConnection.java     # JDBC connection setup
├── department/    Department, DepartmentDAO, DepartmentMenu
├── doctor/        Doctor, DoctorDAO, DoctorMenu
├── patient/       Patient, PatientDAO, PatientMenu
├── appointment/   Appointment, AppointmentDAO, AppointmentMenu
├── lab/           LabTest, LabTestDAO, LabTestMenu
├── pharmacy/      Medicine, PharmacyDAO, PharmacyMenu
├── billing/       Bill, BillingDAO, BillingMenu
└── pom.xml
```

Each module follows the same three-part pattern:

- **Model** – a plain Java class holding the entity's fields
- **DAO** – database access using `PreparedStatement` for all queries
- **Menu** – the console interface for that module

## Prerequisites

- JDK 17 or later
- MySQL Server 8.x
- Maven 3.8+ (or compile with `javac` directly)

## Database Setup

1. Start MySQL and create the database:

   ```sql
   CREATE DATABASE hospital_db;
   USE hospital_db;
   ```

2. Create the tables. The schema below matches the table and column names used in the code:

   ```sql
   CREATE TABLE department (
       department_id   INT AUTO_INCREMENT PRIMARY KEY,
       department_name VARCHAR(100) NOT NULL
   );

   CREATE TABLE doctor (
       doctor_id      INT AUTO_INCREMENT PRIMARY KEY,
       name           VARCHAR(100) NOT NULL,
       specialization VARCHAR(100),
       phone          VARCHAR(15),
       department_id  INT,
       FOREIGN KEY (department_id) REFERENCES department(department_id)
   );

   CREATE TABLE patient (
       patient_id   INT AUTO_INCREMENT PRIMARY KEY,
       patient_name VARCHAR(100) NOT NULL,
       gender       VARCHAR(10),
       age          INT,
       phone        VARCHAR(15),
       address      VARCHAR(255)
   );

   CREATE TABLE appointment (
       appointment_id   INT AUTO_INCREMENT PRIMARY KEY,
       patient_id       INT,
       doctor_id        INT,
       appointment_date DATE,
       appointment_time TIME,
       FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
       FOREIGN KEY (doctor_id)  REFERENCES doctor(doctor_id)
   );

   CREATE TABLE lab_test (
       test_id    INT AUTO_INCREMENT PRIMARY KEY,
       patient_id INT,
       test_name  VARCHAR(100),
       test_date  DATE,
       result     VARCHAR(255),
       FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
   );

   CREATE TABLE medical_store (
       medicine_id   INT AUTO_INCREMENT PRIMARY KEY,
       medicine_name VARCHAR(100) NOT NULL,
       category      VARCHAR(50),
       expiry_date   DATE,
       quantity      INT,
       price         DECIMAL(10,2)
   );

   CREATE TABLE billing (
       bill_id    INT AUTO_INCREMENT PRIMARY KEY,
       patient_id INT,
       bill_date  DATE,
       amount     DECIMAL(10,2),
       status     VARCHAR(20),
       FOREIGN KEY (patient_id) REFERENCES patient(patient_id)
   );
   ```

   > Data types and constraints above are a suggested setup. Adjust them if your own schema differs.

## Configuration

Open `db/DBConnection.java` and set your own MySQL credentials:

```java
private static final String URL =
    "jdbc:mysql://localhost:3306/hospital_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";
```

> **Security note:** Never commit real passwords to GitHub. Keep your credentials local, or read them from environment variables instead of hardcoding them.

## How to Run

### Option 1: Maven

Maven expects sources under `src/main/java/com/hms/`. Move the module folders there first:

```
src/main/java/com/hms/
├── main/  db/  department/  doctor/  patient/
└── appointment/  lab/  pharmacy/  billing/
```

Then build and run:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.hms.main.MainApp"
```

### Option 2: Compile with javac

Download the MySQL Connector/J JAR, then from the project folder:

```bash
mkdir out
javac -cp mysql-connector-java-8.0.33.jar -d out $(find . -name "*.java")
java -cp "out:mysql-connector-java-8.0.33.jar" com.hms.main.MainApp
```

On Windows, use `;` instead of `:` in the classpath.

## Usage

When the app starts, you will see the main menu:

```
===== HOSPITAL MANAGEMENT SYSTEM =====
1.Department
2.Doctor
3.Patient
4.Appointment
5.Lab Test
6.Pharmacy
7.Billing
0.Exit
```

Enter a number to open a module, then choose **Create, View, Update, Delete**, or **Back**.

**Input formats:**

- Appointment date: `YYYY-MM-DD`
- Appointment time: `HH:MM`
- Use existing patient, doctor, and department IDs when creating linked records

**Suggested first-run order:** Department → Doctor → Patient → Appointment → Lab Test / Billing.

## Demo

A walkthrough video is included in the repository: `hospital management.mp4`.

## Known Limitations

- Menu input is read with `Scanner.nextInt()`, so entering text instead of a number will crash the app.
- Only selected fields can be updated (for example, phone for doctors and patients).
- No user login or role-based access.
- No input validation for dates, phone numbers, or foreign keys beyond what the database enforces.

## Future Improvements

- Input validation and error handling
- Login with admin, doctor, and receptionist roles
- Search and filter records
- Full-record updates
- Unit tests for the DAO layer
- A GUI or web front end (Swing, JavaFX, or Spring Boot)

## Author

**Rahul Prajapati**
B.Tech Computer Science Engineering, Dr. Kedarnath Modi Institute of Engineering & Technology (AKTU)
GitHub: [rkpraj1536](https://github.com/rkpraj1536) | LinkedIn: [rahul-217507-prajapati](https://www.linkedin.com/in/rahul-217507-prajapati)
