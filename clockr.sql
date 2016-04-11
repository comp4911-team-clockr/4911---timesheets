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

INSERT INTO Employees VALUES (1, "Bob", "Smith", "000001", 3, 10, 0, "2010-10-10", "aa@aa.aa", "P1");
INSERT INTO Employees VALUES (2, "Jane", "Doe", "000002", 3, 10, 0, "1995-01-01", "bb@bb.bb", "P5");
INSERT INTO Employees VALUES (3, "Cisco", "Ramon", "000003", 4, 10, 0, "2010-01-01", "cc@cc.cc", "P3");
INSERT INTO Employees VALUES (4, "Barry", "Allen", "000004", 3, 10, 0, "2011-01-01", "dd@dd.dd", "P3");
INSERT INTO Employees VALUES (5, "Bruce", "Wayne", "000005", 3, 5, 0, "2011-10-07", "ee@ee.ee", "P4");
INSERT INTO Employees VALUES (6, "Clark", "Kent", "000006", 4, 10, 0, "2011-11-01", "ff@ff.ff", "P3");
INSERT INTO Employees VALUES (7, "Sally", "Sue", "000007", 3, 10, 0, "2011-12-01", "gg@hh.hh", "P2");
INSERT INTO Employees VALUES (8, "Emily", "Doe", "000008", 3, 10, 0, "2012-01-01", "hh@hh.hh", "DS");
INSERT INTO Employees VALUES (9, "Wally", "West", "000009", 4, 10, 0, "2012-02-05", "ii@ii.ii", "SS");
INSERT INTO Employees VALUES (10, "Jackee", "Ma", "000010", 4, 10, 0, "1994-10-07", "jj@jj.jj", "P3");
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

INSERT INTO Credentials VALUES ("000002", "password", 002, "ProjectManager", "bb@bb.bb" ,
                                "",
                                "",
                                "",
                                "-1889844622");

INSERT INTO Credentials VALUES ("000003", "password", 003, "HumanResource", "cc@cc.cc" ,
                                "",
                                "",
                                "",
                                "-58714991");

INSERT INTO Credentials VALUES ("000004", "password", 003, "HumanResource", "dd@dd.dd" ,
                                "",
                                "",
                                "",
                                "128438349");

INSERT INTO Credentials VALUES ("000005", "password", 002, "ProjectManager", "ee@ee.ee" ,
                                "",
                                "",
                                "",
                                "");

INSERT INTO Credentials VALUES ("000006", "password", 002, "ProjectManager", "ff@ff.ff" , 
                                "",
                                "",
                                "",
                                "");

INSERT INTO Credentials VALUES ("000007", "password", 001, "Employee", "gg@gg.gg" ,
                                "",
                                "",
                                "",
                                "");

INSERT INTO Credentials VALUES ("000008", "password", 002, "ProjectManager", "hh@hh.hh" ,
                                "",
                                "",
                                "",
                                "");

INSERT INTO Credentials VALUES ("000009", "password", 001, "Employee", "ii@ii.ii" ,
                                "",
                                "",
                                "",
                                "");

INSERT INTO Credentials VALUES ("000010", "password", 001, "Employee", "jj@jj.jj" ,
                                "",
                                "",
                                "",
                                "");

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

