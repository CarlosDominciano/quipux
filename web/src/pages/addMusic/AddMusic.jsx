import { useState } from "react";
import Header from "../../components/header/Header.jsx"
import Footer from "../../components/footer/Footer.jsx"
import api from "../../api.js";
import './addMusic.css'
import { useNavigate } from "react-router-dom";
function AddMusic() {

    const navigate = useNavigate()

    const [title, setTitle] = useState("")
    const [artist, setArtist] = useState("")
    const [album, setAlbum] = useState("")
    const [creationYear, setCreationYear] = useState("")
    const [genre, setGenre] = useState("")
    const [releaseDate, setReleaseDate] = useState("")

    function addMusic(e){

        e.preventDefault();

        const newMusic = {
            title: e.target.ipt_title.value,
            artist: e.target.ipt_artist.value,
            album: e.target.ipt_album.value,
            creationYear: e.target.ipt_creationYear.value,
            genre: e.target.ipt_genre.value,
            releaseDate: e.target.ipt_releaseDate.value
        }

        api.post("/musics", newMusic)
        .then((response)=> {
            console.log("response: ", response)
            setTimeout(() => {
                navigate('/add-playlist');
            }, 1000);
        })
        .catch((error) => console.error(error))
    }

    return (
        <>
            <Header/>
            <section className="add-music">
                <div className="container-add-music">
                    <form id="form_add_music" onSubmit={addMusic} method="post">
                        <label htmlFor="title">Title</label>
                        <input placeholder="Devil's Never Cry" type="text" name="ipt_title" 
                        onChange={(e) => {setTitle(e.target.value)}} value={title}/>

                        <label htmlFor="artist">Artists</label>
                        <input placeholder="Tetsuya Shibata" type="text" name="ipt_artist"
                        onChange={(e) => {setArtist(e.target.value)}} value={artist}/>

                        <label htmlFor="album">Album</label>
                        <input placeholder="DMC3" type="text" name="ipt_album"
                        onChange={(e) => {setAlbum(e.target.value)}} value={album}/>

                        <label htmlFor="creationYear">Creation Year</label>
                        <input placeholder="2005" type="text" name="ipt_creationYear"
                        onChange={(e) => {setCreationYear(e.target.value)}} value={creationYear}/>

                        <label htmlFor="genre">Genre</label>
                        <input placeholder="Rock" type="text" name="ipt_genre"
                        onChange={(e) => {setGenre(e.target.value)}} value={genre}/>

                        <label htmlFor="releaseDate">Release date</label>
                        <input placeholder="2005-05-26" type="date" name="ipt_releaseDate"
                        onChange={(e) => {setReleaseDate(e.target.value)}} value={releaseDate}/>

                        <button id="btn_add_music">Create Music</button>
                    </form>
                </div>
            </section>
            <Footer/>
        </>
    )
    
}

export default AddMusic;