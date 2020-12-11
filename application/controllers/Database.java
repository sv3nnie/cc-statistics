package application.controllers;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    public static boolean checkQuery(String query) {
        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            String SQL = query;
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception e) {
                }
        }
        return false;
    }

    public static void insertQuery(String query) {

        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = query;
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception e) {
                }
        }
    }

    public static ArrayList selectQueryArrayList(String query) {
        ArrayList<String> results = new ArrayList<>();

        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;";

        Connection con = null;

        Statement stmt = null;

        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = query;
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {

                System.out.println(rs.getString("EmailAddress"));
                results.add(rs.getString("EmailAddress"));
            }

        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception e) {
                }
        }
        return results;
    }

    public static String selectQuery(String query) {

        String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;";

        Connection con = null;

        Statement stmt = null;

        ResultSet rs = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = query;
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            if (rs.next()) {
                String result = rs.getString("Name");
                return result;
            }

        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception e) {
                }
        }
        return "";
    }
}