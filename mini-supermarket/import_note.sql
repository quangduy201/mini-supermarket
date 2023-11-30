create table import_note
(
    id            bigint auto_increment
        primary key,
    staff_id      bigint      null,
    received_date datetime(6) null,
    total         double      null,
    supplier_id   bigint      null,
    constraint FK_STAFF2
        foreign key (staff_id) references staff (id),
    constraint FK_SUPPLIER1
        foreign key (supplier_id) references supplier (id)
);

