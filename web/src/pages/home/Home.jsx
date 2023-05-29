import Header from "../../components/header/Header.jsx"
import Footer from "../../components/footer/Footer.jsx"
import CardPlaylist from "../../components/cardPlaylist/CardPlaylist.jsx";
import api from "../../api.js";
import { useState, useEffect } from "react";
import './home.css'

function Home() {

    const [playlists, setPlaylists] = useState( [] )

    useEffect(() => {
        listPlaylists() 
    }, [] )
    

    function listPlaylists() {
        api.get("/playlists")
        .then(function (response) {
            setPlaylists(response.data);
        })
        .catch((error) => {
            console.log(error)
        });
    }

    function deletePlaylist(name) {
        api.delete(`/playlists/${name}`)
        .then(function (response) {
            setPlaylists(response.data);
            listPlaylists()
        })
        .catch((error) => {
            console.log(error)
        });
    }

    return (
        <>
            <Header/>
            <section className="home">
                <div className="container-home">
                {   
                    playlists.length > 0 ? (
                        playlists.map((playlist, index) => (
                            <CardPlaylist
                                key={playlist.id}
                                id={playlist.id}
                                name={playlist.name}
                                description={playlist.description}
                                musics={playlist.musics}
                                functionDeletePlaylist={deletePlaylist}
                            />
                        )
                    )
                    ) : (
                    <p>No playlists found</p>
                    )
                }
                </div>
            </section>
            
            <Footer/>
        </>
    )
    
}

export default Home;