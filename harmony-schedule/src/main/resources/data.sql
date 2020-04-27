-- Temp data
--

CREATE SCHEMA IF NOT exists planer;
CREATE SCHEMA IF NOT exists availability;
CREATE SCHEMA IF NOT exists employee;
CREATE SCHEMA IF NOT exists messages;
CREATE SCHEMA IF NOT exists schedule;
CREATE SCHEMA IF NOT exists settings;
CREATE SCHEMA IF NOT exists users;

-- OAuth table
drop table if exists oauth_client_details;
    create table oauth_client_details (
    client_id VARCHAR(255) PRIMARY KEY,
    resource_ids VARCHAR(255),
    client_secret VARCHAR(255),
    scope VARCHAR(255),
    authorized_grant_types VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities VARCHAR(255),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(255)
    );

-- Contact details Table
INSERT INTO employee.contact_details(id, address, city, zip_code, phone_number, contact_phone_number, contact_name, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1,'Mickewicza 14','Katowice','41-477','123456789','987654321','Osoba kontaktowa 1', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2,'Damrota 1','Katowice','41-400','458777556','458777556','Osoba kontaktowa 2', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3,'Powstańców 1/4','Sosnowiec','49-999','986574213','986574213','Osoba kontaktowa 3', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4,'Plac Wolności 66/69','Jaworzno','47-477','222577982','222577982','Osoba kontaktowa 4', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5,'Szkolna 124/12','Warszawa','00-017','111222333','111222333','Osoba kontaktowa 5', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Employee details Table
INSERT INTO employee.employee_details(id, lt_login, lt_id, crm_login, crm_expiration_date, user_line, user_section, fte, fte_start, update_date, goal1, goal2, goal3, goal4, goal5, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1,'user_l','1354','user','2020-12-31','Sekcja 1','Level 1','1','1','2019-11-21','100','24','3','1','0', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2,'spec_l','1114','spec','2020-12-31','Sekcja 1','Level 1','1','1','2019-11-21','100','24','3','1','0', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3,'mena_l','2224','kier','2020-12-31','Sekcja 1','Level 2','1','1','2019-11-21','100','24','3','1','0', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4,'hr_l','4334','hr','2020-12-31','Sekcja HR','Level 3','1','1','2019-11-21','0','0','0','0','0', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5,'admin_l','1224','admin','2020-12-31','Sekcja IT','Level 4','1','1','2019-11-21','0','0','0','0','0', 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Employee info Table
INSERT INTO employee.employee_info(id, agreement, ppk, headphones, locker, id_card, parking_card, info1, info2, info3, info4, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1,true,true,true,'MS100','MAKSIMUM101','004',NULL,NULL,NULL,NULL, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2,true,true,true,'MS101','MAKSIMUM104','001',NULL,NULL,NULL,NULL, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3,true,true,true,'MS102','MAKSIMUM103','002',NULL,NULL,NULL,NULL, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4,true,true,true,'MS103','MAKSIMUM102','003',NULL,NULL,NULL,NULL, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5,true,true,true,'MS10$','MAKSIMUM001','006',NULL,NULL,NULL,NULL, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Employee leave Table
INSERT INTO employee.employee_leave(id, normal, uz, additional, past_years, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1,16,4,0,0, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2,16,4,0,0, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3,20,4,0,0, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4,20,4,1,0, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5,20,4,8,12, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Employee Table
INSERT INTO employee.employees(id, first_name, last_name, pesel, sex, birthday, email, position, contract_position, work_status, contract_type, basic_unit, unit, start_work_date, end_work_date, start_contract_date, end_contract_date, employee_details_id, contact_details_id, employee_info_id, employee_leave_id, created, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1,'User','Testowy','12345678987','M','1990-01-01', 'user@mail.pl','Doradca','ds. sprzedaży','WORK','Umowa','Kwota','1.1','2019-01-04','2021-01-04','2019-01-04','2021-01-04',1,1,1,1, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2,'Spec','Testowy','54832165478','K','1990-01-01','spec@mail.pl','Specjalista','ds. monitoringu','WORK','Umowa','Kwota','1.1','2019-01-04','2021-01-04','2019-01-04','2021-01-04',2,2,2,2, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3,'Manager','Testowy','11245678912','M','1990-01-01','man@mail.pl','Kierownik','Kieronwik Kampanii','WORK','Umowa','Kwota','1.1','2019-01-04','2021-01-04','2019-01-04','2021-01-04',3,3,3,3, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4,'Kadry','Testowy','98548555741','K','1990-01-01','hr@mail.pl','Specjalista','ds. zasobów ludzkich','WORK','Umowa','Kwota','1.1','2019-01-04','2021-01-04','2019-01-04','2021-01-04',4,4,4,4, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5,'Admin','Testowy','92457789632','M','1990-01-01','admin@mail.pl','Specjalista','ds. IT','WORK','Umowa','Kwota','1.1','2019-01-04','2021-01-04','2019-01-04','2021-01-04',5,5,5,5, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- User Table
INSERT INTO users.users(id, login, password, status, created, role, employee_id, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1, 'test_user', '$2a$10$gPB/C.DzwDu.nNahJtFzwOZpWCK8zU/Z.BoDBI62lxUdWFtoWwnH2', true, '2019-01-01', 'ROLE_USER', 1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2, 'test_spec', '$2a$10$gPB/C.DzwDu.nNahJtFzwOZpWCK8zU/Z.BoDBI62lxUdWFtoWwnH2', true, '2019-01-01', 'ROLE_SPEC', 2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3, 'test_manager', '$2a$10$gPB/C.DzwDu.nNahJtFzwOZpWCK8zU/Z.BoDBI62lxUdWFtoWwnH2', true, '2018-01-01', 'ROLE_MANAGER', 3, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4, 'test_hr', '$2a$10$gPB/C.DzwDu.nNahJtFzwOZpWCK8zU/Z.BoDBI62lxUdWFtoWwnH2', true, '2018-01-01', 'ROLE_HR', 4, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5, 'test_admin', '$2a$10$gPB/C.DzwDu.nNahJtFzwOZpWCK8zU/Z.BoDBI62lxUdWFtoWwnH2', true, '2018-01-01', 'ROLE_ADMIN', 5, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Schedule record Table
INSERT INTO schedule.schedule_record(id, end_work, start_work, status, types, work_date, employee_id, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-11-01',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-11-02',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-11-03',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4,'00:00:00','00:00:00','CN','ZALOGOWANIE','2019-11-04',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5,'16:00:00','08:00:00','P','ZALOGOWANIE','2019-11-01',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (6,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-01',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (7,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-02',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (8,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-03',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (9,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-04',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (10,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-07',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (11,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-08',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (12,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-09',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (13,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-10',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (14,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-11',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (15,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-14',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (16,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-15',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (17,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-16',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (18,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-17',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (19,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-18',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (20,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-21',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (21,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-22',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (22,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-23',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (23,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-24',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (24,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-25',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (25,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-28',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (26,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-29',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (27,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-30',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (28,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-31',1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (29,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-01',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (30,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-02',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (31,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-03',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (32,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-04',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (33,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-07',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (34,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-08',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (35,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-09',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (36,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-10',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (37,'17:00:00','09:00:00','CN','ZALOGOWANIE','2019-10-11',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (38,'17:00:00','09:00:00','CN','ZALOGOWANIE','2019-10-14',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (39,'17:00:00','09:00:00','CN','ZALOGOWANIE','2019-10-15',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (40,'17:00:00','09:00:00','CN','ZALOGOWANIE','2019-10-16',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (41,'17:00:00','09:00:00','CN','ZALOGOWANIE','2019-10-17',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (42,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-18',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (43,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-21',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (44,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-22',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (45,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-23',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (46,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-24',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (47,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-25',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (48,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-28',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (49,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-29',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (50,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-30',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (51,'17:00:00','09:00:00','P','ZALOGOWANIE','2019-10-31',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (52,'17:00:00','09:00:00','P','OBECNOSC','2019-10-31',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (53,'17:00:00','09:00:00','P','DOSTEPNOSC','2019-10-31',2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Schedule Table
INSERT INTO schedule.schedule(id, schedule_date, active, visible, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1, '2019-10-01', true, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2, '2019-11-01', true, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3, '2020-02-01', true, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4, '2019-12-01', true, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (5, '2020-01-01', true, true, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Schedule summary Table
INSERT INTO schedule.schedule_summary(id, schedule_date, employee_id, created_by, created_date, last_modified_by, last_modified_date) VALUES
  (1, '2019-10-01', 1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (2, '2019-11-01', 1, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (3, '2019-10-01', 2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01'),
  (4, '2019-11-01', 2, 'Wpis Testowy', '2020-01-01', 'Wpis Testowy', '2020-01-01');

-- Schedule summary Table
INSERT INTO schedule.schedule_mapping(schedule_summary_id, schedule_record_id) VALUES
  (2, 1),
  (2, 2),
  (2, 3),
  (2, 4),
  (1, 6),
  (1, 7),
  (1, 8),
  (1, 9),
  (1, 10),
  (1, 11),
  (1, 12),
  (1, 13),
  (1, 14),
  (1, 15),
  (1, 16),
  (1, 17),
  (1, 18),
  (1, 19),
  (1, 20),
  (1, 21),
  (1, 22),
  (1, 23),
  (1, 24),
  (1, 25),
  (1, 26),
  (1, 27),
  (1, 28),
  (3, 29),
  (3, 30),
  (3, 31),
  (3, 32),
  (3, 33),
  (3, 34),
  (3, 35),
  (3, 36),
  (3, 37),
  (3, 38),
  (3, 39),
  (3, 40),
  (3, 41),
  (3, 42),
  (3, 43),
  (3, 44),
  (3, 45),
  (3, 46),
  (3, 47),
  (3, 48),
  (3, 49),
  (3, 50),
  (3, 51),
  (3, 52),
  (3, 53),
  (4, 5);


