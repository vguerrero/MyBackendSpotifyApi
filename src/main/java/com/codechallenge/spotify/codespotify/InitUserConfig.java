package com.codechallenge.spotify.codespotify;

import com.codechallenge.spotify.codespotify.model.SystemUser;
import com.codechallenge.spotify.codespotify.repository.SystemUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class InitUserConfig {

    /**
     * creating our test user in db for basic authentication: admin - secret
     * @param userRepository
     * @param encoder
     * @return
     */
    @Bean
    CommandLineRunner init(SystemUserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                SystemUser user = new SystemUser();
                user.setUsername("admin");
                user.setPassword(encoder.encode("secret"));
                user.setRole("USER");
                userRepository.save(user);
                log.info("admin user for testing has been created");
            }
        };
    }
}
