insert into customer (customer_id, first_name, last_name, id_number)
    values (1, 'Marko', 'Tihane', '39207240239');
insert into customer (customer_id, first_name, last_name, id_number)
    values (2, 'Liisi', 'Laul', '49611240339');
insert into customer (customer_id, first_name, last_name, id_number)
    values (3, 'Toomas', 'Pääsuke', '38207240239');


insert into floor (floor_id, floor_number, height, weight_cap)
    values (1, '1', 336, 160000);
insert into floor (floor_id, floor_number, height, weight_cap)
    values (2, '3', 422, 149000);

insert into slot (slot_id, name, floor_id)
    values (1, '11', 1);
insert into slot (slot_id, name, floor_id)
    values (2, '12', 1);
insert into slot (slot_id, name, floor_id)
    values (3, '13', 1);
insert into slot (slot_id, name, floor_id)
    values (4, '31', 2);
insert into slot (slot_id, name, floor_id)
    values (5, '32', 2);

insert into vehicle (vehicle_id, reg_number, weight, height)
    values (1, '434dfk', 2140, 178);
insert into vehicle (vehicle_id, reg_number, weight, height)
    values (2, '492dfk', 4096, 223);
insert into vehicle (vehicle_id, reg_number, weight, height)
    values (3, '238384dsa', 3440, 180);
insert into vehicle (vehicle_id, reg_number, weight, height)
    values (4, '302kdo', 2856, 170);
insert into vehicle (vehicle_id, reg_number, weight, height)
    values (5, '888daj', 3356, 425);

insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (1, 1, 1, 1, TIMESTAMP '2020-01-22 16:45:03', TIMESTAMP '2020-01-22 17:56:00', 3.33, '');
insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (2, 1, 2, 2, TIMESTAMP '2020-01-23 16:43:00', TIMESTAMP '2020-01-22 17:59:00', 3.33, '');
insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (3, 2, 3, 4, TIMESTAMP '2020-01-23 17:00:03', TIMESTAMP '2020-01-23 18:00:03', 3.33, '');
insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (4, 1, 3, 3, TIMESTAMP '2020-01-29 09:45:03', TIMESTAMP '2020-01-29 17:01:00', 3.33, '');
insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (5, 1, 1, 2, TIMESTAMP '2020-02-04 16:45:03', TIMESTAMP '2020-02-04 17:52:00', 3.33, '');
insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (6, 2, 1, 1, TIMESTAMP '2021-03-07 12:40:03', null, 3.33, '');
insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (7, 1, 4, 5, TIMESTAMP '2021-03-07 16:45:03', null, 3.33, '');
insert into registration (registration_id, customer_id, vehicle_id, slot_id, checkin_time, checkout_time, price, notes)
    values (8, 3, 2, 2, TIMESTAMP '2021-03-09 16:33:09', null, 3.33, '');