import './cardPlaylist.css';
import '../../css/root.css'
import { useState } from 'react';
import ModalPlaylist from '../modalPlaylist/ModalPlaylist.jsx';

function CardPlaylist(props) {

    const [isModalOpen, setIsModalOpen] = useState(false);

    function handleOpenModal() {
        setIsModalOpen(prev => !prev)
    }

    return (
        <section className="card-playlist">
            <div className="container-card-playlist">
                <button className="delete-playlist"
                onClick={ () => props.functionDeletePlaylist(props.name)}
                >X</button>
                <h1>Name: {props.name}</h1> 
                <h1>Description: {props.description}</h1>
                <h1>Musics: {props.musics.length}</h1>
                <button onClick={handleOpenModal}>See songs</button>
            </div>
            {isModalOpen && 
            <ModalPlaylist
            name={props.name}
            musics={props.musics}
            functionHandleOpenModal={handleOpenModal}
            />}
        </section>
    )
}

export default CardPlaylist;