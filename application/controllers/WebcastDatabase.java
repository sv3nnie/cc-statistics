package application.controllers;

import java.util.ArrayList;

public class WebcastDatabase extends Database {
    public WebcastDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    public ArrayList<String> getWebcastViewCount() {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT TOP 3 Title, ViewCount FROM Webcast ORDER BY ViewCount DESC";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                results.add(resultSet.getString("Title") + " (" + resultSet.getInt("ViewCount") + " views)");
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }
}
