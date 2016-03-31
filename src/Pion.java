import javax.swing.*;

/**
 * Created by Adonis on 30/03/16.
 */
/* IMPORTANT *
    Vu que l'on ne peut pas avoir un Pion étendu de plusieurs classe, et qu'on utilise la classe Pièce pour le définir en partie
    et la classe point pour connaitre son emplacement, ne devons-nous pas ajouter la classe Point entant que caractèristique de la classe pièce ?
 * IMPORTANT */
public class Pion extends Piece
{
    /*************************
     * Variables d'instances *
     ************************/
    private boolean noir;
    private int valeur;
    private ImageIcon image;
    private Point coordonnees; // Cette caractéristique sert à savoir où se trouve la pièce sur le plateau c'est bien ça ?

    /*****************
     * Constructeurs *
     ****************/
    public Pion(){
        this.noir = true;
        this.valeur = 1;
        super.Point(){}
    }

    /************
     * Méthodes *
     ***********/

    /**
     * Methode pour le déplacement avant qui utilise les methodes d'un objet Point pour se "situer".
     * @return
     */
    public Point depAvant(){
        // On récupére l'emplacement du pion courant
        super.getX();
        super.getY();

        // Si la place en avant est innoccuper
        if (this.x = this.x && this.y + 1 != true){
            // Définition de la nouvelle coordonnée
            int newY = this.y + 1;
            // Attributions de la nouvelle coordonnée
            this.coordonnees = super.setY(newY);
        }
    }

    /**
     * Methode pour le déplacement avant gauche qui utilise les methodes d'un objet Point pour se "situer".
     * @return
     */
    public Point depAvantGauche(){
        // On récupére l'emplacement du pion courant
        super.getX();
        super.getY();

        // Si la place en en diagonale gauche est occupée
        if (this.x + 1 && this.y - 1 == true){
            // Définition des nouvelles coordonnées
            int newX = this.x + 1;
            int newY = this.y - 1;
            // Attributions des nouvelles coordonnées
            this.coordonnees = super.setX(newX);
            this.coordonnees = super.setY(newY);
        }
    }

    /**
     * Methode pour le déplacement avant droit qui utilise les methodes d'un objet Point pour se "situer".
     * @return
     */
    public Point depAvantDroit(){
        // On récupére l'emplacement du pion courant
        super.getX();
        super.getY();

        // Si la place en en diagonale droite est occupée
        if (this.x + 1 && this.y + 1 == true){
            // Définition des nouvelles coordonnées
            int newX = this.x + 1;
            int newY = this.y + 1;
            // Attributions des nouvelles coordonnées
            this.coordonnees = super.setX(newX);
            this.coordonnees = super.setY(newY);
        }
    }

    /**
     * Retourne la couleur de la pièce
     * @return
     */
    public boolean isNoir() {
        return noir;
    }

    /**
     * Retourne la valeur de la pièce
     * @return
     */
    public int getValeur() {
        return valeur;
    }

    /* IMPORTANT
        Pour détruire un pion (si il upgrade) j'ai pensée qu'il fallait mettre toutes les caractéristiques à zero puis créer un autre pion
        Mais comme le ramasse-miette intervient, j'ai essayer de faire une méthode qui change les cara du pion.
       IMPORTANT */
    public Pion(Piece p){
        //si la piece courant n'est pas un pion
        if (!(p instanceof Pion)){
            // Construire un objet du type de la pièce courant (dame, tour, fou ou cavalier)
            super();
        }
        else {
            System.out.println("Le pion ne peut pas se transformer en pion !");
        }
    }
}
