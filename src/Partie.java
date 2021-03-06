import java.util.*;

/**
    Created by cladlink on 06/04/16.
*/
class Partie
{
    private final static BDDManager bdd = new BDDManager();

    private int choixJoueurBlanc;
    private int choixJoueurNoir;

    private Joueur joueurBlanc;
    private Joueur joueurNoir;

    private ArrayList<Piece> cimetiereBlanc;
    private ArrayList<Piece> cimetiereNoir;
    private ArrayList<Piece> piecesBlanchesPlateau;
    private ArrayList<Piece> piecesNoiresPlateau;

    private ArrayList<Case> casesAtteignables;
    private Case caseMemoire;

    private Board board;
    private boolean tourBlanc;
    // ajout SD : plus partique de gérer avec un id que un boolean
    private int idCurrentPlayer; // = 1 si joueur courant = blanc, = 2 sinon

    private int modePartie; // 0 = partie sans temps ; 1 = temps partie limitée; 2 = temps tour limités
    // ajout SD
    private boolean endOfTurn; // pour signaler la fin d'un tour (mode réseau)
    private Case caseSrc; // pour conserver quel coup vient d'être joueur -> envoi par réseau
    private Case caseDest; // pour conserver quel coup vient d'être joueur -> envoi par réseau
    private int promote = 0;
    
    private boolean echecBlanc;
    private boolean echecNoir;

    private ArrayList<String> historique;

    private boolean partieFinie;    // ajout SD : pour plus de lisibilité avec les modes -> changer les valeurs en dur dans le code
    final static int MODE_NOTIMER = 1;
    final static int MODE_TIMERTURN = 2;
    final static int MODE_TIMERPARTY = 3;

    /**
     * Partie
     * Constructeur pour une partie déjà existante chargée depuis la base de données.
     * @param joueurBlanc (joueur blanc)
     * @param joueurNoir (joueur noir)
     * @param tourBlanc (A qui était le tour avant la sauvegarde)
     * @param historique (Historique de la partie avant sauvegarde)
     * @param choixJoueurBlanc (le skin blanc)
     * @param choixJoueurNoir (le skin noir)
     * @param board (le plateau tel qu'il était avant la sauvegarde)
     * @param cimetiereBlanc (le cimetière blanc tel qu'il était avant la sauvegarde)
     * @param cimetiereNoir (le cimetière noir tel qu'il était avant la sauvegarde)
     */
    Partie(Joueur joueurBlanc, Joueur joueurNoir, boolean tourBlanc, ArrayList<String> historique, int choixJoueurBlanc,
           int choixJoueurNoir, Board board, ArrayList<Piece> cimetiereBlanc, ArrayList<Piece> cimetiereNoir)
    {
        //On ajoute les deux joueurs à la partie
        this.joueurBlanc = joueurBlanc;
        this.joueurNoir = joueurNoir;

        this.choixJoueurBlanc = choixJoueurBlanc;
        this.choixJoueurNoir = choixJoueurNoir;

        piecesBlanchesPlateau = new ArrayList<>();
        piecesNoiresPlateau = new ArrayList<>();
        this.cimetiereBlanc = cimetiereBlanc;
        this.cimetiereNoir = cimetiereNoir;

        // On créé le plateau
        this.board = board;

        System.out.println(piecesBlanchesPlateau);
        System.out.println(piecesNoiresPlateau);

        this.tourBlanc = tourBlanc;

        // ajout SD
        if (tourBlanc)
            idCurrentPlayer = 1;
        else
            idCurrentPlayer = 2;

        // choix du mode de la partie
        this.modePartie = 1;

        // Le roi est protégé en début de partie, il n'y a donc pas d'échec
        echecBlanc = false;
        echecNoir = false;

        partieFinie = false;

        this.historique = historique;
    }

    /**
     * Partie
     * Constructeur pour les parties en local
     *
     * @param pseudo (joueur courant)
     * @param pseudoAdversaire (joueur adverse)
     * @param modePartie (1 = partie normale, 2 = partie tour limité, 3 = partie temps limité)
     * @param netPartie (true si la partie est en réseau)
     * @param choixJoueurB (skin joueurBlanc)
     * @param choixJoueurN (skin joueurNoir)
     */
    Partie(String pseudo, String pseudoAdversaire, int modePartie,
           boolean netPartie, int choixJoueurB, int choixJoueurN)
    {
        initPartie(pseudo, pseudoAdversaire, modePartie, netPartie, choixJoueurB, choixJoueurN);
    }

