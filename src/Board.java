/**
  Created by cladlink on 06/04/16.
 */
public class Board
{
    private final int raw = 8;
    private final int column = 8;
    private Case[][] plateau = null;
    private Case caseMemoire = null; //enregistre la case qui a été cliquée
    private Partie partie = null;


    /**
     * Board (constructeur)
     *
     * initie le plateau
     * la case mémoire à un emplacement vide
     *
     */
    public Board(Partie partie)
    {
        this.partie = partie;
        plateau = new Case[raw][column];
        plateauDeBase();
    }

    /**
     * plateauDeBae
     * "soulage" le constructeur sur le placement des pièces au début d'une partie
     */
    public void plateauDeBase()
    {

        // todo rajouter les pièces dans l'arraylist de partie
        //Coté des pièces noires, en haut du plateau
        plateau[7][0] = new Case(7,0, new Tour(plateau[7][0], false), this);
        plateau[7][1] = new Case(7,1, new Cavalier(plateau[7][1], false), this);
        plateau[7][2] = new Case(7,2, new Fou(plateau[7][2], false), this);
        plateau[7][3] = new Case(7,3, new Roi(plateau[7][3], false), this);
        plateau[7][4] = new Case(7,4, new Reine(plateau[7][4], false), this);
        plateau[7][5] = new Case(7,5, new Fou(plateau[7][5], false), this);
        plateau[7][6] = new Case(7,6, new Cavalier(plateau[7][6], false), this);
        plateau[7][7] = new Case(7,7, new Tour(plateau[7][7], false), this);
        for(int i=0; i<column; i++) plateau[7][i] = new Case(6,i, new Pion(plateau[6][i], false), this);

        //Coté des pièces blanches, en bas du plateau
        plateau[0][0] = new Case(0,0, new Tour(plateau[0][0], true), this);
        plateau[0][1] = new Case(0,1, new Cavalier(plateau[0][1], true), this);
        plateau[0][2] = new Case(0,2, new Fou(plateau[0][2], true), this);
        plateau[0][3] = new Case(0,3, new Roi(plateau[0][3], true), this);
        plateau[0][4] = new Case(0,4, new Reine(plateau[0][4], true), this);
        plateau[0][5] = new Case(0,5, new Fou(plateau[0][5], true), this);
        plateau[0][6] = new Case(0,6, new Cavalier(plateau[0][6], true), this);
        plateau[0][7] = new Case(0,7, new Tour(plateau[0][7], true), this);
        for(int i=0; i<column; i++) plateau[1][i] = new Case(1,i, new Pion(plateau[1][i], true), this);
    }

    /**
     * deplacer
     *  test si la case cliquée est pleine ou non en prenant en compte la case mémoire qui
     *  stocke une case qui peut être déplacé au choix du joueur.
     *  Cette méthode doit prendre en compte le déplacement OU le fait d'afficher les deplacements possible
     *  d'une piece (ou rien du tout)
     *
     * @param caseCliquee (case qui a ... été cliquée)
     */
    public void deplacer(Case caseCliquee, Case destination)
    {
        caseCliquee.getPiece().deplacer(destination);
    }


    //getters / setters
    public int getRaw() {
        return raw;
    }
    public int getColumn() {
        return column;
    }
    public Case[][] getPlateau() {
        return plateau;
    }
    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }
    public Case getCaseMemoire() {
        return caseMemoire;
    }
    public void setCaseMemoire(Case caseMemoire) {
        this.caseMemoire = caseMemoire;
    }
    public Partie getPartie() {
        return partie;
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
