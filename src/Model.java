import java.util.ArrayList;

/*
 * Created by cladlink on 25/03/16.
 */
public class Model
{
    private Partie partie = null;
    private Joueur jBlanc = null;
    private Joueur jNoir = null;
    private int modePartie = 1;
    private boolean netPartie = false;


    public void lancementPartie()
    {
        this.jBlanc = new Joueur(true, "toto");
        this.jNoir = new Joueur(!jBlanc.isBlanc(), "tata");
        this.partie = new Partie(jBlanc, jNoir, modePartie, netPartie);
    }

    public void casesJouables(int row, int column)
    {
        ArrayList<Case> casesJouables = partie.getBoard().getPlateau()[row][column].getPiece().casesAtteignables();
    }

    public Partie getPartie() {
        return partie;
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    public Joueur getjBlanc() {
        return jBlanc;
    }
    public void setjBlanc(Joueur jBlanc) {
        this.jBlanc = jBlanc;
    }
    public Joueur getjNoir() {
        return jNoir;
    }
    public void setjNoir(Joueur jNoir) {
        this.jNoir = jNoir;
    }
    public int getModePartie() {
        return modePartie;
    }
    public void setModePartie(int modePartie) {
        this.modePartie = modePartie;
    }
    public boolean isNetPartie() {
        return netPartie;
    }
    public void setNetPartie(boolean netPartie) {
        this.netPartie = netPartie;
    }
}
