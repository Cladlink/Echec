import javax.swing.*;

/**
 Created by Michael on 31/03/16.
 */
public class Roi extends Piece
{

    private boolean grandRoque;
    private boolean petitRoque;
    public Roi(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/pions/RoiBlanc.png";
        adresseImageNoire = "img/pions/RoiNoir.png";
        adresseImageNoireProf = "img/NoirProf/RoiNoir.png";
        adresseImageBlancheProf = "img/BlancProf/RoiBlanc.png";
        adresseImageNoireEleve = "img/NoirEleve/RoiNoir.png";
        adresseImageBlancheEleve = "img/BlancEleve/RoiBlanc.png";

        skin = isBlanc? new ImageIcon(adresseImageBlanche) :new ImageIcon(adresseImageNoire);

        grandRoque = petitRoque = true;

        initChoixSkinPiece();
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

        for (int i = -1; i<=1; i++)
        {
            for(int j = -1; j<=1; j++)
            {
                if (row + i >= 0 && row + i <= 7
                        && column + j >= 0 && column + j <= 7
                        && ( plateau[row + i][column + j].getPiece() == null
                        || plateau[row + i][column + j].getPiece().blanc != blanc) )
                    casesAtteignables.add(plateau[row + i][column + j]);
            }
        }
        if ( !emplacementPiece.getBoard().getPartie().isEchec() && ( petitRoque || grandRoque ) ) // ne sert qu'à éviter les tests inutiles si le roque n'est pas possible
        {
            roque();
        }
    }

    /**
     * roque
     * Ajoute aux casesAtteignables les déplacements du petit Roque et du grand Roque si les conditions sont réunies
     *
     */
    private void roque()
    {

        Case[][] plateau = emplacementPiece.getBoard().getPlateau();

        int row = blanc? 7 : 0;
        int column = emplacementPiece.getColumn();


        if (plateau[row][4].getPiece() == null)
        {
            petitRoque = false;
            grandRoque = false;
        }
        else if(plateau[row][7].getPiece() == null)
            petitRoque = false;
        else if(plateau[row][0].getPiece() == null)
            grandRoque = false;

        row = emplacementPiece.getRow();

        // petit roque
        if ( petitRoque )
            if ( plateau[row][column + 1].getPiece() == null
                    && plateau[row][column + 2].getPiece() == null)
                casesAtteignables.add(plateau[row][column+2]);

        // grand roque
        if ( grandRoque )
            if ( plateau[row][column - 1].getPiece() == null
                    && plateau[row][column - 2].getPiece() == null
                    && plateau[row][column - 3].getPiece() == null)
                casesAtteignables.add(plateau[row][column - 3]);

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

        return gapRow <= 1 && gapCol <= 1;
    }
}