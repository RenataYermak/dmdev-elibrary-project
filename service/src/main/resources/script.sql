create table books
(
    book_id      bigserial
        primary key,
    title        varchar(50) not null,
    author_id    integer     not null,
    publish_year integer,
    category_id  integer   not null,
    description  text,
    number       integer     not null,
    picture      varchar(128)
);

create table users
(
    user_id   bigserial
        primary key,
    login     varchar(25) not null
        unique,
    firstname varchar(25) not null,
    lastname  varchar(25) not null,
    email     varchar(50) not null
        unique,
    password  varchar(49) not null,
    role      varchar(10) not null
);

create table book_authors
(
    author_id   bigserial
        primary key,
    author_name varchar(50) not null unique
);

create table book_categories
(
    category_id   bigserial
        primary key,
    category_name varchar(50) not null unique
);

create table orders
(
    order_id      bigserial
        primary key,
    book_id       integer     not null,
    user_id       integer     not null,
    status        varchar(20) not null,
    type          varchar(20) not null,
    ordered_date  date        not null,
    reserved_date date,
    returned_date date,
    rejected_date date
);
