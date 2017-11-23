use proj1;
go

drop proc modify_column_type
go
drop proc revert_column_type
go
drop proc add_column
go
drop proc drop_column
go
drop proc add_default
go
drop proc drop_default
go
drop proc add_primary
go
drop proc drop_primary
go
drop proc add_secondary
go
drop proc drop_secondary
go
drop proc add_foreign
go
drop proc drop_foreign
go
drop proc add_table
go
drop proc drop_table
go
drop proc go_to_version
go

create proc modify_column_type
as
	alter table dummy alter column id bigint not null;
	update version_db set version=1;
go

create proc revert_column_type
as
	alter table dummy alter column id int not null;
	update version_db set version=0;
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
	alter table dummy add constraint fk_DummyComments foreign key (fk1) references comments(id);
	update version_db set version=5;
go

create proc drop_secondary
as
	alter table dummy drop constraint fk_DummyComments;
	update version_db set version=4;
go

create proc add_foreign
as
	alter table dummy add constraint fk_DummyTopics foreign key (fk2) references topics(id);
	update version_db set version=6;
go

create proc drop_foreign
as
	alter table dummy drop constraint fk_DummyTopics;
	update version_db set version=5;
go

create proc add_table
as
	create table dummy2 (
		id smallint not null,
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

create proc go_to_version
@version int
as
	declare @current_version int
	set @current_version = (select TOP(1) v.version from version_db v)
	if @version > @current_version
	begin
		declare @func_to_exec varchar(50);
		set @func_to_exec = (select TOP(1) func from version_func where id > @current_version and id <= @version order by id asc);
		select @func_to_exec as executed;
		exec @func_to_exec;
		update version_db set version = @current_version + 1;
		exec go_to_version @version;
	end
	else if @version < @current_version
	begin
		declare @func_to_revert varchar(50);
		set @func_to_revert = (select TOP(1) revert_func from version_func where id <= @current_version and id > @version order by id desc);
		select @func_to_revert as executed;
		exec @func_to_revert
		update version_db set version = @current_version - 1;
		exec go_to_version @version;
	end
go

exec go_to_version 0;