--Create database
create database FTP01;

-- change this to your team id
use FTP01;

-- comment this line for the very first time
drop table if exists EMPLOYEE;

-- create the table
CREATE TABLE `ftp01`.`employee` (
  `EMP_ID` INT NOT NULL,
  `EMP_NAME` VARCHAR(45) NOT NULL,
  `EMP_PHONE` BIGINT(15) NULL,
  `EMP_EMAIL` VARCHAR(45) NOT NULL,
  `EMP_DEPT` VARCHAR(45) NULL,
  `EMP_MANAGER_ID` INT NULL,
  `EMP_LEAVE_BALANCE` INT NULL,
  `EMP_DOJ` DATE NOT NULL,
  PRIMARY KEY (`EMP_ID`),
  UNIQUE INDEX `EMP_PHONE_UNIQUE` (`EMP_PHONE` ASC),
  UNIQUE INDEX `EMPLOYEEcol_UNIQUE` (`EMP_EMAIL` ASC)CONSTRAINT `EMP_ID`
    FOREIGN KEY (`EMP_MANAGER_ID`)
    REFERENCES `ftp01`.`employee` (`EMP_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION));

-- if any leave_history table exists

drop table leave_history;

-- create the table LEAVE_HISTORY

CREATE TABLE `ftp01`.`leave_history` (
  `LEAVE_ID` INT NOT NULL,
  `LEAVE_TYPE` ENUM('EL') NOT NULL,
  `START_DATE` DATE NOT NULL,
  `END_DATE` DATE NOT NULL,
  `NUMBER_OF_DAYS` INT NULL,
  `LEAVE_STATUS` ENUM('APLLIED','DENIED,'PENDING') NULL,
  `LEAVE_REASON` TEXT(100) NULL,
  `LEAVE_APPLIED_ON` DATE NOT NULL,
  `MANAGER_COMMENTS` TEXT(100) NULL,
  `EMP_ID` INT NOT NULL,
  PRIMARY KEY (`LEAVE_ID`),
  INDEX `EMP_ID_idx` (`EMP_ID` ASC),
  CONSTRAINT `EMP_ID`
    FOREIGN KEY (`EMP_ID`)
    REFERENCES `ftp01`.`employee` (`EMP_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);