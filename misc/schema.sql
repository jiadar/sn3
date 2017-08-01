--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: concubines; Type: TABLE; Schema: public; Owner: sn
--

CREATE TABLE concubines (
    race character varying,
    description text,
    user_id bigint,
    id integer NOT NULL
);


ALTER TABLE concubines OWNER TO sn;

--
-- Name: concubine_id_seq; Type: SEQUENCE; Schema: public; Owner: sn
--

CREATE SEQUENCE concubine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE concubine_id_seq OWNER TO sn;

--
-- Name: concubine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sn
--

ALTER SEQUENCE concubine_id_seq OWNED BY concubines.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: sn
--

CREATE TABLE users (
    email character varying,
    password character varying,
    id integer NOT NULL,
    username character varying
);


ALTER TABLE users OWNER TO sn;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: sn
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO sn;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sn
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: concubines id; Type: DEFAULT; Schema: public; Owner: sn
--

ALTER TABLE ONLY concubines ALTER COLUMN id SET DEFAULT nextval('concubine_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: sn
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: concubines concubine_pkey; Type: CONSTRAINT; Schema: public; Owner: sn
--

ALTER TABLE ONLY concubines
    ADD CONSTRAINT concubine_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: sn
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

