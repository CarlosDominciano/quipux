import Header from "../../components/header/Header.jsx"
import Footer from "../../components/footer/Footer.jsx"

function AddMusic() {

    //Aqui ele iria receber as informações e guardar no banco para essas músicas serem utilizadas 
    //na tela de de AddPlaylist e assim fazer essas músicas (pelo menos seu nome e artista)
    //aparecerem em um <select> </select>

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

export default AddMusic;