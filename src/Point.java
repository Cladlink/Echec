/**
 * Created by Adonis on 30/03/16.
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
     * @param y
     * @param x
     */
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
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
