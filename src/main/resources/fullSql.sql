DROP TABLE IF EXISTS Sessions;
DROP TABLE IF EXISTS TestsTaken;
DROP TABLE IF EXISTS QuestionType;
DROP TABLE IF EXISTS WelshWords;
DROP TABLE IF EXISTS Tests;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
    user_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_email VARCHAR(100),
    user_type ENUM('Student','Instructor','Administrator'),
    PRIMARY KEY (user_name)
) Engine = InnoDB;



INSERT INTO Users(user_name, user_password, user_email, user_type) VALUES
 ('stefanos','password','email@email.com','Administrator'),
 ('loizos','password','email1@email.com','Instructor'),
 ('nickolas','password','email2@email.com','Student');


CREATE TABLE QuestionType(
	question_id INT AUTO_INCREMENT NOT NULL,
    question_text VARCHAR(100) NOT NULL,
	PRIMARY KEY (question_id)
) Engine = InnoDB;

INSERT INTO QuestionType(question_text) VALUES
('What is the gender of the Welsh noun +?'),
('What is the meaning of the Welsh noun +?'),
('What is the Welsh noun for the English word for +?');

CREATE TABLE WelshWords(
	word_id INT AUTO_INCREMENT NOT NULL,
    welsh_word VARCHAR(100) NOT NULL,
    english_meaning VARCHAR(100) NOT NULL,
    gender ENUM('M','F') NOT NULL DEFAULT 'M',
	PRIMARY KEY (word_id)
) Engine = InnoDB;

INSERT INTO WelshWords(welsh_word, english_meaning,gender) VALUES
('Tatws','potato','F'),
('Cyfrifiadur','computer','M'),
('brechdan','sandwich','F'),
('myfyriwr','student','M'),
('myfyrwraig','student','F');

CREATE TABLE Tests(
	test_id INT AUTO_INCREMENT NOT NULL,
    test_title VARCHAR(100) NOT NULL,
    date_created DATE NOT NULL,
    user_name VARCHAR(50),
    PRIMARY KEY (test_id),
    FOREIGN KEY (user_name) REFERENCES Users (user_name)
    ON UPDATE CASCADE ON DELETE NO ACTION
) Engine = InnoDB;

INSERT INTO Tests(test_title, date_created, user_name) VALUES
('Test Num1','2018-04-14','loizos'),
('Test Num2','2018-04-13','loizos'),
('Test Num3','2018-04-12','loizos'),
('Test Num4','2018-04-11','loizos');


CREATE TABLE TestsTaken(
	test_id INT NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    grade FLOAT(5,2) NOT NULL,
	date_submitted DATE,
    PRIMARY KEY (test_id, user_name),
    FOREIGN KEY (test_id) REFERENCES Tests (test_id)
    ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_name) REFERENCES Users (user_name)
    ON UPDATE CASCADE ON DELETE CASCADE
) Engine = InnoDB;

INSERT INTO TestsTaken(test_id, user_name, grade, date_submitted) VALUES
(1,'nickolas',12.33,'2018-02-12'),
(2,'nickolas',20,'2018-02-03'),
(3,'nickolas',19.23,'2018-02-01'),
(4,'nickolas',11,'2018-02-11');


CREATE TABLE Sessions(
	session_id VARCHAR(100) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    expiration_date DATETIME NOT NULL,
    PRIMARY KEY(session_id),
    FOREIGN KEY (user_name) REFERENCES Users(user_name)
    ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=InnoDB;


