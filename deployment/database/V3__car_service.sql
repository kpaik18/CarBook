create table car_service_detail
(
    id        bigint primary key,
    name      varchar(255) not null,
    create_ts timestamp    not null,
    price     numeric      not null,
    car_id    bigint       not null,
    constraint fk_car_service_car
        foreign key (car_id) references car (id)
);

grant select, insert, update, delete on car_service_detail to car_book_app;

create sequence seq_car_service_detail start with 1000;

grant select, usage on seq_car_service_detail to car_book_app;


