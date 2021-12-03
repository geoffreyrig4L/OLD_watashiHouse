DROP DATABASE IF EXISTS watashi_house;
create database watashi_house;
use watashi_house;

#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Categorie
#------------------------------------------------------------

CREATE TABLE Categorie(
        id_categorie Int  Auto_increment  NOT NULL ,
        nom          Varchar (30) NOT NULL
	,CONSTRAINT Categorie_PK PRIMARY KEY (id_categorie)
)ENGINE=InnoDB;


insert into categorie values 
(null, "Bureau"),
(null, "Chaise"),                  
(null, "Lampe");

#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        id_utilisateur      Int  Auto_increment  NOT NULL ,
        civilite            Varchar (20) NOT NULL ,
        prenom                 Varchar (30) NOT NULL ,
        nom              Varchar (30) NOT NULL ,
        email               Varchar (50) NOT NULL ,
        mdp                 Varchar (50) NOT NULL ,
        tel                 Varchar (10) NOT NULL ,
        adresse_livraison   Varchar (200) NOT NULL ,
        adresse_facturation Varchar (200) NOT NULL ,
        pays                    Varchar (20)NOT NULL,
        type                Enum ("client","administrateur") NOT NULL
	,CONSTRAINT Utilisateur_PK PRIMARY KEY (id_utilisateur)
)ENGINE=InnoDB;

insert into Utilisateur values 
(null, "Madame", "Olivia", "Hamer", "olivia.hamer@gmail.com", "ohamer", "0601010101","31 rue de Victor Hugo 95210 Argenteuil","31 rue de Victor Hugo 95210 Argenteuil","France", "client"),
(null, "Madame", "Talia", "Zao", "talia.zao@gmail.com", "tzao", "0602020202","45 rue de Marie Curie 77231 Meaux","45 rue de Marie Curie 77231 Meaux","France", "administrateur"),
(null, "Monsieur", "Helio", "Pinto", "helio.pinto@gmail.com", "hpinto", "0603030303","42 avenue du général de Gaulle 93421 Pantin","42 avenue du général de Gaulle 93421 Pantin", "France", "client"),
(null, "Monsieur", "Theo", "Monty", "theo.monty@gmail.com", "tmonty", "0604040404","12 rue de Voltaire 94000 Creteil","12 rue de Voltaire 94000 Creteil","France", "administrateur");

#------------------------------------------------------------
# Table: carte_de_paiement
#------------------------------------------------------------

CREATE TABLE Carte_de_paiement(
        id_carte         Int  Auto_increment  NOT NULL ,
        numero           Varchar(16) NOT NULL ,
        cvc              Int NOT NULL ,
        annee_expiration  Int(2) NOT NULL ,
        mois_expiration  Int(2) NOT NULL ,
        id_utilisateur   Int NOT NULL
	,CONSTRAINT Carte_de_paiement_PK PRIMARY KEY (id_carte)

	,CONSTRAINT Carte_de_paiement_Utilisateur_FK FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur)
)ENGINE=InnoDB;

insert into Carte_de_paiement values 
(null, "4973665498093456" , 312, 24,09, 1),
(null, "4973776708071242" , 555, 25,10, 1),
(null, "4973653474413496" , 985, 23,07, 2),
(null, "4973351111214976" , 182, 22,02, 2);


#------------------------------------------------------------
# Table: Commande
#------------------------------------------------------------

CREATE TABLE Commande(
        id_commande    Int  Auto_increment  NOT NULL ,
        numero         Varchar (10) NOT NULL ,
        date_livraison Date NOT NULL ,
        prix_tot       Float NOT NULL ,
        lien_vers      Varchar (150) NOT NULL ,
        id_utilisateur Int NOT NULL
	,CONSTRAINT Commandes_PK PRIMARY KEY (id_commande)

	,CONSTRAINT Commandes_Utilisateur_FK FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur)
)ENGINE=InnoDB;

insert into commande values 
(null, 1111111111, "2021-12-15", 118.98, "https://watashi-house.com/mon-compte/mes-commandes/1111111111", 1),
(null, 1111111112, "2021-12-16", 126.97, "https://watashi-house.com/mon-compte/mes-commandes/1111111112", 2);


#------------------------------------------------------------
# Table: Collection
#------------------------------------------------------------

CREATE TABLE Collection(
        id_collection Int  Auto_increment  NOT NULL ,
        nom           Varchar (30) NOT NULL
	,CONSTRAINT Collection_PK PRIMARY KEY (id_collection)
)ENGINE=InnoDB;

insert into collection values 
(null, "WatSakura no hana"),         /*fleur de cerisier*/
(null, "Hōken");                     /*japon féodal*/


#------------------------------------------------------------
# Table: Article
#------------------------------------------------------------

CREATE TABLE Article(
        id_article    Int  Auto_increment  NOT NULL ,
        nom           Varchar (100) NOT NULL ,
        description   Varchar (500) NOT NULL ,
        images        Varchar (500) NOT NULL ,
        couleur       Varchar (30) NOT NULL ,
        prix          Float NOT NULL ,
        nbAvis        Int (5) NOT NULL ,
        note          Float NOT NULL,
        stock         Int (3) NOT NULL ,
        id_collection Int NOT NULL
	,CONSTRAINT Article_PK PRIMARY KEY (id_article)

	,CONSTRAINT Article_Collection_FK FOREIGN KEY (id_collection) REFERENCES Collection(id_collection)
)ENGINE=InnoDB;

insert into Article values 
(null, "chaise", "une magnifique chaise", "images/chaise.png", "rouge", 29.99, 75, 4.2, 45, 1), 
(null, "bureau", "une magnifique bureau", "images/bureau.png", "beige", 89.99, 219, 4.4, 31, 1),
(null, "Lampe", "une magnifique lampe", "images/lampe.png", "noir", 24.99, 56, 3.9, 37, 2);

#------------------------------------------------------------
# Table: Appartenir
#------------------------------------------------------------

CREATE TABLE Appartenir(
        id_article   Int NOT NULL ,
        id_categorie Int NOT NULL
	,CONSTRAINT Appartenir_PK PRIMARY KEY (id_article,id_categorie)

	,CONSTRAINT Appartenir_Article_FK FOREIGN KEY (id_article) REFERENCES Article(id_article)
	,CONSTRAINT Appartenir_Categorie0_FK FOREIGN KEY (id_categorie) REFERENCES Categorie(id_categorie)
)ENGINE=InnoDB;

insert into appartenir values 
(1,1),(2,2),(3,3);

#------------------------------------------------------------
# Table: Contenir
#------------------------------------------------------------

CREATE TABLE Contenir(
        id_article  Int NOT NULL ,
        id_commande Int NOT NULL
	,CONSTRAINT Contenir_PK PRIMARY KEY (id_article,id_commande)

	,CONSTRAINT Contenir_Article_FK FOREIGN KEY (id_article) REFERENCES Article(id_article)
	,CONSTRAINT Contenir_Commande0_FK FOREIGN KEY (id_commande) REFERENCES Commande(id_commande)
)ENGINE=InnoDB;

insert into contenir values 
(1,1),(1,2),(3,2),(2,2);
