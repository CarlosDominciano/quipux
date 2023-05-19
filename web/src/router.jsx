import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from "./pages/home/Home.jsx"
import AddPlaylist from './pages/addPlaylist/AddPlaylist.jsx';
import AddMusic from './pages/addMusic/AddMusic.jsx';

function Router() {
  return (
    <BrowserRouter>                                        
        <Routes>       
		    <Route path="/" element={<Home />} />    
		    <Route path="/add-playlist" element={<AddPlaylist />} />    
		    <Route path="/add-music" element={<AddMusic />} />    
        </Routes>
    </BrowserRouter>
  );
}

export default Router;