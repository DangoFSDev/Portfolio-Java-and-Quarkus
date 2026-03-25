CREATE IF NOT EXISTS schema smartflow;

CREATE SEQUENCE IF NOT EXISTS smartflow.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS smartflow.users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    status NUMERIC(1) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER SEQUENCE smartflow.users_id_seq OWNED BY smartflow.users.id;

CREATE UNIQUE index idx_users_id on smartflow.users (id);
CREATE UNIQUE index idx_users_username on smartflow.users (username);