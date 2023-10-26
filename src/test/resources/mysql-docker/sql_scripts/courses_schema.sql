-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema courses
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `courses` ;

-- -----------------------------------------------------
-- Schema courses
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `courses` DEFAULT CHARACTER SET utf8 ;
USE `courses` ;

-- -----------------------------------------------------
-- Table `courses`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`teacher` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`degree` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`course` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `teacher_id` INT UNSIGNED NOT NULL,
  `degree_id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(200) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_course_teacher1_idx` (`teacher_id` ASC) VISIBLE,
  INDEX `fk_course_degree1_idx` (`degree_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  CONSTRAINT `fk_course_teacher1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `courses`.`teacher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_degree1`
    FOREIGN KEY (`degree_id`)
    REFERENCES `courses`.`degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`student` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`course_has_student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`course_has_student` (
  `course_id` INT UNSIGNED NOT NULL,
  `student_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`course_id`, `student_id`),
  INDEX `fk_course_has_student_student1_idx` (`student_id` ASC) VISIBLE,
  INDEX `fk_course_has_student_course_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_course_has_student_course`
    FOREIGN KEY (`course_id`)
    REFERENCES `courses`.`course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_has_student_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `courses`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`student_has_degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`student_has_degree` (
  `degree_id` INT UNSIGNED NOT NULL,
  `student_id` INT UNSIGNED NOT NULL,
  `earning_date` DATE NOT NULL,
  PRIMARY KEY (`degree_id`, `student_id`),
  INDEX `fk_degree_has_student_student1_idx` (`student_id` ASC) VISIBLE,
  INDEX `fk_degree_has_student_degree1_idx` (`degree_id` ASC) VISIBLE,
  CONSTRAINT `fk_degree_has_student_degree1`
    FOREIGN KEY (`degree_id`)
    REFERENCES `courses`.`degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_degree_has_student_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `courses`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`teacher_has_degree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`teacher_has_degree` (
  `teacher_id` INT UNSIGNED NOT NULL,
  `degree_id` INT UNSIGNED NOT NULL,
  `earning_date` DATE NOT NULL,
  PRIMARY KEY (`teacher_id`, `degree_id`),
  INDEX `fk_teacher_has_degree_degree1_idx` (`degree_id` ASC) VISIBLE,
  INDEX `fk_teacher_has_degree_teacher1_idx` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `fk_teacher_has_degree_teacher1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `courses`.`teacher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_teacher_has_degree_degree1`
    FOREIGN KEY (`degree_id`)
    REFERENCES `courses`.`degree` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`app_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`app_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `teacher_id` INT UNSIGNED NULL,
  `student_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_user_teacher1_idx` (`teacher_id` ASC) VISIBLE,
  INDEX `fk_user_student1_idx` (`student_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_teacher1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `courses`.`teacher` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `courses`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`user_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `access_level` TINYINT UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courses`.`app_user_has_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `courses`.`app_user_has_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_has_user_role_user_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_has_user_role_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_user_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `courses`.`app_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_role_user_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `courses`.`user_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
