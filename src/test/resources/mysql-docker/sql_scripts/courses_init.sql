CREATE DATABASE courses;
CREATE USER 'user'@'%' IDENTIFIED BY 'user';
GRANT SELECT ON courses.* TO 'user'@'%';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';
GRANT SELECT ON courses.* TO 'user'@'localhost';
USE courses;