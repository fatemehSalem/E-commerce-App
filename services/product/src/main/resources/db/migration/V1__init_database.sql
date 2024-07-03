CREATE TABLE IF NOT EXISTS category
(
    id          BIGINT NOT NULL
    PRIMARY KEY,
    description VARCHAR(255),
    name        VARCHAR(255)
    );

create table if not exists product
(
    id                 BIGINT          NOT NULL
    PRIMARY KEY,
    available_quantity DOUBLE precision not null,
    description        VARCHAR(255),
    name               VARCHAR(255),
    price              NUMERIC (38, 2),
    category_id        BIGINT
    constraint fk_category
    references category
    );

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;