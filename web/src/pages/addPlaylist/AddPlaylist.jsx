import { useNavigate } from "react-router-dom"
import { useState, useEffect } from "react";

import Header from "../../components/header/Header.jsx"
import Footer from "../../components/footer/Footer.jsx"
import CardMusic from "../../components/cardMusic/CardMusic.jsx";
import api from "../../api.js";

import "./addPlaylist.css"

function AddPlaylist() {

    const navigate = useNavigate()
    const [name, setName] = useState("")
    const [description, setDescription] = useState("")
    const [musicsInPlaylist, setMusicsInPlaylist] = useState([])
    const [musics, setMusics] = useState( [] )
    
    function addMusic(id) {
        const index = musicsInPlaylist.indexOf(id);
        console.log(index)
        if (index !== -1) {
            musicsInPlaylist.splice(index, 1)
            return
        }
        musicsInPlaylist.push(id)
    }

    useEffect(() => { listMusics() }, [] )

    function listMusics() {
        api.get("/musics")
        .then((response)  => {
            console.log(response.data)
            setMusics(response.data);

        })
        .catch((error) => {
            console.log(error)
        });

    }
    
    function addPlaylist(e){

        e.preventDefault();

        const newPlaylist = {
            name: e.target.ipt_name.value,
            description: e.target.ipt_description.value,
            idMusics: musicsInPlaylist
        }

        api.post("/playlists", newPlaylist)
        .then((response) => {
            console.log("response: ", response)
            setTimeout(() => {
                navigate('/');
            }, 1000);
        })
        .catch((error) => console.error(error))
    }

    return (
        <>
            <Header/>
            <section className="add-playlist">
                <div className="container-add-playlist">
                    <div className="form">
                        <form id="form_add_playlist" onSubmit={addPlaylist} method="post">
                            <label htmlFor="name">Name</label>
                            <input placeholder="Devil's Never Cry" type="text" id="ipt_name"
                            onChange={(e) => {setName(e.target.value)}}/>

                            <label htmlFor="description">Description</label>
                            <input placeholder="Let's rock baby" type="text" id="ipt_description"
                            onChange={(e) => {setDescription(e.target.value)}}/>

                            <button id="btn_add_playlist">Create Playlist</button>
                        </form>
                    </div>
                    <div className="musics">
                        {
                            musics.length > 0 ? (
                                musics.map((music, index) => (
                                    <CardMusic
                                        key={music.id}
                                        id={music.id}
                                        title={music.title}
                                        artist={music.artist}
                                        album={music.album}
                                        creationYear={music.creationYear}
                                        genre={music.genre}
                                        releaseDate={music.releaseDate}
                                        functionAddMusic={addMusic}
                                    />
                            ))
                            ) : (
                            <p>No musics found</p>
                            )
                        }
                    </div>
                </div>
            </section>
            <Footer/>
        </>
    )
    
}

export default AddPlaylist;