import java.util.ArrayList;

/*
 * Created by baptiste on 31/03/16.
 */
public class Reine extends Piece
{

    public Reine(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
    }

    /**
     * deplacer
     * déplace la pièce d'un point A à un point B
     *
     * @param destination (case où la pièce selectionnée doit se rendre)
     */
    @Override
    public void deplacer(Case destination)
    {
        emplacementPiece = destination;
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
        ArrayList<Case> resCasesAtteignables;
        resCasesAtteignables = new ArrayList<>();

        // Case en bas à gauche
        if( (emplacementPiece.getRow() -  >= 0 || emplacementPiece.getRow() - 1 < 8) && (emplacementPiece.getColumn() - 1 >= 0 || emplacementPiece.getColumn() - 1 < 8) )
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        // Case à gauche
        if (emplacementPiece.getRow()- 1 >= 0 || emplacementPiece.getRow()- 1 < 8)
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        // Case en haut à gauche
        if ((emplacementPiece.getRow()-1>=0 || emplacementPiece.getRow()-1 <8) && (emplacementPiece.getColumn()+1>=0 || emplacementPiece.getColumn()+1 <8))
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        // Case en haut
        if (emplacementPiece.getRow() + 1 >= 0 || emplacementPiece.getRow() + 1 < 8)
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        // Case en haut à droite
        if ((emplacementPiece.getRow()+1>=0 || emplacementPiece.getRow()+1 <8) && (emplacementPiece.getColumn()+1>=0 || emplacementPiece.getColumn()+1 <8))
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        // Case à droite
        if (emplacementPiece.getColumn() + 1 >= 0 || emplacementPiece.getColumn() + 1 < 8)
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        // Case en bas à droite
        if ((emplacementPiece.getRow()+1>=0 || emplacementPiece.getRow()+1 <8) && (emplacementPiece.getColumn()-1>=0 || emplacementPiece.getColumn()-1 <8))
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        // Case en bas
        if (emplacementPiece.getColumn() - 1 >= 0 || emplacementPiece.getColumn() - 1 < 8)
        {
            resCasesAtteignables.add(emplacementPiece);
        }

        return resCasesAtteignables;
    }
}