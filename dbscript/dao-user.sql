-- Table: public.dao_user

-- DROP TABLE public.dao_user;

CREATE TABLE public.dao_user
(
    id bigint NOT NULL DEFAULT nextval('dao_user_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    mobile_phone character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT dao_user_pkey PRIMARY KEY (id),
    CONSTRAINT uk_oj6c0r2f56xu3bkriwdfpchjn UNIQUE (mobile_phone)
,
    CONSTRAINT uk_q0aabw53xfrij6j0l91xv595u UNIQUE (email)
,
    CONSTRAINT uk_rvug3s166u0ykih44uhs40cyw UNIQUE (user_name)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.dao_user
    OWNER to postgres;