    /**
     * initPartie
     * initialise la partie et ses attributs
     * @param pseudo (joueur courant)
     * @param pseudoAdversaire (joueur adverse)
     * @param modePartie (1 = partie normale, 2 = partie tour limité, 3 = partie temps limité)
     * @param netPartie (true si la partie est en réseau)
     * @param choixJoueurB (skin joueurBlanc)
     * @param choixJoueurN (skin joueurNoir)
     */
    void initPartie(String pseudo, String pseudoAdversaire, int modePartie,
                            boolean netPartie, int choixJoueurB, int choixJoueurN)
    {
        if (pseudo.equals("anonymous"))
            this.joueurBlanc = new Joueur(true);
        else
            this.joueurBlanc = new Joueur(true, pseudo);

        if (pseudoAdversaire.equals("anonymous"))
            this.joueurNoir = new Joueur(false);
        else
            this.joueurNoir = new Joueur(false, pseudoAdversaire);

        this.choixJoueurBlanc = choixJoueurB;
        this.choixJoueurNoir = choixJoueurN;

        piecesBlanchesPlateau = new ArrayList<>();
        piecesNoiresPlateau = new ArrayList<>();
        cimetiereBlanc = new ArrayList<>();
        cimetiereNoir = new ArrayList<>();

        // On créé le plateau
        board = new Board(this);
        tourBlanc = true;

        // ajout SD
        idCurrentPlayer = 1;

        // choix du mode de la partie
        this.modePartie = modePartie;

        // pour la partie en réseau
        this.endOfTurn = false;

        // Le roi est protégé en début de partie, il n'y a donc pas d'échec
        echecBlanc = false;
        echecNoir = false;

        partieFinie = false;

        historique = new ArrayList<>();
    }

    /**
     * Partie
     * Constructeur de Partie vide en attente de toutes les informations de l'adversaire
     * UNIQUEMENT POUR LES PARTIES EN RESEAU
     *
     */
    Partie(){}

    /**
     * jeSuisBlanc
     * Décide aleatoirement si le joueur qui créer la partie est blanc
     *
     * @return (retourne une valeur au hazard blanc ou noir)
     */
    boolean jeSuisBlanc()
    {
        Random rand = new Random();
        return rand.nextBoolean();
    }

    // ajout SD : après un coup joué, qq soit le mode -> modifier le controller
    // pour appeler cette méthode

    /**
     * CoupFait
     * permet d'enregistrer les mouvements qui ont été fait.
     *
     * @param caseSrc (case de départ du mouvement)
     * @param caseDest (case d'arrivé du mouvement)
     */
    synchronized void coupFait(Case caseSrc, Case caseDest)
    {
        this.caseSrc = caseSrc;
        this.caseDest = caseDest;

    }

    /**
     * finTour
     * reveil le thread à la fin d'un tour
     */
    synchronized void finTour()
    {
        endOfTurn = true;
        notifyAll();
    }


    /**
     * waitFinTour
     * Fin
     */
    synchronized void waitFinTour()
    {

        while (!endOfTurn)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        endOfTurn = false;
    }
    
    /**
     * historiqueCoups
     * met a jours l'arrayList de coups jouées a chaque deplacement
     */
    synchronized void historiqueCoups(Case caseCliquee, Case destination)
    {
        String coup = "";

        //recupere le type de piece
        Piece piece = caseCliquee.getPiece();
        coup += histoTypePiece(piece);
        //recupere la couleur de la piece
        if ( piece.isBlanc() )
            coup += 'b';
        else
            coup += 'n';

        //recupere la case départ
        coup += String.valueOf(caseCliquee.getColumn());
        coup += String.valueOf(caseCliquee.getRow());

        //recupere la case Destination
        coup += String.valueOf(destination.getColumn());
        coup += String.valueOf(destination.getRow());

        //recupere si le deplacement a mangé une piece

        Piece pieceDestination = destination.getPiece();
        if (pieceDestination != null)
        {
            coup += '!';
            coup += histoTypePiece(pieceDestination);

            //recupère la couleur de la pieceDestination
            if (pieceDestination.isBlanc())
                coup += 'b';
            else
                coup += 'n';
        }
        // test pour la promotion
        if ( piece instanceof Pion && ( destination.getRow() == 0  || destination.getRow() == 7 ) )
            coup += '?';

        historique.add(coup);
    }

