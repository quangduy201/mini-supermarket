create table promotion
(
    id         bigint auto_increment
        primary key,
    name       varchar(255) null,
    start_date date         null,
    end_date   date         null,
    status     bit          null
);

