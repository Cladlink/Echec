/**
  Created by cladlink on 06/04/16.
 */
class Case
{
    private int row;
    private int column;
    private Piece piece;
    private Board board;
    private boolean isWhite;

    /**
     * Case (constructeur)
     * Initie la case à un emplacement précis
     *
     * @param row (représente la ligne)
     * @param column (représente la colonne)
     * @param piece (représente la pièce)
     * @param board (représente le board)
     */
    Case(int row, int column, Piece piece, Board board, boolean isWhite)
    {
        this.row = row;
        this.column = column;
        this.piece = piece;
        this.board = board;
        this.isWhite = isWhite;
    }

    /**
     * Case
     * Constructeur de copie
     *
     * @param old (case d'origine)
     */
    Case(Case old)
    {
        this.row = old.row;
        this.column = old.column;
        this.piece = old.piece;
        this.board = old.board;
        this.isWhite = old.isWhite;
    }
    Case(){}


    // getters & setters
    int getRow() {
        return row;
    }
    int getColumn() {
        return column;
    }
    Piece getPiece() {
        return piece;
    }
    void setPiece(Piece piece) {
        this.piece = piece;
    }
    Board getBoard() {
        return board;
    }
    boolean isWhite() {
        return isWhite;
    }
}