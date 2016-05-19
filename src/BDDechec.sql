CREATE TABLE JOUEUR 
(
	idJoueur INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pseudoJoueur VARCHAR(100),
	nbPartiesJoueur INT(100),
	nbPartiesGagneesJoueur INT(100),
	nbPartiesPerduesJoueur INT(100),
	nbPartiesAbandonneeJoueur INT(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE HISTORIQUE
(
	idHistorique INT(100) NOT NULL AUTO_INCREMENT,
	joueurBlancPartie INT(100),
	joueurNoirPartie INT(100),
	datePartie date,
	coupsJouee text,
	CONSTRAINT fk_historique_joueurBlanc FOREIGN KEY (joueurBlancPartie) REFERENCES JOUEUR(idJoueur),
	CONSTRAINT fk_historique_joueurNoir FOREIGN KEY (joueurNoirPartie) REFERENCES JOUEUR(idJoueur),
	PRIMARY KEY(idHistorique)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE SAUVEGARDE
(
	idSauvegarde INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	joueurBlancSave INT(100),
	joueurNoirSave INT(100),
	tourSave boolean,
	etatPlateauSave TEXT,
	idHistorique INT(100),
	listePiecesBlanchesCimetiereSave VARCHAR(5),
	listePiecesNoiresCimetiereSave VARCHAR(5),
	skinJoueurBlancSave INT,
	skinJoueurNoirSave INT,
	CONSTRAINT fk_sauvegarde_joueurBlanc FOREIGN KEY (joueurBlancSave) REFERENCES JOUEUR(idJoueur),
	CONSTRAINT fk_sauvegarde_joueurNoir FOREIGN KEY (joueurNoirSave) REFERENCES JOUEUR(idJoueur),
	CONSTRAINT fk_sauvegarde_historique FOREIGN KEY (idHistorique) REFERENCES HISTORIQUE(idHistorique)
)ENGINE=InnoDB DEFAULT CHARSET=utf8