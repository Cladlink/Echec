import javax.swing.*;

/**
 * Created by baptiste on 31/03/16.
 */
public class Cavalier extends Piece {
    //Attributs
    private ImageIcon figurineNoire;
    private ImageIcon figurineBlanche;
    private Point coordonnees;

    //Constructeur
    public Cavalier() {

    }

    //Méthodes de déplacement
    public void NNE() {
        coordonnees.setX(coordonnees.getX()+1);
        coordonnees.setY(coordonnees.getY()+2);
    }

    public void NEE() {
        coordonnees.setX(coordonnees.getX()+2);
        coordonnees.setY(coordonnees.getY()+1);
    }

    public void SEE() {
        coordonnees.setX(coordonnees.getX()+2);
        coordonnees.setY(coordonnees.getY()-1);
    }

    public void SSE() {
        coordonnees.setX(coordonnees.getX()+1);
        coordonnees.setY(coordonnees.getY()-2);
    }

    public void SSO() {
        coordonnees.setX(coordonnees.getX()-1);
        coordonnees.setY(coordonnees.getY()-2);
    }

    public void SOO() {
        coordonnees.setX(coordonnees.getX()-2);
        coordonnees.setY(coordonnees.getY()-1);
    }

    public void NOO() {
        coordonnees.setX(coordonnees.getX()-2);
        coordonnees.setY(coordonnees.getY()+1);
    }

    public void NNO() {
        coordonnees.setX(coordonnees.getX()-1);
        coordonnees.setY(coordonnees.getY()+2);
    }
}