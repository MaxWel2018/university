INSERT INTO courses(course_name)
values ('Math'),
       ('Computer Science'),
       ('English'),
       ('OOP'),
       ('Java Programing');

INSERT INTO role(role)
values ('USER'),('ADMIN');


INSERT INTO specialities(description, exam_end, exams_start, speciality_name, students_number)
values ( 'How do you find the most efficient and fair network topology?
How do you make computers reason about logic?
Theoretical Computer Since looks at reactive systems, programming language theory, and algorithms.
This specialization is very similar to Software Theory, but slightly more abstract.
Computer Since tend to take more courses in logic and formal methods.

Also consider: Software Theory, Artificial Intelligence, Real-World Computing'
       , '2019-12-07'
       , '2019-11-30'
       , 'Computer Since'
       , 5),

       ( 'Artificial Intelligence includes the study of AI principles and techniques,
as well as foundational material on topics such as logic, probability, and language.
Topics in the AI concentration include knowledge representation and logical reasoning, robotics, machine learning, probabilistic modeling and inference, natural language processing, cognition, and applications in domains such as biology and text processing.'
       , '2019-12-14'
       , '2019-12-07'
       , 'Artificial Intelligence'
       , 10),

       ( 'Provides students with an in-depth understanding of the current challenges facing computer scientists designing and developing secure, safety-critical systems.
        Course work includes networking and network security, advanced operating systems, cryptography, secure databases, etc.'
       , '2019-12-21'
       , '2019-12-14'
       , 'Computer and Network Security'
       , 9);

# Users
INSERT INTO users(active, email, first_name, last_name, password, id_speciality)
VALUES (1, 'user0@gmail.com', 'Lily', 'Marshman', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user1@gmail.com', 'Jacob', 'Calhoun', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user2@gmail.com', 'Alfie', 'Adrian', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user3@gmail.com', 'Mia', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user4@gmail.com', 'Alfie', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user5@gmail.com', 'Alfie', 'Bush', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user6@gmail.com', 'AMELIA', 'Marshman', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user7@gmail.com', 'HARRY', 'Marshman', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user8@gmail.com', 'Thomas', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user9@gmail.com', 'William', 'Campbell', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user10@gmail.com', 'Noah', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user11@gmail.com', 'Alfie', 'Howard', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user12@gmail.com', 'Miki', 'Campbell', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user13@gmail.com', 'Oscar', 'Adrian', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user14@gmail.com', 'Jacob', 'Bush', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user15@gmail.com', 'Miki', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user16@gmail.com', 'William', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user17@gmail.com', 'Ella', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user18@gmail.com', 'Oscar', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user19@gmail.com', 'Mia', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user20@gmail.com', 'Alfie', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user21@gmail.com', 'Miki', 'Howard', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user22@gmail.com', 'Oscar', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user23@gmail.com', 'Miki', 'Calhoun', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user24@gmail.com', 'Alfie', 'Howard', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user25@gmail.com', 'William', 'Adrian', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user26@gmail.com', 'Noah', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user27@gmail.com', 'Alfie', 'Bush', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user28@gmail.com', 'Noah', 'Howard', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user29@gmail.com', 'William', 'Marshman', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user30@gmail.com', 'Jacob', 'Adrian', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user31@gmail.com', 'Mia', 'Calhoun', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user32@gmail.com', 'Thomas', 'Howard', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user33@gmail.com', 'Ella', 'Campbell', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user34@gmail.com', 'JACK', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user35@gmail.com', 'Noah', 'Abramson', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user36@gmail.com', 'Thomas', 'Aldridge', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user37@gmail.com', 'Ella', 'Calhoun', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user38@gmail.com', 'Jacob', 'Aldridge', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user39@gmail.com', 'Lily', 'Adrian', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user40@gmail.com', 'HARRY', 'Adrian', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user41@gmail.com', 'Mia', 'Aldridge', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user42@gmail.com', 'Jacob', 'Marshman', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user43@gmail.com', 'William', 'Campbell', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user44@gmail.com', 'Poppy', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user45@gmail.com', 'Lily', 'Bradshaw', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user46@gmail.com', 'William', 'Holiday', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null),
       (1, 'user47@gmail.com', 'Miki', 'Bush', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user48@gmail.com', 'Lily', 'Adrian', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm', null),
       (1, 'user49@gmail.com', 'JACK', 'Marshman', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null);
