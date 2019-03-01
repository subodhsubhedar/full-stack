INSERT INTO Course (ID, title) VALUES (10001, 'Core Java');
INSERT INTO Course (ID, title) VALUES (10002, 'SQL Fundamentals');
INSERT INTO Course (ID, title) VALUES (10003, 'AWS Basics');
INSERT INTO Course (ID, title) VALUES (10004, 'Moving to Cloud');



INSERT INTO Passport (id, number) VALUES (1001, 'A7216387');
INSERT INTO Passport (id, number) VALUES (1002, 'S7216387');
INSERT INTO Passport (id, number) VALUES (1003, 'H7216387');
INSERT INTO Passport (id, number) VALUES (1004, 'E7216387');


INSERT INTO Student (id, name, passport_id) VALUES (2001,'Amit',1001);
INSERT INTO Student (id, name, passport_id) VALUES (2002,'Rohit',1002);
INSERT INTO Student (id, name, passport_id) VALUES (2003,'Shikhar',1003);
INSERT INTO Student (id, name, passport_id) VALUES (2004,'Ronit',1004);


INSERT INTO Review (id, rating, desc, course_id) VALUES (7001,'5-Stars','Excellent',10001);
INSERT INTO Review (id, rating, desc, course_id) VALUES (7002,'4-Stars','Great',10001);
INSERT INTO Review (id, rating, desc, course_id) VALUES (7003,'3-Stars','Okay',10003);
INSERT INTO Review (id, rating, desc, course_id) VALUES (7004,'1-Stars','Bad',10002);
