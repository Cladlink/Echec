import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 Created by cladlink on 03/05/16.
 */
class VueTimer
{
    private Partie partie;
    private boolean isBlanc;
    private int minute, seconde;
    private Timer chrono;
    private DigitalNumber digitDizaineMinute, digitMinute, digitDizaineSeconde, digitSeconde;

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
            minute = 15;
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

        // todo côté model
        if ( partie.getModePartie() == 2
                || partie.getModePartie() == 3 )
        {
            // ajout SD
            // minute = ...; // a calculer en fonction du chrono du joueur courant
            // seconde = ...; // a calculer en fonction du chrono du joueur courant

            g.setColor(Color.WHITE);
            g.fillRect(xBase, yBase, 100, 50);// 160 80 || 1110 80
            g.fillRect(xBase, yBase, 100, 50);
            //plus besoin car digit mtn
            //g.setColor(Color.BLACK);
            //g.drawString(minute + " : " + seconde, xBase+30, yBase+30);

            //set digit
            digitDizaineMinute = new DigitalNumber(xBaseDigit, yBaseDigit,1);
            digitMinute = new DigitalNumber(xBaseDigit+15, yBaseDigit,1);
            digitDizaineSeconde = new DigitalNumber(xBaseDigit+40, yBaseDigit,1);
            digitSeconde = new DigitalNumber(xBaseDigit+55, yBaseDigit,1);

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

    public Partie getPartie() {
        return partie;
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    public boolean isBlanc() {
        return isBlanc;
    }
    public void setBlanc(boolean blanc) {
        isBlanc = blanc;
    }
    public void setMinute(int minute) {
        this.minute = minute;
    }
    public void setSeconde(int seconde) {
        this.seconde = seconde;
    }
}
