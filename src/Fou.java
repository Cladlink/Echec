import javax.swing.*;

/**
 * Created by mlucile on 30/03/16.
 */

public class Fou extends Piece
{
    private final String adressePieceNoire = "adresseNoire";
    private final String adressePieceBlanche = "adresseBlanche";

    /**
     * Constructeur d'une pièce "fou"
     */
    public Fou(int x, int y, boolean isBlanc)
    {
        super(x, y);
        if (isBlanc)
            super.skin = new ImageIcon(this.adressePieceBlanche);
        else
            super.skin = new ImageIcon(this.adressePieceNoire);
    }

    /**
     * Chaque methode gère un déplacement possible
     * @param nbCase // todo mettre un commentaire à chaque fois qu'il y a un param (pour décrire le parametre)
     */
    public void diagoNE(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()+nbCase);
        super.coordonnees.setY(super.coordonnees.getY()+nbCase);
    }

    public void diagoNO(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()-nbCase);
        super.coordonnees.setY(super.coordonnees.getY()+nbCase);
    }

    public void diagoSE(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()+nbCase);
        super.coordonnees.setY(super.coordonnees.getY()-nbCase);
    }

    public void diagoSO(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()-nbCase);
        super.coordonnees.setY(super.coordonnees.getY()-nbCase);
    }
}