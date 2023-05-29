import './cardMusicModal.css'

function CardMusicModal(props) {
    return (
        <section className="card-music-modal">
            <div className="container-card-music-modal">
                <span>Title: {props.title}</span>
                <span>Artist: {props.artist}</span>
                <span>Album: {props.album}</span>
                <span>Genre: {props.genre}</span>
                <span>Year: {props.creationYear}</span>
                <span>Release date: {props.releaseDate}</span>
            </div>
        </section>
    )
}

export default CardMusicModal