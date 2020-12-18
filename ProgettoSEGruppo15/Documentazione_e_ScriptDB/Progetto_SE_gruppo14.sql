CREATE DATABASE Progetto_SE_gruppo14

drop table if exists Planner cascade;
drop table if exists Maintainer cascade;
drop table if exists Competence cascade;
drop table if exists Procedure cascade;
drop table if exists CompetenceToProcedure cascade;
drop table if exists Site cascade;
drop table if exists Typology cascade;
drop table if exists TypologyToCompetence cascade;
drop table if exists CompetenceToMaintainer cascade;
drop table if exists MaintenanceActivity cascade;
drop table if exists MaintainerAvailabilityCurrentWeek cascade;
drop table if exists ActivityTimeDivision cascade;


drop user if exists utente_progetto_se;
/*-----------------------------------
              TABLES
------------------------------------*/	
create table Planner (
	plannerID char(6) Primary Key check(char_length(plannerID)=6),
	plannerPassword varchar(30) not null
);
create table Maintainer (
	maintainerID char(6) Primary Key check(char_length(maintainerID)=6),
	maintainerName varchar(30) not null unique,
	maintainerPassword varchar(30) not null
	
);
create table Competence (
	competenceID char(6) Primary Key check(char_length(competenceID)=6),
	competenceName varchar(30) not null,
	competenceDescription text
);
create table Procedure (
	procedureID char(6) Primary Key check(char_length(procedureID)=6),
	procedureName varchar(30) ,
	procedureDescription varchar(100),
	fileSMP text
);
create table CompetenceToProcedure (
	procedureID char(6) check(char_length(procedureID)=6),
	competenceID char(6) check(char_length(competenceID)=6),
	primary key (procedureID,competenceID),
	foreign key (procedureID)references Procedure(procedureID) on delete cascade on update cascade,
	foreign key (competenceID)references Competence(competenceID) on delete cascade on update cascade
);
create table Site (
	siteID char(6) Primary Key check(char_length(siteID)=6),
	factorySite varchar(30) not null,
	areaSite varchar(30) not null
);
create table Typology (
	activityTypology varchar(20) Primary Key
);
create table TypologyToCompetence (
	competenceID char(6) check(char_length(competenceID)=6),
	activityTypology varchar(20) ,
	primary key (activityTypology,competenceID),
	foreign key (competenceID)references Competence(competenceID) on delete cascade on update cascade,
	foreign key (activityTypology)references Typology(activityTypology) on delete cascade on update cascade
);
create table CompetenceToMaintainer (
	competenceID char(6) check(char_length(competenceID)=6),
	maintainerID char(6) check(char_length(maintainerID)=6),
	primary key (competenceID,maintainerID),
	foreign key (maintainerID)references Maintainer(maintainerID) on delete cascade on update cascade,
	foreign key (competenceID)references Competence(competenceID) on delete cascade on update cascade
);
create table MaintenanceActivity (
	activityID varchar(10) Primary Key,
	plannerID char(6) check(char_length(plannerID)=6),
	maintainerID char(6) check(char_length(maintainerID)=6),
	siteID char(6) check(char_length(siteID)=6),
	procedureID char(6) check(char_length(procedureID)=6),
	plannedActivity varchar(10) not null,
	activityDescription text not null,
	activityInterventionTime int not null,
	interruptibleActivity bool not null,
	activityMaterials varchar(200),
	activityWeekNumber int not null,
	check(activityWeekNumber >= 1 and activityWeekNumber <=52),
	activityWorkspaceNotes text,
	activityTypology varchar(20) not null,
	foreign key (plannerID)references Planner(plannerID) on delete cascade on update cascade,
	foreign key (activityTypology)references Typology(activityTypology) on delete cascade on update cascade,
	foreign key (maintainerID)references Maintainer(maintainerID) on delete cascade on update cascade,
	foreign key (siteID)references Site(siteID) on delete cascade on update cascade,
	foreign key (procedureID)references Procedure(procedureID) on delete cascade on update cascade
);
create table MaintainerAvailabilityCurrentWeek (
	maintainerID char(6) not null check(char_length(maintainerID)=6),
	Day varchar(20) not null,
	check(Day in ('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') ),
	
	TimeSlot1 int check(TimeSlot1>=0 and TimeSlot1 <=60),
	TimeSlot2 int check(TimeSlot2>=0 and TimeSlot2 <=60),
	TimeSlot3 int check(TimeSlot3>=0 and TimeSlot3 <=60),
	TimeSlot4 int check(TimeSlot4>=0 and TimeSlot4 <=60),
	TimeSlot5 int check(TimeSlot5>=0 and TimeSlot5 <=60),
	TimeSlot6 int check(TimeSlot6>=0 and TimeSlot6 <=60),
	TimeSlot7 int check(TimeSlot7>=0 and TimeSlot7 <=60),
	TimeSlot8 int check(TimeSlot8>=0 and TimeSlot8 <=60),
	primary key(maintainerID,Day),
	foreign key (maintainerID)references Maintainer(maintainerID) on delete cascade on update cascade
	
);
create table ActivityTimeDivision (
	activityID varchar(10) not null ,
	maintainerID char(6) not null check(char_length(maintainerID)=6),
	Day varchar(20) not null,
	WeekNumber int not null,
	check(WeekNumber >= 1 and WeekNumber <=52 ),
	check(Day in ('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') ),
	
	SlotAssigned1 int check(SlotAssigned1>=0 and SlotAssigned1 <=60),
	SlotAssigned2 int check(SlotAssigned2>=0 and SlotAssigned2 <=60),
	SlotAssigned3 int check(SlotAssigned3>=0 and SlotAssigned3 <=60),
	SlotAssigned4 int check(SlotAssigned4>=0 and SlotAssigned4 <=60),
	SlotAssigned5 int check(SlotAssigned5>=0 and SlotAssigned5 <=60),
	SlotAssigned6 int check(SlotAssigned6>=0 and SlotAssigned6 <=60),
	SlotAssigned7 int check(SlotAssigned7>=0 and SlotAssigned7 <=60),
	SlotAssigned8 int check(SlotAssigned8>=0 and SlotAssigned8 <=60),
	
	primary key(activityID,maintainerID,Day,WeekNumber),
	foreign key (activityID)references MaintenanceActivity(activityID) on delete cascade on update cascade,
	foreign key (maintainerID)references Maintainer(maintainerID) on delete cascade on update cascade
);

