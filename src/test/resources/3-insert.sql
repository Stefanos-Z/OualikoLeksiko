

INSERT INTO Users(user_name, user_password, user_email, user_type) VALUES
 ('stefanos','password','email@email.com','Administrator'),
 ('loizos','password','email1@email.com','Instructor'),
 ('nickolas','password','email2@email.com','Student');



INSERT INTO QuestionType(question_text) VALUES
('What is the gender of the Welsh noun <>?'),
('What is the meaning of the Welsh noun <>?'),
('What is the Welsh noun for the English word for <>?');


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



INSERT INTO TestsResults(user_name, grade, date_submitted) VALUES
('nickolas',12,'2018-02-12'),
('nickolas',20,'2018-02-03'),
('nickolas',19,'2018-02-01'),
('nickolas',11,'2018-02-11');



