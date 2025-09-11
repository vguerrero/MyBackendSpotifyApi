package com.codechallenge.spotify.codespotify.dto;

public record TrackDTO(String isrc,
                       String name,
                       String artistName,
                       String albumName,
                       String albumId,
                       boolean isExplicit,
                       Integer playbackSeconds,
                       String cover_image
) {
}
