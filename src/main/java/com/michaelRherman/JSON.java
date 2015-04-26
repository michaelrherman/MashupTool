package com.michaelRherman;

import org.json.simple.JSONObject;

public class JSON {

    protected static JSONObject echonestResponse;
    protected static String artistID;
    protected static String songID;
    protected static String artistName;
    protected static String songName;

    protected static Double danceability;
    protected static Double duration;
    protected static Double energy;
    protected static Integer harmonicKey;
    protected static Integer mode;
    protected static Double tempo;
    protected static Double timeSignature;


     public static void JSONArtistSongInfo(JSONObject echonestJSON) {

         echonestResponse = echonestJSON;
         artistID = (String) echonestResponse.get("artist_id");
         songID = (String) echonestResponse.get("id");
         artistName = (String) echonestResponse.get("artist_name");
         songName = (String) echonestResponse.get("title");
    }

    public static void JSONSongDetailsInfo(JSONObject echnonestJSON) {

        echonestResponse = echnonestJSON;
        danceability = Double.valueOf((String) echonestResponse.get("danceability"));
        duration = Double.valueOf((String) echonestResponse.get("duration"));
        energy = Double.valueOf((String) echonestResponse.get("energy"));
        harmonicKey = Integer.valueOf((String) echonestResponse.get("key"));
        mode = Integer.valueOf((String) echonestResponse.get("mode"));
        tempo = Double.valueOf((String) echonestResponse.get("tempo"));
        timeSignature = Double.valueOf((String) echonestResponse.get("time_signature"));
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
