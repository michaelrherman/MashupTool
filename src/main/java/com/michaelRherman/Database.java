package com.michaelRherman;

    import java.sql.*;
    import java.util.*;

public class Database {
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    private static String dbName = "Searches";
    private static final String USER = "username";
    private static final String PASS = "password";

    private static Statement statement = null;
    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement psInsert = null;

    protected static void databaseInitialize() throws Exception {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
            statement = conn.createStatement();

            try {
                String createTableSQL = "CREATE TABLE Searches (Artist varchar(50), Song varchar(50))";
                statement.executeUpdate(createTableSQL);
                System.out.println("Created table Searches");

                createTableSQL = "CREATE TABLE Favorites (Artist varchar(50), Song varchar(50),  SongID varchar(18), ArtistID varchar(18))";
                statement.executeUpdate(createTableSQL);
                System.out.println("Created table Favorites");

            } catch (SQLException se) {
                System.out.println(se);
            }

        } catch (ClassNotFoundException ce) {
            closeDatabase();
            System.out.println(ce);
        }
    }

    protected static void insertSearch(String artistSearch, String songSearch) throws SQLException {

        try {
            String prepStatInsert = "INSERT INTO Searches VALUES ( ? , ?)";
            psInsert = conn.prepareStatement(prepStatInsert);
            psInsert.setString(1, artistSearch);
            psInsert.setString(2, songSearch);
            psInsert.executeUpdate();

//            printSearches();

        } catch (SQLException se) {
            closeDatabase();
            System.out.println(se);
        }
    }

    protected static void insertFavorite(String Artist, String Song, String SongID, String ArtistID ) {

        try {
            String prepStatInsert = "INSERT INTO Favorites VALUES ( ? , ? , ? , ?)";
            psInsert = conn.prepareStatement(prepStatInsert);
            psInsert.setString(1, Artist);
            psInsert.setString(2, Song);
            psInsert.setString(3, SongID);
            psInsert.setString(4, ArtistID);
            psInsert.executeUpdate();
//            printFavorites();

        } catch (SQLException se) {
            closeDatabase();
            System.out.println(se);
        }
    }

    protected static LinkedList getFavorites() throws SQLException{
        LinkedList favorites = new LinkedList();
        try {
            String selectArtistSong = "Select Artist, Song FROM Favorites";
            rs = statement.executeQuery(selectArtistSong);

            while (rs.next()) {
                String artist = rs.getString(1);
                String song = rs.getString(2);
//                String songID = rs.getString(3);
//                String artistID = rs.getString(4);
                String search = artist+"-"+song;
                favorites.add((String) search);
            }
        } catch (SQLException se) {
            closeDatabase();
            System.out.println(se);
        }
        return favorites;
    }

    private static void printSearches() throws SQLException{
        try {
            String printAll = "Select * FROM Searches";
            rs = statement.executeQuery(printAll);

            while (rs.next()) {
                String artist = rs.getString(1);
                String song = rs.getString(2);
                String search = artist+" "+song;
                System.out.println(search);
            }
        } catch (SQLException se) {
            closeDatabase();
            System.out.println(se);
        }
    }

    private static void printFavorites() throws SQLException{
        try {
            String printAll = "Select * FROM Favorites";
            rs = statement.executeQuery(printAll);

            while (rs.next()) {
                String artist = rs.getString(1);
                String song = rs.getString(2);
                String songID = rs.getString(3);
                String artistID = rs.getString(4);
                String search = artist+" "+song+" "+songID+" "+artistID;
                System.out.println(search);
            }
        } catch (SQLException se) {
            closeDatabase();
            System.out.println(se);
        }
    }

    protected static void closeDatabase() {
        try {
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet closed");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException se){
            System.out.println(se);
        }
        try {
            if (psInsert != null) {
                psInsert.close();
                System.out.println("Prepared Statement closed");
            }
        } catch (SQLException se){
            System.out.println(se);
        }
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
    }
}
