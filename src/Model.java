import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by mlucile on 12/05/16.
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

    private String partieNormaleTitre;
    private String partieTempsCoupsLimitesTitre;
    private String partieTempsLimiteTitre;
    private String reseauOuiTitre;
    private String reseauNonTitre;
    private String skinBlancNormalTitre;
    private String skinBlancProfsTitre;
    private String skinBlancElevesTitre;
    private String skinNoirNormalTitre;
    private String skinNoirProfsTitre;
    private String skinNoirElevesTitre;



    private String titreLabel;
    private String joueurBlancLabel;
    private String joueurNoirLabel;
    private String typePartieLabel;
    private String skinLabel;
    private String reseauLabel;

    private String lancerPartieTitre;
    private String rejoindrePartieTitre;
    private String nouveauJoueurTitre;
    private String creditTitre;
    private String chargerPartieTitre;
    private String retourMenuTitre;

    public Model()
    {
        titreLabel = "Echecs";
        joueurBlancLabel = "Selectionnez le joueur blanc :";
        joueurNoirLabel = "Selectionnez le joueur noir :";
        typePartieLabel = "Type de partie :";
        skinLabel = "Skin des pièces :";
        reseauLabel = "Voulez-vous faire une partie en réseau ?";

        lancerPartieTitre = "Lancer une partie";
        rejoindrePartieTitre = "Rejoindre une partie";
        nouveauJoueurTitre = "Nouveau joueur";
        creditTitre = "Credits";
        chargerPartieTitre = "Charger une partie";
        retourMenuTitre = "Retour";

        partieNormaleTitre = "normale";
        partieTempsCoupsLimitesTitre = "temps limité par coup";
        partieTempsLimiteTitre = "temps limité";
        reseauOuiTitre = "oui";
        reseauNonTitre = "non";
        skinBlancNormalTitre = "classique";
        skinBlancProfsTitre = "professeurs";
        skinBlancElevesTitre = "élèves";
        skinNoirNormalTitre = "classique";
        skinNoirProfsTitre = "professeurs";
        skinNoirElevesTitre = "élèves";

    }

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

    public String getJoueurBlancLabel() {
        return joueurBlancLabel;
    }

    public String getJoueurNoirLabel() {
        return joueurNoirLabel;
    }

    public String getTypePartieLabel() {
        return typePartieLabel;
    }

    public String getSkinLabel() {
        return skinLabel;
    }

    public String getReseauLabel() {
        return reseauLabel;
    }

    public String getLancerPartieTitre() {
        return lancerPartieTitre;
    }

    public String getRejoindrePartieTitre() {
        return rejoindrePartieTitre;
    }

    public String getNouveauJoueurTitre() {
        return nouveauJoueurTitre;
    }

    public String getPartieNormaleTitre() {
        return partieNormaleTitre;
    }

    public String getPartieTempsCoupsLimitesTitre() {
        return partieTempsCoupsLimitesTitre;
    }

    public String getPartieTempsLimiteTitre() {
        return partieTempsLimiteTitre;
    }

    public String getReseauOuiTitre() {
        return reseauOuiTitre;
    }

    public String getReseauNonTitre() {
        return reseauNonTitre;
    }

    public String getSkinBlancNormalTitre() {
        return skinBlancNormalTitre;
    }

    public String getSkinBlancProfsTitre() {
        return skinBlancProfsTitre;
    }

    public String getSkinBlancElevesTitre() {
        return skinBlancElevesTitre;
    }

    public String getSkinNoirNormalTitre() {
        return skinNoirNormalTitre;
    }

    public String getSkinNoirProfsTitre() {
        return skinNoirProfsTitre;
    }

    public String getSkinNoirElevesTitre() {
        return skinNoirElevesTitre;
    }

    public String getTitreLabel() {
        return titreLabel;
    }

    public String getCreditTitre() {
        return creditTitre;
    }

    public String getChargerPartieTitre() {
        return chargerPartieTitre;
    }

    public String getRetourMenuTitre() {
        return retourMenuTitre;
    }
}
