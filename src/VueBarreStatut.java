import java.awt.*;

/**
 Created by cladlink on 03/05/16.
 */
public class VueBarreStatut
{
    private Partie partie;
    private VueEchiquier vueEchiquier;

    VueBarreStatut(Partie partie, VueEchiquier vueEchiquier)
    {
        this.partie = partie;
        this.vueEchiquier = vueEchiquier;
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
        g.setColor(Color.black);
        g.fillRect(xBase,yBase-50, vueEchiquier.getWidth(), 40);
        g.setColor(Color.white);
        g.drawString("La barre de statut", xBase + 5, yBase-32);
    }
}
