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
  team_uuid uuid,
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
  member_id uuid,
  to_member_id uuid,
  point int,
  created_date timestamp
);

CREATE TABLE IF NOT EXISTS team(
	id uuid PRIMARY KEY,
	teamid varchar,
	name varchar,
	member_uuid_as_an_admin uuid,
	description varchar,
	created_date timestamp
);
insert into team(id,teamid,name,description) values(now(),'admin','admin','admin team to takecare the app');







