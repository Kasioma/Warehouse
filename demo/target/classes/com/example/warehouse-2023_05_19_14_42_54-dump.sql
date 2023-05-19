--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

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
-- Name: Sailors; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA "Sailors";


ALTER SCHEMA "Sailors" OWNER TO postgres;

--
-- Name: pokemons; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA pokemons;


ALTER SCHEMA pokemons OWNER TO postgres;

--
-- Name: warehouse; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA warehouse;


ALTER SCHEMA warehouse OWNER TO postgres;

--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    age integer NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: moninv; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.moninv (
    id character varying(36) NOT NULL,
    user_id character varying(36) NOT NULL,
    pok_entry integer NOT NULL
);


ALTER TABLE public.moninv OWNER TO postgres;

--
-- Name: monstats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.monstats (
    name character varying(50) NOT NULL,
    hp integer NOT NULL,
    attack integer NOT NULL,
    defence integer NOT NULL,
    sp_attack integer NOT NULL,
    sp_defence integer NOT NULL,
    speed integer NOT NULL
);


ALTER TABLE public.monstats OWNER TO postgres;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    clientid integer NOT NULL,
    productid integer NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_id_seq OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: pokemons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pokemons (
    entry integer NOT NULL,
    name character varying(50) NOT NULL,
    type character varying(50) NOT NULL,
    ability character varying(50) NOT NULL,
    icon character varying(1000) NOT NULL
);


ALTER TABLE public.pokemons OWNER TO postgres;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    stock integer NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id character varying(36),
    nickname character varying(100),
    email character varying(100) NOT NULL,
    password character varying(1000) NOT NULL,
    isadmin boolean DEFAULT false
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: clients; Type: TABLE; Schema: warehouse; Owner: postgres
--

CREATE TABLE warehouse.clients (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    age integer NOT NULL
);


ALTER TABLE warehouse.clients OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: warehouse; Owner: postgres
--

CREATE SEQUENCE warehouse.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE warehouse.clients_id_seq OWNER TO postgres;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: warehouse; Owner: postgres
--

ALTER SEQUENCE warehouse.clients_id_seq OWNED BY warehouse.clients.id;


--
-- Name: orders; Type: TABLE; Schema: warehouse; Owner: postgres
--

CREATE TABLE warehouse.orders (
    id integer NOT NULL,
    clientid integer NOT NULL,
    productid integer NOT NULL,
    quantity integer NOT NULL
);


ALTER TABLE warehouse.orders OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: warehouse; Owner: postgres
--

CREATE SEQUENCE warehouse.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE warehouse.orders_id_seq OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: warehouse; Owner: postgres
--

ALTER SEQUENCE warehouse.orders_id_seq OWNED BY warehouse.orders.id;


--
-- Name: products; Type: TABLE; Schema: warehouse; Owner: postgres
--

CREATE TABLE warehouse.products (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    stock integer NOT NULL
);


ALTER TABLE warehouse.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: warehouse; Owner: postgres
--

CREATE SEQUENCE warehouse.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE warehouse.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: warehouse; Owner: postgres
--

