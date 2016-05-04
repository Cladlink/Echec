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
    private int modePartie = 1;
    private boolean netPartie = false;
    private Case caseMemoire = null;



    public void lancementPartie()
    {
        this.jBlanc = new Joueur(true, "toto");
        this.jNoir = new Joueur(false, "tata");
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