package application.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDatabase extends Database {

    public StudentDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    // get all students from the database
    public ArrayList<String> getStudents() {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT * FROM Student";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add(resultSet.getString("EmailAddress"));
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    // check for duplicate students in the database (used when creating a new
    // student)
    public boolean checkDuplicate(String email) {
        try {
            connectDatabase();

            String SQL = "SELECT EmailAddress FROM Student WHERE EmailAddress = \'" + email + "\'";
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

    // get the name from a student from the database using their e-mailaddress
    public String getName(String email) {
        try {
            connectDatabase();

            String SQL = "SELECT Name FROM Student WHERE EmailAddress = \'" + email + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                return resultSet.getString("Name");
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return "";
    }

    // get any info (address, postalcode, etc.) from the database using the
    // students' e-mailaddress
    public String getInfo(String email, String item) {
        try {
            connectDatabase();

            String SQL = "SELECT " + item + " FROM Student WHERE EmailAddress = \'" + email + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                return resultSet.getString(item);
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return "";
    }

    // get all certificates from selected student email
    public String getCertificates(String email) {
        String results = "";
        try {
            connectDatabase();

            String SQL = "SELECT * FROM Certificate WHERE StudentEmailAddress = \'" + email + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results = results + resultSet.getInt("CertificateId") + resultSet.getDouble("Grade")
                        + resultSet.getString("Employee") + resultSet.getString("CourseName")
                        + resultSet.getDate("EnrollmentDate") + "\n";
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    // add a new student to the database
    public void addStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }

    // remove a student from the database
    public void removeStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }
}