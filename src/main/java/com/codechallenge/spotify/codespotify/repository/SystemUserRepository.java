package com.codechallenge.spotify.codespotify.repository;

import com.codechallenge.spotify.codespotify.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, String> {
    Optional<SystemUser> findByUsername(String userName);
}
