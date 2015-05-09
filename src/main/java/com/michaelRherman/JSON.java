package com.michaelRherman;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class JSON {
    //See http://developer.echonest.com/raw_tutorials/faqs/faq_04.html for more info about the parameters returned here//

    protected static JSONObject echonestResponse;
    protected static JSONObject responseObject;
    protected static JSONArray songArray;
    protected static JSONObject songObject;
    protected static JSONObject songObjectDetails;
    protected static JSONObject spotifyObject;
    protected static JSONArray spotifyArray;
    protected static JSONObject trackObject;

    protected static String artistID;
    protected static String songID;
    protected static String artistName;
    protected static String songName;
    protected static String spotifyID;
    protected static String spotifyIDString;
    protected static String[] spotifyIDStringArray;
    protected static Song song;

    protected static Double danceability;
    protected static Double duration;
    protected static Double energy;
    protected static Long harmonicKey;
    protected static Long mode;
    protected static Double tempo;
    protected static Long timeSignature;

    public static LinkedList<Song> getEchonestResponseInitial(String url) throws Exception {

        LinkedList<Song> songs = new LinkedList<Song>();

    /* Adapted from http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    *  and https://code.google.com/p/json-simple/wiki/DecodingExamples */
          try {
            URL echoNest = new URL(url); //Takes URL it is fed
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(echoNest.openStream())); //Reads the code found at that URL line-by-line
            String echonestResponseString; //The code there will be one line long and we briefly store it as a string
            while ((echonestResponseString = in.readLine()) != null) {
//                System.out.println(echonestResponseString);
                JSONParser parser = new JSONParser();

                //Adapted from http://stackoverflow.com/questions/18899232/how-to-parse-the-this-json-response-in-java//
                try{
                    echonestResponse = (JSONObject) parser.parse(echonestResponseString); //Parser.parse will parse the string into a JSONObject
                    responseObject = (JSONObject) echonestResponse.get("response");
                    songArray = (JSONArray) responseObject.get("songs");
                }
                catch(ParseException pe){
                    System.out.println("position: " + pe.getPosition());
                    System.out.println(pe);
                }
            }
            in.close();

        } catch (MalformedURLException mue) {
            System.out.println(mue); //Shouldn't be necessary but it's here in case.
        }

        for (int x = 0 ; x < songArray.size() ; x++) {
            song = JSONArtistSongInfo((JSONObject) songArray.get(x));
            songs.add(song);
        }

    return songs;
    }

    public static SongDetails getEchonestResponseDetails(String url) throws Exception {

    /* Adapted from http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    *  and https://code.google.com/p/json-simple/wiki/DecodingExamples */
        try {
            URL echoNest = new URL(url); //Takes URL it is fed
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(echoNest.openStream())); //Reads the code found at that URL line-by-line
            String echonestResponseString; //The code there will be one line long and we briefly store it as a string
            while ((echonestResponseString = in.readLine()) != null) {
//                System.out.println(echonestResponseString);
                JSONParser parser = new JSONParser();

                //Adapted from http://stackoverflow.com/questions/18899232/how-to-parse-the-this-json-response-in-java//
                try{
                    echonestResponse = (JSONObject) parser.parse(echonestResponseString); //Parser.parse will parse the string into a JSONObject
                    responseObject = (JSONObject) echonestResponse.get("response");
                    songArray = (JSONArray) responseObject.get("songs");
                    songObject = (JSONObject) songArray.get(0);
                    songObjectDetails = (JSONObject) songObject.get("audio_summary");
                }
                catch(ParseException pe){
                    System.out.println("position: " + pe.getPosition());
                    System.out.println(pe);
                }
            }
            in.close();

        } catch (MalformedURLException mue) {
            System.out.println(mue); //Shouldn't be necessary but it's here in case.
        }

