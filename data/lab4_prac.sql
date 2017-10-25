use master

if exists(select * from sys.databases where name='ora18')
begin -- begin si end only when needed
	drop database ora18
end

create database ora18
go -- go executa un block, dupa care trece la next block
use ora18

create table clienti (
	id int primary key,
	nume varchar(40),
	prenume varchar(50)
);
go

create table produse (
	id int primary key identity(1,1),
	denumire varchar(40),
	pret money
);
go

create table vanzari (
	id_client int constraint FK_clienti_vanzari foreign key references clienti(id),
	id_produs int foreign key references produse(id),
	pret money,
	primary key(id_client, id_produs)
);
