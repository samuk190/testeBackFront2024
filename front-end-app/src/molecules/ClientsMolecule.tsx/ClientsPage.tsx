import React, { useState } from "react";
import { useAuth } from "../../hooks/AuthContext";

const ClientesPage: React.FC = () => {
  const { token } = useAuth();
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [telefone, setTelefone] = useState("");
  const [message, setMessage] = useState<string | null>(null);

  const handleCreateClient = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage(null);

    try {
      const response = await fetch("http://localhost:8080/api/clientes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ nome, email, telefone }),
      });

      if (!response.ok) {
        throw new Error("Erro ao criar cliente");
      }

      setMessage("Cliente criado com sucesso!");
      setNome("");
      setEmail("");
      setTelefone("");
    } catch (err: any) {
      setMessage(err.message);
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "0 auto", padding: "20px" }}>
      <h2>Criar Cliente</h2>
      <form onSubmit={handleCreateClient}>
        <div>
          <label>Nome:</label>
          <input
            type="text"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Telefone:</label>
          <input
            type="text"
            value={telefone}
            onChange={(e) => setTelefone(e.target.value)}
            required
          />
        </div>
        <button type="submit">Criar</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default ClientesPage;
