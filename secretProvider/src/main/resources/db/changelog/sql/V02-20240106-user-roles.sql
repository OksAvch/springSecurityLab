INSERT INTO users (username, password, enabled) values ('manager', '{bcrypt}$2a$12$w/wlJj4nA7sVvAO159Os5OulwNKBLnB6jRe3h6aKuPMhJ7q77Yb2q', true);
INSERT INTO users (username, password, enabled) values ('admin', '{bcrypt}$2a$12$w/wlJj4nA7sVvAO159Os5OulwNKBLnB6jRe3h6aKuPMhJ7q77Yb2q', true);
INSERT INTO users (username, password, enabled) values ('trainee', '{bcrypt}$2a$12$w/wlJj4nA7sVvAO159Os5OulwNKBLnB6jRe3h6aKuPMhJ7q77Yb2q', true);
INSERT INTO users (username, password, enabled) values ('oldTrainee', '{bcrypt}$2a$12$w/wlJj4nA7sVvAO159Os5OulwNKBLnB6jRe3h6aKuPMhJ7q77Yb2q', false);
-------------------------------
INSERT INTO authorities (id, username, authority) values (1, 'manager', 'ROLE_USER');
INSERT INTO authorities (id, username, authority) values (2, 'manager', 'ROLE_VIEW_INFO');

INSERT INTO authorities (id, username, authority) values (3, 'admin', 'ROLE_USER');
INSERT INTO authorities (id, username, authority) values (4, 'admin', 'ROLE_VIEW_INFO');
INSERT INTO authorities (id, username, authority) values (5, 'admin', 'ROLE_VIEW_ADMIN');

INSERT INTO authorities (id, username, authority) values (6, 'trainee', 'ROLE_USER');
INSERT INTO authorities (id, username, authority) values (7, 'oldTrainee', 'ROLE_USER');