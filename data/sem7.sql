use sem7
go

if OBJECT_ID('RuteStatii', 'U') is not null
	drop table RuteStatii
	
if OBJECT_ID('Statii', 'U') is not null
	drop table Statii
	
if OBJECT_ID('Rute', 'U') is not null
	drop table Rute
	
if OBJECT_ID('Trenuri', 'U') is not null
	drop table Trenuri


if OBJECT_ID('TipuriTren', 'U') is not null
	drop table TipuriTren

go

create table TipuriTren
	(CodTT tinyint primary key identity(1,1),
	Descriere varchar(500))

create table Trenuri
	(CodT smallint primary key identity(1,1),
	Nume varchar(50) unique,
	CodTT tinyint references TipuriTren(CodTT))

create table Rute
	(CodR smallint primary key identity(1,1),
	Nume varchar(500) unique,
	CodT smallint references Trenuri(CodT))

create table Statii
	(CodS smallint primary key identity(1,1),
	Nume varchar(500) unique)

create table RuteStatii
	(CodR smallint references Rute(CodR),
	CodS smallint references Statii(CodS),
	Sosire time,
	Plecare time,
	primary key(CodR, CodS))
go

insert into TipuriTren values ('regio'), ('interregio')
insert into Trenuri values ('t1', 1), ('t2', 1), ('t3', 1)
insert into Rute values ('r1', 1), ('r2', 2), ('r3', 3)
insert into Statii values ('s1'), ('s2'), ('s3')
insert into RuteStatii(CodR, CodS, Sosire, Plecare) values
	(1, 1, '7:50am', '8:00am'),
	(1, 2, '8:50am', '9:00am'),
	(1, 3, '9:50am', '10:00am'),
	(2, 1, '7:40am', '7:50am'),
	(2, 3, '17:50', '18:00'),
	(3, 2, '8:45am', '8:55am'),
	(3, 3, '19:00', '19:15');

go

select * from TipuriTren
select * from Trenuri
select * from Rute
select * from Statii
select * from RuteStatii
go

create proc uspStatiePeRute
@NumeR varchar(50),
@NumeS varchar(50),
@Sosire time,
@Plecare time
as
	declare @CodR smallint = (select CodR from Rute where @NumeR = Nume),
			@CodS smallint = (select CodS from Statii where @NumeS = Nume)
	--optional verificari, is not null, etc etc.
	if exists(select * from RuteStatii where CodR = @CodR and CodS = @CodS)
		update RuteStatii set Sosire = @Sosire, Plecare = @Plecare where CodS = @CodS and CodR = @CodR
	else
		insert into RuteStatii(CodR, CodS, Sosire, Plecare) values (@CodR, @CodS, @Sosire, @Plecare)

go

select * from RuteStatii

exec uspStatiePeRute @NumeR = 'r3', @NumeS = 's1', @Sosire = '16:00', @Plecare = '16:10'

go
create view vRuteCuToateStatiile
as
	select R.Nume
	from Rute R
	where not exists (
		select S.CodS
		from Statii S
		except 
		select RS.CodS
		from RuteStatii RS
		where RS.CodR = R.CodR
	)

go

select * from vRuteCuToateStatiile
go

-- sa <= eb ^ ea >= sb -- for intervals if suprapunere (James Allan)
create function ufStatiiCuCelPutin2Trenuri()
returns table
as return
	select Nume
	from Statii
	where CodS in
	(select RS1.CodS
		from RuteStatii RS1 inner join RuteStatii RS2 on RS1.CodS = RS2.CodS and
		RS1.CodR < RS2.CodR and
		RS1.Sosire <= RS2.Plecare and RS1.Plecare >= RS2.Sosire
	)
go

select * from ufStatiiCuCelPutin2Trenuri()