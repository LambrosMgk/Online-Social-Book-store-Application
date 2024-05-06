CREATE DATABASE IF NOT EXISTS `data_diplomas`;
USE `data_diplomas`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `subject`;
DROP TABLE IF EXISTS `application`;
DROP TABLE IF EXISTS `professor`;
DROP TABLE IF EXISTS `thesis`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `users`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `users` (
  id int NOT NULL AUTO_INCREMENT,
  role_id int NOT NULL,
  user_name text DEFAULT NULL,
  password text DEFAULT NULL,
  role text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE professor (
  professor_id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(50) NOT NULL,
  specialty VARCHAR(50)
);

CREATE TABLE student (
  student_id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(50) NOT NULL,
  year_of_studies INT,
  current_avg_grade DECIMAL(4,2),
  remaining_courses_for_graduation INT
);

CREATE TABLE subject (
  subject_id INT PRIMARY KEY AUTO_INCREMENT,
  subject_name VARCHAR(500),
  professor_id INT NOT NULL,
  is_available boolean, 
  description VARCHAR(500),
  FOREIGN KEY (professor_id) REFERENCES professor(professor_id)
);

CREATE TABLE thesis (
  thesis_id INT PRIMARY KEY AUTO_INCREMENT,
  professor_id INT NOT NULL,
  student_id INT,
  subject_id INT NOT NULL,
  title VARCHAR(100) NOT NULL,
  objectives VARCHAR(500),
  supervisor VARCHAR(50),
  implmentationgrade float,
  reportgrade float,
  presentationgrade float,
  FOREIGN KEY (professor_id) REFERENCES professor(professor_id),
  FOREIGN KEY (student_id) REFERENCES student(student_id),
  FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
);

CREATE TABLE application (
  application_id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT NOT NULL,
  subject_id INT NOT NULL,
  full_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  major VARCHAR(50) NOT NULL,
  GPA DECIMAL(4,2) NOT NULL,
  remaining_courses_for_graduation INT,
  status VARCHAR(20) NOT NULL,
  application_date DATE NOT NULL,
  FOREIGN KEY (student_id) REFERENCES student(student_id),
  FOREIGN KEY (subject_id) REFERENCES subject(subject_id)
);





