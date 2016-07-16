
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     16/7/2016 3:31:17 p. m.                      */
/*==============================================================*/


drop table if exists CATEGORIA_NOTICIA;

drop table if exists CONVENIO;

drop table if exists CONVENIO_ESTADO;

drop table if exists DOCUMENTO;

drop table if exists ESCUELA;

drop table if exists ESTADO;

drop table if exists FACULTAD;

drop table if exists NOTICIA;

drop table if exists ORGANISMO;

drop table if exists PAIS;

drop table if exists PERSONA;

drop table if exists PROPUESTA;

drop table if exists PROPUESTA_ESTADO;

drop table if exists PROYECTO;

drop table if exists PROYECTO_ESTADO;

drop table if exists REGION;

drop table if exists TIPO_DOCUMENTO;

drop table if exists TIPO_ORGANISMO;

drop table if exists TIPO_PROYECTO;

drop table if exists UBICACION;

drop table if exists UNIVERSIDAD;

/*==============================================================*/
/* Table: CATEGORIA_NOTICIA                                     */
/*==============================================================*/
create table CATEGORIA_NOTICIA
(
   ID_CATEGORIA         int not null,
   CATEGORIA_NOTICIA    varchar(100),
   primary key (ID_CATEGORIA)
);

/*==============================================================*/
/* Table: CONVENIO                                              */
/*==============================================================*/
create table CONVENIO
(
   ID_CONVENIO          int not null,
   VIGENCIA             varchar(30),
   primary key (ID_CONVENIO)
);

/*==============================================================*/
/* Table: CONVENIO_ESTADO                                       */
/*==============================================================*/
create table CONVENIO_ESTADO
(
   ID_ESTADO            int not null,
   ID_CONVENIO          int not null,
   primary key (ID_ESTADO, ID_CONVENIO)
);

/*==============================================================*/
/* Table: DOCUMENTO                                             */
/*==============================================================*/
create table DOCUMENTO
(
   ID_DOCUMENTO         int not null,
   ID_PROPUESTA         int,
   ID_TIPO_DOCUMENTO    int,
   ID_CONVENIO          int,
   ID_PROYECTO          int,
   FECHA_RECIBIDO       date,
   DOCUMENTO            longblob,
   primary key (ID_DOCUMENTO)
);

/*==============================================================*/
/* Table: ESCUELA                                               */
/*==============================================================*/
create table ESCUELA
(
   ID_ESCUELA           int not null,
   ID_FACULTAD          int,
   NOMBRE_ESCUELA       varchar(60),
   primary key (ID_ESCUELA)
);

/*==============================================================*/
/* Table: ESTADO                                                */
/*==============================================================*/
create table ESTADO
(
   ID_ESTADO            int not null,
   NOMBRE_ESTADO        varchar(60),
   FECHA_INGRESO_ESTADO date,
   primary key (ID_ESTADO)
);

/*==============================================================*/
/* Table: FACULTAD                                              */
/*==============================================================*/
create table FACULTAD
(
   ID_FACULTAD          int not null,
   ID_UNIVERSIDAD       int,
   NOMBRE_FACULTAD      varchar(60),
   primary key (ID_FACULTAD)
);

/*==============================================================*/
/* Table: NOTICIA                                               */
/*==============================================================*/
create table NOTICIA
(
   ID_NOTICIA           int not null,
   ID_CATEGORIA         int,
   TITULO_NOTICIA       varchar(100),
   FECHA_NOTICIA        date,
   CONTENIDO            varchar(600),
   ESTADO_NOTICIA       bool,
   DESCRIPCION_NOTICIA  varchar(300),
   primary key (ID_NOTICIA)
);

/*==============================================================*/
/* Table: ORGANISMO                                             */
/*==============================================================*/
create table ORGANISMO
(
   ID_ORGANISMO         int not null,
   ID_TIPO_ORGANISMO    int,
   ID_UBICACION         int,
   NOMBRE_ORGANISMO     varchar(100),
   TELEFONO_ORGANISMO   varchar(60),
   FAX_ORANISMO         varchar(60),
   CORREO_ORGANISMO     varchar(60),
   DIRECCION_ORGANISMO  varchar(100),
   primary key (ID_ORGANISMO)
);

/*==============================================================*/
/* Table: PAIS                                                  */
/*==============================================================*/
create table PAIS
(
   ID_PAIS              int not null,
   ID_REGION            int,
   CODIGO_PAIS          varchar(10),
   NOMBRE_PAIS          varchar(60),
   primary key (ID_PAIS)
);

