CREATE SCHEMA availability;

CREATE SCHEMA employee;

CREATE SCHEMA messages;

CREATE SCHEMA planer;

CREATE SCHEMA schedule;

CREATE SCHEMA settings;

CREATE SCHEMA users;

CREATE SEQUENCE availability.availability_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE availability.availability (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    active boolean NOT NULL,
    availability_date date NOT NULL
);

CREATE TABLE availability.availability_mapping (
    availability_summary_id bigint NOT NULL,
    schedule_record_id bigint NOT NULL
);

CREATE SEQUENCE availability.availability_summary_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE availability.availability_summary (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    accepted_by character varying(255) NOT NULL,
    accepted_date timestamp without time zone NOT NULL,
    availability_date date NOT NULL,
    employee_id bigint
);

--
--CREATE SEQUENCE employee.contact_details_seq
--    START WITH 1
--    INCREMENT BY 1
--    NO MINVALUE
--    NO MAXVALUE
--    CACHE 1;


CREATE TABLE employee.contact_details (
--    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    zip_code character varying(6)
    phone_number character varying(255),
    contact_name character varying(255),
    contact_phone_number character varying(255),



    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
);


CREATE SEQUENCE employee.employee_details_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE employee.employee_details (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    crm_expiration_date date,
    crm_login character varying(255),
    fte character varying(255),
    fte_start character varying(255),
    goal1 character varying(4),
    goal2 character varying(4),
    goal3 character varying(4),
    goal4 character varying(4),
    goal5 character varying(4),
    lt_id character varying(8),
    lt_login character varying(255),
    user_line character varying(255),
    user_section character varying(255)
);


CREATE SEQUENCE employee.employee_info_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE employee.employee_info (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    agreement boolean NOT NULL,
    headphones boolean NOT NULL,
    id_card character varying(25),
    info1 character varying(255),
    info2 character varying(255),
    info3 character varying(255),
    info4 character varying(255),
    locker character varying(25),
    parking_card character varying(25),
    ppk boolean NOT NULL
);


CREATE SEQUENCE employee.employee_leave_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE employee.employee_leave (
    id bigint
	NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    additional integer NOT NULL,
    normal integer NOT NULL,
    past_years integer,
    uz integer NOT NULL
);


CREATE SEQUENCE employee.employees_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE employee.employees (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    basic_unit character varying(255),
    birthday date,
    contract_position character varying(255),
    contract_type character varying(255),
    created boolean NOT NULL,
    email character varying(255),
    end_contract_date date,
    end_work_date date,
    first_name character varying(20),
    last_name character varying(40),
    pesel character varying(40),
    position character varying(255),
    sex character varying(1),
    start_contract_date date,
    start_work_date date,
    unit character varying(255),
    work_status character varying(255),
    contact_details_id bigint,
    employee_details_id bigint,
    employee_info_id bigint,
    employee_leave_id bigint,
    etat character varying(255),
    active_account boolean
);

CREATE VIEW employee.personal_data_v AS
 SELECT empl.id AS employee_id,
    empl.basic_unit,
    empl.birthday,
    empl.contract_position,
    empl.contract_type,
    empl.created,
    empl.email,
    empl.etat,
    empl.end_contract_date,
    empl.end_work_date,
    empl.first_name,
    empl.last_name,
    empl.pesel,
    empl.position,
    empl.sex,
    empl.start_contract_date,
    empl.start_work_date,
    empl.unit,
    empl.work_status,
    empl.contact_details_id,
    empl.employee_details_id,
    empl.employee_info_id,
    empl.employee_leave_id,
    details.crm_expiration_date,
    details.crm_login,
    details.fte,
    details.fte_start,
    details.goal1,
    details.goal2,
    details.goal3,
    details.goal4,
    details.goal5,
    details.lt_id,
    details.lt_login,
    details.user_line,
    details.user_section,
    info.agreement,
    info.headphones,
    info.id_card,
    info.info1,
    info.info2,
    info.info3,
    info.info4,
    info.locker,
    info.parking_card,
    info.ppk,
    leave.additional,
    leave.normal,
    leave.past_years,
    leave.uz,
    contact.address,
    contact.city,
    contact.contact_name,
    contact.contact_phone_number,
    contact.phone_number,
    contact.zip_code
   FROM ((((employee.employees empl
     JOIN employee.employee_details details USING (id))
     JOIN employee.employee_info info USING (id))
     JOIN employee.employee_leave leave USING (id))
     JOIN employee.contact_details contact USING (id));


