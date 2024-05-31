drop database if exists concesionarioDB;

create database if not exists concesionarioDB;

use concesionarioDB;


create table Sucursales(
	sucursalId int not null auto_increment,
    direccion varchar(50) not null,
    telefono varchar(50) not null,
    primary key PK_sucursalId(sucursalId)
);

create table Vendedores(
	vendedorId int not null auto_increment,
    nombreVendedor varchar(50) not null,
    apellidoVendedor varchar(50) not null,
    telefono varchar(50) not null,
    correo varchar(50) not null,
	sucursalId int not null,
    primary key PK_vendedorId(vendedorId),
    constraint FK_Vendedores_Sucursales foreign key Vendedores(sucursalId)
        references Sucursales(sucursalId)
);


create table Clientes(
	clienteId int not null auto_increment,
    nombreCliente varchar(50) not null,
    apellidoCliente varchar(50) not null,
    telefono varchar(50) not null,
    nit varchar(50) not null,
    direccion varchar(50) not null,
    primary key PK_clienteId(clienteId)
);

create table Carros(
	carroId int not null auto_increment,
    marca varchar(50) not null,
    modelo varchar(50) not null,
    precio decimal(10,2) not null,
    clienteId int not null,
    primary key PK_carroId(carroId),
	constraint FK_Carros_Clientes foreign key Carros(clienteId)
        references Clientes(clienteId)
);

create table Facturas(
    facturaId int not null auto_increment,
    fecha date not null,
    hora time not null,
	total decimal(10,2),
    clienteId int not null,
    vendedorId int not null,
    carroId int not null,
    primary key PK_facturaId(facturaId),
    constraint FK_Facturas_Clientes foreign key Facturas(clienteId)
        references Clientes(clienteId),
    constraint FK_Facturas_Vendedores foreign key Facturas(vendedorId)
        references Vendedores(vendedorId),
	constraint FK_Facturas_Carros foreign key Facturas(carroId)
        references Carros(carroId)
);
