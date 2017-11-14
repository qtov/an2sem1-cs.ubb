use proj1;
go

create proc modify_column_type
as
	alter table dummy alter column id bigint;
	update version_db set version=1;
go

create proc add_column
as
	alter table dummy add type varchar(30);
	update version_db set version=2;
go

create proc drop_column
as
	alter table dummy drop column type;
	update version_db set version=1;
go

create proc add_default
as
	alter table dummy add constraint def_type default 'something' for type;
	update version_db set version=3;
go

create proc drop_default
as
	alter table dummy drop constraint def_type;
	update version_db set version=2;
go

create proc add_primary
as
	alter table dummy add constraint pk_dummy primary key (id);
	update version_db set version=4;
go

create proc drop_primary
as
	alter table dummy drop constraint pk_dummy;
	update version_db set version=3;
go

create proc add_secondary
as
	update version_db set version=5;	
go

create proc drop_secondary
as
	update version_db set version=4;
go

create proc add_foreign
as
	alter table dummy add constraint fk_DummyComments foreign key (id) references comments(id);
	update version_db set version=6;
go

create proc drop_foreign
as
	alter table dummy drop constraint fk_DummyComments;
	update version_db set version=5;
go

create proc add_table
as
	create table dummy2 (
		id smallint,
		name varchar(50),
		type varchar(10),
		timestamp datetime
	);
	update version_db set version=7;
go

create proc drop_table
as
	drop table dummy2;
	update version_db set version=6;
go