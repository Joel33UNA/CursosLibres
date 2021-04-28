create database cursoslibres;
use cursoslibres;

create table usuarios(
	id varchar(45) not null,
    clave varchar(45),
    rol varchar(45),
    nombre varchar(45),
    correo varchar(45),
    telefono int,
    primary key (id)
  );

create table administradores(
	id varchar(45) not null,
    primary key (id),
    foreign key (id) references usuarios(id)
);

create table profesores(
	id varchar(45) not null,
    primary key (id),
    foreign key (id) references usuarios(id)
);

create table estudiantes(
    id varchar(45) not null,
    primary key (id),
    foreign key (id) references usuarios(id)
);

create table cursos(
    id int not null AUTO_INCREMENT,
    nombre varchar(45),
    tematica varchar(45),
    estatus varchar(45),
    precio int,
    primary key (id)
);

create table grupos(
    id int not null AUTO_INCREMENT,
    horario varchar(45),
    curso int,
    profesor varchar(45) not null,
    primary key (id),
    foreign key (curso) references cursos(id),
    foreign key (profesor) references profesores(id)
);

create table gruposestudiantes(
    id_estudiante varchar(45) not null,
    id_grupo int not null,
    nota int,
    idmatricula int,
    primary key (id_estudiante, id_grupo),
    foreign key (id_estudiante) references estudiantes(id),
    foreign key (id_grupo) references grupos(id)
);

alter table usuarios add constraint usuarios_ck_rol check (rol in ('administrador','profesor','estudiante'));