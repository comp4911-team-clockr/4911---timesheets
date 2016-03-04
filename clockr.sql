CREATE USER 'dbadmin'@'localhost' IDENTIFIED BY 'P@$$w0rd';

CREATE DATABASE clockr;

GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@localhost IDENTIFIED by 'P@$$w0rd';
GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@'%' IDENTIFIED by 'P@$$w0rd';

USE clockr;

DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees(EmpNum int, EmpFname TINYTEXT, EmpLname TINYTEXT, UserId TINYTEXT, SickDays int, VacDays int, FlexStart time, FlexEnd time, HireDate date, Email TINYTEXT, PayRateId TINYTEXT);

DROP TABLE IF EXISTS Credentials;
CREATE TABLE Credentials(UserId TINYTEXT, Password TINYTEXT, RoleId int, UserRole TINYTEXT, Email TINYTEXT, DigiSign VARCHAR(255));

INSERT INTO Employees VALUES (1, "Bob", "Smith", "A00123456", 10, 10, "11:30:00", "19:30:00", "2010-10-10", "aa@aa.aa", "P1");
INSERT INTO Employees VALUES (2, "Jane", "Doe", "A00465123", 10, 10, "8:00:00", "17:00:00", "1995-01-01", "bb@bb.bb", "P5");
INSERT INTO Employees VALUES (3, "Cisco", "Ramon", "A00654321", 10, 10, "8:00:00", "17:00:00", "2010-01-01", "cc@cc.cc", "P3");

INSERT INTO Credentials VALUES ("A00123456", "password", 001, "Employee", "aa@aa.aa", "");
INSERT INTO Credentials VALUES ("A00465123", "iamPM", 002, "ProjectManager", "bb@bb.bb" , "");
INSERT INTO Credentials VALUES ("A00654321", "admin", 003, "SystemAdmin", "cc@cc.cc" , "");