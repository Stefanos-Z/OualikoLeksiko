CREATE TABLE student(
student_id varchar(10) NOT NULL CHECK (student_id <> ''),
student_name varchar(100) NOT NULL,
student_scheme varchar(100),
PRIMARY KEY (student_id)
);

CREATE TABLE staff(
staff_Id varchar(10) NOT NULL CHECK (student_id <> ''),
staff_name varchar(100)NOT NULL,
staff_grade varchar(100),
PRIMARY KEY (staff_Id)
);

CREATE TABLE module(
module_Id varchar(10)NOT NULL CHECK (student_id <> ''),
module_name VARCHAR(100)NOT NULL,
credits INT,
PRIMARY KEY (module_Id)
);

CREATE TABLE registered(
student_Id varchar(10),
module_Id varchar(10),
PRIMARY KEY (student_Id, module_Id),
FOREIGN KEY (student_Id) REFERENCES student(student_Id)
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (module_Id) REFERENCES module(module_Id)
ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE teaches(
staff_Id varchar(10),
module_Id varchar(10),
PRIMARY KEY (staff_Id, module_Id),
FOREIGN KEY (staff_iD) REFERENCES staff(staff_Id)
ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (module_Id) REFERENCES module(module_Id)
ON DELETE CASCADE ON UPDATE CASCADE
);
