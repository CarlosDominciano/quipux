import { useEffect, useState } from 'react'
import './modalPlaylist.css'
import api from "../../api.js";
import CardMusicModal from '../cardMusicModal/CardMusicModal.jsx';

function ModalPlaylist(props) {
    return (
        <section className="modal-background">
            <div className="modal-content">
                <div className="content">
                <h2>Playlist: {props.name}</h2>
                <h2>Musics</h2>
                <div className={
                    props.musics.length > 0 ? ( 
                        "cards"
                     ) : ( 
                        ""
                     )
                }>
                {
                            props.musics.length > 0 ? (
                                props.musics.map((music, index) => (
                                    <CardMusicModal
                                        key={music.id}
                                        id={music.id}
                                        title={music.title}
                                        artist={music.artist}
                                        album={music.album}
                                        creationYear={music.creationYear}
                                        genre={music.genre}
                                        releaseDate={music.releaseDate}
                                    />
                                )
                            )
                            ) : (
                            <p>Playlist don't have musics</p>
                            )
                        }
                </div>
                <button onClick={() => (props.functionHandleOpenModal(false))}>Close playlist</button>
                </div>
            </div>
        </section>
    )
}

export default ModalPlaylist