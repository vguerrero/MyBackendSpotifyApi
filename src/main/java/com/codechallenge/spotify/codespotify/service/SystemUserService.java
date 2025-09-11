package com.codechallenge.spotify.codespotify.service;

import com.codechallenge.spotify.codespotify.exception.SystemUserNotFoundException;
import com.codechallenge.spotify.codespotify.model.SystemUser;
import com.codechallenge.spotify.codespotify.repository.SystemUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = systemUserRepository.findByUsername(username)
                .orElseThrow(() -> new SystemUserNotFoundException("User not found"));
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
