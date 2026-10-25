
package validation;

import java.util.Scanner;

public class Validation {

    // Roll Number Validation
    public static int readRollNo(Scanner sc) {
        while (true) {
            System.out.print("Enter Roll Number: ");

            if (sc.hasNextInt()) {
                int rollno = sc.nextInt();
                sc.nextLine();

                if (rollno > 0) {
                    return rollno;
                }

                System.out.println("Roll number must be greater than 0.");
            } else {
                System.out.println("Invalid input! Enter a whole number.");
                sc.nextLine();
            }
        }
    }

    // Name Validation
    public static String readName(Scanner sc) {
        while (true) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();

            if (name.matches("[a-zA-Z ]+") && !name.isEmpty()) {
                return name;
            }

            System.out.println("Invalid name! Use letters and spaces only.");
        }
    }

    // Email Validation
    public static String readEmail(Scanner sc) {
        while (true) {
            System.out.print("Enter Email: ");
            String email = sc.nextLine().trim();

            if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return email;
            }

            System.out.println("Invalid email! Please enter a valid email address.");
        }
    }

    // Course Validation
    public static String readCourse(Scanner sc) {
        while (true) {
            System.out.print("Enter Course: ");
            String course = sc.nextLine().trim();

            if (course.matches("[a-zA-Z0-9 .-]+") && !course.isEmpty()) {
                return course;
            }

            System.out.println("Invalid course! Please enter a valid course name.");
        }
    }

    // Marks Validation
    public static double readMarks(Scanner sc) {
        while (true) {
            System.out.print("Enter Marks (0-100): ");

            if (sc.hasNextDouble()) {
                double marks = sc.nextDouble();
                sc.nextLine();

                if (Double.isFinite(marks) && marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println("Marks must be between 0 and 100.");
            } else {
                System.out.println("Invalid marks! Please enter a number.");
                sc.nextLine();
            }
        }
    }
}