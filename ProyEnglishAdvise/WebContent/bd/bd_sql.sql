create database proyectoPE;
use proyectoPE;

desc tb_empleado;
desc tb_departamento;
desc tb_cliente;
desc tb_producto;

/* INSERT INTO TB_DEPARTAMENTO*/
insert into tb_departamento (nombre) values 
('Ventas'),('Soporte');

/* INSERTS TB_CARGO*/
insert into tb_cargo (nombre) values ('Supervisor'),('Tecnico'),('Representante');

/* INSERT INTO TB_EMPLEADO*/
insert into tb_empleado (nombres,apellidos,dni,celular,departamento_id,cargo_id) values
('Tesheng','Hsieh','000067574','987654321',2,2);

/* INSERT INTO TB_PRODUCTO*/
insert into tb_producto (isbn,titulo,imagen,descripcion,precio) values 
('9682535896125','GET SMART 1','/images/GETSMART/GETSMART1.jpg','DESCRIPCION DEL LIBRO',85.00);


select * from tb_cliente;
select * from tb_cargo;
select * from tb_empleado;
select * from tb_departamento;


/*CREACION DE TABLAS MAESTRAS*/

create table tb_tipoProducto(
	idtipro char(1) primary key,
    desctipro varchar(255) not null
);
create table tb_producto (
	idpro varchar(8) primary key,
    idtipro char(1) not null,
    nompro varchar(255) not null,
    precio decimal(3,2) not null,
    unidad varchar(20) not null,
    estado boolean
);
create table cliente (
	idcliente varchar(8) primary key,
    nomcli varchar(50) not null,
    ape_pacli varchar(50) not null,
    ape_macli varchar(50) not null,
    direccion varchar(255) not null,
    
    estado boolean
);