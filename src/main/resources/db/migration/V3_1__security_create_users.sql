ALTER TABLE customer
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES food_app_user (user_id);

ALTER TABLE restaurant
ADD COLUMN user_id INT,
ADD FOREIGN KEY (user_id) REFERENCES food_app_user (user_id);

insert into food_app_user (user_name, email, password, active) values ('customer_testowy', 'customer_testowy@gmail.com', '$2a$12$gpNbC.KZvJ9ZMkYKlNUZ..ANMphSZ3JBoaUp.D8l5LvpiRBoTBm0O', true);

insert into food_app_user (user_name, email, password, active) values ('restaurant_testowy', 'restaurant_testowy@gmail.com', '$2a$12$gpNbC.KZvJ9ZMkYKlNUZ..ANMphSZ3JBoaUp.D8l5LvpiRBoTBm0O', true);


UPDATE customer SET user_id = 1 WHERE email = 'customer_testowy@gmail.com';

UPDATE restaurant SET user_id = 2 WHERE restaurant_name = 'Chicken';

insert into food_app_role (role_id, role) values (1, 'CUSTOMER'), (2, 'RESTAURANT'), (3, 'ADMIN');

insert into food_app_user_role (user_id, role_id) values (1, 1);
insert into food_app_user_role (user_id, role_id) values (2, 2);

ALTER TABLE customer
ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE restaurant
ALTER COLUMN user_id SET NOT NULL;