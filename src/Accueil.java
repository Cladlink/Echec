import java.util.ArrayList;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
class Accueil
{
    private ArrayList<Case> casesAtteignables; // move to partie
    private Partie partie;
    private Case caseMemoire; // move to partie

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
    private String quitterTitre;


    /**
     *Accueil
     * Model de "l'avant partie"
     */
    Accueil()
    {
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
        quitterTitre = "Quitter";

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

    /**
     * majListeJoueur
     * met à jour la liste de joueur dans le formulaire de partie
     * @return (liste des joueurs à jour)
     */
    Vector<String> majListeJoueur()
    {
        return Joueur.listeJoueurs();
    }

    /**
     * lancementPartie
     * Départ une partie selon les critères choisis dans le formulaire
     * 
     * @param pseudo (pseudo du joueur1)
     * @param pseudoAdversaire (pseudo du joueur 2)
     * @param choixJB (choix de l'aspect des pièces blanches)
     * @param choixJN (choix de l'aspect des pièces noires)
     * @param modePartie (1 = normal // 2 = coups limités // 3 = partie limitée)
     * @param netPartie (la partie est elle en réseau ?)
     */
    void lancementPartie(String pseudo, String pseudoAdversaire,
                                int choixJB, int choixJN, int modePartie, boolean netPartie)
    {
        Joueur jBlanc;
        if (pseudo.equals("anonymous"))
            jBlanc = new Joueur(true);
        else
            jBlanc = new Joueur(true, pseudo);

        Joueur jNoir;
        if (pseudoAdversaire.equals("anonymous"))
            jNoir = new Joueur(false);
        else
            jNoir = new Joueur(false, pseudoAdversaire);

        this.partie = new Partie(jBlanc, jNoir, modePartie, netPartie, choixJB, choixJN);
    }


    // getters & setters
    Partie getPartie() {
        return partie;
    }
    ArrayList<Case> getCasesAtteignables() {
        return casesAtteignables;
    }
    void setCasesAtteignables(ArrayList<Case> casesAtteignables) {
        this.casesAtteignables = casesAtteignables;
    }
    Case getCaseMemoire() {
        return caseMemoire;
    }
    void setCaseMemoire(Case caseMemoire) {
        this.caseMemoire = caseMemoire;
    }
    String getJoueurBlancLabel() {
        return joueurBlancLabel;
    }
    String getJoueurNoirLabel() {
        return joueurNoirLabel;
    }
    String getTypePartieLabel() {
        return typePartieLabel;
    }
    String getSkinLabel() {
        return skinLabel;
    }
    String getReseauLabel() {
        return reseauLabel;
    }
    String getNouvellePartieTitre() {
        return nouvellePartieTitre;
    }
    String getRejoindrePartieTitre() {
        return rejoindrePartieTitre;
    }
    String getNouveauJoueurTitre() {
        return nouveauJoueurTitre;
    }
    String getPartieNormaleTitre() {
        return partieNormaleTitre;
    }
    String getPartieTempsCoupsLimitesTitre() {
        return partieTempsCoupsLimitesTitre;
    }
    String getPartieTempsLimiteTitre() {
        return partieTempsLimiteTitre;
    }
    String getReseauOuiTitre() {
        return reseauOuiTitre;
    }
    String getReseauNonTitre() {
        return reseauNonTitre;
    }
    String getSkinBlancNormalTitre() {
        return skinBlancNormalTitre;
    }
    String getSkinBlancProfsTitre() {
        return skinBlancProfsTitre;
    }
    String getSkinBlancElevesTitre() {
        return skinBlancElevesTitre;
    }
    String getSkinNoirNormalTitre() {
        return skinNoirNormalTitre;
    }
    String getSkinNoirProfsTitre() {
        return skinNoirProfsTitre;
    }
    String getSkinNoirElevesTitre() {
        return skinNoirElevesTitre;
    }
    String getTitreLabel() {
        return titreLabel;
    }
    String getCreditTitre() {
        return creditTitre;
    }
    String getChargerPartieTitre() {
        return chargerPartieTitre;
    }
    String getRetourMenuTitre() {
        return retourMenuTitre;
    }
    String getLancerPartieTitre() {
        return lancerPartieTitre;
    }
    public String getQuitterJeuTitre() {
        return quitterTitre;
    }
}
