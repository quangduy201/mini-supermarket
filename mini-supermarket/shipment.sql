create table shipment
(
    id             bigint auto_increment
        primary key,
    product_id     bigint       null,
    unit_price     double       null,
    quantity       double       null,
    remain         double       null,
    mfg            date         null,
    exp            date         null,
    sku            varchar(255) null,
    import_note_id bigint       null,
    constraint FK_IMPORT_NOTE
        foreign key (import_note_id) references import_note (id),
    constraint FK_PRODUCT4
        foreign key (product_id) references product (id)
);

