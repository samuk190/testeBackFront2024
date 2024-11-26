import React, { useState } from "react";
import { useAuth } from "../../hooks/AuthContext";

const AtendimentosPage: React.FC = () => {
  const { token } = useAuth();
  const [carroId, setCarroId] = useState("");
  const [clienteId, setClienteId] = useState("");
  const [dataAgendamento, setDataAgendamento] = useState("");
  const [horarioAgendamento, setHorarioAgendamento] = useState("");
  const [tipo, setTipo] = useState("REPARO");
  const [status, setStatus] = useState("AGENDADO");
  const [message, setMessage] = useState<string | null>(null);

  const handleCreateAtendimento = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage(null);

    try {
      const response = await fetch("http://localhost:8080/api/atendimentos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          carroId,
          clienteId,
          dataAgendamento,
          horarioAgendamento,
          tipo,
          status,
        }),
      });

      if (!response.ok) {
        throw new Error("Erro ao criar atendimento");
      }

      setMessage("Atendimento criado com sucesso!");
      setCarroId("");
      setClienteId("");
      setDataAgendamento("");
      setHorarioAgendamento("");
      setTipo("REPARO");
      setStatus("AGENDADO");
    } catch (err: any) {
      setMessage(err.message);
    }
  };

  return (
    <div style={{ maxWidth: "400px", margin: "0 auto", padding: "20px" }}>
      <h2>Criar Atendimento</h2>
      <form onSubmit={handleCreateAtendimento}>
        <div>
          <label>ID do Carro:</label>
          <input
            type="text"
            value={carroId}
            onChange={(e) => setCarroId(e.target.value)}
            required
          />
        </div>
        <div>
          <label>ID do Cliente:</label>
          <input
            type="text"
            value={clienteId}
            onChange={(e) => setClienteId(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Data do Agendamento:</label>
          <input
            type="date"
            value={dataAgendamento}
            onChange={(e) => setDataAgendamento(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Horário do Agendamento:</label>
          <input
            type="time"
            value={horarioAgendamento}
            onChange={(e) => setHorarioAgendamento(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Tipo:</label>
          <select value={tipo} onChange={(e) => setTipo(e.target.value)}>
            <option value="REPARO">Reparo</option>
            <option value="MANUTENCAO_PREVENTIVA">Manutenção Preventiva</option>
            <option value="LIMPEZA">Limpeza</option>
          </select>
        </div>
        <div>
          <label>Status:</label>
          <select value={status} onChange={(e) => setStatus(e.target.value)}>
            <option value="AGENDADO">Agendado</option>
            <option value="EM_ANDAMENTO">Em Andamento</option>
            <option value="CONCLUIDO">Concluído</option>
          </select>
        </div>
        <button type="submit">Criar</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default AtendimentosPage;
