import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by baptiste on 31/03/16.
 */
public class Pion extends Piece
{

    public Pion(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/PionBlanc.png";
        adresseImageNoire = "img/PionNoir.png";
        skin = isBlanc?new ImageIcon(adresseImageBlanche):new ImageIcon(adresseImageNoire);
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

        return casesAtteignables;
    }
}