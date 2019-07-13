-- Table: public.bank

-- DROP TABLE public.bank;

CREATE TABLE public.bank
(
    id bigint NOT NULL,
    creation_date timestamp without time zone,
    last_modification_date timestamp without time zone,
    ifsc character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address jsonb,
    bank_id bigint,
    bank_name character varying(255) COLLATE pg_catalog."default",
    branch character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT bank_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6em9grnt5kprra9yvfl4j1nx1 UNIQUE (ifsc)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bank
    OWNER to postgres;