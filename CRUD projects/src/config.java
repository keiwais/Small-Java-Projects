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
            String sqlCreateDB = "CREATE DATABASE IF NOT EXISTS DB3";
            statement.executeUpdate(sqlCreateDB);

            statement.executeUpdate("USE DB3");

            String sqlCreateStudentTable = "CREATE TABLE IF NOT EXISTS EnrolledStudents (" +
                    "StudentID VARCHAR(10) PRIMARY KEY NOT NULL," +
                    "StudentName VARCHAR(64) NOT NULL," +
                    "Address VARCHAR(255)," +
                    "Course VARCHAR(64)," +
                    "Year INT" +
                    ")";

            String sqlCreateSubjectTable = "CREATE TABLE IF NOT EXISTS EnrolledSubjects (" +
                    "SubjectCode VARCHAR(10) PRIMARY KEY NOT NULL," +
                    "Description VARCHAR(64)," +
                    "Schedule VARCHAR(100)," +
                    "Teacher VARCHAR(64)," +
                    "Units INT," +
                    "Total INT" +
                    ")";

            statement.executeUpdate(sqlCreateStudentTable);
            statement.executeUpdate(sqlCreateSubjectTable);

            String insertStudentsDataSQL = "INSERT INTO EnrolledStudents (StudentID, StudentName, Address, Course, Year) VALUES " +
                    "('011', 'Aiden Cruz', 'Roxas City', 'BSIT', 2)," +
                    "('012', 'Bella Santos', 'Kalibo', 'BSIT', 1)," +
                    "('013', 'Carlos Dela Cruz', 'Iloilo City', 'BSBA', 3)," +
                    "('014', 'Diana Reyes', 'Roxas City', 'BSIT', 4)," +
                    "('015', 'Ethan Morales', 'Tagbilaran', 'BSCRIM', 2)," +
                    "('016', 'Faith Mendoza', 'Taguig', 'BSIT', 3)," +
                    "('017', 'Gino Ramos', 'Cebu City', 'BSIT', 1)," +
                    "('018', 'Hannah Lopez', 'Ormoc City', 'BSIT', 2)," +
                    "('019', 'Isla Navarro', 'Tacloban', 'BSBA', 4)," +
                    "('020', 'Jude Garcia', 'Taguig', 'BSCRIM', 1)";
            statement.executeUpdate(insertStudentsDataSQL);

            String insertSubjectsDataSQL = "INSERT INTO EnrolledSubjects (SubjectCode, Description, Schedule, Teacher, Units, Total) VALUES " +
                    "('CS101', 'Intro to Programming', 'M/W 9:00 AM - 10:30 AM', 'Perez', 3, 0)," +
                    "('CS102', 'Database Systems', 'T/Th 1:00 PM - 2:30 PM', 'Gomez', 3, 0)," +
                    "('CS103', 'Web Development', 'W/F 2:00 PM - 3:30 PM', 'Luna', 3, 0)," +
                    "('CS104', 'Data Structures', 'M/W 10:30 AM - 12:00 PM', 'Castro', 3, 0)," +
                    "('CS105', 'Computer Networks', 'T/Th 8:00 AM - 9:30 AM', 'Villanueva', 2, 0)";
            statement.executeUpdate(insertSubjectsDataSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
