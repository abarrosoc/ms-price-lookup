--BRANDS
INSERT INTO brands (id, name, created_date, updated_date) VALUES (1, 'ZARA', '2020-01-01 00:00:00', '2024-01-01 00:00:00');

--PRICES
INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2020-06-14T00:00:00.000Z', '2020-12-31T23:59:59.000Z', 1, '0003326989', 0, 35.50, 'EUR');

INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2020-06-14T15:00:00.000Z', '2020-06-14T18:30:00.000Z', 2, '0003326989', 1, 25.45, 'EUR');

INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2020-06-15T00:00:00.000Z', '2020-06-15T11:00:00.000Z', 3, '0003326989', 1, 30.50, 'EUR');

INSERT INTO prices (brand_id, start_date, end_date, price_list, product_id, priority, price, curr)
VALUES (1, '2020-06-15T16:00:00.000Z', '2020-12-31T23:59:59.000Z', 4, '0003326989', 1, 38.95, 'EUR');
