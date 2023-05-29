package com.app.quipux.controllers;

import com.app.quipux.dtos.requests.PlaylistRequest;
import com.app.quipux.exceptions.MusicNotFoundException;
import com.app.quipux.exceptions.PlaylistNameAlreadyExistsException;
import com.app.quipux.models.MusicModel;
import com.app.quipux.models.PlaylistModel;
import com.app.quipux.services.MusicService;
import com.app.quipux.services.PlaylistService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {

    //Endpoint para criar playlist:
    //Método: POST
    //URL: /api/playlists
    //Descrição: Cria uma nova playlist.
    //Corpo da requisição: Name, Description e Object(Musics).
    //Resposta: Objeto de playlist recém-criado.

    //Endpoint para recuperar todas as playlists:
    //Método: GET
    //URL: /api/playlists
    //Descrição: Recupera todas as playlists existentes.
    //Resposta: Array de objetos de playlists.

    //Endpoint para recuperar uma playlist:
    //Método: GET
    //URL: /api/playlists/nomeDaPlaylist
    //Descrição: Recupera uma playlist existente.
    //Resposta: objeto de playlist.

    //Endpoint para recuperar playlist:
    //Método: DELETE
    //URL: /api/playlists/nomeDaPlaylist
    //Descrição: Exclui uma playlist existente.
    //Resposta: Status 204.

    @Autowired
    PlaylistService playlistService;

    @Autowired
    MusicService musicService;

    @PostMapping
    public ResponseEntity<PlaylistModel> createPlaylist(@RequestBody @Valid PlaylistRequest playlistRequest) {
        if (playlistService.existsByName(playlistRequest.getName()))
            throw new PlaylistNameAlreadyExistsException();
        List<MusicModel> musics = new ArrayList<>();
        for (Long idMusic : playlistRequest.getIdMusics()) {
            if (!musicService.existsById(idMusic))
                throw new MusicNotFoundException();
            musics.add(musicService.findById(idMusic).get());
        }
        PlaylistModel playlistModel = new PlaylistModel();
        BeanUtils.copyProperties(playlistRequest, playlistModel);
        playlistModel.setMusics(musics);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlistService.save(playlistModel));
    }

    @GetMapping
    public ResponseEntity<List<PlaylistModel>> findAllPlaylists() {
        List<PlaylistModel> playlists = playlistService.findAll();
        if (playlists.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(playlists);
    }

    @GetMapping("/{playlistName}")
    public ResponseEntity<PlaylistModel> findPlaylistByName(@PathVariable String playlistName) {
        if (!playlistService.existsByName(playlistName))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(playlistService.findByName(playlistName));
    }

    @DeleteMapping("{playlistName}")
    public ResponseEntity<String> deletePlaylistByName(@PathVariable String playlistName) {
        if (!playlistService.existsByName(playlistName))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        PlaylistModel deletedPlaylist = playlistService.findByName(playlistName);
        playlistService.delete(deletedPlaylist);
        String responseBody = String.format("This playlist: %s, was deleted", deletedPlaylist.getName());
        // response não é mostrado no body pq é NO_CONTENT
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseBody);
    }
}
