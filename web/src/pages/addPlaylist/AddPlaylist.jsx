import { useNavigate } from "react-router-dom"
import { useState, useEffect } from "react";

import Header from "../../components/header/Header.jsx"
import Footer from "../../components/footer/Footer.jsx"
import api from "../../api.js";
import axios from "axios";

import "./AddPlaylist.css"

function AddPlaylist() {

    const navigate = useNavigate()
    const [musics, setMusics] = useState( [] )

    useEffect(() => { listMusics() }, [] )

    function listMusics() {
        api.get("/musics")
        .then(function (response) {
            console.log(response.data);
            setMusics(response.data);
        })
        .catch((error) => {
            console.log(error)
        });
    }

    //Ele pegaria os dados e os deixaria salvos em um array para que o usuário pudesse utilizar disso depois para
    //colocar esses dados na playlist, de preferência através de um 

    return (
        <>
            <Header/>
            <section className="add-playlist">
                <div className="container">
                    <form id="form_add_playlist" action="" method="post">
                        <label htmlFor="name">Name</label>
                        <input placeholder="Devil's Never Cry" type="text" id="name"/>

                        <label htmlFor="description">Description</label>
                        <input placeholder="Let's rock baby" type="text" id="description"/>

                        <label htmlFor="musics">Musics</label>
                        <input placeholder="Musics" type="text" id="musics"/>

                        <button id="btn_add_playlist" type="submit">Create Playlist</button>
                    </form>
                </div>
            </section>
            <Footer/>
        </>
    )
    
}

export default AddPlaylist;