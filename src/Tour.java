import javax.swing.*;
import java.util.ArrayList;

/**
  Created by Michael on 30/03/16.
 */

public class Tour extends Piece
{

    public Tour(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/BlancProf/TourBlanc.png";
        adresseImageNoire = "img/NoirProf/TourNoir.png";
        skin = isBlanc?new ImageIcon(adresseImageBlanche):new ImageIcon(adresseImageNoire);
    }

    /**
     * casesAtteignables
     * Liste les cases où la pièce peut se déplacer rendre)
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

        int decal = 1;
        boolean deplacableN = true, deplacableS = true, deplacableO = true, deplacableE = true;
        while (deplacableE || deplacableO || deplacableS || deplacableN)
        {
            //test S
            if (deplacableS
                    && column + decal <=7
                    && (
                        plateau[row][column + decal].getPiece() == null
                    || plateau[row][column + decal].getPiece().blanc != blanc
                    ))
            {
                casesAtteignables.add(plateau[row ][column + decal]);
                if (plateau[row][column + decal].getPiece() != null)
                    deplacableS = false;
            }
            else
                deplacableS = false;

            //test N
            if (deplacableN
                    && column - decal >= 0
                    && (
                        plateau[row][column - decal].getPiece() == null
                    || plateau[row][column - decal].getPiece().blanc != blanc
                    ))
            {
                casesAtteignables.add(plateau[row ][column - decal]);

                if (plateau[row][column - decal].getPiece() != null)
                    deplacableN = false;
            }
            else
                deplacableN = false;

            //test E
            if (deplacableE
                    && row - decal >= 0
                    && (
                        plateau[row - decal][column].getPiece() == null
                    || plateau[row - decal][column].getPiece().blanc != blanc
                    ))
            {
                casesAtteignables.add(plateau[row  - decal][column]);
                if (plateau[row - decal][column].getPiece() != null)
                    deplacableE = false;
            }
            else
                deplacableE = false;

            // testO
            if (deplacableO
                    && row + decal <= 7
                    && (
                        plateau[row + decal][column].getPiece() == null
                    || plateau[row + decal][column].getPiece().blanc != blanc )
                    )
            {
                casesAtteignables.add(plateau[row + decal][column]);
                if (plateau[row + decal][column].getPiece() != null)
                    deplacableO = false;
            }
            else
                deplacableO = false;

            decal++;
        }
    }

    @Override
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        if (emplacementPiece.getColumn() == caseRoi.getColumn()
                || emplacementPiece.getRow() == caseRoi.getRow())
            return super.peutAtteindreRoi(caseRoi);
        return false;
    }
}