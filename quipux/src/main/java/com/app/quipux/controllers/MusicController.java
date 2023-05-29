package com.app.quipux.controllers;

import com.app.quipux.exceptions.MusicAlreadyExistsException;
import com.app.quipux.exceptions.MusicNotFoundException;
import com.app.quipux.exceptions.MusicYearIsAfterTodayException;
import com.app.quipux.exceptions.MusicYearIsBeforeThanTwoYearsException;
import com.app.quipux.models.MusicModel;
import com.app.quipux.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/musics")
public class MusicController {

    //Endpoint para criar playlist:
    //Método: POST
    //URL: /api/musics
    //Descrição: Cria uma nova musica.
    //Corpo da requisição: Title, Artist, Genre, ReleaseDate, CreationYear, Album.
    //Resposta: Objeto de musica recém-criado.

    //Endpoint para recuperar todas as musicas:
    //Método: GET
    //URL: /api/musics
    //Descrição: Recupera todas as musicas existentes.
    //Resposta: Array de objetos de musicas.

    //Endpoint para recuperar uma musica:
    //Método: GET
    //URL: /api/musics/idDaMusica
    //Descrição: Recupera uma musica existente.
    //Resposta: objeto de musica.

    //Endpoint para recuperar playlist:
    //Método: DELETE
    //URL: /api/musics/idDaMusica
    //Descrição: Exclui uma musica existente.
    //Resposta: Status 204.

    @Autowired
    MusicService musicService;

    @PostMapping
    public ResponseEntity<MusicModel> createMusic(@RequestBody @Valid MusicModel musicModel) {
        if (musicModel.getReleaseDate().isAfter(LocalDateTime.now().toLocalDate()))
            throw new MusicYearIsAfterTodayException();
//        if (musicModel.getReleaseDate().isBefore(LocalDateTime.now().toLocalDate().minusYears(2)))
//            throw new MusicYearIsBeforeThanTwoYearsException();
        return ResponseEntity.status(HttpStatus.CREATED).body(musicService.save(musicModel));
    }

    @GetMapping
    public ResponseEntity<List<MusicModel>> findAllMusics() {
        List<MusicModel> musics = musicService.findAll();
        if (musics.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.OK).body(musics);
    }

    @GetMapping("/{idMusic}")
    public ResponseEntity<MusicModel> findMusicById(@PathVariable Long idMusic) {
        if (!musicService.existsById(idMusic))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(musicService.findById(idMusic).get());
    }

    @DeleteMapping("{idMusic}")
    public ResponseEntity<String> deleteMusicById(@PathVariable Long idMusic) {
        if (!musicService.existsById(idMusic))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        MusicModel deletedMusic = musicService.findById(idMusic).get();
        musicService.delete(deletedMusic);
        String responseBody = String.format("This music: %s, was deleted", deletedMusic.getTitle());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseBody);
    }

}
