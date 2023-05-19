package com.app.quipux.repositories;

import com.app.quipux.models.PlaylistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistModel, Long> {

    boolean existsByName(String name);

    PlaylistModel findByName(String name);

    void deleteByName(String name);
}
