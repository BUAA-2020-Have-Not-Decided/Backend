/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/11/29 10:16:41                          */
/*==============================================================*/


drop table if exists AffiliationFieldRank;

drop table if exists ArticleField;

drop table if exists ChildField;

drop table if exists Citation;

drop table if exists Collect_Article;

drop table if exists Collect_Patent;

drop table if exists Collect_Project;

drop table if exists Fields;

drop table if exists Institution;

drop table if exists Message;

drop table if exists Paper;

drop table if exists Patent;

drop table if exists Patent_Possess;

drop table if exists Project;

drop table if exists Project_Possess;

drop table if exists Scholar;

drop table if exists SubscribeScholar;

drop table if exists User;

drop table if exists Writer;

create table AffiliationFieldRank
(
   FieldsId             bigint not null,
   InstitutionId        int not null,
   RankNumber           int not null,
   primary key (FieldsId, InstitutionId)
);

/*==============================================================*/
/* Table: ArticleField                                          */
/*==============================================================*/
create table ArticleField
(
   FieldsId             bigint not null,
   PaperId              bigint not null,
   primary key (FieldsId, PaperId)
);

/*==============================================================*/
/* Table: ChildField                                            */
/*==============================================================*/
create table ChildField
(
   ChildFieldID         bigint not null,
   FieldsId             bigint not null,
   primary key (ChildFieldID, FieldsId)
);

/*==============================================================*/
/* Table: Citation                                              */
/*==============================================================*/
create table Citation
(
   Pap_PaperId          bigint not null,
   PaperId              bigint not null,
   primary key (Pap_PaperId, PaperId)
);

/*==============================================================*/
/* Table: Collect_Article                                       */
/*==============================================================*/
create table Collect_Article
(
   PaperId              bigint not null,
   WatcherID            int not null,
   CollectDatetime      datetime not null,
   primary key (PaperId, WatcherID)
);

/*==============================================================*/
/* Table: Collect_Patent                                        */
/*==============================================================*/
create table Collect_Patent
(
   PatentId             bigint not null,
   WatcherID            int not null,
   CollectDatetime      datetime not null,
   primary key (PatentId, WatcherID)
);

/*==============================================================*/
/* Table: Collect_Project                                       */
/*==============================================================*/
create table Collect_Project
(
   ProjectId            bigint not null,
   WatcherID            int not null,
   CollectDatetime      datetime not null,
   primary key (ProjectId, WatcherID)
);

/*==============================================================*/
/* Table: Fields                                                */
/*==============================================================*/
create table Fields
(
   FieldsId             bigint not null,
   RankNumber           varchar(20),
   NormalizedName       varchar(50),
   DisplayName          varchar(50) not null,
   MainType             varchar(30),
   Level                int,
   PaperCount           bigint,
   PaperFamilyCount     bigint,
   CitationCount        bigint,
   CreateDate           datetime,
   primary key (FieldsId)
);

/*==============================================================*/
/* Table: Institution                                           */
/*==============================================================*/
create table Institution
(
   InstitutionId        int not null,
   InstitutionName      varchar(50) not null,
   NatureIndex          float,
   primary key (InstitutionId)
);

/*==============================================================*/
/* Table: Message                                               */
/*==============================================================*/
create table Message
(
   MessageId            int not null auto_increment,
   PaperId              bigint,
   PatentId             bigint,
   ProjectId            bigint,
   ComplaintMaterialUrl varchar(200),
   SenderUserID         int,
   ReceiverUserID       int,
   MsgTitle             varchar(30) not null,
   MsgContent           varchar(500) not null,
   MsgStatus            int not null,
   SendTime             datetime not null,
   MsgType              int not null,
   primary key (MessageId)
);

/*==============================================================*/
/* Table: Paper                                                 */
/*==============================================================*/
create table Paper
(
   PaperId              bigint not null,
   Doi                  varchar(50) not null,
   DocType              varchar(20) not null,
   PaperTitle           varchar(30) not null,
   Abtract              varchar(1000),
   CitationCount        bigint,
   Date                 date,
   Journal              varchar(30),
   Conference           varchar(30),
   Volume               varchar(30),
   Issue                varchar(30),
   FirstPage            varchar(20),
   LastPage             varchar(20),
   SourceUrl            varchar(200),
   primary key (PaperId)
);

