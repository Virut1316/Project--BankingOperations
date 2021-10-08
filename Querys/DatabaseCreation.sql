drop table if exists Customers;
create table if not exists Customers (
	customer_id int primary key generated always as identity,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(50) unique not null,
	username varchar(10) unique not null,
	password varchar(20) not null
);

INSERT INTO public.customers
(first_name, last_name,email, username, "password")
VALUES('Alvaro', 'Noboa','anoboa@gmail.com', 'Alvarito', 'bananas');
INSERT INTO public.customers
(first_name, last_name,email, username, "password")
VALUES('Jaime', 'Nebot','jnebot@gmail.com', 'Jaimito', 'guayaquil');


drop table if exists Employee;
create table if not exists Employee (
	employee_id int primary key generated always as identity,
	admin boolean not null,
	username varchar(10) unique not null,
	password varchar(20) not null
);

drop table if exists Account;
create table if not exists Account (
	account_id int primary key generated always as identity,
	customer_id int references customers(customer_id) not null,
	active boolean not null,
	balance int not null
	
);

