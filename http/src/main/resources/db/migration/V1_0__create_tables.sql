-- Postgres Script generated by Postgres Workbench
-- Wed Aug 24 09:28:44 2022
-- Model: New Model    Version: 1.0
-- Postgres Workbench Forward Engineering


--Delete all tables


DROP TABLE IF EXISTS public.item;
DROP TABLE IF EXISTS public.shop;
DROP TABLE IF EXISTS public.online_user;


-- Table: public.shop



CREATE TABLE IF NOT EXISTS public.shop
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    createdate timestamp without time zone NOT NULL,
    total numeric(19,2) NOT NULL,
    useridentifier character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT shop_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.shop
    OWNER to postgres;

-- Table: public.item

-- DROP TABLE IF EXISTS public.item;

CREATE TABLE IF NOT EXISTS public.item
(
    shop_id bigint NOT NULL,
    price numeric(19,2) NOT NULL,
    productidentifier character varying(255) COLLATE pg_catalog."default" NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.item
    OWNER to postgres;

-- Table: public.online_user

-- DROP TABLE IF EXISTS public.online_user;

CREATE TABLE IF NOT EXISTS public.online_user
(
    cpf character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address character varying(255) COLLATE pg_catalog."default" NOT NULL,
    createdate timestamp without time zone NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT online_user_pkey PRIMARY KEY (cpf)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.online_user
    OWNER to postgres;