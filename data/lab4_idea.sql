-- example stuff
use proj1;

declare cursor1
cursor for
select * from users --whatever

open cursor1
while @@FETCH_STATUS = 0
fetch next from cursor1
into @var1, @var2

-- ...

end
close

--4 cursoare
-- 1 big cursor
-- -- 1 cursor for delete
-- -- 1 cursor for insert
-- -- 1 cursor for select