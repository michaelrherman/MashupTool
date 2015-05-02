package com.michaelRherman;

public class SongDetails extends Song {
    private Double danceability;
    private Double duration;
    private Double energy;
    private Integer harmonicKey;
    private Integer mode;
    private Double tempo;
    private Double timeSignature;

    public SongDetails(String artistID, String artistName, String songID, String songTitle, String spotifyID, Double danceability,
                       Double duration, Double energy, Integer harmonicKey, Integer mode, Double tempo, Double timeSignature) {

        super(artistID, artistName, songID, songTitle, spotifyID);
    }

    public Double getDanceability() {
        return danceability;
    }

    public void setDanceability(Double danceability) {
        this.danceability = danceability;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public Integer getHarmonicKey() {
        return harmonicKey;
    }

    public void setHarmonicKey(Integer harmonicKey) {
        this.harmonicKey = harmonicKey;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public Double getTimeSignature() {
        return timeSignature;
    }

    public void setTimeSignature(Double timeSignature) {
        this.timeSignature = timeSignature;
    }
}
