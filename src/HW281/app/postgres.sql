CREATE DATABASE sales_database;

\connect sales_database;

CREATE TABLE IF NOT EXISTS sales (
    id SERIAL PRIMARY KEY,
    product VARCHAR(100),
    price NUMERIC(10, 2),
    quantity INTEGER
);

INSERT INTO sales (product, price, quantity) VALUES
('Laptop', 1000, 5),
('Phone', 700, 3),
('Tablet', 500, 2),
('Printer', 300, 4);

SELECT * FROM sales;

SELECT * FROM sales
LIMIT 2;

SELECT SUM(price * quantity) AS total_sales_value
FROM sales;

SELECT
    product,
    SUM(quantity) AS total_quantity,
    AVG(price) AS average_unit_price
FROM sales
GROUP BY product;
