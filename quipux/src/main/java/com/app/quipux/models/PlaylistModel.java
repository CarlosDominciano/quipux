package com.app.quipux.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_playlists")
public class PlaylistModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "List name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "List description cannot be blank")
    @Column(nullable = false)
    private String description;

    @Valid
    @Column(nullable = true)
    @ManyToMany
    private List<MusicModel> musics;

    public PlaylistModel() {
    }

    public PlaylistModel(Long id, String name, String description, List<MusicModel> musics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.musics = musics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MusicModel> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicModel> musics) {
        this.musics = musics;
    }
}
