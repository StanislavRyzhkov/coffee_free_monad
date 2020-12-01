CREATE DATABASE coffee;
CREATE USER clyde WITH PASSWORD '12345';
ALTER ROLE clyde SET client_encoding TO 'utf8';
ALTER ROLE clyde SET default_transaction_isolation TO 'read committed';
ALTER ROLE clyde SET timezone TO 'UTC';
GRANT ALL PRIVILEGES ON DATABASE coffee TO clyde;

\connect coffee;

create table if not exists countries(
    id                  bigserial not null constraint countries__pkey primary key ,
    name                text not null
);
alter table countries owner to clyde;


create table if not exists coffees(
    id                  bigserial not null constraint coffees__pkey primary key ,
    country_id          bigint not null ,
    name                text not null ,
    price               numeric(10,2) not null ,
    is_premium          boolean not null 
);
alter table coffees owner to clyde;

insert into countries (id, "name") values (2, 'USA');

INSERT INTO coffees (country_id, "name", price, is_premium) 
VALUES (1, 'McCoffee', 10.00, true),
       (2, 'Americano', 7.00, false);
