package com.capstone.Spotify.App.controller;
import com.capstone.Spotify.App.exception.UserAlreadyExistException;
import com.capstone.Spotify.App.model.SpotifyUser;
import com.capstone.Spotify.App.service.SpotifyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/*
 *create respective end points for the methods in userService
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class SpotifyAppUserController {

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    @Autowired
    private SpotifyUserService spotifyUserService;

    @Autowired
    private SpotifyUser spotifyuser;

    @PostMapping("/register")
    public ResponseEntity<SpotifyUser> saveUser(@RequestBody SpotifyUser spotifyUser) throws UserAlreadyExistException {
        SpotifyUser savedUser = spotifyUserService.saveUser(spotifyUser);
        return new ResponseEntity<SpotifyUser>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> validateUser(@RequestBody SpotifyUser spotifyUser) {
        SpotifyUser user1 = spotifyUserService.validateUser(spotifyUser.getEmailId(), spotifyUser.getPassword());
        if (user1 == null) {
            return new ResponseEntity<String>("User is invalid", HttpStatus.NOT_FOUND);
        }
        String token = getToken(spotifyUser.getEmailId());
        return new ResponseEntity<String>("User logged in successfully !! Token:" + token, HttpStatus.OK);
    }

    // create method gettoken to generate token based on emailId
    public String getToken(String emailId) {
        return Jwts.builder().setSubject(emailId).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, jwtSigningKey).compact();
    }



}