ALTER SEQUENCE warehouse.products_id_seq OWNED BY warehouse.products.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: clients id; Type: DEFAULT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.clients ALTER COLUMN id SET DEFAULT nextval('warehouse.clients_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.orders ALTER COLUMN id SET DEFAULT nextval('warehouse.orders_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.products ALTER COLUMN id SET DEFAULT nextval('warehouse.products_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (id, name, address, email, age) FROM stdin;
\.


--
-- Data for Name: moninv; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.moninv (id, user_id, pok_entry) FROM stdin;
33d9e0e2-a222-466a-a809-350bf59e6ccd	289b8a37-9614-4e6a-932c-6f043df9ef36	6
8807e9ea-3d8c-4cec-b2c5-75c51a76da55	289b8a37-9614-4e6a-932c-6f043df9ef36	7
4df2f3b5-ba3c-42e6-a15d-1a125383320b	289b8a37-9614-4e6a-932c-6f043df9ef36	5
132b19a4-cb70-4ca5-be85-26e133c9ee98	289b8a37-9614-4e6a-932c-6f043df9ef36	5
3b1488f1-7c8a-4a20-a072-72040590bbde	289b8a37-9614-4e6a-932c-6f043df9ef36	6
81893128-a9cb-4b62-8016-34da0c917b37	289b8a37-9614-4e6a-932c-6f043df9ef36	7
2af540ae-1ac6-4b33-8ea4-9585225acc36	289b8a37-9614-4e6a-932c-6f043df9ef36	5
8492dec1-8a28-45f6-83c2-fe4b23ea311b	289b8a37-9614-4e6a-932c-6f043df9ef36	5
3ff7be1d-e54d-477b-9812-0e800f06b6f0	289b8a37-9614-4e6a-932c-6f043df9ef36	6
7cebdfdf-99a0-430c-ba57-c51f13f5b0b3	289b8a37-9614-4e6a-932c-6f043df9ef36	7
9c8fdac8-b913-4a3d-8f74-3bf91d132cba	289b8a37-9614-4e6a-932c-6f043df9ef36	5
0e58ece2-c92b-4e61-b16e-c394cd0bc917	289b8a37-9614-4e6a-932c-6f043df9ef36	5
c58ea382-410c-4d47-9025-feaad1f6e435	289b8a37-9614-4e6a-932c-6f043df9ef36	6
b6379c49-71cb-4642-89f8-bc956bc27bd0	289b8a37-9614-4e6a-932c-6f043df9ef36	7
4e9e30c0-fbf4-4d55-a2bc-1b3e3aa69774	289b8a37-9614-4e6a-932c-6f043df9ef36	5
aa4262bc-0a6c-4b36-a51a-22b3fe33f69a	289b8a37-9614-4e6a-932c-6f043df9ef36	5
d6d41d33-2e6f-420c-9d12-4805b7eb657a	289b8a37-9614-4e6a-932c-6f043df9ef36	6
90e48204-de51-4999-8a17-4faf519a2358	289b8a37-9614-4e6a-932c-6f043df9ef36	7
71abae0a-b765-4712-a1ea-0125dd28dd10	289b8a37-9614-4e6a-932c-6f043df9ef36	5
9859bd5a-5583-4829-83ca-54a0ae1723a4	289b8a37-9614-4e6a-932c-6f043df9ef36	5
7983ab70-e089-437c-9dd1-283e4bb97bb1	289b8a37-9614-4e6a-932c-6f043df9ef36	6
28a31f04-d334-4a0f-950f-bf04ac68502f	289b8a37-9614-4e6a-932c-6f043df9ef36	7
0de3e2d2-ea27-47e7-bab2-2de2443792c9	289b8a37-9614-4e6a-932c-6f043df9ef36	5
c581cb7f-e8aa-46bf-acba-5e5400f456a8	289b8a37-9614-4e6a-932c-6f043df9ef36	5
b02a24aa-2876-4d7d-ac35-cfcc8c0f94c8	289b8a37-9614-4e6a-932c-6f043df9ef36	6
0479b75d-b399-4017-bd87-352f5fe88a4d	289b8a37-9614-4e6a-932c-6f043df9ef36	7
9c4274fe-78e0-455b-855a-1306f4cf13e8	289b8a37-9614-4e6a-932c-6f043df9ef36	5
aa56afa6-e8d3-4e68-9351-1a18187ae8b8	289b8a37-9614-4e6a-932c-6f043df9ef36	5
96402097-dcfe-49ea-a60f-dc92dd506fdb	289b8a37-9614-4e6a-932c-6f043df9ef36	6
dce1d16d-7eba-4284-af70-a5463c7e8df4	289b8a37-9614-4e6a-932c-6f043df9ef36	7
b894d32c-b807-4823-9f86-223d8240b070	289b8a37-9614-4e6a-932c-6f043df9ef36	5
bfd89e6c-eee8-4177-8dca-bfee3d65ac70	289b8a37-9614-4e6a-932c-6f043df9ef36	5
62835006-6917-420b-a175-9bad1b41384e	289b8a37-9614-4e6a-932c-6f043df9ef36	6
84e7eb01-9f2c-4819-8860-0b4a8a2464c2	289b8a37-9614-4e6a-932c-6f043df9ef36	7
36e32f88-39fa-46ca-8330-43f7d5775ad3	289b8a37-9614-4e6a-932c-6f043df9ef36	5
4f7bad41-2f1f-4d43-b7b1-e2f484758d60	289b8a37-9614-4e6a-932c-6f043df9ef36	5
52222744-8bc3-4e23-b70c-39dad6bc4370	289b8a37-9614-4e6a-932c-6f043df9ef36	6
1c9250b2-d005-4597-b1ce-fd220ef3077d	289b8a37-9614-4e6a-932c-6f043df9ef36	7
8bb1cc20-9ca7-41e0-837f-3fe24d962532	289b8a37-9614-4e6a-932c-6f043df9ef36	5
91cc3cf0-fb83-4226-be68-dc2b3c2aa212	289b8a37-9614-4e6a-932c-6f043df9ef36	5
8a75993d-072e-4c2d-abd2-0e9ad00df09d	289b8a37-9614-4e6a-932c-6f043df9ef36	6
ee35ff77-d4ae-469f-9da2-67293e46f73e	289b8a37-9614-4e6a-932c-6f043df9ef36	7
86d0a81f-14f0-48db-bcc8-235180bed736	289b8a37-9614-4e6a-932c-6f043df9ef36	5
a13bfe21-7987-4b6e-b93d-91114b1f6a4d	289b8a37-9614-4e6a-932c-6f043df9ef36	5
57a144d7-8e37-45a0-90f1-84490ff39f17	289b8a37-9614-4e6a-932c-6f043df9ef36	6
39176f61-3b4f-42a4-9bb3-c6041d7b59aa	289b8a37-9614-4e6a-932c-6f043df9ef36	7
3fc48d15-71df-4eb7-9548-11e2b934ac11	289b8a37-9614-4e6a-932c-6f043df9ef36	5
1aaae9af-1d60-404c-840d-852e3450debe	289b8a37-9614-4e6a-932c-6f043df9ef36	5
9a616789-6d81-415d-a2d4-2972fff48ada	289b8a37-9614-4e6a-932c-6f043df9ef36	5
147768d6-477a-4ca7-b334-58cee430c904	289b8a37-9614-4e6a-932c-6f043df9ef36	5
c55b402d-4366-447e-96cf-ee7bde7cc80a	289b8a37-9614-4e6a-932c-6f043df9ef36	5
75527344-b4c4-4608-bd47-453d47262842	289b8a37-9614-4e6a-932c-6f043df9ef36	5
737143ff-1620-4508-aa8f-8057029bf227	289b8a37-9614-4e6a-932c-6f043df9ef36	5
e1c01404-6183-4b5c-a9b1-856ba6e2e70a	289b8a37-9614-4e6a-932c-6f043df9ef36	5
\.


--
-- Data for Name: monstats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.monstats (name, hp, attack, defence, sp_attack, sp_defence, speed) FROM stdin;
Charmeleon	58	64	58	80	65	80
Charizard	78	84	78	109	85	100
Squirtle	44	48	65	50	64	43
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, clientid, productid, quantity) FROM stdin;
\.


--
-- Data for Name: pokemons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pokemons (entry, name, type, ability, icon) FROM stdin;
6	Charizard	Fire	Blaze, Solar Power	https://img.pokemondb.net/sprites/black-white/normal/charizard.png
7	Squirtle	Water	Torrent, Rain Dish	https://img.pokemondb.net/sprites/black-white/normal/squirtle.png
5	Charmeleon	Fire	Blaze, Solar Power	https://img.pokemondb.net/sprites/black-white/normal/charmeleon.png
10	Caterpie	Bug	Shield Dust, Run Away	https://img.pokemondb.net/sprites/black-white/normal/caterpie.png
9	Blastoise	Water	Torrent, Rain Dish	https://img.pokemondb.net/sprites/black-white/normal/blastoise.png
4	Charmander	Fire	Blaze, Solar Power	https://img.pokemondb.net/sprites/black-white/normal/charmander.png
3	Venusaur	Grass, Poison	Overgrow, Chlorophyll	https://img.pokemondb.net/sprites/black-white/normal/venusaur-f.png
8	Wartortle	Water	Torrent, Rain Dish	https://img.pokemondb.net/sprites/black-white/normal/wartortle.png
1	Bulbasaur	Grass, Poison	Overgrow, Chlorophyll	https://img.pokemondb.net/sprites/black-white/normal/bulbasaur.png
2	Ivysaur	Grass, Poison	Overgrow, Chlorophyll	https://img.pokemondb.net/sprites/black-white/normal/ivysaur.png
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, name, stock) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, nickname, email, password, isadmin) FROM stdin;
289b8a37-9614-4e6a-932c-6f043df9ef36	Jerry	a@a	$2b$10$ITERde/Seog5ejDxAJCYD.ZgkPouTGh3sKpQxDVxjjjruDYqWU5l6	f
19269a0f-1af8-4f2b-b4e1-4add37318be7	Joldi	admin@admin	$2b$10$Jv3jLWcC7kxHIdN/P9k1JedxF7wk3onNDrBS2Nw5YuhA2Qnrrc2Li	t
\.


