create table promotion_gift
(
    promotion_id bigint not null,
    product_id   bigint not null,
    quantity     double null,
    primary key (promotion_id, product_id),
    constraint FK_PRODUCT1
        foreign key (product_id) references product (id),
    constraint FK_PROMOTION
        foreign key (promotion_id) references promotion (id)
);

