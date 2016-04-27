import javax.swing.*;
import java.util.ArrayList;

/**
  Created by cladlink on 30/03/16.
 */
public abstract class Piece
{
    protected Case emplacementPiece;
    protected ImageIcon skin = null;
    protected boolean blanc = false;
    protected String adresseImageNoire = null;
    protected String adresseImageBlanche = null;
    protected ArrayList<Case> casesAtteignables = null;

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
        this.casesAtteignables = new ArrayList<>();
    }
    /**
     * deplacer
     *
     * déplace la pièce d'un point A à un point B
     * @param destination (case où la pièce selectionnée doit se rendre)
     */
    public void deplacer(Case destination)
    {
        emplacementPiece.setPiece(null);
        emplacementPiece = destination;
        emplacementPiece.setPiece(this);
    }

    /**
     * peutAtteindreRoi
     * Test si une pièce peut atteindre le roi
     *
     * @param caseRoi case qui contient le roi
     * @return (return true si caseRoi peut être atteint)
     */
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        casesAtteignables();
        if (casesAtteignables != null)
            for (int i = 0; i < this.casesAtteignables.size(); i++)
                if (this.casesAtteignables.contains(caseRoi))
                    return true;
        return false;
    }

    public void deplacementPossible()
    {
        Case emplacementPieceDeBase;
        Piece tempPiece;
        ArrayList<Piece> piecesEnJeu;
        Case monRoi;
        int i;
        if (blanc)
            piecesEnJeu = emplacementPiece.getBoard().getPartie().getPiecesNoiresPlateau();
        else
            piecesEnJeu = emplacementPiece.getBoard().getPartie().getPiecesBlanchesPlateau();

        for (i = 0; i < casesAtteignables.size(); i++)
        {
            emplacementPieceDeBase = emplacementPiece; // pour ramener le roi à sa place
            tempPiece = casesAtteignables.get(i).getPiece();
            deplacer(casesAtteignables.get(i)); // on déplace la pièce pour faire le test correctement
            if (tempPiece != null)
                piecesEnJeu.remove(tempPiece);

            monRoi = blanc?
                    emplacementPiece.getBoard().getRoiBlanc().emplacementPiece
                    :emplacementPiece.getBoard().getRoiNoir().emplacementPiece;
            // on teste si les pièces adverses peuvent prenre le roi en simulant le déplacement
            if ( emplacementPiece.getBoard().getPartie().isEchec(monRoi) )
            {
                casesAtteignables.remove(i);
                i--;
            }
            // on remet la pièce en place
            if (tempPiece == null)
                emplacementPiece.setPiece(null);
            else
            {
                emplacementPiece.setPiece(tempPiece);
                piecesEnJeu.add(tempPiece);
            }
            emplacementPiece = emplacementPieceDeBase;
            emplacementPiece.setPiece(this);
        }
    }

    public abstract void casesAtteignables();

    //getters & setters
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