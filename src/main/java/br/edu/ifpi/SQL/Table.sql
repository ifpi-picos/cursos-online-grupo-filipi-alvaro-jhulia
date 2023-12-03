CREATE DATABASE  IF NOT EXISTS `sistema_academico` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sistema_academico`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_academico
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aluno` (
`id` bigint NOT NULL AUTO_INCREMENT,
`nome` varchar(255) NOT NULL,
`email` varchar(255) NOT NULL,
`status` char(7) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'carlos henrique ','carlosHenrique@gmail.com','ATIVO'),(2,'victor','victor@gmail.com','INATIVO'),(3,'jhulia','jhulia@gmail.com','INATIVO'),(4,'antonio','antonio@gmail.com','ATIVO'),(5,'gabriel','gabriel@gmail.com','ATIVO'),(6,'Victor','Victor@gmail.com','INATIVO'),(7,'maykon','maykon@gmail.com','ATIVO'),(8,'filipi','filipi@gmail.com','ATIVO'),(9,'Maria','luci@gmail.com','ATIVO'),(10,'deborah','deborah@gmail.com','ATIVO'),(11,'deborah','deborah@gmail.com','ATIVO'),(12,'deborah','deborah@gmail.com','ATIVO'),(13,'deborah','deborah@gmail.com','ATIVO'),(14,'deborah','deborah@gmail.com','ATIVO'),(15,'deborah','deborah@gmail.com','ATIVO'),(16,'deborah','deborah@gmail.com','ATIVO'),(17,'deborah','deborah@gmail.com','ATIVO'),(18,'deborah','deborah@gmail.com','ATIVO'),(19,'deborah','deborah@gmail.com','ATIVO'),(20,'deborah','deborah@gmail.com','ATIVO');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
`id` bigint NOT NULL AUTO_INCREMENT,
`nome` varchar(255) NOT NULL,
`carga_horaria` int DEFAULT '60',
`status` enum('Aberto','Fechado') DEFAULT 'Aberto',
`professor_id` bigint DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'Letras',100,'Aberto',4),(2,'Historia',40,'Aberto',2),(3,'Matemática',100,'Aberto',2),(4,'Matemática',100,'Aberto',2);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matricula` (
`id` bigint NOT NULL AUTO_INCREMENT,
`curso_id` bigint NOT NULL,
`aluno_id` bigint NOT NULL,
`status` enum('Ativa','Cancelada') DEFAULT 'Ativa',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
INSERT INTO `matricula` VALUES (1,1,10,'Cancelada'),(2,1,11,'Ativa'),(3,1,11,'Ativa'),(4,1,10,'Ativa'),(5,1,11,'Ativa'),(6,1,10,'Ativa'),(7,1,11,'Ativa'),(8,1,10,'Ativa'),(9,1,11,'Ativa'),(10,1,10,'Ativa'),(11,1,11,'Ativa'),(12,1,11,'Ativa'),(13,1,11,'Ativa'),(14,1,11,'Ativa');
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nota` (
`id` bigint NOT NULL AUTO_INCREMENT,
`nota` int NOT NULL,
`curso_id` bigint NOT NULL,
`aluno_id` bigint NOT NULL,
`status` varchar(20) NOT NULL DEFAULT 'Não Concluído',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (16,10,1,1,'APROVADO'),(17,10,1,1,'APROVADO');
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
`id` bigint NOT NULL AUTO_INCREMENT,
`nome` varchar(255) NOT NULL,
`email` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'maykon','maykon@gmail'),(2,'maykon','maykon@gmail.com'),(3,'rogerio','rogerio@gmail.com'),(4,'David','David@ifpi.edu.br'),(5,'Rafael','Rafael@ifpi.edu.br'),(6,'Rafael','Rafael@ifpi.edu.br'),(7,'Rafael','Rafael@ifpi.edu.br');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-03  1:32:30
