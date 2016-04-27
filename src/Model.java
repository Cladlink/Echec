import java.util.ArrayList;

/**
  Created by Michael on 25/03/16.
 */
public class Model
{
    private ArrayList<Case> casesAtteignables = null;
    private Partie partie = null;
    private Joueur jBlanc = null;
    private Joueur jNoir = null;
    private int modePartie = 0;
    private boolean netPartie = false;
    private Case caseMemoire = null;



    public void lancementPartie()
    {
        this.jBlanc = new Joueur(true, "toto");
        this.jNoir = new Joueur(!jBlanc.isBlanc(), "tata");
        this.partie = new Partie(jBlanc, jNoir, modePartie, netPartie);
    }

    /**
     * majCasesAtteignable
     *
     */
    public void majCasesAtteignable()
    {
        ArrayList<Piece> pieceEnJeu;
        if (partie.isTourBlanc())
            pieceEnJeu = partie.getPiecesBlanchesPlateau();
        else
            pieceEnJeu = partie.getPiecesNoiresPlateau();

        for (int i = 0; i < pieceEnJeu.size(); i++)
        {
            pieceEnJeu.get(i).casesAtteignables();
            pieceEnJeu.get(i).deplacementPossible();

        }
    }
    // getters & setters

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
    public ArrayList<Case> getCasesAtteignables() {
        return casesAtteignables;
    }
    public void setCasesAtteignables(ArrayList<Case> casesAtteignables) {
        this.casesAtteignables = casesAtteignables;
    }
    public Case getCaseMemoire() {
        return caseMemoire;
    }
    public void setCaseMemoire(Case caseMemoire) {
        this.caseMemoire = caseMemoire;
    }
}
