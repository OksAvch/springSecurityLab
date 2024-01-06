INSERT INTO users (username, password, enabled) values ('user', '{bcrypt}$2a$12$2ldgasBC1IZXr5DS1dVyY.cfC8D3F1g1hOR0ZZNeqHobkIjGPvEP6', true);
INSERT INTO users (username, password, enabled) values ('admin', '{bcrypt}$2a$12$2ldgasBC1IZXr5DS1dVyY.cfC8D3F1g1hOR0ZZNeqHobkIjGPvEP6', true);

INSERT INTO authorities (username, authority) values ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('admin', 'ROLE_ADMIN');