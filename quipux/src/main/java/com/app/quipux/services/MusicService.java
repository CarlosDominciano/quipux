package com.app.quipux.services;

import com.app.quipux.models.MusicModel;
import com.app.quipux.models.PlaylistModel;
import com.app.quipux.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    @Autowired
    MusicRepository musicRepository;


    @Transactional
    public MusicModel save(MusicModel musicModel) {
        return musicRepository.save(musicModel);
    }

    public boolean existsById(Long id) {
        return musicRepository.existsById(id);
    }

    public List<MusicModel> findAll() {
        return musicRepository.findAll();
    }

    public Optional<MusicModel> findById(Long id) {
        return musicRepository.findById(id);
    }


    public void delete(MusicModel musicModel) {
        musicRepository.delete(musicModel);
    }


}
