create table if not exists cms_wedding_invite
(
    id           serial       not null,
    full_name    varchar(255) not null,
    is_invite    boolean      not null,
    phone        varchar(10),
    attendees_no int2,
    wish         varchar(1024),
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id)
);

create table if not exists cms_wedding_component_images
(
    id           varchar(50)  not null,
    title        varchar(50)  not null,
    url          varchar(255) not null,
    orders       int          not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id)
);

insert into cms_wedding_component_images (id, title, url, orders, created_date, created_by, updated_date, updated_by)
values ('HERO_BG', 'Hero Background', '', 1, now(), 'admin', now(), 'admin'),
       ('CALENDAR_BG', 'Calendar Background', '', 2, now(), 'admin', now(), 'admin');

create table if not exists cms_wedding_gallery_images
(
    id           serial       not null,
    title        varchar(100),
    url          varchar(255) not null,
    orders       int          not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id)
);