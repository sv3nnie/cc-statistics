package application.controllers;

import java.sql.SQLException;
import java.time.LocalDate;

public class EnrollmentDatabase extends Database {

    public EnrollmentDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    //check for duplicate enrollments
    public boolean checkDuplicate(String email, String courseName) {
        try {
            connectDatabase();

            String SQL = "SELECT EmailAddress FROM Enrollment WHERE EmailAddress = \'" + email
                    + "\' AND CourseName = \'" + courseName + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            if (resultSet.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return false;
    }

    //add a new enrollment to the database
    public void addEnrollment(String email, String courseName) throws SQLException {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        LocalDate date = LocalDate.of(year, month, day);
        String query = "INSERT INTO Enrollment(EmailAddress, CourseName, EnrollmentDate) VALUES (\'" + email + "\',\'"
                + courseName + "\',\'" + date + "\')";
        statement.executeUpdate(query);
    }
}