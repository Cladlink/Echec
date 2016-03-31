import javax.swing.*;

/**
 * Created by Gabi on 30/03/16.
 */
public class Reine extends Piece
{
    private ImageIcon noire;
    private ImageIcon blanche;
    private Point coordonnees;

    public Reine(){
        super();

    }

    public void verticalN(int nbCases){
        this.coordonnees.setY(coordonnees.getY()+nbCases);
    }

    public void verticalS(int nbCases){
        this.coordonnees.setY(coordonnees.getY()-nbCases);
    }

    public void horizontalE(int nbCases){
        this.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void horizontalO(int nbCases){
        this.coordonnees.setX(coordonnees.getX()-nbCases);
    }

    public void diagoNE(int nbCases){
        this.coordonnees.setY(coordonnees.getY()+nbCases);
        this.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void diagoNO(int nbCases){
        this.coordonnees.setY(coordonnees.getY()+nbCases);
        this.coordonnees.setX(coordonnees.getX()-nbCases);
    }

    public void diagoSE(int nbCases){
        this.coordonnees.setY(coordonnees.getY()-nbCases);
        this.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void diagoSO(int nbCases){
        this.coordonnees.setY(coordonnees.getY()-nbCases);
        this.coordonnees.setX(coordonnees.getX()-nbCases);
    }
}
