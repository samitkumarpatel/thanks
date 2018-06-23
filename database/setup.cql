DROP KEYSPACE test;
CREATE KEYSPACE test WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

USE test;

CREATE TYPE points(
    id uuid ,
	from_whom text,
	points int,
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
	points list<frozen<points>>
);

INSERT INTO test.member(id,password,firstname,lastname,status) VALUES(now(),'xxxxx','testf','testl',true);
INSERT INTO test.member(id,password,firstname,lastname,status) VALUES(now(),'xxxxx','testf1','testl1',true);

CREATE TABLE test.team (
	id uuid,
	name varchar,
	admin boolean,
	members set<text>,
	PRIMARY KEY(id,name)
);

INSERT INTO test.team (id,name,admin,members) VALUES (now(), 'test_team',true,{'uid1','RDA141','SPA349'});

select * from test.member;
select * from test.member where firstname='testf' ALLOW FILTERING;

