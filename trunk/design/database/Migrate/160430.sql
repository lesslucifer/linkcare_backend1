ALTER TABLE `notification` ADD `params` TEXT NOT NULL AFTER `content`;
DELETE FROM `notification`