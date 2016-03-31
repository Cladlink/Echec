import javax.swing.*;

/*
 * Created by cladlink on 30/03/16.
 */
public class Roi extends Piece
{
    private ImageIcon blanche;
    private ImageIcon noire;
    private Point coordonees;

    // Todo plus tard
    public Roi(){
        super();

    }

    public void verticalN(){ this.coordonees.setY(coordonees.getY()+1); }
    public void verticalS(){ this.coordonees.setY(coordonees.getY()-1); }
    public void horizontalO() { this.coordonees.setX(coordonees.getX()-1); }
    public void horizontalE() { this.coordonees.setX(coordonees.getX()+1); }


}
