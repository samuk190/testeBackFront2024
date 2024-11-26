import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { useAuth } from './hooks/AuthContext'; // Contexto de autenticação
import MainLayout from './molecules/MainMolecule/MainLayout';
import AppointmentsPage from './molecules/AppointmentMolecule/AppointmentsPage';
import EventsPage from './molecules/EventsMolecule/EventsPage';
import CarsPage from './molecules/CarMolecule/CarsPage';
import LoginPage from './molecules/LoginMolecule';
import ClientsPage from './molecules/ClientsMolecule.tsx/ClientsPage';

const ProtectedRoute = ({ children }: { children: JSX.Element }) => {
  const { user } = useAuth();
  return user ? children : <LoginPage />;
};

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />

        <Route
          path="/app/*"
          element={
            <ProtectedRoute>
              <MainLayout />
            </ProtectedRoute>
          }
        >
          <Route path="cars" element={<CarsPage />} />
          <Route path="clients" element={<ClientsPage />} />
          <Route path="appointments" element={<AppointmentsPage />} />
          <Route path="events" element={<EventsPage />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;
