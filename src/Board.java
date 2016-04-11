/**
  Created by cladlink on 06/04/16.
 */
public class Board
{
    private final int raw = 8;
    private final int column = 8;
    private Case[][] plateau = null;
    private Case caseMemoire = null; //enregistre la case qui a été cliquée remettre à null après un coup joué !
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
     * plateauDeBase
     * "soulage" le constructeur sur le placement des pièces au début d'une partie
     */
    public void plateauDeBase()
    {
        boolean isWhite = true;

        // todo doit on initialiser toutes les pièces à part ? Pas très content de cette méthode...
        //initie les cases vides
        for (int i = 2; i < plateau.length-2; i++)
        {
            for (int j = 0; j < plateau[i].length; j++)
            {
                plateau[i][j] = new Case(i, j, null, this, isWhite);
                isWhite = !isWhite; // todo initialistion de la couleur de la case
            }
            isWhite =!isWhite;
        }

        Piece tourBlanche1 = new Tour(plateau[7][0], true);
        Piece tourBlanche2 = new Tour(plateau[7][7], true);
        Piece tourNoire1 = new Tour(plateau[0][0], false);
        Piece tourNoire2 = new Tour(plateau[0][7], false);

        Piece cavalierBlanc1 = new Cavalier(plateau[7][1], true);
        Piece cavalierBlanc2 = new Cavalier(plateau[7][6], true);
        Piece cavalierNoir1 = new Cavalier(plateau[0][1], false);
        Piece cavalierNoir2 = new Cavalier(plateau[0][6], false);

        Piece fouBlanc1 = new Fou(plateau[7][2], true);
        Piece fouBlanc2 = new Fou(plateau[7][5], true);
        Piece fouNoir1 = new Fou(plateau[0][2], false);
        Piece fouNoir2 = new Fou(plateau[0][5], false);

        Piece roiBlanc = new Roi(plateau[7][3], true);
        Piece reineBlanche = new Reine(plateau[7][4], true);
        Piece roiNoir = new Roi(plateau[0][3], false);
        Piece reineNoire = new Reine(plateau[0][4], false);
        
        //Coté des pièces noires, en haut du plateau
        isWhite = plateau[7][0].isWhite(); // todo récupération avec de faire un new (propre ?)
        plateau[7][0] = new Case(7,0, tourBlanche1, this, isWhite);
        isWhite = plateau[7][1].isWhite();
        plateau[7][1] = new Case(7,1, cavalierBlanc1, this, isWhite);
        isWhite = plateau[7][2].isWhite();
        plateau[7][2] = new Case(7,2, fouBlanc1, this, isWhite);
        isWhite = plateau[7][3].isWhite();
        plateau[7][3] = new Case(7,3, roiBlanc, this, isWhite);
        isWhite = plateau[7][4].isWhite();
        plateau[7][4] = new Case(7,4, reineBlanche, this, isWhite);
        isWhite = plateau[7][5].isWhite();
        plateau[7][5] = new Case(7,5, fouBlanc2, this, isWhite);
        isWhite = plateau[7][6].isWhite();
        plateau[7][6] = new Case(7,6, cavalierBlanc2, this, isWhite);
        isWhite = plateau[7][7].isWhite();
        plateau[7][7] = new Case(7,7, tourBlanche2, this, isWhite);
        for(int i=0; i<column; i++)
        {
            isWhite = plateau[6][i].isWhite();
            plateau[6][i] = new Case(6,i, new Pion(plateau[6][i], true), this, isWhite);
        }

        //Coté des pièces blanches, en bas du plateau
        isWhite = plateau[0][0].isWhite();
        plateau[0][0] = new Case(0,0, tourNoire1, this, isWhite);
        isWhite = plateau[0][1].isWhite();
        plateau[0][1] = new Case(0,1, cavalierNoir1, this, isWhite);
        isWhite = plateau[0][2].isWhite();
        plateau[0][2] = new Case(0,2, fouNoir1, this, isWhite);
        isWhite = plateau[0][3].isWhite();
        plateau[0][3] = new Case(0,3, roiNoir, this, isWhite);
        isWhite = plateau[0][4].isWhite();
        plateau[0][4] = new Case(0,4, reineNoire, this, isWhite);
        isWhite = plateau[0][5].isWhite();
        plateau[0][5] = new Case(0,5, fouNoir2, this, isWhite);
        isWhite = plateau[0][6].isWhite();
        plateau[0][6] = new Case(0,6, cavalierNoir2, this, isWhite);
        isWhite = plateau[0][7].isWhite();
        plateau[0][7] = new Case(0,7, tourNoire2, this, isWhite);
        for(int i=0; i<column; i++)
        {
            isWhite = plateau[1][i].isWhite();
            plateau[1][i] = new Case(1,i, new Pion(plateau[1][i], false), this, isWhite);
        }
    }

    /**
     * deplacer
     * deplace la pièce après toutes vérifications
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
