import javax.swing.*;
import java.util.ArrayList;

/**
  Created by cladlink on 30/03/16.
 */
abstract class Piece
{
    protected boolean blanc;
    protected Case emplacementPiece;
    protected ArrayList<Case> casesAtteignables;

    protected ImageIcon skin;
    protected static String adresseImageNoire;
    protected static String adresseImageBlanche;
    protected static String adresseImageNoireProf;
    protected static String adresseImageBlancheProf;
    protected static String adresseImageNoireEleve;
    protected static String adresseImageBlancheEleve;

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
        // les trois autres attributs sont initialisés dans le constructeur de chaque pièces
    }

    public void initChoixSkinPiece()
    {
        if (blanc)
        {
            if (emplacementPiece.getBoard().getPartie().getChoixJoueurBlanc() == 1)
                skin = new ImageIcon(adresseImageBlanche);
            else if (emplacementPiece.getBoard().getPartie().getChoixJoueurBlanc() == 2)
                skin = new ImageIcon(adresseImageBlancheProf);
            else if (emplacementPiece.getBoard().getPartie().getChoixJoueurBlanc() == 3)
                skin = new ImageIcon(adresseImageBlancheEleve);
        }
        else
        {
            if (emplacementPiece.getBoard().getPartie().getChoixJoueurNoir() == 1)
                skin = new ImageIcon(adresseImageNoire);
            else if (emplacementPiece.getBoard().getPartie().getChoixJoueurNoir() == 2)
                skin = new ImageIcon(adresseImageNoireProf);
            else if (emplacementPiece.getBoard().getPartie().getChoixJoueurNoir() == 3)
                skin = new ImageIcon(adresseImageNoireEleve);
        }
    }
    /**
     * deplacer
     *
     * déplace la pièce d'un point A à un point B
     * @param destination (case où la pièce selectionnée doit se rendre)
     */
    public void deplacer(Case destination)
    {
        emplacementPiece.setPiece(null); // on vide la case actuelle
        emplacementPiece = destination; // on définit l'emplacement de la pièce avec la destination
        emplacementPiece.setPiece(this); // on place sur le nouvelle emplacement la pièce courante
    }

    /**
     * peutAtteindreRoi
     * Teste si une pièce peut atteindre le roi
     *
     * @param caseRoi case qui contient le roi
     * @return (return true si caseRoi peut être atteint)
     */
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        casesAtteignables();
        return casesAtteignables != null && this.casesAtteignables.contains(caseRoi);
    }

    /**
     * deplacementPossible
     * Récupère les casesAtteignables
     * et on teste avec un déplacement fictif si le roi est mis en danger.
     *
     */
    public void deplacementPossible()
    {
        Case emplacementPieceDeBase;
        Piece tempPiece;

        ArrayList<Piece> piecesEnJeu;
        if (blanc)
            piecesEnJeu = emplacementPiece.getBoard().getPartie().getPiecesNoiresPlateau();
        else
            piecesEnJeu = emplacementPiece.getBoard().getPartie().getPiecesBlanchesPlateau();


        for (int i = 0; i < casesAtteignables.size(); i++)
        {
            emplacementPieceDeBase = emplacementPiece; // pour ramener le roi à sa place après déplacement fictif
            // on stock la pièce de la case destination sera null si la case ne contient pas de pièce
            tempPiece = casesAtteignables.get(i).getPiece();
            // on déplace la pièce pour tester si le roi adverse est mis en danger
            deplacer(casesAtteignables.get(i));
            if (tempPiece != null)
                piecesEnJeu.remove(tempPiece);

            // on teste si les pièces adverses peuvent prenre le roi en simulant le déplacement
            if ( emplacementPiece.getBoard().getPartie().isEchec() )
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

    /**
     * CasesAtteignables
     * Doit être définie pour chaque pièces en fonction de sa façon de se déplacer
     *
     */
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