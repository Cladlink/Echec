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

    private Board board;
    private boolean tourBlanc;
    // ajout SD : plus partique de gérer avec un id que un boolean
    private int idCurrentPlayer; // = 1 si joueur courant = blanc, = 2 sinon
    

    private int modePartie; // 0 = partie sans temps ; 1 = temps partie limitée; 2 = temps tour limités
    private boolean netPartie;
    // ajout SD
    private boolean endOfTurn; // pour signaler la fin d'un tour (mode réseau)
    private Case caseSrc; // pour conserver quel coup vient d'être joueur -> envoi par réseau
    private Case caseDest; // pour conserver quel coup vient d'être joueur -> envoi par réseau

    // ajout SD : pour plus de lisibilité avec les modes -> changer les valeurs en dur dans le code
    final static int MODE_NOTIMER = 1;
    final static int MODE_TIMERPARTY = 2;
    final static int MODE_TIMERTURN = 3;

    // ajout SD : pour gérer la partie avec chrono limite pour chaque joueur
    private long chronoJoueurBlanc; // temps total alloué en ms au joueur 1 (=blanc) (mode TIMERPARTY)
    private long chronoJoueurNoir; // temps total alloué en ms au joueur 2 (=noir) (mode TIMERPARTY)
    
    private boolean echecBlanc;
    private boolean echecNoir;

    private boolean partieFinie;

    private ArrayList<String> historique;


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
	if (tourBlanc) idCurrentPlayer = 1;
	else idCurrentPlayer = 2;

        // choix du mode de la partie
        this.modePartie = 1;

        // pour la partie en réseau
        this.netPartie = false;

        // Le roi est protégé en début de partie, il n'y a donc pas d'échec
        echecBlanc = false;
        echecNoir = false;

        partieFinie = false;

        this.historique = historique;
    }

    /**
     * Partie Constructeur
     *
     * @param joueurBlanc j1
     * @param joueurNoir j2
     */
    Partie(Joueur joueurBlanc, Joueur joueurNoir, int modePartie, boolean netPartie, int choixJoueurB, int choixJoueurN)
    {
        //On ajoute les deux joueurs à la partie
        this.joueurBlanc = joueurBlanc;
        this.joueurNoir = joueurNoir;

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

	// ajout SD : init chrono : par défaut 15 minutes -> à changer pour le mettre en construction
	if (modePartie == MODE_TIMERPARTY) {
            chronoJoueurBlanc = 900000;
            chronoJoueurNoir = 90000;
	}
	
        // pour la partie en réseau
        this.netPartie = netPartie;
	this.endOfTurn = false;

        // Le roi est protégé en début de partie, il n'y a donc pas d'échec
        echecBlanc = false;
        echecNoir = false;

        partieFinie = false;

        historique = new ArrayList<>();
    }

    // todo
    synchronized void tourLimite()
    {
	// ajout SD : mettre tm en attribut sinon inaccessible par d'autres méthodes
        Timer tm = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                finDeJeuTemps();
            }
        };
        tm.schedule(tt, 30000);

    }

    // todo
    synchronized void tempsLimite()
    {
	// utilité de cette méthode ??
    }

    // todo
    private synchronized void finDeJeuTemps()
    {
	// ajout SD : pas besoin de tout ça : juste mettre partieFinie à true.
	
        if (tourBlanc)
        {
           // finir la partie en défaveur des blancs
        }
        else
        {
            // finir la partie en défaveut des noirs
        }
    }

    // ajout SD : après un coup joué, qq soit le mode -> modifier le controller
    // pour appeler cette méthode
    synchronized void coupFait(Case caseSrc, Case caseDest) {

	/* TO DO:
	   - mettre à jour caseSrc et caseDest,
	   - si mode temps par tour : annuler le timer tm
	   - sinon si mode temps partie : 
	      - cf. rq ci-dessous
	      - si chrono joueur courant <=0 partieFinie = true

	      Rq sur les chronos : soit la Vue du chrono se base sur chronoJoueurBlanc/Noir pour faire son
	      affichage, soit elle se base sur un autre attribut. Dans le premier cas, on peut avoir un Timer (swing)
	      qui a accès à chronoJoueurBlanc/Noir et qui le décrémente de 1000 toutes les secondes avant de faire
	      un repaint() sur l'emplacement visuel du chrono. Dans le deuxième cas, le Timer swing décremente un 
	      autre attribut et quand le coup est joué, le chrono s'arrête et on prend sa valeur pour la retrancher
	      de chronoJoueurBlanc/Noir.
	 */
    }

    // ajout SD : à la fin du tour courant : débloquer le thread courant pour qu'il
    // envoi à l'autre quel coup a été joué
    synchronized void finTour() {

	endOfTurn = true;
	notifyAll();
    }
    
    // ajout SD : attente fin de tour pour le thread du joueur courant et partie réseau
    // uniquement
    public synchronized void waitFinTour() {

        while (!endOfTurn) {
            try {
                wait();
            }
            catch (InterruptedException e) {}
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
            coup+='?';

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
     *
     *
     * @param joueurBlanc
     * @param joueurNoir
     */
    synchronized static void deleteSave (String joueurBlanc, String joueurNoir)
    {
        bdd.start();
        bdd.ask("SELECT joueurBlancSave, joueurNoirSave FROM SAUVEGARDE WHERE joueurBlancSave = " +
                "\"" + joueurBlanc + "\" and joueurNoirSave = \"" + joueurNoir + "\" ;");
        bdd.stop();
    }
    /**
     * save todo
     * envoie l'insert en base de donnée afin de sauvegarder l'état du board
     *
     */
    synchronized boolean save()
    {
        bdd.start();
        int i, j;

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

    synchronized void saveHistorique()
    {
        String coupsJoues = "";
        // sauvegarde de l'historique des coups joués dans la base de donnée
        for(int i=0; i<this.historique.size(); i++)
        {
            coupsJoues += this.historique.get(i) + "-";
        }

        String requeteHistorique = "INSERT INTO HISTORIQUE VALUES (null, " + joueurBlanc.getId() + ", "
                + joueurNoir.getId() + ", '" + coupsJoues + "');";
        bdd.edit(requeteHistorique);  // todo: mon probleme c'etait que cette ligne était a la base tout en bas ce qui fait
        // (todo) que la requete n'était pas encore dans la BDD quand on faisait le select
    }
    
    

    /**
     * attenteAction
     * comportent d'attente qu'une pièce soit jouer quelque soit le tour.
     *
     */
    synchronized void attenteAction()
    {

    }

    /**
     * attenteDebutPartie
     * comportement d'attente du début de la partie
     *
     */
    synchronized void attenteDebutPartie()
    {

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

        for (int i = 0; i < pieceEnJeu.size(); i++)
            if (!pieceEnJeu.get(i).casesAtteignables.isEmpty())
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
    synchronized void undo()
    {
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
        if(tabCoupDecoupe.length > 7)
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

        // Si un roque a eu lieu
        int diff = Math.abs(Character.getNumericValue(dernierCoup.charAt(2)) - Character.getNumericValue(dernierCoup.charAt(4)));
        boolean deplacementsRoi = false;
        for(String aHistorique : historique)
            if (aHistorique.split("")[0].equals("r")) {
                deplacementsRoi = true;
                break;
            }

        if(pieceBougee instanceof Roi)
        {
            if(diff == 3)
            {
                Piece tourGrandRoque = board.getPlateau()[rowArrivee][columnArrivee+1].getPiece();
                tourGrandRoque.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee-1]);
                board.getPlateau()[rowArrivee][columnArrivee+1].setPiece(null);
                board.getPlateau()[rowArrivee][columnArrivee-1].setPiece(tourGrandRoque);
                ((Roi) pieceBougee).setGrandRoque(true);
                ((Roi) pieceBougee).setPetitRoque(true);
            }
            if(diff == 2)
            {
                Piece tourPetitRoque = board.getPlateau()[rowArrivee][columnArrivee-1].getPiece();
                tourPetitRoque.setEmplacementPiece(board.getPlateau()[rowArrivee][columnArrivee+1]);
                board.getPlateau()[rowArrivee][columnArrivee-1].setPiece(null);
                board.getPlateau()[rowArrivee][columnArrivee+1].setPiece(tourPetitRoque);
                ((Roi) pieceBougee).setPetitRoque(true);
                ((Roi) pieceBougee).setGrandRoque(true);
            }
            else if()
        }

        if(tabCoupDecoupe.length  == 7 || tabCoupDecoupe.length == 10)
        {
            pieceBougee.emplacementPiece.setPiece(null);
            pieceBougee.emplacementPiece.setPiece(
                    new Pion(pieceBougee.emplacementPiece, pieceBougee.blanc));
            if (pieceBougee.blanc)
            {
                piecesBlanchesPlateau.remove(piecesBlanchesPlateau.size()-1);
                piecesBlanchesPlateau.add(pieceBougee.emplacementPiece.getPiece());
                cimetiereBlanc.remove(cimetiereBlanc.size()-1);
            }
            else
            {
                piecesNoiresPlateau.remove(piecesNoiresPlateau.size()-1);
                piecesNoiresPlateau.add(pieceBougee.emplacementPiece.getPiece());
                cimetiereNoir.remove(cimetiereNoir.size()-1);
            }
        }
        tourBlanc = !tourBlanc;
        historique.remove(historique.size()-1);
        board.majCasesAtteignable();

    }

    /**
     * requeteHistorique
     *
     * @return
     */
    synchronized ArrayList<ArrayList<ArrayList<String>>> requeteHistorique()
    {
        String requete = "SELECT JOUEUR.pseudoJoueur," +
                "HISTORIQUE.datePartie" +
                " FROM HISTORIQUE" +
                " JOIN JOUEUR" +
                " ON HISTORIQUE.joueurBlancPartie = JOUEUR.idJoueur"+
                " WHERE JOUEUR.idJoueur = HISTORIQUE.joueurBlancPartie;";

        bdd.start();
        ArrayList<ArrayList<String>> nomJoueurBlancEtDate = bdd.ask(requete);

        requete = "SELECT JOUEUR.pseudoJoueur," +
                "HISTORIQUE.datePartie" +
                " FROM HISTORIQUE" +
                " JOIN JOUEUR" +
                " ON HISTORIQUE.joueurNoirPartie = JOUEUR.idJoueur"+
                " WHERE JOUEUR.idJoueur = HISTORIQUE.joueurNoirPartie;";
        ArrayList<ArrayList<String>> nomJoueurNoir = bdd.ask(requete);
        bdd.stop();

        ArrayList<ArrayList<ArrayList<String>>> historiqueRecup = new ArrayList<>();
        historiqueRecup.add(nomJoueurBlancEtDate);
        historiqueRecup.add(nomJoueurNoir);

        return historiqueRecup;
    }

    /**
     * requeteCoupsHistorique
     *
     * @param id
     * @return
     */
    ArrayList<ArrayList<String>> requeteCoupsHistorique(int id)
    {
        //on recupere l'historique de la partie
        String requete = "SELECT HISTORIQUE.coupsJouee, HISTORIQUE.idHistorique"+
                " FROM HISTORIQUE" +
                " WHERE idHistorique = "+id+";";
        bdd.start();
        ArrayList<ArrayList<String>> historiqueCoupRecup = bdd.ask(requete);
        bdd.stop();

        return historiqueCoupRecup;
    }

    // getters / setters
    synchronized Joueur getJoueurBlanc() {
        return joueurBlanc;
    }
    synchronized void setJoueurBlanc(Joueur joueurBlanc) {
        this.joueurBlanc = joueurBlanc;
    }
    synchronized Joueur getJoueurNoir() {
        return joueurNoir;
    }
    synchronized void setJoueurNoir(Joueur joueurNoir) {
        this.joueurNoir = joueurNoir;
    }
    synchronized Board getBoard() {
        return board;
    }
    synchronized void setBoard(Board board) {
        this.board = board;
    }
    synchronized boolean isTourBlanc() {
        return tourBlanc;
    }
    synchronized void setTourBlanc(boolean tourBlanc) {
        this.tourBlanc = tourBlanc;
	// ajout SD
	if (tourBlanc) idCurrentPlayer = 1;
	else idCurrentPlayer = 2;
    }
    synchronized ArrayList<Piece> getCimetiereBlanc() {
        return cimetiereBlanc;
    }
    synchronized void setCimetiereBlanc(ArrayList<Piece> cimetiereBlanc) {
        this.cimetiereBlanc = cimetiereBlanc;
    }
    synchronized ArrayList<Piece> getCimetiereNoir() {
        return cimetiereNoir;
    }
    synchronized void setCimetiereNoir(ArrayList<Piece> cimetiereNoir) {
        this.cimetiereNoir = cimetiereNoir;
    }
    synchronized ArrayList<Piece> getPiecesBlanchesPlateau() {
        return piecesBlanchesPlateau;
    }
    synchronized void setPiecesBlanchesPlateau(ArrayList<Piece> piecesBlanchesPlateau) {
        this.piecesBlanchesPlateau = piecesBlanchesPlateau;
    }
    synchronized ArrayList<Piece> getPiecesNoiresPlateau() {
        return piecesNoiresPlateau;
    }
    synchronized void setPiecesNoiresPlateau(ArrayList<Piece> piecesNoiresPlateau) {
        this.piecesNoiresPlateau = piecesNoiresPlateau;
    }
    synchronized int getModePartie() {
        return modePartie;
    }
    synchronized void setModePartie(int modePartie) {
        this.modePartie = modePartie;
    }
    synchronized boolean isNetPartie() {
        return netPartie;
    }
    synchronized void setNetPartie(boolean netPartie) {
        this.netPartie = netPartie;
    }
    synchronized boolean isEchecBlanc() {
        return echecBlanc;
    }
    synchronized void setEchecBlanc(boolean echecBlanc) {
        this.echecBlanc = echecBlanc;
    }
    synchronized boolean isEchecNoir() {
        return echecNoir;
    }
    synchronized void setEchecNoir(boolean echecNoir) {
        this.echecNoir = echecNoir;
    }
    public BDDManager getBdd() {
        return bdd;
    }
    public int getChoixJoueurBlanc() {
        return choixJoueurBlanc;
    }
    public void setChoixJoueurBlanc(int choixJoueurBlanc) {
        this.choixJoueurBlanc = choixJoueurBlanc;
    }
    public int getChoixJoueurNoir() {
        return choixJoueurNoir;
    }
    public void setChoixJoueurNoir(int choixJoueurNoir) {
        this.choixJoueurNoir = choixJoueurNoir;
    }
    public boolean isPartieFinie() {
        return partieFinie;
    }
    public void setPartieFinie(boolean partieFinie) {
        this.partieFinie = partieFinie;
    }
    public ArrayList<String> getHistorique() {
        return historique;
    }
    public void setHistorique(ArrayList<String> historique) {
        this.historique = historique;
    }
}