# Admins
insert into users(active, email, first_name, last_name, password, id_speciality)
VALUES (1, 'admin0@gmail.com', 'Maksym', 'Kruhovykh ', '$2a$10$BZm2t41BHt10ATjC0UjhOO9WKdA1V2leSZPsh9.Mdr7ObzAMpSbpm',
        null);


#UserRole
INSERT INTO user_role(user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1),
       (14, 1),
       (15, 1),
       (16, 1),
       (17, 1),
       (18, 1),
       (19, 1),
       (20, 1),
       (21, 1),
       (22, 1),
       (23, 1),
       (24, 1),
       (25, 1),
       (26, 1),
       (27, 1),
       (28, 1),
       (29, 1),
       (30, 1),
       (31, 1),
       (32, 1),
       (33, 1),
       (34, 1),
       (35, 1),
       (36, 1),
       (37, 1),
       (38, 1),
       (39, 1),
       (40, 1),
       (41, 1),
       (42, 1),
       (43, 1),
       (44, 1),
       (45, 1),
       (46, 1),
       (47, 1),
       (48, 1),
       (49, 1),
       (50, 1),
       (51, 2);


#Exam Result
INSERT INTO exam_results(date, grade, id_course, id_user)
VALUES
('2019-11-30', null, 1,1),
('2019-12-1', null, 2,1),
('2019-12-3', null, 5,1),
('2019-11-30', null, 1,2),
('2019-12-1', null, 2,2),
('2019-12-3', null, 5,2),
('2019-11-30', null, 1,3),
('2019-12-1', null, 2,3),
('2019-12-3', null, 5,3),
('2019-11-30', null, 1,4),
('2019-12-1', null, 2,4),
('2019-12-3', null, 5,4),
('2019-11-30', null, 1,5),
('2019-12-1', null, 2,5),
('2019-12-3', null, 5,5),
('2019-11-30', null, 1,6),
('2019-12-1', null, 2,6),
('2019-12-3', null, 5,6),
('2019-11-30', null, 1,7),
('2019-12-1', null, 2,7),
('2019-12-3', null, 5,7),
('2019-11-30', null, 1,8),
('2019-12-1', null, 2,8),
('2019-12-3', null, 5,8),
('2019-11-30', null, 1,9),
('2019-12-1', null, 2,9),
('2019-12-3', null, 5,9),
('2019-11-30', null, 1,10),
('2019-12-1', null, 2,10),
('2019-12-3', null, 5,10),
('2019-11-30', null, 1,11),
('2019-12-1', null, 2,11),
('2019-12-3', null, 5,11),
('2019-11-30', null, 1,12),
('2019-12-1', null, 2,12),
('2019-12-3', null, 5,12),
('2019-11-30', null, 1,13),
('2019-12-1', null, 2,13),
('2019-12-3', null, 5,13),
('2019-11-30', null, 1,14),
('2019-12-1', null, 2,14),
('2019-12-3', null, 5,14),
('2019-11-30', null, 1,15),
('2019-12-1', null, 2,15),
('2019-12-3', null, 5,15),
('2019-11-30', null, 1,16),
('2019-12-1', null, 2,16),
('2019-12-3', null, 5,16),
('2019-11-30', null, 1,17),
('2019-12-1', null, 2,17),
('2019-12-3', null, 5,17),
('2019-11-30', null, 1,18),
('2019-12-1', null, 2,18),
('2019-12-3', null, 5,18),
('2019-11-30', null, 1,19),
('2019-12-1', null, 2,19),
('2019-12-3', null, 5,19),
('2019-11-30', null, 1,20),
('2019-12-1', null, 2,20),
('2019-12-3', null, 5,20),
('2019-11-30', null, 1,21),
('2019-12-1', null, 2,21),
('2019-12-3', null, 5,21);



INSERT INTO speciality_courses(id_speciality, id_course)
VALUES (1, 1),
       (1, 2),
       (1, 5),
       (2, 2),
       (2, 3),
       (2, 5),
       (3, 3),
       (3, 4),
       (3, 5);

