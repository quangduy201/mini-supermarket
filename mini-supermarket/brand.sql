create table brand
(
    id          bigint auto_increment
        primary key,
    name        varchar(255) null,
    supplier_id bigint       null,
    deleted     bit          null,
    constraint FK_SUPPLIER
        foreign key (supplier_id) references supplier (id)
);

