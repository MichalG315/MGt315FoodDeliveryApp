CREATE TABLE menu_item
(
    menu_item_id        SERIAL          NOT NULL,
    menu_item_number    VARCHAR(64)     NOT NULL,
    item_name           VARCHAR(64)     NOT NULL,
    description         TEXT            NOT NULL,
    price               NUMERIC(5,2)    NOT NULL,
    category            VARCHAR(32)     NOT NULL,
    image_path          VARCHAR(64),
    restaurant_id       INT             NOT NULL,
    PRIMARY KEY(menu_item_id),
    UNIQUE(menu_item_number),
    CONSTRAINT fk_menu_item_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id)
);