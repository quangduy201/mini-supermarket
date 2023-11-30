create table decentralization
(
    role_id     bigint not null,
    module_id   bigint not null,
    function_id bigint not null,
    primary key (role_id, module_id, function_id),
    constraint FK_FUNCTION
        foreign key (function_id) references function (id),
    constraint FK_MODULE
        foreign key (module_id) references module (id),
    constraint FK_ROLE1
        foreign key (role_id) references role (id)
);

