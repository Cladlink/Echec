/**
 * Created by cladlink on 30/03/16.
 */
public class Tour extends Piece
{
    private boolean noir;
    private int valeur;
    private ImageIcon figurineNoir;
    private ImageIcon figurineBlanche;
    private Point point;

    public Tour(){
        this.vivant = true;
    }


    /**
     * Vérifie si la tour peut se déplacer
     * c-a-d : si le x de point est égal à x de la tour
     * ou si le y de point est égal à y de la tour
     * @param pointDeplace : coordonnées de la case destination
     * @return boolean -> true si peut se déplacer
     */
    public boolean peutDeplacer(Point pointDeplace){
        if (this.point.getX() == pointDeplace.getX()
                || this.point.getY() == pointDeplace.getY()){
            return true;
        }
    }

    /**
     * Change les coordonnées de la pièce en fonction de la méthode appelé et d'une nombre de case "i" donné
     * @param nbCase: nombre de case a se déplacer
     * @return boolean -> true si peut se déplacer
     */
    public void deplacementN(int nbCase){
        point.setY(point.getY()+nbCase);
    }
    public void deplacementE(int nbCase){
        point.setX(point.getX()+nbCase);
    }
    public void deplacementS(int nbCase){
        point.setY(point.getY()-nbCase);
    }
    public void deplacementO(int nbCase){
        point.setX(point.getX()-nbCase);
    }
}
