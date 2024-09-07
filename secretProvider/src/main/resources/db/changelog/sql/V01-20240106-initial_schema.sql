CREATE TABLE users (
    username VARCHAR(50) not null primary key,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    id integer primary key,
    username VARCHAR(50) not null,
    authority VARCHAR(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);

CREATE UNIQUE INDEX ix_auth_username on authorities (username, authority);

CREATE TABLE secrets (
     id VARCHAR(50) primary key,
     data text not null
);