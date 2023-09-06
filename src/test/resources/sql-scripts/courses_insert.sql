SET SQL_SAFE_UPDATES = 0;

DELETE FROM course_has_student;
DELETE FROM teacher_has_degree;
DELETE FROM student_has_degree;
DELETE FROM student;
DELETE FROM course;
DELETE FROM teacher;
DELETE FROM degree;


INSERT INTO student  (id,first_name,last_name,email) VALUES
(1,'Arnold','Swartz','arnold@gmail.com'),
(2,"Rebecca","Revert","revert@gmail.com"),
(3,"Patrick","Sebast","patt@gmail.com"),
(4,"Hayley","Collier","Collier@gmail.com"),
(5,"Nicole","Bishop","nicole@gmail.com"),
(6,"Paxton","Tyler","pax@gmail.com"),
(7,'Helena',"Avecedo","Helena@gmail.com"),
(8,'Dakari',"Gray","Dakari@gmail.com"),
(9,'Sarah',"Hoover","sarah@gmail.com"),
(10,'Alexis',"Lucero","alexis@gmail.com")
;

INSERT INTO teacher  (id,first_name,last_name,email) VALUES
(1,"Felipe","Bond","fel@gmail.com"),
(2,"Alena","White","alena@gmail.com"),
(3,"Aiden","Villarreal","aiden@gmail.com"),
(4,"Jazlyn","Walters","jazlyn@gmail.com"),
(5,'Eve',"Magana","eveM@gmail.com"),
(6,'Rey',"Maxwell","rey@gmail.com"),
(7,'Kyla',"Stanton","kyla@gmail.com"),
(8,'Zyair',"Klein","zyair@gmail.com"),
(9,'Elianna',"Deleon","elianna@gmail.com"),
(10,'Nasir',"David","nasir@gmail.com")
;

INSERT INTO degree (id,name) VALUES 
(1,"Pharmacology"),
(2,"Computer Science"),
(3,"Health Science"),
(4,"Information Technology"),
(5,"Engineering"),
(6,'Business Administration'),
(7,'Finance'),
(8,'Human Resources'),
(9,'Education'),
(10,'Psychology')
;

INSERT INTO course  (id,name,description,teacher_id,degree_id) VALUES
(1,"Pharmacology","lorem ipsum dolor sit amet, consectetur adip lorem element euismod tempor",1,1),
(2,"Computer Science","lorem ipsum dolor sit am et, consectetur adip lorem element eu",1,2),
(3,"Health Science","lorem ipsum dolor sit amet, consectet durante",2,3),
(4,"Information Technology","lorem ipsum dolor sit am et, consectetur adip lorem element",6,4),
(5,"Engineering","lorem ipsum dolor sit am et, consectetur adip lorem element",7,5),
(6,'Business Administration',"lorem ipsum dolor sit am et, consectetur adip lorem element",7,6),
(7,'Finance',"lorem ipsum dolor sit am et, consectetur adip lorem element",7,7),
(8,'Human Resources',"lorem ipsum dolor sit am et",8,8),
(9,'Education',"lorem ipsum dolor sit am 77",9,9),
(10,'Psychology',"lorem ipsum dolor sit",10,10)
;

INSERT INTO teacher_has_degree (teacher_id,degree_id,earning_date) VALUES 
(1,"1","2004-08-01"),
(1,"2","2011-08-01"),
(2,"9","2021-08-01"),
(2,"10","2004-08-01"),
(2,"3","2004-08-01"),
(3,'5',"2004-08-01"),
(3,'7',"2004-08-01"),
(4,'8',"2004-08-01"),
(5,'1',"2004-08-01"),
(6,'4',"2004-08-01"),
(7,'5',"2004-08-01"),
(7,'6',"2004-08-01"),
(7,'7',"2004-08-01"),
(8,'8',"2004-08-01"),
(9,'9',"2004-08-01"),
(10,'10',"2004-08-01")
;

INSERT INTO student_has_degree (student_id,degree_id,earning_date) VALUES 
(1,"8","2021-10-21"),
(1,"9","2021-10-21"),
(1,"10","2021-10-21"),
(2,"6","2021-10-21"),
(2,"7","2021-10-21"),
(4,"1","2021-10-21"),
(5,"4","2021-10-21"),
(7,'5',"2021-10-21")
;

INSERT INTO course_has_student (course_id,student_id) VALUES 
(1,"1"),
(1,"2"),
(1,"3"),
(1,"4"),
(2,"1"),
(3,"5"),
(3,"6"),
(4,"4"),
(4,"6"),
(4,"8"),
(4,"10"),
(5,"1"),
(5,"3"),
(5,"5"),
(5,"7"),
(6,'9'),
(7,'4'),
(7,'5'),
(7,'6'),
(8,'10'),
(8,'7'),
(9,'2'),
(9,'5'),
(9,'8'),
(10,'1'),
(10,'2')
;

SET SQL_SAFE_UPDATES = 1;