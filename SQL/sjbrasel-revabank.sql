drop table if exists accounts_users;
drop table if exists transactions;
drop table if exists accounts;
drop table if exists app_users;
drop table if exists transferals;
drop table if exists account_types;
drop table if exists user_roles;

create table user_roles(
	id					serial,
	role_name 			varchar(25) not null,
	constraint user_roles_pk
		primary key (id)
);

create table account_types(
	id					serial,
	account_type 		varchar(25) not null,
	constraint account_type_pk
		primary key (id)
);

create table transferals(
	id					serial,
	user_id				int not null,
	transaction_id 		int not null,
	transferee_id 		int not null,
	constraint transfer_id
		primary key (id)
);

create table app_users(
	id					serial,
	first_name			varchar(25) not null,
	last_name			varchar(25) not null,
	username 			varchar(25) unique not null,
	password 			varchar(256) not null,
	email				varchar(256) unique not null,
	role_id				int not null,
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
	id					serial,
	transaction_date	date default current_date,
	description 		text,
	amount				money,
	account_id			int not null,
	balance				money,
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


select * from app_users au ;