/*-----------------------------------
        Test popolamento tabelle
------------------------------------*/	

insert into Typology values('Electrical');
insert into Typology values('Hydraulic');
insert into Typology values('Mechanical');
insert into Typology values('Electronic');

insert into Procedure(procedureID,fileSmp) values('proc01','smp1');

insert into Site values('site01', 'factory1', 'area1');
insert into Site values('site02', 'factory2', 'area2');
insert into Site values('site03', 'factory3', 'area3');

insert into Competence values('comp01', 'skill1','descrizione1');
insert into Competence values('comp02', 'skill2','descrizione2');
insert into Competence values('comp03', 'skill3','descrizione3');
insert into Competence values('comp04', 'skill4','descrizione4');

insert into CompetenceToProcedure values ('proc01', 'comp01');
insert into CompetenceToProcedure values ('proc01', 'comp02');
insert into CompetenceToProcedure values ('proc01', 'comp03');

insert into TypologyToCompetence values('comp01', 'Hydraulic');
insert into TypologyToCompetence values('comp02', 'Hydraulic');
insert into TypologyToCompetence values('comp03', 'Hydraulic');
insert into TypologyToCompetence values('comp02', 'Electronic');

insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,
interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID,plannedActivity)
values ('act005','descrizioneAttivita',120,true,51,'Electrical','site01','proc01','PLANNED');

--per il popolamento dei maintainer utilizzare il main apposito in java denominato DemoAdminInsertNewMaintainer

insert into CompetenceToMaintainer values('comp01', 'mant01');
insert into CompetenceToMaintainer values('comp02', 'mant01');
insert into CompetenceToMaintainer values('comp03', 'mant01');
insert into CompetenceToMaintainer values('comp01', 'mant02');
insert into CompetenceToMaintainer values('comp02', 'mant02');
insert into CompetenceToMaintainer values('comp01', 'mant03');

select*from competence;
select*from CompetenceToProcedure;
select*from TypologyToCompetence;
select*from CompetenceToMaintainer;
select*from MaintenanceActivity;
select*from MaintainerAvailabilityCurrentWeek
	order by maintainerid;
select*from Maintainer;

/*-----------------------------------
              QUERY USED
------------------------------------*/
select* from 
	MaintenanceActivity as ma 
	left join Procedure as p on (ma.procedureID=p.procedureID )
	join Site as s on (ma.siteID=s.siteID )
	order by ma.activityID;

select c.competenceName from 
	MaintenanceActivity as ma 
	join competenceToProcedure as cp on (ma.procedureID=cp.procedureID )
	join competence as c on (c.competenceID=cp.competenceID )
	where ma.activityID='act005'
	order by ma.activityID;

select c.competenceName from
	TypologyToCompetence as tc
	join Competence as c on (c.competenceID=tc.competenceID )
	where tc.activityTypology= 'Hydraulic';

select m.maintainerID,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8
	from MaintainerAvailabilityCurrentWeek as ma
	full join Maintainer as m on (m.maintainerID=ma.maintainerID )
	where ma.day='Monday'
	order by m.maintainerID;
	
select c. competenceID , c.competenceName from 
	competenceToMaintainer as cm 
	join competence as c on (c.competenceID=cm.competenceID )
	where cm.MaintainerID = 'mant01';

select ma.maintainerID,ma.day,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8
	from MaintainerAvailabilityCurrentWeek as ma
	where ma.day='Monday' and ma.maintainerID='mant01';

select ma.maintainerID,ma.day,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8
	from MaintainerAvailabilityCurrentWeek as ma
	where  ma.maintainerID='mant01';
	
--query per ewo
select activityId from ActivityTimeDivision
where maintainerid='mant01'and day='Monday' and weekNumber=51 and slotassigned1>0;

select SlotAssigned1, SlotAssigned2, SlotAssigned3, SlotAssigned4, SlotAssigned5, SlotAssigned6, SlotAssigned7, SlotAssigned8
from ActivityTimeDivision 
where activityId='act005';
--

/*-----------------------------------
              JDBC
------------------------------------*/	

create user utente_progetto_se password 'password';

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO utente_progetto_se;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO utente_progetto_se;

