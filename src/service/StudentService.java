
package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import database.StudentDAO;
import student.Student;
import validation.Validation;

public class StudentService {

    private Scanner sc;
    private StudentDAO dao = new StudentDAO();
    public StudentService(Scanner sc) {
        this.sc = sc;
    }

    // Add Student
    public void addStudent() throws SQLException {

        int rollno;
        while (true) {
            rollno = Validation.readRollNo(sc);
            Student existingStudent = dao.searchStudent(rollno);
            if (existingStudent == null) {
                break;
            }
            System.out.println(
                    "Roll number already exists! Please enter a different roll number."
            );
        }
        String name = Validation.readName(sc);
        String email;
        while (true) {
            email = Validation.readEmail(sc);
            Student existingStudent = dao.searchStudentByEmail(email);
            if (existingStudent == null) {
                break;
            }
            System.out.println(
                    "Email already exists! Please enter a different email."
            );
        }
        String course = Validation.readCourse(sc);
        double marks = Validation.readMarks(sc);

        // Create Student object
        Student student = new Student(
                rollno, name, email, course, marks
        );

        // Save student in database
        if (dao.addStudent(student)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Student could not be added.");
        }
    }

    // View All Students
    public void viewStudents() throws SQLException {

        ArrayList<Student> students = dao.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n---------------------- Student Records ----------------------");
        System.out.printf("%-10s %-20s %-32s %-10s %-10s%n",
                "Roll No", "Name", "Email", "Course", "Marks");
        System.out.println("-----------------------------------------------------------------------------------");
        for (Student s : students) {
            System.out.printf("%-10d %-20s %-32s %-10s %-10.2f%n",
                    s.getRollno(),
                    s.getName(),
                    s.getEmail(),
                    s.getCourse(),
                    s.getMarks());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    // Search Student
    public void searchStudent() throws SQLException {

        //System.out.println("Enter the roll number of the student to search:");
        int rollno = Validation.readRollNo(sc);

        Student student = dao.searchStudent(rollno);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("\n----- Student Details -----");
            System.out.println("Roll Number: " + student.getRollno());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Marks: " + student.getMarks());
        }
    }

    // Update Student
    public void updateStudent() throws SQLException {

        //System.out.println("Enter the roll number of the student to update:");
        int rollno = Validation.readRollNo(sc);

        Student student = dao.searchStudent(rollno);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\nWhat do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Course");
        System.out.println("4. Marks");
        System.out.println("5. Cancel");

        int choice;
        while (true) {
            System.out.print("Enter your choice (1-5): ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= 5) {
                    break;
                }
                System.out.println("Invalid choice! Please select 1-5.");
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        }

        switch (choice) {

            case 1:
                student.setName(Validation.readName(sc));
                break;

            case 2:
                student.setEmail(Validation.readEmail(sc));
                break;

            case 3:
                student.setCourse(Validation.readCourse(sc));
                break;

            case 4:
                student.setMarks(Validation.readMarks(sc));
                break;

            case 5:
                System.out.println("Update cancelled.");
                return;

            default:
                System.out.println("Invalid choice!");
                return;
        }

        if (dao.updateStudent(student)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student could not be updated.");
        }
    }

    // Delete Student

    public void deleteStudent() throws SQLException {

        //System.out.println("Enter the roll number of the student to delete:");
        int rollno = Validation.readRollNo(sc);

        Student student = dao.searchStudent(rollno);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("\n----- Student Details -----");
        System.out.println("Roll Number: " + student.getRollno());
        System.out.println("Name: " + student.getName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Course: " + student.getCourse());
        System.out.println("Marks: " + student.getMarks());

        System.out.print("\nAre you sure you want to delete this student? (Y/N): ");
        String confirm = sc.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {
            if (dao.deleteStudent(rollno)) {
                System.out.println("Student record deleted successfully!");
            } else {
                System.out.println("Student record could not be deleted.");
            }
        } else if (confirm.equalsIgnoreCase("N")) {
            System.out.println("Delete cancelled.");
        } else {
            System.out.println("Invalid confirmation! Delete cancelled.");
        }
    }

    // Total Student Count
    public void showStudentCount() throws SQLException {
        int total = dao.getStudentCount();
        System.out.println("Total Students: " + total);
    }
}