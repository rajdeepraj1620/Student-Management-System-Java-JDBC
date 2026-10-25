
package main;

import java.util.Scanner;
import java.sql.SQLException;

import database.DatabaseInitializer;
import service.StudentService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService(sc);

        if (!DatabaseInitializer.initializeDatabase()) {
            System.out.println("Application cannot start without database.");
            return;
        }

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Total Student Count");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number from 1 to 7.");
                sc.nextLine();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {

                    case 1:
                        service.addStudent();
                        break;

                    case 2:
                        service.viewStudents();
                        break;

                    case 3:
                        service.searchStudent();
                        break;

                    case 4:
                        service.updateStudent();
                        break;

                    case 5:
                        service.deleteStudent();
                        break;

                    case 6:
                        service.showStudentCount();
                        break;

                    case 7:
                        System.out.println("Thank you for using the system!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Select 1-7.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }
}