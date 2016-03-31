import javax.swing.*;

/*
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
    private final String adresseNoir = "";
    private final String adresseBlanc = "";

    /*****************
     * Constructeurs *
     ****************/
    public Pion(int x, int y, boolean isBlanc)
    {
        super(x, y);
        super.isBlanc = isBlanc;
        if (isBlanc)
            super.skin = new ImageIcon(this.adresseBlanc);
        else
            super.skin = new ImageIcon(this.adresseNoir);

    }

    /************
     * Méthodes *
     ***********/

    /**
     * Methode pour le déplacement
     */

    /**
     * Méthode qui permet de se déplacer de deux cases uniquement au premier déplacment pour le pion blanc
     */
    public void firstMooveWhite()
    {

        // Si le pion est sur la deuxième ligne du plateau
        if (super.coordonnees.getY() == 2)
        {
            //Déplacement de une case
            super.coordonnees.setY(super.coordonnees.getY() + 1);
        }
        else if (super.coordonnees.getY() == 2)
        {
            super.coordonnees.setY(super.coordonnees.getY()+2);
        }
    }

    /**
     * Méthode qui permet au pion blanc de se déplacer vers l'avant
     */
    public void mooveWhite()
    {
        //Déplacement de une case
        super.coordonnees.setY(super.coordonnees.getY() + 1);
    }

    /**
     * Méthode qui permet au pion blanc d'attaquer par la diago gauche
     */
    public void leftAttackWhite()
    {
        //Déplacement latéral gauche
        super.coordonnees.setX(super.coordonnees.getX() - 1);
        super.coordonnees.setY(super.coordonnees.getY() + 1);
    }

    /**
     * Méthode qui permet au pion blanc d'attaquer par la diago droite
     */
    public void rightAttackWhite()
    {
        //Déplacement latéral gauche
        super.coordonnees.setX(super.coordonnees.getX() + 1);
        super.coordonnees.setY(super.coordonnees.getY() + 1);
    }


    /**
     * Méthode qui permet de se déplacer de deux cases uniquement au premier déplacment pour le pion noir
     */
    public void firstMooveBlack()
    {
        // Si le pion est sur la deuxième ligne du plateau
        if (super.coordonnees.getY() == 7)
        {
            //Déplacement de une case
            super.coordonnees.setY(super.coordonnees.getY() - 1);
        }
        else if (super.coordonnees.getY() == 7)
        {
            super.coordonnees.setY(super.coordonnees.getY() - 2);
        }
    }

    /**
     * Méthode qui permet au pion noir de se déplacer vers l'avant
     */
    public void mooveBlack()
    {
        //Déplacement de une case
        super.coordonnees.setY(super.coordonnees.getY() - 1);
    }

    /**
     * Méthode qui permet au pion black d'attaquer par la diago gauche
     */
    public void leftAttackBlack()
    {
        //Déplacement latéral gauche
        super.coordonnees.setX(super.coordonnees.getX() - 1);
        super.coordonnees.setY(super.coordonnees.getY() - 1);
    }

    /**
     * Méthode qui permet au pion blanc d'attaquer par la diago droite
     */
    public void rightAttackBlack()
    {
        //Déplacement latéral gauche
        super.coordonnees.setX(super.coordonnees.getX() + 1);
        super.coordonnees.setY(super.coordonnees.getY() - 1);
    }
}
