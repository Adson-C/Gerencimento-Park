set foreign_key_checks = 0;

delete from usuarios;

set foreign_key_checks = 1;

alter table usuarios AUTO_INCREMENT = 1;


INSERT INTO usuarios (username, password, role, data_criacao, data_motificacao, criado_por, modificado_por)
VALUES 
('admin', 'hashed_password_here', 'ADMIN', NOW(), NULL, 'system', NULL),
('user1', 'hashed_password_here', 'USER', NOW(), NULL, 'system', NULL),
('user2', 'hashed_password_here', 'USER', NOW(), NULL, 'system', NULL);