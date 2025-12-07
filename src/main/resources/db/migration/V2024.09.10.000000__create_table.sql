create table if not exists cms_category
(
    id           serial       not null,
    name         varchar(50)  not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id)
);

create table if not exists cms_post
(
    id           serial       not null,
    category_id  bigint       not null,
    status       varchar(10)  not null,
    title        varchar(500) not null,
    slug         varchar(255) not null,
    thumbnail    varchar(255),
    content      text         not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id),
    foreign key (category_id) references cms_category (id)
);

create table if not exists cms_comment
(
    id           serial       not null,
    post_id      bigint       not null,
    content      varchar(500) not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id),
    foreign key (post_id) references cms_post (id)
);

create table if not exists cms_file
(
    id           serial       not null,
    post_id      bigint       not null,
    file_name    varchar(500) not null,
    file_path    varchar(500) not null,
    file_type    varchar(100) not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id),
    foreign key (post_id) references cms_post (id)
);

create table if not exists cms_tag
(
    id           serial       not null,
    name         varchar(50)  not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id)
);

create table if not exists cms_post_tag
(
    id      serial not null,
    post_id bigint not null,
    tag_id  bigint not null,
    primary key (id),
    foreign key (post_id) references cms_post (id),
    foreign key (tag_id) references cms_tag (id)
);