drop procedure if exists test2;
delete from test where id >= 0;

delimiter $$
create procedure test2()
begin
	declare i int;
    declare n int;
    set i := 1;
    set n := 10000;
    WHILE (i < n) DO
        insert into test (username, password, email) values (concat('user', i), concat('pass', i), concat('email', i));
        set i := i + 1;
    end while;
end;
$$
delimiter ;
call test2();