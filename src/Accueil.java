import java.util.ArrayList;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
class Accueil
{
    private String adresseIpReseau;
    private final BDDManager bdd = new BDDManager();
    private Partie partie;
    private String partieSelectionneePourChargement;
    private String pseudoChoisi;
    private String partieAVisualiser;

    /**
     *Accueil
     * Model de "l'avant partie" c'est à dire de la partie des éléments qui composent le menu d'accueil
     */
    Accueil()
    {
        partieSelectionneePourChargement = "";
        pseudoChoisi = "";
        partieAVisualiser = "";
        adresseIpReseau = "";
    }

    /**
     * majListeJoueur
     * met à jour la liste de joueur dans le formulaire de partie
     * @return (liste des joueurs à jour)
     */
    Vector<String> majListeJoueur() { return Joueur.listeJoueurs(); }


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
        boolean auTourDuBlanc = histoCoupsJoues.get(histoCoupsJoues.size() - 1).charAt(1) == 'n';
        // Récupération du choix du skin de chaque joueur
        int skinJoueurBlanc = Integer.parseInt(elementsPartie.get(0).get(8));
        int skinJoueurNoir = Integer.parseInt(elementsPartie.get(0).get(9));
        // Création du plateau a l'identique
        String listeEmplacementPieces = elementsPartie.get(0).get(4);
        String[] pieceParPiece = listeEmplacementPieces.split("-");
        Case[][] plateau = new Case[8][8];
        Board board = new Board(partie, plateau);
        int xCase, yCase;
        boolean couleurBlanc;
        // Définition des deux joueurs
        Joueur joueurBlanc = new Joueur(true, pseudoBlanc);
        int idJoueurNoir = Integer.parseInt(elementsPartie.get(0).get(2));
        String pseudoNoir = bdd.ask("SELECT pseudoJoueur FROM JOUEUR WHERE idJoueur = " + idJoueurNoir + ";").get(0).get(0);
        Joueur joueurNoir = new Joueur(false, pseudoNoir);
        // Définition des cimetières
        ArrayList<Piece> piecesCimetiereBlanc = new ArrayList<>();
        ArrayList<Piece> piecesCimetiereNoir = new ArrayList<>();
        String nbPieceBlanc = elementsPartie.get(0).get(6);
        String nbPieceNoir = elementsPartie.get(0).get(7);

        partie = new Partie(joueurBlanc, joueurNoir, auTourDuBlanc, histoCoupsJoues, skinJoueurBlanc, skinJoueurNoir,
                board, piecesCimetiereBlanc, piecesCimetiereNoir);
        board.setPartie(partie);
        //initie les cases vides avec leurs couleurs
        initieCasesVides(plateau, board);
        for(i=0; i<pieceParPiece.length; i++)
        {
            // coordonnées de la case ou se trouve la pièce
            xCase = Character.getNumericValue(pieceParPiece[i].charAt(0));
            yCase = Character.getNumericValue(pieceParPiece[i].charAt(1));
            // couleur de la piece
            couleurBlanc = pieceParPiece[i].charAt(3) == 'b';
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
            else if(pieceParPiece[i].charAt(2) == 'q')
                plateau[xCase][yCase].setPiece(new Reine(plateau[xCase][yCase], couleurBlanc));
            else if(pieceParPiece[i].charAt(2) == 'r')
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
        for (i = 0; i < board.getPlateau().length; i++)
            for (int j = 0; j < board.getPlateau()[i].length; j++)
                if (board.getPlateau()[i][j].getPiece() != null
                        && board.getPlateau()[i][j].getPiece().blanc)
                    partie.getPiecesBlanchesPlateau().add(board.getPlateau()[i][j].getPiece());
                else if (board.getPlateau()[i][j].getPiece() != null
                        && !board.getPlateau()[i][j].getPiece().blanc)
                    partie.getPiecesNoiresPlateau().add(board.getPlateau()[i][j].getPiece());
        Case cim = new Case(8, 8, null, board ,true);
        loadCimetiere(piecesCimetiereBlanc, piecesCimetiereNoir, nbPieceBlanc, nbPieceNoir, cim);
    }

