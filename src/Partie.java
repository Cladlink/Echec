    import java.text.SimpleDateFormat;
    import java.util.*;

    /**
        Created by cladlink on 06/04/16.
    */
class Partie
{
    private final BDDManager bdd = new BDDManager();

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

    private int modePartie; // 0 = partie sans temps ; 1 = temps partie limitée; 2 = temps tour limités
    private boolean netPartie;

    private boolean echecBlanc;
    private boolean echecNoir;

    private boolean partieFinie;

    private ArrayList<String> historique;

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

        // choix du mode de la partie
        this.modePartie = modePartie;

        // pour la partie en réseau
        this.netPartie = netPartie;

        // Le roi est protégé en début de partie, il n'y a donc pas d'échec
        echecBlanc = false;
        echecNoir = false;

        partieFinie = false;

        historique = new ArrayList<>();
        System.out.println(modePartie + " " + netPartie + " ");
    }
    // todo
    synchronized void tourLimite()
    {
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

    }

    // todo
    private synchronized void finDeJeuTemps()
    {
        if (tourBlanc)
        {
           // finir la partie en défaveur des blancs
        }
        else
        {
            // finir la partie en défaveut des noirs
        }
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
        System.out.println(historique);
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
     * save todo
     * envoie l'insert en base de donnée afin de sauvegarder l'état du board
     *
     */
    synchronized boolean save()
    {
        bdd.start();

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        String dateActuelle = year + "-" + month + "-" + day;

        String coupsJoues = "";
        int i, j;
        // sauvegarde de l'historique des coups joués dans la base de donnée
        for(i=0; i<this.historique.size(); i++)
        {
            coupsJoues += this.historique.get(i) + "-";
        }

        String verifHistorique = "SELECT HISTORIQUE.idHistorique FROM HISTORIQUE " +
                "WHERE HISTORIQUE.joueurBlancPartie = " + joueurBlanc.getId() +
                " AND HISTORIQUE.joueurNoirPartie = " + joueurNoir.getId() + ";";
        ArrayList<ArrayList<String>> existeDeja = bdd.ask(verifHistorique);

        if(!existeDeja.isEmpty())
        {
            return false;
        }

        String requeteHistorique = "INSERT INTO HISTORIQUE VALUES (null, " + joueurBlanc.getId() + ", "
                + joueurNoir.getId()
                + ", '" + dateActuelle + "', '" + coupsJoues + "');";
        bdd.edit(requeteHistorique);  // todo: mon probleme c'etait que cette ligne était a la base tout en bas ce qui fait
        // (todo) que la requete n'était pas encore dans la BDD quand on faisait le select

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

        String requeteSauvegarde = "INSERT INTO SAUVEGARDE VALUES (null, " + joueurBlanc.getId() + ", "
                + joueurNoir.getId() + ", '" + dateActuelle + "', " + tourBlanc + ", '" + listeEmplacementsPieces
                + "', " + idHistorique + ");";

        bdd.edit(requeteSauvegarde);
        bdd.stop();
        return true;
    }

    /**
     * load todo
     * recoit un etat du board et redémarre la partie
     *
     */
    synchronized void load()
    {
        //on recupere l'historique de la partie
       String requete = "SELECT HISTORIQUE.joueurNoirPartie, HISTORIQUE.datePartie," +
                " HISTORIQUE.coupsJouee, SAUVEGARDE.tourSave, SAUVEGARDE.etatPlateauSave" +
                " FROM HISTORIQUE JOIN SAUVEGARDE ON" +
                " HISTORIQUE.idHistorique = SAUVEGARDE.idHistorique" +
                " WHERE HISTORIQUE.idHistorique = "+joueurBlanc.getId()+";";
        bdd.start();
        ArrayList<ArrayList<String>> historiqueRecup = bdd.ask(requete);
        bdd.stop();

        //load joueur
        joueurNoir.setId(Integer.getInteger(historiqueRecup.get(0).get(0)));
        //load temps //todo : ne sais pas dans quelle variable mettre le temps
        int temps = Integer.getInteger(historiqueRecup.get(1).get(0));

        String coupsJoues = "";
        this.historique.clear();
        int i;
        for(i=0; i<historiqueRecup.get(2).size(); i++)
        {
            this.historique.add(historiqueRecup.get(2).get(i));
        }

        //--- Puis on charge la sauvegarde
        //tourSave
        if (Boolean.valueOf(historiqueRecup.get(3).get(0))) {
            this.tourBlanc = true;
        }

        //etatPlateauSave
        String etatPlateau = "";
        for (i = 0; i < historiqueRecup.get(4).size(); i++) {
            etatPlateau += historiqueRecup.get(4).get(i);
        }

        //split
        String[] etatPlateauTab = etatPlateau.split("-");

        for (i=0; i<etatPlateauTab.length;i++) {
            //blanc ?
            boolean estBlanc;
            estBlanc = etatPlateau.charAt(1) == 'b';

            //coordonne
            int abscise = (int) etatPlateau.charAt(2);
            int ordonnee = (int) etatPlateau.charAt(3);

            Case casePiece = board.getPlateau()[abscise][ordonnee];

            //piece
            Piece piece;
            if (etatPlateau.charAt(0)=='p') {
                piece = new Pion(casePiece, estBlanc);
            }
            else if (etatPlateau.charAt(0)=='t') {
                piece = new Tour(casePiece, estBlanc);
            }
            else if (etatPlateau.charAt(0)=='f') {
                piece = new Fou(casePiece, estBlanc);
            }
            else if (etatPlateau.charAt(0)=='c') {
                piece = new Cavalier(casePiece, estBlanc);
            }
            else if (etatPlateau.charAt(0)=='r') {
                piece = new Roi(casePiece, estBlanc);
            }
            else {
                piece = new Reine(casePiece, estBlanc);
            }

            //et on l'ajoute dans la liste de la partie
            if (etatPlateau.charAt(1)=='b') {
                piecesBlanchesPlateau.add(piece);
            }
            else{
                piecesNoiresPlateau.add(piece);
            }
        }
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