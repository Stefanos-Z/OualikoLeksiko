CREATE TABLE Users(
    user_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_email VARCHAR(100),
    user_type ENUM('Student','Instructor','Administrator'),
    PRIMARY KEY (user_name)
) Engine = InnoDB;




CREATE TABLE QuestionType(
	question_id INT AUTO_INCREMENT NOT NULL,
    question_text VARCHAR(100) NOT NULL,
	PRIMARY KEY (question_id)
) Engine = InnoDB;


CREATE TABLE WelshWords(
	word_id INT AUTO_INCREMENT NOT NULL,
    welsh_word VARCHAR(100) NOT NULL,
    english_meaning VARCHAR(100) NOT NULL,
    gender ENUM('M','F') NOT NULL DEFAULT 'M',
	PRIMARY KEY (word_id)
) Engine = InnoDB;



CREATE TABLE TestsResults(
	test_id INT AUTO_INCREMENT NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    grade INT NOT NULL,
	date_submitted DATETIME,
    PRIMARY KEY (test_id),
    FOREIGN KEY (user_name) REFERENCES Users (user_name)
    ON UPDATE CASCADE ON DELETE CASCADE
) Engine = InnoDB;




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

