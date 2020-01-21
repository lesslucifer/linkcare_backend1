-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2016 at 04:13 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cliniccore_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
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
  `birdthday` bigint(20) NOT NULL,
  `rate_id` int(11) DEFAULT NULL,
  `loginName` varchar(45) NOT NULL,
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
  KEY `fk_account_rate_idx` (`rate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `annual_fee_id`, `place_id`, `subcategory_id`, `cost_id`, `address_id`, `experience`, `code`, `phone_number`, `id_card`, `id_card_date`, `permanent_address`, `passport`, `first_name`, `last_name`, `midle_name`, `gender`, `login_name`, `email`, `birthday`, `image_url`, `facebook`, `facebook_id`, `google_plus_id`, `website`, `hashed_password`, `need_change_pwd`, `active_flag`, `begin_active_time`, `end_active_time`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`, `birdthday`, `rate_id`, `loginName`) VALUES
(1, 1, 1, 1, 1, 1, 9, 'DOCTOR000001', '01687482737', '215215883', NULL, NULL, NULL, 'Vương', 'Đỗ', 'a', 1, '215215883', 'vuongdt92@gmail.com', 13254, '/var/hcmus/abc.png', NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `account_block_time`
--

DROP TABLE IF EXISTS `account_block_time`;
CREATE TABLE IF NOT EXISTS `account_block_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `begin` datetime NOT NULL,
  `length` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id` (`account_id`) USING BTREE,
  KEY `account_block_time_index_begin_end` (`account_id`,`begin`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account_block_time`
--

INSERT INTO `account_block_time` (`id`, `account_id`, `begin`, `length`, `is_deleted`) VALUES
(1, 1, '2016-04-20 00:00:00', 600, 0);

-- --------------------------------------------------------

--
-- Table structure for table `account_role`
--

DROP TABLE IF EXISTS `account_role`;
CREATE TABLE IF NOT EXISTS `account_role` (
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
  KEY `fk_account_role_role_idx` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account_role`
--

INSERT INTO `account_role` (`id`, `role_id`, `account_id`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(1, 1, 1, 0, NULL, NULL, NULL, NULL),
(2, 2, 1, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `account_timings`
--

DROP TABLE IF EXISTS `account_timings`;
CREATE TABLE IF NOT EXISTS `account_timings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `created_datetime` bigint(11) UNSIGNED DEFAULT NULL,
  `created_by` int(10) UNSIGNED DEFAULT NULL,
  `last_updated` bigint(11) UNSIGNED DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `account_timings_account_id_begin` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `account_timings`
--

INSERT INTO `account_timings` (`id`, `account_id`, `created_datetime`, `created_by`, `last_updated`, `last_updated_by`, `is_deleted`) VALUES
(9, 1, 1459153622240, 0, 1459153650025, 0, 0),
(10, 1, 1459153862782, 0, 1459153862782, 0, 0),
(11, 1, 1459153923137, 0, 1459153923137, 0, 0),
(12, 1, 1459600968387, 0, 1459600968387, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `achievements`
--

DROP TABLE IF EXISTS `achievements`;
CREATE TABLE IF NOT EXISTS `achievements` (
  `id` int(10) UNSIGNED NOT NULL,
  `account_id` int(11) NOT NULL,
  `study` text,
  `research` text,
  `specialist_achievement` text,
  `highlights_achievement` text,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
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

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `house_number`, `street`, `hamlet`, `ward`, `district`, `city`, `longtitude`, `latitude`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`, `account_id`) VALUES
(1, '12', 'Điện Biên Phủ', NULL, '10', '10', 'Hồ Chí Minh', 106.6812825, 10.7656281, 0, NULL, NULL, NULL, NULL, 1),
(2, '12', 'Điện Biên Phủ', NULL, '10', '10', 'Hồ Chí Minh', 106.6812825, 10.7656281, 0, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `annual_fee`
--

DROP TABLE IF EXISTS `annual_fee`;
CREATE TABLE IF NOT EXISTS `annual_fee` (
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

--
-- Dumping data for table `annual_fee`
--

INSERT INTO `annual_fee` (`id`, `name`, `code`, `description`, `price`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(1, 'annual fee', 'annual_fee', 'Một triệu đồng', 1000000, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `appointment_booking`
--

DROP TABLE IF EXISTS `appointment_booking`;
CREATE TABLE IF NOT EXISTS `appointment_booking` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `booker` int(11) NOT NULL,
  `medicar` int(11) NOT NULL,
  `cost` decimal(30,0) UNSIGNED NOT NULL,
  `home` tinyint(1) NOT NULL,
  `address_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time` smallint(6) UNSIGNED NOT NULL,
  `duration` smallint(6) UNSIGNED NOT NULL,
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
  KEY `index_date_time` (`medicar`,`date`,`time`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `appointment_booking`
--

INSERT INTO `appointment_booking` (`id`, `booker`, `medicar`, `cost`, `home`, `address_id`, `date`, `time`, `duration`, `status`, `is_deleted`, `created_by`, `created_datetime`, `last_updated`, `last_updated_by`) VALUES
(2, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 4, NULL, NULL, NULL, 1459958226611, NULL),
(3, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 1, NULL, NULL, NULL, 1459958234604, NULL),
(9, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 2, NULL, NULL, NULL, 1459958399588, NULL),
(10, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 0, NULL, NULL, NULL, NULL, NULL),
(11, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 0, NULL, NULL, NULL, NULL, NULL),
(12, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 0, NULL, NULL, NULL, NULL, NULL),
(13, 1, 1, '1000', 0, 1, '2016-04-07', 500, 10, 5, b'0', 0, 1460043366917, 1460048218932, 0),
(14, 1, 1, '1000', 0, 1, '2016-04-07', 520, 10, 5, b'0', 0, 1460048289255, 1460048311406, 0),
(15, 1, 1, '1000', 0, 1, '2016-04-07', 540, 10, 1, b'0', 0, 1460050211450, 1460050224711, 0),
(16, 1, 1, '1000', 0, 1, '2016-04-08', 540, 10, 5, b'0', 0, 1460050244013, 1460050761437, 0),
(17, 1, 1, '1000', 0, 1, '2016-04-08', 480, 10, 3, b'0', 0, 1460050718910, 1460051234461, 0);

-- --------------------------------------------------------

--
-- Table structure for table `appointment_patient`
--

DROP TABLE IF EXISTS `appointment_patient`;
CREATE TABLE IF NOT EXISTS `appointment_patient` (
  `appointment_id` int(11) UNSIGNED NOT NULL,
  `name` varchar(256) NOT NULL,
  `birth` date NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `address` varchar(2048) DEFAULT NULL,
  `symtoms` text NOT NULL,
  PRIMARY KEY (`appointment_id`) USING HASH,
  KEY `index_birth` (`birth`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment_patient`
--

INSERT INTO `appointment_patient` (`appointment_id`, `name`, `birth`, `gender`, `address`, `symtoms`) VALUES
(2, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(3, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(9, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(10, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(11, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(12, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(13, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(14, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(15, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(16, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!'),
(17, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!');

-- --------------------------------------------------------

--
-- Table structure for table `appointment_prescription`
--

DROP TABLE IF EXISTS `appointment_prescription`;
CREATE TABLE IF NOT EXISTS `appointment_prescription` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `instruction` text CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `booking_id` (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `appointment_prescription`
--

INSERT INTO `appointment_prescription` (`id`, `booking_id`, `date`, `instruction`) VALUES
(9, 13, '2016-04-07', 'Uong sau khi an com'),
(10, 14, '2016-04-07', 'Uong sau khi an com'),
(11, 16, '2016-04-07', 'Uong sau khi an com');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
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
  KEY `FK_CATEGORY_MAJOR` (`major_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `major_id`, `code`, `name`, `description`, `i18n_lang_key_prefix`, `view_radius_limit`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(8, 5, 'NOI', 'Nội', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL),
(9, 5, 'NGOAI', 'Ngoại', NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `clinic_right`
--

DROP TABLE IF EXISTS `clinic_right`;
CREATE TABLE IF NOT EXISTS `clinic_right` (
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clinic_right`
--

INSERT INTO `clinic_right` (`id`, `code`, `name`, `description`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(1, 'RIGHT_UPDATE_TIMINGS', 'Update timings', 'Update doctor / nurse timings', 0, NULL, NULL, NULL, NULL),
(2, 'RIGHT_BOOK_APPOINTMENT', 'Patient book appointment right', 'Patient book appointment right', 0, NULL, NULL, NULL, NULL),
(3, 'RIGHT_APPROVE_APPOINTMENT', 'Approve appointment', NULL, 0, NULL, NULL, NULL, NULL),
(4, 'RIGHT_CANCEL_APPOINTMENT', 'Cancel appointment', 'Cancel appointment', 0, NULL, NULL, NULL, NULL),
(5, 'RIGHT_REJECT_APPOINTMENT', 'Reject appointment', 'Reject appointment', 0, NULL, NULL, NULL, NULL),
(6, 'RIGHT_START_APPOINTMENT', 'Start appointment', 'Start appointment', 0, NULL, NULL, NULL, NULL),
(7, 'RIGHT_GET_MEDICAR_APPOINTMENT', 'Get medicar appointments', 'Get medicar appointments', 0, NULL, NULL, NULL, NULL),
(8, 'RIGHT_SUBMIT_PRESCRIPTION', 'Medicar can submit owned prescription', 'Medicar can submit owned prescription', 0, NULL, NULL, NULL, NULL),
(9, 'RIGHT_GET_PRESCRIPTION_HISTORY', 'Medicar can get prescription of patient in appointment', 'Medicar can get prescription of patient in appointment', 0, NULL, NULL, NULL, NULL),
(10, 'RIGHT_BLOCK_VACATION', 'User can own block vacations', 'User can own block vacations', 0, NULL, NULL, NULL, NULL),
(11, 'RIGHT_HAS_MEDICAR_PROFILE', 'User can have medicar''s profile', 'User can have medicar''s profile', 0, NULL, NULL, NULL, NULL),
(12, 'RIGHT_UPDATE_MEDICAR_PROFILE', 'User can update medicar profile of all users', 'User can update medicar profile of all users', 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL,
  `code` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `content` text CHARACTER SET latin1,
  `medicar_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_account_idx` (`medicar_id`),
  KEY `fk_comment_account_patient_idx` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
CREATE TABLE IF NOT EXISTS `cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `description` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `price` double DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cost`
--

INSERT INTO `cost` (`id`, `code`, `name`, `description`, `price`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(1, 'home', 'home', NULL, 150, 0, NULL, NULL, NULL, NULL),
(2, 'clinic', 'clinic', NULL, 50, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `custom_timings`
--

DROP TABLE IF EXISTS `custom_timings`;
CREATE TABLE IF NOT EXISTS `custom_timings` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `begin` smallint(5) UNSIGNED NOT NULL,
  `length` smallint(5) UNSIGNED NOT NULL,
  `type` tinyint(4) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_account_id` (`account_id`),
  KEY `index_type` (`type`),
  KEY `index_date` (`date`,`begin`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
CREATE TABLE IF NOT EXISTS `major` (
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

--
-- Dumping data for table `major`
--

INSERT INTO `major` (`id`, `code`, `name`, `description`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(5, 'DOCTOR', 'Bác sĩ', NULL, 0, NULL, NULL, NULL, NULL),
(6, 'NURSE', 'Y tá', NULL, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `medicar_profile`
--

DROP TABLE IF EXISTS `medicar_profile`;
CREATE TABLE IF NOT EXISTS `medicar_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NOT NULL,
  `license_number` varchar(256) NOT NULL,
  `graduated_school` varchar(256) DEFAULT NULL,
  `graduated_year` year(4) DEFAULT NULL,
  `master_year` year(4) DEFAULT NULL,
  `master_spec` varchar(256) DEFAULT NULL,
  `doctor_year` year(4) DEFAULT NULL,
  `doctor_spec` varchar(256) DEFAULT NULL,
  `specialist_1_year` year(4) DEFAULT NULL,
  `specialist_1_spec` varchar(256) DEFAULT NULL,
  `specialist_2_year` year(4) DEFAULT NULL,
  `specialist_2_spec` varchar(256) DEFAULT NULL,
  `other_specialist_year` year(4) DEFAULT NULL,
  `other_specialist_spec` varchar(256) DEFAULT NULL,
  `other_training` text,
  `level` tinyint(3) UNSIGNED NOT NULL,
  `clinic_price` int(11) UNSIGNED NOT NULL,
  `patient_home_price` int(11) UNSIGNED NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `licenseNumber` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_id_2` (`account_id`),
  KEY `account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `content` text NOT NULL,
  `type` tinyint(3) UNSIGNED NOT NULL DEFAULT '0',
  `is_read` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `owner` (`owner`),
  KEY `sender` (`sender`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
CREATE TABLE IF NOT EXISTS `place` (
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
  KEY `coord` (`longtitude`,`latitude`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `place`
--

INSERT INTO `place` (`id`, `address_id`, `cost_id`, `code`, `name`, `description`, `note`, `phone_number`, `email`, `website`, `fax`, `daily_open_time`, `daily_close_time`, `image_url`, `status`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`, `longtitude`, `node`, `latitude`) VALUES
(1, 2, 2, 'PK_DOCTOR_00002', 'PK KMS 1', NULL, NULL, '233123', 'ck@gmail.com', 'xxx.com', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prescription_doctor_note`
--

DROP TABLE IF EXISTS `prescription_doctor_note`;
CREATE TABLE IF NOT EXISTS `prescription_doctor_note` (
  `id` int(11) UNSIGNED NOT NULL,
  `text` text CHARACTER SET latin1 NOT NULL,
  `image` varchar(1024) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prescription_doctor_note`
--

INSERT INTO `prescription_doctor_note` (`id`, `text`, `image`) VALUES
(9, 'Tinh trang binh thuong, uong thuoc xong chet!', NULL),
(10, 'Tinh trang binh thuong, uong thuoc xong chet!', NULL),
(11, 'Tinh trang binh thuong, uong thuoc xong chet!', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prescription_medicine`
--

DROP TABLE IF EXISTS `prescription_medicine`;
CREATE TABLE IF NOT EXISTS `prescription_medicine` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prescription_id` int(11) UNSIGNED NOT NULL,
  `name` varchar(512) CHARACTER SET latin1 NOT NULL,
  `quant_morning` smallint(5) UNSIGNED NOT NULL,
  `quant_noon` smallint(5) UNSIGNED NOT NULL,
  `quant_afternoon` smallint(5) UNSIGNED NOT NULL,
  `quant_night` smallint(5) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prescription_id` (`prescription_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `prescription_medicine`
--

INSERT INTO `prescription_medicine` (`id`, `prescription_id`, `name`, `quant_morning`, `quant_noon`, `quant_afternoon`, `quant_night`) VALUES
(1, 9, 'aspirin', 2, 0, 0, 2),
(2, 9, 'beta lecosoda', 1, 0, 0, 1),
(3, 9, 'medax', 1, 1, 1, 1),
(4, 10, 'aspirin', 2, 0, 0, 2),
(5, 10, 'beta lecosoda', 1, 0, 0, 1),
(6, 10, 'medax', 1, 1, 1, 1),
(7, 11, 'aspirin', 2, 0, 0, 2),
(8, 11, 'beta lecosoda', 1, 0, 0, 1),
(9, 11, 'medax', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

DROP TABLE IF EXISTS `rate`;
CREATE TABLE IF NOT EXISTS `rate` (
  `id` int(11) NOT NULL,
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
  KEY `FK_l3rl9odf1q3lfmc6hcd6keda9` (`medicar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rate_trace`
--

DROP TABLE IF EXISTS `rate_trace`;
CREATE TABLE IF NOT EXISTS `rate_trace` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `is_deleted` bit(1) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `mark` double NOT NULL,
  `appointment_booking_id` int(11) NOT NULL,
  `partient_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_renod4bjtwok8fhuhtemcw0st` (`partient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `code`, `name`, `description`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(1, 'DOCTOR_ROLE', 'Doctor''s roles', 'Doctor''s roles', 0, NULL, NULL, NULL, NULL),
(2, 'PATIENT_ROLE', 'Patient''s role', 'Patient''s role', 0, NULL, NULL, NULL, NULL),
(3, 'NURSE_ROLE', 'Nurse''s role', 'Nurse''s role', 0, NULL, NULL, NULL, NULL),
(4, 'ADMIN_ROLE', 'Admin''s role', 'Admin''s role', 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `role_right`
--

DROP TABLE IF EXISTS `role_right`;
CREATE TABLE IF NOT EXISTS `role_right` (
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
  KEY `fk_role_right_role_idx` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role_right`
--

INSERT INTO `role_right` (`id`, `role_id`, `clinic_right_id`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(1, 1, 1, 0, NULL, NULL, NULL, NULL),
(2, 2, 2, 0, NULL, NULL, NULL, NULL),
(3, 1, 3, 0, NULL, NULL, NULL, NULL),
(4, 1, 4, 0, NULL, NULL, NULL, NULL),
(5, 1, 5, 0, NULL, NULL, NULL, NULL),
(6, 1, 6, 0, NULL, NULL, NULL, NULL),
(7, 1, 7, 0, NULL, NULL, NULL, NULL),
(8, 1, 8, 0, NULL, NULL, NULL, NULL),
(9, 1, 9, 0, NULL, NULL, NULL, NULL),
(10, 1, 10, 0, NULL, NULL, NULL, NULL),
(11, 1, 11, 0, NULL, NULL, NULL, NULL),
(12, 4, 12, 0, NULL, NULL, NULL, NULL),
(13, 3, 1, 0, NULL, NULL, NULL, NULL),
(14, 3, 3, 0, NULL, NULL, NULL, NULL),
(15, 3, 4, 0, NULL, NULL, NULL, NULL),
(16, 3, 5, 0, NULL, NULL, NULL, NULL),
(17, 3, 6, 0, NULL, NULL, NULL, NULL),
(18, 3, 7, 0, NULL, NULL, NULL, NULL),
(19, 3, 8, 0, NULL, NULL, NULL, NULL),
(20, 3, 9, 0, NULL, NULL, NULL, NULL),
(21, 3, 10, 0, NULL, NULL, NULL, NULL),
(22, 3, 11, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `session_log`
--

DROP TABLE IF EXISTS `session_log`;
CREATE TABLE IF NOT EXISTS `session_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
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
  UNIQUE KEY `account_id` (`account_id`),
  KEY `fk_sessionlog_account_idx` (`account_id`),
  KEY `login_time_expired_time` (`login_time`,`expired_time`) USING BTREE,
  KEY `session_id` (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `session_log`
--

INSERT INTO `session_log` (`id`, `session_id`, `login_time`, `logout_time`, `expired_time`, `account_id`, `is_deleted`, `last_updated`, `last_updated_by`, `created_by`, `created_datetime`) VALUES
(1, '8c1e1a64-fe38-4e40-863b-06e14fe2169c', 1460474818866, NULL, 1460476018866, 1, 0, 1460474818866, 1, 1, 1460474818866);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
  `id` int(11) NOT NULL,
  `code` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `description` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
CREATE TABLE IF NOT EXISTS `subcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `code` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `description` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `create_datetime` bigint(20) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated` bigint(20) DEFAULT NULL,
  `created_datetime` bigint(20) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT b'0',
  `category_id` int(11) DEFAULT NULL,
  `clinic_dur` smallint(5) UNSIGNED NOT NULL,
  `patient_home_dur` smallint(5) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subcategory_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subcategory`
--

INSERT INTO `subcategory` (`id`, `name`, `code`, `description`, `created_by`, `create_datetime`, `last_updated_by`, `last_updated`, `created_datetime`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES
(1, 'Tim mach', 'TIM', NULL, NULL, NULL, NULL, NULL, NULL, b'0', 8, 10, 30),
(2, 'Ho hap', 'HOHAP', NULL, NULL, NULL, NULL, NULL, NULL, b'0', 8, 10, 30);

-- --------------------------------------------------------

--
-- Table structure for table `timings`
--

DROP TABLE IF EXISTS `timings`;
CREATE TABLE IF NOT EXISTS `timings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_timings_id` int(11) NOT NULL,
  `begin` smallint(6) NOT NULL,
  `length` smallint(6) NOT NULL,
  `type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `timings_index_account_timings_id` (`account_timings_id`),
  KEY `timings_index_begin` (`begin`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `timings`
--

INSERT INTO `timings` (`id`, `account_timings_id`, `begin`, `length`, `type`) VALUES
(1, 10, 480, 210, 0),
(2, 10, 810, 240, 0),
(3, 11, 480, 210, 0),
(4, 11, 810, 240, 0),
(5, 12, 480, 210, 0),
(6, 12, 810, 240, 0);

-- --------------------------------------------------------

--
-- Table structure for table `working_history`
--

DROP TABLE IF EXISTS `working_history`;
CREATE TABLE IF NOT EXISTS `working_history` (
  `id` int(11) UNSIGNED NOT NULL,
  `account_id` int(11) NOT NULL,
  `from_year` year(4) NOT NULL,
  `to_year` year(4) NOT NULL,
  `workplace` varchar(256) DEFAULT NULL,
  `specialist` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment_patient`
--
ALTER TABLE `appointment_patient` ADD FULLTEXT KEY `index_name` (`name`);
ALTER TABLE `appointment_patient` ADD FULLTEXT KEY `index_fulltext_address` (`address`);
ALTER TABLE `appointment_patient` ADD FULLTEXT KEY `index_fulltext_symtoms` (`symtoms`);
ALTER TABLE `appointment_patient` ADD FULLTEXT KEY `index_fulltext_name` (`name`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `fk_account_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_account_annual_fee` FOREIGN KEY (`annual_fee_id`) REFERENCES `annual_fee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_account_cost` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_account_place` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_account_rate` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_account_subcategory` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `account_block_time`
--
ALTER TABLE `account_block_time`
  ADD CONSTRAINT `fk_account_block_time_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `account_role`
--
ALTER TABLE `account_role`
  ADD CONSTRAINT `fk_account_role_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_account_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `account_timings`
--
ALTER TABLE `account_timings`
  ADD CONSTRAINT `fk_account_timings_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `appointment_booking`
--
ALTER TABLE `appointment_booking`
  ADD CONSTRAINT `fk_appointment_booking_address_id_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `fk_appointment_booking_booker_account_id` FOREIGN KEY (`booker`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `fk_appointment_booking_medicar_account_id` FOREIGN KEY (`medicar`) REFERENCES `account` (`id`);

--
-- Constraints for table `appointment_patient`
--
ALTER TABLE `appointment_patient`
  ADD CONSTRAINT `fk_appointment_patient_appointment_id_appointment_booking_id` FOREIGN KEY (`appointment_id`) REFERENCES `appointment_booking` (`id`);

--
-- Constraints for table `appointment_prescription`
--
ALTER TABLE `appointment_prescription`
  ADD CONSTRAINT `fk_appointment_prescription_id_appointment_booking_id` FOREIGN KEY (`booking_id`) REFERENCES `appointment_booking` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK_CATEGORY_MAJOR` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_account_medicar` FOREIGN KEY (`medicar_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_comment_account_patient` FOREIGN KEY (`patient_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `custom_timings`
--
ALTER TABLE `custom_timings`
  ADD CONSTRAINT `fk_custom_timings_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `medicar_profile`
--
ALTER TABLE `medicar_profile`
  ADD CONSTRAINT `fk_doctor_profile_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK_r3mhugotx5tcjxteuti1xixkw` FOREIGN KEY (`sender`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_rg0ks2jlh334m1n721ao16ko8` FOREIGN KEY (`owner`) REFERENCES `account` (`id`);

--
-- Constraints for table `place`
--
ALTER TABLE `place`
  ADD CONSTRAINT `fk_place_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_place_cost` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `prescription_doctor_note`
--
ALTER TABLE `prescription_doctor_note`
  ADD CONSTRAINT `fk_appointment_doctor_note_id_appointment_prescription_id` FOREIGN KEY (`id`) REFERENCES `appointment_prescription` (`id`);

--
-- Constraints for table `prescription_medicine`
--
ALTER TABLE `prescription_medicine`
  ADD CONSTRAINT `fk_prescription_id` FOREIGN KEY (`prescription_id`) REFERENCES `appointment_prescription` (`id`);

--
-- Constraints for table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `FK_l3rl9odf1q3lfmc6hcd6keda9` FOREIGN KEY (`medicar_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `rate_trace`
--
ALTER TABLE `rate_trace`
  ADD CONSTRAINT `FK_renod4bjtwok8fhuhtemcw0st` FOREIGN KEY (`partient_id`) REFERENCES `account` (`id`);

--
-- Constraints for table `role_right`
--
ALTER TABLE `role_right`
  ADD CONSTRAINT `fk_role_right_clinic_right` FOREIGN KEY (`clinic_right_id`) REFERENCES `clinic_right` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_role_right_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `session_log`
--
ALTER TABLE `session_log`
  ADD CONSTRAINT `fk_sessionlog_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `subcategory`
--
ALTER TABLE `subcategory`
  ADD CONSTRAINT `fk_subcategory_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `timings`
--
ALTER TABLE `timings`
  ADD CONSTRAINT `fk_timings_account_timings_id_account_timings_id` FOREIGN KEY (`account_timings_id`) REFERENCES `account_timings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `working_history`
--
ALTER TABLE `working_history`
  ADD CONSTRAINT `fk_working_history_account_id_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
