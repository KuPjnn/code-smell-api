create table cms_category
(
    id           bigint       not null auto_increment,
    name         varchar(50)  not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id)
);

create table cms_post
(
    id           bigint       not null auto_increment,
    category_id  bigint       not null,
    status       varchar(10)  not null,
    title        varchar(500) not null,
    slug         varchar(255) not null,
    content      text         not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id),
    foreign key (category_id) references cms_category (id)
);

create table cms_comment
(
    id           bigint       not null auto_increment,
    post_id      bigint       not null,
    content      varchar(500) not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id),
    foreign key (post_id) references cms_post (id)
);

create table cms_file
(
    id           bigint       not null auto_increment,
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

create table cms_tag
(
    id           bigint       not null auto_increment,
    name         varchar(50)  not null,
    created_date timestamp    not null,
    created_by   varchar(255) not null,
    updated_date timestamp    not null,
    updated_by   varchar(255) not null,
    primary key (id)
);

create table cms_post_tag
(
    id      bigint not null auto_increment,
    post_id bigint not null,
    tag_id  bigint not null,
    primary key (id),
    foreign key (post_id) references cms_post (id),
    foreign key (tag_id) references cms_tag (id)
);