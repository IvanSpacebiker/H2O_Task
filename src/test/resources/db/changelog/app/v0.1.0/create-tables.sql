CREATE TABLE IF NOT EXISTS product
(
    id             UUID PRIMARY KEY,
    serial_number  VARCHAR(255)   NOT NULL,
    manufacturer   VARCHAR(255)   NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    stock_quantity INT            NOT NULL,
    category_id    BIGINT         NOT NULL
);

CREATE TABLE IF NOT EXISTS category
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS category_field
(
    id          BIGINT PRIMARY KEY,
    category_id BIGINT       NOT NULL,
    name        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_category_field
(
    id                   UUID PRIMARY KEY,
    product_id           UUID         NOT NULL,
    category_field_id    BIGINT         NOT NULL,
    category_field_value VARCHAR(255) NOT NULL
);

ALTER TABLE product
    ADD CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES category (id);

ALTER TABLE category_field
    ADD CONSTRAINT fk_category_field_category
        FOREIGN KEY (category_id)
            REFERENCES category (id);

ALTER TABLE product_category_field
    ADD CONSTRAINT fk_product_category_field_product
        FOREIGN KEY (product_id)
            REFERENCES product (id);

ALTER TABLE product_category_field
    ADD CONSTRAINT fk_product_category_field_category_field
        FOREIGN KEY (category_field_id)
            REFERENCES category_field (id);
