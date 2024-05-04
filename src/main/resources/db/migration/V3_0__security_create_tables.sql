create table food_app_user
(
    user_id         SERIAL          NOT NULL,
    user_name       VARCHAR(32)     NOT NULL,
    email           VARCHAR(32)     NOT NULL,
    password        VARCHAR(128)    NOT NULL,
    active          BOOLEAN         NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (user_name),
    UNIQUE (email)
);

create table food_app_role
(
    role_id         SERIAL          NOT NULL,
    role            VARCHAR(20)     NOT NULL,
    PRIMARY KEY (role_id)
);

create table food_app_user_role
(
    user_id         INT             NOT NULL,
    role_id         INT             NOT NULL,
    PRIMARY KEY (user_id, role_id),
        CONSTRAINT fk_food_app_user_role_user
            FOREIGN KEY (user_id)
                REFERENCES food_app_user (user_id),
        CONSTRAINT fk_food_app_user_role_role
            FOREIGN KEY (role_id)
                REFERENCES food_app_role (role_id)
);