/**
  Created by cladlink on 06/04/16.
 */
public class Case
{

    private int row;
    private int column;
    private Piece piece = null;
    private Board board = null;
    private boolean isFree; // todo soit isFree soit on repasse à null ? je trouve cette variable useless...

    /**
     * Case (constructeur
     *
     * Initie la case à un emplacement précis
     *
     * @param row (représente la ligne)
     * @param column (représente la colonne)
     * @param piece (représente la pièce)
     * @param board (représente le board)
     */
    public Case(int row, int column, Piece piece, Board board)
    {
        this.row = row;
        this.column = column;
        this.piece = piece;
        this.board = board;
    }


    // getters / setters
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
    public boolean isFree() {
        return isFree;
    }
    public void setFree(boolean free) {
        isFree = free;
    }
}
