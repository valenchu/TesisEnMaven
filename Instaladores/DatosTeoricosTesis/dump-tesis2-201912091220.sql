-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: tesis2
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `altabajapedidos`
--

DROP TABLE IF EXISTS `altabajapedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `altabajapedidos` (
  `idAltaBajaPedido` int(11) NOT NULL COMMENT 'ID para medir alta baja de pedidos',
  `estado` int(2) NOT NULL COMMENT 'El estado en que esta el pedido 1 alta 0 baja',
  PRIMARY KEY (`idAltaBajaPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `altabajapedidos`
--

LOCK TABLES `altabajapedidos` WRITE;
/*!40000 ALTER TABLE `altabajapedidos` DISABLE KEYS */;
INSERT INTO `altabajapedidos` VALUES (0,0),(1,1);
/*!40000 ALTER TABLE `altabajapedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `altabajareg`
--

DROP TABLE IF EXISTS `altabajareg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `altabajareg` (
  `idBajaAlta` int(1) NOT NULL COMMENT 'ID para medir alta baja de registros',
  `altaYbaja` varchar(10) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'El estado en que esta el registro 1 alta 0 baja',
  PRIMARY KEY (`idBajaAlta`),
  KEY `tipo` (`altaYbaja`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `altabajareg`
--

LOCK TABLES `altabajareg` WRITE;
/*!40000 ALTER TABLE `altabajareg` DISABLE KEYS */;
INSERT INTO `altabajareg` VALUES (1,'Alta'),(0,'Baja');
/*!40000 ALTER TABLE `altabajareg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_productos`
--

DROP TABLE IF EXISTS `categoria_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_productos` (
  `id_categoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(200) COLLATE utf8mb4_spanish2_ci NOT NULL,
  `alta_baja_categoria` int(10) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_productos`
--

LOCK TABLES `categoria_productos` WRITE;
/*!40000 ALTER TABLE `categoria_productos` DISABLE KEYS */;
INSERT INTO `categoria_productos` VALUES (1,'Adhesivos',1),(2,'Fantasía',1),(3,'Comercial',1),(4,'Escolar Y Técnico',1),(5,'Papelería',1),(6,'Cajas/Carton',1),(7,'Mochilas',1),(8,'Cuadernos/Carpetas',1),(9,'Juguetería',1),(10,'Broches',1);
/*!40000 ALTER TABLE `categoria_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `idConfig` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID de config para llamarlo',
  `stockbajo` int(10) NOT NULL COMMENT 'Se asienta la cantidad de stock bajo acá',
  PRIMARY KEY (`idConfig`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,60);
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallepedido`
--

DROP TABLE IF EXISTS `detallepedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallepedido` (
  `idDetallePedido` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID único de detalle',
  `IDPedido` int(10) NOT NULL COMMENT 'ID con el que se une a pedido',
  `IDproducto` int(10) NOT NULL COMMENT 'ID con el que se une a producto',
  `descPedido` varchar(200) NOT NULL COMMENT 'Describe breve mente de que esta compuesto el pedido',
  `cantPedido` int(10) NOT NULL COMMENT 'La cantidad a pedir',
  `altabajadetalle` int(11) NOT NULL,
  PRIMARY KEY (`idDetallePedido`),
  KEY `IDPedido` (`IDPedido`),
  KEY `detallepedido_idproducto_idx` (`IDproducto`) USING BTREE,
  CONSTRAINT `detallepedido_ibfk_1` FOREIGN KEY (`IDPedido`) REFERENCES `pedido` (`IDPedido`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepedido`
--

LOCK TABLES `detallepedido` WRITE;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
INSERT INTO `detallepedido` VALUES (1,3,37,'CARTULINA COLOR AMARILLA PASTEL',10,0),(3,4,37,'CARTULINA COLOR AMARILLA PASTEL',10,0),(4,4,38,'CARTULINA COLOR CELESTE PASTEL',10,0),(5,5,39,'CARTULINA COLOR NARANJA PASTEL',10,0),(6,5,43,'CINTA ADHES.CONDOR 12X30',10,0),(7,5,48,'CUAD.CONDOR C/ESP 29,7 T/C 120HJ',10,0),(8,5,49,'CUAD.CONGRESO 16.21 INDICE 50HJ',10,0),(9,6,50,'CUAD.GLORIA C/ESP 16.21 46HJ',10,0),(10,7,52,'CUCHILLA CUTTER CLOVER CHICA',10,0);
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallevta`
--

DROP TABLE IF EXISTS `detallevta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallevta` (
  `idventa_idproducto` int(12) NOT NULL AUTO_INCREMENT COMMENT 'ID en conjunto de detalle venta',
  `idVenta` int(10) NOT NULL COMMENT 'ID con el que se une a Venta',
  `IDproducto` int(20) NOT NULL COMMENT 'ID con el que se une a producto',
  `Descripcion` varchar(200) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'Describe breve mente de que esta compuesta la venta',
  `Cant` int(10) NOT NULL COMMENT 'La cantidad de productos de la venta',
  `PrecioSIVA` double NOT NULL COMMENT 'El precio excluyendo el IVA',
  `oferta` int(10) NOT NULL COMMENT 'Porcentaje de oferta al producto',
  `Importe` double NOT NULL COMMENT 'Importe de la venta',
  `Total` double NOT NULL COMMENT 'Calculo total total de la venta con IVA',
  `Fecha` date NOT NULL COMMENT 'Fecha que se hizo la venta del producto',
  PRIMARY KEY (`idventa_idproducto`),
  KEY `idVenta` (`idVenta`,`IDproducto`),
  KEY `IDproducto` (`IDproducto`),
  CONSTRAINT `detallevta_ibfk_1` FOREIGN KEY (`IDproducto`) REFERENCES `productos` (`IDproducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detallevta_ibfk_2` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`idVenta`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallevta`
--

LOCK TABLES `detallevta` WRITE;
/*!40000 ALTER TABLE `detallevta` DISABLE KEYS */;
INSERT INTO `detallevta` VALUES (1,1,4,'Cartuchera',12,10,0,120,120,'2018-07-16'),(2,2,3,'Bolitas',10,10,0,100,110.5,'2018-07-16'),(3,3,4,'Cartuchera',10,10,0,100,200,'2018-07-16'),(4,4,4,'Cartuchera',20,10,0,200,508.3,'2018-07-16'),(5,4,5,'Lapicera roja',10,6,0,60,508.3,'2018-07-16'),(6,4,6,'Goma',10,20,0,200,508.3,'2018-07-16'),(7,5,3,'Bolitas',10,10,0,100,360,'2018-07-16'),(8,5,5,'Lapicera roja',10,6,0,60,360,'2018-07-16'),(9,5,6,'Goma',10,20,0,200,360,'2018-07-16'),(10,6,3,'Bolitas',10,10,0,100,176.8,'2018-07-16'),(11,6,5,'Lapicera roja',10,6,0,60,176.8,'2018-07-16'),(12,7,4,'Cartuchera',10,10,0,100,200,'2018-07-16'),(13,7,1,'Lapicera bic',10,10,0,100,200,'2018-07-16'),(14,8,4,'Cartuchera',10,10,0,100,160,'2018-07-16'),(15,8,5,'Lapicera roja',10,6,0,60,160,'2018-07-16'),(16,9,5,'Lapicera roja',10,6,0,60,60,'2018-07-16'),(17,10,4,'Cartuchera',10,10,0,100,160,'2018-07-16'),(18,10,5,'Lapicera roja',10,6,0,60,160,'2018-07-16'),(19,11,5,'Lapicera roja',20,6,0,120,420,'2018-07-16'),(20,11,3,'Bolitas',20,10,0,200,420,'2018-07-16'),(21,11,8,'Libro',5,20,0,100,420,'2018-07-16'),(22,12,3,'Bolitas',20,10,0,200,200,'2018-07-16'),(23,13,3,'Bolitas',20,10,0,200,200,'2018-07-16'),(24,14,3,'Bolitas',20,10,0,200,200,'2018-07-17'),(25,15,3,'Bolitas',20,10,0,200,200,'2018-07-17'),(26,16,3,'Bolitas',20,10,0,200,200,'2018-07-17'),(27,17,4,'Cartuchera',10,10,0,100,221,'2018-07-17'),(28,17,3,'Bolitas',10,10,0,100,221,'2018-07-17'),(29,18,4,'Cartuchera',10,10,0,100,331.5,'2018-07-17'),(30,18,3,'Bolitas',20,10,0,200,331.5,'2018-07-17'),(31,19,2,'Tijeras',10,15,0,150,181.5,'2018-07-31'),(32,20,4,'Cartuchera',10,10,0,100,100,'2018-07-31'),(33,21,2,'Tijeras',5,15,0,75,75,'2018-09-03'),(34,22,6,'Goma',10,20,0,200,200,'2018-09-03'),(35,23,6,'Goma',10,20,0,200,200,'2018-09-03'),(36,24,6,'Goma',10,20,0,200,200,'2018-09-03'),(37,25,4,'Cartuchera',10,10,0,100,110.5,'2018-09-03'),(38,26,3,'Bolitas',10,10,0,100,331.5,'2018-09-04'),(39,26,6,'Goma',10,20,0,200,331.5,'2018-09-04'),(40,27,3,'Bolitas',10,10,0,100,100,'2018-09-04'),(41,28,3,'Bolitas',10,10,0,100,100,'2018-09-04'),(42,29,6,'Goma',10,20,0,200,300,'2018-09-04'),(43,29,3,'Bolitas',10,10,0,100,300,'2018-09-04'),(44,30,6,'Goma',10,20,0,200,242,'2018-09-04'),(45,31,6,'Goma',10,20,0,200,300,'2018-09-04'),(46,31,3,'Bolitas',10,10,0,100,300,'2018-09-04'),(47,32,4,'Cartuchera',10,10,0,100,160,'2018-09-04'),(48,32,5,'Lapicera roja',10,6,0,60,160,'2018-09-04'),(49,33,7,'Plasticola',10,10,0,100,150,'2018-09-04'),(50,33,3,'Bolitas',5,10,0,50,150,'2018-09-04'),(51,34,7,'Plasticola',10,10,0,100,300,'2018-09-04'),(52,34,8,'Libro',10,20,0,200,300,'2018-09-04'),(53,35,7,'Plasticola',8,10,0,80,88.4,'2018-09-04'),(54,36,3,'Bolitas',10,10,0,100,300,'2018-09-04'),(55,36,8,'Libro',10,20,0,200,300,'2018-09-04'),(56,37,7,'Plasticola',10,10,0,100,200,'2018-09-04'),(57,37,3,'Bolitas',10,10,0,100,200,'2018-09-04'),(58,38,6,'Goma',10,20,0,200,300,'2018-09-04'),(59,38,3,'Bolitas',10,10,0,100,300,'2018-09-04'),(60,39,3,'Bolitas',10,10,0,100,200,'2018-09-04'),(61,39,4,'Cartuchera',10,10,0,100,200,'2018-09-04'),(62,40,4,'Cartuchera',10,10,0,100,100,'2018-09-04'),(63,41,3,'Bolitas',20,10,0,200,442,'2018-09-05'),(64,41,6,'Goma',10,20,0,200,442,'2018-09-05'),(65,42,3,'Bolitas',10,10,0,100,242,'2018-09-05'),(66,42,4,'Cartuchera',10,10,0,100,242,'2018-09-05'),(67,43,3,'Bolitas',10,10,0,100,200,'2018-09-05'),(68,43,4,'Cartuchera',10,10,0,100,200,'2018-09-05'),(69,44,3,'Bolitas',10,10,0,100,300,'2018-09-05'),(70,44,6,'Goma',10,20,0,200,300,'2018-09-05'),(71,45,3,'Bolitas',10,10,0,100,200,'2018-09-05'),(72,45,4,'Cartuchera',10,10,0,100,200,'2018-09-05'),(73,46,3,'Bolitas',10,10,0,100,200,'2018-09-05'),(74,46,4,'Cartuchera',10,10,0,100,200,'2018-09-05'),(75,47,3,'Bolitas',10,10,0,100,300,'2018-09-05'),(76,47,6,'Goma',10,20,0,200,300,'2018-09-05'),(77,48,3,'Bolitas',10,10,0,100,200,'2018-09-05'),(78,48,4,'Cartuchera',10,10,0,100,200,'2018-09-05'),(79,49,3,'Bolitas',10,10,0,100,200,'2018-09-05'),(80,49,4,'Cartuchera',10,10,0,100,200,'2018-09-05'),(81,50,3,'Bolitas',10,10,0,100,200,'2018-09-05'),(82,50,4,'Cartuchera',10,10,0,100,200,'2018-09-05'),(83,51,3,'Bolitas',10,10,0,100,200,'2018-09-05'),(84,51,4,'Cartuchera',10,10,0,100,200,'2018-09-05'),(85,52,3,'Bolitas',10,10,0,100,193.6,'2018-09-19'),(86,52,5,'Lapicera roja',10,6,0,60,193.6,'2018-09-19'),(87,53,11,'ABACO X5 AZ/VE/RO/AM/BL',10,34,0,340,5311.9,'2018-11-28'),(88,53,13,'ABROCHADORA DINAMIT 21 PINTADA',10,405,0,4050,5311.9,'2018-11-28'),(89,54,11,'ABACO X5 AZ/VE/RO/AM/BL',10,34,0,340,1100,'2019-03-11'),(90,54,16,'BLOCK ARTE A4 72HJ PUNT.COMUN ',10,76,0,760,1100,'2019-03-11'),(91,55,15,'ABROCHADORA DINAMIT 24 B CERRAR BOLSAS',10,840,0,8400,8500,'2019-03-11'),(92,55,4,'Cartuchera',10,10,0,100,8500,'2019-03-11'),(93,56,8,'Libro',10,20,0,200,221,'2019-06-19'),(94,57,7,'Plasticola',1,10,0,10,10.89,'2019-07-01'),(95,58,15,'ABROCHADORA DINAMIT 24 B CERRAR BOLSAS',1,840,0,840,1016.4,'2019-07-03'),(96,59,14,'ABROCHADORA DINAMIT 24 A 24/8 EXT.CHATO',50,751,0,37550,45435.5,'2019-07-03'),(97,63,8,'Libro',10,20,0,200,200,'2019-11-25'),(98,64,7,'Plasticola',10,10,0,100,110.5,'2019-12-02'),(99,65,10,'ABACO X3',10,31,0,310,718.25,'2019-12-02'),(100,65,11,'ABACO X5 AZ/VE/RO/AM/BL',10,34,0,340,718.25,'2019-12-02'),(101,66,17,'BLOCK ARTE A4 72HJ PUNT.ESCOSES ',10,67,0,670,1657.7,'2019-12-03'),(102,66,18,'BLOCK ARTE A4 72HJ PUNT.ONLINE  V',10,70,0,700,1657.7,'2019-12-03');
/*!40000 ALTER TABLE `detallevta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `IDPedido` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID único del pedido',
  `IDProveedor` int(10) NOT NULL COMMENT 'ID con el que se une a proveedor',
  `fechaPedido` datetime NOT NULL COMMENT 'Fecha en la que se realizo el pedido',
  `altaBajaPedido` int(10) NOT NULL COMMENT 'Se le da 1 alta o 0 baja para identificar el estado del pedido',
  PRIMARY KEY (`IDPedido`),
  KEY `idProve` (`IDProveedor`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`IDProveedor`) REFERENCES `proveedor` (`IDProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (2,2,'2019-12-02 20:10:00',1),(3,2,'2019-12-02 20:16:04',1),(4,3,'2019-12-02 20:22:50',1),(5,10,'2019-12-09 08:11:53',1),(6,2,'2019-12-09 08:22:33',1),(7,9,'2019-12-09 08:44:18',1);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `IDproducto` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID único de producto',
  `Descripcio` varchar(400) COLLATE utf8mb4_spanish2_ci DEFAULT NULL COMMENT 'Descripción breve de que tipo de producto es',
  `PrecioSinIVA` double NOT NULL COMMENT 'Precio del producto sin su IVA',
  `PrecioConIVA` double NOT NULL COMMENT 'Precio del producto con el IVA',
  `Cantidad` int(10) NOT NULL COMMENT 'La cantidad de stock de dicho producto',
  `Oferta` int(10) NOT NULL COMMENT 'Si se le aplica una oferta exclusiva a ese producto',
  `altabajaproductos` int(1) NOT NULL COMMENT '0 El producto esta activo y hay cantidad de dicho producto 1 todo lo contrario',
  `altaBajaPedidos` tinyint(1) NOT NULL COMMENT '1 o 0 Si hay pedido se encarga de avisar que hay pedidos pendientes, en el apartado de pedidos',
  `id_categoria` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`IDproducto`),
  KEY `altabajaproductos` (`altabajaproductos`),
  KEY `altaBajaPedidos` (`altaBajaPedidos`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`altabajaproductos`) REFERENCES `altabajareg` (`idBajaAlta`),
  CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_productos` (`id_categoria`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Lapicera bic',10,10,110,0,1,0,1),(2,'Tijeras',15,20,100,0,1,0,1),(3,'Bolitas',10,15,60,0,1,0,1),(4,'Cartuchera a color',10,15,132,0,1,0,1),(5,'Lapicera roja',6,10,100,0,1,0,1),(6,'Goma',20,20,300,0,1,0,1),(7,'Plasticola',10,14,187,0,1,0,1),(8,'Libro',20,25,230,0,1,0,1),(9,'Plastilina de colores',20,30,200,0,1,0,1),(10,'ABACO X3',31,37,90,0,1,0,1),(11,'ABACO X5 AZ/VE/RO/AM/BL',34,41,60,0,1,0,1),(12,'ABRECARTAS MAPED 037400',48,59,88,0,1,0,1),(13,'ABROCHADORA DINAMIT 21 PINTADA',405,490,87,0,1,0,1),(14,'ABROCHADORA DINAMIT 24 A 24/8 EXT.CHATO',751,908,50,0,1,0,1),(15,'ABROCHADORA DINAMIT 24 B CERRAR BOLSAS',840,1018,67,0,1,0,1),(16,'BLOCK ARTE A4 72HJ PUNT.COMUN ',76,92,90,0,1,0,1),(17,'BLOCK ARTE A4 72HJ PUNT.ESCOSES ',67,82,90,0,1,0,1),(18,'BLOCK ARTE A4 72HJ PUNT.ONLINE  V',70,85,90,0,1,0,1),(19,'BLOCK ARTE A5 72HJ PUNT.ESCOSES 48',41,51,100,0,1,0,1),(20,'BLOCK ARTE A5 72HJ PUNT.ESCOSES = 055',41,51,100,0,1,0,1),(21,'BLOCK ARTE A5 72HJ PUNT.ONLINE ',43,52,100,0,1,0,1),(22,'BRILLANTINA 96062',28,35,100,0,1,0,1),(23,'BRILLANTINA 96068 X1 C/U',1,2,100,0,1,0,1),(24,'BRILLANTINA 96068 X20',21,25,100,0,1,0,1),(25,'BRILLANTINA F/ESTRELLA 96068',11,14,100,0,1,0,1),(26,'CARBONICO COXI OF.AZUL 10HJ',82,100,100,0,1,0,1),(27,'CARBONICO COXI OF.NEGRO 10HJ',69,84,100,0,1,0,1),(28,'CARBONICO EUREKA COPIATIVO 100HJ',48,58,100,0,1,0,1),(29,'CARBONICO EUREKA OF.COPY-FILM NEGRO 50H',36,45,100,0,1,0,1),(30,'CARBONICO EUREKA P/LABORES C/U',34,41,100,0,1,0,2),(31,'CARETA ARTESCO HOMBRE G.EVA ',19,23,60,0,1,0,2),(32,'CARETA PLASTICA MUNDIAL C/U',14.9,18.03,60,0,1,0,2),(33,'CARPETA ALFAPLAST ESC.3.40 ',43.66,52.83,60,0,1,0,2),(34,'CARPETA ASAMBLEA A4 2.40 4X4 ',55,66.55,60,0,1,0,2),(35,'CARTULINA BLANCA',2.14,2.59,52,0,1,0,2),(36,'CARTULINA COLOR (M)',3.67,4.44,52,0,1,0,2),(37,'CARTULINA COLOR AMARILLA PASTEL',3.67,4.44,70,0,1,0,2),(38,'CARTULINA COLOR CELESTE PASTEL',3.67,4.44,70,0,1,0,2),(39,'CARTULINA COLOR NARANJA PASTEL',3.67,4.44,50,0,1,0,2),(40,'CHINCHES CLOVER METAL X50',3.86,4.67,60,0,1,0,2),(41,'CINTA ADHES.AJEC 306 PAPEL 12X50',12.96,15.68,50,0,1,0,2),(42,'CINTA ADHES.AUCA 12X50 C/U',7.7,9.32,50,0,1,0,2),(43,'CINTA ADHES.CONDOR 12X30',3.85,4.66,50,0,1,0,2),(44,'COMPAS ALTO IMPACTO',354.57,429.03,50,0,1,0,2),(45,'CORRECTOR MICRO CINTA C/U',19.87,24.04,50,0,1,0,2),(46,'CORRECTOR MICRO CINTA MINI COMPAC',20.24,24.49,50,0,1,0,2),(47,'CORRECTOR MICRO LIQUIDO FRASCO C/U',10.46,12.66,50,0,1,0,2),(48,'CUAD.CONDOR C/ESP 29,7 T/C 120HJ',39.9,48.28,50,0,1,0,2),(49,'CUAD.CONGRESO 16.21 INDICE 50HJ',124.63,150.8,50,0,1,0,2),(50,'CUAD.GLORIA C/ESP 16.21 46HJ',14.14,17.11,50,0,1,0,2),(51,'CUAD.GLORIA C/ESP 16.21 84HJ',25.18,30.47,100,0,1,0,1),(52,'CUCHILLA CUTTER CLOVER CHICA',4.2,5.08,50,0,1,0,1),(53,'CUCHILLA CUTTER LIGGO MEDIANO',8.2,9.02,40,0,1,0,1),(54,'CUCHILLA CUTTER LIGGO GRANDE',9,10,40,0,1,0,1),(55,'DICCIONARIO INTER INGLES-ESPAÑOL',78.56,78.56,40,0,1,0,1),(56,'DICCIONARIO ESTRADA ESCOLAR',86.1,86.1,40,0,1,0,1),(57,'DICCIONARIO NEO DE LA LENGUA ESPAÑOLA',109,109,40,0,1,0,1),(58,'ESCUADRA PIZZINI 20X45 C/BIS.MIL',11.75,14.22,40,0,1,0,1),(59,'ESCUADRA PIZZINI 20X60 C/BIS.MIL',11.66,14.11,40,0,1,0,1),(60,'ESFERAS TELGOPOR N 1 X10',3.05,3.69,40,0,1,0,1),(61,'FIBRA FAB.ESCOLAR BICOLOR X10',87.18,105.49,210,0,1,0,1),(62,'FIBRA FAB.ESCOLAR X6 LARGA FLUO',32.61,39.46,438,0,1,0,1),(63,'FIBRA FAB.METALIZADA X6',62.73,75.9,132,0,1,0,1),(64,'FIBRA SIMBALL BICOLOR X 10 LARGA',63.53,76.87,40,0,1,0,1),(65,'FOLIO AVERY PREMIUN A4 REF.X10',28.87,34.93,40,0,1,0,1),(66,'FOLIO AVERY PREMIUN OF.REF.X10',31.95,38.66,40,0,1,0,1),(68,'Lapicera Bicolor',10,20,100,0,1,0,4);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `IDProveedor` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID único del proveedor',
  `Nombre` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'Nombre del proveedor ',
  `Apellido` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'Apellido del proveedor',
  `DNI` int(10) NOT NULL COMMENT 'DNI del proveedor',
  `Tel` bigint(20) NOT NULL COMMENT 'TEL del proveedor',
  `Email` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'Email del proveedor',
  `dirección` varchar(500) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'Dirección del proveedor',
  PRIMARY KEY (`IDProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Valentin','Cassino',36618123,2634756990,'valenchu_15@hotmail.com','Paso de los andes n° 686'),(2,'Andres','Aleman',35623432,2634456879,'andres_a@gmail.com','San Martin S/N piso 4'),(3,'Alexander','Casanova',36619800,2634764899,'alex_trinca@gmail.com','Avellaneda, esquina n°460'),(4,'Roberto','Frijerio',35440123,2634756891,'ingroberto@gmail.com','Interlante 610 Rosario'),(6,'Julio','Andriso',10318475,2634667881,'jandriso@hotmail.com','Avellaneda 440'),(7,'Andrea','Delarian',25678912,261445571,'Andrea_Delarian@gmail.com','Guaymallen, calle tolosa n° 310'),(8,'Valerico','Casanova',3661823,2634756998,'ebantay123@gmail.com','paso de los angeles 435'),(9,'Alexander','Canigia',36618123,2634756998,'valerico@gmail.com','paso de los andes 686'),(10,'Emilio','Casanova',35458789,2634756987,'valenchu_13@gmail.com','paso de los andes 686'),(11,'Pasorra','Casanova',2536145,142563,'Valerico@gmail.com','paso alcaucil sin numoe'),(12,'Pastor','Quemado',132456789,263475697,'Pastor@gmail.com','paso de alcaucil 362');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `redsocial`
--

DROP TABLE IF EXISTS `redsocial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `redsocial` (
  `idRedSocial` int(5) NOT NULL AUTO_INCREMENT COMMENT 'ID único de las redes',
  `redFacebook` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL COMMENT 'Se guarda la red de Facebook',
  `redInstagram` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL COMMENT 'Se guarda la red de Instagram',
  `redTwitter` varchar(200) COLLATE utf8mb4_spanish_ci NOT NULL COMMENT 'Se guarda la red social de Twitter',
  PRIMARY KEY (`idRedSocial`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `redsocial`
--

LOCK TABLES `redsocial` WRITE;
/*!40000 ALTER TABLE `redsocial` DISABLE KEYS */;
INSERT INTO `redsocial` VALUES (1,'https://www.facebook.com/Valentin.Cassino','https://www.instagram.com/valecassino','https://twitter.com/ValentinCassino');
/*!40000 ALTER TABLE `redsocial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_roles` int(11) NOT NULL COMMENT 'ID no unico de los roles. Este id lo define tipo de usuario',
  `control` int(11) NOT NULL COMMENT '1 tiene acceso a control 2 no',
  `venta` int(11) NOT NULL COMMENT '1 tiene acceso a venta 2 no',
  `stock` int(11) NOT NULL COMMENT '1 tiene acceso a stock 2 no',
  `rol` varchar(100) COLLATE utf8_spanish2_ci NOT NULL COMMENT 'Se almacena el nombre del rol ',
  KEY `id_roles` (`id_roles`),
  CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`id_roles`) REFERENCES `tipodeuser` (`IDtipou`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,1,1,1,'ADMIN'),(2,0,1,1,'EMPLEADO');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodeuser`
--

DROP TABLE IF EXISTS `tipodeuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodeuser` (
  `IDtipou` int(150) NOT NULL AUTO_INCREMENT COMMENT 'Indice principal de tipo de usuario.',
  `privilegio` varchar(150) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'El tipo de autorización que poseerá el usuario.',
  PRIMARY KEY (`IDtipou`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodeuser`
--

LOCK TABLES `tipodeuser` WRITE;
/*!40000 ALTER TABLE `tipodeuser` DISABLE KEYS */;
INSERT INTO `tipodeuser` VALUES (1,'Admin'),(2,'Empleado');
/*!40000 ALTER TABLE `tipodeuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `IDU` bigint(100) NOT NULL AUTO_INCREMENT COMMENT 'ID principal de los usuarios.',
  `user` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'Nombre que define el usuario.',
  `pass` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'Clave que define el usuario',
  `Edad` int(4) NOT NULL COMMENT 'La edad del usuario.',
  `Sexo` varchar(100) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'El sexo del usuario',
  `IDtipou` int(150) NOT NULL COMMENT 'Nivelde autorización del usuario traído de TiposDe usuarios',
  PRIMARY KEY (`IDU`),
  KEY `IDtipou` (`IDtipou`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`IDtipou`) REFERENCES `tipodeuser` (`IDtipou`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','1234',32,'Masculino',1),(2,'valen','1234',25,'Masculino',2),(3,'roberto','12345',29,'Masculino',1),(4,'joako','12345',29,'Masculino',1),(5,'valentin','vc1532469571',27,'Masculino',1),(6,'valerico','12345',20,'Masculino',1),(7,'pedro','123456',35,'Masculino',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `idVenta` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID único de venta',
  `Usuario` varchar(200) COLLATE utf8mb4_spanish2_ci NOT NULL COMMENT 'El usuario que realizo la venta',
  PRIMARY KEY (`idVenta`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES (1,'Admin'),(2,'admin'),(3,'admin'),(4,'admin'),(5,'admin'),(6,'admin'),(7,'admin'),(8,'admin'),(9,'admin'),(10,'admin'),(11,'admin'),(12,'admin'),(13,'admin'),(14,'admin'),(15,'admin'),(16,'admin'),(17,'admin'),(18,'admin'),(19,'admin'),(20,'admin'),(21,'admin'),(22,'admin'),(23,'admin'),(24,'admin'),(25,'admin'),(26,'admin'),(27,'admin'),(28,'admin'),(29,'Admin'),(30,'Admin'),(31,'Admin'),(32,'Admin'),(33,'Admin'),(34,'Admin'),(35,'Admin'),(36,'admin'),(37,'Admin'),(38,'Admin'),(39,'Admin'),(40,'Admin'),(41,'Admin'),(42,'Admin'),(43,'Admin'),(44,'Admin'),(45,'Admin'),(46,'Admin'),(47,'Admin'),(48,'Admin'),(49,'Admin'),(50,'Admin'),(51,'Admin'),(52,'Admin'),(53,'Admin'),(54,'Admin'),(55,'Admin'),(56,'Admin'),(57,'admin'),(58,'Admin'),(59,'Admin'),(60,'Admin'),(61,'Admin'),(62,'Admin'),(63,'Admin'),(64,'admin'),(65,'admin'),(66,'admin');
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tesis2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 12:20:18
