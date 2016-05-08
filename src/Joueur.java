import java.util.ArrayList;

/**
  Created by Michael on 30/03/16.
 */
class Joueur
{

    private final BDDManager bdd = new BDDManager();
    private int id;
    private boolean isBlanc;
    private String pseudo;
    private boolean egalite;
    private boolean victoire;
    private int nbPartiesJoueur;
    private int nbPartiesEnCours;
    private int nbVictoire;
    private int nbDefaite;
    private int nbEgalite;
    private boolean partieSauvegardee;



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
        this.nbPartiesEnCours = 0;
        this.nbVictoire = 0;
        this.nbDefaite = 0;
        this.nbEgalite = 0;
        this.partieSauvegardee = false;
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
        String requete = "SELECT *" + " FROM JOUEUR " + "WHERE pseudoJoueur = " + pseudo + ";";
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

        this.setId(Integer.valueOf(joueurRecup.get(0).get(0)));
        this.setBlanc(Boolean.getBoolean(joueurRecup.get(2).get(0)));
        this.setEgalite(Boolean.getBoolean(joueurRecup.get(3).get(0)));
        this.setVictoire(Boolean.getBoolean(joueurRecup.get(4).get(0)));
        this.setNbPartiesJoueur(Integer.valueOf(joueurRecup.get(5).get(0)));
        this.setNbPartiesEnCours(Integer.valueOf(joueurRecup.get(6).get(0)));
        this.setNbVictoire(Integer.valueOf(joueurRecup.get(7).get(0)));
        this.setNbEgalite(Integer.valueOf(joueurRecup.get(8).get(0)));
        this.setPartieSauvegardee(Boolean.getBoolean(joueurRecup.get(9).get(0)));
    }

    /**
     * inscription Joueur
     * Inscrit un nouveau joueur dans la BDD
     *
     * @param pseudo (seul élément necessaire à la création d'un joueur)
     */
    private void inscriptionJoueur(String pseudo)
    {
        bdd.start();
        bdd.edit("INSERT INTO JOUEUR (pseudoJoueur, nbPartiesJoueur, nbPartiesGagneesJoueur," +
                "nbPartiesPerduesJoueur, nbPartiesAbandonneeJoueur, partieEnCoursJoueur, " +
                "trophee1, trophee2, trophee3) VALUES ("+ pseudo + ", 0, 0, 0, 0, 0, false, false, false);");;

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
    public int getNbPartiesEnCours() {
        return nbPartiesEnCours;
    }
    public void setNbPartiesEnCours(int nbPartiesEnCours) {
        this.nbPartiesEnCours = nbPartiesEnCours;
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
    public boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }
    public void setPartieSauvegardee(boolean partieSauvegardee) {
        this.partieSauvegardee = partieSauvegardee;
    }
}