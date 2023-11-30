create table receipt
(
    id           bigint auto_increment
        primary key,
    staff_id     bigint      null,
    customer_id  bigint      null,
    invoice_date datetime(6) null,
    total        double      null,
    received     double      null,
    excess       double      null,
    constraint FK_CUSTOMER
        foreign key (customer_id) references customer (id),
    constraint FK_STAFF3
        foreign key (staff_id) references staff (id)
);

