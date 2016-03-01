CREATE USER 'dbadmin'@'localhost' IDENTIFIED BY 'P@$$w0rd';

CREATE DATABASE clockr;

GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@localhost IDENTIFIED by 'P@$$w0rd';
GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@'%' IDENTIFIED by 'P@$$w0rd';

USE clockr;

DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees(EmpNum int, EmpFname TINYTEXT, EmpLname TINYTEXT, UserId TINYTEXT);

DROP TABLE IF EXISTS Credentials;
CREATE TABLE Credentials(UserId TINYTEXT, Password TINYTEXT, UserRole TINYTEXT );

INSERT INTO Employees VALUES (1, "Bob", "Smith", "A00123456");
INSERT INTO Employees VALUES (2, "Jane", "Doe", "A00465123");

INSERT INTO Credentials VALUES ("A00123456", "password", "Employee");
INSERT INTO Credentials VALUES ("A00465123", "iamPM", "ProjectManager");