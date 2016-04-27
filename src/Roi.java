import javax.swing.*;
import java.util.ArrayList;

/**
 Created by Michael on 31/03/16.
 */
public class Roi extends Piece
{
    public Roi(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/RoiBlanc.png";
        adresseImageNoire = "img/RoiNoir.png";
        skin = isBlanc? new ImageIcon(adresseImageBlanche) :new ImageIcon(adresseImageNoire);
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
        int i, j;

        Case[][] plateau = emplacementPiece.getBoard().getPlateau();
        int row = emplacementPiece.getRow();
        int column = emplacementPiece.getColumn();

        for (i = -1; i<=1; i++)
        {
            for( j = -1; j<=1; j++)
            {
                if (row + i >= 0 && row + i <= 7
                        && column + j >= 0 && column + j <= 7
                        && ( plateau[row + i][column + j].getPiece() == null
                        || plateau[row + i][column + j].getPiece().blanc != blanc) )
                    casesAtteignables.add(plateau[row + i][column + j]);
            }
        }
        deplacementPossible();
    }

    /**
     * peutAtteindreRoi
     * une pièce peut elle atteindre le roi ?
     *
     * @param caseRoi (case qui contient le roi qui peut être déplacé)
     * @return (true si la pièce peut atteindre le roi)
     */
    @Override
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        int gapRow = Math.abs( emplacementPiece.getRow() - caseRoi.getRow() );
        int gapCol = Math.abs( emplacementPiece.getColumn() - caseRoi.getColumn() );
        if ( gapRow<=1 && gapCol<=1 )
            return true;
        return false;
    }
}