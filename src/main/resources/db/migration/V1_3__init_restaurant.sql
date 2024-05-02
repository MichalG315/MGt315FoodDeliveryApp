CREATE TABLE restaurant
(
    restaurant_id           SERIAL          NOT NULL,
    restaurant_name         VARCHAR(32)     NOT NULL,
    description             TEXT            NOT NULL,
    address_extended_id     INT             NOT NULL,
    PRIMARY KEY (restaurant_id),
    UNIQUE (restaurant_name),
    CONSTRAINT fk_restaurant_address_extended
        FOREIGN KEY (address_extended_id)
            REFERENCES address_extended (address_extended_id)
);