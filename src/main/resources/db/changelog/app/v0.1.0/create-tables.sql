--liquibase formatted sql

--changeset Ivan Kazakov:1_1
CREATE TABLE IF NOT EXISTS product
(
    id             UUID PRIMARY KEY,
    serial_number  VARCHAR(255)   NOT NULL,
    manufacturer   VARCHAR(255)   NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    stock_quantity INT            NOT NULL,
    category_id    BIGINT         NOT NULL
);

--changeset Ivan Kazakov:1_2
CREATE TABLE IF NOT EXISTS category
(
    id   BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

--changeset Ivan Kazakov:1_3
CREATE TABLE IF NOT EXISTS category_field
(
    id          BIGINT PRIMARY KEY,
    category_id BIGINT       NOT NULL,
    name        VARCHAR(255) NOT NULL
);

--changeset Ivan Kazakov:1_4
CREATE TABLE IF NOT EXISTS product_category_field
(
    id                   UUID PRIMARY KEY,
    product_id           UUID,
    category_field_id    BIGINT       NOT NULL,
    category_field_value VARCHAR(255) NOT NULL
);

--changeset Ivan Kazakov:1_5
ALTER TABLE product
    ADD CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES category (id);

--changeset Ivan Kazakov:1_6
ALTER TABLE category_field
    ADD CONSTRAINT fk_category_field_category
        FOREIGN KEY (category_id)
            REFERENCES category (id);

--changeset Ivan Kazakov:1_7
ALTER TABLE product_category_field
    ADD CONSTRAINT fk_product_category_field_product
        FOREIGN KEY (product_id)
            REFERENCES product (id);

--changeset Ivan Kazakov:1_8
ALTER TABLE product_category_field
    ADD CONSTRAINT fk_product_category_field_category_field
        FOREIGN KEY (category_field_id)
            REFERENCES category_field (id);

--changeset Ivan Kazakov:1_9
ALTER TABLE product
    ADD CONSTRAINT price_constraint
        CHECK (price > 0);

--changeset Ivan Kazakov:1_10
ALTER TABLE product
    ADD CONSTRAINT stock_quantity_constraint
        CHECK (stock_quantity > 0);