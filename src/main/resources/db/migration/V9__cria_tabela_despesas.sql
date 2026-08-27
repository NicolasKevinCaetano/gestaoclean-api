CREATE TABLE despesas (
    id BIGSERIAL PRIMARY KEY,
    valor NUMERIC(10, 2) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    data_despesa TIMESTAMP NOT NULL,
    observacoes TEXT
);