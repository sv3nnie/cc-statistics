package application.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDatabase extends Database {

    public CourseDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    // get all courses from the database
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

    // check for duplicate coursename (used when creating a new course)
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

    // add a new course to the database with all the required information
    public void addCourse(String courseName, String subject, String difficulty, String introductionText)
            throws SQLException {
        String query = "INSERT INTO Course(CourseName, Subject, Difficulty, IntroductionText) VALUES (\'" + courseName
                + "\',\'" + subject + "\',\'" + difficulty + "\',\'" + introductionText + "\')";
        statement.executeUpdate(query);
    }

    // add progress from student to the database
    public void addProgress(String emailaddress, int id) throws SQLException {
        int progress = 0;
        String query = "INSERT INTO Progress(StudentEmailAddress, ContentItemId, ProgressPercentage) VALUES (\'"
                + emailaddress + "\',\'" + id + "\',\'" + progress + "\')";
        statement.executeUpdate(query);
    }

    // edit student progress to database
    public void editProgress(String emailaddress, int id, double progress) throws SQLException {
        String query = "UPDATE Progress SET ProgressPercentage = \'" + progress + "\' WHERE StudentEmailAddress = \'"
                + emailaddress + "\' AND ContentItemId = \'" + id + "\'";
        statement.executeUpdate(query);
    }

    // get interesting courses related to given coursename
    public ArrayList<String> getInterestingCourses(String selected) {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT InterestingCourseName FROM InterestingCourse WHERE CourseName = \'" + selected + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add(resultSet.getString("InterestingCourseName"));
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    // get progress from specific email and contentitemid
    public Double getProgress(int id, String email) {
        try {
            connectDatabase();

            String SQL = "SELECT ProgressPercentage FROM Progress WHERE StudentEmailAddress = \'" + email
                    + "\' AND ContentItemId = \'" + id + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                return resultSet.getDouble("ProgressPercentage");
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return 0.0;
    }

    // get all content from given course
    public ArrayList<String> getContent(String course) {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT Title FROM Module WHERE ContentItemId IN(SELECT ContentItemId FROM Content WHERE CourseName = \'"
                    + course + "\')";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add("[Module] " + resultSet.getString("Title"));
            }

            SQL = "SELECT Title FROM Webcast WHERE ContentItemId IN(SELECT ContentItemId FROM Content WHERE CourseName = \'"
                    + course + "\')";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add("[Webcast] " + resultSet.getString("Title"));
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    // get all contentids from given course
    public ArrayList<Integer> getContentIds(String course) {
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            connectDatabase();
            String SQL = "SELECT ContentItemId FROM Module WHERE ContentItemId IN(SELECT ContentItemId FROM Content WHERE CourseName = \'"
                    + course + "\')";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                ids.add(resultSet.getInt("ContentItemId"));
            }

            SQL = "SELECT ContentItemId FROM Webcast WHERE ContentItemId IN(SELECT ContentItemId FROM Content WHERE CourseName = \'"
                    + course + "\')";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                ids.add(resultSet.getInt("ContentItemId"));
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return ids;
    }

    // get average progress per given contentitemid
    public ArrayList<Double> getAverageProgress(ArrayList<Integer> ids) {
        ArrayList<Double> progress = new ArrayList<>();
        try {
            connectDatabase();
            for (int id : ids) {
                String SQL = "SELECT AVG(ProgressPercentage) AS Average FROM Progress WHERE ContentItemId = \'" + id
                        + "\'";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(SQL);
                while (resultSet.next()) {
                    progress.add(resultSet.getDouble("Average"));
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return progress;
    }

    // get contentid from module/webcast
    public Integer getContentId(String title, String type) {
        try {
            connectDatabase();
            if (type.equals("Module")) {
                String SQL = "SELECT ContentItemId FROM Module WHERE Title = \'" + title + "\'";
                statement = connection.createStatement();

                resultSet = statement.executeQuery(SQL);

                while (resultSet.next()) {
                    return resultSet.getInt("ContentItemId");
                }
            } else if (type.equals("Webcast")) {
                String SQL = "SELECT ContentItemId FROM Webcast WHERE Title = \'" + title + "\'";
                statement = connection.createStatement();

                resultSet = statement.executeQuery(SQL);

                while (resultSet.next()) {
                    return resultSet.getInt("ContentItemId");
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return -1;
    }
}