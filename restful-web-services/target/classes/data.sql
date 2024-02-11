insert into user_details(id, birth_date, name)
values(1001, current_date, 'jay');

insert into user_details(id, birth_date, name)
values(1002, current_date, 'ranges');

insert into post(id, description, user_id)
values(2001, 'i want to learn go-lang', 1002);

insert into post(id, description, user_id)
values(2002, 'i want to learn react', 1001);