INSERT INTO users (username, password, enabled) values ('manager', '{bcrypt}$2a$12$2ldgasBC1IZXr5DS1dVyY.cfC8D3F1g1hOR0ZZNeqHobkIjGPvEP6', true);
INSERT INTO users (username, password, enabled) values ('admin', '{bcrypt}$2a$12$2ldgasBC1IZXr5DS1dVyY.cfC8D3F1g1hOR0ZZNeqHobkIjGPvEP6', true);
INSERT INTO users (username, password, enabled) values ('trainee', '{bcrypt}$2a$12$2ldgasBC1IZXr5DS1dVyY.cfC8D3F1g1hOR0ZZNeqHobkIjGPvEP6', true);
INSERT INTO users (username, password, enabled) values ('oldTrainee', '{bcrypt}$2a$12$2ldgasBC1IZXr5DS1dVyY.cfC8D3F1g1hOR0ZZNeqHobkIjGPvEP6', false);
-------------------------------
INSERT INTO authorities (username, authority) values ('manager', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('manager', 'ROLE_VIEW_INFO');

INSERT INTO authorities (username, authority) values ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('admin', 'ROLE_VIEW_INFO');
INSERT INTO authorities (username, authority) values ('admin', 'ROLE_VIEW_ADMIN');

INSERT INTO authorities (username, authority) values ('trainee', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('oldTrainee', 'ROLE_USER');