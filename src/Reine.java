import javax.swing.*;

/**
 * Created by Gabi on 30/03/16.
 */
public class Reine extends Piece
{
    private final String adressePieceNoire = "adresseNoire";
    private final String adressePieceBlanche = "adresseBlanche";

    public Reine(int x, int y, boolean isBlanc){
        super(x,y);
        if (isBlanc){
            super.figure = new ImageIcon(this.adressePieceBlanche);
        }
        else {
            super.figure = new ImageIcon(this.adressePieceNoire);
        }
    }

    public void verticalN(int nbCases){
        super.coordonnees.setY(coordonnees.getY()+nbCases);
    }

    public void verticalS(int nbCases){
        super.coordonnees.setY(coordonnees.getY()-nbCases);
    }

    public void horizontalE(int nbCases){
        super.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void horizontalO(int nbCases){
        super.coordonnees.setX(coordonnees.getX()-nbCases);
    }

    public void diagoNE(int nbCases){
        super.coordonnees.setY(coordonnees.getY()+nbCases);
        super.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void diagoNO(int nbCases){
        super.coordonnees.setY(coordonnees.getY()+nbCases);
        super.coordonnees.setX(coordonnees.getX()-nbCases);
    }

    public void diagoSE(int nbCases){
        super.coordonnees.setY(coordonnees.getY()-nbCases);
        super.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void diagoSO(int nbCases){
        this.coordonnees.setY(coordonnees.getY()-nbCases);
        this.coordonnees.setX(coordonnees.getX()-nbCases);
    }
}
