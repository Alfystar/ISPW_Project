-- MySQL Script generated by MySQL Workbench
-- Wed Feb  6 12:33:29 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema User
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema User
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `User` ;
USE `User` ;

-- -----------------------------------------------------
-- Table `User`.`PublicData`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`PublicData` (
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
-- Table `User`.`PrivateData`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`PrivateData` (
  `idPrD` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `cityOfBirth` VARCHAR(45) NULL,
  `nationality` VARCHAR(45) NULL,
  PRIMARY KEY (`idPrD`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `User`.`Answers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`Answers` (
  `idAnsw` INT NOT NULL AUTO_INCREMENT,
  `answ1` VARCHAR(45) NOT NULL,
  `answ2` VARCHAR(45) NOT NULL,
  `answ3` VARCHAR(45) NOT NULL,
  `answ4` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAnsw`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `User`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`User` (
  `nick` VARCHAR(45) NOT NULL,
  `pubD_Tc` VARCHAR(16) NOT NULL,
  `prD_id` INT NOT NULL,
  `userStatus` ENUM('ACTIVE', 'INACTIVE', 'CANCELLED', 'BANNED') NOT NULL DEFAULT 'ACTIVE',
  `pw` VARCHAR(45) NOT NULL,
  `answ_id` INT NOT NULL,
  `roles` VARBINARY(2) NOT NULL DEFAULT '00' COMMENT '00 isRegistered\n10 isLocatore\n01 ilLocatario\n11 entrambi',
  PRIMARY KEY (`nick`),
  UNIQUE INDEX `PublicData_taxCode_UNIQUE` (`pubD_Tc` ASC),
  UNIQUE INDEX `PrivateData_idPrivateData_UNIQUE` (`prD_id` ASC),
  UNIQUE INDEX `Answers_idAnswers_UNIQUE` (`answ_id` ASC),
  CONSTRAINT `fk_User_PublicData`
    FOREIGN KEY (`pubD_Tc`)
    REFERENCES `User`.`PublicData` (`taxCode`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_PrivateData`
    FOREIGN KEY (`prD_id`)
    REFERENCES `User`.`PrivateData` (`idPrD`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Answers`
    FOREIGN KEY (`answ_id`)
    REFERENCES `User`.`Answers` (`idAnsw`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `User`.`dateEvent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`dateEvent` (
  `idDate` VARCHAR(10) NOT NULL,
  `nick` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDate`, `nick`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `User`.`deleteSession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`deleteSession` (
  `nextDelS` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`nextDelS`))
ENGINE = InnoDB;

USE `User` ;

-- -----------------------------------------------------
-- Placeholder table for view `User`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `User`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User`.`view1`;
USE `User`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
