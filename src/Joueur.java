/*
 * Created by cladlink on 30/03/16.
    */
public class Joueur
{

    private boolean isBlanc;
    private String pseudo = null;
    private boolean egalite = false;
    private boolean victoire;

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
}