
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import student.Student;

public class StudentDAO {

    // Add Student
    public boolean addStudent(Student student) throws SQLException {

        String query = "INSERT INTO student_management.students "
                + "(rollno, name, email, course, marks) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, student.getRollno());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());
            ps.setDouble(5, student.getMarks());

            return ps.executeUpdate() > 0;
        }
    }

    // View All Students
    public ArrayList<Student> getAllStudents() throws SQLException {

        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT * FROM student_management.students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("rollno"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getDouble("marks")
                );

                students.add(student);
            }
        }

        return students;
    }

    // Search Student by Roll Number
    public Student searchStudent(int rollno) throws SQLException {

        String query = "SELECT * FROM student_management.students "
                + "WHERE rollno = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, rollno);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Student(
                            rs.getInt("rollno"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("course"),
                            rs.getDouble("marks")
                    );
                }
            }
        }
        return null;
    }

    // Search Student by Email
    public Student searchStudentByEmail(String email) throws SQLException {

        String sql = "SELECT * FROM student_management.students WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("rollno"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("course"),
                            rs.getDouble("marks")
                    );
                }
            }
        }
        return null;
    }

    // Update Student
    public boolean updateStudent(Student student) throws SQLException {

        String query = "UPDATE student_management.students "
                + "SET name = ?, email = ?, course = ?, marks = ? "
                + "WHERE rollno = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setDouble(4, student.getMarks());
            ps.setInt(5, student.getRollno());

            return ps.executeUpdate() > 0;
        }
    }

    // Delete Student
    public boolean deleteStudent(int rollno) throws SQLException {

        String query = "DELETE FROM student_management.students "
                + "WHERE rollno = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, rollno);

            return ps.executeUpdate() > 0;
        }
    }

    // Count Total Students
    public int getStudentCount() throws SQLException {

        String query = "SELECT COUNT(*) FROM student_management.students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }
}