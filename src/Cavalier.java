import javax.swing.*;

/*
 * Created by baptiste on 31/03/16.
 */
    // todo * n'envoie pas de code s'il y a du rouge !
    // todo *
public class Cavalier extends Piece
{
    //Attributs
    private ImageIcon figurineNoire;
    private ImageIcon figurineBlanche;
    private Point coordonnees; // todo a supprimer (ca fait partie de Piece maintenant)

    //Constructeur
    public Cavalier() // todo rajoute un super et écrit le correctement ;)
    {

    }

    //Méthodes de déplacement
    // todo rajoute des super.coordonnees pour la lisibilité
    public void NNE()
    {
        coordonnees.setX(coordonnees.getX()+1);
        coordonnees.setY(coordonnees.getY()+2);
    }

    public void NEE()
    {
        coordonnees.setX(coordonnees.getX()+2);
        coordonnees.setY(coordonnees.getY()+1);
    }

    public void SEE()
    {
        coordonnees.setX(coordonnees.getX()+2);
        coordonnees.setY(coordonnees.getY()-1);
    }

    public void SSE()
    {
        coordonnees.setX(coordonnees.getX()+1);
        coordonnees.setY(coordonnees.getY()-2);
    }

    public void SSO()
    {
        coordonnees.setX(coordonnees.getX()-1);
        coordonnees.setY(coordonnees.getY()-2);
    }

    public void SOO()
    {
        coordonnees.setX(coordonnees.getX()-2);
        coordonnees.setY(coordonnees.getY()-1);
    }

    public void NOO()
    {
        coordonnees.setX(coordonnees.getX()-2);
        coordonnees.setY(coordonnees.getY()+1);
    }

    public void NNO()
    {
        coordonnees.setX(coordonnees.getX()-1);
        coordonnees.setY(coordonnees.getY()+2);
    }
}