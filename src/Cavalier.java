import javax.swing.*;

/*
 * Created by baptiste on 31/03/16.
 */
public class Cavalier extends Piece
{
    private final String adressePieceNoire = "adresseNoire";
    private final String adressePieceBlanche = "adresseBlanche";

    //Constructeur
    public Cavalier(int x, int y, boolean isBlanc)
    {
        super(x, y);
        if (isBlanc)
            super.figure = new ImageIcon(this.adressePieceBlanche);
        else
            super.figure = new ImageIcon(this.adressePieceNoire);



    }
    //Méthodes de déplacement
    public void NNE()
    {
        super.coordonnees.setX(super.coordonnees.getX()+1);
        super.coordonnees.setY(super.coordonnees.getY()+2);
    }

    public void NEE()
    {
        super.coordonnees.setX(super.coordonnees.getX()+2);
        super.coordonnees.setY(super.coordonnees.getY()+1);
    }

    public void SEE()
    {
        super.coordonnees.setX(super.coordonnees.getX()+2);
        super.coordonnees.setY(super.coordonnees.getY()-1);
    }

    public void SSE()
    {
        super.coordonnees.setX(super.coordonnees.getX()+1);
        super.coordonnees.setY(super.coordonnees.getY()-2);
    }

    public void SSO()
    {
        super.coordonnees.setX(super.coordonnees.getX()-1);
        super.coordonnees.setY(super.coordonnees.getY()-2);
    }

    public void SOO()
    {
        super.coordonnees.setX(super.coordonnees.getX()-2);
        super.coordonnees.setY(super.coordonnees.getY()-1);
    }

    public void NOO()
    {
        super.coordonnees.setX(super.coordonnees.getX()-2);
        super.coordonnees.setY(super.coordonnees.getY()+1);
    }

    public void NNO()
    {
        super.coordonnees.setX(super.coordonnees.getX()-1);
        super.coordonnees.setY(super.coordonnees.getY()+2);
    }
}