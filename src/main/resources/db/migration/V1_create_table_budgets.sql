create table budgets(
    id UUID primary key,
    customer_name varchar(100) not null,
    project varchar(100) not null,
    project_stages text not null,
    work_address varchar(100) not null,
    estimated_hours int not null,
    "date" date not null,
    "value" double not null
);