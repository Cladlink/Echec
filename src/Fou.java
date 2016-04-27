import javax.swing.*;
import java.util.ArrayList;

/**
 Created by baptiste on 31/03/16.
 */
public class Fou extends Piece
{

    public Fou(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/FouBlanc.png";
        adresseImageNoire = "img/FouNoir.png";
        skin = isBlanc?new ImageIcon(adresseImageBlanche):new ImageIcon(adresseImageNoire);

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
        Case[][] plateau = emplacementPiece.getBoard().getPlateau();
        int row = emplacementPiece.getRow();
        int column = emplacementPiece.getColumn();

        /*int end = row<col?7-col:7-row; // plus on est près du bord droit ou haut/bas, moins il y a de case possibles.
        for(int i=1;i<=end;i++)
        {
            if ( ( plateau[row+i][col+i].getPiece() == null)
                    || (plateau[row+i][col+i].getPiece().blanc != blanc) )
                casesAtteignables.add(plateau[row+i][col+i]);
            else break; // arrêt boucle
        }
        end = row>col?col:row; // plus on est près du bord droit ou haut/bas, moins il y a de case possibles.
        for(int i=1;i<=end;i++)
        {
            if ( ( plateau[row-i][col-i].getPiece() == null)
                    || (plateau[row-i][col-i].getPiece().blanc != blanc) )
                casesAtteignables.add(plateau[row-i][col-i]);
            else break; // arrêt boucle
        }

        end = row<col?col:7-row; // plus on est près du bord droit ou haut/bas, moins il y a de case possibles.
        for(int i=1;i<=end;i++)
        {
            if ( ( plateau[row+i][col-i].getPiece() == null)
                    || (plateau[row+i][col-i].getPiece().blanc != blanc) )
                casesAtteignables.add(plateau[row+i][col-i]);
            else break; // arrêt boucle
        }

        end = row>col?7-col:row; // plus on est près du bord droit ou haut/bas, moins il y a de case possibles.
        for(int i=1;i<=end;i++)
        {
            if ( ( plateau[row-i][col+i].getPiece() == null)
                    || (plateau[row-i][col+i].getPiece().blanc != blanc) )
                casesAtteignables.add(plateau[row-i][col+i]);
            else break; // arrêt boucle
        }

*/
        int decal = 1;
        boolean deplacableNO = true, deplacableSO = true, deplacableSE = true, deplacableNE = true;
        while (deplacableNE || deplacableSE || deplacableSO || deplacableNO)
        {
            // test NO
            if (deplacableSO
                    &&( column + decal <= 7
                    && row + decal <= 7 )
                    && ( plateau[row + decal][column + decal].getPiece() == null
                    || plateau[row + decal][column + decal].getPiece().blanc != blanc ))
            {
                casesAtteignables.add(plateau[row + decal][column + decal]);
                if (plateau[row + decal][column + decal].getPiece() != null)
                    deplacableSO = false;
            }
            else
                deplacableSO = false;

            // test SE
            if ((deplacableSE
                    && column - decal >= 0
                    && row + decal <= 7 )
                    && ( plateau[row + decal][column - decal].getPiece() == null
                    || plateau[row + decal][column - decal].getPiece().blanc != blanc ))
            {
                casesAtteignables.add(plateau[row + decal][column - decal]);
                if(plateau[row + decal][column - decal].getPiece() != null)
                    deplacableSE = false;
            }
            else
                deplacableSE = false;

            //testNO
                if ((deplacableNO
                    && column - decal >= 0
                    && row - decal >= 0 )
                    && ( plateau[row - decal][column - decal].getPiece() == null
                    || plateau[row - decal][column - decal].getPiece().blanc != blanc ))
            {
                casesAtteignables.add(plateau[row - decal][column - decal]);
                if(plateau[row - decal][column - decal].getPiece() != null)
                    deplacableNO = false;
            }
            else
                deplacableNO = false;

            // test NE
            if ((deplacableNE
                    && column + decal <= 7
                    && row - decal >= 0 )
                    && ( plateau[row - decal][column + decal].getPiece() == null
                    || plateau[row - decal][column + decal].getPiece().blanc != blanc ))
            {
                casesAtteignables.add(plateau[row - decal][column + decal]);
                if(plateau[row - decal][column + decal].getPiece() != null)
                    deplacableNE = false;
            }
            else
                deplacableNE = false;

            decal++;
        }
    }

    @Override
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        int gapRow = Math.abs(emplacementPiece.getRow() - caseRoi.getRow());
        int gapCol = Math.abs(emplacementPiece.getColumn() - caseRoi.getColumn());
        if(gapCol == gapRow)
            return super.peutAtteindreRoi(caseRoi);
        return false;
    }

}