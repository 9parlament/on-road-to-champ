create table users
(
    id uuid primary key default gen_random_uuid()
    username varchar_ignorecase(50) unique not null,
    password varchar_ignorecase(500) not null,
    enabled  boolean not null
);
create table authorities
(
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);