package com.app.quipux.controllers;

import com.app.quipux.dtos.requests.PlaylistRequest;
import com.app.quipux.exceptions.MusicNotFoundException;
import com.app.quipux.exceptions.MusicYearIsAfterTodayException;
import com.app.quipux.exceptions.MusicYearIsBeforeThanTwoYearsException;
import com.app.quipux.exceptions.PlaylistNameAlreadyExistsException;
import com.app.quipux.models.MusicModel;
import com.app.quipux.models.PlaylistModel;
import com.app.quipux.repositories.MusicRepository;
import com.app.quipux.repositories.PlaylistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlaylistControllerTest {

    @Autowired
    private PlaylistController playlistController;

    @MockBean
    private PlaylistRepository playlistRepository;

    @MockBean
    private MusicRepository musicRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MusicController musicController;

   @Test
   @DisplayName("Music: Return throw year now")
   public void testPostMusicYearHiggerThanToday() {
       MusicModel musicModel = new MusicModel();
       musicModel.setReleaseDate(LocalDate.parse("2024-05-05"));
       assertThrows(MusicYearIsAfterTodayException.class, () -> {
           musicController.createMusic(musicModel);
       });
   }
//    @Test
//    @DisplayName("Music: Return throw year 2 years")
//    public void testPostMusicYearOlderThanTwoYears() {
//        MusicModel musicModel1 = new MusicModel();
//        musicModel1.setReleaseDate(LocalDate.parse("2021-05-05"));
//        MusicModel musicModel2 = new MusicModel();
//        musicModel2.setReleaseDate(LocalDate.parse("2020-05-05"));
//        MusicModel musicModel3 = new MusicModel();
//        musicModel3.setReleaseDate(LocalDate.parse("2019-05-05"));
//        assertThrows(MusicYearIsBeforeThanTwoYearsException.class, () -> {
//            musicController.createMusic(musicModel1);
//        });
//        assertThrows(MusicYearIsBeforeThanTwoYearsException.class, () -> {
//            musicController.createMusic(musicModel2);
//        });
//        assertThrows(MusicYearIsBeforeThanTwoYearsException.class, () -> {
//            musicController.createMusic(musicModel3);
//        });
//    }


    @Test
    @DisplayName("Playlist: Return already exists exception")
    public void testPostPlaylistAlreadyExists() {
        PlaylistRequest playlistRequest = new PlaylistRequest();
        playlistRequest.setName("Already Exists");
        playlistRequest.setDescription("Let's rock baby");
        playlistRequest.setIdMusics(List.of(2L, 1L));
        Mockito.when(playlistRepository.existsByName(playlistRequest.getName())).thenReturn(true);
        assertThrows(PlaylistNameAlreadyExistsException.class, () -> {
            playlistController.createPlaylist(playlistRequest);
        });
    }

    @Test
    @DisplayName("Playlist: Return music don't exists exception")
    public void testPostPlaylistMusicNotExists() {
        PlaylistRequest playlistRequest = new PlaylistRequest();
        playlistRequest.setName("Music id don't exists");
        playlistRequest.setDescription("Let's rock baby");
        playlistRequest.setIdMusics(List.of(2L));
        Mockito.when(playlistRepository.existsByName(playlistRequest.getName())).thenReturn(false);
        Mockito.when(musicRepository.existsById(playlistRequest.getIdMusics().get(0))).thenReturn(false);
        assertThrows(MusicNotFoundException.class, () -> {
            playlistController.createPlaylist(playlistRequest);
        });
    }
    @Test
    @DisplayName("Playlist: Create playlist and return 201 status")
    public void testPostPlaylistCreated() {
        PlaylistRequest playlistRequest = new PlaylistRequest();
        playlistRequest.setName("DMC");
        playlistRequest.setDescription("Let's rock baby");
        playlistRequest.setIdMusics(List.of(2L));
        Mockito.when(playlistRepository.existsByName(playlistRequest.getName())).thenReturn(false);
        Mockito.when(musicRepository.existsById(playlistRequest.getIdMusics().get(0))).thenReturn(true);
        Mockito.when(musicRepository.findById(playlistRequest.getIdMusics().get(0))).thenReturn(
                Optional.of(new MusicModel())
        );
        ResponseEntity<PlaylistModel> playlistCreation = playlistController.createPlaylist(playlistRequest);
        assertEquals(201, playlistCreation.getStatusCodeValue());
    }

    @Test
    @DisplayName("Playlist: Return empty list and 204 status")
    public void testGetEmptyListPlaylist() {
        Mockito.when(playlistRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<PlaylistModel>> playlistList = playlistController.findAllPlaylists();
        assertEquals(204, playlistList.getStatusCodeValue());
        assertNull(playlistList.getBody());
    }

    @Test
    @DisplayName("Playlist: Return list and 200 status")
    public void testGetListPlaylist() {
        Mockito.when(playlistRepository.findAll()).thenReturn(List.of(
                new PlaylistModel(),
                new PlaylistModel()
        ));
        ResponseEntity<List<PlaylistModel>> playlistList = playlistController.findAllPlaylists();
        assertEquals(200, playlistList.getStatusCodeValue());
    }

    @Test
    @DisplayName("Playlist: Return name not found and 404 status")
    public void testGetFindPlaylistByNameNotFound() throws Exception {
        String playlistName = "Don't exists";

        mockMvc.perform(MockMvcRequestBuilders.get("/{playlistName}", playlistName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Playlist: Return playlist exists and 200 status")
    public void testGetFindPlaylistByNameOk() throws Exception {
        String playlistName = "Exists";
        Mockito.when(playlistRepository.existsByName(playlistName)).thenReturn(true);
        ResponseEntity<PlaylistModel> playlistFindByName = playlistController.findPlaylistByName(playlistName);
        assertEquals(200, playlistFindByName.getStatusCodeValue());
    }

    @Test
    @DisplayName("Playlist: Return playlist not found and 404 status")
    public void testDeletePlaylistByNameNotFound() throws Exception {
        String playlistName = "Don't exists";
        Mockito.when(playlistRepository.existsByName(playlistName)).thenReturn(false);
        ResponseEntity<String> playlistDeleteByName = playlistController.deletePlaylistByName(playlistName);
        assertEquals(404, playlistDeleteByName.getStatusCodeValue());
    }

    @Test
    @DisplayName("Playlist: Return playlist not found and 404 status")
    public void testDeletePlaylistByNameOk() throws Exception {
        String playlistName = "DMC";
        Mockito.when(playlistRepository.existsByName(playlistName)).thenReturn(true);
        Mockito.when(playlistRepository.findByName(playlistName)).thenReturn(
                new PlaylistModel(1L, playlistName, "I am a teapot?", List.of(new MusicModel()))
        );
        ResponseEntity<String> playlistDeleteByName = playlistController.deletePlaylistByName(playlistName);
        assertEquals(204, playlistDeleteByName.getStatusCodeValue());
        assertEquals("This playlist: " + playlistName + ", was deleted", playlistDeleteByName.getBody());
    }
}
