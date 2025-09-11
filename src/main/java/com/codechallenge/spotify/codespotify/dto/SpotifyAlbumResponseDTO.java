package com.codechallenge.spotify.codespotify.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpotifyAlbumResponseDTO {
    private List<Image> images;

    @Data
    public static class Image {
        private String url;
        private int height;
        private int width;
    }

}
