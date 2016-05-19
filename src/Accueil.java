import java.util.ArrayList;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
class Accueil
{
    private final BDDManager bdd = new BDDManager();
    private ArrayList<Case> casesAtteignables; // move to partie
    private Partie partie;
    private Case caseMemoire; // move to partie

    private String partieRandomTitre;
    private String partieNormaleTitre;
    private String partieTempsCoupsLimitesTitre;
    private String partieTempsLimiteTitre;
    private String reseauOuiTitre;
    private String reseauNonTitre;
    private String skinBlancNormalTitre;
    private String skinBlancProfsTitre;
    private String skinBlancElevesTitre;
    private String skinNoirNormalTitre;
    private String skinNoirProfsTitre;
    private String skinNoirElevesTitre;

    private String titreLabel;
    private String joueurBlancLabel;
    private String joueurNoirLabel;
    private String typePartieLabel;
    private String skinLabel;
    private String reseauLabel;

    private String nouvellePartieTitre;
    private String rejoindrePartieTitre;
    private String nouveauJoueurTitre;
    private String creditTitre;
    private String chargerPartieTitre;
    private String retourMenuTitre;
    private String lancerPartieTitre;
    private String quitterTitre;

    private String partieSelectionneePourChargement;


    /**
     *Accueil
     * Model de "l'avant partie"
     */
    Accueil()
    {
        partieRandomTitre = "Partie rapide";
        titreLabel = "Echecs";
        joueurBlancLabel = "Selectionnez le joueur blanc :";
        joueurNoirLabel = "Selectionnez le joueur noir :";
        typePartieLabel = "Type de partie :";
        skinLabel = "Skin des pièces :";
        reseauLabel = "Voulez-vous faire une partie en réseau ?";

        nouvellePartieTitre = "Lancer une partie";
        rejoindrePartieTitre = "Rejoindre une partie";
        nouveauJoueurTitre = "Nouveau joueur";
        creditTitre = "Credits";
        chargerPartieTitre = "Charger une partie";
        retourMenuTitre = "Retour";
        lancerPartieTitre = "Lancer la partie";
        quitterTitre = "Quitter";

        partieNormaleTitre = "normale";
        partieTempsCoupsLimitesTitre = "temps limité par coup";
        partieTempsLimiteTitre = "temps limité";
        reseauOuiTitre = "oui";
        reseauNonTitre = "non";
        skinBlancNormalTitre = "classique";
        skinBlancProfsTitre = "professeurs";
        skinBlancElevesTitre = "élèves";
        skinNoirNormalTitre = "classique";
        skinNoirProfsTitre = "professeurs";
        skinNoirElevesTitre = "élèves";

        partieSelectionneePourChargement = "";

    }

    /**
     * majListeJoueur
     * met à jour la liste de joueur dans le formulaire de partie
     * @return (liste des joueurs à jour)
     */
    Vector<String> majListeJoueur()
    {
        return Joueur.listeJoueurs();
    }

    /**
     * lancementPartie
     * Départ une partie selon les critères choisis dans le formulaire
     * 
     * @param pseudo (pseudo du joueur1)
     * @param pseudoAdversaire (pseudo du joueur 2)
     * @param choixJB (choix de l'aspect des pièces blanches)
     * @param choixJN (choix de l'aspect des pièces noires)
     * @param modePartie (1 = normal // 2 = coups limités // 3 = partie limitée)
     * @param netPartie (la partie est elle en réseau ?)
     */
    void lancementPartie(String pseudo, String pseudoAdversaire,
                                int choixJB, int choixJN, int modePartie, boolean netPartie)
    {
        Joueur jBlanc;
        if (pseudo.equals("anonymous"))
            jBlanc = new Joueur(true);
        else
            jBlanc = new Joueur(true, pseudo);

        Joueur jNoir;
        if (pseudoAdversaire.equals("anonymous"))
        {
            System.out.println("coucou");
            jNoir = new Joueur(false);
        }
        else
            jNoir = new Joueur(false, pseudoAdversaire);

        this.partie = new Partie(jBlanc, jNoir, modePartie, netPartie, choixJB, choixJN);
    }

