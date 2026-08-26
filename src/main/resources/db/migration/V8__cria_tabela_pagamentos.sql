CREATE TABLE pagamentos (
    id BIGSERIAL PRIMARY KEY,

    ordem_servico_id BIGINT NOT NULL,

    valor NUMERIC(10, 2) NOT NULL,

    status VARCHAR(20) NOT NULL,

    data_pagamento TIMESTAMP,

    forma_pagamento VARCHAR(30),

    observacoes TEXT,

    CONSTRAINT fk_pagamento_ordem_servico
        FOREIGN KEY (ordem_servico_id)
        REFERENCES ordens_servico(id)
);
