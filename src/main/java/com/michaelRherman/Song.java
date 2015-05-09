package com.michaelRherman;

public class Song {
    private String artistID;
    private String artistName;
    private String songID;
    private String songTitle;

    public Song(String artistID, String artistName, String songID, String songTitle) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.songID = songID;
        this.songTitle = songTitle;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

}
