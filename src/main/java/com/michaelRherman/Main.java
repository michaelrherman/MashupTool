package com.michaelRherman;

import java.awt.Desktop;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class Main {

    private static String apiKey;
    private static String redditUsername;
    private static String redditPW;

    public static void main(String[] args) throws Exception {

        try { //Reads the API key from a text file
            FileReader reader = new FileReader("keyAPI.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            apiKey = bufferedReader.readLine();
            redditUsername = bufferedReader.readLine();
            redditPW = bufferedReader.readLine();
            reader.close();
            EchoNest.setKeyAPI(apiKey);
            Reddit.setRedditUsername(redditUsername);
            Reddit.setRedditPW(redditPW);
        } catch (Exception e) {
            System.out.println("Necessary key not present");
        }

        Database.databaseInitialize(); //Sets up the database
        new MashupGUI(); //Starts the GUI
    }

    public static void openDefaultBrowser(String searchTerm) throws Exception {
        try {
            if (Desktop.isDesktopSupported()) {
                String url = "https://play.spotify.com/track/";
                String encodedUrl = url+URLEncoder.encode(searchTerm, "UTF-8"); //URLEncoder necessary to account for spaces, dashes, etc.
                Desktop.getDesktop().browse(new URI(encodedUrl));
            }
        } catch (URISyntaxException USE) {
            System.out.println(USE);
        } catch (IOException IOE) {
            System.out.println(IOE);
        } catch (Exception E) {
            System.out.println(E);
        }
    }
}