package com.capstone.Spotify.App.service;

import com.capstone.Spotify.App.exception.SongNotFoundException;
import com.capstone.Spotify.App.model.SpotifySong;
import com.capstone.Spotify.App.exception.SongExistException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotifySongService {
    /*
     * Create methods in the SpotifySongService interface to perform the following
     * operations:
     *
     * Save a Song.
     * Delete a Song based on its entity_id.
     * Retrieve all Songs.
     * Retrieve all Saved Songs.
     * Search Songs by name
     *
     */
    public SpotifySong saveSong(SpotifySong song) throws SongExistException;

    public String deleteSong(String entityId) throws SongNotFoundException;

    public List<SpotifySong> getAllSongs() throws JSONException;

    public List<SpotifySong> getSavedSongs();

}




