import java.util.ArrayList;

/**
  Created by cladlink on 06/04/16.
 */
public class Board
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
    public Board(Partie partie)
    {
        plateau = new Case[row][column];
        this.partie = partie;

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

        roiBlanc = new Roi(plateau[7][4], true);
        Piece reineBlanche = new Reine(plateau[7][3], true);
        roiNoir = new Roi(plateau[0][4], false);
        Piece reineNoire = new Reine(plateau[0][3], false);

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
     * todo virer la vue
     * deplacer
     * deplace la pièce aprés avoir vérifier : si l'empalcement cible est vide ou non
     * doit vérifier si une pièce est mangée, faire les actions en conséquence
     *
     * @param caseCliquee (case qui a ... été cliquée)
     * @param destination (case ou la pièce doit se rendre)
     */
    public void deplacer(Case caseCliquee, Case destination, Vue vue)
    {
        // ajoute le coup dans l'historique
        partie.historiqueCoups(caseCliquee, destination);

        // si la case destination contient une pièce
        if (destination.getPiece() != null)
        {
            //si la pièce est blanche
            if (destination.getPiece().blanc)
            {
                partie.getPiecesBlanchesPlateau().remove(destination.getPiece());
                partie.getCimetiereBlanc().add(destination.getPiece());
                destination.setPiece(null);
            }
            // si la pièce est noire
            else
            {
                partie.getPiecesNoiresPlateau().remove(destination.getPiece());
                partie.getCimetiereNoir().add(destination.getPiece());
                destination.setPiece(null);
            }
        }
        // si on roque
        if (caseCliquee.getPiece() instanceof Roi)
        {
            Case[][] plateau = caseCliquee.getBoard().getPlateau();
            int row = caseCliquee.getRow();
            int column = caseCliquee.getColumn();
            Piece tourRoque;

            // petit roque
            if (Math.abs( column - destination.getColumn() ) == 2)
            {
                tourRoque = plateau[row][column + 3].getPiece();

                plateau[row][column + 3].setPiece(null);
                tourRoque.emplacementPiece = plateau[row][column + 1];
                plateau[row][column + 1].setPiece(tourRoque);
            }
            // grand roque
            else if (Math.abs( column - destination.getColumn() ) == 3)
            {
                tourRoque = plateau[row][column + 3].getPiece();

                plateau[row][column - 4].setPiece(null);
                tourRoque.emplacementPiece = plateau[row][column - 2];
                plateau[row][column - 2].setPiece(tourRoque);
            }
        }

        // deplace
        caseCliquee.getPiece().deplacer(destination);
        // test de la promotion
        if( ( ( destination.getRow() == 0
                && destination.getPiece().blanc )
                || ( destination.getRow() == 7)
                && !destination.getPiece().blanc )
                && destination.getPiece() instanceof Pion )
            vue.choixPiece( (Pion)destination.getPiece() );
        // todo fait appel à la vue. Est ce acceptable vu qu'on est dans le model?

    }
    /**
     * majCasesAtteignable
     * Mets à jour la variable représentant toutes les cases atteignables des pièces de l'adversaire
     *
     */
    public void majCasesAtteignable()
    {
        ArrayList<Piece> pieceEnJeu;
        if (partie.isTourBlanc())
            pieceEnJeu = partie.getPiecesBlanchesPlateau();
        else
            pieceEnJeu = partie.getPiecesNoiresPlateau();

        for (int i = 0; i < pieceEnJeu.size(); i++)
        {
            pieceEnJeu.get(i).casesAtteignables();
            pieceEnJeu.get(i).deplacementPossible();
        }
    }

    //getters & setters
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
    public Partie getPartie() {
        return partie;
    }
    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    public int getSizeCase() {
        return sizeCase;
    }
    public Piece getRoiNoir() {
        return roiNoir;
    }
    public void setRoiNoir(Piece roiNoir) {
        this.roiNoir = roiNoir;
    }
    public Piece getRoiBlanc() {
        return roiBlanc;
    }
    public void setRoiBlanc(Piece roiBlanc) {
        this.roiBlanc = roiBlanc;
    }
}