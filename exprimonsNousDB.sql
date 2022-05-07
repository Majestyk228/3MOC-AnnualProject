
-- -----------------------------------------------------
-- Schema exprimonsNous
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `exprimonsNous` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `exprimonsNous` ;

-- -----------------------------------------------------
-- Table `exprimonsNous`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Admin` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`idAdmin`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(50) NOT NULL,
  `lastName` VARCHAR(50) NOT NULL,
  `birthDate` DATE NOT NULL,
  `gender` VARCHAR(50) NOT NULL,
  `areaCode` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` TEXT NULL DEFAULT NULL,
  `points` INT NOT NULL,
  `signInDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Administers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Administers` (
  `idUser` INT NOT NULL,
  `idAdmin` INT NOT NULL,
  `date` DATE NOT NULL,
  `label` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idUser`, `idAdmin`),
  INDEX `Administers_Admin0_FK` (`idAdmin` ASC) VISIBLE,
  CONSTRAINT `Administers_Admin0_FK`
    FOREIGN KEY (`idAdmin`)
    REFERENCES `exprimonsNous`.`Admin` (`idAdmin`),
  CONSTRAINT `Administers_User_FK`
    FOREIGN KEY (`idUser`)
    REFERENCES `exprimonsNous`.`User` (`idUser`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Community`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Community` (
  `idCommunity` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(50) NOT NULL,
  `enterprise` TINYINT(1) NOT NULL,
  `description` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idCommunity`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Associate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Associate` (
  `idUser` INT NOT NULL,
  `idCommunity` INT NOT NULL,
  PRIMARY KEY (`idUser`, `idCommunity`),
  INDEX `Associate_Community0_FK` (`idCommunity` ASC) VISIBLE,
  CONSTRAINT `Associate_Community0_FK`
    FOREIGN KEY (`idCommunity`)
    REFERENCES `exprimonsNous`.`Community` (`idCommunity`),
  CONSTRAINT `Associate_User_FK`
    FOREIGN KEY (`idUser`)
    REFERENCES `exprimonsNous`.`User` (`idUser`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Vote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Vote` (
  `idVote` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `body` TEXT NULL DEFAULT NULL,
  `nbChoices` INT NOT NULL,
  `important` TINYINT(1) NOT NULL,
  `idUser` INT NULL DEFAULT NULL,
  `idAdmin` INT NULL DEFAULT NULL,
  `voteBegins` DATE NULL DEFAULT NULL,
  `voteEnds` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idVote`),
  INDEX `Vote_User_FK` (`idUser` ASC) VISIBLE,
  INDEX `Vote_Admin0_FK` (`idAdmin` ASC) VISIBLE,
  CONSTRAINT `Vote_Admin0_FK`
    FOREIGN KEY (`idAdmin`)
    REFERENCES `exprimonsNous`.`Admin` (`idAdmin`),
  CONSTRAINT `Vote_User_FK`
    FOREIGN KEY (`idUser`)
    REFERENCES `exprimonsNous`.`User` (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`VoteOptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`VoteOptions` (
  `idVoteOptions` INT NOT NULL AUTO_INCREMENT,
  `label` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idVoteOptions`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Chose`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Chose` (
  `idVoteOptions` INT NOT NULL,
  `idVote` INT NOT NULL,
  PRIMARY KEY (`idVoteOptions`, `idVote`),
  INDEX `Chose_Vote0_FK` (`idVote` ASC) VISIBLE,
  CONSTRAINT `Chose_Vote0_FK`
    FOREIGN KEY (`idVote`)
    REFERENCES `exprimonsNous`.`Vote` (`idVote`),
  CONSTRAINT `Chose_VoteOptions_FK`
    FOREIGN KEY (`idVoteOptions`)
    REFERENCES `exprimonsNous`.`VoteOptions` (`idVoteOptions`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Post` (
  `idPost` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `body` TEXT NULL DEFAULT NULL,
  `date` DATE NOT NULL,
  `time` TIME NULL DEFAULT NULL,
  `likes` INT NOT NULL,
  `dislikes` INT NOT NULL,
  `idCommunity` INT NOT NULL,
  `idUser` INT NULL DEFAULT NULL,
  `idAdmin` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idPost`),
  INDEX `Post_Community_FK` (`idCommunity` ASC) VISIBLE,
  INDEX `Post_User0_FK` (`idUser` ASC) VISIBLE,
  INDEX `Post_Admin1_FK` (`idAdmin` ASC) VISIBLE,
  CONSTRAINT `Post_Admin1_FK`
    FOREIGN KEY (`idAdmin`)
    REFERENCES `exprimonsNous`.`Admin` (`idAdmin`),
  CONSTRAINT `Post_Community_FK`
    FOREIGN KEY (`idCommunity`)
    REFERENCES `exprimonsNous`.`Community` (`idCommunity`),
  CONSTRAINT `Post_User0_FK`
    FOREIGN KEY (`idUser`)
    REFERENCES `exprimonsNous`.`User` (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Comment` (
  `idComment` INT NOT NULL AUTO_INCREMENT,
  `body` TEXT NULL DEFAULT NULL,
  `likes` INT NOT NULL,
  `dislikes` INT NOT NULL,
  `reports` INT NULL DEFAULT NULL,
  `anonymous` TINYINT(1) NOT NULL,
  `idPost` INT NOT NULL,
  `idUser` INT NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idComment`),
  INDEX `Comment_Post_FK` (`idPost` ASC) VISIBLE,
  INDEX `Comment_User0_FK` (`idUser` ASC) VISIBLE,
  CONSTRAINT `Comment_Post_FK`
    FOREIGN KEY (`idPost`)
    REFERENCES `exprimonsNous`.`Post` (`idPost`),
  CONSTRAINT `Comment_User0_FK`
    FOREIGN KEY (`idUser`)
    REFERENCES `exprimonsNous`.`User` (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Rewards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Rewards` (
  `idRewards` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `urlMedia` VARCHAR(50) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `score` INT NOT NULL,
  `cost` INT NOT NULL,
  PRIMARY KEY (`idRewards`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Congratulate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Congratulate` (
  `idRewards` INT NOT NULL,
  `idPost` INT NOT NULL,
  PRIMARY KEY (`idRewards`, `idPost`),
  INDEX `Congratulate_Post0_FK` (`idPost` ASC) VISIBLE,
  CONSTRAINT `Congratulate_Post0_FK`
    FOREIGN KEY (`idPost`)
    REFERENCES `exprimonsNous`.`Post` (`idPost`),
  CONSTRAINT `Congratulate_Rewards_FK`
    FOREIGN KEY (`idRewards`)
    REFERENCES `exprimonsNous`.`Rewards` (`idRewards`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Enriches`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Enriches` (
  `idComment` INT NOT NULL,
  `idRewards` INT NOT NULL,
  PRIMARY KEY (`idComment`, `idRewards`),
  INDEX `Enriches_REWARDS0_FK` (`idRewards` ASC) VISIBLE,
  CONSTRAINT `Enriches_Comment_FK`
    FOREIGN KEY (`idComment`)
    REFERENCES `exprimonsNous`.`Comment` (`idComment`),
  CONSTRAINT `Enriches_REWARDS0_FK`
    FOREIGN KEY (`idRewards`)
    REFERENCES `exprimonsNous`.`Rewards` (`idRewards`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Invitation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Invitation` (
  `code` INT NOT NULL AUTO_INCREMENT,
  `idCommunity` INT NOT NULL,
  `creationDate` DATE NULL DEFAULT NULL,
  `endDate` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`code`),
  INDEX `Invitation_Community_FK` (`idCommunity` ASC) VISIBLE,
  CONSTRAINT `Invitation_Community_FK`
    FOREIGN KEY (`idCommunity`)
    REFERENCES `exprimonsNous`.`Community` (`idCommunity`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `exprimonsNous`.`Votes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `exprimonsNous`.`Votes` (
  `idUser` INT NOT NULL,
  `idVote` INT NOT NULL,
  `choice` INT NOT NULL,
  PRIMARY KEY (`idUser`, `idVote`),
  INDEX `Votes_Vote0_FK` (`idVote` ASC) VISIBLE,
  CONSTRAINT `Votes_User_FK`
    FOREIGN KEY (`idUser`)
    REFERENCES `exprimonsNous`.`User` (`idUser`),
  CONSTRAINT `Votes_Vote0_FK`
    FOREIGN KEY (`idVote`)
    REFERENCES `exprimonsNous`.`Vote` (`idVote`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

