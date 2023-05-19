package com.app.quipux.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_musics")
public class MusicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Music title cannot be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Music artist cannot be blank")
    @Column(nullable = false)
    private String artist;

    @NotBlank(message = "Music album cannot be blank")
    @Column(nullable = false)
    private String album;

    @Column(nullable = false)
    @Min(value = 1900, message = "the year must be at least 1900")
    @Max(value = 2023, message = "the year must be at most 2023")
    private Integer creationYear;

    @NotBlank(message = "Music genre cannot be blank")
    @Column(nullable = false)
    private String genre;

    public MusicModel() {
    }

    public MusicModel(Long id, String title, String artist, String album, Integer creationYear, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.creationYear = creationYear;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
