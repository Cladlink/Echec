import java.util.ArrayList;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
public class Model
{
    private ArrayList<Case> casesAtteignables;
    private Partie partie;
    private Joueur jBlanc;
    private Joueur jNoir;
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

    private String nouvellePartieTitre;
    private String rejoindrePartieTitre;
    private String nouveauJoueurTitre;
    private String creditTitre;
    private String chargerPartieTitre;
    private String retourMenuTitre;
    private String lancerPartieTitre;

    public Model()
    {
        majListeJoueur();
        titreLabel = "Echecs";
        joueurBlancLabel = "Selectionnez le joueur blanc :";
        joueurNoirLabel = "Selectionnez le joueur noir :";
        typePartieLabel = "Type de partie :";
        skinLabel = "Skin des pièces :";
        reseauLabel = "Voulez-vous faire une partie en réseau ?";

        nouvellePartieTitre = "Lancer une partie";
        rejoindrePartieTitre = "Rejoindre une partie";
        nouveauJoueurTitre = "Nouveau joueur";
        creditTitre = "Credits";
        chargerPartieTitre = "Charger une partie";
        retourMenuTitre = "Retour";
        lancerPartieTitre = "Lancer la partie";

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

    public Vector<String> majListeJoueur()
    {
        return Joueur.listeJoueurs();
    }
    public void lancementPartie(String pseudo, String pseudoAdversaire,
                                int choixJB, int choixJN, int modePartie, boolean netPartie)
    {
        if (pseudo.equals("anonymous"))
            this.jBlanc = new Joueur(true);
        else
            this.jBlanc = new Joueur(true, pseudo);

        if (pseudoAdversaire.equals("anonymous"))
            this.jNoir = new Joueur(false);
        else
            this.jNoir = new Joueur(false, pseudoAdversaire);

        this.partie = new Partie(jBlanc, jNoir, modePartie, netPartie, choixJB, choixJN);
    }


    // getters & setters
    public Partie getPartie() {
        return partie;
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
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

    public String getNouvellePartieTitre() {
        return nouvellePartieTitre;
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

    public String getLancerPartieTitre() {
        return lancerPartieTitre;
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

    public void setPartieNormaleTitre(String partieNormaleTitre) {
        this.partieNormaleTitre = partieNormaleTitre;
    }

    public void setPartieTempsCoupsLimitesTitre(String partieTempsCoupsLimitesTitre) {
        this.partieTempsCoupsLimitesTitre = partieTempsCoupsLimitesTitre;
    }

    public void setPartieTempsLimiteTitre(String partieTempsLimiteTitre) {
        this.partieTempsLimiteTitre = partieTempsLimiteTitre;
    }

    public void setReseauOuiTitre(String reseauOuiTitre) {
        this.reseauOuiTitre = reseauOuiTitre;
    }

    public void setReseauNonTitre(String reseauNonTitre) {
        this.reseauNonTitre = reseauNonTitre;
    }

    public void setSkinBlancNormalTitre(String skinBlancNormalTitre) {
        this.skinBlancNormalTitre = skinBlancNormalTitre;
    }

    public void setSkinBlancProfsTitre(String skinBlancProfsTitre) {
        this.skinBlancProfsTitre = skinBlancProfsTitre;
    }

    public void setSkinBlancElevesTitre(String skinBlancElevesTitre) {
        this.skinBlancElevesTitre = skinBlancElevesTitre;
    }

    public void setSkinNoirNormalTitre(String skinNoirNormalTitre) {
        this.skinNoirNormalTitre = skinNoirNormalTitre;
    }

    public void setSkinNoirProfsTitre(String skinNoirProfsTitre) {
        this.skinNoirProfsTitre = skinNoirProfsTitre;
    }

    public void setSkinNoirElevesTitre(String skinNoirElevesTitre) {
        this.skinNoirElevesTitre = skinNoirElevesTitre;
    }

    public void setTitreLabel(String titreLabel) {
        this.titreLabel = titreLabel;
    }

    public void setJoueurBlancLabel(String joueurBlancLabel) {
        this.joueurBlancLabel = joueurBlancLabel;
    }

    public void setJoueurNoirLabel(String joueurNoirLabel) {
        this.joueurNoirLabel = joueurNoirLabel;
    }

    public void setTypePartieLabel(String typePartieLabel) {
        this.typePartieLabel = typePartieLabel;
    }

    public void setSkinLabel(String skinLabel) {
        this.skinLabel = skinLabel;
    }

    public void setReseauLabel(String reseauLabel) {
        this.reseauLabel = reseauLabel;
    }

    public void setNouvellePartieTitre(String nouvellePartieTitre) {
        this.nouvellePartieTitre = nouvellePartieTitre;
    }

    public void setRejoindrePartieTitre(String rejoindrePartieTitre) {
        this.rejoindrePartieTitre = rejoindrePartieTitre;
    }

    public void setNouveauJoueurTitre(String nouveauJoueurTitre) {
        this.nouveauJoueurTitre = nouveauJoueurTitre;
    }

    public void setCreditTitre(String creditTitre) {
        this.creditTitre = creditTitre;
    }

    public void setChargerPartieTitre(String chargerPartieTitre) {
        this.chargerPartieTitre = chargerPartieTitre;
    }

    public void setRetourMenuTitre(String retourMenuTitre) {
        this.retourMenuTitre = retourMenuTitre;
    }

    public void setLancerPartieTitre(String lancerPartieTitre) {
        this.lancerPartieTitre = lancerPartieTitre;
    }
}