    /**
     * load todo
     * recoit un etat du board et redémarre la partie
     *
     */
    void load(String pseudoBlanc)
    {
        int i;

        // On récupère l'id du joueur blanc
        int idJoueurBlancChargement = Integer.parseInt(bdd.ask("SELECT idJoueur FROM JOUEUR WHERE pseudoJoueur = '"
                + pseudoBlanc + "';").get(0).get(0));

        // On récupère toutes les infos nécessaires au lancement de la partie qui concerne le joueur blanc
        ArrayList<ArrayList<String>> elementsPartie = bdd.ask("SELECT * FROM SAUVEGARDE WHERE joueurBlancSave = "
                + idJoueurBlancChargement + ";");

        // Récupération de l'historique
        int idHistorique = Integer.parseInt(elementsPartie.get(0).get(5));
        String histo = bdd.ask("SELECT * FROM HISTORIQUE WHERE idHistorique = " + idHistorique + ";").get(0).get(3);
        ArrayList<String> histoCoupsJoues = new ArrayList<>();
        for(i=0; i<histo.split("-").length; i++)
            histoCoupsJoues.add(histo.split("-")[i]);

        // Définition du joueur qui a la main
        boolean auTourDuBlanc;
        if(histoCoupsJoues.get(histoCoupsJoues.size()-1).charAt(1) == 'n')
            auTourDuBlanc = true;
        else
            auTourDuBlanc = false;


        // Récupération du choix du skin de chaque joueur
        int skinJoueurBlanc = Integer.parseInt(elementsPartie.get(0).get(8));
        int skinJoueurNoir = Integer.parseInt(elementsPartie.get(0).get(9));

        // Création du plateau a l'identique
        String listeEmplacementPieces = elementsPartie.get(0).get(4);
        String[] pieceParPiece = listeEmplacementPieces.split("-");
        Case[][] plateauChargement = new Case[8][8];
        int xCase;
        int yCase;
        boolean couleurBlanc;
        for(i=0; i<pieceParPiece.length; i++)
        {
            // coordonnées de la case ou se trouve la pièce
            xCase = pieceParPiece[i].charAt(0);
            yCase = pieceParPiece[i].charAt(1);

            // couleur de la piece
            if(pieceParPiece[i].charAt(3) == 'b')
                couleurBlanc = true;
            else
                couleurBlanc = false;

            // en fonction du type de la piece on crée de nouvelles pièces sur le plateau
            if(pieceParPiece[i].charAt(3) == 'p')
                plateauChargement[xCase][yCase].setPiece(new Pion(plateauChargement[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(3) == 'c')
                plateauChargement[xCase][yCase].setPiece(new Cavalier(plateauChargement[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(3) == 't')
                plateauChargement[xCase][yCase].setPiece(new Tour(plateauChargement[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(3) == 'f')
                plateauChargement[xCase][yCase].setPiece(new Fou(plateauChargement[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(3) == 'r')
                plateauChargement[xCase][yCase].setPiece(new Roi(plateauChargement[xCase][yCase], couleurBlanc));
        }

        // Définition des deux joueurs
        Joueur jb = new Joueur(true, pseudoBlanc);
        int idJoueurN = Integer.parseInt(elementsPartie.get(0).get(2));
        String pseudoNoir = bdd.ask("SELECT pseudoJoueur FROM JOUEUR WHERE idJoueur = " + idJoueurN + ";").get(0).get(0);
        Joueur jn = new Joueur(false, pseudoNoir);

        // Définition des cimetières
        ArrayList<Piece> piecesCimetiereB = new ArrayList<>();
        ArrayList<Piece> piecesCimetiereN = new ArrayList<>();
        String nbPiecesB = elementsPartie.get(0).get(6);
        String nbPiecesN = elementsPartie.get(0).get(7);
        // Pour les blancs
        for(i=0; i<nbPiecesB.charAt(0); i++)
            piecesCimetiereB.add(new Pion(null, true));
        for(i=0; i<nbPiecesB.charAt(1); i++)
            piecesCimetiereB.add(new Tour(null, true));
        for(i=0; i<nbPiecesB.charAt(2); i++)
            piecesCimetiereB.add(new Cavalier(null, true));
        for(i=0; i<nbPiecesB.charAt(3); i++)
            piecesCimetiereB.add(new Fou(null, true));
        for(i=0; i<nbPiecesB.charAt(4); i++)
            piecesCimetiereB.add(new Reine(null, true));
        // Pour les noirs
        for(i=0; i<nbPiecesN.charAt(0); i++)
            piecesCimetiereN.add(new Pion(null, false));
        for(i=0; i<nbPiecesN.charAt(1); i++)
            piecesCimetiereN.add(new Tour(null, false));
        for(i=0; i<nbPiecesN.charAt(2); i++)
            piecesCimetiereN.add(new Cavalier(null, false));
        for(i=0; i<nbPiecesN.charAt(3); i++)
            piecesCimetiereN.add(new Fou(null, false));
        for(i=0; i<nbPiecesN.charAt(4); i++)
            piecesCimetiereN.add(new Reine(null, false));

        Partie partieChargement = new Partie(jb, jn, auTourDuBlanc, histoCoupsJoues, skinJoueurBlanc, skinJoueurNoir,
                plateauChargement, piecesCimetiereB, piecesCimetiereN);
        
    }


    // getters & setters
    Partie getPartie() {
        return partie;
    }
    ArrayList<Case> getCasesAtteignables() {
        return casesAtteignables;
    }
    void setCasesAtteignables(ArrayList<Case> casesAtteignables) {
        this.casesAtteignables = casesAtteignables;
    }
    Case getCaseMemoire() {
        return caseMemoire;
    }
    void setCaseMemoire(Case caseMemoire) {
        this.caseMemoire = caseMemoire;
    }
    String getJoueurBlancLabel() {
        return joueurBlancLabel;
    }
    String getJoueurNoirLabel() {
        return joueurNoirLabel;
    }
    String getTypePartieLabel() {
        return typePartieLabel;
    }
    String getSkinLabel() {
        return skinLabel;
    }
    String getReseauLabel() {
        return reseauLabel;
    }
    String getNouvellePartieTitre() {
        return nouvellePartieTitre;
    }
    String getRejoindrePartieTitre() {
        return rejoindrePartieTitre;
    }
    String getNouveauJoueurTitre() {
        return nouveauJoueurTitre;
    }
    String getPartieNormaleTitre() {
        return partieNormaleTitre;
    }
    String getPartieTempsCoupsLimitesTitre() {
        return partieTempsCoupsLimitesTitre;
    }
    String getPartieTempsLimiteTitre() {
        return partieTempsLimiteTitre;
    }
    String getReseauOuiTitre() {
        return reseauOuiTitre;
    }
    String getReseauNonTitre() {
        return reseauNonTitre;
    }
    String getSkinBlancNormalTitre() {
        return skinBlancNormalTitre;
    }
    String getSkinBlancProfsTitre() {
        return skinBlancProfsTitre;
    }
    String getSkinBlancElevesTitre() {
        return skinBlancElevesTitre;
    }
    String getSkinNoirNormalTitre() {
        return skinNoirNormalTitre;
    }
    String getSkinNoirProfsTitre() {
        return skinNoirProfsTitre;
    }
    String getSkinNoirElevesTitre() {
        return skinNoirElevesTitre;
    }
    String getTitreLabel() {
        return titreLabel;
    }
    String getCreditTitre() {
        return creditTitre;
    }
    String getChargerPartieTitre() {
        return chargerPartieTitre;
    }
    String getRetourMenuTitre() {
        return retourMenuTitre;
    }
    String getLancerPartieTitre() {
        return lancerPartieTitre;
    }
    String getQuitterJeuTitre() {
        return quitterTitre;
    }
    String getPartieRandomTitre() {
        return partieRandomTitre;
    }

    String getPartieSelectionneePourChargement() {
        return partieSelectionneePourChargement;
    }

    void setPartieSelectionneePourChargement(String partieSelectionneePourChargement) {
        this.partieSelectionneePourChargement = partieSelectionneePourChargement;
    }
}
