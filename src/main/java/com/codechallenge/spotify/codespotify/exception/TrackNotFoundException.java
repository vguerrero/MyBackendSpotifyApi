package com.codechallenge.spotify.codespotify.exception;

public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(String isrc) {
        super("Track with ISRC " + isrc + " not found");
    }

}