    /**
     * pieceChoisisPromoSave
     * assiste la construction de l'historique dans le cas d'une promotion, ajoute à un coup quelle pièce a été choisie
     *
     */
    void pieceChoisiePromoSave()
    {
        String coup = historique.get(historique.size()-1);

        if(promote == 1)
            coup += 'c';
        else if(promote == 2)
            coup += 't';
        else if(promote == 3)
            coup += 'f';
        else if(promote == 4)
            coup += 'q';

        historique.remove(historique.size()-1);
        historique.add(coup);
    }

    /**
     * histoTypePiece
     * factorisation d'un bout de code utilisé plusieurs fois
     *
     * @param piece (necessaire pur savoir le type)
     */
    private char histoTypePiece(Piece piece)
    {
        if (piece instanceof Pion)
            return 'p';
        else if (piece instanceof Tour)
            return 't';
        else if (piece instanceof Cavalier)
            return 'c';
        else if (piece instanceof Fou)
            return 'f';
        else if (piece instanceof Reine)
            return 'q';
        else // donc roi
            return 'r';
    }

    /**
     * deleteSave
     * détruit la sauvegarde à la fin d'une partie
     * @param joueurBlanc (nom du joueur blanc)
     * @param joueurNoir (nom du joueur noir)
     */
    synchronized static void deleteSave (String joueurBlanc, String joueurNoir)
    {
        bdd.start();
        bdd.ask("SELECT joueurBlancSave, joueurNoirSave FROM SAUVEGARDE WHERE joueurBlancSave = " +
                "\"" + joueurBlanc + "\" and joueurNoirSave = \"" + joueurNoir + "\" ;");
        bdd.stop();
    }
    /**
     * save
     * envoie l'insert en base de donnée afin de sauvegarder l'état du board
     *
     */
    synchronized boolean save()
    {
        int i, j;
        bdd.start();

        ArrayList<ArrayList<String>> saveDejaExistante = bdd.ask("SELECT SAUVEGARDE.idSauvegarde FROM SAUVEGARDE " +
                "WHERE joueurBlancSave = " + joueurBlanc.getId() +
                " AND joueurNoirSave = " + joueurNoir.getId() + ";");

        if(!saveDejaExistante.isEmpty())
            return false;
        bdd.stop();

        saveHistorique();
        // sauvegarde de l'état du plateau au moment de l'intéruption de la partie
        String listeEmplacementsPieces = "";
        Case[][] plateau = board.getPlateau();
        for(i=0; i<board.getPlateau().length; i++)
        {
            for(j=0; j<board.getPlateau()[i].length; j++)
            {
                if(plateau[i][j].getPiece() != null)
                {
                    if(plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + "" + j + "" + histoTypePiece(plateau[i][j].getPiece()) + "b-";
                    else
                        listeEmplacementsPieces += i + "" + j + "" + histoTypePiece(plateau[i][j].getPiece()) + "n-";
                }
            }
        }
        String requeteIdhistorique = "SELECT HISTORIQUE.idHistorique FROM HISTORIQUE " +
                "WHERE HISTORIQUE.joueurBlancPartie = " + joueurBlanc.getId() +
                " AND HISTORIQUE.joueurNoirPartie = " + joueurNoir.getId() + ";";
        bdd.start();

        ArrayList<ArrayList<String>> resultat = bdd.ask(requeteIdhistorique);
        ArrayList<String> resultat2 = resultat.get(0);
        int idHistorique = Integer.parseInt(resultat2.get(0));

        // On récupère ce qu'il y a dans les cimetières
        int pb=0,tb=0,cb=0,fb=0,rb=0;
        int pn=0,tn=0,cn=0,fn=0,rn=0;

        for(i = 0; i<cimetiereBlanc.size(); i++)
        {
            if(cimetiereBlanc.get(i) instanceof Pion)
                pb++;
            else if(cimetiereBlanc.get(i) instanceof Tour)
                tb++;
            else if(cimetiereBlanc.get(i) instanceof Cavalier)
                cb++;
            else if(cimetiereBlanc.get(i) instanceof Fou)
                fb++;
            else if(cimetiereBlanc.get(i) instanceof Reine)
                rb++;
        }

        for(i = 0; i<cimetiereNoir.size(); i++)
        {
            if(cimetiereNoir.get(i) instanceof Pion)
                pn++;
            else if(cimetiereNoir.get(i) instanceof Tour)
                tn++;
            else if(cimetiereNoir.get(i) instanceof Cavalier)
                cn++;
            else if(cimetiereNoir.get(i) instanceof Fou)
                fn++;
            else if(cimetiereNoir.get(i) instanceof Reine)
                rn++;
        }

        String cimetiereBlancSave = pb + "" + tb + "" + cb + "" + fb + "" + rb;
        String cimetiereNoirSave = pn + "" + tn + "" + cn + "" + fn + "" + rn;

        String requeteSauvegarde = "INSERT INTO SAUVEGARDE VALUES (null, " + joueurBlanc.getId() + ", "
                + joueurNoir.getId() + ", " + tourBlanc + ", '" + listeEmplacementsPieces
                + "', " + idHistorique + ", '" + cimetiereBlancSave + "', '" + cimetiereNoirSave
                + "', " + choixJoueurBlanc + ", " + choixJoueurNoir + ");";

        bdd.edit(requeteSauvegarde);
        bdd.stop();
        return true;
    }

    /**
     * saveHistorique
     * sauvegarde l'historique dans la base de données
     *
     */
    synchronized void saveHistorique()
    {
        bdd.start();
        String coupsJoues = "";
        // sauvegarde de l'historique des coups joués dans la base de donnée
        for (String aHistorique : this.historique)
            coupsJoues += aHistorique + "-";

        String requeteHistorique = "INSERT INTO HISTORIQUE VALUES (null, " + joueurBlanc.getId() + ", "
                + joueurNoir.getId() + ", '" + coupsJoues + "', now());";
        System.out.println(requeteHistorique);
        if (requeteHistorique.length() != 0)
            bdd.edit(requeteHistorique);
        bdd.stop();
    }

    /**
     * isEchec
     * Verifie si après un coup joué le roi adverse est en echec.
     *
     * @return true si echec
     */
    synchronized boolean isEchec()
    {
        Case caseRoi = tourBlanc?
                board.getRoiBlanc().emplacementPiece
                :board.getRoiNoir().emplacementPiece;
        ArrayList<Piece> pieceEnJeu;
        int i;
        if ( !caseRoi.getPiece().blanc )
            pieceEnJeu = piecesBlanchesPlateau;
        else
            pieceEnJeu = piecesNoiresPlateau;

        for (i = 0; i < pieceEnJeu.size(); i++)
            if (pieceEnJeu.get(i).peutAtteindreRoi(caseRoi))
                return true;
        return false;
    }

    /**
     * isEchecEtMat
     * Teste si le roi est en echec et si un déplacement est possible
     * (sera controler au préalable isEchec(Case caseRoi) dans le model
     *
     * @return true si echec et mat
     */
    synchronized boolean isEchecEtMat()
    {
        isEchec();
        ArrayList<Piece> pieceEnJeu;
        if(tourBlanc)
            pieceEnJeu = piecesBlanchesPlateau;
        else
            pieceEnJeu = piecesNoiresPlateau;

        for (Piece aPieceEnJeu : pieceEnJeu)
            if (!aPieceEnJeu.casesAtteignables.isEmpty())
                return false;
        return true;
    }

    /**
     * isPat
     * La partie retourne true si :
     * 1) le joueur dont c'est le tour ne peut rien jouer sans mettre le roi en échec
     * 2) s'il reste sur le plateau les deux roi
     * 3) s'il reste sur le plateau les deux roi et un cavalier
     *
     * @return (true if is pat)
     */
    synchronized boolean isPat()
    {
        int i;
        // s'il ne reste que deux rois
        if(piecesBlanchesPlateau.size() == 1 && piecesNoiresPlateau.size() == 1)
            return true;
        // Utilisation de variables locales pour les tests suivants
        ArrayList<Piece> piecesJoueurEnCours;
        ArrayList<Piece> piecesAutreJoueur;
        if(tourBlanc)
        {
            piecesJoueurEnCours = piecesBlanchesPlateau;
            piecesAutreJoueur = piecesNoiresPlateau;
        }
        else
        {
            piecesJoueurEnCours = piecesNoiresPlateau;
            piecesAutreJoueur = piecesBlanchesPlateau;
        }
        // S'il reste les deux roi plus un cavalier pour la pièce en cours...
        if(piecesJoueurEnCours.size()== 2
                && piecesJoueurEnCours.get(1) instanceof Cavalier
                && piecesAutreJoueur.size() == 1)
                return true;
        // ... ou pour le joueur adverse (faisable en un seul test mais plus lisible comme ça)
        else if(piecesAutreJoueur.size()== 2
                && piecesAutreJoueur.get(1) instanceof Cavalier
                && piecesJoueurEnCours.size() == 1)
                return true;
        // si aucun déplacement n'est possible et que le roi n'est pas en échec
        for (i = 0; i < piecesJoueurEnCours.size(); i++)
            if (isEchec() || !piecesJoueurEnCours.get(i).casesAtteignables.isEmpty())
                return false;
        return true;
    }


    /**
     * undo
     * Annule le dernier coup joué. Ne fonctionne que si au tour de l'adversaire de jouer.
     *
     */
    synchronized boolean undo()
    {
        if(historique.size()-1 < 0)
            return false;
        String dernierCoup = historique.get(historique.size()-1);
        String[] tabCoupDecoupe = dernierCoup.split("");
        boolean isBlanc = tabCoupDecoupe[1].equals("b");
        int columnDepart = Integer.parseInt(tabCoupDecoupe[2]);
        int rowDepart = Integer.parseInt(tabCoupDecoupe[3]);
        int columnArrivee = Integer.parseInt(tabCoupDecoupe[4]);
        int rowArrivee = Integer.parseInt(tabCoupDecoupe[5]);

        // Recupération de la pièce qui a été bougée au dernière tour
        Piece pieceBougee = board.getPlateau()[rowArrivee][columnArrivee].getPiece();
        board.getPlateau()[rowArrivee][columnArrivee].setPiece(null);
        board.getPlateau()[rowDepart][columnDepart].setPiece(pieceBougee);
        pieceBougee.setEmplacementPiece(board.getPlateau()[rowDepart][columnDepart]);


        // veut dire qu'une piece est mangée
        if(tabCoupDecoupe.length > 7 && tabCoupDecoupe[6].equals("!"))
        {
            Piece pieceMangee;
            if(isBlanc)
            {
                pieceMangee = cimetiereNoir.get(cimetiereNoir.size()-1);
                cimetiereNoir.remove(cimetiereNoir.size()-1);
                piecesNoiresPlateau.add(pieceMangee);
            }
            else
            {
                pieceMangee = cimetiereBlanc.get(cimetiereBlanc.size()-1);
                cimetiereBlanc.remove(getCimetiereBlanc().size()-1);
                piecesBlanchesPlateau.add(pieceMangee);
            }
            board.getPlateau()[rowArrivee][columnArrivee].setPiece(pieceMangee);
            pieceMangee.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee]);
        }
        boolean deplacementsTour = false;
        if(pieceBougee instanceof Tour)
        {
            if(isBlanc)
            {
                for (int i = 0; i < historique.size() - 1; i++)
                    if (historique.get(i).split("")[0].equals("t") && historique.get(i).split("")[1].equals("b"))
                    {
                        deplacementsTour = true;
                        break;
                    }
                if (!deplacementsTour)
                {
                    ((Roi)board.getRoiBlanc()).setPetitRoque(true);
                    ((Roi)board.getRoiBlanc()).setGrandRoque(true);
                }
            }
            else
            {
                for (int i = 0; i < historique.size() - 1; i++)   // historique.size()-1  --> pour ne pas prendre en compte la ligne que l'on veut annuler
                    if (historique.get(i).split("")[0].equals("t") && historique.get(i).split("")[1].equals("n"))
                    {
                        deplacementsTour = true;
                        break;
                    }
                if (!deplacementsTour)
                {
                    ((Roi)board.getRoiNoir()).setPetitRoque(true);
                    ((Roi)board.getRoiNoir()).setGrandRoque(true);
                }
            }
        }

