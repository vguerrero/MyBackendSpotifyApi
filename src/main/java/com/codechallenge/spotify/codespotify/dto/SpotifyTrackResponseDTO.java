package com.codechallenge.spotify.codespotify.dto;

import lombok.Data;

import java.util.List;

@Data
public class SpotifyTrackResponseDTO {
    private Tracks tracks;

    @Data
    public static class Tracks {
        private List<TrackItem> items;
    }

    @Data
    public static class TrackItem {
        private String name;
        private List<Artist> artists;
        private Album album;
        private boolean explicit;
        private int duration_ms;


        @Data
        public static class Artist {
            private String name;
        }

        @Data
        public static class Album {
            private String id;
            private String name;
            private List<Image> images;

            @Data
            public static class Image {
                private String url;
                private int height;
                private int width;
            }
        }

    }
}
