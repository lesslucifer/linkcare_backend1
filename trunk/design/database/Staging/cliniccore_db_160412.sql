-- MySQL dump 10.13  Distrib 5.1.73, for redhat-linux-gnu (x86_64)
--
-- Host: localhost    Database: cliniccore_db
-- ------------------------------------------------------
-- Server version	5.1.73

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annual_fee_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `subcategory_id` int(11) DEFAULT NULL,
  `cost_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `experience` double DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) NOT NULL COMMENT 'phone_number login',
  `id_card` varchar(45) NOT NULL,
  `passport` varchar(45) DEFAULT NULL COMMENT 'PASSPORT',
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `midle_name` varchar(45) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `login_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL COMMENT 'email login',
  `birthday` bigint(20) NOT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `facebook` varchar(45) DEFAULT NULL,
  `facebook_id` varchar(45) DEFAULT NULL COMMENT 'facebook_id login',
  `google_plus_id` varchar(45) DEFAULT NULL COMMENT 'google_plus_id login',
  `website` varchar(45) DEFAULT NULL,
  `hashed_password` varchar(256) DEFAULT NULL,
  `need_change_pwd` tinyint(1) DEFAULT NULL,
  `active_flag` smallint(6) DEFAULT '1' COMMENT 'new/active/deactivated/deleted',
  `begin_active_time` bigint(20) DEFAULT NULL,
  `end_active_time` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `birdthday` bigint(20) NOT NULL,
  `loginName` varchar(45) NOT NULL,
  `rate_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `UK_k9qlqijt38kmryafdhhq04lon` (`code`),
  UNIQUE KEY `facebook_id_UNIQUE` (`facebook_id`),
  UNIQUE KEY `google_plus_id_UNIQUE` (`google_plus_id`),
  KEY `fk_annual_fee_account_idx` (`annual_fee_id`),
  KEY `fk_account_place_idx` (`place_id`),
  KEY `fk_account_subcategory_idx` (`subcategory_id`),
  KEY `fk_account_cost_idx` (`cost_id`),
  KEY `fk_account_address_idx` (`address_id`),
  KEY `fk_account_rate_idx` (`rate_id`),
  CONSTRAINT `fk_account_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_annual_fee` FOREIGN KEY (`annual_fee_id`) REFERENCES `annual_fee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_cost` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_place` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_rate` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_subcategory` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,1,1,1,1,9,'DOCTOR000001','01687482737','215215883',NULL,'Vương','Đỗ','a',1,'215215883','vuongdt92@gmail.com',13254,'/var/hcmus/abc.png',NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,1,NULL,NULL,0,NULL,NULL,NULL,NULL,0,'',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_block_time`
--

DROP TABLE IF EXISTS `account_block_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_block_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `begin` datetime NOT NULL,
  `length` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id` (`account_id`) USING BTREE,
  KEY `account_block_time_index_begin_end` (`account_id`,`begin`) USING BTREE,
  CONSTRAINT `fk_account_block_time_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_block_time`
--

LOCK TABLES `account_block_time` WRITE;
/*!40000 ALTER TABLE `account_block_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_block_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_role_account_idx` (`account_id`),
  KEY `fk_account_role_role_idx` (`role_id`),
  CONSTRAINT `fk_account_role_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_role`
--

LOCK TABLES `account_role` WRITE;
/*!40000 ALTER TABLE `account_role` DISABLE KEYS */;
INSERT INTO `account_role` VALUES (1,1,1,0,NULL,NULL,NULL,NULL),(2,2,1,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_timings`
--

