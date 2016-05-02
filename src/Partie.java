    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Date;
    import java.util.Locale;
    import java.util.TimerTask;
    import java.util.Timer;

    /**
        Created by cladlink on 06/04/16.
    */
public class Partie
{
    private Timer tm = null;
    private TimerTask tt = null;
    private Joueur joueurBlanc = null;
    private Joueur joueurNoir = null;
    private Board board = null;
    private ArrayList<String> listeCoups = null;
    private boolean tourBlanc = true;
    private ArrayList<Piece> cimetiereBlanc = null;
    private ArrayList<Piece> cimetiereNoir = null;
    private ArrayList<Piece> piecesBlanchesPlateau = null;
    private ArrayList<Piece> piecesNoiresPlateau = null;
    private int modePartie; // 0 = partie sans temps ; 1 = temps partie limitée; 2 = temps tour limités
    private ArrayList<String> historique = null;
    private boolean netPartie;
    private boolean echecBlanc;
    private boolean echecNoir;
    private boolean finPartie;


    /**
     * Partie Constructeur
     *
     * @param joueurBlanc j1
     * @param joueurNoir j2
     */
    public Partie(Joueur joueurBlanc, Joueur joueurNoir, int modePartie, boolean netPartie)
    {
        finPartie = false;
        //On ajoute les deux joueurs à la partie
        this.joueurBlanc = joueurBlanc;
        this.joueurNoir = joueurNoir;
        // On créé le plateau
        board = new Board(this);

        // choix du mode de la partie
        this.modePartie = modePartie;

        // pour la partie en réseau
        this.netPartie = netPartie;

        // Le roi est protégé en début de partie, il n'y a donc pas d'échec
        echecBlanc = false;
        echecNoir = false;

        piecesBlanchesPlateau = new ArrayList<>();
        piecesNoiresPlateau = new ArrayList<>();
        // on initie le cimetière à 0 (car en début de partie il n'y a pas de pièce dans le cimetierre !)
        cimetiereBlanc = new ArrayList<>();
        cimetiereNoir = new ArrayList<>();

        // on met à jour la liste des pièces blanches et noires en jeu
        for (int i = 0; i < board.getPlateau().length; i++)
            for (int j = 0; j < board.getPlateau()[i].length; j++)
                    if(board.getPlateau()[i][j].getPiece() != null
                            && board.getPlateau()[i][j].getPiece().isBlanc())
                        piecesBlanchesPlateau.add(board.getPlateau()[i][j].getPiece());
                    else if(board.getPlateau()[i][j].getPiece() != null
                            && !board.getPlateau()[i][j].getPiece().isBlanc())
                        piecesNoiresPlateau.add(board.getPlateau()[i][j].getPiece());
        historique = new ArrayList<>();
    }
    // todo
    public synchronized void tourLimite()
    {
        tm = new Timer();
        tt = new TimerTask()
        {
            @Override
            public void run()
            {
                finDeJeuTemps();
            }
        };
        tm.schedule(tt, 30000);


    }
    // todo
    public synchronized  void tempsLimite()
    {

    }
    // todo
    public synchronized void finDeJeuTemps()
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
     * pause todo
     * met en pause
     */
    public synchronized void pause()
    {
        // a faire en fonction de l'état des timers quand il y en aura
    }

    /**
     * stop todo
     * arrète la partie todo prendre en compte abandon, mat
     */
    public synchronized void stop()
    {

    }

    /**
     * historiqueCoups
     * met a jours l'arrayList de coups jouées a chaque deplacement
     */
    public synchronized void historiqueCoups(Case caseCliquee, Case destination)
    {
        String coup = "";

        //recupere le type de piece
        Piece piece = caseCliquee.getPiece();
        //verifie que la piece n'est pas null
        if (piece != null)
            if (piece instanceof Pion)
                coup += 'p';
            else if (piece instanceof Tour)
                coup += 't';
            else if (piece instanceof Cavalier)
                coup += 'c';
            else if (piece instanceof Fou)
                coup += 'f';
            else if (piece instanceof Reine)
                coup += 'q';
            else if (piece instanceof Roi)
                coup += 'r';

        //recupere la couleur de la piece
        if (piece.isBlanc())
            coup +='b';
        else
            coup+='n';

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
            coup+='!';
            //si c'est une piece blanche on va chercher le dernier ajouté du cimetiere noir
            if (pieceDestination != null)
                if (pieceDestination instanceof Pion)
                    coup += 'p';
                else if (pieceDestination instanceof Tour)
                    coup += 't';
                else if (pieceDestination instanceof Cavalier)
                    coup += 'c';
                else if (pieceDestination instanceof Fou)
                    coup += 'f';
                else if (pieceDestination instanceof Reine)
                    coup += 'q';
                else if (pieceDestination instanceof Roi)
                    coup += 'r';

            //recupere la couleur de la pieceDestination
            if (pieceDestination.isBlanc())
                coup +='b';
            else
                coup+='n';
        }

