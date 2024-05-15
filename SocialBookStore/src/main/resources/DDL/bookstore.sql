CREATE DATABASE IF NOT EXISTS `bookstore`;
USE `bookstore`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `userprofile`;
DROP TABLE IF EXISTS `book`;
DROP TABLE IF EXISTS `bookcategory`;
DROP TABLE IF EXISTS `bookauthor`;
DROP TABLE IF EXISTS `user_authors`;
DROP TABLE IF EXISTS `user_categories`;
DROP TABLE IF EXISTS `book_author_book`;
DROP TABLE IF EXISTS `user_book_offers` ;
DROP TABLE IF EXISTS `user_requested_books`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `users` (
  userid int not NULL AUTO_INCREMENT,
  username varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role varchar(255) DEFAULT 'guest',
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `userprofile` (
    userprofile_id int NOT NULL AUTO_INCREMENT,
    userid int NOT NULL,
    fullname varchar(255) DEFAULT NULL,
    username varchar(255) DEFAULT NULL, 
    age int NOT NULL,
    address varchar(255),
    phonenumber int, 
    PRIMARY KEY (userprofile_id),
    UNIQUE KEY `unique_userid` (userid),
    FOREIGN KEY (userid) REFERENCES `users`(userid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `bookcategory` (
    categoryid int NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    PRIMARY KEY (categoryid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `bookauthor` (
    authorid int NOT NULL AUTO_INCREMENT,
    name varchar(255) DEFAULT NULL,
    PRIMARY KEY (authorid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `book` (
    bookid int NOT NULL AUTO_INCREMENT,
    title varchar(255) DEFAULT NULL,
    userprofile_id int NOT NULL,
    authorid int not NULL,
    description text,
    PRIMARY KEY (bookid),
    FOREIGN KEY (userprofile_id) REFERENCES `userprofile`(userprofile_id),
    FOREIGN KEY (authorid) REFERENCES `bookauthor`(authorid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_categories` (
  userprofile_id int NOT NULL,
  categoryid int NOT NULL,
  PRIMARY KEY (userprofile_id, categoryid),
  FOREIGN KEY (userprofile_id) REFERENCES `userprofile`(userprofile_id),
  FOREIGN KEY (categoryid) REFERENCES `bookcategory`(categoryid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_authors` (
  userprofile_id int NOT NULL,
  authorid int NOT NULL,
  PRIMARY KEY (userprofile_id, authorid),
  FOREIGN KEY (userprofile_id) REFERENCES `userprofile`(userprofile_id),
  FOREIGN KEY (authorid) REFERENCES `bookauthor`(authorid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_book_offers` (
  `userprofile_id` INT NOT NULL,
  `bookid` INT NOT NULL,
  PRIMARY KEY (`userprofile_id`, `bookid`),
  FOREIGN KEY (`userprofile_id`) REFERENCES `userprofile` (`userprofile_id`) ON DELETE CASCADE,
  FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_requested_books` (
  `userprofile_id` INT NOT NULL,
  `bookid` INT NOT NULL,
  PRIMARY KEY (`userprofile_id`, `bookid`),
  FOREIGN KEY (`userprofile_id`) REFERENCES `userprofile` (`userprofile_id`),
  FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `book_author_book` (
  `bookid` INT NOT NULL,
  `authorid` INT NOT NULL,
  PRIMARY KEY (`bookid`, `authorid`),
  FOREIGN KEY (`bookid`) REFERENCES `book` (`bookid`) ON DELETE CASCADE,
  FOREIGN KEY (`authorid`) REFERENCES `bookauthor` (`authorid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `users` (username, password, role) VALUES
('john_doe', 'password123', 'GUEST'),
('user1', '12345', 'USER'),
('bob_jackson', 'bobspassword', 'GUEST'),
('waaaargr', '$2a$10$YECF6Bj11.sz7FDIs4104uRYLjSSeSQt0ln3VyfvccxwJLLshkiLC', 'USER'),
('forthnet', '$2a$10$DElagAdAfKrabR9bOfYCp.aYq648YjXMKvbWJCNvW/iXGGS6.0Fza', 'GUEST');

INSERT INTO `bookcategory` (name) VALUES ('Art'), ('Comic'), ('Fantasy'), ('Fiction'), 
('Biographies'), ('History'), ('Science'), ('Literature'), ('Adventure'), ('Crime'), ('Other');

INSERT INTO `bookauthor` (name) VALUES ('Author1'), ('Author2'), ('Author3'), ('Author4'), 
('Author5'), ('Author6'), ('Author7'), ('Author8'), ('Author9'), ('Author10'), ('Author11');