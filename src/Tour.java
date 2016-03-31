import javax.swing.*;

/*
 * Created by Kevin on 30/03/16.
 */
public class Tour extends Piece
{
    private final String adressePieceNoire = "adresseNoire";
    private final String adressePieceBlanche = "adresseBlanche";

    public Tour(int x, int y)
    {
        super(x,y);
        if (isBlanc)
            super.skin = new ImageIcon(this.adressePieceBlanche);
        else
            super.skin = new ImageIcon(this.adressePieceNoire);
    }



    /**
     * Vérifie si la tour peut se déplacer
     * c-a-d : si le x de point est égal à x de la tour
     * ou si le y de point est égal à y de la tour
     * @param pointDeplace : coordonnées de la case destination
     * @return boolean -> true si peut se déplacer
     */
    public boolean peutDeplacer(Point pointDeplace)
    {
        if (this.coordonnees.getX() == pointDeplace.getX()
                || this.coordonnees.getY() == pointDeplace.getY())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Change les coordonnées de la pièce en fonction de la méthode appelé et d'une nombre de case "i" donné
     * @param nbCase: nombre de case a se déplacer
     * @return boolean -> true si peut se déplacer // todo pas bon faut le virer t'as pas de return ici (faut mettre
     *                                             // todo au bon endroit).
     */
    public void deplacementN(int nbCase){
        coordonnees.setY(coordonnees.getY()+nbCase);
    }
    public void deplacementE(int nbCase){
        coordonnees.setX(coordonnees.getX()+nbCase);
    }
    public void deplacementS(int nbCase){
        coordonnees.setY(coordonnees.getY()-nbCase);
    }
    public void deplacementO(int nbCase){
        coordonnees.setX(coordonnees.getX()-nbCase);
    }
}
