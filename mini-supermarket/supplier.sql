create table supplier
(
    id      bigint auto_increment
        primary key,
    name    varchar(255) null,
    phone   varchar(255) null,
    address varchar(255) null,
    email   varchar(255) null,
    deleted bit          null
);

