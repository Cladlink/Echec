import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by baptiste on 31/03/16.
 */
public class Cavalier extends Piece
{

    public Cavalier(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/CavalierBlanc.png";
        adresseImageNoire = "img/CavalierNoir.png";
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