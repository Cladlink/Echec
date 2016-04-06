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
     * @param plateau (l'état du plateau)
     * @param tourBlanc (a qui le tour ?)
     */
    public void load(Case[][] plateau, boolean tourBlanc)
    {

    }


}
