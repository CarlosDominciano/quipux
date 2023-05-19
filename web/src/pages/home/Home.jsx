import Header from "../../components/header/Header.jsx"
import Footer from "../../components/footer/Footer.jsx"
import CardPlaylist from "../../components/cardPlaylist/CardPlaylist.jsx";
import api from "../../api.js";
import { useNavigate } from "react-router-dom"
import { useState, useEffect } from "react";

function Home() {

    const navigate = useNavigate()
    const [playlists, setPlaylists] = useState( [] )

    useEffect(() => { listPlaylists() }, [] )

    function listPlaylists() {
        api.get("/playlists")
        .then(function (response) {
            console.log(response.data);
            setPlaylists(response.data);
        })
        .catch((error) => {
            console.log(error)
        });
    }

    //Aqui ficaria todas as playlists listadas e organizadas, cada uma pondendo ser expandida para ver as músicas 
    //todas as suas informações
    //Passei o dia todo tentando resolcer esse problema, então esse final acabou ficando sem css :(
    

    return (
        <>
            <Header/>
            {
            playlists.map((playlist, index) => {
                        return (    
                            <CardPlaylist 
                                key={playlist.id} 
                                id={playlist.id}
                                name={playlist.name}
                                description={playlist.description} 
                                musics={playlist.musics.length} 
                            />
                        );
                    })
            }
            <Footer/>
        </>
    )
    
}

export default Home;