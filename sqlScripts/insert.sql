CREATE TABLE roles(
    role_id SERIAL PRIMARY KEY,
    name varchar
);

INSERT INTO roles(name) VALUES ('ADMIN');
INSERT INTO roles(name) VALUES ('STUDENT');