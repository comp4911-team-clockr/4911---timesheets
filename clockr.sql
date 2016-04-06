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
                        SupervisorEmpNum int,
						VacationDays int,
                        FlexHours double,
                        HireDate date, 
						Email TINYTEXT, 
						PayRateId TINYTEXT
						);
                                                
INSERT INTO Employees VALUES (1, "Bob", "Smith", "000001", 2, 10, 0, "2010-10-10", "aa@aa.aa", "P1");
INSERT INTO Employees VALUES (2, "Jane", "Doe", "000002", 2, 10, 0, "1995-01-01", "bb@bb.bb", "P5");
INSERT INTO Employees VALUES (3, "Cisco", "Ramon", "000003", 2, 10, 0, "2010-01-01", "cc@cc.cc", "P3");
INSERT INTO Employees VALUES (4, "Barry", "Allen", "000004", 2, 10, 0, "2011-01-01", "dd@dd.dd", "P3");

DROP TABLE IF EXISTS Credentials;
CREATE TABLE Credentials( UserId TINYTEXT, 
						  Password TINYTEXT, 
						  RoleId int, 
						  UserRole TINYTEXT, 
						  Email TINYTEXT, 
                          RecoveryAnswer1 TINYTEXT,
                          RecoveryAnswer2 TINYTEXT,
                          RecoveryAnswer3 TINYTEXT,
						  DigiSign VARCHAR(255));
						                            
INSERT INTO Credentials VALUES ("000001", "password", 001, "Employee", "aa@aa.aa", 
                                "", 
                                "",
                                "",
                                "1504678463");
                                
INSERT INTO Credentials VALUES ("000002", "iamPM", 002, "ProjectManager", "bb@bb.bb" , 
                                "", 
                                "",
                                "",
                                "-1889844622");

INSERT INTO Credentials VALUES ("000003", "iamgod", 003, "SystemAdmin", "cc@cc.cc" ,
                                "", 
                                "",
                                "",
                                "-58714991");

INSERT INTO Credentials VALUES ("000004", "f3@rMe", 004, "ResponsibilityEngineer", "dd@dd.dd" , 
                                "", 
                                "",
                                "",
                                "128438349");

                                
DROP TABLE IF EXISTS PayRate;
CREATE TABLE PayRate( PayRateId TINYTEXT,
					  CostinMD double,
					  IntRate double,
					  ExtRate double,
                      OvertimeRate double);

INSERT INTO PayRate VALUES ("P1", 115, 1.0, 1.5, 1.5);
INSERT INTO PayRate VALUES ("P2", 147, 1.0, 1.5, 1.5);
INSERT INTO PayRate VALUES ("P3", 179, 1.0, 1.5, 1.5);
INSERT INTO PayRate VALUES ("P4", 225, 1.0, 1.5, 1.5);
INSERT INTO PayRate VALUES ("P5", 266, 1.0, 1.5, 1.5);
INSERT INTO PayRate VALUES ("DS", 97, 1.0, 1.5, 1.5);
INSERT INTO PayRate VALUES ("SS", 107, 1.0, 1.5, 1.5);
                      

DROP TABLE IF EXISTS TimeSheet;
CREATE TABLE TimeSheet( TimesheetId TINYTEXT, 
						EmpNum int, 
						WeekNum int,
                        WeekEnding date,
                        OverallTotalHrs double,
                        SatTotalHrs double,
                        SunTotalHrs double,
						MonTotalHrs double,
                        TuesTotalHrs double,
                        WedsTotalHrs double,
                        ThursTotalHrs double,
                        FriTotalHrs double,
                        FlextimeHrs double, 
                        Signature VARCHAR(255),
                        Submitted BOOL,
                        Approval BOOL,
                        IsActive BOOL);
						
INSERT INTO TimeSheet VALUES("2|1", 2, 25, "2016-02-01", 160, 0, 0, 32, 32, 32, 32, 32, 0, "", FALSE, FALSE, TRUE);	
                        
DROP TABLE IF EXISTS TimeSheetRow;
CREATE TABLE TimeSheetRow( TimeSheetRowId TINYTEXT,
                           TimeSheetId TINYTEXT,
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
                           VacationDays int,
                           FlextimeHrs double,
                           Notes TINYTEXT);

