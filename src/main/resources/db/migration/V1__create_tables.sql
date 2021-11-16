create table vehicle_feature
(
    id          serial primary key,
    name        VARCHAR(50),
    description TEXT
);

create table vehicle_type
(
    id          serial primary key,
    code        VARCHAR(5),
    name        VARCHAR(50),
    description TEXT
);

create table vehicle
(
    id                 serial primary key,
    number             VARCHAR(10),
    available          boolean,
    vehicle_type_id    integer,
    constraint vehicle_vehicle_type_fk foreign key (vehicle_type_id) references vehicle_type (id)
);

create table vehicle_to_vehicle_feature
(
    vehicle_id         integer,
    vehicle_feature_id integer,
    constraint vehicle_feature_vehicle_fk foreign key (vehicle_id) references vehicle (id),
    constraint vehicle_vehicle_feature_fk foreign key (vehicle_feature_id) references vehicle_feature (id)
)