import './App.scss';
import 'boxicons/css/boxicons.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import AppLayout from './components/layout/AppLayout';
import Blank from './pages/Blank';
import Personal from './pages/Personal';
import Diagram from './pages/Diagram';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<AppLayout />}>
                    <Route index element={<Personal />} />
                    <Route path='/football' element={<Diagram />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
