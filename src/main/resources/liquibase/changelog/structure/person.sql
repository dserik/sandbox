-- liquibase formatted sql

-- changeset daryn:1
create table person
(
    id               SERIAL PRIMARY KEY,
    first_name       varchar(50),
    last_name        varchar(50),
    middle_name      varchar(50),
    birth_date       date
)