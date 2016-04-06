-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 06, 2016 at 04:41 PM
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

INSERT INTO `account` (`id`, `annual_fee_id`, `place_id`, `subcategory_id`, `cost_id`, `address_id`, `experience`, `code`, `phone_number`, `id_card`, `passport`, `first_name`, `last_name`, `midle_name`, `gender`, `login_name`, `email`, `birthday`, `image_url`, `facebook`, `facebook_id`, `google_plus_id`, `website`, `hashed_password`, `need_change_pwd`, `active_flag`, `begin_active_time`, `end_active_time`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`, `birdthday`, `rate_id`, `loginName`) VALUES
(1, 1, 1, 1, 1, 1, 9, 'DOCTOR000001', '01687482737', '215215883', NULL, 'Vương', 'Đỗ', 'a', 1, '215215883', 'vuongdt92@gmail.com', 13254, '/var/hcmus/abc.png', NULL, NULL, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, NULL, '');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `begin_date` date NOT NULL,
  `created_datetime` bigint(11) UNSIGNED DEFAULT NULL,
  `created_by` int(10) UNSIGNED DEFAULT NULL,
  `last_updated` bigint(11) UNSIGNED DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `account_timings_account_id_begin` (`account_id`,`begin_date`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_timings`
--

INSERT INTO `account_timings` (`id`, `account_id`, `begin_date`, `created_datetime`, `created_by`, `last_updated`, `last_updated_by`, `is_deleted`) VALUES
(9, 1, '2016-03-28', 1459153622240, 0, 1459153650025, 0, 0),
(10, 1, '2016-03-28', 1459153862782, 0, 1459153862782, 0, 0),
(11, 1, '2016-03-28', 1459153923137, 0, 1459153923137, 0, 0),
(12, 1, '2016-03-28', 1459600968387, 0, 1459600968387, 0, 0);

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE IF NOT EXISTS `appointment` (
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
  KEY `fk_appointment_status_idx` (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment_booking`
--

INSERT INTO `appointment_booking` (`id`, `booker`, `medicar`, `cost`, `home`, `address_id`, `date`, `time`, `duration`, `status`, `is_deleted`, `created_by`, `created_datetime`, `last_updated`, `last_updated_by`) VALUES
(2, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 4, NULL, NULL, NULL, 1459958226611, NULL),
(3, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 1, NULL, NULL, NULL, 1459958234604, NULL),
(9, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 2, NULL, NULL, NULL, 1459958399588, NULL),
(10, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 0, NULL, NULL, NULL, NULL, NULL),
(11, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 0, NULL, NULL, NULL, NULL, NULL),
(12, 1, 1, '1000', 1, 1, '2016-03-30', 480, 30, 0, NULL, NULL, NULL, NULL, NULL);

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
(12, 'Quang Vu', '1992-06-02', 0, 'Ho chi minh', 'Dau dau, so mui, chong mat, buon non!');

-- --------------------------------------------------------

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
CREATE TABLE IF NOT EXISTS `calendar` (
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
  KEY `FK_grar0vt1grsig5irlyh9n6btw` (`configuration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
(7, 'RIGHT_GET_MEDICAR_APPOINTMENT', 'Get medicar appointments', 'Get medicar appointments', 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
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
  KEY `fk_comment_account_patient_idx` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
CREATE TABLE IF NOT EXISTS `configuration` (
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

-- --------------------------------------------------------

--
-- Table structure for table `cost`
--

DROP TABLE IF EXISTS `cost`;
CREATE TABLE IF NOT EXISTS `cost` (
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  KEY `fk_place_cost_idx` (`cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `place`
--

INSERT INTO `place` (`id`, `address_id`, `cost_id`, `code`, `name`, `description`, `note`, `phone_number`, `email`, `website`, `fax`, `daily_open_time`, `daily_close_time`, `image_url`, `status`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`, `longtitude`, `node`, `latitude`) VALUES
(1, 2, 2, 'PK_DOCTOR_00002', 'PK KMS 1', NULL, NULL, '233123', 'ck@gmail.com', 'xxx.com', NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `code`, `name`, `description`, `is_deleted`, `created_by`, `created_datetime`, `last_updated_by`, `last_updated`) VALUES
(1, 'DOCTOR_ROLE', 'Doctor''s roles', 'Doctor''s roles', 0, NULL, NULL, NULL, NULL),
(2, 'PATIENT_ROLE', 'Patient''s role', 'Patient''s role', 0, NULL, NULL, NULL, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
(7, 1, 7, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `session_log`
--

DROP TABLE IF EXISTS `session_log`;
CREATE TABLE IF NOT EXISTS `session_log` (
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
  KEY `session_id` (`session_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session_log`
--

INSERT INTO `session_log` (`id`, `session_id`, `login_time`, `logout_time`, `expired_time`, `account_id`, `is_deleted`, `last_updated`, `last_updated_by`, `created_by`, `created_datetime`) VALUES
(1, 'c2a22134-1e1c-4f60-b3ff-f94f51214494', 1459066742537, NULL, 1459068542537, 1, 0, 1459066742537, 0, 0, 1459066742537),
(2, 'bc87f2eb-54ae-460f-890e-2bb81441fdff', 1459066745907, NULL, 1459068545907, 1, 0, 1459066745907, 0, 0, 1459066745907),
(3, '2085c6f0-02ab-4d37-9f93-3a8c767fdf53', 1459066746514, NULL, 1459068546514, 1, 0, 1459066746514, 0, 0, 1459066746514),
(4, 'ceb125c9-dbf4-45e7-a56e-863bb7eddf5d', 1459068909111, NULL, 1459070709111, 1, 0, 1459068909111, 0, 0, 1459068909111),
(5, '04a325d8-a773-4659-81cc-82eb50257f64', 1459070760385, NULL, 1459072560385, 1, 0, 1459070760385, 0, 0, 1459070760385),
(6, '01e42691-4818-441a-9a02-e4375b4081a5', 1459085433610, NULL, 1459087233610, 1, 0, 1459085433610, 0, 0, 1459085433610),
(7, 'a6c321a6-27a7-4d9e-9fac-a48a613b01f1', 1459089556865, NULL, 1459091356865, 1, 0, 1459089556865, 0, 0, 1459089556865),
(8, '33030de6-4fba-432a-8367-d9963e3d81e2', 1459089626288, NULL, 1459091426288, 1, 0, 1459089626288, 0, 0, 1459089626288),
(9, '9ab45649-db7b-41c3-9579-632a6bb25b4f', 1459089646081, NULL, 1459091446081, 1, 0, 1459089646081, 0, 0, 1459089646081),
(10, 'be0c143d-08e7-4e60-8660-b11548e34aeb', 1459146636885, NULL, 1459148436885, 1, 0, 1459146636885, 0, 0, 1459146636885),
(11, '987f1a35-d48c-4d2c-9bfd-bcc3e0b9c966', 1459148950599, NULL, 1459150750599, 1, 0, 1459148950599, 0, 0, 1459148950599),
(12, 'f7fb5322-1b38-49da-9e8c-d5ddd140afe8', 1459151768923, NULL, 1459153568923, 1, 0, 1459151768923, 0, 0, 1459151768923),
(13, 'e9990702-5d93-4fb7-a1f7-e9fe74d8e3c2', 1459153614575, NULL, 1459155414575, 1, 0, 1459153614575, 0, 0, 1459153614575),
(14, '5ee90aab-7603-4644-96d2-1c04d3634c5b', 1459156587204, NULL, 1459158387204, 1, 0, 1459156587205, 0, 0, 1459156587205),
(15, 'b286207b-4849-4d50-b115-05072164b3cc', 1459176220624, NULL, 1459178020624, 1, 0, 1459176220625, 0, 0, 1459176220625),
(16, '146b0c9e-a924-4660-a038-5bae30e65518', 1459177840458, NULL, 1459179640458, 1, 0, 1459177840458, 0, 0, 1459177840458),
(17, '077839ae-2aaf-40c3-b6ff-d3bec83c799c', 1459180687621, NULL, 1459182487621, 1, 0, 1459180687621, 0, 0, 1459180687621),
(18, 'cbb361b2-12e2-4666-81be-1cf3ad3b4d9e', 1459183194724, NULL, 1459184994724, 1, 0, 1459183194724, 0, 0, 1459183194724),
(19, '7596ec8b-d0ba-49a0-9964-5c5bc63326c7', 1459258325696, NULL, 1459260125696, 1, 0, 1459258325696, 0, 0, 1459258325696),
(20, '9324ee55-da37-4fab-9a66-2ac709ad834b', 1459260834039, NULL, 1459262634039, 1, 0, 1459260834040, 0, 0, 1459260834040),
(21, '3b1d5f7c-f6c7-4d07-a8f7-f13500cb5b6e', 1459317403898, NULL, 1459319203898, 1, 0, 1459317403899, 0, 0, 1459317403899),
(22, '362959e9-c72a-4384-b6b8-885773fcb00d', 1459320393000, NULL, 1459322193000, 1, 0, 1459320393000, 0, 0, 1459320393000),
(23, 'de133103-99bb-4384-b6a3-89129d4f2572', 1459322290706, NULL, 1459324090706, 1, 0, 1459322290706, 0, 0, 1459322290706),
(24, 'dd1cfa18-fe28-46ef-93ff-76c470432595', 1459327686286, NULL, 1459329486286, 1, 0, 1459327686287, 0, 0, 1459327686287),
(25, '7eab3ffe-0b72-4a95-b291-f560506a3118', 1459335111508, NULL, 1459336911508, 1, 0, 1459335111509, 0, 0, 1459335111509),
(26, '51ad26d6-5e0d-4400-8c64-803003104289', 1459424265475, NULL, 1459426065475, 1, 0, 1459424265475, 0, 0, 1459424265475),
(27, 'cf3be09d-7b87-4193-9333-b2ce1fdd7bee', 1459524222875, NULL, 1459526022875, 1, 0, 1459524222876, 0, 0, 1459524222876),
(28, 'a3b35f34-22f2-4bd8-8faf-92a095ea9ec5', 1459524354614, NULL, 1459526154614, 1, 0, 1459524354614, 0, 0, 1459524354614),
(29, '2ff03f91-641b-428b-a965-48959de0d86c', 1459526104480, NULL, 1459527904480, 1, 0, 1459526104481, 0, 0, 1459526104481),
(30, '83677400-e99a-4a24-bcfb-4019c61a039e', 1459562456777, NULL, 1459564256777, 1, 0, 1459562456778, 0, 0, 1459562456777),
(31, 'a1b25422-5c27-4406-b73c-fda04ec523fa', NULL, NULL, 1459601700860, 1, 0, 1459600500861, 1, 1, 1459600500860),
(32, '34a47eb9-b0be-48ac-9918-d718f723d503', NULL, NULL, 1459601751778, 1, 0, 1459600551778, 1, 1, 1459600551778),
(33, '3cdebdd2-bda3-448a-98e0-310c347c196f', NULL, NULL, 1459602960945, 1, 0, 1459601760945, 1, 1, 1459601760945),
(34, 'c08465cf-a572-4d88-a55c-ce97bfb25cce', NULL, NULL, 1459603802745, 1, 0, 1459602602745, 1, 1, 1459602602745),
(35, '3b348919-e869-45e6-b9c2-1a436cb59c96', NULL, NULL, 1459958019356, 1, 0, 1459956819357, 1, 1, 1459956819356),
(36, '5703775f-6036-4ca8-9e1b-6a62e5a19702', NULL, NULL, 1459959318326, 1, 0, 1459958118326, 1, 1, 1459958118326),
(37, '0786ef80-d65a-4bc2-86fa-992588bf4fa6', NULL, NULL, 1459959712015, 1, 0, 1459958512016, 1, 1, 1459958512015),
(38, '39d73f29-0624-407f-843e-246cd0539905', NULL, NULL, 1459961374312, 1, 0, 1459960174313, 1, 1, 1459960174312),
(39, '430546b2-a491-4d58-b5f1-844dd5647700', NULL, NULL, 1459961924066, 1, 0, 1459960724066, 1, 1, 1459960724066);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
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

-- --------------------------------------------------------

--
-- Table structure for table `subcategory`
--

DROP TABLE IF EXISTS `subcategory`;
CREATE TABLE IF NOT EXISTS `subcategory` (
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
  `clinic_dur` smallint(5) UNSIGNED NOT NULL,
  `patient_home_dur` smallint(5) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_subcategory_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

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
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_appointment_account_0` FOREIGN KEY (`patient`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appointment_acocunt_1` FOREIGN KEY (`medicar`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appointment_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appointment_calendar` FOREIGN KEY (`calendar_id`) REFERENCES `calendar` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appointment_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
-- Constraints for table `calendar`
--
ALTER TABLE `calendar`
  ADD CONSTRAINT `FK_grar0vt1grsig5irlyh9n6btw` FOREIGN KEY (`configuration_id`) REFERENCES `configuration` (`id`),
  ADD CONSTRAINT `fk_calendar_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
-- Constraints for table `place`
--
ALTER TABLE `place`
  ADD CONSTRAINT `fk_place_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_place_cost` FOREIGN KEY (`cost_id`) REFERENCES `cost` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `FK_l3rl9odf1q3lfmc6hcd6keda9` FOREIGN KEY (`medicar_id`) REFERENCES `account` (`id`);

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