CREATE VIEW employee.hr_table_v AS
 SELECT personal_data_v.employee_id AS id,
    concat(personal_data_v.last_name, ' ', personal_data_v.first_name) AS full_name,
    personal_data_v.position,
    personal_data_v.etat,
    personal_data_v.lt_login,
    personal_data_v.end_work_date,
    personal_data_v.start_work_date,
    personal_data_v.user_section,
    personal_data_v.user_line,
    personal_data_v.work_status,
    personal_data_v.contract_type,
    personal_data_v.start_contract_date,
    personal_data_v.end_contract_date,
    personal_data_v.crm_expiration_date,
    personal_data_v.email,
    ((personal_data_v.normal + personal_data_v.past_years) + personal_data_v.additional) AS leave
   FROM employee.personal_data_v
  WHERE ((personal_data_v.work_status)::text <> 'NOT_WORK'::text);


CREATE SEQUENCE messages.messages_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE messages.messages (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    crated date,
    expired date,
    text character varying(255) NOT NULL,
    employee_id bigint NOT NULL
);



CREATE SEQUENCE planer.leave_planer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE planer.leave_planer (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    active boolean NOT NULL,
    planer_date date NOT NULL
);


CREATE TABLE planer.leave_planer_mapping (
    leave_planer_id bigint NOT NULL,
    schedule_record_id bigint NOT NULL
);


CREATE SEQUENCE planer.leave_planer_summary_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE planer.leave_planer_summary (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    accepted_by character varying(255) NOT NULL,
    year date NOT NULL,
    employee_id bigint
);


CREATE TABLE schedule.absence_record (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    accepted_by character varying(255),
    visible boolean NOT NULL,
    work_date date NOT NULL,
    employee_id bigint NOT NULL
);


CREATE SEQUENCE schedule.absence_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE schedule.schedule (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    active boolean NOT NULL,
    schedule_date date NOT NULL,
    visible boolean NOT NULL
);


CREATE SEQUENCE schedule.schedule_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE schedule.schedule_mapping (
    schedule_summary_id bigint NOT NULL,
    schedule_record_id bigint NOT NULL
);


CREATE SEQUENCE schedule.schedule_record_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE schedule.schedule_record (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    end_work time without time zone NOT NULL,
    start_work time without time zone NOT NULL,
    status character varying(255),
    types character varying(255),
    update_date date,
    work_date date NOT NULL,
    employee_id bigint NOT NULL
);


CREATE SEQUENCE schedule.schedule_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE SEQUENCE schedule.schedule_summary_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE schedule.schedule_summary (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    schedule_date date,
    employee_id bigint
);


CREATE SEQUENCE settings.contract_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



CREATE TABLE settings.contract_type (
    id bigint  NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    name character varying(50) NOT NULL
);



CREATE SEQUENCE settings.dayoff_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE settings.dayoff (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    date date NOT NULL,
    info character varying(255)
);


CREATE SEQUENCE settings.month_hours_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE settings.month_hours (
    id integer NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    april integer,
    august integer,
    december integer,
    february integer,
    january integer,
    july integer,
    june integer,
    march integer,
    may integer,
    november integer,
    october integer,
    september integer,
    year character varying(4) NOT NULL
);


CREATE SEQUENCE settings.user_line_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE settings.user_line (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    name character varying(20) NOT NULL
);


CREATE SEQUENCE settings.user_section_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE settings.user_section (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    expired date NOT NULL,
    lider character varying(255) NOT NULL,
    name character varying(40) NOT NULL
);



CREATE SEQUENCE users.users_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE users.users (
    id bigint NOT NULL,
    created_by character varying(255) NOT NULL,
    created_date timestamp without time zone NOT NULL,
    last_modified_by character varying(255) NOT NULL,
    last_modified_date timestamp without time zone NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(68) NOT NULL,
    role character varying(255) NOT NULL,
    status boolean NOT NULL,
    employee_id bigint
);
