insert into users (username, password, email, register_date, role)
values ('root', 'password', 'something@something.com', GETDATE(), 'admin');

insert into users (username, password, email, register_date, role)
values ('rex', 'password', 'something2@something.com', GETDATE(), 'user');

insert into friends (id_user1, id_user2, timestamp)
values (1, 5, GETDATE());

select * from users;

select * from friends;

delete from users where id=4;