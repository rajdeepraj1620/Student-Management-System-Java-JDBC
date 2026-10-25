
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/";
        String username = "your_DB_Username";

        // Use your local MySQL password here.
        String password = "your_DB_Password";

        return DriverManager.getConnection(
                url, username, password
        );
    }
}