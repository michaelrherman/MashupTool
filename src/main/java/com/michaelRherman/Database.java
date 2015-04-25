package com.michaelRherman;

    import java.sql.*;
    import java.util.*;

public class Database {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    private static String dbName = "Searches";
    private static final String USER = "username";
    private static final String PASS = "password";

    protected static String searchTerm;

    public static void databaseMethod() throws SQLException {
        Statement statement = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psDelete = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
            statement = conn.createStatement();

            try {
                String createTableSQL = "CREATE TABLE Searches (Search varchar(50))";
                statement.executeUpdate(createTableSQL);
                System.out.println("Created table");
            } catch (SQLException e) {
                System.out.println("SQL Error");
                System.out.println(e);
            }

            searchTerm = MashupGUI.getSearchTerm();
            String prepStatInsert = "INSERT INTO Searches VALUES ( ? )";
            psInsert = conn.prepareStatement(prepStatInsert);
            psInsert.setString(1, searchTerm);
            psInsert.executeUpdate();

            String printAll = "Select * Searches";
            rs = statement.executeQuery(printAll);

            while (rs.next()){
                String search = rs.getString(1);
                System.out.println(search);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    System.out.println("ResultSet closed");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed");
                }
            } catch (SQLException se){
                se.printStackTrace();
            }
            try {
                if (psInsert != null) {
                    psInsert.close();
                    System.out.println("Prepared Statement closed");
                }
            } catch (SQLException se){
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Database connection closed");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