/*==============================================================*/
/* Table: Patent                                                */
/*==============================================================*/
create table Patent
(
   PatentId             bigint not null,
   Abtract              varchar(1000),
   ApplicationDate      date,
   Agency               varchar(50),
   ApplicationNumber    varchar(30),
   Agent                varchar(30),
   Content              varchar(1000),
   Province             varchar(30),
   Location             varchar(50),
   ClassificationNumber varchar(30),
   MainClassificationNumber varchar(30),
   Inventor             varchar(30),
   PublishDate          date,
   Applicant            varchar(30),
   CurrentObligee       varchar(30),
   PublishNumber        varchar(30),
   Title                varchar(50),
   State                varchar(20),
   primary key (PatentId)
);

/*==============================================================*/
/* Table: Patent_Possess                                         */
/*==============================================================*/
create table Patent_Possess
(
   PatentId             bigint not null,
   OwnerID              int not null,
   SequenceNumber       int not null,
   PossessDatetime      datetime not null,
   primary key (PatentId, OwnerID)
);

/*==============================================================*/
/* Table: Project                                               */
/*==============================================================*/
create table Project
(
   ProjectId            bigint not null,
   Organization         varchar(30),
   FundProjectCode      varchar(30),
   Source               varchar(50),
   Doi                  varchar(50),
   FieldName            varchar(30),
   DoiUrl               varchar(200),
   ZhAbstract           varchar(1000),
   FundProject          varchar(30),
   ProjectAuthors       varchar(50),
   FieldCode            varchar(30),
   OrganizationId       varchar(30),
   SupportTypeName      varchar(30),
   ChineseTitle         varchar(30),
   PublishDate          date,
   FundProjectNo        varchar(30),
   AchievementID        varchar(30),
   Journal              varchar(30),
   ProductType          varchar(20),
   ZhKeyword            varchar(50),
   SupportType          varchar(20),
   primary key (ProjectId)
);

/*==============================================================*/
/* Table: Project_Possess                                       */
/*==============================================================*/
create table Project_Possess
(
   ProjectId            bigint not null,
   OwnerID              int not null,
   SequenceNumber        int not null,
   PossessDatetime      datetime not null,
   primary key (ProjectId, OwnerID)
);

/*==============================================================*/
/* Table: Scholar                                               */
/*==============================================================*/
create table Scholar
(
   ScholarId            int not null auto_increment,
   InstitutionId        int,
   EnglishName          varchar(127) not null,
   Name                 varchar(20),
   Title                varchar(50),
   Organization         varchar(30),
   Email                varchar(50),
   Phone                varchar(31),
   Fans                 int not null,
   PersonalPage         varchar(127),
   Introduction         varchar(1023),
   Papers               int not null,
   Citations            int,
   HIndex               int,
   GIndex               int,
   Sociability          int,
   Diversity            int,
   Activity             int,
   AvatarUrl            varchar(200),
   LastKnownTime        datetime not null,
   primary key (ScholarId)
);


/*==============================================================*/
/* Table: DataScholar                                                */
/*==============================================================*/
create table DataScholar
(
   AuthorId             int not null,
   NormalizedName       varchar(127) not null,
   DisplayName          varchar(127),
   LastKnownAffiliationId int,
   PaperCount           int,
   PaperFamilyCount     int,
   CitationCount        int,
   primary key (AuthorId)
);

/*==============================================================*/
/* Table: Paper_DataScholar                                            */
/*==============================================================*/
create table Paper_DataScholar
(
    PaperId         bigint not null,
    AuthorID        int not null,
    primary  key(PaperId,AuthorID)
);
/*==============================================================*/
/* Table: Paper_Institution                                           */
/*==============================================================*/
create table Paper_Institution
(
    PaperId         bigint not null,
    InstitutionId   int not null,
    primary  key(PaperId,InstitutionId)
);

/*==============================================================*/
/* Table: Subscribe_Scholar                                      */
/*==============================================================*/
create table Subscribe_Scholar
(
   FanID                int not null,
   ScholarId            int not null,
   SubscribeDatetime    datetime not null,
   primary key (FanID, ScholarId)
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   UserID               int not null auto_increment,
   Password             varchar(20) not null,
   Name                 varchar(20) not null,
   Sex                  int,
   UserImagePath        varchar(100),
   Organization         varchar(30),
   Email                varchar(50),
   BriefIntroduction    varchar(50),
   Identify             int,
   primary key (UserID)
);

/*==============================================================*/
/* Table: Writer                                                */
/*==============================================================*/
create table Writer
(
   PaperId              bigint not null,
   ScholarId            int not null,
   AuthorSequence       int not null,
   primary key (PaperId, ScholarId)
);
alter table Scholar add constraint FK_UserAndScholar foreign key (UID) references User(UserID);
alter table AffiliationFieldRank add constraint FK_AffiliationFieldRank foreign key (FieldsId)
      references Fields (FieldsId) on delete restrict on update restrict;

