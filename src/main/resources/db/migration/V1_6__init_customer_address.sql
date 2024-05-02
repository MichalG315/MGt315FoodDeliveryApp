CREATE TABLE customer_address
(
    customer_address_id     SERIAL          NOT NULL,
    address_extended_id     INT             NOT NULL,
    customer_id             INT             NOT NULL,
    PRIMARY KEY (customer_address_id),
    CONSTRAINT fk_customer_address_address_extended
            FOREIGN KEY (address_extended_id)
                REFERENCES address_extended (address_extended_id),
        CONSTRAINT fk_customer_address_customer
            FOREIGN KEY (customer_id)
                REFERENCES customer (customer_id)
);