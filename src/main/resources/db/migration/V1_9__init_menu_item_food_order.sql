CREATE TABLE menu_item_food_order
(
    menu_item_food_order_id      SERIAL   NOT NULL,
    quantity                INT      NOT NULL,
    menu_item_id            INT      NOT NULL,
    food_order_id           INT      NOT NULL,
    PRIMARY KEY (menu_item_food_order_id),
    CONSTRAINT fk_menu_item_food_order_menu_item
        FOREIGN KEY (menu_item_id)
            REFERENCES menu_item (menu_item_id),
    CONSTRAINT fk_menu_item_food_order_food_order
        FOREIGN KEY (food_order_id)
            REFERENCES food_order (food_order_id)
);