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
    private int modePartie = 0;
    private boolean netPartie; // à la fin
    private Case caseMemoire;

    public void lancementPartie(String pseudo, String pseudoAdversaire)
    {
        if (pseudo.equals("anonymous"))
            this.jBlanc = new Joueur(true);
        else
            this.jBlanc = new Joueur(true, pseudo);

        if (pseudoAdversaire.equals("anonymous"))
            this.jNoir = new Joueur(false);
        else
            this.jNoir = new Joueur(false, pseudoAdversaire);

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