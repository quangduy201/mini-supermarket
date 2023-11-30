create table role
(
    id      bigint auto_increment
        primary key,
    name    varchar(255) null,
    deleted bit          null
);

