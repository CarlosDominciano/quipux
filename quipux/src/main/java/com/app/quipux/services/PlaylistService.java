package com.app.quipux.services;

import com.app.quipux.models.MusicModel;
import com.app.quipux.models.PlaylistModel;
import com.app.quipux.repositories.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    @Transactional
    public PlaylistModel save(PlaylistModel playlistModel) {
        return playlistRepository.save(playlistModel);
    }

    public List<PlaylistModel> findAll() {
        return playlistRepository.findAll();
    }

    public PlaylistModel findByName(String name) {
        return playlistRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return playlistRepository.existsByName(name);
    }

    public void deleteByName(String name) {
        playlistRepository.deleteByName(name);
    }

    public void delete(PlaylistModel playlistModel) {
        playlistRepository.delete(playlistModel);
    }
}
