package com.capstone.Spotify.App.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
 * create the following fields
 * emailId,password,userName,mobile
 * use Lombok to generate no-args constructor,All Argument constructor, getters, setters, and toString() method.
 * Use @Document annotation to specify the collection name in Mysql
 */

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class SpotifyUser {
    @Id
    @GeneratedValue  (strategy = GenerationType.IDENTITY)
    @Column(name="Email_id")
    private String emailId;
    @Column(name="Password")
    private String password;
    @Column(name="User_name")
    private String userName;
    @Column(name="Mobile")
    private String mobile;
}



