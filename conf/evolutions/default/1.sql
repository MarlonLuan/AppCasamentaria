# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cliente (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  data_nascimento           datetime(6),
  email                     varchar(255),
  apelido                   varchar(255),
  cpf                       varchar(255),
  rg                        varchar(255),
  constraint pk_cliente primary key (id))
;

create table endereco (
  id                        bigint auto_increment not null,
  logradouro                varchar(255),
  numero                    integer,
  complemento               varchar(255),
  cep                       varchar(255),
  bairro                    varchar(255),
  cidade                    varchar(255),
  uf                        varchar(255),
  cliente_id                bigint,
  constraint pk_endereco primary key (id))
;

create table entrega (
  id                        bigint auto_increment not null,
  data                      datetime(6),
  status                    varchar(255),
  cliente_id                bigint,
  constraint pk_entrega primary key (id))
;

create table imagem (
  id                        bigint auto_increment not null,
  foto                      varbinary(255),
  produto_id                bigint,
  constraint pk_imagem primary key (id))
;

create table pagamento (
  id                        bigint auto_increment not null,
  ativo                     tinyint(1) default 0,
  constraint pk_pagamento primary key (id))
;

create table pedido (
  id                        bigint auto_increment not null,
  valor                     double,
  data                      datetime(6),
  status                    varchar(255),
  cliente_id                bigint,
  pagamento_id              bigint,
  constraint pk_pedido primary key (id))
;

create table produto (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  descricao                 varchar(255),
  peso                      float,
  tamanho                   float,
  preco                     float,
  pedido_id                 bigint,
  constraint pk_produto primary key (id))
;

create table telefone (
  id                        bigint auto_increment not null,
  ddd                       varchar(255),
  numero                    varchar(255),
  cliente_id                bigint,
  constraint pk_telefone primary key (id))
;

alter table endereco add constraint fk_endereco_cliente_1 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_endereco_cliente_1 on endereco (cliente_id);
alter table entrega add constraint fk_entrega_cliente_2 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_entrega_cliente_2 on entrega (cliente_id);
alter table imagem add constraint fk_imagem_produto_3 foreign key (produto_id) references produto (id) on delete restrict on update restrict;
create index ix_imagem_produto_3 on imagem (produto_id);
alter table pedido add constraint fk_pedido_cliente_4 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_pedido_cliente_4 on pedido (cliente_id);
alter table pedido add constraint fk_pedido_pagamento_5 foreign key (pagamento_id) references pagamento (id) on delete restrict on update restrict;
create index ix_pedido_pagamento_5 on pedido (pagamento_id);
alter table produto add constraint fk_produto_pedido_6 foreign key (pedido_id) references pedido (id) on delete restrict on update restrict;
create index ix_produto_pedido_6 on produto (pedido_id);
alter table telefone add constraint fk_telefone_cliente_7 foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_telefone_cliente_7 on telefone (cliente_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table cliente;

drop table endereco;

drop table entrega;

drop table imagem;

drop table pagamento;

drop table pedido;

drop table produto;

drop table telefone;

SET FOREIGN_KEY_CHECKS=1;

