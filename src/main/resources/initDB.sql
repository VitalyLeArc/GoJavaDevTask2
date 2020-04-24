create schema if not exists gosqltask1

CHARACTER SET = utf8mb4
COLLATE= utf8mb4_0900_ai_ci;
 
use gosqltask1;

drop table if exists developers;
create table developers(
	id int auto_increment,
    	name varchar(45) not null,
    	age int not null,
        sex enum('male','female') not null,
	salary float,
    	primary key (id));

drop table if exists skills;
create table skills(
	id int not null auto_increment,
    	skill_name varchar(45)not null,
    	grade enum('Junior','Middle','Senior'),
    	primary key(id));
        
drop table if exists projects;
create table projects(
	id int auto_increment,
    	project_name varchar(45) not null,
    	version varchar(45) not null,
        datebegin date,
    	primary key(id));
        
drop table if exists companies;
create table companies(
    	id int auto_increment,
    	company_name varchar(45) not null,
    	main_office_city_id int not null,
    	primary key(id));

drop table if exists customers;
create table customers(
    	id int auto_increment,
    	customer_name varchar(45) not null,
    	minage int,
    	maxage int,
    	primary key(id));
        
drop table if exists cities;
create table cities(
	id int not null auto_increment,
	city_name varchar(45) not null,
	primary key(id));
        
drop table if exists link_developers_skills;        
create table link_developers_skills(
    	dev_id int not null,
    	skill_id int not null,
    	primary key (dev_id,skill_id));

drop table if exists link_developers_projects;
create table link_developers_projects(
	dev_id int not null,
	project_id int not null,
    	primary key (dev_id,project_id));

drop table if exists link_companies_projects;
create table link_companies_projects(
	company_id int not null,
	project_id int not null,
        primary key (company_id,project_id));
        
drop table if exists link_customers_projects;
create table link_customers_projects(
	customer_id int not null,
	project_id int not null,
        primary key (customer_id,project_id));

truncate table developers;
insert into developers (name,age,sex) values
	('Vasya',18,'male'),
    	('Alla',28,'female'),
	('Borya',32,'male'),
    	('Alina',22,'female'),
    	('Alisa',31,'female'),
    	('Petya',45,'male'),
	('Gosha',29,'male'),
    	('Nazar',54,'male'),
    	('Foma',62,'male');
        
truncate table skills;
insert into skills (skill_name,grade) values 
    ('Java','Middle'),
    ('Java','Senior'),
    ('C++','Senior'),
    ('JS','Junior'),
    ('C#','Middle'),
    ('C#','Junior'),
    ('lua','Middle');
    
truncate table projects;
insert into projects (project_name,version) values
    ('Minecraft','1.15.2'),
    ('WorldOfWarcraft','8.3'),
    ('UnrealEngine4','4.24'),
    ('Rhinoceros','6.1.18023'),
    ('GroundWar:Tanks','3.4'),
    ('PizzaNinja4','2.3'),
    ('Android','7.2');

truncate table cities;
insert into cities (city_name) values
    ('Irvine'),
    ('Stockgolm'),
    ('Cary'),
    ('North america'),
    ('Berdyansk'),
    ('Mountain View');
    
truncate table companies;
insert into companies (company_name,main_office_city_id) values
    ('Blizzard Entertainment',1),
    ('Mojang',2),
    ('Epic Games',3),
    ('McNeel',4),
    ('Garaj Corporation',5),
    ('Google',6);

truncate table customers;
insert into customers (customer_name,minage,maxage) values
    ('Other developers',18,50),
    ('All people',null,null),
    ('Schoolchildren',7,16),
    ('Nolife gamers',15,45),
    ('Designers',18,55),
    ('Workers',18,65);

truncate table link_developers_skills;
insert into link_developers_skills values
    (1,4),
    (2,1),
    (2,4),
    (3,3),
    (3,7),
    (4,1),
    (4,5),
    (5,2),
    (5,5),
    (6,5),
    (6,4),
    (6,1),
    (7,6),
    (7,1),
    (8,3),
    (8,7),
    (8,2),
    (9,7),
    (9,5),
    (9,2);

