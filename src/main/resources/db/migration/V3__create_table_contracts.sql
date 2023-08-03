create table contracts(
    id UUID primary key,
    form_of_payment varchar(100) not null,
    initial_date date not null,
    final_date date not null,
    budget_id UUID references budgets(id) not null,
    customer_cpf char(11) references customers(cpf) not null,
    created_at date not null default now(),
    updated_at timestamp not null default now()
);