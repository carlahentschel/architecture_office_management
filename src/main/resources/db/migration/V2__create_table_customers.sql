create table customers(
    cpf char(11) primary key,
    name varchar(100) not null,
    address varchar(100),
    phone char(11) not null,
    email varchar(100) not null unique,
    created_at date not null default now(),
    updated_at timestamp not null default now()
);