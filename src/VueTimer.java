import java.awt.*;

/**
 Created by cladlink on 03/05/16.
 */
class VueTimer
{
    private Partie partie;
    private boolean isBlanc;
    private int minute, seconde;

    /**
     * VueTimer
     * définit l'affichage du temps d'une partie avec un affichage digital
     *
     * @param partie (model)
     * @param isBlanc (true si il s'agit du timer blanc, false pour noir)
     */
    VueTimer(Partie partie, boolean isBlanc)
    {
        this.partie = partie;
        this.isBlanc = isBlanc;
        if (partie.getModePartie() == 2)
        {
            minute = 0;
            seconde = 30;
        }
        else if (partie.getModePartie() == 3)
        {
            minute = 5;
            seconde = 0;
        }
    }

    /**
     * PaintMe
     * Paint l'objet graphique
     *
     * @param g (boite à outil servant à peindre des éléments)
     * @param xBase (axe x de l'objet)
     * @param yBase (axe y de l'objet)
     */
    void paintMe(Graphics g, int xBase, int yBase, Vue vue)
    {
        int xBaseDigit = xBase+20;
        int yBaseDigit = yBase+25;

        if ( partie.getModePartie() == 2
                || partie.getModePartie() == 3 )
        {
            g.setColor(Color.WHITE);
            g.fillRect(xBase, yBase, 100, 50);// 160 80 || 1110 80
            g.fillRect(xBase, yBase, 100, 50);
            //set digit
            DigitalNumber digitDizaineMinute = new DigitalNumber(xBaseDigit, yBaseDigit, 1);
            DigitalNumber digitMinute = new DigitalNumber(xBaseDigit + 15, yBaseDigit, 1);
            DigitalNumber digitDizaineSeconde = new DigitalNumber(xBaseDigit + 40, yBaseDigit, 1);
            DigitalNumber digitSeconde = new DigitalNumber(xBaseDigit + 55, yBaseDigit, 1);
            //-minutes
            if (minute>=10)
            {
                digitDizaineMinute.setNumber(minute/10);
                digitMinute.setNumber(minute%10);
            }
            else{
                digitDizaineMinute.setNumber(0);
                digitMinute.setNumber(minute%10);
            }
            //seconde
            if (seconde>=10)
            {
                digitDizaineSeconde.setNumber(seconde/10);
                digitSeconde.setNumber(seconde%10);
            }
            else
            {
                digitDizaineSeconde.setNumber(0);
                digitSeconde.setNumber(seconde%10);
            }
            digitDizaineMinute.drawNumber(g);
            digitMinute.drawNumber(g);
            digitDizaineSeconde.drawNumber(g);
            digitSeconde.drawNumber(g);
        }
    }

    // getters and setters
    public Partie getPartie() { return partie; }
    public void setPartie(Partie partie) { this.partie = partie; }
    public boolean isBlanc() { return isBlanc; }
    public void setBlanc(boolean blanc) { isBlanc = blanc; }
    void setMinute(int minute) { this.minute = minute; }
    void setSeconde(int seconde) { this.seconde = seconde; }
}
