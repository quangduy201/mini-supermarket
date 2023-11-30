create table promotion_item
(
    promotion_id bigint not null,
    product_id   bigint not null,
    quantity     double null,
    primary key (promotion_id, product_id),
    constraint FK_PRODUCT2
        foreign key (product_id) references product (id),
    constraint FK_PROMOTION1
        foreign key (promotion_id) references promotion (id)
);

