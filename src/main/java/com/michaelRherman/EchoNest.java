package com.michaelRherman;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class EchoNest {

    private static String keyAPI;
    private String artistName;
    private String songTitle;
    private String artistNum;
    private String songNum;

    public static void prepareEchoNest(String artistSearch, String songSearch) {
        try {
            if (Desktop.isDesktopSupported()) {
                if (artistSearch !=null && songSearch !=null) {
                    artistSearch = URLEncoder.encode(artistSearch, "UTF-8"); //URLEncoder necessary to account for spaces, dashes, etc.
                    songSearch = URLEncoder.encode(songSearch, "UTF-8");
                    String url = "http://developer.echonest.com/api/v4/song/search?api_key="+keyAPI+"&artist="+artistSearch+"&title="+songSearch;
                    Desktop.getDesktop().browse(new URI(url));
                } else if (artistSearch !=null && songSearch ==null) {
                    artistSearch = URLEncoder.encode(artistSearch, "UTF-8");
                    String url = "http://developer.echonest.com/api/v4/song/search?api_key="+keyAPI+"&artist="+artistSearch;
                } else if (artistSearch ==null && songSearch !=null) {
                    songSearch = URLEncoder.encode(songSearch, "UTF-8");
                    String url = "http://developer.echonest.com/api/v4/song/search?api_key="+keyAPI+"&title="+songSearch;
                } else {
                    System.out.println("Something went wrong putting the query together.");
                }
            }
        } catch (URISyntaxException USE) {
            System.out.println(USE);
        } catch (IOException IOE) {
            System.out.println(IOE);
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public static void echonestInfo(String searchTerm) {
        //TODO Have this system build the song info query to EchoNest
    }

    public static void setKeyAPI(String keyAPI) {
        EchoNest.keyAPI = keyAPI;
    }
}
