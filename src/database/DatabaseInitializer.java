
package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseInitializer {

    public static boolean initializeDatabase() {

        String createDatabase =
                "CREATE DATABASE IF NOT EXISTS student_management";

        String createTable =
                "CREATE TABLE IF NOT EXISTS student_management.students (" +
                        "rollno INT PRIMARY KEY, " +
                        "name VARCHAR(100) NOT NULL, " +
                        "email VARCHAR(100), " +
                        "course VARCHAR(50), " +
                        "marks DOUBLE)";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            st.executeUpdate(createDatabase);
            System.out.println("Database Ready!");

            st.executeUpdate(createTable);
            System.out.println("Students Table Ready!");
            return true;

        } catch (SQLException e) {
            System.out.println("Database setup failed!");
            System.out.println(e.getMessage());
            return false;
        }
    }
}