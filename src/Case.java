/**
  Created by cladlink on 06/04/16.
 */
public class Case
{

    private int row;
    private int column;
    private Piece piece = null;
    private Plateau plateau = null;
    private boolean isFree;

    /**
     * Case (constructeur
     *
     * Initie la case à un emplacement précis
     *
     * @param row (représente la ligne)
     * @param column (représente la colonne)
     * @param piece (représente la pièce)
     * @param plateau (représente le plateau)
     */
    public Case(int row, int column, Piece piece, Plateau plateau)
    {

    }

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

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
