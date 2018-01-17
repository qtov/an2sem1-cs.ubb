use lab5;

alter table ta add primary key (coda);
alter table tb add primary key (codb);
alter table tc add constraint fk_ta_coda foreign key (coda) references ta(coda);
alter table tc add constraint fk_tb_codb foreign key (codb) references tb(codb);

declare @i int;
set @i = 0;

while @i < 10000
begin
	insert into ta values (@i, @i);
	set @i = @i + 1;
end

select * from ta;

go

delete from tb;

declare @i int;
set @i = 0;

while @i < 3000
begin
	insert into tb values (@i, @i);
	set @i = @i + 1;
end

select * from tb;

go

delete from tc;

declare @i int;
set @i = 0;

while @i < 30000
begin
	insert into tc values (@i, @i % 10000, @i % 3000);
	set @i = @i + 1;
end

select * from tc;

go

select * from ta
where a2 = 100;

select * from ta
where coda = 23

go
create function check_if_exists_ta(
@id int
) returns bit
as begin
	if exists(select coda from ta where @id=coda)
	begin
		return 1;
	end
	return 0;
end

go
create function check_if_valid_a2(
@col1 int
) returns bit
as begin
	if exists(select a2 from ta where @col1 = a2)
	begin
		return 0;
	end
	return 1;
end


go

alter proc add_ta
@id int,
@col1 int,
@col2 int
as
	if (dbo.check_if_exists_ta(@id) = 1)
	begin
		raiserror('Id-ul exista deja.', 6, 1);
		return;
	end
	if (dbo.check_if_valid_a2(@col1) = 0)
	begin
		raiserror('a2 e cheie unique si exista deja', 6, 1);
		return;
	end
	insert into ta (coda, a2, a3) values (@id, @col1, @col2);
go

exec add_ta @id=2, @col1=3, @col2=6

go
alter proc delete_from_ta
@id int
as
	if (dbo.check_if_exists_ta(@id) = 1)
		delete from ta where coda=@id;
	else
		raiserror('Id-ul nu exista', 2, 1);
go

alter proc update_ta
@id int,
@col1 int,
@col2 int
as
	if (dbo.check_if_exists_ta(@id) = 1)
		if (dbo.check_if_valid_a2(@col1) = 1)
			update ta set a2=@col1, a3=@col2 where coda=@id;
		else
			raiserror('a2 nu e unique', 6, 1);
	else
		raiserror('Id nu exista', 6, 1);
go

create proc select_ta_a2_bigger
@col1 int
as
	select * from ta where a2 > @col1;
go

create function check_if_exists_tb(
@id int
) returns bit
as begin
	if exists(select codb from tb where @id=codb)
	begin
		return 1;
	end
	return 0;
end

go

create proc add_tb
@id int,
@col1 int
as
	if (dbo.check_if_exists_tb(@id) = 1)
	begin
		raiserror('Id-ul exista deja.', 6, 1);
		return;
	end
	insert into tb (codb, b2) values (@id, @col1);
go

create proc delete_from_tb
@id int
as
	if (dbo.check_if_exists_tb(@id) = 1)
		delete from tb where codb=@id;
	else
		raiserror('Id-ul nu exista', 2, 1);
go

create proc update_tb
@id int,
@col1 int
as
	if (dbo.check_if_exists_tb(@id) = 1)
		update tb set b2=@col1 where codb=@id;
	else
		raiserror('Id nu exista', 6, 1);
go

create proc select_tb_b2_lower
@col1 int
as
	select * from tb where b2 < @col1;
go

select coda from ta order by coda; -- clustered index scan
select coda from ta where coda = 66; --clustered index seek

select coda from ta where a2 > 955; --index seek nonclustered
select a2 from ta order by a2; --index scan nonclustered

select a2 from ta where a2 = 598; --lookup

create unique nonclustered index idx_ncl on tb(b2);
drop index idx_ncl on tb;
select b2 from tb where b2 = 1542; --nr of rows read
--0.007 before, 0.003 after

create nonclustered index idx_ncl_coda on tc(coda);
create nonclustered index idx_ncl_codb on tc(codb);

select ta.a2, tb.b2, tc.codc
from tc
inner join ta on tc.coda = ta.coda
inner join tb on tc.codb = tb.codb

drop index idx_ncl_coda on tc;
drop index idx_ncl_codb on tc;

drop index idx_ncl_a2_a3 on ta;
create nonclustered index idx_ncl_a2_a3 on ta(a2) include (a3);
select a2, a3 from ta where a2 = 540;

go
alter view view_ta
as
	select ta.a2, ta.a3, tb.codb from ta, tb where ta.a2 = 300 and tb.b2 = 356;
go

select * from view_ta;