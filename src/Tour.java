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

    @Override
    public void deplacer(Case origine, Case destination)
    {

    }

    @Override
    public ArrayList<Case> jeuDeCase(Case Origine, Piece pieceABouger)
    {
        return null;
    }
}