--
-- Data for Name: clients; Type: TABLE DATA; Schema: warehouse; Owner: postgres
--

COPY warehouse.clients (id, name, address, email, age) FROM stdin;
6	aaa	aaa	aaa	1
7	v	v	v	1
3	ge	d	d	4
8	qqq	qqq	qqq	1000
10	wdawd	awdawd	adawdw	13
11	Vasile	sum	example@email.com	14
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: warehouse; Owner: postgres
--

COPY warehouse.orders (id, clientid, productid, quantity) FROM stdin;
9	10	6	50
10	10	6	50
11	10	6	50
7	7	6	5
13	11	6	20
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: warehouse; Owner: postgres
--

COPY warehouse.products (id, name, stock) FROM stdin;
3	branza	100
2	branza	60
4	sampn	200
5	sampon	350
7	awdawd	7
6	kek	40
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_id_seq', 1, false);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_id_seq', 1, false);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 1, false);


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: warehouse; Owner: postgres
--

SELECT pg_catalog.setval('warehouse.clients_id_seq', 12, true);


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: warehouse; Owner: postgres
--

SELECT pg_catalog.setval('warehouse.orders_id_seq', 13, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: warehouse; Owner: postgres
--

SELECT pg_catalog.setval('warehouse.products_id_seq', 8, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: moninv moninv_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.moninv
    ADD CONSTRAINT moninv_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: pokemons pokemons_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemons
    ADD CONSTRAINT pokemons_name_key UNIQUE (name);


--
-- Name: pokemons pokemons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pokemons
    ADD CONSTRAINT pokemons_pkey PRIMARY KEY (entry);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: users users_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_key UNIQUE (id);


--
-- Name: users users_nickname_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_nickname_key UNIQUE (nickname);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (email);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: moninv moninv_pok_entry_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.moninv
    ADD CONSTRAINT moninv_pok_entry_fkey FOREIGN KEY (pok_entry) REFERENCES public.pokemons(entry);


--
-- Name: moninv moninv_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.moninv
    ADD CONSTRAINT moninv_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: orders orders_clientid_fkey; Type: FK CONSTRAINT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.orders
    ADD CONSTRAINT orders_clientid_fkey FOREIGN KEY (clientid) REFERENCES warehouse.clients(id);


--
-- Name: orders orders_productid_fkey; Type: FK CONSTRAINT; Schema: warehouse; Owner: postgres
--

ALTER TABLE ONLY warehouse.orders
    ADD CONSTRAINT orders_productid_fkey FOREIGN KEY (productid) REFERENCES warehouse.products(id);


--
-- PostgreSQL database dump complete
--

