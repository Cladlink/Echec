import javax.swing.*;
import java.util.ArrayList;

/**
 *Created by mlucile on 30/03/16.
 */

public class Tour extends Piece
{

    public Tour(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/TourBlanc.png";
        adresseImageNoire = "img/TourNoir.png";
        skin = isBlanc?new ImageIcon(adresseImageBlanche):new ImageIcon(adresseImageNoire);
    }

    /**
     * casesAtteignables
     * Liste les cases où la pièce peut se déplacer rendre)
     *
     * @return jeu de case représentant la liste des mouvements possible
     */
    @Override
    public ArrayList<Case> casesAtteignables()
    {
        return null;
    }
}