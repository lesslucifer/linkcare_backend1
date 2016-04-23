use cliniccore_db;

ALTER TABLE `cliniccore_db`.`subcategory` 
CHANGE COLUMN `name` `name` TEXT(1000) NULL DEFAULT NULL ;

ALTER TABLE `cliniccore_db`.`subcategory` 
CHANGE COLUMN `is_deleted` `is_deleted` TINYINT(1) NULL DEFAULT 0 ;

ALTER TABLE `cliniccore_db`.`subcategory` 
CHANGE COLUMN `name` `name` NVARCHAR(1000) NULL DEFAULT NULL ;

UPDATE `cliniccore_db`.`major` SET `name`='Điều dưỡng' WHERE `id`='6';
INSERT INTO `cliniccore_db`.`category` (`major_id`, `code`, `name`, `is_deleted`) VALUES ('6', 'CHAM_SOC_SAN', 'Chăm sóc sản', '0');
INSERT INTO `cliniccore_db`.`category` (`major_id`, `code`, `béname`, `is_deleted`) VALUES ('6', 'CHAM_SOC_BENH', 'Chăm sóc bệnh', '0');
ALTER TABLE `cliniccore_db`.`account` 
DROP COLUMN `loginName`;

UPDATE `cliniccore_db`.`subcategory` SET `name`='Tim mạch' WHERE `id`='1';
UPDATE `cliniccore_db`.`subcategory` SET `name`='Hô hấp' WHERE `id`='2';

INSERT INTO `cliniccore_db`.`subcategory` (`name`, `code`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES ('Tắm ', 'TAM_BE', '0', '10', '10', '30');
INSERT INTO `cliniccore_db`.`subcategory` (`name`, `code`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES ('Theo dõi vàng da', 'THEO_DOI_VANG_DA', '0', '10', '10', '30');
INSERT INTO `cliniccore_db`.`subcategory` (`name`, `code`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES ('Chăm sóc thiếu tháng', 'CHAM_SOC_THIEU_THANG', '0', '10', '10', '30');
INSERT INTO `cliniccore_db`.`subcategory` (`name`, `code`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES ('Chăm sóc vết mổ', 'CHAM_SOC_VET_MO', '0', '10', '10', '30');
INSERT INTO `cliniccore_db`.`subcategory` (`name`, `code`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES ('Chăm sóc hậu phẩu thuật', 'CHAM_SOC_HAU_PHAU_THUAT', '0', '10', '10', '30');
INSERT INTO `cliniccore_db`.`subcategory` (`name`, `code`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES ('Chăm sóc căng sữa', 'CHAM_SOC_CANG_SUA', '0', '10', '10', '30');
INSERT INTO `cliniccore_db`.`subcategory` (`name`, `code`, `is_deleted`, `category_id`, `clinic_dur`, `patient_home_dur`) VALUES ('Chăm sóc bệnh theo yêu cầu', 'CHAM_SOC_BENH_THEO_YEU_CAU', '0', '11', '10', '30');

INSERT INTO `cliniccore_db`.`account` (`annual_fee_id`, `place_id`, `subcategory_id`, `cost_id`, `address_id`, `experience`, `code`, `phone_number`, `id_card`, `first_name`, `last_name`, `midle_name`, `gender`, `login_name`, `email`, `birthday`, `image_url`, `hashed_password`, `active_flag`, `is_deleted`, `birdthday`) VALUES ('1', '1', '5', '1', '1', '2', 'NURSE000001', '0909448445', '215215115', 'Bích', 'Tô', 'Ngọc', '2', '215215115', 'bichto@gmail.com', '12234', '/var/hcmus/abc.png', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', '0');
INSERT INTO `cliniccore_db`.`account` (`annual_fee_id`, `place_id`, `subcategory_id`, `cost_id`, `address_id`, `experience`, `code`, `phone_number`, `id_card`, `first_name`, `last_name`, `midle_name`, `gender`, `login_name`, `email`, `birthday`, `image_url`, `hashed_password`, `active_flag`, `is_deleted`, `birdthday`) VALUES ('1', '1', '6', '1', '1', '3', 'NURSE000002', '0933002993', '215215116', 'Nguyên', 'Nguyễn', 'Thảo', '2', '215215116', 'nguyen@gmail.com', '323412', '/var/hcmus/abc.png', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', '0');
INSERT INTO `cliniccore_db`.`account` (`annual_fee_id`, `place_id`, `subcategory_id`, `cost_id`, `address_id`, `experience`, `code`, `phone_number`, `id_card`, `first_name`, `last_name`, `midle_name`, `gender`, `login_name`, `email`, `birthday`, `image_url`, `hashed_password`, `active_flag`, `is_deleted`, `birdthday`) VALUES ('1', '1', '7', '1', '1', '2', 'NURSE000003', '0989331242', '215215117', 'Ngô', 'Tô', 'Kim', '2', '215215117', 'nguyet@gmail.com', '23123', '/var/hcmus/abc.png', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', '0');
INSERT INTO `cliniccore_db`.`account` (`annual_fee_id`, `place_id`, `subcategory_id`, `cost_id`, `address_id`, `experience`, `code`, `phone_number`, `id_card`, `first_name`, `last_name`, `midle_name`, `gender`, `login_name`, `email`, `birthday`, `image_url`, `hashed_password`, `active_flag`, `is_deleted`, `birdthday`) VALUES ('1', '1', '8', '1', '1', '4', 'NURSE000004', '0967234455', '215215118', 'Vu', 'Nguyên', 'Văn', '1', '215215118', 'vu@gmail.com', '34121', '/var/hcmus/abc.png', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', '0');
INSERT INTO `cliniccore_db`.`account` (`annual_fee_id`, `place_id`, `subcategory_id`, `cost_id`, `address_id`, `experience`, `code`, `phone_number`, `id_card`, `first_name`, `last_name`, `midle_name`, `gender`, `login_name`, `email`, `birthday`, `image_url`, `hashed_password`, `active_flag`, `is_deleted`, `birdthday`) VALUES ('1', '1', '9', '1', '1', '4', 'NURSE000005', '0967234455', '215215119', 'Tuan', 'Nguyên', 'Văn', '1', '215215119', 'tuan@gmail.com', '34121', '/var/hcmus/abc.png', 'e10adc3949ba59abbe56e057f20f883e', '1', '0', '0');

INSERT INTO `cliniccore_db`.`role` (`code`, `name`, `description`, `is_deleted`) VALUES ('NURSE_ROLE', 'Nurse\'s role', 'Nurse\'s role', '0');

INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('3', '2', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('3', '3', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('3', '4', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('3', '5', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('3', '6', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('2', '2', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('2', '3', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('2', '4', '0');
INSERT INTO `cliniccore_db`.`account_role` (`role_id`, `account_id`, `is_deleted`) VALUES ('2', '5', '0');
