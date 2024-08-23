-- object: public."tipoPerfil" | type: TYPE --
-- DROP TYPE IF EXISTS public."tipoPerfil" CASCADE;
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
CREATE TYPE public."tipoPerfil" AS
ENUM ('administrador','atendente', 'entregador');
-- ddl-end --
ALTER TYPE public."tipoPerfil" OWNER TO postgres;
-- ddl-end --

-- object: public.status | type: TYPE --
-- DROP TYPE IF EXISTS public.status CASCADE;
CREATE TYPE public.status AS
ENUM ('Em preparação','Saiu para Entrega','Entregue');
-- ddl-end --
ALTER TYPE public.status OWNER TO postgres;
-- ddl-end --

-- object: public.cliente | type: TABLE --
-- DROP TABLE IF EXISTS public.cliente CASCADE;
CREATE TABLE public.cliente (
	id_cliente smallint NOT NULL,
	nome varchar(1024),
	logradouro varchar(1024),
	bairro varchar(256),
	telefone varchar(18),
	CNPJ_cliente varchar(32),
	CPF_cliente varchar(32),
	id_empresa_empresa smallint NOT NULL,
	CONSTRAINT cliente_pk PRIMARY KEY (id_cliente)
);
-- ddl-end --
ALTER TABLE public.cliente OWNER TO postgres;
-- ddl-end --

-- object: public.pedido | type: TABLE --
-- DROP TABLE IF EXISTS public.pedido CASCADE;
CREATE TABLE public.pedido (
	id_pedido smallint NOT NULL,
	data_pedido date,
	valor_total double precision,
	id_cliente_cliente smallint NOT NULL,
	CONSTRAINT pedido_pk PRIMARY KEY (id_pedido)
);
-- ddl-end --
ALTER TABLE public.pedido OWNER TO postgres;
-- ddl-end --

-- object: cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.pedido DROP CONSTRAINT IF EXISTS cliente_fk CASCADE;
ALTER TABLE public.pedido ADD CONSTRAINT cliente_fk FOREIGN KEY (id_cliente_cliente)
REFERENCES public.cliente (id_cliente) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."ItemPedido" | type: TABLE --
-- DROP TABLE IF EXISTS public."ItemPedido" CASCADE;
CREATE TABLE public."ItemPedido" (
	valor_unitario double precision,
	id_item smallint NOT NULL,
	quantidade_item integer,
	id_pedido_pedido smallint NOT NULL,
	CONSTRAINT "ItemPedido_pk" PRIMARY KEY (id_item)
);
-- ddl-end --
ALTER TABLE public."ItemPedido" OWNER TO postgres;
-- ddl-end --

-- object: pedido_fk | type: CONSTRAINT --
-- ALTER TABLE public."ItemPedido" DROP CONSTRAINT IF EXISTS pedido_fk CASCADE;
ALTER TABLE public."ItemPedido" ADD CONSTRAINT pedido_fk FOREIGN KEY (id_pedido_pedido)
REFERENCES public.pedido (id_pedido) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.produto | type: TABLE --
-- DROP TABLE IF EXISTS public.produto CASCADE;
CREATE TABLE public.produto (
	id_produto smallint NOT NULL,
	nome_produto varchar(1024),
	localizacao_produto varchar(1024),
	"id_item_ItemPedido" smallint NOT NULL,
	CONSTRAINT produto_pk PRIMARY KEY (id_produto)
);
-- ddl-end --
ALTER TABLE public.produto OWNER TO postgres;
-- ddl-end --

-- object: "ItemPedido_fk" | type: CONSTRAINT --
-- ALTER TABLE public.produto DROP CONSTRAINT IF EXISTS "ItemPedido_fk" CASCADE;
ALTER TABLE public.produto ADD CONSTRAINT "ItemPedido_fk" FOREIGN KEY ("id_item_ItemPedido")
REFERENCES public."ItemPedido" (id_item) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.empresa | type: TABLE --
-- DROP TABLE IF EXISTS public.empresa CASCADE;
CREATE TABLE public.empresa (
	id_empresa smallint NOT NULL,
	nome varchar(1024),
	cnpj varchar(32),
	CONSTRAINT empresa_pk PRIMARY KEY (id_empresa)
);
-- ddl-end --
ALTER TABLE public.empresa OWNER TO postgres;
-- ddl-end --

-- object: empresa_fk | type: CONSTRAINT --
-- ALTER TABLE public.cliente DROP CONSTRAINT IF EXISTS empresa_fk CASCADE;
ALTER TABLE public.cliente ADD CONSTRAINT empresa_fk FOREIGN KEY (id_empresa_empresa)
REFERENCES public.empresa (id_empresa) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.funcionario | type: TABLE --
-- DROP TABLE IF EXISTS public.funcionario CASCADE;
CREATE TABLE public.funcionario (
	id_funcionario smallint NOT NULL,
	nome varchar(1024),
	senha varchar(16384),
	telefone varchar(32),
	id_empresa_empresa smallint NOT NULL,
	CONSTRAINT funcionario_pk PRIMARY KEY (id_funcionario)
);
-- ddl-end --
ALTER TABLE public.funcionario OWNER TO postgres;
-- ddl-end --

-- object: empresa_fk | type: CONSTRAINT --
-- ALTER TABLE public.funcionario DROP CONSTRAINT IF EXISTS empresa_fk CASCADE;
ALTER TABLE public.funcionario ADD CONSTRAINT empresa_fk FOREIGN KEY (id_empresa_empresa)
REFERENCES public.empresa (id_empresa) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public.perfil | type: TABLE --
-- DROP TABLE IF EXISTS public.perfil CASCADE;
CREATE TABLE public.perfil (
    id_perfil smallint NOT NULL,  -- Correção aqui
	tipo_perfil smallint,
    id_funcionario_funcionario smallint NOT NULL,
    CONSTRAINT perfil_pk PRIMARY KEY (id_perfil)
);
-- ddl-end --
ALTER TABLE public.perfil OWNER TO postgres;
-- ddl-end --

-- object: funcionario_fk | type: CONSTRAINT --
-- ALTER TABLE public.perfil DROP CONSTRAINT IF EXISTS funcionario_fk CASCADE;
ALTER TABLE public.perfil ADD CONSTRAINT funcionario_fk FOREIGN KEY (id_funcionario_funcionario)
REFERENCES public.funcionario (id_funcionario) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;

-- ddl-end --