/*==============================================================*/
/* Table: PERSONA                                               */
/*==============================================================*/
create table PERSONA
(
   ID_PERSONA           int not null,
   ID_ORGANISMO         int,
   NOMBRE_PERSONA       varchar(60),
   APELLIDO_PERSONA     varchar(60),
   TELEFONO_PERSONA     varchar(60),
   CORREO_PERSONA       varchar(30),
   DUI_PERSONA          varchar(10),
   TIPO_PERSONA         varchar(60),
   NIT_PERSONA          varchar(17),
   CARGO_PERSONA        varchar(60),
   PASAPORTE            varchar(60),
   primary key (ID_PERSONA)
);

/*==============================================================*/
/* Table: PROPUESTA                                             */
/*==============================================================*/
create table PROPUESTA
(
   ID_PROPUESTA         int not null,
   ID_CONVENIO          int,
   ID_UNIVERSIDAD       int,
   ID_PERSONA           int,
   PER_ID_PERSONA       int,
   PER_ID_PERSONA2      int,
   NOMBRE_PROPUESTA     varchar(100),
   FINALIDAD_PROPUESTA  varchar(300),
   primary key (ID_PROPUESTA)
);

/*==============================================================*/
/* Index: PROPUESTA_PK                                          */
/*==============================================================*/
create unique index PROPUESTA_PK on PROPUESTA
(
   ID_PROPUESTA
);

/*==============================================================*/
/* Table: PROPUESTA_ESTADO                                      */
/*==============================================================*/
create table PROPUESTA_ESTADO
(
   ID_PROPUESTA         int not null,
   ID_ESTADO            int not null,
   primary key (ID_PROPUESTA, ID_ESTADO)
);

/*==============================================================*/
/* Table: PROYECTO                                              */
/*==============================================================*/
create table PROYECTO
(
   ID_PROYECTO          int not null,
   ID_TIPO_PROYECTO     int,
   ID_CONVENIO          int,
   ID_PERSONA           int,
   PER_ID_PERSONA       int,
   NOMBRE_PROYECTO      varchar(300),
   LUGAR_PROYECTO       varchar(300),
   MONTO_PROYECTO       numeric(8,0),
   OBJETIVO_PROYECTO    varchar(300),
   primary key (ID_PROYECTO)
);

/*==============================================================*/
/* Table: PROYECTO_ESTADO                                       */
/*==============================================================*/
create table PROYECTO_ESTADO
(
   ID_ESTADO            int not null,
   ID_PROYECTO          int not null,
   primary key (ID_ESTADO, ID_PROYECTO)
);

/*==============================================================*/
/* Table: REGION                                                */
/*==============================================================*/
create table REGION
(
   ID_REGION            int not null,
   NOMBRE_REGION        varchar(60),
   primary key (ID_REGION)
);

/*==============================================================*/
/* Table: TIPO_DOCUMENTO                                        */
/*==============================================================*/
create table TIPO_DOCUMENTO
(
   ID_TIPO_DOCUMENTO    int not null,
   NOMBRE_DOCUMENTO     varchar(100),
   primary key (ID_TIPO_DOCUMENTO)
);

/*==============================================================*/
/* Table: TIPO_ORGANISMO                                        */
/*==============================================================*/
create table TIPO_ORGANISMO
(
   ID_TIPO_ORGANISMO    int not null,
   NOMBRE_TIPO          varchar(60),
   DESCRIPCION_TIPO     varchar(100),
   primary key (ID_TIPO_ORGANISMO)
);

/*==============================================================*/
/* Table: TIPO_PROYECTO                                         */
/*==============================================================*/
create table TIPO_PROYECTO
(
   ID_TIPO_PROYECTO     int not null,
   NOMBRE_TIPO_PROYECTO varchar(100),
   primary key (ID_TIPO_PROYECTO)
);

/*==============================================================*/
/* Table: UBICACION                                             */
/*==============================================================*/
create table UBICACION
(
   ID_UBICACION         int not null,
   ID_PAIS              int,
   CODIGO_POSTAL        varchar(60),
   CIUDAD               varchar(60),
   CALLE                varchar(100),
   ESTADO_UBICACION     varchar(100),
   primary key (ID_UBICACION)
);

/*==============================================================*/
/* Table: UNIVERSIDAD                                           */
/*==============================================================*/
create table UNIVERSIDAD
(
   ID_UNIVERSIDAD       int not null,
   NOMBRE_UNIVERSIDAD   varchar(60),
   primary key (ID_UNIVERSIDAD)
);

