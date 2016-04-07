import java.util.ArrayList;

/**
  Created by cladlink on 06/04/16.
 */
public class Partie
{

    private Joueur joueurBlanc = null;
    private Joueur joueurNoir = null;
    private Plateau plateau = null;
    private ArrayList<String> listeCoups = null;
    private boolean tourBlanc = true;
    private ArrayList<Piece> cimetiereBlanc = null;
    private ArrayList<Piece> cimetiereNoir = null;
    private ArrayList<Piece> piecesBlanchesPlateau = null;
    private ArrayList<Piece> piecesNoiresPlateau = null;
    private int modePartie; // 0 = partie normale ; 1 = temps partie limitée; 3 = temps tour limités
    private boolean netPartie;


    /**
     * Partie Constructeur
     * les joueurs sont passé en paramêtres
     *
     * @param joueurBlanc j1
     * @param joueurNoir j2
     */
    public Partie(Joueur joueurBlanc, Joueur joueurNoir)
    {

    }

    /**
     * endGame
     * met fin à la partie
     */
    public void start()
    {

    }

    /**
     * stopGame
     * arrète la partie
     */
    public void stop()
    {

    }

    /**
     * save
     * envoie l'insert en base de donnée afin de sauvegarder l'état du plateau
     *
     * utiliser la class BDDManager
     */
    public void save()
    {

    }

    /**
     * load
     * recoit un etat du plateau et redémarre la partie
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
    public Plateau getPlateau() {
        return plateau;
    }
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
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
