/*
 * Created by Adonis Ndolo on 30/03/16.
 */
public class Point
{

    /**
     * Variables d'instances
     */
    protected int x;
    protected int y;

    /**
     * Constructeur par defaut et personnalisé
     * @param y (axe ordonnee)
     * @param x (axe abscisses)
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Méthodes
     */

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
