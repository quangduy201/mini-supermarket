create table category
(
    id       bigint auto_increment
        primary key,
    name     varchar(255) null,
    quantity int          null,
    deleted  bit          null
);