alter table CONVENIO_ESTADO add constraint FK_CONVENIO_ESTADO foreign key (ID_ESTADO)
      references ESTADO (ID_ESTADO) on delete restrict on update restrict;

alter table CONVENIO_ESTADO add constraint FK_CONVENIO_ESTADO2 foreign key (ID_CONVENIO)
      references CONVENIO (ID_CONVENIO) on delete restrict on update restrict;

alter table DOCUMENTO add constraint FK_CONVENIO_TIENE_DOCUMENTO foreign key (ID_CONVENIO)
      references CONVENIO (ID_CONVENIO) on delete restrict on update restrict;

alter table DOCUMENTO add constraint FK_DOCUMENTO_CONTIENE_TIPODOCUMENTO foreign key (ID_TIPO_DOCUMENTO)
      references TIPO_DOCUMENTO (ID_TIPO_DOCUMENTO) on delete restrict on update restrict;

alter table DOCUMENTO add constraint FK_PROPUESTA_RESPALDADA_DOCUMENTO foreign key (ID_PROPUESTA)
      references PROPUESTA (ID_PROPUESTA) on delete restrict on update restrict;

alter table DOCUMENTO add constraint FK_PROYECTO_CONTIENE_DOCUMENTO foreign key (ID_PROYECTO)
      references PROYECTO (ID_PROYECTO) on delete restrict on update restrict;

alter table ESCUELA add constraint FK_PERTENECEN_A foreign key (ID_FACULTAD)
      references FACULTAD (ID_FACULTAD) on delete restrict on update restrict;

alter table FACULTAD add constraint FK_UNIVERSIDAD_CONTINE_FACULTADES foreign key (ID_UNIVERSIDAD)
      references UNIVERSIDAD (ID_UNIVERSIDAD) on delete restrict on update restrict;

alter table NOTICIA add constraint FK_NOTICIA_PERTENECE_A_CATEGORIANOTICIA foreign key (ID_CATEGORIA)
      references CATEGORIA_NOTICIA (ID_CATEGORIA) on delete restrict on update restrict;

alter table ORGANISMO add constraint FK_LOCALIDAD_UBICADA_ORGANISMO foreign key (ID_UBICACION)
      references UBICACION (ID_UBICACION) on delete restrict on update restrict;

alter table ORGANISMO add constraint FK_TIPOORGANISMO_CONTIENE_ORGANISMO foreign key (ID_TIPO_ORGANISMO)
      references TIPO_ORGANISMO (ID_TIPO_ORGANISMO) on delete restrict on update restrict;

alter table PAIS add constraint FK_REGION_CONTIENE_PAIS foreign key (ID_REGION)
      references REGION (ID_REGION) on delete restrict on update restrict;

alter table PERSONA add constraint FK_PERSONA_PERTENECE_ORGANISMO foreign key (ID_ORGANISMO)
      references ORGANISMO (ID_ORGANISMO) on delete restrict on update restrict;

alter table PROPUESTA add constraint FK_PERSONA_PROPUESTA_EXTERNO foreign key (ID_PERSONA)
      references PERSONA (ID_PERSONA) on delete restrict on update restrict;

alter table PROPUESTA add constraint FK_PERSONA_PROPUESTA_INTERNO foreign key (ID_PERSONA)
      references PERSONA (ID_PERSONA) on delete restrict on update restrict;

alter table PROPUESTA add constraint FK_PERSONA_PROPUESTA_SOLICITANTE foreign key (ID_PERSONA)
      references PERSONA (ID_PERSONA) on delete restrict on update restrict;

alter table PROPUESTA add constraint FK_PROPUESTA_GENERA_CONVENIO foreign key (ID_CONVENIO)
      references CONVENIO (ID_CONVENIO) on delete restrict on update restrict;

alter table PROPUESTA add constraint FK_UNIVERSIDAD_REGISTRA_PROPUESTA foreign key (ID_UNIVERSIDAD)
      references UNIVERSIDAD (ID_UNIVERSIDAD) on delete restrict on update restrict;

alter table PROPUESTA_ESTADO add constraint FK_PROPUESTA_ESTADO foreign key (ID_PROPUESTA)
      references PROPUESTA (ID_PROPUESTA) on delete restrict on update restrict;

alter table PROPUESTA_ESTADO add constraint FK_PROPUESTA_ESTADO2 foreign key (ID_ESTADO)
      references ESTADO (ID_ESTADO) on delete restrict on update restrict;

