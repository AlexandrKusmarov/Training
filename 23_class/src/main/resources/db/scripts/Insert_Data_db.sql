insert into cargo (id, name, weight, cargoType, size, material)
values (1, 'Shirt', 50, 'CLOTHERS', 33, 'Wool'),
       (2, 'Cap', 15, 'CLOTHERS', 10, 'Wool');

insert into cargo (id, name, weight, cargoType, expirationLocalDateTime, storeTemperature)
values (3, 'Apple', 77, 'FOOD', '23.11.2020', 8),
       (4, 'Banana', 50, 'FOOD', '22.05.2020', 8);

insert into carrier (id, name, address, carrierType)
values (1, 'Petrovich', 'Spb, Nevskiy pr 77', 'CAR'),
       (2, 'Novaposhta', 'Kyiv pr Slavu 44', 'PLANE');

insert into transportation (id, idCargo, idCarrier, description, billTo, transportationBeginLocalDateTime)
values (9, 1, 1, 'Some cloth', 'Pupkin', '01.01.2020'),
       (19, 2, 1, 'Some cloth', 'Ivanov', '01.02.2020'),
       (10, 3, 2, 'Some food', 'Sidorov', '06.01.2020'),
       (100, 4, 2, 'Some food', 'Petrovna V', '11.02.2020');