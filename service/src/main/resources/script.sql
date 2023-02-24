CREATE TABLE public.category
(
    id   SERIAL
        PRIMARY KEY,
    name VARCHAR(50) NOT NULL
        UNIQUE
);

ALTER TABLE public.category
    OWNER TO postgres;

CREATE TABLE public.author
(
    id   BIGSERIAL
        PRIMARY KEY,
    name VARCHAR(50) NOT NULL
        UNIQUE
);

ALTER TABLE public.author
    OWNER TO postgres;

CREATE TABLE public.book
(
    id           BIGSERIAL
        PRIMARY KEY,
    title        VARCHAR(50) NOT NULL,
    author_id    BIGINT      NOT NULL
        REFERENCES public.author,
    publish_year INTEGER,
    category_id  INTEGER     NOT NULL
        REFERENCES public.category,
    description  TEXT,
    number       INTEGER     NOT NULL,
    picture      VARCHAR(128)
);

ALTER TABLE public.book
    OWNER TO postgres;

CREATE TABLE public.users
(
    id        BIGSERIAL
        PRIMARY KEY,
    login     VARCHAR(25) NOT NULL
        UNIQUE,
    firstname VARCHAR(25) NOT NULL,
    lastname  VARCHAR(25) NOT NULL,
    email     VARCHAR(50) NOT NULL
        UNIQUE,
    password  VARCHAR(49) NOT NULL,
    role      VARCHAR(10) NOT NULL
);

ALTER TABLE public.users
    OWNER TO postgres;

CREATE TABLE public.orders
(
    id            BIGSERIAL
        PRIMARY KEY,
    book_id       BIGINT      NOT NULL
        REFERENCES public.book,
    user_id       BIGINT      NOT NULL
        REFERENCES public.users,
    status        VARCHAR(20) NOT NULL,
    type          VARCHAR(20) NOT NULL,
    ordered_date  TIMESTAMP   NOT NULL,
    reserved_date TIMESTAMP,
    returned_date TIMESTAMP,
    rejected_date TIMESTAMP
);

ALTER TABLE public.orders
    OWNER TO postgres;

