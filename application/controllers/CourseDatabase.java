package application.controllers;

import java.util.ArrayList;

public class CourseDatabase extends Database {

    public CourseDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    public ArrayList getCourses() {
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
}