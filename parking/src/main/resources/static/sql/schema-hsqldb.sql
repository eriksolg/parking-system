drop table if exists customer CASCADE;
drop table if exists customer_registrations CASCADE;
drop table if exists floor CASCADE;
drop table if exists floor_slots CASCADE;
drop table if exists registration CASCADE;
drop table if exists slot CASCADE;
drop table if exists slot_registrations CASCADE;
drop table if exists vehicle CASCADE;
drop table if exists vehicle_registrations CASCADE;
drop sequence hibernate_sequence if exists;

create sequence hibernate_sequence start with 100 increment by 1;

create table customer (
    customer_id bigint not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    id_number varchar(255) not null,
    primary key (customer_id)
);

create table floor (
    floor_id bigint not null,
    floor_number varchar(255) not null,
    height integer not null,
    weight_cap integer not null,
    primary key (floor_id)
);

create table registration (
    registration_id bigint not null,
    customer_id bigint not null,
    slot_id bigint not null,
    vehicle_id bigint not null,
    checkin_time timestamp not null,
    checkout_time timestamp,
    notes varchar(2560),
    primary key (registration_id)
);

create table slot (
    slot_id bigint not null,
    name varchar(255) not null,
    floor_id bigint not null,
    primary key (slot_id)
);

create table vehicle (
    vehicle_id bigint not null,
    height integer not null,
    reg_number varchar(255) not null,
    weight integer not null,
    primary key (vehicle_id)
);

alter table customer
    add constraint UQ_customer_id_number
    unique (id_number)

alter table registration
    add constraint FK_registration_customer
    foreign key (customer_id)
    references customer;

alter table registration
    add constraint FK_registration_slot
    foreign key (slot_id)
    references slot;

alter table registration
    add constraint FK_registration_vehicle
    foreign key (vehicle_id)
    references vehicle;

alter table slot
    add constraint FK_slot_floor
    foreign key (floor_id)
    references floor;

alter table vehicle
    add constraint UQ_vehicle_reg_number
    unique (reg_number)
