CREATE TABLE product
(
    id          bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    description varchar(255)          NOT NULL,
    price       numeric(19, 2)        NOT NULL
);

CREATE TABLE animal_category
(
    id   bigserial PRIMARY KEY NOT NULL,
    name varchar(255),
    constraint animal_category_uq unique (name)
);

CREATE TABLE product_gallery
(
    product_id bigint NOT NULL,
    url        varchar(2083),
    constraint product_fk foreign key (product_id) references product (id),
    constraint product_gallery_uq unique (product_id, url)
);

CREATE TABLE product_animal_category
(
    product_id         bigint NOT NULL,
    animal_category_id bigint NOT NULL,
    constraint product_fk foreign key (product_id) references product (id),
    constraint animal_category_fk foreign key (animal_category_id) references animal_category (id),
    constraint product_animal_category_pk primary key (product_id, animal_category_id)
);

CREATE TABLE eshop_user
(
    id       bigserial PRIMARY KEY NOT NULL,
    username varchar(255)          NOT NULL,
    password varchar(255)          NOT NULL,
    email    varchar(255)          NOT NULL,
    CONSTRAINT eshop_user_name_uq UNIQUE (username)
);

CREATE TABLE eshop_order
(
    id            bigserial PRIMARY KEY NOT NULL,
    total_price   numeric(19, 2),
    time          date,
    eshop_user_id bigint                NOT NULL,
    constraint eshop_user_id_fk foreign key (eshop_user_id) references eshop_user (id)
);

CREATE TABLE order_product
(
    product_id bigint         NOT NULL,
    order_id   bigint         NOT NULL,
    price      numeric(19, 2) NOT NULL,
    count      integer        NOT NULL,
    constraint order_fk foreign key (order_id) references eshop_order (id) ON DELETE CASCADE,
    constraint product_fk foreign key (product_id) references product (id)
);