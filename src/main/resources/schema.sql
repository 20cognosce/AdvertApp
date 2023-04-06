create table IF NOT EXISTS users (
    id serial primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(255) not null,
    phone varchar(20) not null
    );

create table IF NOT EXISTS advert (
    id serial primary key,
    title varchar(255) not null,
    content text not null,
    published_on timestamp not null,
    updated_on timestamp,
    user_id int,
    foreign key (user_id) references users (id)
);