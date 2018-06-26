DROP KEYSPACE IF EXISTS test;

CREATE KEYSPACE IF NOT EXISTS test WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};
USE test;



CREATE TYPE IF NOT EXISTS address(
  country varchar,
  city varchar,
  pin varchar
);

CREATE TYPE IF NOT EXISTS contact(
  email varchar,
  mobile varchar,
  llt varchar
);

CREATE TABLE IF NOT EXISTS member (
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
  address frozen<address>,
  contact frozen<contact>,
  created_date timestamp,
  update_date timestamp
);


CREATE TABLE IF NOT EXISTS history(
  id uuid PRIMARY KEY,
  from_id uuid,
  point int,
  date timestamp
);