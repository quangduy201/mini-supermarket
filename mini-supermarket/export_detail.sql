create table export_detail
(
    export_note_id bigint not null,
    shipment_id    bigint not null,
    quantity       double null,
    total          double null,
    primary key (export_note_id, shipment_id),
    constraint FK_EXPORT_NOTE
        foreign key (export_note_id) references export_note (id),
    constraint FK_SHIPMENT
        foreign key (shipment_id) references shipment (id)
);

