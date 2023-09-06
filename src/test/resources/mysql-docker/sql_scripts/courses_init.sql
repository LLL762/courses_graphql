CREATE DATABASE courses;
CREATE USER 'user'@'%' IDENTIFIED BY 'user';
GRANT SELECT ON courses.* TO 'user'@'%';
USE courses;