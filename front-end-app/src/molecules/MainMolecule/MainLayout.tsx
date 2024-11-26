import React from "react";
import { Link, Outlet } from "react-router-dom";

const MainLayout: React.FC = () => {
  return (
    <div style={{ fontFamily: "Arial, sans-serif", maxWidth: "800px", margin: "0 auto" }}>
      <header style={{ padding: "10px 20px", backgroundColor: "#007BFF", color: "white" }}>
        <h1 style={{ margin: 0, fontSize: "24px" }}>Sistema de Gerenciamento</h1>
      </header>

      <nav style={{ margin: "20px 0", padding: "10px", border: "1px solid #ddd", borderRadius: "5px" }}>
        <ul style={{ display: "flex", gap: "15px", listStyle: "none", padding: 0, margin: 0 }}>
          <li>
            <Link to="/cars" style={{ textDecoration: "none", color: "#007BFF" }}>
              Carros
            </Link>
          </li>
          <li>
            <Link to="/clients" style={{ textDecoration: "none", color: "#007BFF" }}>
              Clientes
            </Link>
          </li>
          <li>
            <Link to="/appointments" style={{ textDecoration: "none", color: "#007BFF" }}>
              Atendimentos
            </Link>
          </li>
          <li>
            <Link to="/events" style={{ textDecoration: "none", color: "#007BFF" }}>
              Eventos
            </Link>
          </li>
          <li>
            <Link to="/logout" style={{ textDecoration: "none", color: "#FF0000" }}>
              Logout
            </Link>
          </li>
        </ul>
      </nav>

      <main style={{ padding: "20px", border: "1px solid #ddd", borderRadius: "5px", backgroundColor: "#f9f9f9" }}>
        <Outlet />
      </main>
    </div>
  );
};

export default MainLayout;
