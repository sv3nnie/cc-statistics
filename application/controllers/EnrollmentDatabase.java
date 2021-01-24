package application.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EnrollmentDatabase extends Database {

    public EnrollmentDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    // check for duplicate enrollments
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

    // add a new enrollment to the database
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

    //get the date from a student enrollment
    public String getDate(String email, String courseName) {
        try {
            connectDatabase();

            String SQL = "SELECT EnrollmentDate FROM Enrollment WHERE EmailAddress = \'" + email
                    + "\' AND CourseName = \'" + courseName + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                return resultSet.getString("EnrollmentDate");
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return "";
    }

    //add certificate to a student in database
    public void addCertificate(String email, String courseName, String employee, double grade) throws SQLException {
        LocalDate localDate = LocalDate.parse(getDate(email, courseName));
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        LocalDate date = LocalDate.of(year, month, day);
        String query = "INSERT INTO Certificate(Grade, Employee, StudentEmailAddress, CourseName, EnrollmentDate) VALUES (\'"
                + grade + "\',\'" + employee + "\',\'" + email + "\',\'" + courseName + "\',\'" + date + "\')";
        statement.executeUpdate(query);
    }

    //get all available certificates from a student
    public ArrayList<String> getAvailableCertificates(String email) {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT CourseName FROM Enrollment WHERE EmailAddress = \'" + email
                    + "\' AND CourseName NOT IN(SELECT CourseName FROM Certificate WHERE StudentEmailAddress = \'"
                    + email + "\')";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add(resultSet.getString("CourseName"));
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

}