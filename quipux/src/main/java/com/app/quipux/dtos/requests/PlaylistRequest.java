package com.app.quipux.dtos.requests;

import com.app.quipux.models.MusicModel;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class PlaylistRequest {

    @NotBlank(message = "List name cannot be blank")
    private String name;

    @NotBlank(message = "List description cannot be blank")
    private String description;

    private List<Long> idMusics;

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

    public List<Long> getIdMusics() {
        return idMusics;
    }

    public void setIdMusics(List<Long> idMusics) {
        this.idMusics = idMusics;
    }
}
