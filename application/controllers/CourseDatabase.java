package application.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDatabase extends Database {

    public CourseDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    public ArrayList<String> getCourses() {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT * FROM Course";
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

    public boolean checkDuplicate(String courseName) {
        try {
            connectDatabase();

            String SQL = "SELECT CourseName FROM Course WHERE CourseName = \'" + courseName + "\'";
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

    public void addCourse(String courseName, String subject, String difficulty, String introductionText)
            throws SQLException {
        String query = "INSERT INTO Course(CourseName, Subject, Difficulty, IntroductionText) VALUES (\'" + courseName
                + "\',\'" + subject + "\',\'" + difficulty + "\',\'" + introductionText + "\')";
        statement.executeUpdate(query);
    }
}