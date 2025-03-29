CREATE DATABASE  IF NOT EXISTS `hostelfoodmanagementsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hostelfoodmanagementsystem`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: hostelfoodmanagementsystem
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `enrolment_no` varchar(20) NOT NULL,
  `faculty_no` varchar(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `hostel_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`enrolment_no`),
  UNIQUE KEY `faculty_no` (`faculty_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('GL5698','24CSEUG101','Ali Raza','2001-02-15','Sir Syed Hostel'),('GL5699','24MBBSU103','Fatima Noor','2002-07-10','Begum Rokeya Hostel'),('GL5700','24BATEC105','Zoya Khan','2002-05-01','Indira Gandhi Hostel'),('GL5701','24LLMGR202','Yusuf Hamid','2001-11-23','Sir Syed Hostel'),('GL5702','24BAHIS107','Sameer Javed','2000-09-12','Aftab Hostel'),('GL5703','24BTECH208','Sana Tariq','2001-03-18','Begum Rokeya Hostel'),('GL5704','24MCAIT301','Nasir Ali','2000-12-20','Sir Syed Hostel'),('GL5705','24MBAHR303','Hina Qureshi','2001-06-15','Indira Gandhi Hostel'),('GL5706','24BSCBZ112','Talha Syed','2002-01-11','Zakir Hussain Hostel'),('GL5707','24BCOMFN211','Uzma Khatoon','2001-10-19','Begum Rokeya Hostel'),('GL5708','24MTECHCE401','Adnan Farooq','2000-07-25','Aftab Hostel'),('GL5709','24PHDLAW501','Shaista Rehman','1998-04-12','Begum Rokeya Hostel'),('GL5710','24LLBUG603','Tariq Zaman','2002-03-05','Sir Syed Hostel'),('GL5711','24BBAFN701','Hiba Anwar','2001-09-08','Indira Gandhi Hostel'),('GL5712','24BTECHME801','Saif Uddin','2000-02-22','Zakir Hussain Hostel'),('GL5713','24BCSEDS103','Arsalan Mehmood','2002-12-30','Sir Syed Hostel'),('GL5714','24MAECON901','Noor Fatima','2001-05-17','Begum Rokeya Hostel'),('GL5715','24BSWSP708','Farhan Raza','2002-08-14','Aftab Hostel'),('GL5716','24BAENG204','Rehana Bano','2000-11-02','Begum Rokeya Hostel'),('GL5717','24BSCMAT301','Junaid Akhtar','2001-01-09','Sir Syed Hostel');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-29 12:14:21
