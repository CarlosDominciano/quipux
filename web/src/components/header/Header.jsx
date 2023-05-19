import './header.css';
import '../../css/root.css'
import musicLogo from "../../assets/images/music_logo.png"

function Header() {
    return (
        <header className="header">
            <nav className="container">
                <img src={musicLogo} alt="" />
                <ul>
                    <li><a href='/'>Home</a></li>
                    <li><a href="/add-playlist">Playlist</a></li>
                    <li><a href='/add-music'>Music</a></li>
                </ul>
            </nav>
        </header>
    )
}

export default Header;