DROP TABLE IF EXISTS `account_timings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_timings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `begin_date` date NOT NULL,
  `created_datetime` bigint(11) unsigned DEFAULT NULL,
  `created_by` int(10) unsigned DEFAULT NULL,
  `last_updated` bigint(11) unsigned DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `account_timings_account_id_begin` (`account_id`,`begin_date`),
  CONSTRAINT `fk_account_timings_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_timings`
--

LOCK TABLES `account_timings` WRITE;
/*!40000 ALTER TABLE `account_timings` DISABLE KEYS */;
INSERT INTO `account_timings` VALUES (9,1,'2016-03-28',1459153622240,0,1459153650025,0,0),(10,1,'2016-03-28',1459153862782,0,1459153862782,0,0),(11,1,'2016-03-28',1459153923137,0,1459153923137,0,0),(12,1,'2016-03-28',1459600968387,0,1459600968387,0,0);
/*!40000 ALTER TABLE `account_timings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_number` varchar(200) DEFAULT NULL,
  `street` varchar(200) DEFAULT NULL,
  `hamlet` varchar(45) DEFAULT NULL,
  `ward` varchar(45) NOT NULL,
  `district` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `longtitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'12','Điện Biên Phủ',NULL,'10','10','Hồ Chí Minh',106.6812825,10.7656281,0,NULL,NULL,NULL,NULL,1),(2,'12','Điện Biên Phủ',NULL,'10','10','Hồ Chí Minh',106.6812825,10.7656281,0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `annual_fee`
--

DROP TABLE IF EXISTS `annual_fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `annual_fee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annual_fee`
--

LOCK TABLES `annual_fee` WRITE;
/*!40000 ALTER TABLE `annual_fee` DISABLE KEYS */;
INSERT INTO `annual_fee` VALUES (1,'annual fee','annual_fee','Một triệu đồng',1000000,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `annual_fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `patient` int(11) DEFAULT NULL,
  `medicar` int(11) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `calendar_id` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_appointment_patient_idx` (`patient`),
  KEY `fk_appointment_acocunt_1_idx` (`medicar`),
  KEY `fk_appointment_address_idx` (`address_id`),
  KEY `fk_appointment_calendar_idx` (`calendar_id`),
  KEY `fk_appointment_status_idx` (`status_id`),
  CONSTRAINT `fk_appointment_account_0` FOREIGN KEY (`patient`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_acocunt_1` FOREIGN KEY (`medicar`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_calendar` FOREIGN KEY (`calendar_id`) REFERENCES `calendar` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_appointment_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_booking`
--

DROP TABLE IF EXISTS `appointment_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment_booking` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `booker` int(11) NOT NULL,
  `medicar` int(11) NOT NULL,
  `cost` decimal(30,0) unsigned NOT NULL,
  `home` tinyint(1) NOT NULL,
  `address_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` smallint(6) unsigned NOT NULL,
  `duration` smallint(6) unsigned NOT NULL,
  `status` tinyint(4) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_booker` (`booker`),
  KEY `index_bookee` (`medicar`),
  KEY `index_adress` (`address_id`),
  KEY `index_status` (`status`),
  KEY `index_date_status` (`medicar`,`date`,`status`) USING BTREE,
  KEY `index_date_time` (`medicar`,`date`,`time`,`status`) USING BTREE,
  CONSTRAINT `fk_appointment_booking_address_id_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_appointment_booking_booker_account_id` FOREIGN KEY (`booker`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_appointment_booking_medicar_account_id` FOREIGN KEY (`medicar`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_booking`
--

LOCK TABLES `appointment_booking` WRITE;
/*!40000 ALTER TABLE `appointment_booking` DISABLE KEYS */;
INSERT INTO `appointment_booking` VALUES (2,1,1,'1000',1,1,'2016-03-30',480,30,4,NULL,NULL,NULL,1459958226611,NULL),(3,1,1,'1000',1,1,'2016-03-30',480,30,1,NULL,NULL,NULL,1459958234604,NULL),(9,1,1,'1000',1,1,'2016-03-30',480,30,2,NULL,NULL,NULL,1459958399588,NULL),(10,1,1,'1000',1,1,'2016-03-30',480,30,0,NULL,NULL,NULL,NULL,NULL),(11,1,1,'1000',1,1,'2016-03-30',480,30,0,NULL,NULL,NULL,NULL,NULL),(12,1,1,'1000',1,1,'2016-03-30',480,30,0,NULL,NULL,NULL,NULL,NULL),(13,1,1,'1000',0,1,'2016-04-07',500,10,5,'\0',0,1460043366917,1460284789798,0),(14,1,1,'1000',0,1,'2016-04-07',520,10,5,'\0',0,1460048289255,1460048311406,0),(15,1,1,'1000',0,1,'2016-04-07',540,10,1,'\0',0,1460050211450,1460050224711,0),(16,1,1,'1000',0,1,'2016-04-08',540,10,5,'\0',0,1460050244013,1460050761437,0),(17,1,1,'1000',0,1,'2016-04-08',480,10,3,'\0',0,1460050718910,1460051234461,0);
/*!40000 ALTER TABLE `appointment_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_patient`
--

DROP TABLE IF EXISTS `appointment_patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment_patient` (
  `appointment_id` int(11) unsigned NOT NULL,
  `name` varchar(256) NOT NULL,
  `birth` date NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `address` varchar(2048) DEFAULT NULL,
  `symtoms` text NOT NULL,
  PRIMARY KEY (`appointment_id`) USING HASH,
  KEY `index_birth` (`birth`),
  CONSTRAINT `fk_appointment_patient_appointment_id_appointment_booking_id` FOREIGN KEY (`appointment_id`) REFERENCES `appointment_booking` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_patient`
--

LOCK TABLES `appointment_patient` WRITE;
/*!40000 ALTER TABLE `appointment_patient` DISABLE KEYS */;
INSERT INTO `appointment_patient` VALUES (2,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(3,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(9,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(10,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(11,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(12,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(13,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(14,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(15,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(16,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!'),(17,'Quang Vu','1992-06-02',0,'Ho chi minh','Dau dau, so mui, chong mat, buon non!');
/*!40000 ALTER TABLE `appointment_patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_prescription`
--

DROP TABLE IF EXISTS `appointment_prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment_prescription` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) unsigned NOT NULL,
  `date` date NOT NULL,
  `instruction` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_id` (`booking_id`),
  CONSTRAINT `fk_appointment_prescription_id_appointment_booking_id` FOREIGN KEY (`booking_id`) REFERENCES `appointment_booking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_prescription`
--

LOCK TABLES `appointment_prescription` WRITE;
/*!40000 ALTER TABLE `appointment_prescription` DISABLE KEYS */;
INSERT INTO `appointment_prescription` VALUES (9,13,'2016-04-07','Uong sau khi an com'),(10,14,'2016-04-07','Uong sau khi an com'),(11,16,'2016-04-07','Uong sau khi an com');
/*!40000 ALTER TABLE `appointment_prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar` (
  `id` int(11) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL,
  `slot` bigint(20) DEFAULT NULL,
  `date` bigint(20) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  `configuration_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  `is_blocked` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(11) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_calendar_account_idx` (`account_id`),
  KEY `FK_grar0vt1grsig5irlyh9n6btw` (`configuration_id`),
  CONSTRAINT `FK_grar0vt1grsig5irlyh9n6btw` FOREIGN KEY (`configuration_id`) REFERENCES `configuration` (`id`),
  CONSTRAINT `fk_calendar_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `major_id` int(11) DEFAULT NULL,
  `code` varchar(45) NOT NULL,
  `name` varchar(450) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `i18n_lang_key_prefix` varchar(100) DEFAULT NULL,
  `view_radius_limit` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `FK_CATEGORY_MAJOR` (`major_id`),
  CONSTRAINT `FK_CATEGORY_MAJOR` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (8,5,'NOI','Nội',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL),(9,5,'NGOAI','Ngoại',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_right`
--

DROP TABLE IF EXISTS `clinic_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinic_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL COMMENT 'view/approve/create/reject',
  `name` varchar(450) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_right`
--

LOCK TABLES `clinic_right` WRITE;
/*!40000 ALTER TABLE `clinic_right` DISABLE KEYS */;
INSERT INTO `clinic_right` VALUES (1,'RIGHT_UPDATE_TIMINGS','Update timings','Update doctor / nurse timings',0,NULL,NULL,NULL,NULL),(2,'RIGHT_BOOK_APPOINTMENT','Patient book appointment right','Patient book appointment right',0,NULL,NULL,NULL,NULL),(3,'RIGHT_APPROVE_APPOINTMENT','Approve appointment',NULL,0,NULL,NULL,NULL,NULL),(4,'RIGHT_CANCEL_APPOINTMENT','Cancel appointment','Cancel appointment',0,NULL,NULL,NULL,NULL),(5,'RIGHT_REJECT_APPOINTMENT','Reject appointment','Reject appointment',0,NULL,NULL,NULL,NULL),(6,'RIGHT_START_APPOINTMENT','Start appointment','Start appointment',0,NULL,NULL,NULL,NULL),(7,'RIGHT_GET_MEDICAR_APPOINTMENT','Get medicar appointments','Get medicar appointments',0,NULL,NULL,NULL,NULL),(8,'RIGHT_SUBMIT_PRESCRIPTION','Medicar can submit owned prescription','Medicar can submit owned prescription',0,NULL,NULL,NULL,NULL),(9,'RIGHT_GET_PRESCRIPTION_HISTORY','Medicar can get prescription of patient in appointment','Medicar can get prescription of patient in appointment',0,NULL,NULL,NULL,NULL),(10,'RIGHT_RATING','Right rating',NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `clinic_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `content` text,
  `medicar_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_account_idx` (`medicar_id`),
  KEY `fk_comment_account_patient_idx` (`patient_id`),
  CONSTRAINT `fk_comment_account_medicar` FOREIGN KEY (`medicar_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_account_patient` FOREIGN KEY (`patient_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost`
--

LOCK TABLES `cost` WRITE;
/*!40000 ALTER TABLE `cost` DISABLE KEYS */;
INSERT INTO `cost` VALUES (1,'home','home',NULL,150,0,NULL,NULL,NULL,NULL),(2,'clinic','clinic',NULL,50,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custom_timings`
--

DROP TABLE IF EXISTS `custom_timings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `custom_timings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `begin` smallint(5) unsigned NOT NULL,
  `length` smallint(5) unsigned NOT NULL,
  `type` tinyint(4) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_account_id` (`account_id`),
  KEY `index_type` (`type`),
  KEY `index_date` (`date`,`begin`) USING BTREE,
  CONSTRAINT `fk_custom_timings_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custom_timings`
--

LOCK TABLES `custom_timings` WRITE;
/*!40000 ALTER TABLE `custom_timings` DISABLE KEYS */;
/*!40000 ALTER TABLE `custom_timings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(450) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (5,'DOCTOR','Bác sĩ',NULL,0,NULL,NULL,NULL,NULL),(6,'NURSE','Y tá',NULL,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address_id` int(11) NOT NULL,
  `cost_id` int(11) DEFAULT NULL,
  `code` varchar(45) NOT NULL,
  `name` varchar(450) NOT NULL,
  `description` text,
  `note` varchar(1000) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(450) DEFAULT NULL,
  `website` varchar(450) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `daily_open_time` bigint(20) DEFAULT NULL,
  `daily_close_time` bigint(20) DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  `node` varchar(1000) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_place_address1_idx` (`address_id`),
  KEY `fk_place_cost_idx` (`cost_id`),
  CONSTRAINT `fk_place_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_place_cost` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,2,2,'PK_DOCTOR_00002','PK KMS 1',NULL,NULL,'233123','ck@gmail.com','xxx.com',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_doctor_note`
--

DROP TABLE IF EXISTS `prescription_doctor_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription_doctor_note` (
  `id` int(11) unsigned NOT NULL,
  `text` text NOT NULL,
  `image` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_appointment_doctor_note_id_appointment_prescription_id` FOREIGN KEY (`id`) REFERENCES `appointment_prescription` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_doctor_note`
--

LOCK TABLES `prescription_doctor_note` WRITE;
/*!40000 ALTER TABLE `prescription_doctor_note` DISABLE KEYS */;
INSERT INTO `prescription_doctor_note` VALUES (9,'Tinh trang binh thuong, uong thuoc xong chet!',NULL),(10,'Tinh trang binh thuong, uong thuoc xong chet!',NULL),(11,'Tinh trang binh thuong, uong thuoc xong chet!',NULL);
/*!40000 ALTER TABLE `prescription_doctor_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_medicine`
--

DROP TABLE IF EXISTS `prescription_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription_medicine` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `prescription_id` int(11) unsigned NOT NULL,
  `name` varchar(512) NOT NULL,
  `quant_morning` smallint(5) unsigned NOT NULL,
  `quant_noon` smallint(5) unsigned NOT NULL,
  `quant_afternoon` smallint(5) unsigned NOT NULL,
  `quant_night` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prescription_id` (`prescription_id`),
  CONSTRAINT `fk_prescription_id` FOREIGN KEY (`prescription_id`) REFERENCES `appointment_prescription` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_medicine`
--

LOCK TABLES `prescription_medicine` WRITE;
/*!40000 ALTER TABLE `prescription_medicine` DISABLE KEYS */;
INSERT INTO `prescription_medicine` VALUES (1,9,'aspirin',2,0,0,2),(2,9,'beta lecosoda',1,0,0,1),(3,9,'medax',1,1,1,1),(4,10,'aspirin',2,0,0,2),(5,10,'beta lecosoda',1,0,0,1),(6,10,'medax',1,1,1,1),(7,11,'aspirin',2,0,0,2),(8,11,'beta lecosoda',1,0,0,1),(9,11,'medax',1,1,1,1);
/*!40000 ALTER TABLE `prescription_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rate`
--

DROP TABLE IF EXISTS `rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `last_update_by` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(11) DEFAULT NULL,
  `mark` double DEFAULT '0',
  `count` int(11) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `medicar_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rate_account_idx` (`medicar_id`),
  CONSTRAINT `fk_rate_account` FOREIGN KEY (`medicar_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rate`
--

LOCK TABLES `rate` WRITE;
/*!40000 ALTER TABLE `rate` DISABLE KEYS */;
INSERT INTO `rate` VALUES (1,'abc','abc',0,1460284789792,NULL,NULL,NULL,2.375,4,NULL,1);
/*!40000 ALTER TABLE `rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rate_trace`
--

DROP TABLE IF EXISTS `rate_trace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rate_trace` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mark` double NOT NULL,
  `appointment_booking_id` int(11) unsigned NOT NULL,
  `partient_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rate_trace_account_idx` (`partient_id`),
  KEY `fk_rate_trace_appointment_booking_idx` (`appointment_booking_id`),
  CONSTRAINT `fk_rate_trace_account` FOREIGN KEY (`partient_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rate_trace_appointment_booking` FOREIGN KEY (`appointment_booking_id`) REFERENCES `appointment_booking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rate_trace`
--

LOCK TABLES `rate_trace` WRITE;
/*!40000 ALTER TABLE `rate_trace` DISABLE KEYS */;
INSERT INTO `rate_trace` VALUES (1,'\0',1,1460283534557,1460283534558,1,'ok',2.5,13,1),(2,'\0',1,1460283772614,1460283772614,1,'ok',2.5,13,1),(3,'\0',1,1460284688618,1460284688618,1,'ok',2.5,13,1),(4,'\0',1,1460284789795,1460284789795,1,'ok',2,13,1);
/*!40000 ALTER TABLE `rate_trace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL COMMENT 'doctor/nuser/admin',
  `name` varchar(450) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'DOCTOR_ROLE','Doctor\'s roles','Doctor\'s roles',0,NULL,NULL,NULL,NULL),(2,'PATIENT_ROLE','Patient\'s role','Patient\'s role',0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_right`
--

DROP TABLE IF EXISTS `role_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `clinic_right_id` int(11) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_right_maper_right_idx` (`clinic_right_id`),
  KEY `fk_role_right_role_idx` (`role_id`),
  CONSTRAINT `fk_role_right_clinic_right` FOREIGN KEY (`clinic_right_id`) REFERENCES `clinic_right` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_right_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_right`
--

LOCK TABLES `role_right` WRITE;
/*!40000 ALTER TABLE `role_right` DISABLE KEYS */;
INSERT INTO `role_right` VALUES (1,1,1,0,NULL,NULL,NULL,NULL),(2,2,2,0,NULL,NULL,NULL,NULL),(3,1,3,0,NULL,NULL,NULL,NULL),(4,1,4,0,NULL,NULL,NULL,NULL),(5,1,5,0,NULL,NULL,NULL,NULL),(6,1,6,0,NULL,NULL,NULL,NULL),(7,1,7,0,NULL,NULL,NULL,NULL),(8,1,8,0,NULL,NULL,NULL,NULL),(9,1,9,0,NULL,NULL,NULL,NULL),(10,1,10,0,NULL,NULL,NULL,NULL),(11,2,10,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_log`
--

DROP TABLE IF EXISTS `session_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(45) DEFAULT NULL,
  `login_time` bigint(20) DEFAULT NULL,
  `logout_time` bigint(20) DEFAULT NULL,
  `expired_time` bigint(20) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `last_updated` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `session_id_2` (`session_id`),
  KEY `fk_sessionlog_account_idx` (`account_id`),
  KEY `login_time_expired_time` (`login_time`,`expired_time`) USING BTREE,
  KEY `session_id` (`session_id`),
  CONSTRAINT `fk_sessionlog_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_log`
--

LOCK TABLES `session_log` WRITE;
/*!40000 ALTER TABLE `session_log` DISABLE KEYS */;
INSERT INTO `session_log` VALUES (47,'865fc5b4-3bc9-4042-9e12-bb52cb43f5a2',1460269546777,NULL,1460270746777,1,0,1460477523097,1,1,1460269546777);
/*!40000 ALTER TABLE `session_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `create_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0',
  `category_id` int(11) DEFAULT NULL,
  `clinic_dur` smallint(5) unsigned NOT NULL,
  `patient_home_dur` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subcategory_category` (`category_id`),
  CONSTRAINT `fk_subcategory_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategory`
--

LOCK TABLES `subcategory` WRITE;
/*!40000 ALTER TABLE `subcategory` DISABLE KEYS */;
INSERT INTO `subcategory` VALUES (1,'Tim mach','TIM',NULL,NULL,NULL,NULL,NULL,NULL,'\0',8,10,30),(2,'Ho hap','HOHAP',NULL,NULL,NULL,NULL,NULL,NULL,'\0',8,10,30);
/*!40000 ALTER TABLE `subcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timings`
--

DROP TABLE IF EXISTS `timings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `timings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_timings_id` int(11) NOT NULL,
  `begin` smallint(6) NOT NULL,
  `length` smallint(6) NOT NULL,
  `type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `timings_index_account_timings_id` (`account_timings_id`),
  KEY `timings_index_begin` (`begin`) USING BTREE,
  CONSTRAINT `fk_timings_account_timings_id_account_timings_id` FOREIGN KEY (`account_timings_id`) REFERENCES `account_timings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timings`
--

LOCK TABLES `timings` WRITE;
/*!40000 ALTER TABLE `timings` DISABLE KEYS */;
INSERT INTO `timings` VALUES (1,10,480,210,0),(2,10,810,240,0),(3,11,480,210,0),(4,11,810,240,0),(5,12,480,210,0),(6,12,810,240,0);
/*!40000 ALTER TABLE `timings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-12 23:18:46
