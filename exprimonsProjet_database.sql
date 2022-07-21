CREATE DATABASE IF NOT EXISTS exprimonsNousProjet ;
USE exprimonsNousProjet ;


#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: User
#------------------------------------------------------------

CREATE TABLE User(
        idUser    Int  Auto_increment  NOT NULL ,
        firstname Varchar (50) NOT NULL ,
        lastname  Varchar (50) NOT NULL ,
        email     Varchar (50) NOT NULL ,
        password  Text NOT NULL
	,CONSTRAINT User_PK PRIMARY KEY (idUser)
)ENGINE=InnoDB;


INSERT INTO User VALUES (null, "Sarah", "KOUTA", "skoutalopatey@myges.fr", "test1234"),(null, "Théo", "TORRES", "ttorresdacosta@myges.fr", "root");


#------------------------------------------------------------
# Table: List
#------------------------------------------------------------

CREATE TABLE List(
        idList Int  Auto_increment  NOT NULL ,
        title  Text NOT NULL ,
        idUser Int NOT NULL
	,CONSTRAINT List_PK PRIMARY KEY (idList)

	,CONSTRAINT List_User_FK FOREIGN KEY (idUser) REFERENCES User(idUser)
)ENGINE=InnoDB;


INSERT INTO List VALUES (null, "Tâches à faire", 1),(null, "Tâches en cours", 1),(null, "Tâches terminées", 1),(null, "Tâches bloquées", 1);


#------------------------------------------------------------
# Table: Tag
#------------------------------------------------------------

CREATE TABLE Tag(
        idTag Int  Auto_increment  NOT NULL ,
        name  Varchar (50) NOT NULL
	,CONSTRAINT Tag_PK PRIMARY KEY (idTag)
)ENGINE=InnoDB;


INSERT INTO Tag VALUES (null, "JAVA"),(null, "ANDROID"),(null, "FLUTTER"),(null, "IOS"),(null, "API");


#------------------------------------------------------------
# Table: Task
#------------------------------------------------------------

CREATE TABLE Task(
        idTask      Int  Auto_increment  NOT NULL ,
        title       Varchar (50) NOT NULL ,
        description Text ,
        idUser      Int NOT NULL ,
        idList      Int NOT NULL ,
        idTag       Int NOT NULL
	,CONSTRAINT Task_PK PRIMARY KEY (idTask)

	,CONSTRAINT Task_User_FK FOREIGN KEY (idUser) REFERENCES User(idUser)
	,CONSTRAINT Task_List0_FK FOREIGN KEY (idList) REFERENCES List(idList)
	,CONSTRAINT Task_Tag1_FK FOREIGN KEY (idTag) REFERENCES Tag(idTag)
)ENGINE=InnoDB;


INSERT INTO Task VALUES (null, "Connexion user à l'application Android","Faire la requete vers l'API afin d'assurer la connexion de l'utilisateur de l'application Android", 1, 1, 2);
INSERT INTO Task VALUES (null, "Connexion user à l'application iOS","Faire la requete vers l'API afin d'assurer la connexion de l'utilisateur de l'application iOS", 2, 1, 4);
INSERT INTO Task VALUES (null, "Connexion user à l'application Flutter","Faire la requete vers l'API afin d'assurer la connexion de l'utilisateur de l'application Flutter", 2, 1, 3);


#------------------------------------------------------------
# Table: Comment
#------------------------------------------------------------

CREATE TABLE Comment(
        idComment Int  Auto_increment  NOT NULL ,
        body      Text NOT NULL ,
        date      Date NOT NULL ,
        idTask    Int NOT NULL ,
        idUser    Int NOT NULL
	,CONSTRAINT Comment_PK PRIMARY KEY (idComment)

	,CONSTRAINT Comment_Task_FK FOREIGN KEY (idTask) REFERENCES Task(idTask)
	,CONSTRAINT Comment_User0_FK FOREIGN KEY (idUser) REFERENCES User(idUser)
)ENGINE=InnoDB;

INSERT INTO Comment VALUES (null, "Début de tâche prévu la semaine prochaine.", curdate(), 1,1);
