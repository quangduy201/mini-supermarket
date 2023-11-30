create table export_note
(
    id           bigint auto_increment
        primary key,
    staff_id     bigint       null,
    invoice_date datetime(6)  null,
    total        double       null,
    reason       varchar(255) null,
    constraint FK_STAFF1
        foreign key (staff_id) references staff (id)
);

