package BookSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class config {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";

        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()
        ) {

            String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS DB2";
            statement.executeUpdate(sqlCreateDB);
            System.out.println("Database DB2 created successfully.");

            statement.executeUpdate("USE DB2");
            System.out.println("Switched to database 'DB2'.");

            String sqlCreateBookTable = "CREATE TABLE IF NOT EXISTS Book (" +
                    "BookCode VARCHAR(10) PRIMARY KEY NOT NULL," +
                    "Title VARCHAR(255) NOT NULL," +
                    "Type VARCHAR(100)," +
                    "Price DOUBLE," +
                    "Quantity INT," +
                    "Sales DOUBLE" +
                    ")";
            statement.executeUpdate(sqlCreateBookTable);
            System.out.println("Table Book created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
