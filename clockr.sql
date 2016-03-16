CREATE USER 'dbadmin'@'localhost' IDENTIFIED BY 'P@$$w0rd';

DROP DATABASE clockr;

CREATE DATABASE clockr;

GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@localhost IDENTIFIED by 'P@$$w0rd';
GRANT ALL PRIVILEGES ON clockr.* TO dbadmin@'%' IDENTIFIED by 'P@$$w0rd';

USE clockr;

DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees(	EmpNum int, 
						EmpFname TINYTEXT, 
						EmpLname TINYTEXT,
						UserId TINYTEXT, 
						SickDays int, 
						VacDays int, 
						FlexStart time, 
						FlexEnd time, 
						HireDate date, 
						Email TINYTEXT, 
						PayRateId TINYTEXT,
						ProjectId int,
						WpId int);

DROP TABLE IF EXISTS Credentials;
CREATE TABLE Credentials( UserId TINYTEXT, 
						  Password TINYTEXT, 
						  RoleId int, 
						  UserRole TINYTEXT, 
						  Email TINYTEXT, 
						  DigiSign VARCHAR(255));

INSERT INTO Employees VALUES (1, "Bob", "Smith", "000001", 10, 10, "11:30:00", "19:30:00", "2010-10-10", "aa@aa.aa", "P1", 10, 1);
INSERT INTO Employees VALUES (2, "Jane", "Doe", "000002", 10, 10, "8:00:00", "17:00:00", "1995-01-01", "bb@bb.bb", "P5", 10, 1);
INSERT INTO Employees VALUES (3, "Cisco", "Ramon", "000003", 10, 10, "8:00:00", "17:00:00", "2010-01-01", "cc@cc.cc", "P3", 20, 2);
INSERT INTO Employees VALUES (4, "Barry", "Allen", "000004", 10, 10, "8:00:00", "17:00:00", "2011-01-01", "dd@dd.dd", "P3", 20, 2);


INSERT INTO Credentials VALUES ("000001", "password", 001, "Employee", "aa@aa.aa", "1504678463");
INSERT INTO Credentials VALUES ("000002", "iamPM", 002, "ProjectManager", "bb@bb.bb" , "-1889844622");
INSERT INTO Credentials VALUES ("000003", "iamgod", 003, "SystemAdmin", "cc@cc.cc" , "-58714991");
INSERT INTO Credentials VALUES ("000004", "f3@rMe", 004, "ResponsibilityEngineer", "dd@dd.dd" , "128438349");


DROP TABLE IF EXISTS TimeSheet;
CREATE TABLE TimeSheet( TimesheetId int, 
						EmpNum int, 
						WeekNum int,
                        WeekEnding date,
                        SatTotalHrs double,
                        SunTotalHrs double,
						MonTotalHrs double,
                        TuesTotalHrs double,
                        WedsTotalHrs double,
                        ThursTotalHrs double,
                        FriTotalHrs double,
						FlextimeHrs double, 
                        OverallTotalHrs double,
                        OvertimeTotalHrs double,
                        Signature VARCHAR(255),
                        Approval VARCHAR(255));
						
DROP TABLE IF EXISTS TimeSheetRow;
CREATE TABLE TimeSheetRow( TimeSheetRowId int, 
						   TimeSheetId int,
						   ProjectId int, 
                           WpId int, 
                           WeekTotalHrs double,
                           SatHrs double, 
                           SunHrs double, 
                           MonHrs double, 
                           TuesHrs double, 
                           WedHrs double, 
                           ThursHrs double, 
                           FriHrs double, 
                           Notes TINYTEXT);

INSERT INTO TimeSheet VALUES(1, 2, 25, "2016-02-01", 45.5, 20, 21, 32, 4, 4, 6.5, 12, 11, 0, "", "");	

INSERT INTO TimeSheetRow VALUES(1, 1, 10, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, "much codin");	
INSERT INTO TimeSheetRow VALUES(2, 1, 10, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, "such hax");
INSERT INTO TimeSheetRow VALUES(3, 1, 10, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, "very db");	
						 
DROP TABLE IF EXISTS Project;
CREATE TABLE Project( ProjectId int, 
					  ProjName TINYTEXT, 
					  ManDays int, 
					  EmpNum int, 
					  WpId int, 
					  IssueDate date,
					  Descript TEXT);	

DROP TABLE IF EXISTS WorkPackage;
CREATE TABLE WorkPackage( WpId int, 
						  WpNum int, 
						  WpTitle TINYTEXT, 
						  Contractor TINYTEXT, 
						  RespEngId TINYTEXT, 
						  PayRateId TINYTEXT, 
						  ManDays int, 
						  ProjectId int, 
						  EmpNum int);

INSERT INTO Project VALUES(10, "Project Alpha", 300, 2, 1, "2016-02-01", "");
INSERT INTO Project VALUES(20, "Project Beta", 400, 2, 2, "2016-01-31", "");	

INSERT INTO WorkPackage VALUES(1, 1, "Project Set-Up", "TEK Solutions", "000004", "P1", 13, 10, 1);
INSERT INTO WorkPackage VALUES(2, 2, "Ongoing Update", "Microsoft", "000004", "P1", 54, 20, 1);	
