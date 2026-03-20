package My_Shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Configure {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url,user,password);
             Statement statement = connection.createStatement()){

            statement.executeUpdate("DROP DATABASE IF EXISTS shop");
            statement.executeUpdate("CREATE DATABASE shop");
            statement.executeUpdate("USE shop");

            System.out.println("Database shop created successfully!");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS My_Shop (" +
                    "FOOD_ID int PRIMARY KEY, FOOD_NAME varchar(250), FOOD_STOCK int, FOOD_PRICE double)");

            statement.executeUpdate("INSERT VALUES INTO shop (" +
                            "0001, 'Odong', 105, 20)" );
            statement.executeUpdate("INSERT VALUES INTO shop (" +
                    "0002, 'Miswa', 55, 25)" );
            statement.executeUpdate("INSERT VALUES INTO shop (" +
                    "0003, 'Tinapa', 90, 25)" );
            statement.executeUpdate("INSERT VALUES INTO shop (" +
                    "0004, 'Beef Loaf', 105, 35)" );

            System.out.println("Table created successfully!");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

