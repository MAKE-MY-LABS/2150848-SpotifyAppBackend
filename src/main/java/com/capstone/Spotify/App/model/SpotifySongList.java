package com.capstone.Spotify.App.model;

    /*
     * The data field should be of type List<Song>.
     *
     * Use the Lombok library to automatically generate
     * a no-args constructor, an all-args constructor,
     * getters, setters, and the toString() method.
     */

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class SpotifySongList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private List<SpotifySong> songs;
    }

