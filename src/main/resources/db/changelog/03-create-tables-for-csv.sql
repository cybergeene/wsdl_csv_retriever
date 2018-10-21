--liquibase formatted sql

--changeset e.mednov:1 dbms:h2
CREATE TABLE csv
(
  id int NOT NULL,
  file_name varchar(100) NOT NULL,
  CONSTRAINT Csv_pk PRIMARY KEY (id, file_name)
);

--rollback drop table csv


--changeset e.mednov:2 dbms:h2
CREATE UNIQUE INDEX Csv_id_file_name_uindex ON Csv (id, file_name);