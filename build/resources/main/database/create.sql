CREATE TABLE client
(
    id           number(10)   NOT NULL,
    email        varchar(255) NOT NULL UNIQUE,
    password     varchar(255) NOT NULL,
    first_name   varchar(255) NOT NULL,
    last_name    varchar(255) NOT NULL,
    role_id      number(10)   NOT NULL,
    shop_id      number(10) UNIQUE,
    phone_number varchar(255) NOT NULL,
    avatar_id    number(10),
    PRIMARY KEY (id)
);

CREATE TABLE photo
(
    id        number(10)   NOT NULL,
    file_name varchar(255) NOT NULL,
    google_file_id      varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id             number(10)   NOT NULL,
    name           varchar(255) NOT NULL,
    type_id        number(10)   NOT NULL,
    alias          varchar(255),
    shop_id        number(10)   NOT NULL,
    stock_quantity number(10),
    price          double precision,
    discount_price double precision,
    photo_id       number(10),
    PRIMARY KEY (id)
);

CREATE TABLE product_type
(
    id   number(10)   NOT NULL,
    name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE purchase
(
    id            number(10) NOT NULL,
    purchase_date date       NOT NULL,
    status_id     number(10) NOT NULL,
    total_price   double precision,
    client_id     number(10) NOT NULL,
    product_id    number(10) NOT NULL,
    quantity      number(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE review_product
(
    id         number(10) NOT NULL,
    client_id  number(10) NOT NULL,
    product_id number(10) NOT NULL,
    message    varchar(255),
    rating     number(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE role
(
    id   number(10)   NOT NULL,
    name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE shop
(
    id           number(10)   NOT NULL,
    name         varchar(255) NOT NULL,
    alias        varchar(255),
    address      varchar(255),
    city         varchar(255),
    state        varchar(255),
    country      varchar(255) NOT NULL,
    zip_code     number(10),
    email        varchar(255) NOT NULL,
    phone_number varchar(255) NOT NULL,
    logo_id      number(10),
    banner_id    number(10),
    PRIMARY KEY (id)
);

CREATE TABLE status
(
    id   number(10)   NOT NULL,
    name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);