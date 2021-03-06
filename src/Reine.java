/**
  Created by baptiste on 31/03/16.
 */
class Reine extends Piece
{

    /**
     * Reine
     * classe définissant le comportement de la reine
     *
     * @param caseInitiale (placement de départ de la reine)
     * @param isBlanc (couleur de la reine)
     */
    Reine(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/pions/ReineBlanc.png";
        adresseImageNoire = "img/pions/ReineNoir.png";
        adresseImageBlancheProf = "img/BlancProf/ReineBlanc.png";
        adresseImageNoireProf = "img/NoirProf/ReineNoir.png";
        adresseImageNoireEleve = "img/NoirEleve/ReineNoir.png";
        adresseImageBlancheEleve = "img/BlancEleve/ReineBlanc.png";

        initChoixSkinPiece();
    }

    /**
     * casesAtteignables
     * Liste les cases où la pièce peut se déplacer
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
        boolean deplacableNO = true, deplacableSO = true, deplacableSE = true, deplacableNE = true;

        while (deplacableE || deplacableO || deplacableS || deplacableN
                || deplacableNO || deplacableNE || deplacableSE || deplacableSO)
        {
            if ((deplacableS
                    && column + decal <= 7)
                    && (plateau[row][column + decal].getPiece() == null
                    || plateau[row][column + decal].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row][column + decal]);
                if (plateau[row][column + decal].getPiece() != null)
                    deplacableS = false;
            }
            else
                deplacableS = false;

            //test N
            if ((deplacableN
                    && column - decal >= 0)
                    && (plateau[row][column - decal].getPiece() == null
                    || plateau[row][column - decal].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row][column - decal]);
                if (plateau[row][column - decal].getPiece() != null)
                    deplacableN = false;
            }
            else
                deplacableN = false;

            if ((deplacableE
                    && row - decal >= 0)
                    && (plateau[row - decal][column].getPiece() == null
                    || plateau[row - decal][column].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row - decal][column]);
                if (plateau[row - decal][column].getPiece() != null)
                    deplacableE = false;
            }
            else
                deplacableE = false;

            // testO
            if ((deplacableO
                    && row + decal <= 7)
                    && (plateau[row + decal][column].getPiece() == null
                    || plateau[row + decal][column].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row + decal][column]);
                if (plateau[row + decal][column].getPiece() != null)
                    deplacableO = false;
            }
            else
                deplacableO = false;
            if (deplacableSO
                    && (column + decal <= 7
                    && row + decal <= 7)
                    && (plateau[row + decal][column + decal].getPiece() == null
                    || plateau[row + decal][column + decal].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row + decal][column + decal]);
                if (plateau[row + decal][column + decal].getPiece() != null)
                    deplacableSO = false;
            }
            else
                deplacableSO = false;

            if ((deplacableSE
                    && column - decal >= 0
                    && row + decal <= 7)
                    && (plateau[row + decal][column - decal].getPiece() == null
                    || plateau[row + decal][column - decal].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row + decal][column - decal]);
                if (plateau[row + decal][column - decal].getPiece() != null)
                    deplacableSE = false;
            }
            else
                deplacableSE = false;

            if ((deplacableNO
                    && column - decal >= 0
                    && row - decal >= 0)
                    && (plateau[row - decal][column - decal].getPiece() == null
                    || plateau[row - decal][column - decal].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row - decal][column - decal]);
                if (plateau[row - decal][column - decal].getPiece() != null)
                    deplacableNO = false;
            }
            else
                deplacableNO = false;

            if ((deplacableNE
                    && column + decal <= 7
                    && row - decal >= 0)
                    && (plateau[row - decal][column + decal].getPiece() == null
                    || plateau[row - decal][column + decal].getPiece().blanc != blanc))
            {
                casesAtteignables.add(plateau[row - decal][column + decal]);
                if (plateau[row - decal][column + decal].getPiece() != null)
                    deplacableNE = false;
            }
            else
                deplacableNE = false;
            decal++;
        }
    }

    /**
     * peutAtteindreRoi
     * définit si le la reine peut atteindre le roi
     *
     * @param caseRoi (case qui contient le roi)
     * @return (retourne true si le roi peut être atteint)
     */
    @Override
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        int gapRow = Math.abs(emplacementPiece.getRow() - caseRoi.getRow());
        int gapCol = Math.abs(emplacementPiece.getColumn() - caseRoi.getColumn());

        if( gapCol == gapRow
                || emplacementPiece.getColumn() == caseRoi.getColumn()
                || emplacementPiece.getRow() == caseRoi.getRow() )
            return super.peutAtteindreRoi(caseRoi);
        return false;
    }
}