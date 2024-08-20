create table car
(
    id           bigint primary key,
    state_number varchar(7)  not null,
    vin_code     varchar(17) not null,
    brand        varchar(64) not null,
    model        varchar(64) not null,
    constraint uk_car_state_number
        unique (state_number),
    constraint uk_car_vin_code
        unique (vin_code)
);


grant select, insert, update, delete on car to car_book_app;


create sequence seq_car start with 1000;

grant select, usage on seq_car to car_book_app;
