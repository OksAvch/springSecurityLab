INSERT INTO users (username, password, enabled) values ('manager', '{bcrypt}$2a$12$lau.u5yXYECvCYN2OMgdRe1eJnVFJmvT4jAdNuB4JTomNXZH4URjC', true);
INSERT INTO users (username, password, enabled) values ('admin', '{bcrypt}$2a$12$lau.u5yXYECvCYN2OMgdRe1eJnVFJmvT4jAdNuB4JTomNXZH4URjC', true);
INSERT INTO users (username, password, enabled) values ('trainee', '{bcrypt}$2a$12$lau.u5yXYECvCYN2OMgdRe1eJnVFJmvT4jAdNuB4JTomNXZH4URjC', true);
INSERT INTO users (username, password, enabled) values ('oldTrainee', '{bcrypt}$2a$12$lau.u5yXYECvCYN2OMgdRe1eJnVFJmvT4jAdNuB4JTomNXZH4URjC', false);
-------------------------------
INSERT INTO authorities (id, username, authority) values (1, 'manager', 'ROLE_USER');
INSERT INTO authorities (id, username, authority) values (2, 'manager', 'ROLE_VIEW_INFO');

INSERT INTO authorities (id, username, authority) values (3, 'admin', 'ROLE_USER');
INSERT INTO authorities (id, username, authority) values (4, 'admin', 'ROLE_VIEW_INFO');
INSERT INTO authorities (id, username, authority) values (5, 'admin', 'ROLE_VIEW_ADMIN');

INSERT INTO authorities (id, username, authority) values (6, 'trainee', 'ROLE_USER');
INSERT INTO authorities (id, username, authority) values (7, 'oldTrainee', 'ROLE_USER');