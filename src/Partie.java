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
    private boolean netPartie;
    private boolean isEchecBlanc;
    private boolean isEchecNoir;


    /**
     * Partie Constructeur
     * les joueurs sont passé en paramêtres
     *
     * @param joueurBlanc j1
     * @param joueurNoir j2
     */
    public Partie(Joueur joueurBlanc, Joueur joueurNoir, int modePartie, boolean netPartie)
    {
        this.joueurBlanc = joueurBlanc;
        this.joueurNoir = joueurNoir;
        board = new Board(this);
        this.modePartie = modePartie;
        this.netPartie = netPartie;
        isEchecBlanc = false;
        isEchecNoir = false;
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
     * arrète la partie todo prendre en compte abandon, pat, mat
     */
    public void stop()
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

}
