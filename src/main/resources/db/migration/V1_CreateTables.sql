CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    documento VARCHAR(255) NOT NULL UNIQUE,
    data_nasc DATE
);

CREATE TABLE carro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(255) NOT NULL,
    cor VARCHAR(255) NOT NULL,
    placa VARCHAR(255) NOT NULL UNIQUE,
    chassi VARCHAR(255) NOT NULL UNIQUE,
    ano INT NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE atendimento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    veiculo_id BIGINT,
    cliente_id BIGINT,
    data_agendamento DATE,
    horario_agendamento TIME,
    tipo VARCHAR(50),
    status VARCHAR(50),
    FOREIGN KEY (veiculo_id) REFERENCES carro(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
