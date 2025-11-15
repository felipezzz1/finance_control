CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount NUMERIC(18,2) NOT NULL,
    type VARCHAR(20) NOT NULL,
    date DATE NOT NULL
);