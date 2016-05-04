import java.awt.*;

/**
 Created by cladlink on 03/05/16.
 */
public class VueTimer
{
    private Partie partie;
    private boolean isBlanc;
    private int minute, seconde;

    VueTimer(Partie partie, boolean isBlanc)
    {
        this.partie = partie;
        this.isBlanc = isBlanc;
    }
    /**
     * PaintMe
     * Paint l'objet graphique
     *
     * @param g (boite à outil servant à peindre des éléments)
     * @param xBase (axe x de l'objet)
     * @param yBase (axe y de l'objet)
     */
    void paintMe(Graphics g, int xBase, int yBase)
    {
        if ( partie.getModePartie() == 1 || partie.getModePartie() == 2 )
        {
            g.setColor(Color.BLUE);
            g.fillRect(xBase, yBase, 100, 50);// 160 80 || 1110 80
            g.fillRect(xBase, yBase, 100, 50);
            g.setColor(Color.BLACK);
            g.drawString(minute + " : " + seconde, xBase+30, yBase+30);
        }
    }
}
