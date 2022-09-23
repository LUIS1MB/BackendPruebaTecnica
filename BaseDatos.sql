
-- -----------------------------------------------------
-- Schema dbbanco
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbbanco` DEFAULT CHARACTER SET utf8 ;
USE `dbbanco` ;

-- -----------------------------------------------------
-- Table `dbbanco`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbbanco`.`Persona` (
  `id_persona` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(60) NULL,
  `genero` VARCHAR(10) NULL,
  `edad` INT NULL,
  `identificacion` VARCHAR(45) NULL,
  `direccion` VARCHAR(150) NULL,
  `telefono` VARCHAR(20) NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE INDEX `id_persona_UNIQUE` (`id_persona` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbanco`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbbanco`.`Cliente` (
  `id_persona` INT NOT NULL,
  `contrasena` VARCHAR(60) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id_persona`),
  CONSTRAINT `fk_Cliente_Persona1`
    FOREIGN KEY (`id_persona`)
    REFERENCES `dbbanco`.`Persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbanco`.`Cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbbanco`.`Cuenta` (
  `id_cuenta` INT NOT NULL AUTO_INCREMENT,
  `id_persona` INT NOT NULL,
  `numero_cuenta` VARCHAR(45) NULL,
  `tipo_cuenta` VARCHAR(45) NULL,
  `saldo_inicial` DECIMAL(14,2) NULL,
  `estado` TINYINT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE INDEX `id_cuenta_UNIQUE` (`id_cuenta` ASC) VISIBLE,
  UNIQUE INDEX `numero_cuenta_UNIQUE` (`numero_cuenta` ASC) VISIBLE,
  INDEX `fk_Cuenta_Cliente1_idx` (`id_persona` ASC) VISIBLE,
  CONSTRAINT `fk_Cuenta_Cliente1`
    FOREIGN KEY (`id_persona`)
    REFERENCES `dbbanco`.`Cliente` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbbanco`.`Movimiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbbanco`.`Movimiento` (
  `id_movimiento` INT NOT NULL AUTO_INCREMENT,
  `id_cuenta` INT NOT NULL,
  `fecha` DATETIME NULL,
  `tipo_movimiento` VARCHAR(45) NULL,
  `valor` DECIMAL(14,2) NULL,
  `saldo` DECIMAL(14,2) NULL,
  PRIMARY KEY (`id_movimiento`),
  UNIQUE INDEX `id_movimiento_UNIQUE` (`id_movimiento` ASC) VISIBLE,
  INDEX `fk_Movimiento_Cuenta1_idx` (`id_cuenta` ASC) VISIBLE,
  CONSTRAINT `fk_Movimiento_Cuenta1`
    FOREIGN KEY (`id_cuenta`)
    REFERENCES `dbbanco`.`Cuenta` (`id_cuenta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;