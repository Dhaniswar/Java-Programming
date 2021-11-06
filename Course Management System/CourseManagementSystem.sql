Create Database CourseManagementSystem;
create table AdminList(
ID int primary key identity(1,1),
AdminName nvarchar(50) not null,
Password nvarchar(50)
);
create table CourseList(
 CID int primary key identity(1,1),
 CourseName nvarchar(50) unique not null,
 CourseStatus nvarchar(20) default('Active') not null check(CourseStatus like 'Active' or CourseStatus like 'Disabled')
 );

create table LevelList(
LevelID int primary key identity(1,1),
LevelName nvarchar(50) not null,
CourseID int not null foreign key references CourseList(CID),
Unique(LevelName,CourseID)
); 
create table Instructor(
ID int primary key identity(1,1),
IName nvarchar(50) not null,
Password nvarchar(50)
);
create table Student(
StdID int primary key,
StdName nvarchar(50) not null,
EnrolledLevelID int not null foreign key references LevelList(LevelID),
Password nvarchar(50)
);
create table ModuleList(
MID int primary key identity(1,1),
MName nvarchar(50) not null,
LevelID int not null foreign key references LevelList(LevelID),
InstructorID int foreign key references Instructor(ID),
IsElective bit not null default(0),
Unique(MName)
);
create table MarkRecord(
StdID int not null foreign key references Student(StdID),
MID int not null foreign key references ModuleList(MID),
Mark int,
primary key(StdID,MID)
);