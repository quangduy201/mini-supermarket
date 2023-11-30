create table receipt_detail
(
    receipt_id bigint not null,
    product_id bigint not null,
    quantity   double null,
    total      double null,
    percent    double null,
    primary key (receipt_id, product_id),
    constraint FK_PRODUCT3
        foreign key (product_id) references product (id),
    constraint FK_RECEIPT
        foreign key (receipt_id) references receipt (id)
);

