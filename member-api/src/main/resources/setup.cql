CREATE KEYSPACE test WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};
USE test;

CREATE TYPE history(
  id uuid ,
  getfrom text,
  date timestamp
);

CREATE TABLE member (
  id uuid PRIMARY KEY,
  password varchar,
  firstname varchar,
  lastname varchar,
  role varchar,
  team varchar,
  empid varchar,
  status boolean,
  technology list<text>,
  points int,
  history list<frozen<history>>
);

INSERT INTO test.member(id,password,firstname,lastname,status) VALUES(now(),'xxxxx','testf','testl',true);
INSERT INTO test.member(id,password,firstname,lastname,status) VALUES(now(),'xxxxx','testf1','testl1',true);
