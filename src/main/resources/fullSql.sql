DROP VIEW IF EXISTS TestQuestionsFull;
DROP TABLE IF EXISTS TestQuestions;
DROP TABLE IF EXISTS Sessions;
DROP TABLE IF EXISTS TestsResults;
DROP TABLE IF EXISTS QuestionType;
DROP TABLE IF EXISTS WelshWords;
-- DROP TABLE IF EXISTS Tests;
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
('What is the gender of the Welsh noun <>?'),
('What is the meaning of the Welsh noun <>?'),
('What is the Welsh noun for the English word for <>?');

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
('myfyrwraig','student','F'),
('ciwcymbr','cucumber','M'),
('oren','orange','M'),
('lemwn','lemon','M'),
('ystafell','room','M'),
('llyfr','book','M'),
('pensil','pencil','M'),
('cath','cat','M'),
('ceffyl','horse','M'),
('coffi','coffee','M'),
('cwch','boat','M'),
('cyflymder','speed','M'),
('amser','time','M'),
('lon','road','M'),
('cyllell','knife','M'),
('fforc','fork','M'),
('llwy','spoon','M'),
('coeden','tree','M'),
('tŷ','house','M'),
('dŵr','water','M'),
('cerddoriaeth','music','M'),
('Tŷ','House','F'),
('cyfrifiadur','computer','M'),
('golau','light','F'),
('sgrin','screen','F'),
('cebl','cable','M'),
('modiwl','module','F'),
('geirfa','vocabulary','M'),
('Technoleg','Technology','F'),
('meddygaeth','medicine','M'),
('cyfalaf','capital','F'),
('Darlith','lecture','F'),
('desg','desk','M'),
('Adran','section','F'),
('briwsionyn','cookie','M'),
('sudd','juice','M'),
('rhyngrwyd','internet','M'),
('rhwydwaith','network','M'),
('cyfieithydd','translator','F'),
('aer','air','F'),
('bysellfwrdd','keyboard','M'),
('glaswellt','grass','M'),
('môr','sea','F'),
('llong','ship','M'),
('rhywbeth','something','M'),
('ceffyl','horse','M'),
('crwban','turtle','F'),
('ffordd','road','M'),
('gwres','heat','F'),
('Sesiwn','Session','F'),
('pecyn','package','M'),
('pren mesur','ruler','F');

-- 
-- CREATE TABLE Tests(
--     test_id INT AUTO_INCREMENT NOT NULL,
--     test_title VARCHAR(100) NOT NULL,
--     date_created DATE NOT NULL,
--     user_name VARCHAR(50),
--     PRIMARY KEY (test_id),
--     FOREIGN KEY (user_name) REFERENCES Users (user_name)
--     ON UPDATE CASCADE ON DELETE NO ACTION
-- ) Engine = InnoDB;
-- 
-- INSERT INTO Tests(test_title, date_created, user_name) VALUES
-- ('Test Num1','2018-04-14','loizos'),
-- ('Test Num2','2018-04-13','loizos'),
-- ('Test Num3','2018-04-12','loizos'),
-- ('Test Num4','2018-04-11','loizos');


CREATE TABLE TestsResults(
	test_id INT AUTO_INCREMENT NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    grade INT NOT NULL,
	date_submitted DATETIME,
    PRIMARY KEY (test_id),
    FOREIGN KEY (user_name) REFERENCES Users (user_name)
    ON UPDATE CASCADE ON DELETE CASCADE
) Engine = InnoDB;

INSERT INTO TestsResults(user_name, grade, date_submitted) VALUES
('nickolas',12,'2018-02-12'),
('nickolas',20,'2018-02-03'),
('nickolas',19,'2018-02-01'),
('nickolas',11,'2018-02-11');


CREATE TABLE Sessions(
	session_id VARCHAR(100) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    expiration_date DATETIME NOT NULL,
    PRIMARY KEY(session_id),
    FOREIGN KEY (user_name) REFERENCES Users(user_name)
    ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_name) REFERENCES Users(user_name)
    ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=InnoDB;


CREATE TABLE TestQuestions(
	respondent_question_id INT AUTO_INCREMENT NOT NULL, 
	question_id INT NOT NULL,
    word_id INT NOT NULL,
    question_full VARCHAR(100) NOT NULL,
    correct_answer VARCHAR(100) NOT NULL,
    PRIMARY KEY(respondent_question_id),
    FOREIGN KEY(question_id) REFERENCES QuestionType(question_id)
	ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(word_id) REFERENCES WelshWords(word_id)
	ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=InnoDB;

CREATE VIEW TestQuestionsFull AS(
	SELECT tq.respondent_question_id, qt.question_id, tq.question_full, w.welsh_word, w.english_meaning, w.gender, tq.correct_answer
    FROM TestQuestions tq, QuestionType qt, WelshWords w
    WHERE tq.question_id = qt.question_id AND
        w.word_id = tq.word_id
);