        // Si un roque a eu lieu
        int diff = Math.abs(Character.getNumericValue(dernierCoup.charAt(2)) - Character.getNumericValue(dernierCoup.charAt(4)));
        boolean deplacementsRoi = false;
        if(pieceBougee instanceof Roi)
        {
            if(isBlanc)
            {
                for (int i = 0; i < historique.size() - 1; i++)   // historique.size()-1  --> pour ne pas prendre en compte la ligne que l'on veut annuler
                    if (historique.get(i).split("")[0].equals("r") && historique.get(i).split("")[1].equals("b"))
                    {
                        deplacementsRoi = true;
                        break;
                    }
            }
            else
            {
                for (int i = 0; i < historique.size() - 1; i++)   // historique.size()-1  --> pour ne pas prendre en compte la ligne que l'on veut annuler
                    if (historique.get(i).split("")[0].equals("r") && historique.get(i).split("")[1].equals("n"))
                    {
                        deplacementsRoi = true;
                        break;
                    }
            }

            if(!deplacementsRoi)
            {
                ((Roi)pieceBougee).setPetitRoque(true);
                ((Roi)pieceBougee).setGrandRoque(true);
            }
            if(diff == 3)
            {
                Piece tourGrandRoque = board.getPlateau()[rowArrivee][columnArrivee+1].getPiece();
                tourGrandRoque.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee-1]);
                board.getPlateau()[rowArrivee][columnArrivee+1].setPiece(null);
                board.getPlateau()[rowArrivee][columnArrivee-1].setPiece(tourGrandRoque);
                ((Roi) pieceBougee).setGrandRoque(true);
                ((Roi) pieceBougee).setPetitRoque(true);
            }
            else if(diff == 2)
            {
                Piece tourPetitRoque = board.getPlateau()[rowArrivee][columnArrivee - 1].getPiece();
                tourPetitRoque.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee + 1]);
                board.getPlateau()[rowArrivee][columnArrivee - 1].setPiece(null);
                board.getPlateau()[rowArrivee][columnArrivee + 1].setPiece(tourPetitRoque);
                ((Roi) pieceBougee).setPetitRoque(true);
                ((Roi) pieceBougee).setGrandRoque(true);
            }
        }

        // En cas de promotion
        if(tabCoupDecoupe.length  == 8 || tabCoupDecoupe.length == 11)
        {
            pieceBougee.emplacementPiece.setPiece(null);
            pieceBougee.emplacementPiece.setPiece(new Pion(pieceBougee.emplacementPiece, pieceBougee.blanc));
            if (pieceBougee.blanc)
            {
                piecesBlanchesPlateau.remove(pieceBougee);
                piecesBlanchesPlateau.add(pieceBougee.emplacementPiece.getPiece());
                cimetiereBlanc.remove(cimetiereBlanc.size()-1);
            }
            else
            {
                piecesNoiresPlateau.remove(pieceBougee);
                piecesNoiresPlateau.add(pieceBougee.emplacementPiece.getPiece());
                cimetiereNoir.remove(cimetiereNoir.size()-1);
            }
        }
        tourBlanc = !tourBlanc;
        historique.remove(historique.size()-1);
        board.majCasesAtteignable();
        return true;
    }

    /**
     * undoHisto
     * même algo que pour undo mais pour l'affichage de l'historique
     *
     * @param coupAAnnuler (coup ... à annuler)
     */
    synchronized void undoHisto(String coupAAnnuler)
    {
        String[] tabCoupDecoupe = coupAAnnuler.split("");
        boolean isBlanc = tabCoupDecoupe[1].equals("b");

        int columnDepart = Integer.parseInt(tabCoupDecoupe[2]);
        int rowDepart = Integer.parseInt(tabCoupDecoupe[3]);
        int columnArrivee = Integer.parseInt(tabCoupDecoupe[4]);
        int rowArrivee = Integer.parseInt(tabCoupDecoupe[5]);

        // Recupération de la pièce qui a été bougée au dernière tour
        Piece pieceBougee = board.getPlateau()[rowArrivee][columnArrivee].getPiece();
        board.getPlateau()[rowArrivee][columnArrivee].setPiece(null);
        board.getPlateau()[rowDepart][columnDepart].setPiece(pieceBougee);
        pieceBougee.setEmplacementPiece(board.getPlateau()[rowDepart][columnDepart]);
        // veut dire qu'une piece est mangée
        if (tabCoupDecoupe.length > 7)
        {
            Piece pieceMangee;
            if (isBlanc)
            {
                pieceMangee = cimetiereNoir.get(cimetiereNoir.size() - 1);
                cimetiereNoir.remove(cimetiereNoir.size() - 1);
                piecesNoiresPlateau.add(pieceMangee);
            }
            else
            {
                pieceMangee = cimetiereBlanc.get(cimetiereBlanc.size() - 1);
                cimetiereBlanc.remove(getCimetiereBlanc().size() - 1);
                piecesBlanchesPlateau.add(pieceMangee);
            }
            board.getPlateau()[rowArrivee][columnArrivee].setPiece(pieceMangee);
            pieceMangee.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee]);
        }
        // Si c'est une tour qui a été déplacée on autorise de nouveau le roque si c'est son premier déplacement
        boolean deplacementsTour = false;
        if (pieceBougee instanceof Tour)
        {
            if (isBlanc)
            {
                for (int i = 0; i < historique.size() - 1; i++)   // historique.size()-1  --> pour ne pas prendre en compte la ligne que l'on veut annuler
                    if (historique.get(i).split("")[0].equals("t") && historique.get(i).split("")[1].equals("b"))
                    {
                        deplacementsTour = true;
                        break;
                    }
                if (!deplacementsTour)
                {
                    ((Roi) board.getRoiBlanc()).setPetitRoque(true);
                    ((Roi) board.getRoiBlanc()).setGrandRoque(true);
                }
            }
            else
            {
                for (int i = 0; i < historique.size() - 1; i++)   // historique.size()-1  --> pour ne pas prendre en compte la ligne que l'on veut annuler
                    if (historique.get(i).split("")[0].equals("t") && historique.get(i).split("")[1].equals("n"))
                    {
                        deplacementsTour = true;
                        break;
                    }
                if (!deplacementsTour)
                {
                    ((Roi) board.getRoiNoir()).setPetitRoque(true);
                    ((Roi) board.getRoiNoir()).setGrandRoque(true);
                }
            }
        }
        // Si un roque a eu lieu
        int diff = Math.abs(Character.getNumericValue(coupAAnnuler.charAt(2)) - Character.getNumericValue(coupAAnnuler.charAt(4)));
        boolean deplacementsRoi = false;
        if (pieceBougee instanceof Roi)
        {
            if (isBlanc)
            {
                for (int i = 0; i < historique.size() - 1; i++)
                    if (historique.get(i).split("")[0].equals("r") && historique.get(i).split("")[1].equals("b"))
                    {
                        deplacementsRoi = true;
                        break;
                    }
            }
            else
            {
                for (int i = 0; i < historique.size() - 1; i++)
                    if (historique.get(i).split("")[0].equals("r") && historique.get(i).split("")[1].equals("n"))
                    {
                        deplacementsRoi = true;
                        break;
                    }
            }

            if (!deplacementsRoi)
            {
                ((Roi) pieceBougee).setPetitRoque(true);
                ((Roi) pieceBougee).setGrandRoque(true);
            }

            if (diff == 3)
            {
                Piece tourGrandRoque = board.getPlateau()[rowArrivee][columnArrivee + 1].getPiece();
                tourGrandRoque.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee - 1]);
                board.getPlateau()[rowArrivee][columnArrivee + 1].setPiece(null);
                board.getPlateau()[rowArrivee][columnArrivee - 1].setPiece(tourGrandRoque);
                ((Roi) pieceBougee).setGrandRoque(true);
                ((Roi) pieceBougee).setPetitRoque(true);
            }
            if (diff == 2)
            {
                Piece tourPetitRoque = board.getPlateau()[rowArrivee][columnArrivee - 1].getPiece();
                tourPetitRoque.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee + 1]);
                board.getPlateau()[rowArrivee][columnArrivee - 1].setPiece(null);
                board.getPlateau()[rowArrivee][columnArrivee + 1].setPiece(tourPetitRoque);
                ((Roi) pieceBougee).setPetitRoque(true);
                ((Roi) pieceBougee).setGrandRoque(true);
            }
        }

        if (tabCoupDecoupe.length == 8 || tabCoupDecoupe.length == 11) {
            pieceBougee.emplacementPiece.setPiece(null);
            pieceBougee.emplacementPiece.setPiece(new Pion(pieceBougee.emplacementPiece, pieceBougee.blanc));
            if (pieceBougee.blanc)
            {
                piecesBlanchesPlateau.remove(piecesBlanchesPlateau.size() - 1);
                piecesBlanchesPlateau.add(pieceBougee.emplacementPiece.getPiece());
                cimetiereBlanc.remove(cimetiereBlanc.size() - 1);
            } else
            {
                piecesNoiresPlateau.remove(piecesNoiresPlateau.size() - 1);
                piecesNoiresPlateau.add(pieceBougee.emplacementPiece.getPiece());
                cimetiereNoir.remove(cimetiereNoir.size() - 1);
            }
        }
        if((tabCoupDecoupe.length > 6 && tabCoupDecoupe[6].equals("?")) || (tabCoupDecoupe.length > 9 && tabCoupDecoupe[9].equals("?")))
        {
            Pion pion = new Pion(board.getPlateau()[rowDepart][columnArrivee], isBlanc);
            if(isBlanc)
            {
                piecesBlanchesPlateau.add(pion);
                cimetiereBlanc.remove(cimetiereBlanc.size() - 1);
            }
            else
            {
                piecesNoiresPlateau.add(pion);
                cimetiereNoir.remove(cimetiereNoir.size() - 1);
            }
            board.getPlateau()[rowDepart][columnDepart].setPiece(null);
            board.getPlateau()[rowDepart][columnDepart].setPiece(pion);
        }

        tourBlanc = !tourBlanc;
    }

    // getters / setters
    synchronized Joueur getJoueurBlanc() { return joueurBlanc; }
    synchronized Joueur getJoueurNoir() { return joueurNoir; }
    synchronized Board getBoard() { return board; }
    synchronized boolean isTourBlanc() { return tourBlanc; }
    synchronized void setTourBlanc(boolean tourBlanc)
    {
        this.tourBlanc = tourBlanc;
        // ajout SD
        if (tourBlanc) idCurrentPlayer = 1;
        else idCurrentPlayer = 2;
    }
    synchronized ArrayList<Piece> getCimetiereBlanc() { return cimetiereBlanc; }
    synchronized ArrayList<Piece> getCimetiereNoir() { return cimetiereNoir; }
    synchronized ArrayList<Piece> getPiecesBlanchesPlateau() { return piecesBlanchesPlateau; }
    synchronized ArrayList<Piece> getPiecesNoiresPlateau() { return piecesNoiresPlateau; }
    synchronized int getModePartie() { return modePartie; }
    synchronized int getChoixJoueurBlanc() { return choixJoueurBlanc; }
    synchronized int getChoixJoueurNoir() { return choixJoueurNoir; }
    synchronized ArrayList<String> getHistorique() { return historique; }
    synchronized boolean isPartieFinie() { return partieFinie; }
    synchronized void setPartieFinie(boolean partieFinie) { this.partieFinie = partieFinie; }
    synchronized int getIdCurrentPlayer() { return idCurrentPlayer; }
    synchronized Case getCaseSrc() { return caseSrc; }
    synchronized Case getCaseDest() { return caseDest; }
    synchronized void setPromote(int promote) { this.promote = promote; }
    synchronized int getPromote() { return promote; }
    synchronized void setCaseDest(Case caseDest) { this.caseDest = caseDest; }
    ArrayList<Case> getCasesAtteignables() { return casesAtteignables; }
    void setCasesAtteignables(ArrayList<Case> casesAtteignables) { this.casesAtteignables = casesAtteignables; }
    Case getCaseMemoire() { return caseMemoire; }
    void setCaseMemoire(Case caseMemoire) { this.caseMemoire = caseMemoire; }
}