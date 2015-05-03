package com.michaelRherman;

public class SongDetails extends Song {
    private Double danceability;
    private Double duration;
    private Double energy;
    private Long harmonicKey;
    private Long mode;
    private Double tempo;
    private Long timeSignature;

    public SongDetails(String artistID, String artistName, String songID, String songTitle, String spotifyID, Double danceability,
                       Double duration, Double energy, Long harmonicKey, Long mode, Double tempo, Long timeSignature) {

        super(artistID, artistName, songID, songTitle, spotifyID);

        this.danceability = danceability;
        this.energy = energy;
        this.duration = duration;
        this.harmonicKey = harmonicKey;
        this.mode = mode;
        this.tempo = tempo;
        this.timeSignature = timeSignature;
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

    public Long getHarmonicKey() {
        return harmonicKey;
    }

    public void setHarmonicKey(Long harmonicKey) {
        this.harmonicKey = harmonicKey;
    }

    public Long getMode() {
        return mode;
    }

    public void setMode(Long mode) {
        this.mode = mode;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    public Long getTimeSignature() {
        return timeSignature;
    }

    public void setTimeSignature(Long timeSignature) {
        this.timeSignature = timeSignature;
    }
}
