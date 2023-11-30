create table customer
(
    id             bigint auto_increment
        primary key,
    name           varchar(255) null,
    gender         bit          null,
    birthdate      date         null,
    phone          varchar(255) null,
    membership     bit          null,
    signed_up_date date         null,
    point          int          null,
    deleted        bit          null
);

