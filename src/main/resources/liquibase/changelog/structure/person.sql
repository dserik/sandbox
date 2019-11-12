-- liquibase formatted sql

-- changeset daryn:1
create table person
(
    id          SERIAL PRIMARY KEY,
    first_name  varchar(50),
    last_name   varchar(50),
    middle_name varchar(50),
    birth_date  date
);

create table clothes
(
    id        serial primary key,
    person_id bigint references person (id)
);

create table pants
(
    id   serial primary key,
    size integer
);

create table shirt
(
    id    serial primary key,
    color varchar(12)
);
