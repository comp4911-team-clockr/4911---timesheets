CREATE USER 'dbadmin'@'localhost' IDENTIFIED BY 'P@$$w0rd';

CREATE DATABASE clockr;

GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@localhost IDENTIFIED by 'P@$$w0rd';
GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@"%" IDENTIFIED by 'P@$$w0rd';

USE clockr;

DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees(EmpNum int, EmpFname TINYTEXT, EmpLname TINYTEXT, UserId TINYTEXT);

DROP TABLE IF EXISTS Credentials;
CREATE TABLE Credentials(UserId TINYTEXT, Password TINYTEXT, UserRole TINYTEXT );

DROP TABLE IF EXISTS TimeSheet;
CREATE TABLE TimeSheet(timeSID int, timeSName TINYTEXT, weekID int, Signature TINYTEXT, approval bool, FlexTime int);

DROP TABLE IF EXISTS TimeSheetRow;
CREATE TABLE TimeSheetRow(TimeSheetRowID int, projectID int, workpackID int, hours int, dayOfWeek int);

INSERT INTO Employees VALUES (1, "Bob", "Smith", "A00123456");
INSERT INTO Employees VALUES (2, "Jane", "Doe", "A00465123");

INSERT INTO Credentials VALUES ("A00123456", "password", "Employee");
INSERT INTO Credentials VALUES ("A00465123", "iamPM", "ProjectManager");

INSERT INTO TimeSheet VALUES(1, "TSTest", 5, "STest", true, "32");
INSERT INTO TimeSheet VALUES(2, "TSTest2", 6, "STest2", false, "33");

INSERT INTO TimeSheetRow VALUES(69, 68, 67, 22, 5);
INSERT INTO TimeSheetRow VALUES(169, 168, 167, 122, 6);