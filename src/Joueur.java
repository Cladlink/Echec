import java.util.ArrayList;
import java.util.Vector;

/**
  Created by Michael on 30/03/16.
 */
class Joueur
{
    private final static BDDManager bdd = new BDDManager();
    private int id;
    private boolean isBlanc;
    private String pseudo;

    /**
     * Joueur (Constructeur)
     * définit le cas d'un joueur Anonyme (pas d'appel à la bdd possible)
     *
     * @param isBlanc couleur du joueur
     */
    Joueur(boolean isBlanc)
    {
        this.id = 0;
        this.isBlanc = isBlanc;
        this.pseudo = "Anonyme";
    }

    /**
     * Joueur
     * création du joueur si son nom n'est pas "anonyme"
     *
     * @param isBlanc (couleur du joueur)
     * @param pseudo (pseudonyme du joueur)
     */
    Joueur(boolean isBlanc, String pseudo)
    {
        this.isBlanc = isBlanc;
        this.pseudo = pseudo;
        loadJoueur(pseudo);
    }

    /**
     * loadJoueur
     * charge les données d'un joueur depuis la bdd s'il existe, sinon, le créé
     *
     * @param pseudo (nécessaire pour identifier le joueur dans la bdd)
     */
    private void loadJoueur(String pseudo)
    {
        String requete = "SELECT * FROM JOUEUR WHERE pseudoJoueur = \"" + pseudo + "\";";
        bdd.start();
        ArrayList<ArrayList<String>> joueurRecup = bdd.ask(requete);
        bdd.stop();

        //si pas de joueur avec ce pseudo
        if (joueurRecup.size()==0)
        {
            inscriptionJoueur(pseudo);
            bdd.start();
            joueurRecup = bdd.ask(requete);
            bdd.stop();
        }

        this.id = Integer.valueOf(joueurRecup.get(0).get(0));
    }

    /**
     * inscription Joueur
     * Inscrit un nouveau joueur dans la BDD
     *
     * @param pseudo (seul élément necessaire à la création d'un joueur)
     */
    static void inscriptionJoueur(String pseudo)
    {
        bdd.start();
        bdd.edit("INSERT INTO JOUEUR VALUES (null, \"" + pseudo + "\", 0, 0, 0, 0);");
        bdd.stop();
    }

    /**
     * listeJoueurs
     *
     *
     * @return (retourne une liste de joueur)
     */
    static Vector<String> listeJoueurs()
    {
        bdd.start();
        ArrayList<ArrayList<String>> joueurs = bdd.ask("SELECT JOUEUR.pseudoJoueur FROM JOUEUR;");
        Vector<String> listeJoueurs = new Vector<>();
        for (ArrayList<String> joueur : joueurs)
            for (String aJoueur : joueur)
                listeJoueurs.add(aJoueur);

        bdd.stop();

        return listeJoueurs;
    }

    /**
     * ajouteVictoire
     *
     * @param JoueurGagnant ()
     * @param JoueurPerdant ()
     */
    static void ajouteVictoire(String JoueurGagnant, String JoueurPerdant)
    {
        bdd.start();
        bdd.edit("UPDATE JOUEUR " +
                "SET nbPartiesGagneesJoueur = nbPartiesGagneesJoueur+1 " +
                "WHERE pseudoJoueur = \"" + JoueurGagnant + "\";");
        bdd.edit("UPDATE JOUEUR " +
                "SET nbPartiesPerduesJoueur = nbPartiesPerduesJoueur+1 " +
                "WHERE pseudoJoueur = \"" + JoueurPerdant + "\";");
        bdd.stop();
    }

    /**
     *
     * @param Joueur1 ()
     * @param Joueur2 ()
     */
    static void ajoutePat(String Joueur1, String Joueur2)
    {
        bdd.start();
        bdd.edit("UPDATE JOUEUR" +
                "SET nbPartiesAbandonneeJoueur = nbPartiesAbandonneeJoueur+1" +
                "WHERE pseudoJoueur = \"" + Joueur1 + "\" or pseudoJoueur = \" " + Joueur2 + " ;");
        bdd.stop();
    }
    
    // getters & setters
    boolean isBlanc() {
        return isBlanc;
    }
    void setBlanc(boolean blanc) {
        isBlanc = blanc;
    }
    String getPseudo() {
        return pseudo;
    }
    int getId() {
        return id;
    }
}