package com.michaelRherman;

import java.awt.Desktop;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class Main {

    private static String apiKey;

    public static void main(String[] args) throws Exception {

        try {
            FileReader reader = new FileReader("keyAPI.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            apiKey = bufferedReader.readLine();
            reader.close();
        } catch (Exception e) {
            System.out.println("API key not present");
        }

        Database.databaseInitialize();
        new MashupGUI();
//        openDefaultBrowser();
    }

    public static void openDefaultBrowser() throws Exception {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://www.google.com"));
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