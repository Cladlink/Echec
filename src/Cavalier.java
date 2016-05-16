/**
  Created by baptiste on 31/03/16.
 */
class Cavalier extends Piece
{

    Cavalier(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/pions/CavalierBlanc.png";
        adresseImageNoire = "img/pions/CavalierNoir.png";
        adresseImageBlancheProf = "img/BlancProf/CavalierBlanc.png";
        adresseImageNoireProf = "img/NoirProf/CavalierNoir.png";
        adresseImageNoireEleve = "img/NoirEleve/CavalierNoir.png";
        adresseImageBlancheEleve = "img/BlancEleve/CavalierBlanc.png";

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

        // test position (+2;-1)
        if (( column - 1 >= 0
                && row + 2 <= 7 )
                && ( plateau[row + 2][column - 1].getPiece() == null
                || plateau[row + 2][column - 1].getPiece().blanc != blanc ))
            casesAtteignables.add(plateau[row + 2][column - 1]);

        // test position (-2;+1)
        if (column + 1 <= 7
                && row - 2 >= 0
                && (plateau[row - 2][column + 1].getPiece() == null
                || plateau[row - 2][column + 1].getPiece().blanc != blanc))
            casesAtteignables.add(plateau[row - 2][column + 1]);

        // test position (+1;-2)
        if (column - 2 >= 0
                && row + 1 <= 7
                   && (plateau[row + 1][column - 2].getPiece() == null
                || plateau[row + 1][column - 2].getPiece().blanc != blanc))
            casesAtteignables.add(plateau[row + 1][column - 2]);

        // test position (-1;+2)
        if (column + 2 <= 7
                && row - 1 >= 0
                && (plateau[row - 1][column + 2].getPiece() == null
                || plateau[row - 1][column + 2].getPiece().blanc != blanc))
            casesAtteignables.add(plateau[row - 1][column + 2]);

        // test position (-2;-1)
        if (column - 1 >= 0
                && row - 2 >= 0
                && (plateau[row - 2][column - 1].getPiece() == null
                || plateau[row - 2][column - 1].getPiece().blanc != blanc))
            casesAtteignables.add(plateau[row - 2][column - 1]);

        // test position (-1;-2)
        if (column - 2 >= 0
                && row - 1 >= 0
                && (plateau[row - 1][column - 2].getPiece() == null
                || plateau[row - 1][column - 2].getPiece().blanc != blanc))
            casesAtteignables.add(plateau[row - 1][column - 2]);

        // test position (+1;+2)
        if (column + 2 <= 7
                && row + 1 <= 7
                && (plateau[row + 1][column + 2].getPiece() == null
                || plateau[row + 1][column + 2].getPiece().blanc != blanc))
            casesAtteignables.add(plateau[row + 1][column + 2]);

        // test position (+2;+1)
        if (column + 1 <= 7
                && row + 2 <= 7
                && (plateau[row + 2][column + 1].getPiece() == null
                || plateau[row + 2][column + 1].getPiece().blanc != blanc))
            casesAtteignables.add(plateau[row + 2][column + 1]);
    }

    @Override
    public boolean peutAtteindreRoi(Case caseRoi)
    {
        int gapRow = Math.abs( emplacementPiece.getRow() - caseRoi.getRow() );
        int gapCol = Math.abs( emplacementPiece.getColumn() - caseRoi.getColumn() );

        return (gapRow == 2 && gapCol == 1) || (gapRow == 1 && gapCol == 2);
    }
}