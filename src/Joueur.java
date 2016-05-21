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
    private boolean egalite;
    private boolean victoire;
    private int nbPartiesJoueur;
    private int nbVictoire;
    private int nbDefaite;
    private int nbEgalite;

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
        this.egalite = false;
        this.victoire = false;
        this.nbPartiesJoueur = 0;
        this.nbVictoire = 0;
        this.nbDefaite = 0;
        this.nbEgalite = 0;
    }

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
        this.victoire = Boolean.getBoolean(joueurRecup.get(0).get(2));
        this.nbPartiesJoueur = Integer.valueOf(joueurRecup.get(0).get(3));
        this.nbVictoire = Integer.valueOf(joueurRecup.get(0).get(4));
        this.nbEgalite = Integer.valueOf(joueurRecup.get(0).get(5));
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

    public static Vector<String> listeJoueurs()
    {
        bdd.start();
        ArrayList<ArrayList<String>> joueurs = bdd.ask("SELECT JOUEUR.pseudoJoueur FROM JOUEUR;");
        Vector<String> listeJoueurs = new Vector<>();
        for(int i=0; i<joueurs.size(); i++)
            for (int j = 0; j < joueurs.get(i).size(); j++)
                listeJoueurs.add(joueurs.get(i).get(j));
        bdd.stop();

        return listeJoueurs;
    }

    /**
     *
     * @param JoueurGagnant
     * @param JoueurPerdant
     */
    public static void ajouteVictoire(String JoueurGagnant, String JoueurPerdant)
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
     * @param Joueur1
     * @param Joueur2
     */
    public static void ajoutePat(String Joueur1, String Joueur2)
    {
        bdd.start();
        bdd.edit("UPDATE JOUEUR " +
                "SET nbPartiesEgaliteJoueur = nbPartiesEgaliteJoueur+1 " +
                "WHERE pseudoJoueur = \"" + Joueur1 + "\" or pseudoJoueur = \"" + Joueur2 + "\"  ;");
        bdd.stop();
    }
    // getters & setters
    public boolean isBlanc() {
        return isBlanc;
    }
    public void setBlanc(boolean blanc) {
        isBlanc = blanc;
    }
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public boolean isEgalite() {
        return egalite;
    }
    public void setEgalite(boolean egalite) {
        this.egalite = egalite;
    }
    public boolean isVictoire() {
        return victoire;
    }
    public void setVictoire(boolean victoire) {
        this.victoire = victoire;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNbPartiesJoueur() {
        return nbPartiesJoueur;
    }
    public void setNbPartiesJoueur(int nbPartiesJoueur) {
        this.nbPartiesJoueur = nbPartiesJoueur;
    }
    public int getNbVictoire() {
        return nbVictoire;
    }
    public void setNbVictoire(int nbVictoire) {
        this.nbVictoire = nbVictoire;
    }
    public int getNbDefaite() {
        return nbDefaite;
    }
    public void setNbDefaite(int nbDefaite) {
        this.nbDefaite = nbDefaite;
    }
    public int getNbEgalite() {
        return nbEgalite;
    }
    public void setNbEgalite(int nbEgalite) {
        this.nbEgalite = nbEgalite;
    }
    public BDDManager getBdd() {
        return bdd;
    }
}