-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema kaliper_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema kaliper_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kaliper_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `kaliper_db` ;

-- -----------------------------------------------------
-- Table `kaliper_db`.`voucher_model`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`voucher_model` (
  `model_id` INT(11) NOT NULL AUTO_INCREMENT,
  `model_name` VARCHAR(45) NOT NULL,
  `model_desc` VARCHAR(255) NULL,
  `model_data` BLOB NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`model_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`voucher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`voucher` (
  `voucher_id` VARCHAR(128) NOT NULL,
  `voucher_code` VARCHAR(45) NOT NULL,
  `valid_from` TIMESTAMP NULL,
  `expiry_date` TIMESTAMP NULL,
  `revoked` TINYINT(1) NULL,
  `revoked_by` VARCHAR(45) NULL,
  `initial_credit` INT(11) NULL,
  `available_credit` INT(11) NULL,
  `created_by` VARCHAR(45) NULL,
  `category` INT NOT NULL,
  `status` INT NOT NULL,
  `model_id` INT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`voucher_id`),
  INDEX `index2` (`available_credit` ASC),
  INDEX `index3` (`voucher_code` ASC),
  INDEX `index4` (`status` ASC),
  INDEX `fk_voucher_1_idx` (`model_id` ASC),
  CONSTRAINT `fk_voucher_1`
    FOREIGN KEY (`model_id`)
    REFERENCES `kaliper_db`.`voucher_model` (`model_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`brand_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`brand_detail` (
  `brand_id` INT NOT NULL AUTO_INCREMENT,
  `brand_name` VARCHAR(45) NULL,
  `brand_desc` VARCHAR(255) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`brand_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`voucher_brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`voucher_brand` (
  `voucher_id` VARCHAR(128) NOT NULL,
  `brand_id` INT(11) NOT NULL,
  `status` INT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `fk_voucher_brands_1_idx` (`voucher_id` ASC),
  INDEX `fk_voucher_brands_2_idx` (`brand_id` ASC),
  CONSTRAINT `fk_voucher_brands_1`
    FOREIGN KEY (`voucher_id`)
    REFERENCES `kaliper_db`.`voucher` (`voucher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_voucher_brands_2`
    FOREIGN KEY (`brand_id`)
    REFERENCES `kaliper_db`.`brand_detail` (`brand_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`payment` (
  `payment_id` VARCHAR(128) NOT NULL,
  `order_ref` VARCHAR(45) NULL,
  `amount` INT(11) NULL,
  `billing_address` VARCHAR(80) NULL,
  `shipping_address` VARCHAR(80) NULL,
  `payment_status` INT NULL,
  `payment_flag` INT NULL,
  `client_ip` VARCHAR(45) NULL,
  `client_details` VARCHAR(45) NULL,
  `first_name` VARCHAR(80) NULL,
  `last_name` VARCHAR(80) NULL,
  `emai_id` VARCHAR(80) NULL,
  `phone_number` VARCHAR(20) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`payment_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`transaction` (
  `transaction_id` VARCHAR(128) NOT NULL,
  `payment_id` VARCHAR(128) NULL,
  `transaction_type` INT NULL,
  `transaction_state` INT NULL,
  `transaction_funnel_state` INT NULL,
  `track_id` VARCHAR(60) NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_transaction_1_idx` (`payment_id` ASC),
  CONSTRAINT `fk_transaction_1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `kaliper_db`.`payment` (`payment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`payment_voucher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`payment_voucher` (
  `payment_id` VARCHAR(128) NOT NULL,
  `voucher_id` VARCHAR(128) NOT NULL,
  `amount` INT(11) NOT NULL,
  INDEX `fk_payment_vouchers_1_idx` (`payment_id` ASC),
  INDEX `fk_payment_vouchers_2_idx` (`voucher_id` ASC),
  CONSTRAINT `fk_payment_vouchers_1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `kaliper_db`.`payment` (`payment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_vouchers_2`
    FOREIGN KEY (`voucher_id`)
    REFERENCES `kaliper_db`.`voucher` (`voucher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`transaction_voucher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`transaction_voucher` (
  `transaction_id` VARCHAR(128) NOT NULL,
  `voucher_id` VARCHAR(128) NOT NULL,
  `amount` INT(11) NULL,
  INDEX `fk_transaction_vouchers_2_idx` (`voucher_id` ASC),
  CONSTRAINT `fk_transaction_vouchers_1`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `kaliper_db`.`transaction` (`transaction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_vouchers_2`
    FOREIGN KEY (`voucher_id`)
    REFERENCES `kaliper_db`.`voucher` (`voucher_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kaliper_db`.`transaction_params`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kaliper_db`.`transaction_params` (
  `transaction_id` VARCHAR(128) NOT NULL,
  `key` VARCHAR(80) NULL,
  `value` VARCHAR(255) NULL,
  INDEX `fk_transaction_params_1_idx` (`transaction_id` ASC),
  CONSTRAINT `fk_transaction_params_1`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `kaliper_db`.`transaction` (`transaction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `kaliper_db`.`brand_detail`
-- -----------------------------------------------------
START TRANSACTION;
USE `kaliper_db`;
INSERT INTO `kaliper_db`.`brand_detail` (`brand_id`, `brand_name`, `brand_desc`, `create_time`, `update_time`) VALUES (NULL, 'DEFAULT_ALL', 'Vouchers with this brand support can be used with all brands in the brand table', NULL, NULL);
INSERT INTO `kaliper_db`.`brand_detail` (`brand_id`, `brand_name`, `brand_desc`, `create_time`, `update_time`) VALUES (NULL, 'NONE', 'Vouchers with this brand wont applied to any of the brand even it is added specifically', NULL, NULL);

COMMIT;

