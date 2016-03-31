import javax.swing.*;

/**
 *Created by mlucile on 30/03/16.
 */

public class Fou extends Piece
{
    /**
     * Attributs de Fou
     */
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
     * Déplacement de nbCase cases sur la diagonale Nord-Est
     * @param nbCase : nombre de case dont il faut se déplacer
     */
    public void diagoNE(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()+nbCase);
        super.coordonnees.setY(super.coordonnees.getY()+nbCase);
    }

    /**
     * Déplacement de nbCase cases sur la diagonale Nord-Ouest
     * @param nbCase : nombre de case dont il faut se déplacer
     */
    public void diagoNO(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()-nbCase);
        super.coordonnees.setY(super.coordonnees.getY()+nbCase);
    }

    /**
     * Déplacement de nbCase cases sur la diagonale Sud-Est
     * @param nbCase : nombre de case dont il faut se déplacer
     */
    public void diagoSE(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()+nbCase);
        super.coordonnees.setY(super.coordonnees.getY()-nbCase);
    }

    /**
     * Déplacement de nbCase cases sur la diagonale Sud-Ouest
     * @param nbCase : nombre de case dont il faut se déplacer
     */
    public void diagoSO(int nbCase)
    {
        super.coordonnees.setX(super.coordonnees.getX()-nbCase);
        super.coordonnees.setY(super.coordonnees.getY()-nbCase);
    }
}