truncate table link_developers_projects;
insert into link_developers_projects values
    (1,6),
    (1,5),
    (2,5),
    (2,6),
    (2,1),
    (3,3),
    (3,2),
    (4,7),
    (4,4),
    (5,4),
    (5,1),
    (6,5),
    (6,1),
    (6,4),
    (7,4),
    (7,7),
    (8,1),
    (8,2),
    (8,3),
    (8,7),
    (9,1),
    (9,2);

truncate table link_companies_projects;
insert into link_companies_projects values
    (1,2),
    (2,1),
    (3,3),
    (4,4),
    (5,5),
    (5,6),
    (6,7);

truncate table link_customers_projects;
insert into link_customers_projects values
    (1,2),
    (1,3),
    (1,7),
    (2,7),
    (3,1),
    (3,2),
    (3,5),
    (3,6),
    (4,1),
    (4,2),
    (4,5),
    (5,3),
    (5,2),
    (6,5),
    (6,6);

update developers dev
join (select dev.id,sk.grade
	from developers dev
    join link_developers_skills lds
on dev.id=lds.dev_id
join skills sk
on sk.id=lds.skill_id) tmp
set dev.salary=600 where tmp.grade='Junior' and tmp.id=dev.id;

update developers dev
join (select dev.id,sk.grade
	from developers dev
    join link_developers_skills lds
on dev.id=lds.dev_id
join skills sk
on sk.id=lds.skill_id) tmp
set dev.salary=1200 where tmp.grade='Middle' and tmp.id=dev.id;

update developers dev
join (select dev.id,sk.grade
	from developers dev
    join link_developers_skills lds
on dev.id=lds.dev_id
join skills sk
on sk.id=lds.skill_id) tmp
set dev.salary=3000 where tmp.grade='Senior' and tmp.id=dev.id;

update developers dev
join (select dev.id,sk.skill_name
	from developers dev
    join link_developers_skills lds
on dev.id=lds.dev_id
join skills sk
on sk.id=lds.skill_id) tmp
set dev.salary=dev.salary+300 where tmp.skill_name='C++' and tmp.id=dev.id;

update developers dev
join (select dev.id,sk.skill_name
	from developers dev
    join link_developers_skills lds
on dev.id=lds.dev_id
join skills sk
on sk.id=lds.skill_id) tmp
set dev.salary=dev.salary+500 where tmp.skill_name='Java' and tmp.id=dev.id;

update developers dev
join (select dev.id,sk.skill_name
	from developers dev
    join link_developers_skills lds
on dev.id=lds.dev_id
join skills sk
on sk.id=lds.skill_id) tmp
set dev.salary=dev.salary+600 where tmp.skill_name='C#' and tmp.id=dev.id;

update developers dev
join (select dev.id,sk.skill_name
	from developers dev
    join link_developers_skills lds
on dev.id=lds.dev_id
join skills sk
on sk.id=lds.skill_id) tmp
set dev.salary=dev.salary+400 where tmp.skill_name='lua' and tmp.id=dev.id;

update gosqltask1.projects pr set datebegin=adddate(now(),interval -5 day) where pr.id = 1;
update gosqltask1.projects pr set datebegin=adddate(now(),interval -3 month) where pr.id = 2;
update gosqltask1.projects pr set datebegin=adddate(now(),interval -2 month) where pr.id = 3;
update gosqltask1.projects pr set datebegin=adddate(now(),interval -7 month) where pr.id = 4;
update gosqltask1.projects pr set datebegin=adddate(now(),interval -4 month) where pr.id = 5;
update gosqltask1.projects pr set datebegin=adddate(now(),interval -1 month) where pr.id = 6;
update gosqltask1.projects pr set datebegin=adddate(now(),interval -11 month) where pr.id = 7;