alter table PROYECTO add constraint FK_CONVENIO_ORIGINA_PROYECTO foreign key (ID_CONVENIO)
      references CONVENIO (ID_CONVENIO) on delete restrict on update restrict;

alter table PROYECTO add constraint FK_PERSONA_PROYECTO_EXTERNO foreign key (ID_PERSONA)
      references PERSONA (ID_PERSONA) on delete restrict on update restrict;

alter table PROYECTO add constraint FK_PERSONA_PROYECTO_INTERNO foreign key (ID_PERSONA)
      references PERSONA (ID_PERSONA) on delete restrict on update restrict;

alter table PROYECTO add constraint FK_TIPOPROYECTO_PERTENECE_PROYECTO foreign key (ID_TIPO_PROYECTO)
      references TIPO_PROYECTO (ID_TIPO_PROYECTO) on delete restrict on update restrict;

alter table PROYECTO_ESTADO add constraint FK_PROYECTO_ESTADO foreign key (ID_ESTADO)
      references ESTADO (ID_ESTADO) on delete restrict on update restrict;

alter table PROYECTO_ESTADO add constraint FK_PROYECTO_ESTADO2 foreign key (ID_PROYECTO)
      references PROYECTO (ID_PROYECTO) on delete restrict on update restrict;

alter table UBICACION add constraint FK_PAIS_ESTA_EN_UBICACION foreign key (ID_PAIS)
      references PAIS (ID_PAIS) on delete restrict on update restrict;

/*Seguridad*/

/*Table structure for table `ss_historico_clave` */

DROP TABLE IF EXISTS `ss_historico_clave`;

