DROP KEYSPACE test;
CREATE KEYSPACE test WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};
USE test;

CREATE TYPE address(
  country varchar,
  city varchar,
  pin varchar
);

CREATE TYPE contact(
  email varchar,
  mobile varchar,
  llt varchar
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
  address map<text,frozen<address>>,
  contact map<text,frozen<contact>>,
  created_date timestamp,
  update_date timestamp
);

CREATE TABLE history(
  id uuid PRIMARY KEY,
  from_id uuid,
  point int,
  date timestamp
);
