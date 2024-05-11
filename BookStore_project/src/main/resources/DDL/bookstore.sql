CREATE DATABASE IF NOT EXISTS `bookstore`;
USE `bookstore`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `userprofile`;
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `bookcategory`;
DROP TABLE IF EXISTS `bookauthor`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `users` (
  userid int not NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role varchar(255) DEFAULT NULL,
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `userprofile` (
    userid int NOT NULL AUTO_INCREMENT,
    userfullname varchar(255) DEFAULT NULL,
    username varchar(255) DEFAULT NULL, 
    age int NOT NULL,
    phonenumber int, 
    PRIMARY KEY (userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `book` (
    idbook int NOT NULL AUTO_INCREMENT,
    title varchar(255) DEFAULT NULL,
    userid int NOT NULL,
    idauthor int not NULL,
    description text,
    PRIMARY KEY (idbook),
    FOREIGN KEY (userid) REFERENCES userprofile(userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `bookcategory` (
    idcategory int NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    userid int NOT NULL,
    PRIMARY KEY (idcategory),
    FOREIGN KEY (userid) REFERENCES userprofile(userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `bookauthor` (
    idauthor int NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    userid int NOT NULL,
    PRIMARY KEY (idauthor),
    FOREIGN KEY (userid) REFERENCES userprofile(userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `users` (username, password, role) VALUES
('john_doe', 'password123', 'admin'),
('jane_smith', 'secret456', 'user'),
('bob_jackson', 'bobspassword', 'user');

