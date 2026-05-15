CREATE TABLE categories (
    id            BIGSERIAL     PRIMARY KEY,
    name          VARCHAR(100)  NOT NULL UNIQUE,
    created_at    TIMESTAMPTZ   NOT NULL DEFAULT NOW()
);