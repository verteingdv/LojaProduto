drop database if exists projeto;

create database projeto;

use projeto;

create table categoria(
	idcategoria int auto_increment primary key,
	nomecategoria varchar(30) not null
);

desc categoria;

create table produto(
	idproduto int auto_increment primary key,
	nome varchar(40) not null,
	estoque int not null,
	valor double(10,2) not null,
	datacadastro date,
	id_categoria int
);

alter table produto add constraint fkprodutocategoria 
		foreign key(id_categoria) references categoria(idcategoria);
		
insert into categoria values(null, 'Informatica'), (null, 'Eletronico'),
(null, 'Livros'), (null, 'Eletrodomestico'), (null, 'Roupas');

select * from categoria;


---------------------------------------------------------------------

use projeto;

create table usuario(
	idusuario int primary key auto_increment,
	nome varchar(40) not null,
	login varchar(50) not null unique,
	senha varchar(200) not null
);

desc usuario;


