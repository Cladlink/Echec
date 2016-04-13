import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by baptiste on 31/03/16.
 */
public class Roi extends Piece
{

    public Roi(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/RoiBlanc.png";
        adresseImageNoire = "img/RoiNoir.png";
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