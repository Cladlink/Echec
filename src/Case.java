/**
  Created by cladlink on 06/04/16.
 */
public class Case
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
    public Case(int row, int column, Piece piece, Board board, boolean isWhite)
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
    public Case(Case old)
    {
        this.row = old.row;
        this.column = old.column;
        this.piece = old.piece;
        this.board = old.board;
        this.isWhite = old.isWhite;
    }
    public Case(){}


    // getters & setters
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public boolean isWhite() {
        return isWhite;
    }
    public void setWhite(boolean white) {
        isWhite = white;
    }
}