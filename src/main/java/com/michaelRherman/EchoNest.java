package com.michaelRherman;

import java.io.IOException;
import java.net.*;

public class EchoNest {

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
            System.out.println(IOE);
        } catch (Exception E) {
            System.out.println(E);
        }
    return url;
    }

    public static void echonestInfo(String searchTerm) {
        //TODO Have this system build the song info query to EchoNest
    }

    public static void setKeyAPI(String keyAPI) {
        EchoNest.keyAPI = keyAPI;
    }
}
