package com.codechallenge.spotify.codespotify.service;

import com.codechallenge.spotify.codespotify.dto.SpotifyTrackResponseDTO;
import com.codechallenge.spotify.codespotify.dto.TrackDTO;
import com.codechallenge.spotify.codespotify.exception.TrackAlreadyExistException;
import com.codechallenge.spotify.codespotify.exception.TrackNotFoundException;
import com.codechallenge.spotify.codespotify.mapper.TrackMapper;
import com.codechallenge.spotify.codespotify.model.Track;
import com.codechallenge.spotify.codespotify.repository.TrackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;
    private final WebClient webClient;

    public TrackServiceImpl(TrackRepository repository, TrackMapper trackMapper, WebClient webClient) {
        this.trackRepository = repository;
        this.trackMapper = trackMapper;
        this.webClient = webClient;
    }

    public TrackDTO save(TrackDTO trackDTO) {
        Track track = trackMapper.toEntity(trackDTO);
        Track saved = trackRepository.save(track);
        return trackMapper.toDTO(saved);

    }

    /**
     * Method allow us search track in api and save selected track in db
     *
     * @param isrc
     * @param token
     * @return TrackDTO
     */
    @Override
    public TrackDTO save(String isrc, String token) {
        if (isrc.isEmpty()) throw new IllegalArgumentException("isrc field is mandatory");
        if (trackRepository.findById(isrc).isPresent()) throw new TrackAlreadyExistException(isrc);
        //fetch track
        SpotifyTrackResponseDTO response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", "isrc:" + isrc)
                        .queryParam("type", "track")
                        .build())
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(SpotifyTrackResponseDTO.class)
                .block();

        if (response == null || response.getTracks() == null || response.getTracks().getItems().isEmpty()) {
            throw new TrackNotFoundException("Track not found for ISRC: " + isrc);
        }

        SpotifyTrackResponseDTO.TrackItem item = response.getTracks().getItems().get(0);

        // Extract album image
        String coverImage = item.getAlbum().getImages().stream()
                .findFirst()
                .map(SpotifyTrackResponseDTO.TrackItem.Album.Image::getUrl)
                .orElse(null);

        // Map to DTO
        TrackDTO dto = new TrackDTO(
                isrc,
                item.getName(),
                item.getArtists().get(0).getName(),
                item.getAlbum().getName(),
                item.getAlbum().getId(),
                item.isExplicit(),
                item.getDuration_ms() / 1000,
                coverImage
        );

        // Save db
        Track entity = trackMapper.toEntity(dto);
        trackRepository.save(entity);

        return dto;

    }

    @Override
    public TrackDTO getTrackMetadata(String isrc) {
        Optional<Track> trackOpt = trackRepository.findById(isrc);
        return trackOpt.map(trackMapper::toDTO).orElseThrow(() -> new TrackNotFoundException(isrc));
    }

    @Override
    public byte[] getCoverImage(String isrc) throws IOException {
        Track track = trackRepository.findById(isrc)
                .orElseThrow(() -> new TrackNotFoundException(isrc));

        String imageUrl = track.getCover_image();
        try (InputStream in = new URL(imageUrl).openStream()) {
            return in.readAllBytes();
        }
    }

    @Override
    public Page<TrackDTO> getAllTracks(Pageable pageable) {
        return trackRepository.findAll(pageable)
                .map(trackMapper::toDTO);
    }
}
