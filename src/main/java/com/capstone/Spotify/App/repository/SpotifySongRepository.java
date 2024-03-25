package com.capstone.Spotify.App.repository;

/**
 * create a SongRepository interface using mysql repository to perform the
 * CRUD operations:
 *
 */

import com.capstone.Spotify.App.model.SpotifySong;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@EnableJpaRepositories

public interface SpotifySongRepository extends JpaRepository<SpotifySong, String> {

}

