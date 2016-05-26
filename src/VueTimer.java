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
        // todo côté model
        if ( partie.getModePartie() == 2
                || partie.getModePartie() == 3 )
        {
	    // ajout SD
	    // minute = ...; // a calculer en fonction du chrono du joueur courant
	    // seconde = ...; // a calculer en fonction du chrono du joueur courant
	    
            g.setColor(Color.BLUE);
            g.fillRect(xBase, yBase, 100, 50);// 160 80 || 1110 80
            g.fillRect(xBase, yBase, 100, 50);
            g.setColor(Color.BLACK);
            g.drawString(minute + " : " + seconde, xBase+30, yBase+30);

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

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSeconde() {
        return seconde;
    }

    public void setSeconde(int seconde) {
        this.seconde = seconde;
    }

    public Timer getChrono() {
        return chrono;
    }

    public void setChrono(Timer chrono) {
        this.chrono = chrono;
    }
}
