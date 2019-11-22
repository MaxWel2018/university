INSERT INTO courses(course_name)
values ('Math'),
       ('Computer Science'),
       ('English'),
       ('OOP'),
       ('Java Programing');

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

