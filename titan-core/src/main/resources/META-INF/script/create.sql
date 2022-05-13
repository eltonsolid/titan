CREATE TABLE TAPPLICATION (
	application_id number(6),
	name varchar(100),
	description varchar(100),
	created TIMESTAMP,
	constraint pk_application primary key(application_id)
);