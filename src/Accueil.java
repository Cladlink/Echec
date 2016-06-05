import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
class Accueil
{
    // attribut pour pesudorezo, ipserveur,skinrzo
    private String adresseIpReseau;
    private String pseudoReseau;
    private int choixSkinReseau;

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
    private String statsJoueurTitre;
    private String creerPartieReseauTitre;
    private String lancerPartieReseauTitre;
    private String rejoindrePartieReseauTitre;

    private String partieSelectionneePourChargement;
    private String pseudoChoisi;


    /**
     *Accueil
     * Model de "l'avant partie"
     */
    Accueil()
    {
        partieRandomTitre = "Partie rapide";
        titreLabel = "Echecs";
        joueurBlancLabel = "Selectionnez le joueur 1 :";
        joueurNoirLabel = "Selectionnez le joueur 2 :";
        typePartieLabel = "Type de partie :";
        skinLabel = "Skin des pièces :";
        reseauLabel = "Voulez-vous faire une partie en réseau ?";

        nouvellePartieTitre = "Lancer une partie locale";
        rejoindrePartieTitre = "Rejoindre une partie en réseau";
        nouveauJoueurTitre = "Nouveau joueur";
            creditTitre = "Credits";
        chargerPartieTitre = "Charger une partie locale";
        retourMenuTitre = "Retour";
        lancerPartieTitre = "Lancer la partie";
        quitterTitre = "Quitter";
        statsJoueurTitre = "Statistiques des joueurs";
        creerPartieReseauTitre = "Lancer une partie en réseau";
        lancerPartieReseauTitre = "Lancer la partie";
        rejoindrePartieReseauTitre = "Rejoindre la partie";

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
        pseudoChoisi = "";

        pseudoReseau = "";
        adresseIpReseau = "";
        choixSkinReseau = 0;
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
	    this.partie = new Partie(pseudo, pseudoAdversaire, modePartie, netPartie, choixJB, choixJN);
    }

    // ajout SD : méthodes spécifique pour le réseau
    /**
     * lancementPartieReseau
     *
     *
     */
    void initPartieReseau()
    {
        this.partie = new Partie();
    }

