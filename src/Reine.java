import javax.swing.*;
import java.util.ArrayList;

/*
 * Created by baptiste on 31/03/16.
 */
public class Reine extends Piece
{

    public Reine(Case caseInitiale, boolean isBlanc)
    {
        super(caseInitiale, isBlanc);
        adresseImageBlanche = "img/ReineBlanc.png";
        adresseImageNoire = "img/ReineNoir.png";
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