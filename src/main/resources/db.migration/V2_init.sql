create table users
(
	id           bigserial    primary key,
	name     varchar(255) unique
);
insert into users(name,id)
values ("user_1",1),("user_2",2);