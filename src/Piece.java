import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by cladlink on 30/03/16.
 */
public abstract class Piece
{
    protected Case emplacementPiece;
    protected ImageIcon skin = null;
    private boolean isBlanc = false;
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
    public abstract ArrayList<Case> casesAtteignables();


    //getters / setters
    public Case getEmplacementPiece() {
        return emplacementPiece;
    }
    public void setEmplacementPiece(Case emplacementPiece) {
        this.emplacementPiece = emplacementPiece;
    }
    public ImageIcon getSkin() {
        return skin;
    }
    public void setSkin(ImageIcon skin) {
        this.skin = skin;
    }
    public boolean isBlanc() {
        return isBlanc;
    }
    public void setBlanc(boolean blanc) {
        isBlanc = blanc;
    }
    public String getAdresseImageNoire() {
        return adresseImageNoire;
    }
    public void setAdresseImageNoire(String adresseImageNoire) {
        this.adresseImageNoire = adresseImageNoire;
    }
    public String getAdresseImageBlanche() {
        return adresseImageBlanche;
    }
    public void setAdresseImageBlanche(String adresseImageBlanche) {
        this.adresseImageBlanche = adresseImageBlanche;
    }
}
