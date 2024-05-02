CREATE TABLE restaurant_delivery_address
(
    restaurant_delivery_address_id    SERIAL          NOT NULL,
    address_id                          INT             NOT NULL,
    restaurant_id                       INT             NOT NULL,
    PRIMARY KEY (restaurant_delivery_address_id),
        CONSTRAINT fk_restaurant_delivery_address_address
            FOREIGN KEY (address_id)
                REFERENCES address (address_id),
        CONSTRAINT fk_restaurant_delivery_address_restaurant
            FOREIGN KEY (restaurant_id)
                REFERENCES restaurant (restaurant_id)
);