package com.codechallenge.spotify.codespotify.exception;

public class SystemUserNotFoundException extends RuntimeException {
    public SystemUserNotFoundException(String userName) {
        super("Username " + userName + " not found");
    }
}
