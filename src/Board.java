import java.util.ArrayList;
/**
  Created by cladlink on 06/04/16.
 */
class Board
{
    private final int row = 8;
    private final int column = 8;
    private final int sizeCase = 80;
    private Case[][] plateau;
    private Partie partie;
    private Piece roiNoir, roiBlanc;

    /**
     * Board (constructeur)
     *
     * initie le plateau
     * la case mémoire à un emplacement vide
     *
     */
    Board(Partie partie)
    {
        plateau = new Case[row][column];
        this.partie = partie;
        plateauDeBase();
    }

    /**
     * Board (Constructeur)
     *
     *
     */
    Board(Partie partie, Case[][] plateau)
    {
        this.partie = partie;
        this.plateau = plateau;
    }

    /**
     * plateauDeBase
     * "soulage" le constructeur sur le placement des pièces au début d'une partie
     */
    public void plateauDeBase()
    {
        boolean white = true;

        ArrayList<Piece> piecesBlanchesPlateau = partie.getPiecesBlanchesPlateau();
        ArrayList<Piece> piecesNoiresPlateau = partie.getPiecesNoiresPlateau();

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
        roiBlanc = new Roi(plateau[7][4], true);
        piecesBlanchesPlateau.add(roiBlanc);
        Piece reineBlanche = new Reine(plateau[7][3], true);
        piecesBlanchesPlateau.add(reineBlanche);

        roiNoir = new Roi(plateau[0][4], false);
        piecesNoiresPlateau.add(roiNoir);
        Piece reineNoire = new Reine(plateau[0][3], false);
        piecesNoiresPlateau.add(reineNoire);

        Piece tourBlanche1 = new Tour(plateau[7][0], true);
        piecesBlanchesPlateau.add(tourBlanche1);
        Piece tourBlanche2 = new Tour(plateau[7][7], true);
        piecesBlanchesPlateau.add(tourBlanche2);
        Piece tourNoire1 = new Tour(plateau[0][0], false);
        piecesNoiresPlateau.add(tourNoire1);
        Piece tourNoire2 = new Tour(plateau[0][7], false);
        piecesNoiresPlateau.add(tourNoire2);

        Piece cavalierBlanc1 = new Cavalier(plateau[7][1], true);
        piecesBlanchesPlateau.add(cavalierBlanc1);
        Piece cavalierBlanc2 = new Cavalier(plateau[7][6], true);
        piecesBlanchesPlateau.add(cavalierBlanc2);
        Piece cavalierNoir1 = new Cavalier(plateau[0][1], false);
        piecesNoiresPlateau.add(cavalierNoir1);
        Piece cavalierNoir2 = new Cavalier(plateau[0][6], false);
        piecesNoiresPlateau.add(cavalierNoir2);

        Piece fouBlanc1 = new Fou(plateau[7][2], true);
        piecesBlanchesPlateau.add(fouBlanc1);
        Piece fouBlanc2 = new Fou(plateau[7][5], true);
        piecesBlanchesPlateau.add(fouBlanc2);
        Piece fouNoir1 = new Fou(plateau[0][2], false);
        piecesNoiresPlateau.add(fouNoir1);
        Piece fouNoir2 = new Fou(plateau[0][5], false);
        piecesNoiresPlateau.add(fouNoir2);

        Piece pionBlanc1 = new Pion(plateau[6][0], true);
        piecesBlanchesPlateau.add(pionBlanc1);
        Piece pionBlanc2 = new Pion(plateau[6][1], true);
        piecesBlanchesPlateau.add(pionBlanc2);
        Piece pionBlanc3 = new Pion(plateau[6][2], true);
        piecesBlanchesPlateau.add(pionBlanc3);
        Piece pionBlanc4 = new Pion(plateau[6][3], true);
        piecesBlanchesPlateau.add(pionBlanc4);
        Piece pionBlanc5 = new Pion(plateau[6][4], true);
        piecesBlanchesPlateau.add(pionBlanc5);
        Piece pionBlanc6 = new Pion(plateau[6][5], true);
        piecesBlanchesPlateau.add(pionBlanc6);
        Piece pionBlanc7 = new Pion(plateau[6][6], true);
        piecesBlanchesPlateau.add(pionBlanc7);
        Piece pionBlanc8 = new Pion(plateau[6][7], true);
        piecesBlanchesPlateau.add(pionBlanc8);

        Piece pionNoir1 = new Pion(plateau[1][0], false);
        piecesNoiresPlateau.add(pionNoir1);
        Piece pionNoir2 = new Pion(plateau[1][1], false);
        piecesNoiresPlateau.add(pionNoir2);
        Piece pionNoir3 = new Pion(plateau[1][2], false);
        piecesNoiresPlateau.add(pionNoir3);
        Piece pionNoir4 = new Pion(plateau[1][3], false);
        piecesNoiresPlateau.add(pionNoir4);
        Piece pionNoir5 = new Pion(plateau[1][4], false);
        piecesNoiresPlateau.add(pionNoir5);
        Piece pionNoir6 = new Pion(plateau[1][5], false);
        piecesNoiresPlateau.add(pionNoir6);
        Piece pionNoir7 = new Pion(plateau[1][6], false);
        piecesNoiresPlateau.add(pionNoir7);
        Piece pionNoir8 = new Pion(plateau[1][7], false);
        piecesNoiresPlateau.add(pionNoir8);

        //Coté des pièces blanches, en bas du plateau
        plateau[7][0].setPiece(tourBlanche1);
        plateau[7][1].setPiece(cavalierBlanc1);
        plateau[7][2].setPiece(fouBlanc1);
        plateau[7][4].setPiece(roiBlanc);
        plateau[7][3].setPiece(reineBlanche);
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
        plateau[0][4].setPiece(roiNoir);
        plateau[0][3].setPiece(reineNoire);
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
    }

