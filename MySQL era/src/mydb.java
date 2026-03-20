import java.sql.*;

public class mydb {

    Connection connection;
    private void displaySQLErrors(SQLException e){
        System.out.println("SQLException:"+e.getMessage());
        System.out.println("SQLState:" + e.getSQLState());
        System.out.println("VendorError:"+e.getErrorCode());
    }

    public mydb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.print("Connected successfully!");
        } catch(Exception e) {
            System.err.println("Unable to find and load driver");
            System.exit(1);
        }
    }

    public void ToDB() {
        try {
            connection=DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/","root", "");
        }
        catch(SQLException e) {
            displaySQLErrors(e);
        }
    }


    public void insertDatabase(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "CREATE DATABASE mydb";
            int result = stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void useDatabase(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "USE mydb";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void table1(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Sales_Rep (SLSREP_NUMBER INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, LAST VARCHAR(25), FIRST VARCHAR(25), STREET VARCHAR(50), CITY VARCHAR(25), STATE VARCHAR(25), ZIP_CODE VARCHAR(15), TOTAL_COMMISSION DOUBLE, COMMISSION_RATE DOUBLE)";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void table2(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Customer (CUSTOMER_NUMBER INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, LAST VARCHAR(25), FIRST VARCHAR(25), STREET VARCHAR(50), CITY VARCHAR(25), STATE VARCHAR(25), ZIP_CODE VARCHAR(15), BALANCE DOUBLE, CREDIT_LIMIT DOUBLE, SLSREP_NUMBER INT(11))";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void table3(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Orders (ORDER_NUMBER INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT, ORDER_DATE DATE, CUSTOMER_NUMBER INT(11))";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void table4(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Part (PART_NUMBER VARCHAR(50) PRIMARY KEY NOT NULL, PART_DESCRIPTION VARCHAR(50), UNITS_ON_HAND INT(11), ITEM_CLASS VARCHAR(25), WAREHOUSE_NUMBER INT(11), UNIT_PRICE DOUBLE)";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void table5(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE Order_Line (ORDER_NUMBER INT(11), PART_NUMBER VARCHAR(25), NUMBER_ORDERED INT(11), QUOTED_PRICE DOUBLE)";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void values1(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Sales_Rep VALUES(3, 'Jones', 'Mary', '123 Main', 'Grant', 'MI', '49219', 2150, 0.05), (6, 'Smith', 'William', '102 Raymond', 'Ada', 'MI', '49441', 4912.5, 0.07), (12, 'Diaz', 'Miguel', '419 Harper', 'Lansing', 'MI', '49224', 2150, 0.05)";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void values2(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Customer (CUSTOMER_NUMBER, LAST, FIRST, STREET, CITY, STATE, ZIP_CODE, BALANCE, CREDIT_LIMIT, SLSREP_NUMBER) VALUES\n" +"(124, 'Adams', 'Sally', '481 Oak', 'Lansing', 'MI', '49224', 818.75, 1000, 3),\n" +"(256, 'Samuels', 'Ann', '215 Pete', 'Grant', 'MI', '49219', 21.5, 1500, 6),\n" +"(311, 'Charles', 'Don', '48 College', 'Ira', 'MI', '49034', 825.75, 1500, 12),\n" +"(315, 'Daniels', 'Tom', '914 Cherry', 'Kent', 'MI', '48391', 770.75, 750, 6),\n" +"(405, 'Williams', 'Al', '519 Watson', 'Grant', 'MI', '49219', 402.75, 1500, 12),\n" +"(412, 'Adams', 'Sally', '16 Elm', 'Lansing', 'MI', '49224', 1817.75, 2000, 3),\n" +"(522, 'Nelson', 'Mary', '108 Pine', 'Ada', 'MI', '49441', 98.75, 1500, 6),\n" +"(567, 'Dirh', 'Tran', '808 Ridge', 'Harper', 'MI', '48421', 402.4, 750, 6),\n" +"(587, 'Galves', 'Mara', '512 Pine', 'Ada', 'MI', '49441', 114.6, 1000, 6),\n" +"(622, 'Martin', 'Dan', '419 Chip', 'Grant', 'MI', '49219', 1045.75, 1000, 3);\n";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void values3(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Orders (ORDER_NUMBER, ORDER_DATE, CUSTOMER_NUMBER) VALUES\n" +"(12489, '2002-09-02', 124),\n" +"(12491, '2002-09-02', 311),\n" +"(12494, '2002-09-04', 315),\n" +"(12495, '2002-09-04', 256),\n" +"(12498, '2002-09-05', 522),\n" +"(12500, '2002-09-05', 124),\n" +"(12504, '2002-09-05', 522);\n";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void values4(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Part (PART_NUMBER, PART_DESCRIPTION, UNITS_ON_HAND, ITEM_CLASS, WAREHOUSE_NUMBER, UNIT_PRICE) VALUES\n" +"('AX12', 'Iron', 104, 'HW', 3, 24.95),\n" +"('AZ52', 'Dartboard', 20, 'SG', 2, 12.95),\n" +"('BA74', 'Basketball', 40, 'SG', 1, 29.95),\n" +"('BH22', 'Compopper', 95, 'HW', 3, 24.95),\n" +"('BT04', 'Gas Grill', 11, 'AP', 1, 149.99),\n" +"('BX66', 'Washer', 52, 'AP', 2, 399.99),\n" +"('CA14', 'Griddle', 78, 'HW', 3, 39.99),\n" +"('CB03', 'Bike', 44, 'SG', 4, 299.99),\n" +"('CX11', 'Blender', 112, 'HW', 3, 22.95),\n" +"('CZ81', 'Treadmill', 68, 'SG', 2, 349.95);\n";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }

    public void values5(){
        try{
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Order_Line (ORDER_NUMBER, PART_NUMBER, NUMBER_ORDERED, QUOTED_PRICE) VALUES\n" +"(12489, 'AX12', 11, 21.95),\n" +"(12498, 'AZ52', 2, 12.95),\n" +"(12498, 'BA74', 4, 24.95),\n" +"(12491, 'BT04', 1, 149.99),\n" +"(12500, 'BT04', 1, 149.99),\n" +"(12491, 'BX66', 4, 399.99),\n" +"(12494, 'CB03', 2, 279.99),\n" +"(12495, 'CX11', 2, 22.95),\n" +"(12504, 'CZ81', 2, 325.99);\n";
            int result = stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e){
            displaySQLErrors(e);
        }
    }


    public static void main(String[] args) {
        mydb hello = new mydb();
        hello.ToDB();
        hello.insertDatabase();
        hello.useDatabase();
        hello.table1();
        hello.table2();
        hello.table3();
        hello.table4();
        hello.table5();
        hello.values1();
        hello.values2();
        hello.values3();
        hello.values4();
        hello.values5();
    }
}