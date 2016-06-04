/**
  Created by Michael on 30/03/16.
 */

class Tour extends Piece
{

    /**
     * Tour
     *
     * @param caseInitiale ()
     * @param isBlanc ()
     */
    Tour(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);

        adresseImageBlanche = "img/pions/TourBlanc.png";
        adresseImageNoire = "img/pions/TourNoir.png";
        adresseImageBlancheProf = "img/BlancProf/TourBlanc.png";
        adresseImageNoireProf = "img/NoirProf/TourNoir.png";
        adresseImageNoireEleve = "img/NoirEleve/TourNoir.png";
        adresseImageBlancheEleve = "img/BlancEleve/TourBlanc.png";

        initChoixSkinPiece();
    }

    /**
     * casesAtteignables
     * Liste les cases où la pièce peut se déplacer rendre)
     *
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