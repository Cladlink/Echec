import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 Created by cladlink on 03/05/16.
 */
public class VueGraveyard
{
    private Partie partie;
    private ImageIcon bgCimetiere;
    private boolean isBlanc;

    VueGraveyard(Partie partie, boolean isBlanc)
    {
        this.partie = partie;
        this.bgCimetiere = new ImageIcon("img/cimetiere.png");
        this.isBlanc = isBlanc;
    }
    //cimetiere

    void paintMe(Graphics g, int xBase, int yBase)
    {
        int i, pion=0, pasPion=0;

        //cimetiere blanc
        g.drawImage(bgCimetiere.getImage(), xBase, yBase, 200, 500, null);//110 150
        ArrayList<Piece> cimetiere;
        
        if (isBlanc)
            cimetiere = partie.getCimetiereBlanc();
        else
            cimetiere = partie.getCimetiereNoir();
        
        for(i = 0; i<cimetiere.size(); i++)
        {
            if (cimetiere.get(i) instanceof Pion)
            {
                g.drawImage(cimetiere.get(i).skin.getImage(), xBase + 10, yBase +50 + 50 * pion, 100, 100, null);
                pion++;
            }
            else
            {
                g.drawImage(cimetiere.get(i).skin.getImage(),xBase + 60, yBase + 50 + 50 * pasPion, 100, 100, null);
                pasPion++;
            }
        }
    }

}
