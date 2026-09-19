CREATE TABLE usuarios (
                          id VARCHAR(36) PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(150) NOT NULL UNIQUE,
                          cpf VARCHAR(14) UNIQUE,
                          cnpj VARCHAR(18) UNIQUE,
                          senha VARCHAR(255) NOT NULL,
                          telefone VARCHAR(20)
);

CREATE TABLE contas_bancarias (
                                  numero_conta VARCHAR(36) PRIMARY KEY,
                                  numero_agencia VARCHAR(20),
                                  codigo_banco VARCHAR(20),
                                  nome_banco VARCHAR(100) NOT NULL,
                                  saldo NUMERIC(15, 2) NOT NULL DEFAULT 0.00,
                                  status VARCHAR(20) NOT NULL,
                                  usuario_id VARCHAR(36) NOT NULL UNIQUE,

                                  CONSTRAINT fk_conta_usuario
                                      FOREIGN KEY (usuario_id)
                                          REFERENCES usuarios(id)
);

CREATE TABLE chaves_pix (
                            id BIGSERIAL PRIMARY KEY,
                            chave VARCHAR(255) NOT NULL UNIQUE,
                            tipo VARCHAR(20) NOT NULL,
                            conta_id VARCHAR(36) NOT NULL,

                            CONSTRAINT fk_chave_conta
                                FOREIGN KEY (conta_id)
                                    REFERENCES contas_bancarias(numero_conta)
);

CREATE TABLE transacoes (
                            id VARCHAR(36) PRIMARY KEY,
                            origem_conta_id VARCHAR(36) NOT NULL,
                            destino_conta_id VARCHAR(36) NOT NULL,
                            valor NUMERIC(15, 2) NOT NULL,
                            data_hora TIMESTAMP NOT NULL,
                            status VARCHAR(20) NOT NULL,

                            CONSTRAINT fk_transacao_origem
                                FOREIGN KEY (origem_conta_id)
                                    REFERENCES contas_bancarias(numero_conta),

                            CONSTRAINT fk_transacao_destino
                                FOREIGN KEY (destino_conta_id)
                                    REFERENCES contas_bancarias(numero_conta)
);