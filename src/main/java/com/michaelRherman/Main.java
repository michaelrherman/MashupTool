package com.michaelRherman;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new MashupGUI();
    }

    public static void openDefaultBrowser() throws Exception {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://www.google.com"));
            }
        } catch (URISyntaxException USE) {
            USE.printStackTrace();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
}