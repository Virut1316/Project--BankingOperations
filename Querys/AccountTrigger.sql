
<-- implementation based on https://severalnines.com/database-blog/postgresql-triggers-and-stored-function-basics -->
create or replace function create_account() returns trigger 
as $createAccount$
begin
	insert into account (customer_id,active,balance) values (new.customer_id,false,0);
return new;
end;
$createAccount$ language 'plpgsql';

drop trigger assign_account on customers 

create trigger assign_account
after insert on customers for each  row
execute procedure create_account();