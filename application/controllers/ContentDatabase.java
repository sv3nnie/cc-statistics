package application.controllers;

import java.util.ArrayList;
import java.sql.SQLException;

public class ContentDatabase extends Database {

    public ContentDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    public ArrayList<String> getAvailableModules() {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT * FROM Module WHERE ContentItemId IS NULL AND CourseName IS NULL";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add(resultSet.getString("Title"));
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    public ArrayList<String> getAvailableContent() {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT * FROM Module WHERE ContentItemId IS NULL AND CourseName IS NULL";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add(resultSet.getString("Title"));
            }

            SQL = "SELECT Title FROM Webcast WHERE ContentItemId IN (SELECT ContentItemId FROM Content WHERE CourseName IS NULL)";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add(resultSet.getString("Title"));
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    public void editModule(String module, String courseName) throws SQLException {
        String query = "UPDATE Module SET CourseName = \'" + courseName + "\' WHERE Title = \'" + module + "\'";
        statement.executeUpdate(query);
    }
}