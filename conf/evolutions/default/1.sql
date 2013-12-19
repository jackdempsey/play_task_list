# Tasks schema
 
# --- !Ups


CREATE TABLE task (
    id integer not null auto_increment primary key,
    label varchar(255)
);
 
# --- !Downs
 
DROP TABLE task;
