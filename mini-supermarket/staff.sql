create table staff
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) null,
    gender     bit          null,
    birthdate  date         null,
    phone      varchar(255) null,
    address    varchar(255) null,
    email      varchar(255) null,
    entry_date date         null,
    deleted    bit          null
);

