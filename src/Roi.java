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
    public ArrayList<Case> casesAtteignables()
    {
        ArrayList<Case> casesAtteignables = new ArrayList<>();
        ArrayList<Piece> piecesEnJeu;
        int i, j;

        if (blanc)
            piecesEnJeu = emplacementPiece.getBoard().getPartie().getPiecesNoiresPlateau();
        else
            piecesEnJeu = emplacementPiece.getBoard().getPartie().getPiecesBlanchesPlateau();

        Case[][] plateau = emplacementPiece.getBoard().getPlateau();
        int row = emplacementPiece.getRow();
        int column = emplacementPiece.getColumn();
        Case temp, temp2;

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
        for (i = 0; i < casesAtteignables.size(); i++)
        {
            for (j = 0; j < piecesEnJeu.size(); j++)
            {
                temp = casesAtteignables.get(i);
                temp2 = emplacementPiece;
                // on déplace la pièce pour faire le test correctement
                emplacementPiece.setPiece(null);
                emplacementPiece = casesAtteignables.get(i);
                emplacementPiece.setPiece(this);
                // on teste si la les pièces adverses peuvent prenre le roi en simulant le déplacement
                if (piecesEnJeu.get(j).peutAtteindreRoi(emplacementPiece))
                {
                    casesAtteignables.set(i, null);
                    emplacementPiece.setPiece(null);
                    emplacementPiece = temp2;
                    emplacementPiece.setPiece(this);
                    break;
                }
                // on remet la pièce en place
                emplacementPiece.setPiece(null);
                emplacementPiece = temp2;
                emplacementPiece.setPiece(this);
                casesAtteignables.set(i, temp);
            }
        }

        // le déplacement est il possible pour un roi ?*/
        return casesAtteignables;
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