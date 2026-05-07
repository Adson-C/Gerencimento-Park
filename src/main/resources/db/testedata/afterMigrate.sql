set foreign_key_checks = 0;

delete from usuario;

set foreign_key_checks = 1;

alter table usuario AUTO_INCREMENT = 1;


INSERT INTO usuario (username, password, role, data_criacao, data_motificacao, criado_por, modificado_por)
VALUES
('admin@yahoo.com.br', '123456', 'ROLE_ADMIN', NOW(), NULL, 'system', NULL),
('adson@gmail.com.br', '123456', 'ROLE_CLIENTE', NOW(), NULL, 'system', NULL),
('john@gmail.com.br', '123456', 'ROLE_CLIENTE', NOW(), NULL, 'system', NULL);