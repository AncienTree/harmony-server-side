CREATE SCHEMA availability;

;

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
