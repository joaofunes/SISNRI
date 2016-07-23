
--
-- Estructura de tabla para la tabla `ss_historico_claves`
--

CREATE TABLE IF NOT EXISTS `ss_historico_claves` (
  `ID_HISTORICO_CLAVE` DECIMAL(9,0) NOT NULL COMMENT 'ID del histórico de clave.',
  `ID_USUARIO` DECIMAL(9,0) DEFAULT NULL,
  `ID_USUARIO2` DECIMAL(9,0) DEFAULT NULL,
  `FECHA_CLAVE` DATETIME DEFAULT NULL COMMENT 'Fecha en que la clave fue cambiada y movida al histórico.',
  `CLAVE2` VARCHAR(100) DEFAULT NULL COMMENT 'Clave',
  PRIMARY KEY (`ID_HISTORICO_CLAVE`),
  KEY `AK_UK_SS_HISTORICO_CLAVES` (`ID_USUARIO2`,`CLAVE2`),
  KEY `FK_FK_SS_HIST_CLAVES_USUARIOS` (`ID_USUARIO`)
) ENGINE=INNODB DEFAULT CHARSET=latin1 COMMENT='Contiene las claves que ha cambiado el usuario para controla';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_menus`
--

CREATE TABLE IF NOT EXISTS `ss_menus` (
  `ID_MENU` DECIMAL(9,0) NOT NULL COMMENT 'Correlativo que identifica al menú.',
  `SS__ID_MENU` DECIMAL(9,0) DEFAULT NULL COMMENT 'Correlativo que identifica al menú.',
  `NOMBRE_MENU` VARCHAR(100) DEFAULT NULL COMMENT 'Nombre del menú ',
  `USUARIO_REGISTRO` VARCHAR(15) DEFAULT NULL COMMENT 'Usuario que registra la información.',
  `FECHA_REGISTRO` DATETIME DEFAULT NULL COMMENT 'Fecha y hora en que se registra información',
  `USUARIO_ULTIMAMODIFICACION` VARCHAR(15) DEFAULT NULL COMMENT 'Último usuario que modificó la información.',
  `FECHA_ULTIMAMODIFICACION` DATETIME DEFAULT NULL COMMENT 'Fecha y hora de última modificación de información.',
  PRIMARY KEY (`ID_MENU`),
  KEY `FK_FK_SS_MENUS_MENU_PADRE` (`SS__ID_MENU`)
) ENGINE=INNODB DEFAULT CHARSET=latin1 COMMENT='Contiene los diferentes menus de las aplicaciones';

--
-- Volcado de datos para la tabla `ss_menus`
--

INSERT INTO `ss_menus` (`ID_MENU`, `SS__ID_MENU`, `NOMBRE_MENU`, `USUARIO_REGISTRO`, `FECHA_REGISTRO`, `USUARIO_ULTIMAMODIFICACION`, `FECHA_ULTIMAMODIFICACION`) VALUES
('1', NULL, 'Indice Encarrilamiento', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('2', NULL, 'Gastos por Reparación ', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('3', NULL, 'Gastos por Depreciación', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('4', NULL, 'Vida Útil', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('5', NULL, 'Comparativo de Gastos', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('6', NULL, 'Tasa de Éxito/Fallo', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('7', NULL, 'Historial', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('8', NULL, 'Proyección Tecnico/Tiempo ', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('9', NULL, 'Reparación vrs Tiempo', 'desarrollo', '2015-05-09 00:00:00', NULL, NULL),
('10', NULL, 'Mantenimientos', 'desarrollo', '2015-06-05 00:00:00', NULL, NULL),
('11', NULL, 'Exportaciones', 'desarrollo', '2015-06-05 00:00:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_menus_opciones`
--

CREATE TABLE IF NOT EXISTS `ss_menus_opciones` (
  `ID_MENU` DECIMAL(9,0) NOT NULL COMMENT 'Correlativo que identifica al menú.',
  `ID_OPCION` DECIMAL(9,0) NOT NULL COMMENT 'Correlativo que identifica a la opción.',
  PRIMARY KEY (`ID_MENU`,`ID_OPCION`),
  KEY `FK_FK_SS_OPCIONES_MENUS` (`ID_OPCION`)
) ENGINE=INNODB DEFAULT CHARSET=latin1 COMMENT='Contiene la relacion n:n entre menus y opciones (de menú).';

