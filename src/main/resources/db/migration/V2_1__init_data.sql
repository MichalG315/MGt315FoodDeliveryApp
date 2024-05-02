INSERT INTO address (country, city, postal_code, street_name)
VALUES
('Poland', 'Warszawa', '01-949', 'Sokratesa'),
('Poland', 'Warszawa', '01-949', 'Przy Agorze'),
('Poland', 'Warszawa', '01-949', 'Antyczna'),
('Poland', 'Gdańsk', '80-180', 'Zeusa'),
('Poland', 'Gdańsk', '80-180', 'Aresa');

INSERT INTO address_extended (street_number, address_id)
VALUES
('8/4', 1),
('10A/2', 2),
('25C/1', 3),
('1/12', 4),
('13/2N', 5);

INSERT INTO restaurant (restaurant_name, description, address_extended_id)
VALUES
('Chicken', 'We like chicken', 1),
('VegeWorld', 'We do not like chicken', 2),
('SoupHarbor', 'Soup is our world!', 3),
('Coffee and sweets', 'Best coffee in the city', 4),
('Homemade dinner', 'Best dinner in the country', 5);

INSERT INTO restaurant_delivery_address (address_id, restaurant_id)
VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2), (2, 3),
(3, 1), (3, 2), (3, 3),
(4, 4), (4, 5),
(5, 4), (5, 5);

--INSERT INTO menu_item (menu_item_number, item_name, description, price, category, restaurant_id)
--VALUES
--(,'Chicken', 'Breaded chicken', '10', 'Meat', 1),
--(,'Meat balls', 'Meat balls with cheese', '7', 'Meat', 1),
--(,'lettuce', 'lettuce', '12', 'Vege', 2),
--(,'tofu', 'sometimes tastes like chicken', '13', 'Vege', 2),
--(,'Vegetarian burrito', 'Veggie burrito bowl', '15', 'Vege', 2);