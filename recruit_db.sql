-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: recruit
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `company`
--
USE heroku_bae5303db3cc20e;

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Ba Đình, Hà Nội','badinhcompany@gmail.com','Ba Đình Company','0839999888'),(3,'Hoàng Mai, Hà Nội','maithanhtu@gmail.com','Tu Thanh Auto','0983764534'),(4,'Ba Đình, Hà Nội','badinhcompany@gmail.com','Ba Đình Company','0839999888');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'Tài xế'),(2,'Kế Toán'),(3,'Marketing'),(4,'Lập Trình Viên'),(5,'Tester'),(7,'Giảng Viên');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_job`
--

DROP TABLE IF EXISTS `list_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `list_job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `certification` varchar(255) DEFAULT NULL,
  `contact_info` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `experence` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL,
  `work_address` varchar(255) DEFAULT NULL,
  `job_id` int DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh2n7tur68xe8ygmnvtgt3dhvg` (`company_id`),
  KEY `FKmfpod77d5bwibim3o1mlovqm4` (`job_id`),
  CONSTRAINT `FKh2n7tur68xe8ygmnvtgt3dhvg` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `FKmfpod77d5bwibim3o1mlovqm4` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_job`
--

LOCK TABLES `list_job` WRITE;
/*!40000 ALTER TABLE `list_job` DISABLE KEYS */;
INSERT INTO `list_job` VALUES (3,'Tài Xế Lái Xe Tải Giao Hàng','25 tuổi trở lên','TP.HCM','Chứng chỉ',NULL,'Lái xe giao hàng theo đơn hàng của công ty, Nhận hàng tại Kho Công ty và giao hàng đến điểm được chỉ định, Cụ thể trao đổi thêm khi phỏng vấn','1 năm',2,'10 - 12 triệu',_binary '','TP.HCM',1,3),(4,'Tài Xế Lái Xe - Lái Xe Chở Sếp','30 đến 45','Hà Nội',NULL,NULL,' Lái xe 7 chỗ cho Giám đốc di chuyển từ HCM đến Đồng Nai và ngược lại, Làm theo sự phân công của Giám đốc, Sẽ trao đổi cụ thể khi phỏng vấn','1 năm',1,'thỏa thuận theo năng lực ',NULL,'Ba Đình, Hà Nội',1,1),(5,'Tài Xế Lái Xe Bán Thời Gian Cho Giám Đốc','30 đến 45','Hà Nội',NULL,NULL,' Lái xe 7 chỗ cho Giám đốc di chuyển từ HCM đến Đồng Nai và ngược lại, Làm theo sự phân công của Giám đốc, Sẽ trao đổi cụ thể khi phỏng vấn','1 năm',1,'thỏa thuận theo năng lực ',NULL,'Ba Đình, Hà Nội',1,1);
/*!40000 ALTER TABLE `list_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_profile`
--

DROP TABLE IF EXISTS `list_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `list_profile` (
  `user_id` int NOT NULL,
  `list_job_id` int NOT NULL,
  PRIMARY KEY (`list_job_id`,`user_id`),
  KEY `FK8y4y4frh9lt04ptlr0ej576t8` (`user_id`),
  CONSTRAINT `FK3s8f7encgxvb5g3xnrrmkj7mu` FOREIGN KEY (`list_job_id`) REFERENCES `list_job` (`id`),
  CONSTRAINT `FK8y4y4frh9lt04ptlr0ej576t8` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_profile`
--

LOCK TABLES `list_profile` WRITE;
/*!40000 ALTER TABLE `list_profile` DISABLE KEYS */;
INSERT INTO `list_profile` VALUES (3,3),(3,4),(3,5),(5,4),(5,5);
/*!40000 ALTER TABLE `list_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cccd` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `career` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `experence` int DEFAULT NULL,
  `height` int DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `homeid` varchar(255) DEFAULT NULL,
  `job_information` varchar(255) DEFAULT NULL,
  `personality` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `salary` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `weight` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'342537','gfhgkhj','khljl','fgfh',1,65,NULL,NULL,NULL,NULL,NULL,NULL,'kma',172),(2,'342537','gfhgkhj','khljl','fgfh',1,65,NULL,NULL,NULL,NULL,NULL,NULL,'kma',172);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birth_day` date DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `passsword` varchar(255) DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL,
  `url_img` varchar(255) DEFAULT NULL,
  `profile_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `FK5q3e9303ap1wvtia6sft7ht1s` (`profile_id`),
  KEY `FK4qu1gr772nnf6ve5af002rwya` (`role_id`),
  CONSTRAINT `FK4qu1gr772nnf6ve5af002rwya` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK5q3e9303ap1wvtia6sft7ht1s` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'1991-09-09','buianhtuan@gmail.com','Bùi Anh Tuấn','$2a$10$M/JjTBxZCdyesvpNmbkVMeUF/dnUdUp2oFuDQGEyHUqwJ1J./GCIK',_binary '','http://localhost:8080/image/big_city_boi.jpg',1,2,'241588fhfjghj'),(5,'1991-09-09','buianhtuan123@gmail.com','Nguyễn Thanh Tú','$2a$10$GZnvwXc3rlXj7QG38x8HE.yzOieST.OTi7w5E80HL.7GvBmVo2oPG',_binary '','http://localhost:8080/image/big_city_boi.jpg',2,2,'241588fhfjghj');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-03  9:48:29
