-- we need user with login in postgresql, name=car_book_app


create table sec_user
(
    id         bigint primary key,
    first_name varchar(255),
    last_name  varchar(255),
    email      varchar(255),
    password   varchar(255)
);

grant select, insert, update, delete on sec_user to car_book_app;

create sequence seq_sec_user start with 1000;

grant select, update on seq_sec_user to car_book_app;



create table permission
(
    id   bigint primary key,
    code varchar(255) not null,
    name varchar(255) not null
);

create sequence seq_permission start with 1000;

grant select, insert, update, delete on permission to car_book_app;
grant select, update on seq_permission to car_book_app;



create table role
(
    id   bigint primary key,
    code varchar(255) not null,
    name varchar(255) not null
);

create sequence seq_role start with 1000;

grant select, insert, update, delete on role to car_book_app;
grant select, update on seq_role to car_book_app;


create table role_permission
(
    role_id       bigint not null,
    permission_id bigint not null,
    constraint fk_role_permission_role
        foreign key (role_id)
            references role (id),
    constraint fk_role_permission_permission
        foreign key (permission_id)
            references permission (id),
    constraint uk_role_permission
        unique (role_id, permission_id)
);

grant select, insert, update, delete on role_permission to car_book_app;

insert into permission (id, code, name)
values (1, 'adm_car_read', 'მანქანის დათვალიერება'),
       (2, 'adm_car_update', 'მანქანის რედაქტირება'),
       (3, 'adm_car_delete', 'მანქანის წაშლა'),
       (4, 'adm_car_create', 'მანქანის შექმნა');


insert into role (id, code, name)
values (1, 'ADMIN', 'ადმინისტრატორი');

insert into role_permission (role_id, permission_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4);


create table user_role
(
    user_id bigint not null,
    role_id bigint not null,
    constraint fk_user_role_user
        foreign key (user_id)
            references sec_user (id),
    constraint fk_user_role_role
        foreign key (role_id)
            references role (id),
    constraint uk_user_role
        unique (user_id, role_id)
);

grant select, insert, update, delete on user_role to car_book_app;
