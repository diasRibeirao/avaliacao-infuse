CREATE TABLE PEDIDOS (
    CODIGO_PEDIDO BIGINT NOT NULL AUTO_INCREMENT,
    NUMERO_CONTROLE VARCHAR(40) UNIQUE NOT NULL,
    DATA_CADASTRO DATETIME NOT NULL,
    NOME VARCHAR(500) NOT NULL,
    VALOR DECIMAL(38, 2) NOT NULL,
    QUANTIDADE INT NOT NULL,
    CODIGO_CLIENTE BIGINT NOT NULL,
    VALOR_TOTAL DECIMAL(38, 2) NOT NULL,
    OBSERVACAO VARCHAR(1000),

    PRIMARY KEY(CODIGO_PEDIDO),
    KEY FK_PEDIDOS_CLIENTE (CODIGO_CLIENTE),
    CONSTRAINT FK_PEDIDOS_CLIENTE FOREIGN KEY (CODIGO_CLIENTE) REFERENCES CLIENTES (CODIGO_CLIENTE)
);