import javax.swing.*;

/*
 * Created by cladlink on 30/03/16.
 */
public class Piece
{
    protected boolean vivant;
    protected Point coordonnees;
    protected ImageIcon figure;

    public Piece(int x, int y)
    {
        this.vivant = true;
        this.coordonnees = new Point(x, y);
    }
}
