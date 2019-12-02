create database radial;
use radial;

create table telefono(
idtelefono int(11) not null primary key auto_increment,
telefono varchar(12) unique not null,
estado int(1) default 1
)ENGINE InnoDB;

create table productora(
idproductora int(11) not null primary key auto_increment,
numbre varchar(100) not null unique,
rfc varchar(20) not null,
estado int(1) not null default 1
)ENGINE InnoDB;

create table telefonosproductora(
idproductora int(11) not null,
idtelefono int(11) not null,
constraint foreign key (idproductora) references productora(idproductora) on delete cascade on update cascade,
constraint foreign key (idtelefono) references telefono(idtelefono) on delete cascade on update cascade
)ENGINE InnoDB;

create table tipousuario(
idtipo int(11) not null primary key auto_increment,
tipo varchar(25) not null unique,
estado int(1) not null default 1
)ENGINE InnoDB;

create table usuarios(
idusuario int(11) not null primary key auto_increment,
idtipo int(11) not null,
usuario varchar(20) unique not null,
clave varchar(100) not null,
estado int(11) not null default 1,
constraint foreign key (idtipo) references tipousuario(idtipo) on delete cascade on update cascade
)ENGINE InnoDB;
select * from usuarios;
select idusuario, idtipo, case when usuario = 'roberto' then usuario else idusuario END as nuevo,
usuario, estado
from usuarios;


create table cargo(
idcargo int(11) not null primary key auto_increment,
cargo varchar(50) unique not null,
estado int(1) not null
)ENGINE InnoDB;

create table personal(
idpersonal int(11) not null primary key auto_increment,
idproductora int(11) not null,
idcargo int(11) not null,
nombre varchar(60) not null,
apellido varchar(60) not null,
dui varchar(15) unique not null,
estado int(1) not null default 1,
constraint foreign key (idcargo) references cargo(idcargo) on delete cascade on update cascade,
constraint foreign key (idproductora) references productora(idproductora) on delete cascade on update cascade
)ENGINE InnoDB;

SELECT per.idpersonal, per.idproductora, per.idcargo, per.nombre,
                per.apellido, per.dui, pro.numbre, c.cargo FROM personal as per 
               INNER JOIN productora as pro ON pro.idproductora = per.idproductora
            INNER JOIN cargo as c ON c.idcargo = per.idcargo WHERE per.estado = 1;

create table frecuencia(
idfrecuencia int(11) not null primary key auto_increment,
frecuencia decimal(10,2) not null unique,
tipo varchar(2) not null,
estado int(1) not null default 1
)ENGINE InnoDB;

create table radio(
idradio int(11) not null primary key auto_increment,
idproductora int(11) not null,
idfrecuencia int(11) not null,
nombre varchar(70) not null unique,
estado int(1) not null default 1,
constraint foreign key (idproductora) references productora(idproductora) on delete cascade on update cascade,
constraint foreign key (idfrecuencia) references frecuencia(idfrecuencia) on delete cascade on update cascade
)ENGINE InnoDB;

SELECT rad.idproductora, rad.idfrecuencia, rad.nombre, 
                 pro.numbre, fre.frecuencia FROM radio as rad 
                INNER JOIN productora as pro ON pro.idproductora = rad.idproductora 
                INNER JOIN frecuencia as fre ON fre.idfrecuencia = rad.idfrecuencia 
              WHERE rad.estado = 1;

create table genero(
idgenero int(11) not null primary key auto_increment,
genero varchar(60) not null unique,
estado int(1) not null default 1
)ENGINE InnoDB;

create table programas(
idprograma int(11) not null primary key auto_increment,
idgenero int(11) not null,
nombre varchar(60) not null unique,
descripcion varchar(100) not null,
estado int(1) not null default 1,
constraint foreign key (idgenero) references genero(idgenero) on delete cascade on update cascade
)ENGINE InnoDB;
  
create table emision(
idemision int(11) not null primary key auto_increment,
idprograma int(11) not null,
fecha date not null,
horainicio time not null,
duracion time not null,
repeticion varchar(2) not null,
estado int(1) not null,
constraint foreign key (idprograma) references programas(idprograma) on delete cascade on update cascade
)ENGINE InnoDB;

create table resumen(
idresumen int(11) not null primary key auto_increment,
nota varchar(150) not null,
estado int(1) not null default 1
)ENGINE InnoDB;

create table resumenprograma(
idresumen int(11) not null,
idprograma int(11) not null,
constraint foreign key (idresumen) references resumen(idresumen) on delete cascade on update cascade,
constraint foreign key (idprograma) references programas(idprograma) on delete cascade on update cascade
)ENGINE InnoDB;

create table encuesta(
idencuesta int(11) not null primary key auto_increment,
idemision int(11) not null,
idprograma int(11) not null,
total int(11) not null,
aprobacion int(11) not null,
rechazo int(11) not null,
indiferencia int(11) not null,
estado int(1) not null default 1,
constraint foreign key (idemision) references emision(idemision) on delete cascade on update cascade,
constraint foreign key (idprograma) references programas(idprograma) on delete cascade on update cascade
)ENGINE InnoDB;

create table programaradial(
idradio int(11) not null,
idprograma int(11),
idresumen int(11),
constraint foreign key (idradio) references radio(idradio) on delete cascade on update cascade,
constraint foreign key (idprograma) references programas(idprograma) on delete cascade on update cascade,
constraint foreign key (idresumen) references resumen(idresumen) on delete cascade on update cascade
)ENGINE InnoDB;

alter table personal 
add foreign key (idpersonal) references usuarios(idusuario) on delete cascade on update cascade;
