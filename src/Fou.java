import javax.swing.*;

/**
 * Created by mlucile on 30/03/16.
 */

public class Fou extends Piece
{
    private ImageIcon blanche;
    private ImageIcon noire;
    private Point coordonnees;

    /**
     * Constructeur d'une pièce "fou"
     */
    public Fou()
    {
    }

    /**
     * Chaque methode gère un déplacement possible
     * @param nbCase
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

    public void peutDeplacer(Point destination)
    {
        if()
        if(destination.getX() > this.coordonnees.getX() && destination.getY() > this.coordonnees.getY())
        {

        }
    }
}