alter table AffiliationFieldRank add constraint FK_AffiliationFieldRank2 foreign key (InstitutionId)
      references Institution (InstitutionId) on delete restrict on update restrict;

alter table ArticleField add constraint FK_ArticleField foreign key (FieldsId)
      references Fields (FieldsId) on delete restrict on update restrict;

alter table ArticleField add constraint FK_ArticleField2 foreign key (PaperId)
      references Paper (PaperId) on delete restrict on update restrict;

alter table ChildField add constraint FK_ChildField foreign key (ChildFieldID)
      references Fields (FieldsId) on delete restrict on update restrict;

alter table ChildField add constraint FK_ChildField2 foreign key (FieldsId)
      references Fields (FieldsId) on delete restrict on update restrict;

alter table Citation add constraint FK_Citation foreign key (Pap_PaperId)
      references Paper (PaperId) on delete restrict on update restrict;

alter table Citation add constraint FK_Citation2 foreign key (PaperId)
      references Paper (PaperId) on delete restrict on update restrict;

alter table Collect_Article add constraint FK_Collect_Article foreign key (PaperId)
      references Paper (PaperId) on delete restrict on update restrict;

alter table Collect_Article add constraint FK_Collect_Article2 foreign key (WatcherID)
      references User (UserID) on delete restrict on update restrict;

alter table Collect_Patent add constraint FK_Collect_Patent foreign key (PatentId)
      references Patent (PatentId) on delete restrict on update restrict;

alter table Collect_Patent add constraint FK_Collect_Patent2 foreign key (WatcherID)
      references User (UserID) on delete restrict on update restrict;

alter table Collect_Project add constraint FK_Collect_Project foreign key (ProjectId)
      references Project (ProjectId) on delete restrict on update restrict;

alter table Collect_Project add constraint FK_Collect_Project2 foreign key (WatcherID)
      references User (UserID) on delete restrict on update restrict;

alter table Message add constraint FK_MsgPaper foreign key (PaperId)
      references Paper (PaperId) on delete restrict on update restrict;

alter table Message add constraint FK_MsgPatent foreign key (PatentId)
      references Patent (PatentId) on delete restrict on update restrict;

alter table Message add constraint FK_MsgProject foreign key (ProjectId)
      references Project (ProjectId) on delete restrict on update restrict;

alter table Message add constraint FK_MsgReceiver foreign key (ReceiverUserID)
      references User (UserID) on delete restrict on update restrict;

alter table Message add constraint FK_MsgSender foreign key (SenderUserID)
      references User (UserID) on delete restrict on update restrict;

alter table PatentPossess add constraint FK_PatentPossess foreign key (PatentId)
      references Patent (PatentId) on delete restrict on update restrict;

alter table PatentPossess add constraint FK_ProjectPossess2 foreign key (OwnerID)
      references User (UserID) on delete restrict on update restrict;

alter table Project_Possess add constraint FK_Project_Possess foreign key (ProjectId)
      references Project (ProjectId) on delete restrict on update restrict;

alter table Project_Possess add constraint FK_Project_Possess2 foreign key (OwnerID)
      references User (UserID) on delete restrict on update restrict;

alter table Scholar add constraint FK_AuthorAffiliation foreign key (InstitutionId)
      references Institution (InstitutionId) on delete restrict on update restrict;

alter table SubscribeScholar add constraint FK_SubscribeScholar foreign key (FanID)
      references User (UserID) on delete restrict on update restrict;

alter table SubscribeScholar add constraint FK_SubscribeScholar2 foreign key (ScholarId)
      references Scholar (ScholarId) on delete restrict on update restrict;

alter table Writer add constraint FK_Writer foreign key (PaperId)
      references Paper (PaperId) on delete restrict on update restrict;

alter table Writer add constraint FK_Writer2 foreign key (ScholarId)
      references Scholar (ScholarId) on delete restrict on update restrict;
alter table Paper_DataScholar add constraint  FK_Paper_DataScholar foreign key(PaperId)
      references Paper (PaperId) on delete  restrict  on update  restrict ;
alter table Paper_DataScholar add constraint  FK_Paper_DataScholar2 foreign key(AuthorId)
      references DataScholar (AuthorId) on delete  restrict  on update  restrict ;
alter table Paper_Institution add constraint  FK_Paper_Institution foreign key(PaperId)
      references Paper (PaperId) on delete  restrict  on update  restrict ;
alter table Paper_Institution add constraint  FK_Paper_Institution2 foreign key(InstitutionId)
      references Institution (InstitutionId) on delete  restrict  on update  restrict ;

