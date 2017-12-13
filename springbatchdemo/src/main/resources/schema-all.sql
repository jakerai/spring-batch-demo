DROP TABLE IF EXISTS people;
DROP TABLE IF EXISTS people_report;

CREATE TABLE people  (
   id BIGSERIAL NOT NULL PRIMARY KEY,
   first_name VARCHAR(20),
   last_name VARCHAR(20),status SMALLINT, date_time TIMESTAMP);

CREATE TABLE people_report (id BIGINT NOT NULL,full_name VARCHAR(40),creation_date_time TIMESTAMP);

    INSERT INTO public.people(
	first_name, last_name,status,date_time)
	VALUES ('Vishal', 'Rai',0,now());
	
	INSERT INTO public.people(
	 first_name, last_name,status,date_time)
	VALUES ('Jake', 'Rai',0,now());
	
	INSERT INTO public.people(
	first_name, last_name,status,date_time)
	VALUES ('John', 'Cater',0,now());
	
	INSERT INTO public.people(
	first_name, last_name,status,date_time)
	VALUES ('James', 'Bond',0,now());
	
	INSERT INTO public.people(
	first_name, last_name,status,date_time)
	VALUES ('Sylvester', 'Stallone',0,now());
	
	INSERT INTO public.people(
	first_name, last_name,status,date_time)
	VALUES ('Emma', 'Watson',0,now());
	
	INSERT INTO public.people(
	first_name, last_name,status,date_time)
	VALUES ('Arnold', 'schwarzenegger',0,now());
	
	INSERT INTO public.people(
	 first_name, last_name,status,date_time)
	VALUES ('Mark', 'Zukerburg',0,now());
	
	INSERT INTO public.people(
	 first_name, last_name,status,date_time)
	VALUES ('Bill', 'gates',0,now());