import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by baptiste on 31/03/16.
 */
public class Fou extends Piece
{

    public Fou(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/FouBlanc.png";
        adresseImageNoire = "img/FouNoir.png";
        if(isBlanc)
            skin = new ImageIcon(adresseImageBlanche);
        else
            skin = new ImageIcon(adresseImageNoire);

    }

    /**
     * deplacer
     *
     * déplace la pièce d'un point A à un point B
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
        return null;
    }
}