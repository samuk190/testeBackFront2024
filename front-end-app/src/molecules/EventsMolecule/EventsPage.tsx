import React, { useState, useEffect } from "react";
import { useAuth } from "../../hooks/AuthContext";

interface Event {
  id: string;
  type: string;
  description: string;
  createdAt: string;
}

const EventsPage: React.FC = () => {
  const { token } = useAuth();
  const [events, setEvents] = useState<Event[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchEvents = async () => {
    setIsLoading(true);
    setError(null);

    try {
      const response = await fetch("http://localhost:8080/api/events", {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      if (!response.ok) {
        throw new Error("Erro ao buscar eventos");
      }

      const data = await response.json();
      setEvents(data);
    } catch (err: any) {
      setError(err.message || "Erro desconhecido");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchEvents();

    const interval = setInterval(() => {
      fetchEvents();
    }, 5000);

    return () => clearInterval(interval);
  }, [token]);

  return (
    <div style={{ maxWidth: "600px", margin: "0 auto", padding: "20px" }}>
      <h2>Eventos Recentes</h2>
      {isLoading && <p>Carregando...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
      <ul style={{ listStyle: "none", padding: 0 }}>
        {events.map((event) => (
          <li
            key={event.id}
            style={{
              marginBottom: "15px",
              padding: "10px",
              border: "1px solid #ddd",
              borderRadius: "5px",
              backgroundColor: "#f9f9f9",
            }}
          >
            <strong>Tipo:</strong> {event.type}
            <br />
            <strong>Descrição:</strong> {event.description}
            <br />
            <strong>Criado em:</strong> {new Date(event.createdAt).toLocaleString()}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default EventsPage;
