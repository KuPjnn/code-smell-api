alter table cms_wedding_invite
    add column if not exists transportation varchar(50),
    add column if not exists relation       varchar(20);