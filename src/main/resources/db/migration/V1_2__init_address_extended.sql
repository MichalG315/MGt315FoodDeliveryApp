CREATE TABLE address_extended
(
    address_extended_id     SERIAL          NOT NULL,
    street_number           VARCHAR(64)     NOT NULL,
    address_id              INT             NOT NULL,
    PRIMARY KEY(address_extended_id),
        CONSTRAINT fk_address_extended_address
            FOREIGN KEY (address_id)
                REFERENCES address (address_id)
);