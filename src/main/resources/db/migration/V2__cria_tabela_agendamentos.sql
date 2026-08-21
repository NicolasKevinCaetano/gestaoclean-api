CREATE TABLE agendamentos (
    id BIGSERIAL PRIMARY KEY,

    cliente_id BIGINT NOT NULL,

    data_agendamento TIMESTAMP NOT NULL,

    servico VARCHAR(100) NOT NULL,

    observacoes TEXT,

    status VARCHAR(30) NOT NULL,

    valor DECIMAL(10,2),

    CONSTRAINT fk_agendamento_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES clientes(id)
);