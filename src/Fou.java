import javax.swing.*;

/**
 * Created by mlucile on 30/03/16.
 */

public class Fou extends Piece
{
    private ImageIcon blanche;
    private ImageIcon noire;
    private Point coordonnees; // todo a supprimer (passé dans Piece)

    /**
     * Constructeur d'une pièce "fou"
     */
    public Fou() // todo vu que le constructeur à changer il faut l'adapter (+ penser à utiliser le constructeur de Piece)
    {

    }

    /**
     * Chaque methode gère un déplacement possible
     * @param nbCase // todo mettre un commentaire à chaque fois qu'il y a un param (pour décrire le parametre)
     */
    public void diagoNE(int nbCase)
    {
        this.coordonnees.setX(this.coordonnees.getX() + nbCase);
        this.coordonnees.setY(this.coordonnees.getY() + nbCase);
    }

    public void diagoNO(int nbCase)
    {
        this.coordonnees.setX(this.coordonnees.getX() - nbCase);
        this.coordonnees.setY(this.coordonnees.getY() + nbCase);
    }

    public void diagoSE(int nbCase)
    {
        this.coordonnees.setX(this.coordonnees.getX() + nbCase);
        this.coordonnees.setY(this.coordonnees.getY() - nbCase);
    }

    public void diagoSO(int nbCase)
    {
        this.coordonnees.setX(this.coordonnees.getX() - nbCase);
        this.coordonnees.setY(this.coordonnees.getY() - nbCase);
    }

    // todo t'en occupe pas pour l'instant
    public void peutDeplacer(Point destination)
    {
        if()
        if(destination.getX() > this.coordonnees.getX() && destination.getY() > this.coordonnees.getY())
        {

        }
    }
}