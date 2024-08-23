INSERT INTO product (id, serial_number, manufacturer, price, stock_quantity, category_id)
VALUES ('6e4f7a8b-9c1d-2e3f-4a5b-7d8e9f1a2b3c', 'SN12345', 'BrandX', 1500.00, 10,
        '1'),
       ('7a8b9c1d-2e3f-4a5b-6c7d-8e9f1a2b3c4d', 'SN67890', 'BrandY', 1200.00, 15,
        '2'),
       ('8c1d2e3f-4a5b-6c7d-8e9f-1a2b3c4d5e6f', 'SN24680', 'BrandZ', 400.00, 20,
        '3'),
       ('9d1e2f3a-4b5c-6d7e-8f9a-2b3c4d5e6f7a', 'SN13579', 'BrandA', 250.00, 30,
        '4');

INSERT INTO product_category_field (id, product_id, category_field_id, category_field_value)
VALUES ('a1b2c3d4-1234-5678-9abc-def012345678', '6e4f7a8b-9c1d-2e3f-4a5b-7d8e9f1a2b3c',
        '1', '16GB'),
       ('b2c3d4e5-2345-6789-abcd-ef0123456789', '6e4f7a8b-9c1d-2e3f-4a5b-7d8e9f1a2b3c',
        '2', 'Intel i7'),
       ('cd3e4f5d-3456-789a-bcde-f0123456789a', '7a8b9c1d-2e3f-4a5b-6c7d-8e9f1a2b3c4d',
        '3', '15.6"');

