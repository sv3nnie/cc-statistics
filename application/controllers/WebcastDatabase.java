package application.controllers;

import java.util.ArrayList;
import java.sql.SQLException;

public class WebcastDatabase extends Database {
    public WebcastDatabase(String connectionUrl) {
        super(connectionUrl);
    }

    public ArrayList<String> getWebcastViewCount() {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();

            String SQL = "SELECT Title, ViewCount FROM Webcast";
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
}
