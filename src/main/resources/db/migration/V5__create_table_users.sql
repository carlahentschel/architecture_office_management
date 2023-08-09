create table users(
    id UUID primary key,
    username varchar(100) not null,
    password varchar(50) not null
);