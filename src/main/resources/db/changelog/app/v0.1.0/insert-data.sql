--liquibase formatted sql

--changeset Ivan Kazakov:2_1
INSERT INTO category (id, name)
VALUES ('1', 'PC'),
       ('2', 'LAPTOP'),
       ('3', 'MONITOR'),
       ('4', 'DRIVE');

--changeset Ivan Kazakov:2_2
INSERT INTO category_field (id, name, category_id)
VALUES ('1', 'form-factor', '1'),
       ('2', 'inch-size', '2'),
       ('3', 'diagonal', '3'),
       ('4', 'capacity', '4');
