create table discount_detail
(
    discount_id bigint not null,
    product_id  bigint not null,
    status      bit    null,
    primary key (discount_id, product_id),
    constraint FK_DISCOUNT
        foreign key (discount_id) references discount (id),
    constraint FK_PRODUCT
        foreign key (product_id) references product (id)
);

