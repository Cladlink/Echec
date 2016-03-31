import javax.swing.*;

/**
 * Created by Gabi on 30/03/16.
 */
public class Reine extends Piece
{
    private final String adressePieceNoire = "adresseNoire";
    private final String adressePieceBlanche = "adresseBlanche";

    public Reine(int x, int y, boolean isBlanc){
        super(x,y);
        if (isBlanc){
            super.skin = new ImageIcon(this.adressePieceBlanche);
        }
        else {
            super.skin = new ImageIcon(this.adressePieceNoire);
        }
    }

    public void verticalN(int nbCases){ //Déplacement vers le haut
        super.coordonnees.setY(coordonnees.getY()+nbCases);
    }

    public void verticalS(int nbCases){ //Déplacement vers le bas
        super.coordonnees.setY(coordonnees.getY()-nbCases);
    }

    public void horizontalE(int nbCases){ //Déplacement vers la droite
        super.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void horizontalO(int nbCases){ //Déplacement vers la gauche
        super.coordonnees.setX(coordonnees.getX()-nbCases);
    }

    public void diagoNE(int nbCases){ //Déplacement en diagonale haut-droite
        super.coordonnees.setY(coordonnees.getY()+nbCases);
        super.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void diagoNO(int nbCases){ //Déplacement en diagonale haut-gauche
        super.coordonnees.setY(coordonnees.getY()+nbCases);
        super.coordonnees.setX(coordonnees.getX()-nbCases);
    }

    public void diagoSE(int nbCases){ //Déplacement en diagonale bas-droite
        super.coordonnees.setY(coordonnees.getY()-nbCases);
        super.coordonnees.setX(coordonnees.getX()+nbCases);
    }

    public void diagoSO(int nbCases){ //Déplacement en diagonale bas-gauche
        this.coordonnees.setY(coordonnees.getY()-nbCases);
        this.coordonnees.setX(coordonnees.getX()-nbCases);
    }
}
