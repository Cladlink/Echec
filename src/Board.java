/**
  Created by cladlink on 06/04/16.
 */
public class Board
{
    private final int row = 8;
    private final int column = 8;
    private Case[][] plateau = null;
    private Case caseMemoire = null; //enregistre la case qui a été cliquée remettre à null après un coup joué !
    private Partie partie = null;
    private int sizeCase = 80;


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
        plateau = new Case[row][column];

        plateauDeBase();
    }

    /**
     * plateauDeBase
     * "soulage" le constructeur sur le placement des pièces au début d'une partie
     */
    public void plateauDeBase()
    {
        boolean white = true;
        
        //initie les cases vides avec leurs couleurs
        for (int i = 0; i < plateau.length; i++)
        {
            for (int j = 0; j < plateau[i].length; j++)
            {
                plateau[i][j] = new Case(i, j, null, this, white);
                white = !white;
            }
            white =!white;
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

        Piece pionBlanc1 = new Pion(plateau[6][0], true);
        Piece pionBlanc2 = new Pion(plateau[6][1], true);
        Piece pionBlanc3 = new Pion(plateau[6][2], true);
        Piece pionBlanc4 = new Pion(plateau[6][3], true);
        Piece pionBlanc5 = new Pion(plateau[6][4], true);
        Piece pionBlanc6 = new Pion(plateau[6][5], true);
        Piece pionBlanc7 = new Pion(plateau[6][6], true);
        Piece pionBlanc8 = new Pion(plateau[6][7], true);

        Piece pionNoir1 = new Pion(plateau[1][0], false);
        Piece pionNoir2 = new Pion(plateau[1][1], false);
        Piece pionNoir3 = new Pion(plateau[1][2], false);
        Piece pionNoir4 = new Pion(plateau[1][3], false);
        Piece pionNoir5 = new Pion(plateau[1][4], false);
        Piece pionNoir6 = new Pion(plateau[1][5], false);
        Piece pionNoir7 = new Pion(plateau[1][6], false);
        Piece pionNoir8 = new Pion(plateau[1][7], false);

        //Coté des pièces blanches, en bas du plateau
        plateau[7][0].setPiece(tourBlanche1);
        plateau[7][1].setPiece(cavalierBlanc1);
        plateau[7][2].setPiece(fouBlanc1);
        plateau[7][3].setPiece(roiBlanc);
        plateau[7][4].setPiece(reineBlanche);
        plateau[7][5].setPiece(fouBlanc2);
        plateau[7][6].setPiece(cavalierBlanc2);
        plateau[7][7].setPiece(tourBlanche2);

        plateau[6][0].setPiece(pionBlanc1);
        plateau[6][1].setPiece(pionBlanc2);
        plateau[6][2].setPiece(pionBlanc3);
        plateau[6][3].setPiece(pionBlanc4);
        plateau[6][4].setPiece(pionBlanc5);
        plateau[6][5].setPiece(pionBlanc6);
        plateau[6][6].setPiece(pionBlanc7);
        plateau[6][7].setPiece(pionBlanc8);

        //Coté des pièces noires, en haut du plateau
        plateau[0][0].setPiece(tourNoire1);
        plateau[0][1].setPiece(cavalierNoir1);
        plateau[0][2].setPiece(fouNoir1);
        plateau[0][3].setPiece(roiNoir);
        plateau[0][4].setPiece(reineNoire);
        plateau[0][5].setPiece(fouNoir2);
        plateau[0][6].setPiece(cavalierNoir2);
        plateau[0][7].setPiece(tourNoire2);

        plateau[1][0].setPiece(pionNoir1);
        plateau[1][1].setPiece(pionNoir2);
        plateau[1][2].setPiece(pionNoir3);
        plateau[1][3].setPiece(pionNoir4);
        plateau[1][4].setPiece(pionNoir5);
        plateau[1][5].setPiece(pionNoir6);
        plateau[1][6].setPiece(pionNoir7);
        plateau[1][7].setPiece(pionNoir8);

        plateau[5][4].setPiece(new Tour(plateau[5][4], false));
        plateau[3][7].setPiece(new Cavalier(plateau[3][7], false));
        plateau[3][4].setPiece(new Roi(plateau[3][4], true));
        plateau[5][2].setPiece(new Reine(plateau[5][2], false));
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
    public int getRow() {
        return row;
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
    public int getSizeCase() {
        return sizeCase;
    }
    public void setSizeCase(int sizeCase) {
        this.sizeCase = sizeCase;
    }
}
