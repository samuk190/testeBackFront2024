import React, { useState } from "react";
import { useAuth } from "../../hooks/AuthContext";

const CarrosPage: React.FC = () => {
  const { token } = useAuth(); // Obtém o token do Context API
  const [modelo, setModelo] = useState("");
  const [marca, setMarca] = useState("");
  const [ano, setAno] = useState("");
  const [message, setMessage] = useState<string | null>(null);

  const handleCreateCar = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage(null);

    try {
      const response = await fetch("http://localhost:8080/api/carros", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ modelo, marca, ano }),
      });

      if (!response.ok) {
        throw new Error("Erro ao criar carro");
      }

      setMessage("Carro criado com sucesso!");
      setModelo("");
      setMarca("");
      setAno("");
    } catch (err: any) {
      setMessage(err.message);
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "0 auto", padding: "20px" }}>
      <h2>Criar Carro</h2>
      <form onSubmit={handleCreateCar}>
        <div>
          <label>Modelo:</label>
          <input
            type="text"
            value={modelo}
            onChange={(e) => setModelo(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Marca:</label>
          <input
            type="text"
            value={marca}
            onChange={(e) => setMarca(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Ano:</label>
          <input
            type="number"
            value={ano}
            onChange={(e) => setAno(e.target.value)}
            required
          />
        </div>
        <button type="submit">Criar</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default CarrosPage;
