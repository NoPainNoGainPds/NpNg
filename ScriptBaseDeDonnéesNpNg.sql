


drop table if exists ACHAT;

drop table if exists BOUTIQUE;

drop table if exists CATEGORIE_BOUTIQUE;

drop table if exists CATEGORIE_EMPLACEMENT;

drop table if exists CATEGORIE_PRODUIT;

drop table if exists CLIENT;

drop table if exists COMPTE_INTERNET;

drop table if exists COORDONNEES_EMPLACEMENT;

drop table if exists EMPLACEMENT;

drop table if exists ENTREE_STOCK;

drop table if exists FOURNISSEUR;

drop table if exists NOEUD_PARCOURS;

drop table if exists PARCOURS;

drop table if exists PERFORMANCE;

drop table if exists PRODUIT;

drop table if exists PROFIL_CLIENT;

drop table if exists REDEVANCE;

drop table if exists SORTIE_STOCK;

/*==============================================================*/
/* Table : ACHAT                                                */
/*==============================================================*/
create table ACHAT
(
   ID_ACHAT             int not null auto_increment,
   ID_CLIENT            int not null,
   primary key (ID_ACHAT)
);

/*==============================================================*/
/* Table : BOUTIQUE                                             */
/*==============================================================*/
create table BOUTIQUE
(
   ID_BOUTIQUE          int not null auto_increment,
   ID_EMPLACEMENT       int not null,
   ID_CATEGORIE_BOUTIQUE int not null,
   NOM_BOUTIQUE         varchar(50),
   primary key (ID_BOUTIQUE)
);

/*==============================================================*/
/* Table : CATEGORIE_BOUTIQUE                                   */
/*==============================================================*/
create table CATEGORIE_BOUTIQUE
(
   ID_CATEGORIE_BOUTIQUE int not null auto_increment,
   NOM_CATEGORIE_BOUTIQUE varchar(25),
   primary key (ID_CATEGORIE_BOUTIQUE)
);

/*==============================================================*/
/* Table : CATEGORIE_EMPLACEMENT                                */
/*==============================================================*/
create table CATEGORIE_EMPLACEMENT
(
   ID_CATEGORIE_EMPLACEMENT int not null auto_increment,
   NOM_EMPLACEMENT      varchar(25),
   SUPERFICIE           decimal(5,2),
   POSITION             varchar(25),
   primary key (ID_CATEGORIE_EMPLACEMENT)
);

/*==============================================================*/
/* Table : CATEGORIE_PRODUIT                                    */
/*==============================================================*/
create table CATEGORIE_PRODUIT
(
   ID_CATEGORIE_PRODUIT int not null auto_increment,
   NOM_CATEGORIE_PRODUIT varchar(25),
   primary key (ID_CATEGORIE_PRODUIT)
);

/*==============================================================*/
/* Table : CLIENT                                               */
/*==============================================================*/
create table CLIENT
(
   ID_CLIENT            int not null auto_increment,
   ID_PROFIL            int not null,
   NOM_CLIENT           varchar(25),
   PRENOM_CLIENT        varchar(25),
   SEXE                 varchar(25),
   ADRESSE_CLIENT       varchar(100),
   DATE_NAISSANCE       date,
   TELEPHONE            char(17),
   primary key (ID_CLIENT)
);

/*==============================================================*/
/* Table : COMPTE_INTERNET                                      */
/*==============================================================*/
create table COMPTE_INTERNET
(
   MAIL                 varchar(50) not null,
   ID_CLIENT            int not null,
   MOT_DE_PASSE         varchar(25),
   NUM_CARTE_FIDELITE   int,
   POINTS_FIDELITE      int,
   primary key (MAIL)
);

/*==============================================================*/
/* Table : COORDONNEES_EMPLACEMENT                              */
/*==============================================================*/
create table COORDONNEES_EMPLACEMENT
(
   ID_COORDONNEES_EMPLACEMENT int not null auto_increment,
   ID_EMPLACEMENT       int not null,
   COORD_X              int,
   COORD_Y              int,
   primary key (ID_COORDONNEES_EMPLACEMENT)
);