    /**
     * initieCasesVides
     * initie le plateau de cases vides
     *
     * @param plateau (plateau de cases)
     * @param board (objet qui contient le plateau)
     */
    private void initieCasesVides( Case[][] plateau, Board board)
    {
        int i;
        boolean white = false;
        for (i = 0; i < plateau.length; i++)
        {
            for (int j = 0; j < plateau[i].length; j++)
            {
                plateau[i][j] = new Case(i, j, null, board, white);
                white = !white;
            }
            white =!white;
        }
    }

    /**
     * loadCimetiere
     * Place les pièces dans le cimetières
     *
     * @param piecesCimetiereBlanc (pièces du cimetière blanc)
     * @param piecesCimetiereNoir (pièces du cimetière noir)
     * @param nbPiecesBlanches (nombre de pièces blanches par type)
     * @param nbPiecesNoires (nombre de pièces noires par type)
     * @param caseFictiveCimetiere ( case fictive représentant le cimetiere)
     */
    private void loadCimetiere(ArrayList<Piece> piecesCimetiereBlanc, ArrayList<Piece> piecesCimetiereNoir,
                               String nbPiecesBlanches, String nbPiecesNoires, Case caseFictiveCimetiere)
    {
        int i;
        for(i=0; i<Character.getNumericValue(nbPiecesBlanches.charAt(0)); i++)
            piecesCimetiereBlanc.add(new Pion(caseFictiveCimetiere, true));
        for(i=0; i<Character.getNumericValue(nbPiecesBlanches.charAt(1)); i++)
            piecesCimetiereBlanc.add(new Tour(caseFictiveCimetiere, true));
        for(i=0; i<Character.getNumericValue(nbPiecesBlanches.charAt(2)); i++)
            piecesCimetiereBlanc.add(new Cavalier(caseFictiveCimetiere, true));
        for(i=0; i<Character.getNumericValue(nbPiecesBlanches.charAt(3)); i++)
            piecesCimetiereBlanc.add(new Fou(caseFictiveCimetiere, true));
        for(i=0; i<Character.getNumericValue(nbPiecesBlanches.charAt(4)); i++)
            piecesCimetiereBlanc.add(new Reine(caseFictiveCimetiere, true));

        for(i=0; i<Character.getNumericValue(nbPiecesNoires.charAt(0)); i++)
            piecesCimetiereNoir.add(new Pion(caseFictiveCimetiere, false));
        for(i=0; i<Character.getNumericValue(nbPiecesNoires.charAt(1)); i++)
            piecesCimetiereNoir.add(new Tour(caseFictiveCimetiere, false));
        for(i=0; i<Character.getNumericValue(nbPiecesNoires.charAt(2)); i++)
            piecesCimetiereNoir.add(new Cavalier(caseFictiveCimetiere, false));
        for(i=0; i<Character.getNumericValue(nbPiecesNoires.charAt(3)); i++)
            piecesCimetiereNoir.add(new Fou(caseFictiveCimetiere, false));
        for(i=0; i<Character.getNumericValue(nbPiecesNoires.charAt(4)); i++)
            piecesCimetiereNoir.add(new Reine(caseFictiveCimetiere, false));
    }

    // getters & setters
    Partie getPartie() { return partie; }
    String getPartieSelectionneePourChargement() { return partieSelectionneePourChargement; }
    void setPartieSelectionneePourChargement(String partieSelectionneePourChargement)
    { this.partieSelectionneePourChargement = partieSelectionneePourChargement; }
    String getPseudoChoisi() { return pseudoChoisi; }
    void setPseudoChoisi(String pseudoChoisi) { this.pseudoChoisi = pseudoChoisi; }
    String getAdresseIpReseau() { return adresseIpReseau; }
    void setAdresseIpReseau(String adresseIpReseau) { this.adresseIpReseau = adresseIpReseau; }
    String getPartieAVisualiser() { return partieAVisualiser; }
    void setPartieAVisualiser(String partieAVisualiser) { this.partieAVisualiser = partieAVisualiser; }
}