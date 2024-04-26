import {BrowserRouter, Routes, Route} from 'react-router-dom';
import HomePage from './pages/Home';
import UserPage from './pages/UserPage';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Option page as the base route */}
        <Route path="/" element={<HomePage />} />

        {/* Routes for NGO and User pages */}
        {/* <Route path="/ngo" element={<NgoPage />} /> */}
        <Route path="/user" element={<UserPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App