INSERT INTO TimeSheetRow VALUES("2|1|1", "2|1", 10, 1, 40, 0, 0, 8, 8, 8, 8, 8, 0, 0, "much codin");	
INSERT INTO TimeSheetRow VALUES("2|1|2", "2|1", 10, 1, 44, 0, 0, 12, 8, 8, 8, 8, 0, 4, "such hax");
INSERT INTO TimeSheetRow VALUES("2|1|3", "2|1", 10, 1, 40, 0, 0, 0, 8, 8, 8, 8, 1, 0, "very db");	
						 
DROP TABLE IF EXISTS Project;
CREATE TABLE Project( ProjectId int NOT NULL, 
					  ProjName TINYTEXT NOT NULL, 
					  EmpNum int NOT NULL,
                      ProjAssistant int,
                      IssueDate date,
                      EstimatedCompletionDate date,
                      
                      CostingProposal double,
                      InitialBudget double,
                      RO1Budget double,
                      RO2Budget double,
                      FinalBudget double,
                      
                      MDP1 int NOT NULL,
                      MDP2 int NOT NULL,
                      MDP3 int NOT NULL,
                      MDP4 int NOT NULL,
                      MDP5 int NOT NULL,
                      MDDS int NOT NULL,
                      MDSS int NOT NULL,                      
					  Descript TEXT NOT NULL);	
                      
INSERT INTO Project VALUES(1, "Project Alpha", 2, 1, "2016-02-01", "2016-02-20",
                            10000,
                            0,
                            0,
                            0,
                            10000,
                            100,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            "");
INSERT INTO Project VALUES(2, "Project Beta", 2, 3, "2016-01-31", "2016-02-20",
                            10000,
                            0,
                            0,
                            0,
                            10000,
                            100,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            "");	
                      
DROP TABLE IF EXISTS WorkPackage;
CREATE TABLE WorkPackage( WpId TINYTEXT, 
						  WpNum TINYTEXT, 
						  WpTitle TINYTEXT, 
						  Customer TINYTEXT, 
						  RespEngId TINYTEXT,

                          IssueDate date,
                          PlannedCompletionDate date,
                          ActualCompletionDate date,
                          
						  MDP1 int NOT NULL,
                          MDP2 int NOT NULL,
                          MDP3 int NOT NULL,
                          MDP4 int NOT NULL,
                          MDP5 int NOT NULL,
                          MDDS int NOT NULL,
                          MDSS int NOT NULL,
                          ProjectId int, 
                          IsActive BOOL);
                          
INSERT INTO WorkPackage VALUES("1|1", "B1111", "Project Set-Up", "TEK Solutions", "000004",
                                "2016-02-01", "2016-02-20", "2016-02-21",
                                100,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                1, true);
                                
INSERT INTO WorkPackage VALUES("2|1", "B1112", "Ongoing Update", "Microsoft", "000004", 
                                "2016-02-01", "2016-02-20","2016-02-21",
                                200,
                                0,
                                0,
                                0,
                                0,
                                0,
                                0,
                                2, true);

DROP TABLE IF EXISTS EmployeeWPList;
CREATE TABLE EmployeeWPList(WpEmpId TINYTEXT, WpId TINYTEXT, EmpNum int);
                            
INSERT INTO EmployeeWPList VALUES(
							"1|1|1",
							"1|1",
							1);
INSERT INTO EmployeeWPList VALUES(
							"1|1|2",
							"1|1",
							2);
INSERT INTO EmployeeWPList VALUES(
							"2|1|3",
							"2|1",
							3);
INSERT INTO EmployeeWPList VALUES(
							"2|1|4",
							"2|1",
							4);

DROP TABLE IF EXISTS StatusReport;
CREATE TABLE StatusReport(
                        StatusReportId TINYTEXT,
                        RespEngId TINYTEXT,
                        ReportDate date,
                        MDPlanned double,
                        MDActual double,
                        WorkAccomplishedThisPeriod TEXT,
                        WorkPlannedNextPeriod TEXT,
                        ProblemsEncountered TEXT,
                        ProblemsAnticipated TEXT,
                        timeVar double,
                        mdVar double,
                        costVar double
                        );
                        
INSERT INTO StatusReport VALUES(
                        "1|1|1",
                        "000004",
                        "2016-01-31",
                        "100",
                        "120",
                        "Test deliverable 1. Test deliverable 2.",
                        "Test deliverable 3. Test deliverable 4.",
                        "Half dev team got the flu.",
                        "Catching up with unfinished work - need to put in more time than anticipated",
                        0,
                        .2,
                        .2
                        );