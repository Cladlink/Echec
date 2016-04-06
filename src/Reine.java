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
     *
     * déplace la pièce d'un point A à un point B
     * @param origine (case où la pièce selectionnée est placée)
     * @param destination (case où la pièce selectionnée doit se rendre)
     */
    @Override
    public void deplacer(Case origine, Case destination)
    {

    }

    /**
     * jeuDeCase
     * Liste les cases où la pièce peut se déplacer
     *
     * @param Origine (case où la pièce selectionnée est placée)
     * @param pieceABouger (case où la pièce selectionnée doit se rendre)
     * @return jeu de case représentant la liste des mouvements possible
     */
    @Override
    public ArrayList<Case> jeuDeCase(Case Origine, Piece pieceABouger)
    {
        return null;
    }
}