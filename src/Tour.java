import java.util.ArrayList;

/**
 *Created by mlucile on 30/03/16.
 */

public class Tour extends Piece
{

    public Tour(Case caseInitiale, boolean isBlanc)
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

    }

    /**
     * jeuDeCase
     * Liste les cases où la pièce peut se déplacer rendre)
     *
     * @return jeu de case représentant la liste des mouvements possible
     */
    @Override
    public ArrayList<Case> jeuDeCase(Piece pieceABouger)
    {
        return null;
    }
}