CREATE OR REPLACE VIEW credit_view AS
SELECT
    c.credit_id AS credit_id,
    c.credit_number AS credit_number,
    c.credit_name AS credit_name,
    cust.name AS name,
    cust.surname AS customer_surname,
    cust.pesel AS pesel,
    c.product_value AS product_value
FROM credit c
JOIN customer cust ON c.customer_id = cust.customer_id;