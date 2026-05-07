CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    role VARCHAR(25) NOT NULL,
    data_criacao DATETIME,
    data_motificacao DATETIME,
    criado_por VARCHAR(255),
    modificado_por VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;