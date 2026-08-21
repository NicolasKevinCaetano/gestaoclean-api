CREATE TABLE itens_agendamento (
    id BIGSERIAL PRIMARY KEY,

    agendamento_id BIGINT NOT NULL,

    servico VARCHAR(100) NOT NULL,

    valor DECIMAL(10,2) NOT NULL,

    status VARCHAR(30) NOT NULL,

    CONSTRAINT fk_item_agendamento
        FOREIGN KEY (agendamento_id)
        REFERENCES agendamentos(id)
);

INSERT INTO itens_agendamento (
    agendamento_id,
    servico,
    valor,
    status
)
SELECT
    id,
    servico,
    valor,
    status
FROM agendamentos;