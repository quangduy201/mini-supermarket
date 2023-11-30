create table product
(
    id          bigint auto_increment
        primary key,
    name        varchar(255) null,
    brand_id    bigint       null,
    category_id bigint       null,
    unit        varchar(255) null,
    cost        double       null,
    quantity    double       null,
    image       varchar(255) null,
    barcode     varchar(255) null,
    deleted     bit          null,
    constraint FK_BRAND
        foreign key (brand_id) references brand (id),
    constraint FK_CATEGORY
        foreign key (category_id) references category (id)
);

