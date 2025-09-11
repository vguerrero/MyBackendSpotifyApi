package com.codechallenge.spotify.codespotify.repository;

import com.codechallenge.spotify.codespotify.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, String> {
}
