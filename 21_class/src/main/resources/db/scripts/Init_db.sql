create database if not exists transportation_company;

# SET FOREIGN_KEY_CHECKS=0;
drop table if exists cargo;
drop table if exists carrier;
drop table if exists transportation;
# SET FOREIGN_KEY_CHECKS=1;

create table if not exists cargo (
  id int(8) not null auto_increment primary key,
  name          varchar(255) not null ,
  weight  int(16) not null,
  idTransportations int(8),
  cargoType enum('FOOD','CLOTHERS') not null ,
  size varchar(255),
  material varchar(255),
  expirationLocalDateTime DATETIME,
  storeTemperature int(8)
);

create table if not exists carrier (
  id int(8) not null auto_increment primary key,
  name          varchar(255) not null ,
  address          varchar(255) ,
  carrierType enum('SHIP','PLANE','CAR','TRAIN') not null ,
  IdTransportations int(8)
);

create table if not exists transportation (
  id int(8)       not null auto_increment primary key,
  idCargo int(8),
  idCarrier int(8),
  description    varchar(255) ,
  billTo varchar(255),
  transportationBeginLocalDateTime DATETIME
);

alter table transportation
  add constraint transportation_cargo_fk
foreign key (idCargo) references cargo (id);

alter table transportation
  add constraint transportation_carrier_fk
foreign key (idCarrier) references carrier (id);

