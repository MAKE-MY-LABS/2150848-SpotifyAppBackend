package com.capstone.Spotify.App.repository;


import com.capstone.Spotify.App.model.SpotifyUser;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * create a UserRepository interface using mysql repository to perform the
 * CRUD operations:
 *
 */
@EnableJpaRepositories
public interface SpotifyUserRepository extends JpaRepository<SpotifyUser, String> {
    Optional<SpotifyUser> findByEmailIdAndPassword(String emailId, String password);

    Optional<SpotifyUser> findByEmailId(String emailId);
}