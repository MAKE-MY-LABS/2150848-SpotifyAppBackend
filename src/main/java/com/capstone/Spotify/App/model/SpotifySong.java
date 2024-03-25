package com.capstone.Spotify.App.model;

/**
 * Song class has been created to store the song details.
 * Language:English
 * name: O Come All ye Faithful
 * entity_id : 1
 * favourite_count:100
 *  Use Lombok to generate no-args constructor,All Argument constructor,
 *  * getters,
 *  * setters, and toString() method.
    */


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="song")
public class SpotifySong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name="Language", columnDefinition = "VARCHAR(255)")
    private String language;

    @Column(name="Name", columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name="Entity_id", columnDefinition = "INTEGER")
    private int entity_id;

    @Column(name="Favourite_count", columnDefinition = "INTEGER")
    private int favourite_count;
}