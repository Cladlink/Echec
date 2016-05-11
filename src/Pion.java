import javax.swing.*;
import java.util.ArrayList;

/**
  Created by Michael on 31/03/16.
 */
public class Pion extends Piece
{

    public Pion(Case caseInitiale, boolean blanc)
    {
        super(caseInitiale, blanc);
        adresseImageBlanche = "img/BlancProf/PionBlanc.png";
        adresseImageNoire = "img/NoirProf/PionNoir.png";
        skin = blanc? new ImageIcon(adresseImageBlanche) : new ImageIcon(adresseImageNoire);
    }

    /**
     * casesAtteignables
     * Liste les cases où la pièce peut se déplacer
     *
     * @return jeu de case représentant la liste des mouvements possible
     */
    @Override
    public void casesAtteignables()
    {

        casesAtteignables.clear();
        int decal = blanc?-1:1;

        Case[][] plateau = emplacementPiece.getBoard().getPlateau();
        int row = emplacementPiece.getRow();
        int column = emplacementPiece.getColumn();

        // mouvement de deux cases est il possible ?
        if( ( row == 6 && blanc ) || ( row == 1 && !blanc ) )
            if( plateau[row + decal * 2][column].getPiece() == null
                    && plateau[row + decal][column].getPiece() == null)
                casesAtteignables.add(plateau[row + decal*2][column]);

        // mouvement d'une case
        if ( row + decal <= 7 && row + decal >=0
               && plateau[row + decal][column].getPiece() == null )
            casesAtteignables.add(plateau[row + decal][column]);

        // la pièce peut elle manger à  gauche ?
        if (column -1 >= 0 && row + decal <= 7 && row + decal >=0
                && plateau[row + decal][column-1].getPiece() != null
                && plateau[row + decal][column-1].getPiece().blanc != blanc)
            casesAtteignables.add(plateau[row + decal][column - 1]);

        // la pièce peut elle manger à  droite ?
        if (column + 1 <= 7 && row + decal <= 7 && row + decal >=0
                && plateau[row + decal][column + 1].getPiece() != null
                && plateau[row + decal][column + 1].getPiece().blanc != blanc)
            casesAtteignables.add(plateau[row + decal][column + 1]);
    }

    @Override
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        int decal = blanc?-1:1;

        int row = emplacementPiece.getRow();
        int col = emplacementPiece.getColumn();
        int rowRoi = caseRoi.getRow();
        int colRoi = caseRoi.getColumn();

        return (row + decal == rowRoi && col + 1 == colRoi)
                || (row + decal == rowRoi && col - 1 == colRoi);
    }

    /**
     * promotion
     * dans le model il est controlé si un pion est en ligne 7 ou 0. Si c'est le cas on fait appel à cette méthode
     *
     * @param choix (represente le choix fait par le joueur : 1 Cavalier, 2 Tour, 3 Fou, 4 Reine)
     */
    void promotion(int choix)
    {
        ArrayList<Piece> piecesCimetiere;
        ArrayList<Piece> piecesEnJeu;

        if(blanc)
        {
            piecesEnJeu = emplacementPiece.getBoard().getPartie().getPiecesBlanchesPlateau();
            piecesCimetiere = emplacementPiece.getBoard().getPartie().getCimetiereBlanc();
        }
        else
        {
            piecesEnJeu =  emplacementPiece.getBoard().getPartie().getPiecesNoiresPlateau();
            piecesCimetiere = emplacementPiece.getBoard().getPartie().getCimetiereNoir();
        }

        piecesEnJeu.remove(this);
        piecesCimetiere.add(this);

        emplacementPiece.setPiece(null);
        switch (choix)
        {
            case 1 :
                emplacementPiece.setPiece(new Cavalier(this.emplacementPiece, this.blanc));
                break;
            case 2 :
                emplacementPiece.setPiece(new Tour(this.emplacementPiece, this.blanc));
                break;
            case 3 :
                emplacementPiece.setPiece(new Fou(this.emplacementPiece, this.blanc));
                break;
            case 4 :
                emplacementPiece.setPiece(new Reine(this.emplacementPiece, this.blanc));
                break;
        }
        piecesEnJeu.add(emplacementPiece.getPiece());
    }
}