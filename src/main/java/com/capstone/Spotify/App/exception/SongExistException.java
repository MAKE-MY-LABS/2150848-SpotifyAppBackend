package com.capstone.Spotify.App.exception;

public class SongExistException extends Exception{
    /*
     * StockAlreadyExistsException EXTENDS Exception
     */
        public SongExistException(String message) {
            super(message);
        }
    }

