package application.controllers;

import java.text.DecimalFormat;

import application.logic.Gender;

public class CertificateDatabase extends Database {

    public CertificateDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    // get top 3 given certificates
    public String getTop3() {
        String top3 = "";
        try {
            connectDatabase();

            String SQL = "SELECT TOP 3 CourseName FROM Certificate GROUP BY CourseName";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);
            int i = 1;
            if (resultSet.next()) {
                top3 = top3 + i + ": " + resultSet.getString("CourseName") + "\n";
                i++;
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return top3;
    }

    // get count of given certificates
    public Integer getCount(String courseName) {
        try {
            connectDatabase();

            String SQL = "SELECT COUNT(CourseName) AS Count FROM Certificate WHERE CourseName = \'" + courseName + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                return resultSet.getInt("Count");
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return 0;
    }

    // get percentage of completed courses compared to enrollments
    public String getPercentageCompleted(Gender gender) {
        try {
            double countenrolled = 0.0;
            double countcompleted = 0.0;
            connectDatabase();

            String SQL = "SELECT COUNT(StudentEmailAddress) AS Count FROM Certificate WHERE StudentEmailAddress IN (SELECT EmailAddress FROM Enrollment WHERE EmailAddress IN(SELECT EmailAddress FROM Student WHERE Gender = \'"
                    + gender + "\'))";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                countcompleted = resultSet.getInt("Count");
            }

            SQL = "SELECT COUNT(EmailAddress) AS Count FROM Enrollment WHERE EmailAddress IN(SELECT EmailAddress FROM Student WHERE Gender = \'"
                    + gender + "\')";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                countenrolled = resultSet.getInt("Count");
            }

            DecimalFormat format = new DecimalFormat("#0.0");
            String formatted = format.format((countcompleted / countenrolled) * 100);
            if (formatted.equals("NaN")) {
                return "0";
            }
            return formatted;
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return "0";
    }

}