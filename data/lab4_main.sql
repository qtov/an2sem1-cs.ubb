use proj1;

delete from Teste
dbcc checkident('Teste', reseed, 0);
insert into Teste (Nume) values ('TesteTabele');
insert into Teste (Nume) values ('TesteViewuri');
--sau
insert into Teste (Nume) values ('insert');
insert into Teste (Nume) values ('select');
insert into Teste (Nume) values ('delete');

delete from Tabele
dbcc checkident('Tabele', reseed, 0);
insert into Tabele (Nume) values ('users');
insert into Tabele (Nume) values ('topics');
insert into Tabele (Nume) values ('topic_follows');

delete from TesteTabele;
insert into TesteTabele (CodTest, CodTabel, NrRanduri, Pozitie) values (1, 1, 10000, 1);
insert into TesteTabele (CodTest, CodTabel, NrRanduri, Pozitie) values (1, 2, 10000, 2);
insert into TesteTabele (CodTest, CodTabel, NrRanduri, Pozitie) values (1, 3, 10000, 3);
update TesteTabele set NrRanduri = 10000;
-- 100 000 -- 40 sec

delete from Viewuri
dbcc checkident('Viewuri', reseed, 0);
insert into Viewuri (Nume) values ('view_topics');
insert into Viewuri (Nume) values ('view_user_register');
insert into Viewuri (Nume) values ('view_users');

delete from TesteViewuri;
insert into TesteViewuri (CodTest, CodView) values (2, 1);
insert into TesteViewuri (CodTest, CodView) values (2, 2);
insert into TesteViewuri (CodTest, CodView) values (2, 3);

go

drop proc addfields1;
drop proc addfields2;
drop proc addfields3;

delete from topic_follows;
go
delete from topics
dbcc checkident('topics', reseed, 0);
delete from users
dbcc checkident('users', reseed, 0);
delete from RulariTesteTabele;
delete from RulariTesteViewuri;
go
delete from RulariTeste
dbcc checkident('RulariTeste', reseed, 0);
go

go
create proc addfields1
@amount int
as
	declare @i int;
	set @i = 1;
	while @i <= @amount
	begin
		insert into users (username, password, email) values (concat('user', @i), concat('qwe', @i), concat('em', @i)); 
		set @i = @i + 1;
	end	
go

create proc addfields2
@amount int
as
	declare @i int;
	set @i = 1;
	while @i <= @amount
	begin
		insert into topics (id_creator_user, id_category, title) values (@i, 1, concat('title', @i)); 
		set @i = @i + 1;
	end	
go

create proc addfields3
@amount int
as
	declare @i int;
	set @i = 1;
	while @i <= @amount
	begin
		insert into topic_follows (id_user, id_topic) values (@i, @i); 
		set @i = @i + 1;
	end	
go

declare @id_test int
declare @name_test nvarchar(50)

declare ctest cursor
for
	select CodTest, Nume from Teste;
open ctest
	fetch next from ctest into @id_test, @name_test;
	while @@FETCH_STATUS = 0 begin
		if @name_test = 'TesteTabele'
		-- id_test if teste (insert, delete, select)
		begin
			declare @t_t_cte int
			declare @t_t_cta int
			declare @t_t_nr int
			declare @t_t_p int

			insert into RulariTeste (Descriere, IncepeLa, SeIncheieLa) values ('Adaugare in tabele', SYSDATETIME(), null);
			declare c_t_t cursor
			for
				select * from TesteTabele
			open c_t_t
				fetch next from c_t_t into @t_t_cte, @t_t_cta, @t_t_nr, @t_t_p
				while @@FETCH_STATUS = 0 begin
					insert into RulariTesteTabele (CodRulareTest, CodTabel, IncepeLa, SeIncheieLa) values (@t_t_cte, @t_t_cta, SYSDATETIME(), 0);
					exec('addfields' + @t_t_cta + ' ' + @t_t_nr);
					update RulariTesteTabele set SeIncheieLa = SYSDATETIME() where CodRulareTest = @t_t_cte and CodTabel = @t_t_cta;
					fetch next from c_t_t into @t_t_cte, @t_t_cta, @t_t_nr, @t_t_p
				end
			close c_t_t
			deallocate c_t_t
			update RulariTeste set SeIncheieLa = SYSDATETIME() where @id_test = CodRulareTest;
		end
		else if @name_test = 'TesteViewuri'
		-- id_test if teste (insert, delete, select)
		begin
			declare @t_v_ct int
			declare @t_v_cv int

			insert into RulariTeste (Descriere, IncepeLa, SeIncheieLa) values ('Select din view-uri', SYSDATETIME(), null);
			declare c_t_v cursor
			for
				select * from TesteViewuri
			open c_t_v
				fetch next from c_t_v into @t_v_ct, @t_v_cv
				while @@FETCH_STATUS = 0 begin
					insert into RulariTesteViewuri (CodRulareTest, CodView, IncepeLa, SeIncheieLa) values (@t_v_ct, @t_v_cv, SYSDATETIME(), 0);
					select * from (select Nume from Viewuri where CodView = @t_v_cv) as aview;
					update RulariTesteViewuri set SeIncheieLa = SYSDATETIME() where CodRulareTest = @t_v_ct and CodView = @t_v_cv;
					fetch next from c_t_v into @t_v_ct, @t_v_cv
				end
			close c_t_v
			deallocate c_t_v
			update RulariTeste set SeIncheieLa = SYSDATETIME() where @id_test = CodRulareTest;
		end
		fetch next from ctest into @id_test, @name_test
	end
close ctest;
deallocate ctest;

--insert into cursor if teste (insert, delete, select)
insert into RulariTeste (Descriere, IncepeLa, SeIncheieLa) values ('Delete din tabele', SYSDATETIME(), null);
delete from topic_follows;
go
delete from topics;
delete from users;
update RulariTeste set SeIncheieLa = SYSDATETIME() where CodRulareTest = (select TOP(1) CodRulareTest from RulariTeste order by CodRulareTest desc);

dbcc checkident('users', reseed, 0);
dbcc checkident('topics', reseed, 0);
dbcc checkident('RulariTeste', reseed, 0);