package com.michaelRherman;

import java.io.IOException;
import java.net.*;

public class EchoNest {
    //See http://developer.echonest.com/raw_tutorials/faqs/faq_04.html for more info about the queries formed here//
    private static String keyAPI;
    private static String url;

    public static String prepareEchoNest(String artistSearch, String songSearch) {
        try { //TODO Change url to query with Spotify bucket to get Spotify IDs
//            if (Desktop.isDesktopSupported()) { //Commented out code in this section was a part of testing.
                if (artistSearch !=null && songSearch !=null) {
                    artistSearch = URLEncoder.encode(artistSearch, "UTF-8"); //URLEncoder necessary to account for spaces, dashes, etc.
                    songSearch = URLEncoder.encode(songSearch, "UTF-8");
                    url = "http://developer.echonest.com/api/v4/song/search?api_key="+keyAPI+"&artist="+artistSearch+"&title="+songSearch;
//                    Desktop.getDesktop().browse(new URI(url));
                } else if (artistSearch !=null && songSearch ==null) {
                    artistSearch = URLEncoder.encode(artistSearch, "UTF-8");
                    url = "http://developer.echonest.com/api/v4/song/search?api_key="+keyAPI+"&artist="+artistSearch;
//                    Desktop.getDesktop().browse(new URI(url));
                } else if (artistSearch ==null && songSearch !=null) {
                    songSearch = URLEncoder.encode(songSearch, "UTF-8");
                    url = "http://developer.echonest.com/api/v4/song/search?api_key="+keyAPI+"&title="+songSearch;
//                    Desktop.getDesktop().browse(new URI(url));
                } else {
                    System.out.println("Something went wrong putting the query together.");
                }
//            }
//        } catch (URISyntaxException USE) {
//            System.out.println(USE);
        } catch (IOException IOE) {
            System.out.println("There was a problem talking to EchoNest.");
            if (IOE.toString().contains("400 for URL")) {
                System.out.println("It was on the user side");
            } else if (IOE.toString().contains("500 for URL")) {
                System.out.println("It was on the server side");
            }
        } catch (Exception E) {
            System.out.println(E);
        }
    return url;
    }

    public static String getSpotifyID(String artistSearch, String songSearch) {
        try {
            artistSearch = URLEncoder.encode(artistSearch, "UTF-8"); //URLEncoder necessary to account for spaces, dashes, etc.
            songSearch = URLEncoder.encode(songSearch, "UTF-8");
            url = "http://developer.echonest.com/api/v4/song/search?api_key="+keyAPI+"&format=json&results=1&artist="+artistSearch+"&title="+songSearch+"&bucket=id:spotify&bucket=tracks&limit=true";
        } catch (IOException IOE) {
            System.out.println("There was a problem talking to EchoNest.");
            if (IOE.toString().contains("400 for URL")) {
                System.out.println("It was on the user side");
            } else if (IOE.toString().contains("500 for URL")) {
                System.out.println("It was on the server side");
            }
        } catch (Exception E) {
            System.out.println(E);
        }
        return url;
    }

    public static String echonestInfo(String echonestID) {
        try {
            url = "http://developer.echonest.com/api/v4/song/profile?api_key="+keyAPI+"&id="+echonestID+"&bucket=audio_summary";
        } catch (Exception E) {
            System.out.println(E);
        }
        return url;
    }

    public static void setKeyAPI(String keyAPI) {
        EchoNest.keyAPI = keyAPI;
    }
}