INSERT INTO TimeSheet VALUES("2|1", 2, 25, "2016-02-01", 160, 0, 0, 32, 32, 32, 32, 32, 0, "", TRUE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("1|1", 1, 25, "2016-02-01", 160, 0, 0, 32, 32, 32, 32, 32, 0, "", FALSE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("3|1", 3, 25, "2016-02-01", 100, 0, 0, 20, 20, 20, 20, 20, 0, "", FALSE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("4|1", 4, 26, "2016-02-08", 130, 0, 0, 25, 25, 25, 25, 30, 0, "", TRUE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("5|1", 5, 26, "2016-02-08", 160, 0, 0, 32, 32, 32, 32, 32, 0, "", FALSE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("6|1", 6, 27, "2016-02-15", 160, 0, 0, 32, 32, 32, 32, 32, 0, "", TRUE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("7|1", 7, 27, "2016-02-15", 150, 0, 0, 25, 25, 25, 25, 50, 0, "", FALSE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("8|1", 8, 27, "2016-02-15", 140, 0, 0, 30, 20, 30, 20, 40, 0, "", TRUE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("9|1", 9, 27, "2016-02-15", 130, 0, 0, 25, 25, 25, 25, 30, 0, "", FALSE, FALSE, TRUE);
INSERT INTO TimeSheet VALUES("10|1", 10, 27, "2016-02-15", 120, 0, 0, 30, 30, 30, 10, 20, 0, "", TRUE, FALSE, TRUE);

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

INSERT INTO TimeSheetRow VALUES("1|1|1", "1|1", 1, 1, 40, 0, 0, 8, 8, 8, 8, 8, 0, 0, "much codin");
INSERT INTO TimeSheetRow VALUES("1|1|2", "1|1", 1, 1, 44, 0, 0, 12, 8, 8, 8, 8, 0, 0, "such hax");
INSERT INTO TimeSheetRow VALUES("1|1|3", "1|1", 1, 1, 32, 0, 0, 0, 8, 8, 8, 8, 1, 0, "very db");
INSERT INTO TimeSheetRow VALUES("2|1|1", "2|1", 2, 1, 40, 0, 0, 8, 8, 8, 8, 8, 0, 0, "i be chillin");
INSERT INTO TimeSheetRow VALUES("2|1|2", "2|1", 2, 1, 44, 0, 0, 12, 8, 8, 8, 8, 0, 0, "like a pro");
INSERT INTO TimeSheetRow VALUES("2|1|3", "2|1", 2, 1, 40, 0, 0, 1, 8, 8, 8, 8, 1, 7, "droppin beats");
INSERT INTO TimeSheetRow VALUES("2|1|4", "2|1", 2, 1, 37, 0, 0, 5, 8, 8, 8, 8, 0, 0, "in slow mo");
INSERT INTO TimeSheetRow VALUES("3|1|1", "3|1", 1, 1, 41, 0, 0, 9, 8, 8, 8, 8, 0, 0, "my individuality");
INSERT INTO TimeSheetRow VALUES("4|1|1", "4|1", 1, 1, 41, 0, 0, 8, 8, 8, 8, 9, 0, 0, "gets less and less");
INSERT INTO TimeSheetRow VALUES("5|1|1", "5|1", 2, 1, 40, 0, 0, 8, 7, 8, 8, 9, 0, 0, "further down");
INSERT INTO TimeSheetRow VALUES("6|1|1", "6|1", 1, 1, 39, 0, 0, 8, 7, 7, 8, 9, 0, 0, "this rabbit hole");
INSERT INTO TimeSheetRow VALUES("7|1|1", "7|1", 2, 1, 42, 0, 0, 10, 7, 8, 8, 9, 0, 0, "i am the dark");
INSERT INTO TimeSheetRow VALUES("8|1|1", "8|1", 1, 1, 36, 0, 0, 8, 7, 7, 5, 9, 0, 0, "i am batman");
INSERT INTO TimeSheetRow VALUES("9|1|1", "9|1", 2, 1, 40, 0, 0, 8, 7, 8, 8, 9, 0, 0, "i really dig this website");
INSERT INTO TimeSheetRow VALUES("10|1|1", "10|1", 2, 1, 40, 0, 0, 8, 7, 8, 8, 9, 0, 0, "hey supervisor guy, im diggin your sweater last week");

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
					            Descript TEXT NOT NULL,
                      IsActive BOOL NOT NULL);

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
                            "",
                            TRUE);
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
                            "",
                            TRUE);

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
CREATE TABLE EmployeeWPList(WpEmpId TINYTEXT, WpId TINYTEXT, EmpNum int, ProjectId int);

INSERT INTO EmployeeWPList VALUES(
              "1|0|1",
              NULL,
              1,
              1);
INSERT INTO EmployeeWPList VALUES(
              "1|0|2",
              NULL,
              2,
              1);
INSERT INTO EmployeeWPList VALUES(
              "2|0|3",
              NULL,
              3,
              2);
INSERT INTO EmployeeWPList VALUES(
              "2|0|4",
              NULL,
              4,
              2);
INSERT INTO EmployeeWPList VALUES(
              "1|0|5",
              NULL,
              5,
              1);
INSERT INTO EmployeeWPList VALUES(
							"1|1|1",
							"1|1",
							1,
              1);
INSERT INTO EmployeeWPList VALUES(
							"1|1|2",
							"1|1",
							2,
              1);
INSERT INTO EmployeeWPList VALUES(
							"2|1|3",
							"2|1",
							3,
              2);
INSERT INTO EmployeeWPList VALUES(
							"2|1|4",
							"2|1",
							4,
              2);

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
