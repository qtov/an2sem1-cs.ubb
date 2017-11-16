use proj1;

select * from users;
select * from categories;
select * from comments;
select * from friends;
select * from groups;
select * from topics;
select * from users_groups;

select * from users
where id between 1 and 5;

delete from users_groups;
delete from comments
dbcc checkident('comments', reseed, 0);
delete from group_follows;
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
values ('rex', 'password', 'something2@something.com', GETDATE(), 'admin');

insert into users (username, password, email, register_date, role)
values ('root1', 'password', 'something@something.com', GETDATE(), 'admin');

insert into users (username, password, email, register_date, role)
values ('rex1', 'password', 'something2@something.com', GETDATE(), 'user');

insert into users (username, password, email, register_date, role)
values ('root2', 'password', 'something@something.com', GETDATE(), 'admin');

insert into users (username, password, email, register_date, role)
values ('rex2', 'password', 'something2@something.com', GETDATE(), 'user');

go

insert into friends (id_user1, id_user2, timestamp)
values (
	(select id from users where username='test'),
	(select id from users where username='root'),
	GETDATE()
);

insert into friends (id_user1, id_user2, timestamp)
values (3, 4, GETDATE());

insert into categories (name)
values ('Introductions');

insert into categories (name)
values ('General');

insert into categories (name)
values ('Support');

go

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
	'a5',
	GETDATE()
);

go

insert into comments (id_user, id_topic, text, creation_date)
values (1, 1, 'Some random long faq comment', GETDATE());

insert into comments (id_user, id_topic, text, creation_date)
values (1, 1, 'Some bad comment by some bad user', GETDATE());

insert into groups (name, creation_date, motto)
values ('new group', GETDATE(), 'new motto');

insert into groups (name, creation_date)
values ('new group2', GETDATE());

go

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

select distinct * from users where role like 'admin' or id = 1 order by username;

insert into topics (id_creator_user, id_category, title)
values (7, 1, 'test');

select username as title_user from users
intersect
select title from topics;

select distinct username as title_user from users where username in (select title from topics);

select id from users
except
select id_user from comments;

select id from users where id not in (select id_user from comments);
select id from users where id not in (select id_user1 from friends) and id not in (select id_user2 from friends);

insert into users_groups (id_user, id_group, join_date, role)
values(1, 1, GETDATE(), 'founder');

insert into users_groups (id_user, id_group, join_date, role)
values(2, 1, GETDATE(), 'founder');

select distinct users.username
from users
inner join users_groups on users_groups.id_user = users.id
inner join groups on groups.name = 'new group';

insert into group_follows (id_user, id_group, follow_date) values (1, 1, GETDATE());
select users.username, groups.creation_date, groups.name
from users
inner join groups on groups.id = 1
inner join users_groups on users_groups.role = 'founder' and users_groups.id_user = users.id
inner join group_follows on group_follows.id_group = 1 and users.id = group_follows.id_user;

select users.username, users_groups.join_date
from users
left join users_groups on users_groups.id_user = users.id;

select users.username, users_groups.join_date
from users
right join users_groups on users_groups.id_user = users.id;

insert into likes (id_user, id_comment, like_date) values (1, 1, GETDATE());
select users.username, comments.id, likes.like_date
from users
full join likes on likes.id_user = users.id
inner join comments on comments.id = likes.id_comment;

select TOP(5) * from users u where u.id in (select id_user from comments c where c.id_user in (select t.id_creator_user from topics t where t.id_creator_user = c.id_user));
select * from users where id in (select id_user from users_groups) order by id;

insert into comments (id_user, id_topic, text, creation_date)
values (2, 1, 'Some random long faq comment2', GETDATE());
select * from users where exists(select id_user from comments where id_user=users.id);
select * from groups where exists(select id_group from users_groups where id_group=groups.id);

select *
from (select username as username, role as role from users where role='admin') as tab
where username like '___';

select *
from (select * from users where email like '%@test.com') as new_table;

update users set avatar='' where id=1;
select count(username) as avatar_num, sum(id) as sumid
from users
group by avatar
having avatar='';

select min(id) as first_user, max(id) as last_user, avg(id) as avg_howmany
from users
group by email
having email like '%________________%';

select count(id) as count_ids
from groups
group by logo
having logo='/imagevault/default_logo.jpg';

-- comentarii num dupa ultima inregistrare a ult util.
select comments.creation_date, count(*) as comments
from comments
group by creation_date
having DAY(creation_date) >= DAY((select max(register_date) from users));

select follow_date, count(*) as follows_bef
from group_follows
group by follow_date
having DAY(follow_date) < DAY((select max(DAY(register_date)) from users));

select TOP(1) g.name, g.creation_date, count(gf.id_group) as nr_follows
from groups g
full join group_follows gf on g.id = gf.id_group
group by g.name, g.creation_date
having g.creation_date >= ALL(select ug.join_date from users_groups ug)
order by nr_follows desc;

select count(*) as cid, count(*) * 2 from users
where id > ANY(select id from comments);

select *, 3*3
from users
where register_date < ALL(select creation_date from comments);

select *
from users u, comments c
where register_date < min(c.creation_date);

select *, 2+2 from topics t
where t.creation_date > ANY(select c.creation_date from comments c);

select *
from topics t, comments c
where t.creation_date > min(c.creation_date)

select *
from users
where register_date >= ALL(select creation_date from comments);

select *
from users u, comments c
where u.register_date >= max(c.creation_date);

select * from topics t
where t.creation_date <= ANY(select c.creation_date from comments c);

select *
from topics t, comments c
where t.creation_date <= min(c.creation_date);

select * from topics t
where t.creation_date = ANY(select c.creation_date from comments c);

select *
from topics t, comments c
where t.creation_date = c.creation_date;

select *
from comments c
where c.id >= ANY(select t.id from topics t where DAY(t.creation_date) in (select DAY(g.creation_date) from groups g));

select *
from group_follows gf
where DAY(gf.follow_date) >= ALL(
	select DAY(ug.join_date) from users_groups ug where ug.id_user in (
		select u.id from users u inner join comments c on u.id = c.id_user
	)
);
