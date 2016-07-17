import java.awt.*;

/**
 Created by Adonis on 25/05/2016.
 */
class DigitalNumber
{
    private Led[] leds;

    /**
     * DigitalNumber
     *définit le placement des leds pour chaque chiffres
     *
     * @param x (abcisse)
     * @param y (ordonné)
     * @param k (coefficient)
     *
     */
    DigitalNumber(int x, int y, int k)
    {
        leds = new Led[7];
        leds[0] = new Led(x,y,k,"vert");
        leds[1] = new Led(x,y+10*k,k,"vert");
        leds[2] = new Led(x+8*k,y,k,"vert");
        leds[3] = new Led(x+8*k,y+10*k,k,"vert");
        leds[4] = new Led(x+2*k,y-9*k,k,"horiz");
        leds[5] = new Led(x+2*k,y+k,k,"horiz");
        leds[6] = new Led(x+2*k,y+11*k,k,"horiz");
    }

    /**
     * setNumber
     * définit les polygones qu'il faut définir pour afficher un chiffre
     *
     * @param num (numero de 0 à 9 pour l'affichage digital
     */
    void setNumber(int num)
    {
        if(num==0)
        {
            leds[0].setState(true);
            leds[1].setState(true);
            leds[2].setState(true);
            leds[3].setState(true);
            leds[4].setState(true);
            leds[5].setState(false);
            leds[6].setState(true);
        }
        else if(num==1)
        {
            leds[0].setState(false);
            leds[1].setState(false);
            leds[2].setState(true);
            leds[3].setState(true);
            leds[4].setState(false);
            leds[5].setState(false);
            leds[6].setState(false);
        }else if(num==2)
        {
            leds[0].setState(false);
            leds[1].setState(true);
            leds[2].setState(true);
            leds[3].setState(false);
            leds[4].setState(true);
            leds[5].setState(true);
            leds[6].setState(true);
        }
        else if(num==3)
        {
            leds[0].setState(false);
            leds[1].setState(false);
            leds[2].setState(true);
            leds[3].setState(true);
            leds[4].setState(true);
            leds[5].setState(true);
            leds[6].setState(true);
        }
        else if(num==4)
        {
            leds[0].setState(true);
            leds[1].setState(false);
            leds[2].setState(true);
            leds[3].setState(true);
            leds[4].setState(false);
            leds[5].setState(true);
            leds[6].setState(false);
        }
        else if(num==5)
        {
            leds[0].setState(true);
            leds[1].setState(false);
            leds[2].setState(false);
            leds[3].setState(true);
            leds[4].setState(true);
            leds[5].setState(true);
            leds[6].setState(true);
        }
        else if(num==6)
        {
            leds[0].setState(true);
            leds[1].setState(true);
            leds[2].setState(false);
            leds[3].setState(true);
            leds[4].setState(true);
            leds[5].setState(true);
            leds[6].setState(true);
        }
        else if(num==7)
        {
            leds[0].setState(false);
            leds[1].setState(false);
            leds[2].setState(true);
            leds[3].setState(true);
            leds[4].setState(true);
            leds[5].setState(false);
            leds[6].setState(false);
        }
        else if(num==8 )
        {
            leds[0].setState(true);
            leds[1].setState(true);
            leds[2].setState(true);
            leds[3].setState(true);
            leds[4].setState(true);
            leds[5].setState(true);
            leds[6].setState(true);
        }
        else if(num==9)
        {
            leds[0].setState(true);
            leds[1].setState(false);
            leds[2].setState(true);
            leds[3].setState(true);
            leds[4].setState(true);
            leds[5].setState(true);
            leds[6].setState(true);
        }
    }

    /**
     * drawNumber
     * dessine le nombre voulu
     * todoAdonis
     * @param g2 (sert à dessiner les leds)
     */
    void drawNumber(Graphics g2)
    {
        for(int i=0; i<7; i++)
            leds[i].render(g2);
    }
}