/*==============================================================*/
/* Table : EMPLACEMENT                                          */
/*==============================================================*/
create table EMPLACEMENT
(
   ID_EMPLACEMENT       int not null auto_increment,
   ID_CATEGORIE_EMPLACEMENT int not null,
   NOM_EMPLACEMENT      varchar(25),
   SUPERFICIE           decimal(5,2),
   POSITION             varchar(25),
   primary key (ID_EMPLACEMENT)
);

/*==============================================================*/
/* Table : ENTREE_STOCK                                         */
/*==============================================================*/
create table ENTREE_STOCK
(
   ID_ENTREE            int not null auto_increment,
   ID_PRODUIT           int not null,
   ID_BOUTIQUE          int not null,
   DATE_ENTREE          datetime,
   QUANTITE             int,
   MONTANT              decimal(10,2),
   primary key (ID_ENTREE)
);

/*==============================================================*/
/* Table : FOURNISSEUR                                          */
/*==============================================================*/
create table FOURNISSEUR
(
   ID_FOURNISSEUR       int not null auto_increment,
   NOM_FOURNISSEUR      varchar(25),
   MAIL_FOURNISSEUR     varchar(25),
   TELEPHONE_FOURNISSEUR char(17),
   ADRESSE_FOURNISSEUR  varchar(100),
   primary key (ID_FOURNISSEUR)
);

/*==============================================================*/
/* Table : NOEUD_PARCOURS                                       */
/*==============================================================*/
create table NOEUD_PARCOURS
(
   ID_NOEUD             int not null auto_increment,
   ID_PARCOURS          int not null,
   ID_EMPLACEMENT       int not null,
   ID_NOEUD_SUIVANT     int,
   primary key (ID_NOEUD)
);

/*==============================================================*/
/* Table : PARCOURS                                             */
/*==============================================================*/
create table PARCOURS
(
   ID_PARCOURS          int not null auto_increment,
   ID_PROFIL            int not null,
   NOM_PARCOURS         varchar(25),
   primary key (ID_PARCOURS)
);

/*==============================================================*/
/* Table : PERFORMANCE                                          */
/*==============================================================*/
create table PERFORMANCE
(
   ID_PERFORMANCE       int not null auto_increment,
   ID_BOUTIQUE          int not null,
   DATE_PERFORMANCE     date,
   CHIFFRE_AFFAIRE      decimal(10,2),
   TAUX_FREQUENTATION   decimal(2,2),
   TAUX_OCCUPATION      decimal(2,2),
   NB_CLIENT            int,
   primary key (ID_PERFORMANCE)
);

/*==============================================================*/
/* Table : PRODUIT                                              */
/*==============================================================*/
create table PRODUIT
(
   ID_PRODUIT           int not null auto_increment,
   ID_FOURNISSEUR       int not null,
   ID_CATEGORIE_PRODUIT int not null,
   NOM_PRODUIT          varchar(25),
   COUT_UNITAIRE        decimal(10,2),
   QUANTITE             int,
   CODEBARRE            int,
   LARGEUR              decimal(10,2),
   LONGUEUR             decimal(10,2),
   POIDS                decimal(10,2),
   primary key (ID_PRODUIT)
);

/*==============================================================*/
/* Table : PROFIL_CLIENT                                        */
/*==============================================================*/
create table PROFIL_CLIENT
(
   ID_PROFIL            int not null auto_increment,
   NOM_PROFIL           varchar(25),
   primary key (ID_PROFIL)
);

/*==============================================================*/
/* Table : REDEVANCE                                            */
/*==============================================================*/
create table REDEVANCE
(
   ID_REDEVANCE         int not null auto_increment,
   ID_BOUTIQUE          int not null,
   MONTANT_REDEVANCE    decimal(10,2),
   DATE_REDEVANCE       date,
   primary key (ID_REDEVANCE)
);

