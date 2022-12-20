--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: pasteproj; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA pasteproj;


ALTER SCHEMA pasteproj OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

SET search_path to pasteproj;

--
-- Name: pastes; Type: TABLE; Schema: pasteproj; Owner: postgres
--

CREATE TABLE pastes (
    id bigserial PRIMARY KEY,
    data varchar(1000),
    hash varchar(35),
    lifetime varchar(100),
    isPublic boolean
);

ALTER TABLE pastes OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: pasteproj; Owner: postgres
--

CREATE TABLE users (
    id serial PRIMARY KEY,
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    enabled boolean,
    fk_role varchar(35)
);

ALTER TABLE users OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: pasteproj; Owner: postgres
--

CREATE TABLE roles(
    name varchar(35) PRIMARY KEY
);

ALTER TABLE roles OWNER TO postgres;

--
-- Name: permissions; Type: TABLE; Schema: pasteproj; Owner: postgres
--

CREATE TABLE permissions(
    id serial PRIMARY KEY,
    name varchar(45) not null,
    fk_role varchar(35)
);


ALTER TABLE permissions OWNER TO postgres;

--
-- Name: users users_role_fkey; Type: FK CONSTRAINT; Schema: pasteproj; Owner: postgres
--

ALTER TABLE users
ADD CONSTRAINT users_role_fkey
FOREIGN KEY (fk_role) REFERENCES roles(name);

--
-- Name: permissions permissions_role_fkey; Type: FK CONSTRAINT; Schema: pasteproj; Owner: postgres
--

ALTER TABLE permissions 
ADD CONSTRAINT permissions_role_fkey
FOREIGN KEY (fk_role) REFERENCES roles(name);

--
-- Data for Name: roles; Type: TABLE DATA; Schema: pasteproj; Owner: postgres
--

INSERT INTO roles(name) values ('ROLE_USER'),('ROLE_ADMIN');

--
-- Data for Name: users; Type: TABLE DATA; Schema: pasteproj; Owner: postgres
--

INSERT INTO users(username, password, enabled, fk_role)
values
( ('Admin'), ('{bcrypt}$2a$12$M/B./UmqLaPoR3tIaWbdkul2rlyzkH3K/bouU.NAQm5iDN/HNBNVm'), (true), ('ROLE_ADMIN') ),
( ('User'), ('{bcrypt}$2a$12$M/B./UmqLaPoR3tIaWbdkul2rlyzkH3K/bouU.NAQm5iDN/HNBNVm'), (true), ('ROLE_USER'));

--
-- Data for Name: permissions; Type: TABLE DATA; Schema: pasteproj; Owner: postgres
-- 

INSERT INTO permissions(name, fk_role)
values
( ('paste:create'), ('ROLE_USER') ),
( ('paste:create'), ('ROLE_ADMIN') ),
( ('paste:remove'), ('ROLE_ADMIN') );

--
-- PostgreSQL database dump complete
--





















