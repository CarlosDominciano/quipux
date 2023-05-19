import './cardPlaylist.css';
import '../../css/root.css'

function CardPlaylist(props) {
    return (
        <section className="CardPlaylist">
            <div className="container">
                <h1>{props.name}</h1> <br />
                <h1>{props.description}</h1> <br />
                <h1>{props.musics}</h1> <br />
            </div>
        </section>
    )
}

export default CardPlaylist;