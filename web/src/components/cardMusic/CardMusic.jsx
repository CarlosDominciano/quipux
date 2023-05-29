import { useState } from 'react'
import './cardMusic.css'

function CardMusic(props) {

    const [added, setAdded] = useState(false)
    
    return (
        <section className="card-music">
            <div className="container-card-music">
                <span>Title: {props.title}</span>
                <span>Artist: {props.artist}</span>
                <span>Album: {props.album}</span>
                <span>Genre: {props.genre}</span>
                <span>Year: {props.creationYear}</span>
                <span>Release date: {props.releaseDate}</span>
                <button onClick={() => { props.functionAddMusic(props.id); setAdded(!added) }}
                className={
                    added ? ( 
                        "remove-music-btn"
                     ) : ( 
                        "add-music-btn"
                     )
                }>
                    {
                        added ? ( 
                            <>Remove music</>
                         ) : ( 
                            <>Add music</>
                         )
                    }
                </button>
            </div>
        </section>
    )
}

export default CardMusic