create table user_accounts (id bigserial primary key,
                          account_number varchar(20),
                          balance numeric(10,0),
                          custom_limit numeric(10,2),
                          user_id numeric(5,0));

insert into user_accounts (account_number, balance, custom_limit, user_id)
                        values ('1111', 5000, 20000.00, 1), ('2222', 6000, 30000.00, 2);
