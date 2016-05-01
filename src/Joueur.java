/**
  Created by Michael on 30/03/16.
 */
public class Joueur
{

    private int id;
    private boolean isBlanc;
    private String pseudo = null;
    private boolean egalite = false;
    private boolean victoire;
    private int nbPartiesJoueur;
    private int nbPartiesEnCours;
    private int nbVictoire;
    private int nbDefaite;
    private int nbEgalite;
    private boolean partieSauvegardee;



    /**
     * Joueur (Constructeur)
     * définit la couleur du joueur et le pseudo (si pas de pseudo mettre le pseudo anonyme qui sera réservé)
     *
     * @param isBlanc couleur du joueur
     * @param pseudo pseudo du joueur
     */
    public Joueur(boolean isBlanc, String pseudo)
    {
        this.isBlanc = isBlanc;
        if (pseudo.length() == 0)
            this.pseudo = "Anonyme";
        else
            this.pseudo = pseudo;
        victoire = false;

        partieSauvegardee = false;
    }

    // getters and setters
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