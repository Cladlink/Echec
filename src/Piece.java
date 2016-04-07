import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by cladlink on 30/03/16.
 */
public abstract class Piece
{
    protected Case emplacementPiece;
    protected ImageIcon skin = null;
    protected boolean isBlanc = false;
    protected String adresseImageNoire = null; // mettre les images ici quand on les auas
    protected String adresseImageBlanche = null;

    /**
     * Pièce (constructeur)
     *
     * @param caseInitiale (place la pièce à la première case qu'elle occupera en début de partie)
     * @param isBlanc (définit si la pièce sera blanche ou noire)
     */
    public Piece(Case caseInitiale, boolean isBlanc)
    {
        this.isBlanc = isBlanc;
        if (isBlanc)
            skin = new ImageIcon(adresseImageBlanche);
        else
            skin = new ImageIcon(adresseImageNoire);
        this.emplacementPiece = caseInitiale;
    }

    public abstract void deplacer( Case destination);
    public abstract ArrayList<Case> jeuDeCase();
}
