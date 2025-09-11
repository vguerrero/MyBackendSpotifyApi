package com.codechallenge.spotify.codespotify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "track")
public class Track {

    @Id
    @Column(name = "isrc", nullable = false, unique = true)
    private String isrc;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "artist_name", nullable = false)
    private String artistName;

    @Column(name = "album_name", nullable = false)
    private String albumName;

    @Column(name = "album_id", nullable = false)
    private String albumId;

    @Column(name = "is_explicit", nullable = false)
    private boolean isExplicit;

    @Column(name = "playback_seconds")
    private Integer playbackSeconds;

    @Column(name = "cover_image", nullable = false)
    private String cover_image;


    public Track() {
    }

    public Track(String isrc, String name, String artistName, String albumName, String albumId, boolean isExplicit, Integer playbackSeconds, String cover_image) {
        this.isrc = isrc;
        this.name = name;
        this.artistName = artistName;
        this.albumName = albumName;
        this.albumId = albumId;
        this.isExplicit = isExplicit;
        this.playbackSeconds = playbackSeconds;
        this.cover_image = cover_image;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public boolean isExplicit() {
        return isExplicit;
    }

    public void setExplicit(boolean explicit) {
        isExplicit = explicit;
    }

    public Integer getPlaybackSeconds() {
        return playbackSeconds;
    }

    public void setPlaybackSeconds(Integer playbackSeconds) {
        this.playbackSeconds = playbackSeconds;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }
}
