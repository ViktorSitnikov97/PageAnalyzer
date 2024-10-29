DROP TABLE IF EXISTS urls;
DROP TABLE IF EXISTS url_checks;

CREATE TABLE urls (
    id SERIAL,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_url PRIMARY KEY (id)
);

CREATE TABLE url_checks (
    id SERIAL,
    url_id BIGINT NOT NULL,
    status_code INTEGER NOT NULL,
    h1 VARCHAR(255),
    title VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_url_checks PRIMARY KEY (id)
);

CREATE INDEX ix_url_check_url_id ON url_checks (url_id);
ALTER TABLE url_checks ADD CONSTRAINT fk_url_checks_url_id FOREIGN KEY (url_id) REFERENCES urls (id) ON DELETE RESTRICT ON UPDATE RESTRICT;




