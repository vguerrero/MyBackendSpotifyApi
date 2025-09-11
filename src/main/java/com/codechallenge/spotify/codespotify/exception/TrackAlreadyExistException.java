package com.codechallenge.spotify.codespotify.exception;

public class TrackAlreadyExistException extends RuntimeException{
    public TrackAlreadyExistException(String isrc) {
        super("Track with ISRC " + isrc + " already exist");
    }

}