--
-- Volcado de datos para la tabla `ss_menus_opciones`
--

INSERT INTO `ss_menus_opciones` (`ID_MENU`, `ID_OPCION`) VALUES
('1', '2'),
('2', '3'),
('3', '4'),
('4', '5'),
('5', '6'),
('6', '7'),
('7', '8'),
('8', '9'),
('9', '10'),
('10', '11'),
('11', '11'),
('11', '12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_opciones`
--

CREATE TABLE IF NOT EXISTS `ss_opciones` (
  `ID_OPCION` DECIMAL(9,0) NOT NULL COMMENT 'Correlativo que identifica a la opción.',
  `NOMBRE_OPCION` VARCHAR(100) DEFAULT NULL COMMENT 'Nombre de la opción',
  `URL` VARCHAR(300) DEFAULT NULL COMMENT 'URL de ubicación de la página de la opción.',
  `VISIBLE` VARCHAR(1) DEFAULT '1' COMMENT 'Indica si la opción se muestra o no. (Si tiene un 1 = SI, Si tiene un 0 = NO, default=0)',
  `USUARIO_REGISTRO` VARCHAR(15) DEFAULT NULL COMMENT 'Usuario que registra la información.',
  `FECHA_REGISTRO` DATETIME DEFAULT NULL COMMENT 'Fecha y hora en que se registra información',
  `USUARIO_ULTIMAMODIFICACION` VARCHAR(15) DEFAULT NULL COMMENT 'Último usuario que modificó la información.',
  `FECHA_ULTIMAMODIFICACION` DATETIME DEFAULT NULL COMMENT 'Fecha y hora de última modificación de información.',
  `IMAGEN_OPCION` VARCHAR(45) DEFAULT NULL,
  PRIMARY KEY (`ID_OPCION`)
) ENGINE=INNODB DEFAULT CHARSET=latin1 COMMENT='Contiene las diferentes opciones de los menús de las aplicac';

--
-- Volcado de datos para la tabla `ss_opciones`
--

INSERT INTO `ss_opciones` (`ID_OPCION`, `NOMBRE_OPCION`, `URL`, `VISIBLE`, `USUARIO_REGISTRO`, `FECHA_REGISTRO`, `USUARIO_ULTIMAMODIFICACION`, `FECHA_ULTIMAMODIFICACION`, `IMAGEN_OPCION`) VALUES
('1', 'Home', '/siapa/views/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('2', 'Índice de encarrilamiento empresarial', '/dimesa/views/IndiceEncarrilamiento/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('3', 'Índice/Promedio de Gasto por Reparación de equipo por Area', '/dimesa/views/IndicePromedioGastoPorReparacion/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('4', 'Índice/Promedio de Gasto por Depreciación de equipo por Area', '/dimesa/views/IndicePromedioGastoPorDepreciacion/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('5', 'Comparativo de Tiempo de Vida Util entre los Equipos', '/dimesa/views/ComparativoTiempoDeVidaUtil/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('6', 'Comparativo de Gastos en Reparación entre Equipos en un Periodos ', '/dimesa/views/comparativodegastosreparacion/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('7', 'Tasa de Exito/Fallo en Reparaciones', '/dimesa/views/TasaExitoFalloReparacion/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('8', 'Resumen de Reparación Efectuada por Técnicos no Subcontratados', '/dimesa/views/ResumenHistorialReparacionTecnicoNoSubcontratados/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('9', 'Proyección Tecnico/Tiempo Requerido para Dolventar un Inconveniente', '/dimesa/views/ProyeccionTecnicoTiempo/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('10', 'Proyección de Efectividad de Reparación vrs Tiempo', '/dimesa/views/ProyeccionEfectividadReparacionTiempo/index.xhtml  ', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('11', 'Proyección y Programación de Mantenimientos Preventivos', '/dimesa/views/ProyeccionProgramacionMantenimientosPreventivos/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('12', 'Exportación de Procesos de Proyección y Tazas a Formato Matriz', '/dimesa/views/ExportacionDeProcesos/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('13', 'pantalla12', '/dimesa/views/common/Print.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL),
('14', 'pantalla13', '/siapa/views/alimento/index.xhtml', 'S', 'desarrollo', '2014-11-01 00:00:00', NULL, NULL, NULL),
('15', 'pantalla14', '/siapa/views/proveedor/index.xhtml', 'S', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_roles`
--

CREATE TABLE IF NOT EXISTS `ss_roles` (
  `ID_ROL` DECIMAL(9,0) NOT NULL COMMENT 'Identificador único del rol',
  `CODIGO_ROL` VARCHAR(50) DEFAULT NULL COMMENT 'Código que identifica al rol.',
  `NOMBRE_ROL` VARCHAR(50) DEFAULT NULL COMMENT 'Nombre del Rol',
  `DESCRIPCION` VARCHAR(150) DEFAULT NULL COMMENT 'Propiedades o usos que identifican al rol',
  `USUARIO_REGISTRO` VARCHAR(15) DEFAULT NULL COMMENT 'Usuario que registra la información.',
  `FECHA_REGISTRO` DATETIME DEFAULT NULL COMMENT 'Fecha y hora en que se registra información',
  `USUARIO_ULTIMAMODIFICACION` VARCHAR(15) DEFAULT NULL COMMENT 'Último usuario que modificó la información.',
  `FECHA_ULTIMAMODIFICACION` DATETIME DEFAULT NULL COMMENT 'Fecha y hora de última modificación de información.',
  PRIMARY KEY (`ID_ROL`),
  KEY `AK_UK_SS_ROLES_CODIGO_ROL` (`CODIGO_ROL`)
) ENGINE=INNODB DEFAULT CHARSET=latin1 COMMENT='Contiene los diferentes niveles de acceso que un usuario pue';

--
-- Volcado de datos para la tabla `ss_roles`
--

INSERT INTO `ss_roles` (`ID_ROL`, `CODIGO_ROL`, `NOMBRE_ROL`, `DESCRIPCION`, `USUARIO_REGISTRO`, `FECHA_REGISTRO`, `USUARIO_ULTIMAMODIFICACION`, `FECHA_ULTIMAMODIFICACION`) VALUES
('1', 'EOP', 'Encargado Operativo', 'Posee privilegios ..', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('2', 'EAD', 'Encargado Administrativo', 'Posee Privilegios de ...', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('3', 'VEN', 'Vendedor', 'Encargado de registrar las ventas ', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('4', 'OPE', 'Operario', 'Encargado de Realizar ...', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('5', 'ADM', 'Administrador del Sistema', 'Tiene todos los provilegios de gestion de usuario y otras tareas de adminitracion del sistema', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('6', 'ASC', 'Asociado', 'puede ver los reportes que se generan desde el sistema', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL),
('7', 'RT_ESTRA', 'Usuario Estrategico', 'Usuario Estrategico', 'desa', '2015-06-01 00:00:00', NULL, NULL),
('8', 'RS_TACT', 'Usuario Tactico', 'Usuario Tactico', 'desa', '2015-06-01 00:00:00', NULL, NULL),
('10', 'joao.funes', 'Joao Funes', 'desarrollo', 'desa', '2015-06-01 00:00:00', NULL, NULL),
('11', 'henry.cortez', 'Henry Cortez', 'desarrollo', 'desa', '2015-06-01 00:00:00', NULL, NULL),
('12', 'marcos.barrera', 'Marcos Barrera', 'desarrollo', 'desa', '2015-06-01 00:00:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_roles_menu`
--

CREATE TABLE IF NOT EXISTS `ss_roles_menu` (
  `ID_MENU` DECIMAL(9,0) NOT NULL COMMENT 'Correlativo que identifica al menú.',
  `ID_ROL` DECIMAL(9,0) NOT NULL COMMENT 'Identificador único del rol',
  PRIMARY KEY (`ID_MENU`,`ID_ROL`),
  KEY `FK_SS_ROLES_MENU2` (`ID_ROL`)
) ENGINE=INNODB DEFAULT CHARSET=latin1 COMMENT='Contiene la relacion n:n entre roles y menus';

--
-- Volcado de datos para la tabla `ss_roles_menu`
--

INSERT INTO `ss_roles_menu` (`ID_MENU`, `ID_ROL`) VALUES
('3', '1'),
('4', '1'),
('5', '1'),
('6', '1'),
('8', '1'),
('1', '2'),
('2', '2'),
('3', '2'),
('4', '2'),
('7', '2'),
('1', '3'),
('8', '3'),
('3', '4'),
('1', '5'),
('2', '5'),
('3', '5'),
('4', '5'),
('5', '5'),
('6', '5'),
('7', '5'),
('8', '5'),
('9', '5'),
('10', '5'),
('11', '5'),
('2', '6'),
('6', '6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_roles_opciones`
--

CREATE TABLE IF NOT EXISTS `ss_roles_opciones` (
  `ID_ROL` DECIMAL(9,0) NOT NULL COMMENT 'Identificador único del rol',
  `ID_OPCION` DECIMAL(9,0) NOT NULL COMMENT 'Correlativo que identifica a la opción.',
  PRIMARY KEY (`ID_ROL`,`ID_OPCION`),
  KEY `FK_SS_ROLES_OPCIONES2` (`ID_OPCION`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ss_roles_opciones`
--

INSERT INTO `ss_roles_opciones` (`ID_ROL`, `ID_OPCION`) VALUES
('1', '1'),
('2', '1'),
('3', '1'),
('4', '1'),
('5', '1'),
('6', '1'),
('2', '2'),
('3', '2'),
('5', '2'),
('2', '3'),
('3', '3'),
('5', '3'),
('2', '4'),
('5', '4'),
('6', '4'),
('4', '5'),
('5', '5'),
('1', '6'),
('5', '6'),
('4', '7'),
('5', '7'),
('1', '8'),
('2', '8'),
('5', '8'),
('1', '9'),
('5', '9'),
('1', '10'),
('2', '10'),
('5', '10'),
('1', '11'),
('5', '11'),
('1', '12'),
('5', '12'),
('3', '13'),
('5', '13'),
('1', '14'),
('5', '14'),
('2', '15'),
('5', '15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_roles_usuarios`
--

CREATE TABLE IF NOT EXISTS `ss_roles_usuarios` (
  `ID_ROL` DECIMAL(9,0) NOT NULL COMMENT 'Identificador único del rol',
  `ID_USUARIO` DECIMAL(9,0) NOT NULL,
  PRIMARY KEY (`ID_ROL`,`ID_USUARIO`),
  KEY `FK_SS_ROLES_USUARIOS2` (`ID_USUARIO`)
) ENGINE=INNODB DEFAULT CHARSET=latin1 COMMENT='Contiene la relación n:n entre roles y usuarios';

--
-- Volcado de datos para la tabla `ss_roles_usuarios`
--

INSERT INTO `ss_roles_usuarios` (`ID_ROL`, `ID_USUARIO`) VALUES
('1', '1'),
('2', '2'),
('3', '3'),
('4', '4'),
('5', '5'),
('6', '6'),
('7', '7'),
('8', '8'),
('10', '10'),
('11', '11'),
('12', '12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ss_usuarios`
--

CREATE TABLE IF NOT EXISTS `ss_usuarios` (
  `ID_USUARIO` DECIMAL(9,0) NOT NULL,
  `CODIGO_USUARIO` VARCHAR(15) DEFAULT NULL,
  `NOMBRE_USUARIO` VARCHAR(100) DEFAULT NULL,
  `TELEFONO` VARCHAR(20) DEFAULT NULL,
  `EMAIL` VARCHAR(100) DEFAULT NULL,
  `CARGO` VARCHAR(100) DEFAULT NULL,
  `DESCRIPCION` VARCHAR(150) DEFAULT NULL COMMENT 'Propiedades o usos que identifican al rol',
  `BLOQUEADO` VARCHAR(1) DEFAULT '0',
  `CLAVE` VARCHAR(100) DEFAULT NULL,
  `INTENTOS_ACCESO_FALLIDOS` DECIMAL(3,0) DEFAULT '0',
  `USUARIO_REGISTRO` VARCHAR(15) DEFAULT NULL COMMENT 'Usuario que registra la información.',
  `FECHA_REGISTRO` DATETIME DEFAULT NULL COMMENT 'Fecha y hora en que se registra información',
  `USUARIO_ULTIMAMODIFICACION` VARCHAR(15) DEFAULT NULL COMMENT 'Último usuario que modificó la información.',
  `FECHA_ULTIMAMODIFICACION` DATETIME DEFAULT NULL COMMENT 'Fecha y hora de última modificación de información.',
  `FECHA_ULTIMO_ACCESO` DATETIME DEFAULT NULL,
  `DIRECCION_ACCESO` VARCHAR(100) DEFAULT NULL,
  `DETALLE_ULTIMO_ACCESO` VARCHAR(300) DEFAULT NULL,
  `FECHA_CAMBIO_CLAVE` DATETIME DEFAULT NULL,
  `CODIGO_SUCURSAL` VARCHAR(2) DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ss_usuarios`
--

INSERT INTO `ss_usuarios` (`ID_USUARIO`, `CODIGO_USUARIO`, `NOMBRE_USUARIO`, `TELEFONO`, `EMAIL`, `CARGO`, `DESCRIPCION`, `BLOQUEADO`, `CLAVE`, `INTENTOS_ACCESO_FALLIDOS`, `USUARIO_REGISTRO`, `FECHA_REGISTRO`, `USUARIO_ULTIMAMODIFICACION`, `FECHA_ULTIMAMODIFICACION`, `FECHA_ULTIMO_ACCESO`, `DIRECCION_ACCESO`, `DETALLE_ULTIMO_ACCESO`, `FECHA_CAMBIO_CLAVE`, `CODIGO_SUCURSAL`) VALUES
('1', 'EOP', 'Encargado Operativo', '78421829', 'siapa@gmail.com', 'Encargado Operativo', NULL, 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2', 'EAD', 'Encargado Administrativo', '78421829', 'siapa@gmail.com', 'Encargado Administrativo', NULL, 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('3', 'VEN', 'Vendedor', '78421829', 'siapa@gmail.com', 'Vendedor', NULL, 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('4', 'OPE', 'Operario', '78421829', 'siapa@gmail.com', 'Operario', NULL, 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('5', 'ADM', 'Administrador del Sistema', '78421829', 'siapa@gmail.com', 'Administrador del Sistema', NULL, 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('6', 'ASC', 'Asociado', '78421829', 'siapa@gmail.com', 'Asociado', NULL, 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desarrollo', '2014-10-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('7', 'RT_ESTRA', 'Usuario Estrategico', '7892-2310', 'estrar@dimesa.com', 'Gerente', 'Usuario Estrategico solamnete podra ver pantallas para su rol', 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desa', '2015-06-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8', 'RS_TACT', 'Usuario Tactico', '7498-2356', 'tactico@dimesa.com', 'Gerente Tactico', 'Gerente Tactico', 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desa', '2015-06-02 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('10', 'joao.funes', 'Joao Funes', '7689-5896', 'joao@gmail.com', 'desarrollo', 'implemetacion SIG', 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desa', '2015-06-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('11', 'henry.cortez', 'Henry Cortez', '7171-2589', 'henry@gmail.com', 'desarrollo', 'implementacion SIG', 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desa', '2015-06-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('12', 'marcos.barrera', 'Marcos Barrera ', '7589-9632', 'marcos@gmail.com', 'desarrollo', 'desarrollo', 'N', 'e10adc3949ba59abbe56e057f20f883e', '0', 'desa', '2015-06-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------


