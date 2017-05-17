-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Horario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Horario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Horario` DEFAULT CHARACTER SET utf8 ;
USE `Horario` ;

-- -----------------------------------------------------
-- Table `Horario`.`Profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Horario`.`Profesor` (
  `numeroDePersonal` INT NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `apellidoPaterno` VARCHAR(20) NOT NULL,
  `apellidoMaterno` VARCHAR(20) NULL,
  `correoE` VARCHAR(20) NOT NULL,
  `telefono` INT NULL,
  PRIMARY KEY (`numeroDePersonal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Horario`.`Horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Horario`.`Horario` (
  `idHorario` INT NOT NULL AUTO_INCREMENT,
  `semestre` INT NULL,
  `carrera` VARCHAR(45) NULL,
  PRIMARY KEY (`idHorario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Horario`.`ExperienciaEducativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Horario`.`ExperienciaEducativa` (
  `nrc` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `creditos` INT NOT NULL,
  `Profesor_numeroDePersonal` INT NOT NULL,
  `Horario_idHorario` INT NOT NULL,
  PRIMARY KEY (`nrc`),
  INDEX `fk_ExperienciaEducativa_Profesor_idx` (`Profesor_numeroDePersonal` ASC),
  INDEX `fk_ExperienciaEducativa_Horario1_idx` (`Horario_idHorario` ASC),
  CONSTRAINT `fk_ExperienciaEducativa_Profesor`
    FOREIGN KEY (`Profesor_numeroDePersonal`)
    REFERENCES `Horario`.`Profesor` (`numeroDePersonal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExperienciaEducativa_Horario1`
    FOREIGN KEY (`Horario_idHorario`)
    REFERENCES `Horario`.`Horario` (`idHorario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Horario`.`Aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Horario`.`Aula` (
  `idAula` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NOT NULL,
  PRIMARY KEY (`idAula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Horario`.`Aula_has_ExperienciaEducativa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Horario`.`Aula_has_ExperienciaEducativa` (
  `Aula_idAula` INT NOT NULL,
  `ExperienciaEducativa_nrc` INT NOT NULL,
  PRIMARY KEY (`Aula_idAula`, `ExperienciaEducativa_nrc`),
  INDEX `fk_Aula_has_ExperienciaEducativa_ExperienciaEducativa1_idx` (`ExperienciaEducativa_nrc` ASC),
  INDEX `fk_Aula_has_ExperienciaEducativa_Aula1_idx` (`Aula_idAula` ASC),
  CONSTRAINT `fk_Aula_has_ExperienciaEducativa_Aula1`
    FOREIGN KEY (`Aula_idAula`)
    REFERENCES `Horario`.`Aula` (`idAula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Aula_has_ExperienciaEducativa_ExperienciaEducativa1`
    FOREIGN KEY (`ExperienciaEducativa_nrc`)
    REFERENCES `Horario`.`ExperienciaEducativa` (`nrc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Horario`.`Dia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Horario`.`Dia` (
  `idDia` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idDia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Horario`.`ExperienciaEducativa_has_Dia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Horario`.`ExperienciaEducativa_has_Dia` (
  `ExperienciaEducativa_nrc` INT NOT NULL,
  `Dia_idDia` INT NOT NULL,
  PRIMARY KEY (`ExperienciaEducativa_nrc`, `Dia_idDia`),
  INDEX `fk_ExperienciaEducativa_has_Dia_Dia1_idx` (`Dia_idDia` ASC),
  INDEX `fk_ExperienciaEducativa_has_Dia_ExperienciaEducativa1_idx` (`ExperienciaEducativa_nrc` ASC),
  CONSTRAINT `fk_ExperienciaEducativa_has_Dia_ExperienciaEducativa1`
    FOREIGN KEY (`ExperienciaEducativa_nrc`)
    REFERENCES `Horario`.`ExperienciaEducativa` (`nrc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExperienciaEducativa_has_Dia_Dia1`
    FOREIGN KEY (`Dia_idDia`)
    REFERENCES `Horario`.`Dia` (`idDia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
