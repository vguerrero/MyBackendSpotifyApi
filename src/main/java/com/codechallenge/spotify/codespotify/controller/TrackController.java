package com.codechallenge.spotify.codespotify.controller;

import com.codechallenge.spotify.codespotify.dto.TrackDTO;
import com.codechallenge.spotify.codespotify.exception.UnauthorizedException;
import com.codechallenge.spotify.codespotify.service.SpotifyTokenService;
import com.codechallenge.spotify.codespotify.service.TrackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "Tracks", description = "Music track manager")
@RestController
@RequestMapping("/api/track")
public class TrackController {

    private final SpotifyTokenService tokenService;

    private final TrackService trackService;

    public TrackController(SpotifyTokenService tokenService, TrackService trackService) {
        this.tokenService = tokenService;
        this.trackService = trackService;
    }

    @Operation(summary = "fetch and create a track by ISRC", description = "return saved track"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "track found and saved"),
            @ApiResponse(responseCode = "404", description = "track not found"),
            @ApiResponse(responseCode = "500", description = "internal server error"),
            @ApiResponse(responseCode = "401", description = "unauthorized")
    })
    @PostMapping("create")
    public ResponseEntity<TrackDTO> saveTrack(@RequestParam String isrc) {
        String token = tokenService.getAccessToken();
        TrackDTO savedTrack = trackService.save(isrc, token);
        return ResponseEntity.ok(savedTrack);
    }

    @Operation(summary = "Find track by ISRC", description = "return track information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "track found"),
            @ApiResponse(responseCode = "404", description = "track not found")
    })
    @GetMapping("/metadata/{isrc}")
    public ResponseEntity<TrackDTO> getTrackMetadata(@PathVariable String isrc) {
        TrackDTO track = trackService.getTrackMetadata(isrc);
        return ResponseEntity.ok(track);

    }

    @Operation(summary = "download track cover image by ISRC", description = "download track cover image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "track cover downloaded"),
            @ApiResponse(responseCode = "404", description = "track cover not found")
    })
    @GetMapping("/cover/{isrc}")
    public ResponseEntity<byte[]> getCoverImage(@PathVariable String isrc) throws IOException {
        byte[] imageBytes = trackService.getCoverImage(isrc);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
                .body(imageBytes);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TrackDTO>> getAllTracks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TrackDTO> tracks = trackService.getAllTracks(PageRequest.of(page, size));
        return ResponseEntity.ok(tracks);
    }



}
