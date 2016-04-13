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
        return null;
    }
}