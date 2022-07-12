DROP DATABASE IF EXISTS ijse;
CREATE DATABASE IF NOT EXISTS ijse;
USE ijse;
DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(
	student_id VARCHAR(45),
	student_name VARCHAR(45),
	email TEXT,
	contact VARCHAR(20),
	address TEXT,
	nic VARCHAR(45),
	CONSTRAINT PRIMARY KEY (student_id)
);
INSERT INTO student VALUES ('ST001','Akila','akila@gmail.com','0771234567','gampaha','9745123658V');
INSERT INTO student VALUES ('ST002','saman','saman@gmail.com','0774561238','colombo','9111123658V');
INSERT INTO student VALUES ('ST003','Piyal','piyal@gmail.com','0777894562','jaffna','98888652658V');
INSERT INTO student VALUES ('ST004','kasun','kasun@gmail.com','0712564789','jaela','92236666658V');
INSERT INTO student VALUES ('ST005','Nimal','nimal@gmail.com','0702354871','nugegoda','94587991118V');


DESC student;
SELECT * FROM student;
##------------------------------------------------------------------
DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(
	teachert_id VARCHAR(45),
	name VARCHAR(45),
	nic VARCHAR(45),
	contact VARCHAR(45),
	address TEXT,
	
	CONSTRAINT PRIMARY KEY (teachert_id)
);
INSERT INTO Teacher VALUES ('TI001','Jagath','91987991118V','0751234567','jaffna');
INSERT INTO Teacher VALUES ('TI002','Perera','90000991118V','0751234567','gampaha');
INSERT INTO Teacher VALUES ('TI003','Ajith','99876991118V','0751234567','colombo');
INSERT INTO Teacher VALUES ('TI004','Lalith','95588991118V','0751234567','kandy');
INSERT INTO Teacher VALUES ('TI005','Sujith','91987123456V','0751234567','colombo');



DESC Teacher;
SELECT * FROM Teacher;

DROP TABLE IF EXISTS Subject;
CREATE TABLE IF NOT EXISTS Subject(
	subject_id VARCHAR(45),
	subject_name VARCHAR(45),
	credit DOUBLE,
	teacher_id VARCHAR(45),
	CONSTRAINT PRIMARY KEY (subject_id),
	CONSTRAINT FOREIGN KEY (teacher_id) REFERENCES Teacher(teachert_id)
	ON UPDATE CASCADE
	
);
INSERT INTO subject VALUES ('ITS001','DBMS',200,'TI001');
INSERT INTO subject VALUES ('ITS002','PRF',300,'TI002');
INSERT INTO subject VALUES ('ITS003','HIBERNATE',400,'TI003');
INSERT INTO subject VALUES ('ITS004','OOP',500,'TI004');
INSERT INTO subject VALUES ('ITS005','JAVA',600,'TI005');


DESC Subject;
SELECT * FROM Subject;



DROP TABLE IF EXISTS Course;
CREATE TABLE IF NOT EXISTS Course(
	course_id VARCHAR(45),
	course_name VARCHAR(45),
	cost DOUBLE,
	duration VARCHAR(45),
	subject_id VARCHAR(45),
	CONSTRAINT PRIMARY KEY (course_id),
	CONSTRAINT FOREIGN KEY (subject_id) REFERENCES Subject(subject_id)
	ON UPDATE CASCADE
);
INSERT INTO Course VALUES ('CI001','GDSE',35000,'2 YEAR','ITS001');
INSERT INTO Course VALUES ('CI002','CMJD',85000,'6 MONTH','ITS002');
INSERT INTO Course VALUES ('CI003','DEP',42000,'1 YEAR','ITS003');
INSERT INTO Course VALUES ('CI004','RAPID',55000,'6 MOTH','ITS001');
INSERT INTO Course VALUES ('CI005','ICTT',15000,'1 YEAR','ITS002');



DESC Course;
SELECT * FROM Course;


DROP TABLE IF EXISTS intake;
CREATE TABLE IF NOT EXISTS intake(
	intake_id VARCHAR(45),
	start_date DATE,
	intakecol VARCHAR(45),
	description VARCHAR(45),
	course_id VARCHAR(45),
	CONSTRAINT PRIMARY KEY (intake_id),
	CONSTRAINT FOREIGN KEY (course_id) REFERENCES Course (course_id)ON UPDATE CASCADE
);
INSERT INTO intake VALUES ('IN001','2020-01-10','GDSE55','NONE','CI001');
INSERT INTO intake VALUES ('IN002','2021-06-12','GDSE56','NONE','CI002');
INSERT INTO intake VALUES ('IN003','2021-12-05','GDSE57','NONE','CI003');
INSERT INTO intake VALUES ('IN004','2021-01-16','GDSE58','NONE','CI004');
INSERT INTO intake VALUES ('IN005','2022-04-01','GDSE59','NONE','CI001');


DESC intake;
SELECT * FROM intake;




DROP TABLE IF EXISTS Registration;
CREATE TABLE if NOT EXISTS Registration(
	registration_id VARCHAR(45),
	reg_date DATE,
	student_id VARCHAR(45),
	intake_id VARCHAR(45),
	CONSTRAINT PRIMARY KEY(registration_id),
	CONSTRAINT FOREIGN KEY (student_id) REFERENCES Student (student_id)ON UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY (intake_id) REFERENCES intake (intake_id)ON UPDATE CASCADE
);
INSERT INTO Registration VALUES ('RI001','2021-04-05','ST001','IN001');
INSERT INTO Registration VALUES ('RI002','2021-04-06','ST002','IN002');
INSERT INTO Registration VALUES ('RI003','2021-04-12','ST003','IN004');
INSERT INTO Registration VALUES ('RI004','2021-04-18','ST002','IN001');


DESC Registration;
SELECT * FROM Registration;


DROP  TABLE IF EXISTS payment;
CREATE TABLE payment(
    payment_id VARCHAR(45),
    date DATE,
    cost DOUBLE,
    registration_id varchar(45),
    CONSTRAINT FOREIGN KEY (registration_id) REFERENCES registration(registration_id) ON DELETE CASCADE
	ON UPDATE CASCADE

);

INSERT INTO payment VALUE ('PI001','2021-04-15',40000,'RI001');
INSERT INTO payment VALUE ('PI002','2021-04-22',30000,'RI002');
INSERT INTO payment VALUE ('PI003','2021-04-14',20000,'RI003');

DESC payment;
SELECT * FROM payment;





