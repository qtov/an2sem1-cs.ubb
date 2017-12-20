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