CREATE DATABASE my_database;

\connect my_database;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INTEGER,
    email VARCHAR(255)
);

INSERT INTO users (name, age, email) VALUES
('John', 30, 'john@example.com'),
('Alice', 25, 'alice@example.com'),
('Bob', 35, 'bob@example.com');

SELECT * FROM users;

DELETE FROM users
WHERE name = 'Bob';

SELECT * FROM users;