        //ajoute le coups joué dans l'historique
        historique.add(coup);
        System.out.println(historique);
    }

    /**
     * inscription Joueur todo
     * Inscrit un nouveau joueur dans la BDD
     *
     * @param pseudo (seul élément necessaire à la création d'un joueur)
     */
    public synchronized void inscriptionJoueur(String pseudo)
    {
        BDDManager bdd = new BDDManager();
        bdd.start();
        bdd.edit("INSERT INTO JOUEUR (pseudoJoueur, nbPartiesJoueur, nbPartiesGagneesJoueur," +
                "nbPartiesPerduesJoueur, nbPartiesAbandonneeJoueur, partieEnCoursJoueur, " +
                "trophee1, trophee2, trophee3) VALUES ("+ pseudo + ", 0, 0, 0, 0, 0, false, false, false);");;

        bdd.stop();
    }

    /**
     * save todo
     * envoie l'insert en base de donnée afin de sauvegarder l'état du board
     *
     * utiliser la class BDDManager
     */
    public synchronized void save()
    {
        BDDManager bdd = new BDDManager();
        bdd.start();
        String dateActuelle = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(new Date());
        String coupsJoues = "";
        int i, j;
        // sauvegarde de l'historique des coups joués dans la base de donnée
        for(i=0; i<this.historique.size(); i++)
        {
            coupsJoues += this.historique.get(i) + "-";
        }

        String requeteHistorique = "INSERT INTO HISTORIQUE VALUES (null, \"" + joueurBlanc.getId() + "\", \""
                + joueurNoir.getId()
                + "\", \"" + dateActuelle + "\", \"" + coupsJoues + "\";";

        // sauvegarde de l'état du plateau au moment de l'intéruption de la partie
        String listeEmplacementsPieces = "";
        Case[][] plateau = board.getPlateau();
        for(i=0; i<board.getPlateau().length; i++)
        {
            for(j=0; j<board.getPlateau()[i].length; j++)
            {
                if(plateau[i][j].getPiece() != null)
                {
                    if(plateau[i][j].getPiece() instanceof Roi && plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "RoB-";
                    else if(plateau[i][j].getPiece() instanceof Roi && !plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "RN-";
                    else if(plateau[i][j].getPiece() instanceof Reine && plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "ReB-";
                    else if(plateau[i][j].getPiece() instanceof Reine && !plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "ReN-";
                    else if(plateau[i][j].getPiece() instanceof Tour && plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "TB-";
                    else if(plateau[i][j].getPiece() instanceof Tour && !plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "TN-";
                    else if(plateau[i][j].getPiece() instanceof Fou && plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "FB-";
                    else if(plateau[i][j].getPiece() instanceof Fou && !plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "FN-";
                    else if(plateau[i][j].getPiece() instanceof Cavalier && plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "CB-";
                    else if(plateau[i][j].getPiece() instanceof Cavalier && !plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "CN-";
                    else if(plateau[i][j].getPiece() instanceof Pion && plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "PB-";
                    else if(plateau[i][j].getPiece() instanceof Pion && !plateau[i][j].getPiece().blanc)
                        listeEmplacementsPieces += i + j + "PN-";
                }
            }
        }
        String requeteIdhistorique = "SELECT HISTORIQUE.idHistorique FROM HISTORIQUE " +
                "WHERE HISTORIQUE.joueurBlancPartie = " + joueurBlanc.getId() +
                "AND HISTORIQUE.joueurNoirPartie = " + joueurNoir.getId() + ";";
        ArrayList<ArrayList<String>> resultat = bdd.ask(requeteIdhistorique);
        ArrayList<String> resultat2 = resultat.get(0);
        int idHistorique = Integer.parseInt(resultat2.get(0));

        String requeteSauvegarde = "INSERT INTO SAUVEGARDE VALUES (null, \"" + joueurBlanc.getId() + "\", \""
                + joueurNoir.getId()
                + "\", \"" + dateActuelle + "\", \"" + tourBlanc + "\", \"" + listeEmplacementsPieces + "\", \""
                + idHistorique;

        bdd.edit(requeteHistorique);
        bdd.edit(requeteSauvegarde);
        bdd.stop();
    }

    /**
     * load todo
     * recoit un etat du board et redémarre la partie
     *
     */
    public synchronized void load()
    {

    }

    /**
     * attenteAction todo
     * comportent d'attente qu'une pièce soit jouer quelque soit le tour.
     *
     */
    public synchronized void attenteAction()
    {

    }

    /**
     * attenteDebutPartie todo
     * comportement d'attente du début de la partie
     *
     */
    public synchronized void attenteDebutPartie()
    {

    }

    /**
     * isEchec
     * Verifie si après un coup joué le roi adverse est en echec.
     *
     * @return true si echec
     */
    synchronized boolean isEchec(Case caseRoi)
    {
        int i;
        if (!caseRoi.getPiece().blanc)
        {
            for (i = 0; i < piecesBlanchesPlateau.size(); i++)
                if (piecesBlanchesPlateau.get(i).peutAtteindreRoi(caseRoi))
                    return true;
        }
        else
        {
            for (i = 0; i < piecesNoiresPlateau.size(); i++)
                if (piecesNoiresPlateau.get(i).peutAtteindreRoi(caseRoi))
                    return true;
        }
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
        if(tourBlanc)
        {
            for (int i = 0; i < piecesBlanchesPlateau.size(); i++)
                if (piecesBlanchesPlateau.get(i).casesAtteignables != null)
                    return false;

        }
        else
        {
            for (int i = 0; i < piecesNoiresPlateau.size(); i++)
                if (piecesNoiresPlateau.get(i).casesAtteignables != null)
                    return false;

        }
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
        int i, j;

        // s'il ne reste que deux rois
        if(piecesBlanchesPlateau.size() == 1 && piecesNoiresPlateau.size() == 1)
            return true;

        // s'il reste un cavalier blanc et les deux rois
        else if(piecesBlanchesPlateau.size()== 2)
                if( (piecesBlanchesPlateau.get(0) instanceof Cavalier)
                        || (piecesBlanchesPlateau.get(1) instanceof Cavalier)
                    && piecesNoiresPlateau.size() == 1)
                return true;

        // s'il reste un cavalier noir et les deux rois
        else if(piecesNoiresPlateau.size()== 2)
            if( (piecesNoiresPlateau.get(0) instanceof Cavalier)
                        || (piecesNoiresPlateau.get(1) instanceof Cavalier)
                    && piecesBlanchesPlateau.size() == 1)
                return true;

        // si aucun déplacement n'est possible et que le roi n'est pas en échec
        if(tourBlanc)
        {
            for (i = 0; i < piecesBlanchesPlateau.size(); i++)
                for (j = 0; j < piecesBlanchesPlateau.get(i).casesAtteignables.size(); j++)
                    if (piecesBlanchesPlateau.get(i).casesAtteignables.get(i) != null)
                        return false;
            return true;
        }
        else
        {
            for (i = 0; i < piecesNoiresPlateau.size(); i++)
                for (j = 0; j < piecesNoiresPlateau.get(i).casesAtteignables.size(); j++)
                    if (piecesNoiresPlateau.get(i).casesAtteignables.get(i) != null)
                        return false;
            return true;
        }
    }


    // getters / setters
    public synchronized Joueur getJoueurBlanc() {
        return joueurBlanc;
    }
    public synchronized void setJoueurBlanc(Joueur joueurBlanc) {
        this.joueurBlanc = joueurBlanc;
    }
    public synchronized Joueur getJoueurNoir() {
        return joueurNoir;
    }
    public synchronized void setJoueurNoir(Joueur joueurNoir) {
        this.joueurNoir = joueurNoir;
    }
    public synchronized Board getBoard() {
        return board;
    }
    public synchronized void setBoard(Board board) {
        this.board = board;
    }
    public synchronized ArrayList<String> getListeCoups() {
        return listeCoups;
    }
    public synchronized void setListeCoups(ArrayList<String> listeCoups) {
        this.listeCoups = listeCoups;
    }
    public synchronized boolean isTourBlanc() {
        return tourBlanc;
    }
    public synchronized void setTourBlanc(boolean tourBlanc) {
        this.tourBlanc = tourBlanc;
    }
    public synchronized ArrayList<Piece> getCimetiereBlanc() {
        return cimetiereBlanc;
    }
    public synchronized void setCimetiereBlanc(ArrayList<Piece> cimetiereBlanc) {
        this.cimetiereBlanc = cimetiereBlanc;
    }
    public synchronized ArrayList<Piece> getCimetiereNoir() {
        return cimetiereNoir;
    }
    public synchronized void setCimetiereNoir(ArrayList<Piece> cimetiereNoir) {
        this.cimetiereNoir = cimetiereNoir;
    }
    public synchronized ArrayList<Piece> getPiecesBlanchesPlateau() {
        return piecesBlanchesPlateau;
    }
    public synchronized void setPiecesBlanchesPlateau(ArrayList<Piece> piecesBlanchesPlateau) {
        this.piecesBlanchesPlateau = piecesBlanchesPlateau;
    }
    public synchronized ArrayList<Piece> getPiecesNoiresPlateau() {
        return piecesNoiresPlateau;
    }
    public synchronized void setPiecesNoiresPlateau(ArrayList<Piece> piecesNoiresPlateau) {
        this.piecesNoiresPlateau = piecesNoiresPlateau;
    }
    public synchronized int getModePartie() {
        return modePartie;
    }
    public synchronized void setModePartie(int modePartie) {
        this.modePartie = modePartie;
    }
    public synchronized boolean isNetPartie() {
        return netPartie;
    }
    public synchronized void setNetPartie(boolean netPartie) {
        this.netPartie = netPartie;
    }
    public synchronized boolean isEchecBlanc() {
        return echecBlanc;
    }
    public synchronized void setEchecBlanc(boolean echecBlanc) {
        this.echecBlanc = echecBlanc;
    }
    public synchronized boolean isEchecNoir() {
        return echecNoir;
    }
    public synchronized void setEchecNoir(boolean echecNoir) {
        this.echecNoir = echecNoir;
    }
    public synchronized boolean isFinPartie() {
        return finPartie;
    }
    public synchronized void setFinPartie(boolean finPartie) {
        this.finPartie = finPartie;
    }
}