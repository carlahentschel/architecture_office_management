create table payments(
    id UUID primary key,
    "value" decimal not null,
    quota varchar(100) not null,
    "date" date not null,
    contract_id UUID references contracts(id) not null
);