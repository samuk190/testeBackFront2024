import { useEffect, useState } from "react";
import axios from "axios";

function EventPolling() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    const interval = setInterval(() => {
      axios.get("/api/events").then((response) => {
        setEvents(response.data);
      });
    }, 5000);

    return () => clearInterval(interval);
  }, []);

  const markAsRead = (id: any) => {
    axios.post("/api/events/acknowledge", { id }).then(() => {
      setEvents((prevEvents) => prevEvents.filter((event: any) => event.id !== id));
    });
  };

  return (
    <div>
      <h1>Eventos</h1>
      {events.map((event: any) => (
        <div key={event.id}>
          <p>{event.payload}</p>
          <button onClick={() => markAsRead(event.id)}>Marcar como lido</button>
        </div>
      ))}
    </div>
  );
}

export default EventPolling;
