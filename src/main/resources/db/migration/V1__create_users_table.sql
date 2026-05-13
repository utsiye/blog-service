CREATE TYPE user_role AS ENUM ('MODERATOR', 'USER');

CREATE TABLE users (
    id            BIGSERIAL     PRIMARY KEY,
    name          VARCHAR(100)  NOT NULL UNIQUE,
    role          VARCHAR(50)   NOT NULL,
    password_hash VARCHAR(255)  NOT NULL,
    created_at    TIMESTAMPTZ   NOT NULL DEFAULT NOW()
);