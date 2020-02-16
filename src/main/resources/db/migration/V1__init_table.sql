create table user
(
    id           int auto_increment
        primary key,
    account_id   varchar(100) null,
    name         varchar(50)  null,
    token        char(36)     null,
    gmt_create   varchar(100) null,
    gmt_modified varchar(100) null,
    dio          varchar(256) null
);