CREATE TABLE `ss_historico_clave` (
  `ID_HISTORICO_CLAVE` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID del histórico de clave.',
  `ID_USUARIO` int(11) DEFAULT NULL,
  `FECHA_CLAVE` datetime DEFAULT NULL COMMENT 'Fecha en que la clave fue cambiada y movida al histórico.',
  PRIMARY KEY (`ID_HISTORICO_CLAVE`),
  KEY `FK_SS_USUARIO_HISTORICOCLAVE` (`ID_USUARIO`),
  CONSTRAINT `FK_SS_USUARIO_HISTORICOCLAVE` FOREIGN KEY (`ID_USUARIO`) REFERENCES `ss_usuario` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_historico_clave` */

/*Table structure for table `ss_menu` */

DROP TABLE IF EXISTS `ss_menu`;

CREATE TABLE `ss_menu` (
  `ID_MENU` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo que identifica al menú.',
  `SS__ID_MENU` int(11) DEFAULT NULL COMMENT 'Correlativo que identifica al menú.',
  `NOMBRE_MENU` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'Nombre del menú ',
  PRIMARY KEY (`ID_MENU`),
  KEY `FK_FK_SS_MENUS_MENU` (`SS__ID_MENU`),
  CONSTRAINT `FK_FK_SS_MENUS_MENU` FOREIGN KEY (`SS__ID_MENU`) REFERENCES `ss_menu` (`ID_MENU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_menu` */

/*Table structure for table `ss_menu_opcion` */

DROP TABLE IF EXISTS `ss_menu_opcion`;

CREATE TABLE `ss_menu_opcion` (
  `ID_OPCION` int(11) NOT NULL COMMENT 'Correlativo que identifica a la opción.',
  `ID_MENU` int(11) NOT NULL COMMENT 'Correlativo que identifica al menú.',
  PRIMARY KEY (`ID_OPCION`,`ID_MENU`),
  KEY `FK_SS_MENU_OPCION2` (`ID_MENU`),
  CONSTRAINT `FK_SS_MENU_OPCION2` FOREIGN KEY (`ID_MENU`) REFERENCES `ss_menu` (`ID_MENU`),
  CONSTRAINT `FK_SS_MENU_OPCION` FOREIGN KEY (`ID_OPCION`) REFERENCES `ss_opciones` (`ID_OPCION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_menu_opcion` */

/*Table structure for table `ss_opciones` */

DROP TABLE IF EXISTS `ss_opciones`;

CREATE TABLE `ss_opciones` (
  `ID_OPCION` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Correlativo que identifica a la opción.',
  `NOMBRE_OPCION` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'Nombre de la opción',
  `URL` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT 'URL de ubicación de la página de la opción.',
  `VISIBLE` varchar(1) COLLATE utf8_bin DEFAULT '1' COMMENT 'Indica si la opción se muestra o no. (Si tiene un 1 = SI, Si tiene un 0 = NO, default=0)',
  `IMAGEN_OPCION` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_OPCION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_opciones` */

/*Table structure for table `ss_role` */

DROP TABLE IF EXISTS `ss_role`;

CREATE TABLE `ss_role` (
  `ID_ROL` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del rol',
  `CODIGO_ROL` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'Código que identifica al rol.',
  `NOMBRE_ROL` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'Nombre del Rol',
  `DESCRIPCION` varchar(150) COLLATE utf8_bin DEFAULT NULL COMMENT 'Propiedades o usos que identifican al rol',
  PRIMARY KEY (`ID_ROL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_role` */

/*Table structure for table `ss_role_opciones` */

DROP TABLE IF EXISTS `ss_role_opciones`;

CREATE TABLE `ss_role_opciones` (
  `ID_OPCION` int(11) NOT NULL COMMENT 'Correlativo que identifica a la opción.',
  `ID_ROL` int(11) NOT NULL COMMENT 'Identificador único del rol',
  PRIMARY KEY (`ID_OPCION`,`ID_ROL`),
  KEY `FK_SS_ROLE_OPCIONES2` (`ID_ROL`),
  CONSTRAINT `FK_SS_ROLE_OPCIONES2` FOREIGN KEY (`ID_ROL`) REFERENCES `ss_role` (`ID_ROL`),
  CONSTRAINT `FK_SS_ROLE_OPCIONES` FOREIGN KEY (`ID_OPCION`) REFERENCES `ss_opciones` (`ID_OPCION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_role_opciones` */

/*Table structure for table `ss_role_usuario` */

DROP TABLE IF EXISTS `ss_role_usuario`;

CREATE TABLE `ss_role_usuario` (
  `ID_USUARIO` int(11) NOT NULL,
  `ID_ROL` int(11) NOT NULL COMMENT 'Identificador único del rol',
  PRIMARY KEY (`ID_USUARIO`,`ID_ROL`),
  KEY `FK_SS_ROLE_USUARIO2` (`ID_ROL`),
  CONSTRAINT `FK_SS_ROLE_USUARIO2` FOREIGN KEY (`ID_ROL`) REFERENCES `ss_role` (`ID_ROL`),
  CONSTRAINT `FK_SS_ROLE_USUARIO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `ss_usuario` (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_role_usuario` */

/*Table structure for table `ss_roles_menu` */

DROP TABLE IF EXISTS `ss_roles_menu`;

CREATE TABLE `ss_roles_menu` (
  `ID_ROL` int(11) NOT NULL COMMENT 'Identificador único del rol',
  `ID_MENU` int(11) NOT NULL COMMENT 'Correlativo que identifica al menú.',
  PRIMARY KEY (`ID_ROL`,`ID_MENU`),
  KEY `FK_SS_ROLES_MENU2` (`ID_MENU`),
  CONSTRAINT `FK_SS_ROLES_MENU2` FOREIGN KEY (`ID_MENU`) REFERENCES `ss_menu` (`ID_MENU`),
  CONSTRAINT `FK_SS_ROLES_MENU` FOREIGN KEY (`ID_ROL`) REFERENCES `ss_role` (`ID_ROL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ss_roles_menu` */

/*Table structure for table `ss_usuario` */

DROP TABLE IF EXISTS `ss_usuario`;

CREATE TABLE `ss_usuario` (
  `ID_USUARIO` int(11) NOT NULL,
  `CODIGO_USUARIO` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `NOMBRE_USUARIO` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `BLOQUEADO` varchar(1) COLLATE utf8_bin DEFAULT '0',
  `CLAVE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `INTENTOS_ACCESO_FALLIDO` decimal(3,0) DEFAULT '0',
  `USUARIO_REGISTRO` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'Usuario que registra la información.',
  `FECHA_REGISTRO` datetime DEFAULT NULL COMMENT 'Fecha y hora en que se registra información',
  `USUARIO_ULTIMAMODIFICACION` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'Último usuario que modificó la información.',
  `FECHA_ULTIMAMODIFICACION` datetime DEFAULT NULL COMMENT 'Fecha y hora de última modificación de información.',
  `FECHA_ULTIMO_ACCESO` datetime DEFAULT NULL,
  `DIRECCION_ACCESO` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DETALLE_ULTIMO_ACCESO` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `FECHA_CAMBIO_CLAVE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
