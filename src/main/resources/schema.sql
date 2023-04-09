CREATE TABLE IF NOT EXISTS users
(
    id            SERIAL PRIMARY KEY,
    first_name    VARCHAR(100)       NOT NULL,
    last_name     VARCHAR(100)       NOT NULL,
    phone         VARCHAR(25) UNIQUE,
    email         VARCHAR(50) UNIQUE NOT NULL,
    hash_password VARCHAR,
    role          VARCHAR(50),
    status        VARCHAR(50)
);

create table IF NOT EXISTS address
(
    id           SERIAL PRIMARY KEY,

    county       VARCHAR(100),
    city         VARCHAR(50),
    district     VARCHAR(50),
    street       VARCHAR(100),
    house_number VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS advert
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  TEXT         NOT NULL,
    published    TIMESTAMP    NOT NULL,
    updated      TIMESTAMP,
    address_id   INT,
    user_id      INT,
    FOREIGN KEY (address_id) REFERENCES address (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS photo
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255),
    path      VARCHAR(255) NOT NULL,
    advert_id INT,
    FOREIGN KEY (advert_id) REFERENCES advert (id)
);
