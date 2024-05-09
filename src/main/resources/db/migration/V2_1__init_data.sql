INSERT INTO address (country, city, postal_code, street_name)
VALUES
('Poland', 'Warszawa', '01-949', 'Sokratesa'),
('Poland', 'Warszawa', '01-949', 'Przy Agorze'),
('Poland', 'Warszawa', '01-949', 'Antyczna'),
('Poland', 'Sopot', '80-180', 'Zeusa'),
('Poland', 'Sopot', '80-180', 'Aresa');

INSERT INTO address_extended (street_number, address_id)
VALUES
('8/4', 1),
('10A/2', 2),
('25C/1', 3),
('1/12', 4),
('13/2N', 5);

INSERT INTO restaurant (restaurant_name, description, address_extended_id)
VALUES
('Pizza center', 'Welcome', 1),
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

INSERT INTO customer (name, surname, email, phone)
VALUES
('customer', 'testowy', 'customer_testowy@gmail.com', '+48 671 239 001');