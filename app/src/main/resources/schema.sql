DROP TABLE IF EXISTS urls CASCADE;
DROP TABLE IF EXISTS url_checks;

CREATE TABLE urls (
    id SERIAL,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_url PRIMARY KEY (id)
);

CREATE TABLE url_checks (
    id SERIAL,
    url_id BIGINT REFERENCES urls(id) ON DELETE CASCADE,
    status_code INT,
    h1 VARCHAR(255),
    title VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_url_checks PRIMARY KEY (id)
);