//        System.out.println(songObjectDetails.toString());

        SongDetails songDetails = JSONSongDetailsInfo(songObject, songObjectDetails);
        return songDetails;
    }

    public static String getSpotifyID(String url) throws Exception {
            /* Adapted from http://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    *  and https://code.google.com/p/json-simple/wiki/DecodingExamples */
        try {
            URL echoNest = new URL(url); //Takes URL it is fed
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(echoNest.openStream())); //Reads the code found at that URL line-by-line
            String echonestResponseString; //The code there will be one line long and we briefly store it as a string
            while ((echonestResponseString = in.readLine()) != null) {
//                System.out.println(echonestResponseString);
                JSONParser parser = new JSONParser();

                //Adapted from http://stackoverflow.com/questions/18899232/how-to-parse-the-this-json-response-in-java//
                //Spotify calls adapted from https://developer.spotify.com/technologies/widgets/spotify-play-button/ //
                try{
                    echonestResponse = (JSONObject) parser.parse(echonestResponseString); //Parser.parse will parse the string into a JSONObject
                    responseObject = (JSONObject) echonestResponse.get("response");
                    songArray = (JSONArray) responseObject.get("songs");
                    spotifyObject = (JSONObject) songArray.get(0);
                    spotifyArray = (JSONArray) spotifyObject.get("tracks");
                    trackObject = (JSONObject) spotifyArray.get(0);
                    spotifyIDString = trackObject.get("foreign_id").toString();
                    spotifyIDStringArray = spotifyIDString.split(":");
                    spotifyID = spotifyIDStringArray[2];
//                    System.out.println(spotifyID);
                }
                catch(ParseException pe){
                    System.out.println("position: " + pe.getPosition());
                    System.out.println(pe);
                }
            }
            in.close();

        } catch (MalformedURLException mue) {
            System.out.println(mue); //Shouldn't be necessary but it's here in case.
        }
        return spotifyID;
    }

     public static Song JSONArtistSongInfo(JSONObject jsonObject) {
         artistID = (String) jsonObject.get("artist_id");
         songID = (String) jsonObject.get("id");
         artistName = (String) jsonObject.get("artist_name");
         songName = (String) jsonObject.get("title");
         Song arraySong = new Song(artistID, artistName, songID, songName);
         return arraySong;
    }

    public static SongDetails JSONSongDetailsInfo(JSONObject jsonSongObject, JSONObject jsonSongDetailsObject) {
        artistID = (String) jsonSongObject.get("artist_id");
        songID = (String) jsonSongObject.get("id");
        artistName = (String) jsonSongObject.get("artist_name");
        songName = (String) jsonSongObject.get("title");
        danceability = Double.valueOf((Double) jsonSongDetailsObject.get("danceability"));
        duration = Double.valueOf((Double) jsonSongDetailsObject.get("duration"));
        energy = Double.valueOf((Double) jsonSongDetailsObject.get("energy"));
        harmonicKey = Long.valueOf((Long) jsonSongDetailsObject.get("key"));
        mode = Long.valueOf((Long) jsonSongDetailsObject.get("mode"));
        tempo = Double.valueOf((Double) jsonSongDetailsObject.get("tempo"));
        timeSignature = Long.valueOf((Long) jsonSongDetailsObject.get("time_signature"));
        SongDetails songDetails = new SongDetails(artistID, artistName, songID, songName,
                danceability, duration, energy, harmonicKey, mode, tempo, timeSignature);
        return songDetails;
    }
}

/* From http://developer.echonest.com/raw_tutorials/faqs/faq_04.html
    Website Title: Getting The Tempo And Other Audio Attributes Of A Song | The Echo Nest Developer Center
    Article Title: Getting The Tempo And Other Audio Attributes Of A Song | The Echo Nest Developer Center
    Date Accessed: April 26, 2015

Understanding audio_summary results

The tempo field represents the BPM of the song in question. In this case, it's 142 BPM. Other interesting attributes are:

    danceability: A number that ranges from 0 to 1, representing how danceable The Echo Nest thinks this song is.
    duration: Length of the song, in seconds.
    energy: A number that ranges from 0 to 1, representing how energetic The Echo Nest thinks this song is.
    key: The key that The Echo Nest believes the song is in. Key signatures start at 0 (C) and ascend the chromatic scale. In this case, a key of 1 represents a song in D-flat.
    mode: Number representing whether the song is in a minor (0) or major (1) key. Use this in conjunction with 'key'.
    time_signature: Time signature of the key; how many beats per measure.
*/
