import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by cladlink on 30/03/16.
 */
public abstract class Piece
{
    protected Case emplacementPiece;
    protected ImageIcon skin = null;
    protected boolean blanc = false;
    protected String adresseImageNoire = null;
    protected String adresseImageBlanche = null;

    /**
     * Pièce (constructeur)
     *
     * @param caseInitiale (place la pièce à la première case qu'elle occupera en début de partie)
     * @param blanc (définit si la pièce sera blanche ou noire)
     */
    public Piece(Case caseInitiale, boolean blanc)
    {
        this.blanc = blanc;
        this.emplacementPiece = caseInitiale;
    }
    /**
     * deplacer
     *
     * déplace la pièce d'un point A à un point B
     * @param destination (case où la pièce selectionnée doit se rendre)
     */
    public void deplacer( Case destination)
    {
        emplacementPiece.setPiece(null);
        emplacementPiece = destination;
        emplacementPiece.setPiece(this);
    }

    public abstract boolean peutAtteindreRoi(Case caseRoi);
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
        return blanc;
    }
    public void setBlanc(boolean blanc) {
        this.blanc = blanc;
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
