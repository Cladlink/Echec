/**
 Created by baptiste on 31/03/16.
 */

class Fou extends Piece
{

    /**
     * Fou
     * Classe représentant le fou
     *
     * @param caseInitiale (case de début de partie)
     * @param isBlanc (couleur du fou)
     */
    Fou(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/pions/FouBlanc.png";
        adresseImageNoire = "img/pions/FouNoir.png";
        adresseImageBlancheProf = "img/BlancProf/FouBlanc.png";
        adresseImageNoireProf = "img/NoirProf/FouNoir.png";
        adresseImageNoireEleve = "img/NoirEleve/FouNoir.png";
        adresseImageBlancheEleve = "img/BlancEleve/FouBlanc.png";

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
        boolean deplacableNO = true, deplacableSO = true, deplacableSE = true, deplacableNE = true;

        while (deplacableNE || deplacableSE || deplacableSO || deplacableNO)
        {
            // test NO
            if (deplacableSO
                    &&( column + decal <= 7
                    && row + decal <= 7
                    && (
                        plateau[row + decal][column + decal].getPiece() == null
                    || plateau[row + decal][column + decal].getPiece().blanc != blanc )
                    ))
            {
                casesAtteignables.add(plateau[row + decal][column + decal]);
                if (plateau[row + decal][column + decal].getPiece() != null)
                    deplacableSO = false;
            }
            else
                deplacableSO = false;

            // test SE
            if (deplacableSE
                    && column - decal >= 0
                    && row + decal <= 7
                    && (
                        plateau[row + decal][column - decal].getPiece() == null
                    || plateau[row + decal][column - decal].getPiece().blanc != blanc )
                    )
            {
                casesAtteignables.add(plateau[row + decal][column - decal]);
                if(plateau[row + decal][column - decal].getPiece() != null)
                    deplacableSE = false;
            }
            else
                deplacableSE = false;

            //testNO
                if (deplacableNO
                    && column - decal >= 0
                    && row - decal >= 0
                    && (
                        plateau[row - decal][column - decal].getPiece() == null
                    || plateau[row - decal][column - decal].getPiece().blanc != blanc )
                    )
            {
                casesAtteignables.add(plateau[row - decal][column - decal]);
                if(plateau[row - decal][column - decal].getPiece() != null)
                    deplacableNO = false;
            }
            else
                deplacableNO = false;

            // test NE
            if (deplacableNE
                    && column + decal <= 7
                    && row - decal >= 0
                    && (
                        plateau[row - decal][column + decal].getPiece() == null
                    || plateau[row - decal][column + decal].getPiece().blanc != blanc )
                    )
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

    /**
     * peutAtteindreRoi
     * cette méthode détermine si le fou peut atteindre le roi
     *
     * @param caseRoi (case qui contient le roi)
     * @return (retourne true si peut atteindre le roi)
     */
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

        /*
        Solution Domas, interessante mais peu lisible à mon gout (Michael)
        int end = row<col?7-col:7-row; // plus on est près du bord droit ou haut/bas, moins il y a de case possibles.
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