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
  `id_card_date` date DEFAULT NULL,
  `permanent_address` varchar(1024) DEFAULT NULL,
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
  `rate_id` int(11) DEFAULT NULL,
  `birdthday` bigint(20) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,1,1,1,1,9,'DOCTOR000001','01687482737','215215883',NULL,NULL,NULL,'Vương','Đỗ','a',1,'215215883','vuongdt92@gmail.com',13254,'/var/hcmus/abc.png',NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,1,NULL,NULL,0,NULL,NULL,NULL,NULL,0,0),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'+841663016163','225483004',NULL,NULL,NULL,'Quang Vũ','Lưu',NULL,NULL,'225483004','quangvu.luu92@gmail.com',0,NULL,NULL,NULL,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,1,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

LOCK TABLES `role_right` WRITE;
/*!40000 ALTER TABLE `role_right` DISABLE KEYS */;
INSERT INTO `role_right` VALUES (1,1,1,0,NULL,NULL,NULL,NULL),(2,2,2,0,NULL,NULL,NULL,NULL),(3,1,3,0,NULL,NULL,NULL,NULL),(4,1,4,0,NULL,NULL,NULL,NULL),(5,1,5,0,NULL,NULL,NULL,NULL),(6,1,6,0,NULL,NULL,NULL,NULL),(7,1,7,0,NULL,NULL,NULL,NULL),(8,1,8,0,NULL,NULL,NULL,NULL),(9,1,9,0,NULL,NULL,NULL,NULL),(10,1,10,0,NULL,NULL,NULL,NULL),(11,1,11,0,NULL,NULL,NULL,NULL),(12,4,12,0,NULL,NULL,NULL,NULL),(13,3,1,0,NULL,NULL,NULL,NULL),(14,3,3,0,NULL,NULL,NULL,NULL),(15,3,4,0,NULL,NULL,NULL,NULL),(16,3,5,0,NULL,NULL,NULL,NULL),(17,3,6,0,NULL,NULL,NULL,NULL),(18,3,7,0,NULL,NULL,NULL,NULL),(19,3,8,0,NULL,NULL,NULL,NULL),(20,3,9,0,NULL,NULL,NULL,NULL),(21,3,10,0,NULL,NULL,NULL,NULL),(22,3,11,0,NULL,NULL,NULL,NULL),(23,2,4,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role_right` ENABLE KEYS */;
UNLOCK TABLES;
