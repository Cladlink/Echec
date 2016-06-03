import java.awt.*;

/**
 Created by Adonis on 25/05/2016.
 */
class Led
{
    private Polygon polygon;        // forme
    private boolean lightOn = false;        // état de la led

    Led(int x, int y, int k, String orientation)
    {
        polygon = new Polygon();

        if(orientation.equals("vert"))
        {
            polygon.addPoint(x,y);
            polygon.addPoint(x+k,y+k);
            polygon.addPoint(x+2*k,y);
            polygon.addPoint(x+2*k,y-8*k);
            polygon.addPoint(x+k,y-9*k);
            polygon.addPoint(x,y-8*k);
        }
        if(orientation.equals("horiz"))
        {
            polygon.addPoint(x,y);
            polygon.addPoint(x+k,y+k);
            polygon.addPoint(x+5*k,y+k);
            polygon.addPoint(x+6*k,y);
            polygon.addPoint(x+5*k,y-k);
            polygon.addPoint(x+k,y-k);
        }
    }

    /**
     * Methode à appelé dans VueTimer. Car c'est elle qui dessine les polygones.
     * @param g2
     */

    void render(Graphics g2)
    {
        g2.setColor(new Color(230,230,230));
        if(lightOn)g2.setColor(Color.RED);
        g2.fillPolygon(polygon);
    }

    void setState(boolean s)
    {
        lightOn=s;
    }
}