package com.codechallenge.spotify.codespotify.service;

import com.codechallenge.spotify.codespotify.dto.TrackDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface TrackService {
    public TrackDTO save(String isrc, String token);
    TrackDTO getTrackMetadata(String isrc);
    byte[] getCoverImage(String isrc) throws IOException;
    Page<TrackDTO> getAllTracks(Pageable pageable);

}
