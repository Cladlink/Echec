import java.util.ArrayList;

/**
  Created by Michael on 25/03/16.
 */
public class Model
{
    private ArrayList<Case> casesAtteignables;
    private Partie partie;
    private Joueur jBlanc;
    private Joueur jNoir;
    private int modePartie = 1;
    private boolean netPartie; // à la fin
    private Case caseMemoire;

    public void lancementPartie()
    {
        this.jBlanc = new Joueur(true);
        this.jNoir = new Joueur(false);
        this.partie = new Partie(jBlanc, jNoir, modePartie, netPartie);
    }

    // getters & setters

    public Partie getPartie() {
        return partie;
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    public void setModePartie(int modePartie) {
        this.modePartie = modePartie;
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