create table if not exists category
(
    id integer not null primary key,
    description varchar(255),
    name varchar(255)
);

create table if not exists product
(
    id integer not null primary key,
    description varchar(255),
    name varchar(255),
    available_quantity integer,
    price numeric(38, 2),
    category_id integer
            constraint fk1ladkfldfjdkd references category
);


CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 1;
