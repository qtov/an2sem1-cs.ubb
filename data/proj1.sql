use proj1;

select * from users;
select * from categories;
select * from comments;
select * from friends;
select * from groups;
select * from topics;
select * from users_groups;

delete from users_groups;
delete from comments
dbcc checkident('comments', reseed, 0);
delete from groups
dbcc checkident('groups', reseed, 0);
delete from topics
dbcc checkident('topics', reseed, 0);
delete from categories
dbcc checkident('categories', reseed, 0);
delete from friends;
delete from users
dbcc checkident('users', reseed, 0);

insert into users (username, password, email, register_date, role)
values ('test', 'encrypted', 'test@test.com', GETDATE(), 'moderator');

insert into users (username, password, email, register_date, role)
values ('root', 'password', 'something@something.com', GETDATE(), 'admin');

insert into users (username, password, email, register_date, role)
values ('rex', 'password', 'something2@something.com', GETDATE(), 'user');

insert into users (username, password, email, register_date, role)
values ('root1', 'password', 'something@something.com', GETDATE(), 'admin');

insert into users (username, password, email, register_date, role)
values ('rex1', 'password', 'something2@something.com', GETDATE(), 'user');

insert into users (username, password, email, register_date, role)
values ('root2', 'password', 'something@something.com', GETDATE(), 'admin');

insert into users (username, password, email, register_date, role)
values ('rex2', 'password', 'something2@something.com', GETDATE(), 'user');

insert into friends (id_user1, id_user2, timestamp)
values (
	(select id from users where username='test'),
	(select id from users where username='root'),
	GETDATE()
);

insert into categories (name)
values ('Support');

insert into topics (id_creator_user, id_category, title, creation_date)
values ((select id from users where username='test'), 1, 'FAQ', GETDATE());

insert into friends(id_user1, id_user2, timestamp)
values (
	(select id from topics where title = 'FAQ'),
	5,
	GETDATE()
);

insert into friends(id_user1, id_user2, timestamp)
values (
	2,
	5,
	-- 'a5',
	GETDATE()
);

insert into comments (id_user, id_topic, text, creation_date)
values (1, 1, 'Some random long faq comment', GETDATE());

insert into comments (id_user, id_topic, text, creation_date)
values (1, 1, 'Some bad comment by some bad user', GETDATE());

insert into groups (name, creation_date, motto)
values ('new group', GETDATE(), 'new motto');

insert into groups (name, creation_date)
values ('new group2', GETDATE());

update users set password='new_password' where username like 'root';
update comments set text='Edited random long faq comment' where id=1;
update groups set motto='I added motto' where id=1;

delete from users where username in ('root1', 'root2');
delete from comments where id between 3 and 5;
delete from groups where motto is null;

-- 2

select id_user1 from friends where id_user2 = 3
union
select id_user2 from friends where id_user1 = 3
union all
select id from users where id = 3;

select * from users where role like 'admin' or id = 1;


insert into topics (id_creator_user, id_category, title)
values (7, 1, 'test');

select username from users
intersect
select title from topics;

select username from users where username in (select title from topics);

select id from users
except
select id_user from comments;

select id from users where id not in (select id_user from comments);
select id from users where id not in (select id_user1 from friends) and id not in (select id_user2 from friends);

insert into users_groups (id_user, id_group, join_date, role)
values(1, 1, GETDATE(), 'founder');

insert into users_groups (id_user, id_group, join_date, role)
values(2, 1, GETDATE(), 'founder');

select users.username
from users
inner join users_groups on users_groups.id_user = users.id
inner join groups on groups.name = 'new group';

insert into group_follows (id_user, id_group, follow_date) values (1, 1, GETDATE());
select users.username
from users
inner join groups on groups.id = 1
inner join users_groups on users_groups.role = 'founder' and users_groups.id_user = users.id
inner join group_follows on group_follows.id_group = 1 and users.id = group_follows.id_user;

select users.username
from users
left join users_groups on users_groups.id_user = users.id;

select users.username
from users
right join users_groups on users_groups.id_user = users.id;

select * from users where id in (select id_user from comments where id_user in (select id_user from topics));
select * from users where id in (select id_user from users_groups);

insert into comments (id_user, id_topic, text, creation_date)
values (2, 1, 'Some random long faq comment2', GETDATE());
select * from users where exists(select id_user from comments where id_user=users.id);
select * from groups where exists(select id_group from users_groups where id_group=groups.id);

select *
from (select username as username from users where id > 2) as tab
where username like '____';

select *
from (select * from users where email like '%@test.com') as new_table;

update users set avatar='' where id=1;
select count(username) as avatar_numm, sum(id)
from users
group by avatar
having avatar='';

select max(id), min(id), avg(id)
from users
group by email
having email like '%________________%';

select count(id) as count_ids
from groups
group by logo
having logo='/imagevault/default_logo.jpg';

select count(*)
from comments
group by creation_date
having DAY(creation_date) = DAY((select min(register_date) from users));

select count(*)
from group_follows
group by follow_date
having DAY(follow_date) > DAY((select avg(DAY(register_date)) from users));