    /**
     * deplacer
     * deplace la pièce aprés avoir vérifier : si l'empalcement cible est vide ou non
     * doit vérifier si une pièce est mangée, faire les actions en conséquence
     *
     * @param caseCliquee (case qui a ... été cliquée)
     * @param destination (case ou la pièce doit se rendre)
     */
    void deplacer(Case caseCliquee, Case destination, Vue vue)
    {
        // ajoute le coup dans l'historique
        partie.historiqueCoups(caseCliquee, destination);
        // si la case destination contient une pièce
        if (destination.getPiece() != null)
            if (destination.getPiece().blanc)
            {
                partie.getPiecesBlanchesPlateau().remove(destination.getPiece());
                partie.getCimetiereBlanc().add(destination.getPiece());
                destination.setPiece(null);
            }
            else
            {
                partie.getPiecesNoiresPlateau().remove(destination.getPiece());
                partie.getCimetiereNoir().add(destination.getPiece());
                destination.setPiece(null);
            }
        roque(caseCliquee, destination);
        // deplace
        caseCliquee.getPiece().deplacer(destination);
        // test de la promotion
        if(  ( destination.getRow() == 0 && destination.getPiece().blanc )
                || ( ( destination.getRow() == 7) && !destination.getPiece().blanc )
                && destination.getPiece() instanceof Pion )
            vue.choixPiece( (Pion)destination.getPiece() );
    }

    /**
     * roque
     * le roque est il faisable ?
     *
     * @param caseCliquee (case d'origine)
     * @param destination (case de destination)
     */
    private void roque(Case caseCliquee, Case destination)
    {
        if (caseCliquee.getPiece() instanceof Roi)
        {
            Case[][] plateau = caseCliquee.getBoard().getPlateau();
            int row = caseCliquee.getRow();
            int column = caseCliquee.getColumn();

            if (Math.abs( column - destination.getColumn() ) == 2)
                deplacerPetitRoque(plateau, row, column);
            else if (Math.abs( column - destination.getColumn() ) == 3)
                deplacerGrandRoque(plateau, row, column);
        }
    }

    /**
     * deplacerPetitRoque
     * execute le petit roque
     *
     * @param plateau (etat du plateau)
     * @param row (rang)
     * @param column (column)
     */
    private void deplacerPetitRoque(Case[][] plateau, int row, int column)
    {
        Piece tourRoque = plateau[row][column + 3].getPiece();

        plateau[row][column + 3].setPiece(null);
        tourRoque.emplacementPiece = plateau[row][column + 1];
        plateau[row][column + 1].setPiece(tourRoque);
    }

    /**
     * deplacerGrandRoque
     * exécute le grand roque
     *
     * @param plateau (etat du plateau)
     * @param row (rang)
     * @param column (column)
     */
    private void deplacerGrandRoque(Case[][] plateau, int row, int column) {
        Piece tourRoque;
        tourRoque = plateau[row][column - 4].getPiece();

        plateau[row][column - 4].setPiece(null);
        tourRoque.emplacementPiece = plateau[row][column - 2];
        plateau[row][column - 2].setPiece(tourRoque);
    }

    /**
     * majCasesAtteignable
     * Mets à jour la variable représentant toutes les cases atteignables des pièces de l'adversaire
     *
     */
    void majCasesAtteignable()
    {
        ArrayList<Piece> pieceEnJeu;
        if (partie.isTourBlanc())
            pieceEnJeu = partie.getPiecesBlanchesPlateau();
        else
            pieceEnJeu = partie.getPiecesNoiresPlateau();

        for (Piece pej : pieceEnJeu)
        {
            pej.casesAtteignables();
            pej.deplacementPossible();
        }
    }

    //getters & setters
    Case[][] getPlateau() {
        return plateau;
    }
    Partie getPartie() {
        return partie;
    }
    int getSizeCase() {
        return sizeCase;
    }
    Piece getRoiNoir() {
        return roiNoir;
    }
    Piece getRoiBlanc() {
        return roiBlanc;
    }
    void setPartie(Partie partie) {
        this.partie = partie;
    }
    void setRoiBlanc(Piece roiBlanc) {
        this.roiBlanc = roiBlanc;
    }
    void setRoiNoir(Piece roiNoir) {
        this.roiNoir = roiNoir;
    }
}