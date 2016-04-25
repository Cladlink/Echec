import java.util.ArrayList;

/**
  Created by cladlink on 06/04/16.
 */
public class Partie
{
    private Joueur joueurBlanc = null;
    private Joueur joueurNoir = null;
    private Board board = null;
    private ArrayList<String> listeCoups = null;
    private boolean tourBlanc = true;
    private ArrayList<Piece> cimetiereBlanc = null;
    private ArrayList<Piece> cimetiereNoir = null;
    private ArrayList<Piece> piecesBlanchesPlateau = null;
    private ArrayList<Piece> piecesNoiresPlateau = null;
    private int modePartie; // 0 = partie normale ; 1 = temps partie limitée; 3 = temps tour limités
    private String histoCoups = "";
    private boolean netPartie;
    private boolean echecBlanc;
    private boolean echecNoir;
    private boolean finPartie;
    private boolean roqueFaisable = true;


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
        {
            for (int j = 0; j < board.getPlateau()[i].length; j++)
            {
                    if(board.getPlateau()[i][j].getPiece() != null
                            && board.getPlateau()[i][j].getPiece().isBlanc())
                        piecesBlanchesPlateau.add(board.getPlateau()[i][j].getPiece());
                    else if(board.getPlateau()[i][j].getPiece() != null
                            && !board.getPlateau()[i][j].getPiece().isBlanc())
                        piecesNoiresPlateau.add(board.getPlateau()[i][j].getPiece());
            }
        }
    }

    /**
     * pause
     * met en pause
     */
    public void pause()
    {
        // a faire en fonction de l'état des timers quand il y en aura
    }

    /**
     * stop
     * arrète la partie todo prendre en compte abandon, mat
     */
    public void stop()
    {

    }

    /**
     * historiqueCoups
     *
     */
    public void historiqueCoups()
    {

    }

    /**
     * save
     * envoie l'insert en base de donnée afin de sauvegarder l'état du board
     *
     * utiliser la class BDDManager
     */
    public void save()
    {

    }

    /**
     * load
     * recoit un etat du board et redémarre la partie
     *
     */
    public void load()
    {

    }

    /**
     * attenteAction
     * comportent d'attente qu'une pièce soit jouer quelque soit le tour.
     *
     */
    public void attenteAction()
    {

    }

    /**
     * attenteDebutPartie
     * comportement d'attente du début de la partie
     *
     */
    public void attenteDebutPartie()
    {

    }

    /**
     * isEchec
     * Verifie si après un coup joué le roi adverse est en echec.
     *
     * @return true si echec
     */
    boolean isEchec(Case caseRoi)
    {
        int i;
        if (tourBlanc)
            for (i = 0; i < piecesBlanchesPlateau.size(); i++)
                if (piecesBlanchesPlateau.get(i).peutAtteindreRoi(caseRoi))
                    return true;
        else
            for (i = 0; i < piecesNoiresPlateau.size(); i++)
                if (piecesNoiresPlateau.get(i).peutAtteindreRoi(caseRoi))
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
    boolean isEchecEtMat(Case caseRoi)
    {

        return false;
    }


    // getters / setters
    public Joueur getJoueurBlanc() {
        return joueurBlanc;
    }
    public void setJoueurBlanc(Joueur joueurBlanc) {
        this.joueurBlanc = joueurBlanc;
    }
    public Joueur getJoueurNoir() {
        return joueurNoir;
    }
    public void setJoueurNoir(Joueur joueurNoir) {
        this.joueurNoir = joueurNoir;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public ArrayList<String> getListeCoups() {
        return listeCoups;
    }
    public void setListeCoups(ArrayList<String> listeCoups) {
        this.listeCoups = listeCoups;
    }
    public boolean isTourBlanc() {
        return tourBlanc;
    }
    public void setTourBlanc(boolean tourBlanc) {
        this.tourBlanc = tourBlanc;
    }
    public ArrayList<Piece> getCimetiereBlanc() {
        return cimetiereBlanc;
    }
    public void setCimetiereBlanc(ArrayList<Piece> cimetiereBlanc) {
        this.cimetiereBlanc = cimetiereBlanc;
    }
    public ArrayList<Piece> getCimetiereNoir() {
        return cimetiereNoir;
    }
    public void setCimetiereNoir(ArrayList<Piece> cimetiereNoir) {
        this.cimetiereNoir = cimetiereNoir;
    }
    public ArrayList<Piece> getPiecesBlanchesPlateau() {
        return piecesBlanchesPlateau;
    }
    public void setPiecesBlanchesPlateau(ArrayList<Piece> piecesBlanchesPlateau) {
        this.piecesBlanchesPlateau = piecesBlanchesPlateau;
    }
    public ArrayList<Piece> getPiecesNoiresPlateau() {
        return piecesNoiresPlateau;
    }
    public void setPiecesNoiresPlateau(ArrayList<Piece> piecesNoiresPlateau) {
        this.piecesNoiresPlateau = piecesNoiresPlateau;
    }
    public int getModePartie() {
        return modePartie;
    }
    public void setModePartie(int modePartie) {
        this.modePartie = modePartie;
    }
    public boolean isNetPartie() {
        return netPartie;
    }
    public void setNetPartie(boolean netPartie) {
        this.netPartie = netPartie;
    }
    public boolean isEchecBlanc() {
        return echecBlanc;
    }
    public void setEchecBlanc(boolean echecBlanc) {
        this.echecBlanc = echecBlanc;
    }
    public boolean isEchecNoir() {
        return echecNoir;
    }
    public void setEchecNoir(boolean echecNoir) {
        this.echecNoir = echecNoir;
    }
    public boolean isFinPartie() {
        return finPartie;
    }
    public void setFinPartie(boolean finPartie) {
        this.finPartie = finPartie;
    }
}
