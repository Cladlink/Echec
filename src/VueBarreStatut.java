import java.awt.*;

/**
 Created by cladlink on 03/05/16.
 */
class VueBarreStatut
{
    private String statutText;
    private Partie partie;
    private VueEchiquier vueEchiquier;

    VueBarreStatut(Partie partie, VueEchiquier vueEchiquier)
    {
        this.partie = partie;
        this.vueEchiquier = vueEchiquier;
        this.statutText = "";
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
        g.drawString(statutText, xBase + 5, yBase-32);
    }

    public String getStatutText() {
        return statutText;
    }
    public void setStatutText(String statutText) {
        this.statutText = statutText;
    }
}
