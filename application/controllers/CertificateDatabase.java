package application.controllers;

public class CertificateDatabase extends Database {

    public CertificateDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    // check for duplicate enrollments
    public String getTop3() {
        String top3 = "";
        try {
            connectDatabase();

            String SQL = "SELECT TOP 3 CourseName FROM Certificate GROUP BY CourseName;";
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

}