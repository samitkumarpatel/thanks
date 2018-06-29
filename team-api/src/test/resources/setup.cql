CREATE TABLE IF NOT EXISTS team(
  id uuid PRIMARY KEY,
  teamid varchar,
  name varchar,
  member_uuid_as_an_admin uuid,
  description varchar,
  created_date timestamp
);
insert into team(id,teamid,name,description) values(now(),'admin','admin','admin team to takecare the app');