package com.capstone.Spotify.App.service;

import java.util.Optional;

/* create the following methods
 * 1. validate user by checking the credentials  , if the emailId and password are correct return the user object
 * otherwise return null
 * 2. save user to the database  check if the user alraedy exists throw  UserAlreadyExistException
 *
 * autowire the UserRepository
 */

import com.capstone.Spotify.App.exception.UserAlreadyExistException;
import com.capstone.Spotify.App.model.SpotifyUser;
import com.capstone.Spotify.App.repository.SpotifyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SpotifyUserService {
    @Autowired
    private SpotifyUserRepository spotifyUserRepository;

    public SpotifyUser validateUser(String emailId, String password) {
        Optional<SpotifyUser>  existingUser = spotifyUserRepository.findByEmailIdAndPassword(emailId, password);
        if(existingUser.isPresent()) {
            return existingUser.get();
        }
        return null;
    }

    public SpotifyUser saveUser(SpotifyUser user) throws UserAlreadyExistException {
        Optional<SpotifyUser>  op=spotifyUserRepository.findById(user.getEmailId());
        if(op.isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }
        return spotifyUserRepository.save(user);
    }
}


