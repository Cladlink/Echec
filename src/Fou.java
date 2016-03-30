import javax.swing.*;

/**
 * Created by mlucile on 30/03/16.
 */
public class Fou extends Piece
{
    private boolean noir;
    private boolean vivant;
    private int valeur;
    private ImageIcon image;
    private Coordonnees coordonnees;

    public Fou()
    {
        this.noir = true;
        this.vivant = true;
        this.valeur = 3; // J'ai trouvé ca sur Wikipédia
    }
}