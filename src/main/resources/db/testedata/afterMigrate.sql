set foreign_key_checks = 0;

delete from usuario;

set foreign_key_checks = 1;

alter table usuario AUTO_INCREMENT = 1;


INSERT INTO usuario (username, password, role, data_criacao, data_motificacao, criado_por, modificado_por)
VALUES
('admin@yahoo.com.br', '$2a$10$LS3kW5vvL1bgP4sV.svITewKWcNtjcOsm4dtBT7PsCb3xWexLtR1G', 'ROLE_ADMIN', NOW(), NULL, 'system', NULL),
('adson@gmail.com.br', '$2a$10$L1H5gBBtQAq9xwLH01PCVO2faFYeBb6KV7XbkVyJDy5iTpIXQv3FS', 'ROLE_CLIENTE', NOW(), NULL, 'system', NULL),
('john@gmail.com.br', '$2a$10$YWFStm.nNyqbSJGfCmIRUepHHqShTYPXckR3GcwPJEun1GacQftZG', 'ROLE_CLIENTE', NOW(), NULL, 'system', NULL);
