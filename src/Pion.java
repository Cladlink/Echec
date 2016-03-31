import javax.swing.*;

/*
 * Created by Adonis on 30/03/16.
 */
/* IMPORTANT *
    Vu que l'on ne peut pas avoir un Pion étendu de plusieurs classe, et qu'on utilise la classe Pièce pour le définir en partie
    et la classe point pour connaitre son emplacement, ne devons-nous pas ajouter la classe Point entant que caractèristique de la classe pièce ?
 * IMPORTANT */

/*
    todo * evite d'envoyer un fichier avec autant d'erreur. Si tu veux proposer qqc, tu en parles et apres on met en place ou non
    todo * un fichier envoyé doit être compatible avec le code de tes petits camarades :p
    todo * algorithmiquement ton code doit être correct aussi, n'invente rien ;) Si tu as besoin d'aide sur la facon d'ecrire quelque chose
    todo * tu demandes ;)
 */
public class Pion extends Piece
{
    /*************************
     * Variables d'instances *
     ************************/
    private boolean noir; // todo le joueur définira la couleur
    private int valeur; // todo la valeur n'a pas d'interet (sauf exemple contraire)
    private ImageIcon image; // todo faut une image noire et une autre image blanche
    private Point coordonnees; // Cette caractéristique sert à savoir où se trouve la pièce sur le plateau c'est bien ça ? // todo ok ca roule je l'ai monté

    /*****************
     * Constructeurs *
     ****************/
    public Pion()
    {
        this.noir = true; // todo meme commentaire qu'au dessus
        this.valeur = 1; // todo meme commentaire qu'au dessus
        super.Point(){} //todo ceci n'existe pas à écrire comme il faut
    }

    /************
     * Méthodes *
     ***********/

    /**
     * Methode pour le déplacement avant qui utilise les methodes d'un objet Point pour se "situer".
     * @return
     */
    public Point depAvant()
    {
        // On récupére l'emplacement du pion courant
        super.getX(); // todo getX appartient à la classe Point ;) je te laisse réfléchir à la facon d'écrire ;)
        super.getY(); // todo idem

        // Si la place en avant est innoccuper
        if (this.x = this.x && this.y + 1 != true) // todo idem
        {
            // Définition de la nouvelle coordonnée
            int newY = this.y + 1; // todo idem
            // Attributions de la nouvelle coordonnée
            this.coordonnees = super.setY(newY); // todo idem
        }
        // todo mettre un retour
    }

    /**
     * Methode pour le déplacement avant gauche qui utilise les methodes d'un objet Point pour se "situer".
     * @return
     */
    public Point depAvantGauche()
    {
        // On récupére l'emplacement du pion courant
        super.getX(); // todo idem
        super.getY(); // todo idem

        // Si la place en en diagonale gauche est occupée
        if (this.x + 1 && this.y - 1 == true) // todo idem
        {
            // Définition des nouvelles coordonnées
            int newX = this.x + 1; // todo idem
            int newY = this.y - 1; // todo idem
            // Attributions des nouvelles coordonnées
            this.coordonnees = super.setX(newX); // todo idem
            this.coordonnees = super.setY(newY); // todo idem
        }
    }

    /**
     * Methode pour le déplacement avant droit qui utilise les methodes d'un objet Point pour se "situer".
     * @return
     */
    public Point depAvantDroit() // todo mettre un return ou un void
    {
        // On récupére l'emplacement du pion courant
        super.getX(); // todo idem
        super.getY(); // todo idem

        // Si la place en en diagonale droite est occupée
        if (this.x + 1 && this.y + 1 == true){ // todo idem + ca ne vérifie pas que la case est occupée
            // Définition des nouvelles coordonnées
            int newX = this.x + 1; // todo idem
            int newY = this.y + 1; // todo idem
            // Attributions des nouvelles coordonnées
            this.coordonnees = super.setX(newX); // todo idem
            this.coordonnees = super.setY(newY); // todo idem
        }
    }

    /**
     * Retourne la couleur de la pièce
     * @return
     */
    public boolean isNoir() {
        return noir;
    } // todo inutile cf plus haut

    /**
     * Retourne la valeur de la pièce
     * @return
     */
    public int getValeur() {
        return valeur;
    } // todo inutile cf plus haut

    /* IMPORTANT
        Pour détruire un pion (si il upgrade) j'ai pensée qu'il fallait mettre toutes les caractéristiques à zero puis créer un autre pion
        Mais comme le ramasse-miette intervient, j'ai essayer de faire une méthode qui change les cara du pion.
       IMPORTANT */
    // todo je vais reflechir à ca je suis pas sur que ca soit gérer là. L'idée est là sinon.
    public Pion(Piece p)
    {
        //si la piece courant n'est pas un pion
        if (!(p instanceof Pion))
        {
            // Construire un objet du type de la pièce courant (dame, tour, fou ou cavalier)
            super();// todo j'ai passé ton idee de monter Point dans Piece du coup, le constructeur doit être avec des coordonnees
        }
        else {
            System.out.println("Le pion ne peut pas se transformer en pion !");
        }
    }
}