/*==============================================================*/
/* Table : SORTIE_STOCK                                         */
/*==============================================================*/
create table SORTIE_STOCK
(
   ID_SORTIE            int not null auto_increment,
   ID_PRODUIT           int not null,
   ID_ACHAT             int not null,
   ID_BOUTIQUE          int not null,
   QUANTITE             int,
   DATE_SORTIE          datetime,
   primary key (ID_SORTIE)
);

alter table ACHAT add constraint FK_EST_EFFECTUE_PAR foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT) on delete restrict on update restrict;

alter table BOUTIQUE add constraint FK_ASSOCIATION_9 foreign key (ID_EMPLACEMENT)
      references EMPLACEMENT (ID_EMPLACEMENT) on delete restrict on update restrict;

alter table BOUTIQUE add constraint FK_POSSEDE_UNE foreign key (ID_CATEGORIE_BOUTIQUE)
      references CATEGORIE_BOUTIQUE (ID_CATEGORIE_BOUTIQUE) on delete restrict on update restrict;

alter table CLIENT add constraint FK_A foreign key (ID_PROFIL)
      references PROFIL_CLIENT (ID_PROFIL) on delete restrict on update restrict;

alter table COMPTE_INTERNET add constraint FK_POSSEDE foreign key (ID_CLIENT)
      references CLIENT (ID_CLIENT) on delete restrict on update restrict;

alter table COORDONNEES_EMPLACEMENT add constraint FK_EST_LOCALISE foreign key (ID_EMPLACEMENT)
      references EMPLACEMENT (ID_EMPLACEMENT) on delete restrict on update restrict;

alter table EMPLACEMENT add constraint FK_EST_AFFECTEE foreign key (ID_CATEGORIE_EMPLACEMENT)
      references CATEGORIE_EMPLACEMENT (ID_CATEGORIE_EMPLACEMENT) on delete restrict on update restrict;

alter table ENTREE_STOCK add constraint FK_CONCERNE foreign key (ID_PRODUIT)
      references PRODUIT (ID_PRODUIT) on delete restrict on update restrict;

alter table ENTREE_STOCK add constraint FK_EST_AJOUTEE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE) on delete restrict on update restrict;

alter table NOEUD_PARCOURS add constraint FK_APPARTIENT_A foreign key (ID_PARCOURS)
      references PARCOURS (ID_PARCOURS) on delete restrict on update restrict;

alter table NOEUD_PARCOURS add constraint FK_EST_LIE foreign key (ID_EMPLACEMENT)
      references EMPLACEMENT (ID_EMPLACEMENT) on delete restrict on update restrict;

alter table PARCOURS add constraint FK_EST_ASSOCIE foreign key (ID_PROFIL)
      references PROFIL_CLIENT (ID_PROFIL) on delete restrict on update restrict;

alter table PERFORMANCE add constraint FK_AFFICHE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE) on delete restrict on update restrict;

alter table PRODUIT add constraint FK_EST_CONTENU foreign key (ID_CATEGORIE_PRODUIT)
      references CATEGORIE_PRODUIT (ID_CATEGORIE_PRODUIT) on delete restrict on update restrict;

alter table PRODUIT add constraint FK_EST_FOURNI foreign key (ID_FOURNISSEUR)
      references FOURNISSEUR (ID_FOURNISSEUR) on delete restrict on update restrict;

alter table REDEVANCE add constraint FK_ASSOCIATION_10 foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE) on delete restrict on update restrict;

alter table SORTIE_STOCK add constraint FK_CONCERNE_CE foreign key (ID_PRODUIT)
      references PRODUIT (ID_PRODUIT) on delete restrict on update restrict;

alter table SORTIE_STOCK add constraint FK_EST_ASSOCIE_A foreign key (ID_ACHAT)
      references ACHAT (ID_ACHAT) on delete restrict on update restrict;

alter table SORTIE_STOCK add constraint FK_EST_RETIRE foreign key (ID_BOUTIQUE)
      references BOUTIQUE (ID_BOUTIQUE) on delete restrict on update restrict;

