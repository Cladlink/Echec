import javax.swing.*;

/*
 * Created by cladlink on 30/03/16.
 */
public class Roi extends Piece
{
    private final String adressePieceNoire = "adresseNoire";
    private final String adressePieceBlanche = "adresseBlanche";
    //lol

    public Roi(int x, int y, boolean isBlanc){
        super(x,y);
        if (isBlanc)
            super.figure = new ImageIcon(this.adressePieceBlanche);
        else
            super.figure = new ImageIcon(this.adressePieceNoire);

    }

    public void verticalN(){ super.coordonnees.setY(coordonnees.getY()+1); }
    public void verticalS(){ super.coordonnees.setY(coordonnees.getY()-1); }
    public void horizontalO() { super.coordonnees.setX(coordonnees.getX()-1); }
    public void horizontalE() { super.coordonnees.setX(coordonnees.getX()+1); }


}
