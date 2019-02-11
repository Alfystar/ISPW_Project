-- MySQL Script generated by MySQL Workbench
-- Mon Feb 11 10:23:56 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fersa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fersa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fersa` ;
USE `fersa` ;

-- -----------------------------------------------------
-- Table `fersa`.`publicdata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fersa`.`publicdata` (
  `taxCode` VARCHAR(16) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `birthday` VARCHAR(10) NOT NULL,
  `gender` ENUM('MAN', 'WOMAN') NOT NULL,
  `socStat` VARCHAR(45) NULL DEFAULT 'unknown',
  `usImg` VARCHAR(45) NULL DEFAULT 'default-Avatar',
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`taxCode`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fersa`.`privatedata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fersa`.`privatedata` (
  `idPrD` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `cityOfBirth` VARCHAR(45) NULL,
  `nationality` VARCHAR(45) NULL,
  PRIMARY KEY (`idPrD`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fersa`.`answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fersa`.`answers` (
  `idAnsw` INT NOT NULL AUTO_INCREMENT,
  `answ1` VARCHAR(45) NOT NULL,
  `answ2` VARCHAR(45) NOT NULL,
  `answ3` VARCHAR(45) NOT NULL,
  `answ4` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAnsw`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fersa`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fersa`.`user` (
  `nick` VARCHAR(45) NOT NULL,
  `pubD_Tc` VARCHAR(16) NOT NULL,
  `prD_id` INT NOT NULL,
  `userStatus` ENUM('ACTIVE', 'INACTIVE', 'CANCELLED', 'BANNED') NOT NULL DEFAULT 'ACTIVE',
  `pw` VARCHAR(45) NOT NULL,
  `answ_id` INT NOT NULL,
  `roles` VARBINARY(2) NOT NULL DEFAULT '00' COMMENT '00 isRegistered\n10 isLocatore\n01 ilLocatario\n11 entrambi',
  PRIMARY KEY (`nick`),
  UNIQUE INDEX `PublicData_taxCode_UNIQUE` (`pubD_Tc` ASC) VISIBLE,
  UNIQUE INDEX `PrivateData_idPrivateData_UNIQUE` (`prD_id` ASC) VISIBLE,
  UNIQUE INDEX `Answers_idAnswers_UNIQUE` (`answ_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_PublicData`
    FOREIGN KEY (`pubD_Tc`)
    REFERENCES `fersa`.`publicdata` (`taxCode`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_PrivateData`
    FOREIGN KEY (`prD_id`)
    REFERENCES `fersa`.`privatedata` (`idPrD`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Answers`
    FOREIGN KEY (`answ_id`)
    REFERENCES `fersa`.`answers` (`idAnsw`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fersa`.`dateevent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fersa`.`dateevent` (
  `idDate` VARCHAR(10) NOT NULL,
  `nick` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDate`, `nick`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fersa`.`deletesession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fersa`.`deletesession` (
  `nextDelS` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`nextDelS`))
ENGINE = InnoDB;

USE `fersa` ;

-- -----------------------------------------------------
-- Placeholder table for view `fersa`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fersa`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `fersa`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fersa`.`view1`;
USE `fersa`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;