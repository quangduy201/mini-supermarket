create table discount
(
    id         bigint auto_increment
        primary key,
    percent    double null,
    start_date date   null,
    end_date   date   null,
    status     bit    null
);

