create table account
(
    id             bigint auto_increment
        primary key,
    username       varchar(255) null,
    password       varchar(255) null,
    role_id        bigint       null,
    staff_id       bigint       null,
    last_signed_in datetime(6)  null,
    deleted        bit          null,
    constraint FK_ROLE
        foreign key (role_id) references role (id),
    constraint FK_STAFF
        foreign key (staff_id) references staff (id)
);

