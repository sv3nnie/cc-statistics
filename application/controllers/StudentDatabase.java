package application.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDatabase extends Database {

    public StudentDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    public ArrayList getStudents() {
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
        return "None";
    }

    public void addStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }

    public void removeStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }
}