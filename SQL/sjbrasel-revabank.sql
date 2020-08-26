drop table if exists account_users;
drop table if exists transactions;
drop table if exists accounts cascade;
drop table if exists app_users cascade;
drop table if exists transferals;
drop table if exists account_types;
drop table if exists user_roles;

create table user_roles(
	id						serial,
	role_name 				varchar(25) not null,
	constraint user_roles_pk
		primary key (id)
);

create table account_types(
	id						serial,
	account_type 			varchar(25) not null,
	constraint account_type_pk
		primary key (id)
);

create table transferals(
	id						serial,
	user_account_id			int not null,
	transaction_id 			int not null,
	transferee_account_id	int not null,
	constraint transfer_id
		primary key (id)
);

create table app_users(
	id						serial,
	first_name				varchar(25) not null,
	last_name				varchar(25) not null,
	username 				varchar(25) unique not null,
	user_password 				varchar(256) not null,
	email					varchar(256) unique not null,
	role_id					int not null,
	constraint user_pk
		primary key (id),
	constraint user_role_fk
		foreign key (role_id) 
		references user_roles (id)
		on delete no action 
		on update no action
);

create table accounts(
	id 						serial,
	account_name			varchar(25),
	balance					money,
	account_type			int not null,
	constraint account_pk 
		primary key (id),
	constraint account_type_fk 
		foreign key (account_type)
		references account_types (id)
		on delete no action
		on update cascade
);

create table transactions(
	id						serial,
	transaction_date		date default current_date,
	description 			text,
	amount					money,
	account_id				int not null,
	balance					money,
	constraint transaction_pk
		primary key (id),
	constraint transaction_account_id_fk
		foreign key (account_id)
		references accounts (id) 
		on delete cascade
);

-- TODO 
-- Make function that gets called on delete to check if account_user is the last
-- 		user of a specific account, if so, cascade deletion.
create table account_users(
	id 						serial,
	account_id				int not null,
	account_user_id 		int not null,
	constraint account_user_pk 
		primary key (id),
	constraint account_id_fk
		foreign key (account_id)
		references accounts (id),
	constraint account_user_id_fk
		foreign key (account_user_id)
		references app_users (id)
);

insert into user_roles (role_name)
values 
	('Admin'),
	('Manager'),
	('Clerk'),
	('User'),
	('Locked');
	
insert into account_types (account_type)
values
	('Saving'),
	('Checking');

create or replace function no_overdrafting()
returns trigger 
as $$
	
	begin
		if(new.balance < cast(0 as money)) then 
			return null; -- cancels the execution of the original statement
		end if;
	return new; -- return to the trigger without halting the original statement
	end
$$ language plpgsql;

-- create a trigger 
create trigger no_overdraft 
before insert or update on accounts
for each row 
execute function no_overdrafting();


-- +-------------------------------------------------------------+
-- +                    	  TESTING
-- +-------------------------------------------------------------+

select * from app_users au;
select * from accounts a ;
select * from account_users au2 ;
select * -- everything
from accounts ac
join account_users au 
on ac.id = au.account_id
join app_users au2 
on au.account_user_id = au2.id 
join account_types at2 
on ac.account_type = at2.id
order by au.account_user_id;

select au2.first_name, au2.last_name, au2.username , ac.account_name as Type, ac.balance, ac.id
from accounts ac
join account_users au 
on ac.id = au.account_id
join app_users au2 
on au.account_user_id = au2.id 
join account_types at2 
on ac.account_type = at2.id
group by ac.id, au2.first_name, au2.last_name, au2.username , ac.account_name, ac.balance, au.account_user_id
order by au.account_user_id;

select * from accounts a 
join account_users au 
on a.id = au.id
where au.account_user_id = 1;


insert into app_users (first_name, last_name , username , user_password ,email , role_id )
values
	('Alice', 'Anderrson', 'aanderson', 'password', 'aanderson@revature.net', 1),
	('Benjamin', 'Barker', 'bbarker', 'password', 'bbarker@revature.net', 1),
	('Charlie', 'Courtson', 'ccourtson', 'password', 'ccourtson@revature.net', 1),
	('Debra', 'Delion', 'ddelion', 'password', 'ddelion@revature.net', 1),
	('Edward', 'Eliotson', 'eeliotson', 'password', 'eeliotson@revature.net', 1);
insert into accounts (account_name , balance , account_type )
values
	('Checking', 100, 1),
	('Checking', 110, 1),
	('Checking', 120, 1),
	('Checking', 130, 1),
	('Checking', 130, 1),
	('Saving', 140, 2)
;
insert into account_users (account_id, account_user_id)
values 
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 1),
	(1, 2);

update accounts
set balance = balance - cast(200 as money)
where id = 1;

delete
from accounts 
where id = 7;