    /**
     * load
     * recoit un etat du board et redémarre la partie
     *
     */
    void load(String pseudoBlanc)
    {
        int i;
        // On récupère l'id du joueur blanc
        bdd.start();
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
        boolean white = false;
        String listeEmplacementPieces = elementsPartie.get(0).get(4);
        String[] pieceParPiece = listeEmplacementPieces.split("-");
        Case[][] plateau = new Case[8][8];
        Board board = new Board(partie, plateau);
        int xCase;
        int yCase;
        boolean couleurBlanc;

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


        partie = new Partie(jb, jn, auTourDuBlanc, histoCoupsJoues, skinJoueurBlanc, skinJoueurNoir,
                board, piecesCimetiereB, piecesCimetiereN);
        board.setPartie(partie);
        //initie les cases vides avec leurs couleurs
        for (i = 0; i < plateau.length; i++)
        {
            for (int j = 0; j < plateau[i].length; j++)
            {
                plateau[i][j] = new Case(i, j, null, board, white);
                white = !white;
            }
            white =!white;
        }
        for(i=0; i<pieceParPiece.length; i++)
        {
            System.out.println(pieceParPiece[i]);
            // coordonnées de la case ou se trouve la pièce
            xCase = Character.getNumericValue(pieceParPiece[i].charAt(0));
            yCase = Character.getNumericValue(pieceParPiece[i].charAt(1));
            // couleur de la piece
            if(pieceParPiece[i].charAt(3) == 'b')
                couleurBlanc = true;
            else
                couleurBlanc = false;
            Roi roiBlanc, roiNoir;
            // en fonction du type de la piece on crée de nouvelles pièces sur le plateau
            if(pieceParPiece[i].charAt(2) == 'p')
                plateau[xCase][yCase].setPiece(new Pion(plateau[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(2) == 'c')
                plateau[xCase][yCase].setPiece(new Cavalier(plateau[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(2) == 't')
                plateau[xCase][yCase].setPiece(new Tour(plateau[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(2) == 'f')
                plateau[xCase][yCase].setPiece(new Fou(plateau[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(2) == 'r')
            {
                if (couleurBlanc)
                {
                    roiBlanc = new Roi(plateau[xCase][yCase], true);
                    board.setRoiBlanc(roiBlanc);
                    plateau[xCase][yCase].setPiece(roiBlanc);
                }
                else
                {
                    roiNoir = new Roi(plateau[xCase][yCase], false);
                    board.setRoiNoir(roiNoir);
                    plateau[xCase][yCase].setPiece(roiNoir);
                }


            }
        }
        for (i = 0; i < board.getPlateau().length; i++)
        {
            for (int j = 0; j < board.getPlateau()[i].length; j++)
            {
                if (board.getPlateau()[i][j].getPiece() != null
                        && board.getPlateau()[i][j].getPiece().blanc)
                {
                    partie.getPiecesBlanchesPlateau().add(board.getPlateau()[i][j].getPiece());
                }
                else if (board.getPlateau()[i][j].getPiece() != null
                        && !board.getPlateau()[i][j].getPiece().blanc)
                {
                    partie.getPiecesNoiresPlateau().add(board.getPlateau()[i][j].getPiece());
                }
            }
        }
        Case cim = new Case(8, 8, null, board ,true);
        // Pour les blancs
        for(i=0; i<Character.getNumericValue(nbPiecesB.charAt(0)); i++)
            piecesCimetiereB.add(new Pion(cim, true));
        for(i=0; i<Character.getNumericValue(nbPiecesB.charAt(1)); i++)
            piecesCimetiereB.add(new Tour(cim, true));
        for(i=0; i<Character.getNumericValue(nbPiecesB.charAt(2)); i++)
            piecesCimetiereB.add(new Cavalier(cim, true));
        for(i=0; i<Character.getNumericValue(nbPiecesB.charAt(3)); i++)
            piecesCimetiereB.add(new Fou(cim, true));
        for(i=0; i<Character.getNumericValue(nbPiecesB.charAt(4)); i++)
            piecesCimetiereB.add(new Reine(cim, true));
        // Pour les noirs
        for(i=0; i<Character.getNumericValue(nbPiecesN.charAt(0)); i++)
            piecesCimetiereN.add(new Pion(cim, false));
        for(i=0; i<Character.getNumericValue(nbPiecesN.charAt(1)); i++)
            piecesCimetiereN.add(new Tour(cim, false));
        for(i=0; i<Character.getNumericValue(nbPiecesN.charAt(2)); i++)
            piecesCimetiereN.add(new Cavalier(cim, false));
        for(i=0; i<Character.getNumericValue(nbPiecesN.charAt(3)); i++)
            piecesCimetiereN.add(new Fou(cim, false));
        for(i=0; i<Character.getNumericValue(nbPiecesN.charAt(4)); i++)
            piecesCimetiereN.add(new Reine(cim, false));

    }

    /**
     * attenteDebutPartie
     * comportement d'attente du début de la partie
     *
     */
    synchronized void attenteDebutPartie()
    {

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

    String getStatsJoueurTitre() {
        return statsJoueurTitre;
    }
    String getPseudoChoisi() {
        return pseudoChoisi;
    }
    void setPseudoChoisi(String pseudoChoisi) {
        this.pseudoChoisi = pseudoChoisi;
    }
    int getChoixSkinReseau() {
        return choixSkinReseau;
    }
    void setChoixSkinReseau(int choixSkinReseau) {
        this.choixSkinReseau = choixSkinReseau;
    }
    String getPseudoReseau() {
        return pseudoReseau;
    }
    void setPseudoReseau(String pseudoReseau) {
        this.pseudoReseau = pseudoReseau;
    }
    String getAdresseIpReseau() {
        return adresseIpReseau;
    }
    void setAdresseIpReseau(String adresseIpReseau) {
        this.adresseIpReseau = adresseIpReseau;
    }
    String getCreerPartieReseauTitre() {
        return creerPartieReseauTitre;
    }
    String getLancerPartieReseauTitre() {
        return lancerPartieReseauTitre;
    }
    void setLancerPartieReseauTitre(String lancerPartieReseauTitre) {
        this.lancerPartieReseauTitre = lancerPartieReseauTitre;
    }
    String getRejoindrePartieReseauTitre() {
        return rejoindrePartieReseauTitre;
    }
}
