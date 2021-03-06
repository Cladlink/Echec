import java.awt.*;

/**
 Created by cladlink on 03/05/16.
 */
class VueBarreStatut
{
    private String statutText;
    private String joueurBlanc = "", joueurNoir = "";
    private VueEchiquier vueEchiquier;

    /**
     * VueBarreStatut
     * affiche le nom des joueurs et la case survolée par la souris
     *
     * @param partie (model)
     * @param vueEchiquier (jpanel qui affiche la barre de statut)
     */
    VueBarreStatut(Partie partie, VueEchiquier vueEchiquier)
    {
        this.vueEchiquier = vueEchiquier;
        this.statutText = "";
        this.joueurBlanc += "joueur Blanc : " + partie.getJoueurBlanc().getPseudo();
        this.joueurNoir += " joueur Noir : " + partie.getJoueurNoir().getPseudo();
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

        g.drawString(statutText, xBase + 650, yBase-32);
        g.drawString(joueurBlanc, xBase + 5, yBase -32);
        g.drawString(joueurNoir, xBase + 1200, yBase -32);
    }

    /**
     * setStatutText
     * met à jour le texte de la barre de statut
     * @param statutText (texte de la barre de statut)
     */
    void setStatutText(String statutText) {
        this.statutText = statutText;
    }
}
