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
CREATE TABLE TimeSheet(timesheetID int, 
					   empNum int, 
                       weekNum int,
                       flextimeHrs int, 
                       weekEnding datetime,
                       satTotalHrs double,
                       sunTotalHrs double,
                       tuesTotalHrs double,
                       wedsTotalHrs double,
                       thursTotalHrs double,
                       friTotalHrs double,
                       overallTotalHrs double,
                       overtimeTotalHrs double,
                       Signature TINYTEXT, 
                       approval bool);

DROP TABLE IF EXISTS TimeSheetRow;
CREATE TABLE TimeSheetRow(rowID int, 
						   projectID int, 
                           wpID int, 
                           weekTotalHrs double,
                           satHrs double, 
                           sunHrs double, 
                           monHrs double, 
                           tuesHrs double, 
                           wedHrs double, 
                           thursHrs double, 
                           friHrs double, 
                           notes TINYTEXT);
                           
                           DROP TABLE IF EXISTS WorkPackage;
CREATE TABLE WorkPackage(wpId TINYTEXT, 
						wpNum int, 
						wpTitle TINYTEXT, 
						contractorId TINYTEXT, 
						userId TINYTEXT, 
						payRateId TINYTEXT, 
						manDays int, 
						projectId int, 
						empNum int);

DROP TABLE IF EXISTS Project;
CREATE TABLE Project(projectId int, 
					projName TINYTEXT, 
					roleId TINYTEXT, 
					manDays int, 
					empNum int, 
					wpId TINYTEXT, 
					issueDate DATETIME);	

INSERT INTO WorkPackage VALUES("B1111", 1, "Project Set-Up", "TestContractorId", "TestEngineerUserID", "TestPayRateID", 13, 010, 1);
INSERT INTO WorkPackage VALUES("B1112", 2, "Ongoing Updating", "TestContractorId2", "TestEngineerUserID2", "TestPayRateID2", 54, 010, 1);
INSERT INTO WorkPackage VALUES("B1113", 3, "Detailed Planning", "TestContractorId3", "TestEngineerUserID3", "TestPayRateID3", 20, 020, 2);
INSERT INTO WorkPackage VALUES("B1110", 4, "Schedule Effort", "TestContractorId24", "TestEngineerUserID4", "TestPayRateID4", 10, 020, 2);

INSERT INTO Project VALUES(010, "Project Alpha", "A00123456", 300, 1, "B1111", '2016-03-01 12:59:59');
INSERT INTO Project VALUES(020, "Project Beta", "A00456123", 400, 2, "B1113", '2016-03-31 16:59:59');		

INSERT INTO Employees VALUES (1, "Bob", "Smith", "A00123456");
INSERT INTO Employees VALUES (2, "Jane", "Doe", "A00465123");

INSERT INTO Credentials VALUES ("A00123456", "password", "Employee");
INSERT INTO Credentials VALUES ("A00465123", "iamPM", "ProjectManager");

INSERT INTO TimeSheet VALUES(1, 3, 5, 1, '1000-01-01 00:00:00', 1, 2, 3, 4, 5, 6, 7, 8,"STest", true);

INSERT INTO TimeSheetRow VALUES(69, 68, 67, 22, 5, 6, 7, 8, 9, 10, 11, "notes");
