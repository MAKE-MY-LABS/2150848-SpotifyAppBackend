package com.capstone.Spotify.App.controller;

import com.capstone.Spotify.App.exception.SongExistException;
import com.capstone.Spotify.App.model.SpotifySong;
import com.capstone.Spotify.App.service.SpotifySongService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.capstone.Spotify.App.exception.SongNotFoundException;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin

public class SpotifySongController {

    /*
     * Create a controller for the methods available in SongService
     * Autowire the SongService class
     * use throws keyword wherever required for exception handling
     */
        @Autowired
        private SpotifySongService spotifySongService;

        @PostMapping("/song")
        public SpotifySong saveSong(@RequestBody SpotifySong song) throws SongExistException {
            return spotifySongService.saveSong(song);
        }

        @DeleteMapping("/song/{entityId}")
        public String deleteSong(@PathVariable String entityId) throws SongNotFoundException {
            return spotifySongService.deleteSong(entityId);
        }

        @GetMapping("/saved-songs")
        public List<SpotifySong> getSavedSongs() {
            return spotifySongService.getSavedSongs();
        }

        @GetMapping(value = "/all-songs", produces = "application/json")
        public List<SpotifySong> getAllSongs() throws JSONException {
            return spotifySongService.getAllSongs();
        }

    }

