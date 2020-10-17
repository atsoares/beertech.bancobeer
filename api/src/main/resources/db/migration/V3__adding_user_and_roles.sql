truncate table transaction;
truncate table account;

create table USER (
    ID numeric(20) NOT NULL,
    DOCUMENT_NUMBER varchar(20) NOT NULL,
    EMAIL varchar(200) NOT NULL,
    ROLE int NOT NULL
);

alter table "user" add constraint pk_user primary key(ID);


alter table account add column FK_USER_ID numeric(20) not null;

alter table account add constraint fk_account_user foreign key (FK_USER_ID)
references user (ID) on delete no action on update no action;