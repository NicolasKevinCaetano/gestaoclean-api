CREATE TABLE ordens_servico (
    id BIGSERIAL PRIMARY KEY,

    agendamento_id BIGINT NOT NULL UNIQUE,

    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    observacoes TEXT,

    CONSTRAINT fk_ordem_servico_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agendamentos(id)
);