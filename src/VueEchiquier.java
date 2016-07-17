import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 Created by cladlink on 06/04/16.
 */
class VueEchiquier extends JPanel
{
    private Vue vue;
    private Accueil accueil;
    private Board board;

    //va servir a savoir quand il y a eu un mouvement
    private Case[][] plateau;

    private ImageIcon backGround;
    private ImageIcon deplacement;
    private ImageIcon deplacementAttaque;

    private VueGraveyard cimetiereBlanc, cimetiereNoir;
    private VueBarreStatut barreStatut;
    private VueTimer chronoBlanc, chronoNoir;
    private int pas;
    private boolean deplacementEnCours=false;

    private Timer timerAnim;

    /***
     * VueEchiquier
     * JPanel principal pour afficher une partie
     * @param accueil (model)
     * @param vue (vue)
     *
     */
    VueEchiquier(Accueil accueil, Vue vue)
    {
        this.vue = vue;
        this.accueil = accueil;
        this.board = accueil.getPartie().getBoard();

        plateau = new Case[board.getPlateau().length][board.getPlateau().length];
        initPlateauFictif();

        backGround = new ImageIcon("img/echiquier.png");
        deplacement = new ImageIcon("img/deplacement.png");
        deplacementAttaque = new ImageIcon("img/deplacementAttaque.png");

        setBorder(BorderFactory.createLineBorder(Color.black));
        cimetiereBlanc = new VueGraveyard(accueil.getPartie(), true);
        cimetiereNoir = new VueGraveyard(accueil.getPartie(), false);
        barreStatut = new VueBarreStatut(accueil.getPartie(), this);
        chronoBlanc = new VueTimer(accueil.getPartie(), true);
        chronoNoir = new VueTimer(accueil.getPartie(), false);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(8*board.getSizeCase(),8*board.getSizeCase());
    }
    /**
     * PaintComponent
     * Paint l'objet graphique. Regroupe tous les objets graphiques
     * @param g (boite à outil servant à peindre des éléments)
     *
     */
    @Override
    public void paintComponent(Graphics g)
    {
        boolean isDepPossible = false, isDepPossiblePiece = false;
        super.paintComponent(g);
        g.drawImage(backGround.getImage(), 0, 0, 1380, 768, null);
        if(accueil.getPartie().isTourBlanc())
        {
            g.setColor(Color.WHITE);
            g.drawRect(355, 15, 650, 650);
        }
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board.getPlateau()[i][j].isWhite())
                    g.setColor(Color.WHITE);
                else
                    g.setColor(Color.BLUE);

                if (accueil.getPartie().getCasesAtteignables() != null)
                {
                    for (int k = 0; k < accueil.getPartie().getCasesAtteignables().size(); k++)
                    {
                        if ( accueil.getPartie().getCasesAtteignables().get(k) == board.getPlateau()[i][j]
                                && accueil.getPartie().getCasesAtteignables().get(k).getPiece() == null)
                            isDepPossible = true;
                        else if ( accueil.getPartie().getCasesAtteignables().get(k) == board.getPlateau()[i][j]
                                && accueil.getPartie().getCasesAtteignables().get(k).getPiece() != null)
                            isDepPossiblePiece = true;
                    }
                }
                if (board.getPlateau()[i][j].isWhite())
                    g.fillRect(j * board.getSizeCase() + 360,
                            i * board.getSizeCase() + 20,
                            board.getSizeCase(),
                            board.getSizeCase());
                if( accueil.getPartie().getCaseDest() != null
                        && i == accueil.getPartie().getCaseDest().getRow())
                {
                    if(accueil.getPartie().getCaseDest() != null
                            && j == accueil.getPartie().getCaseDest().getColumn())
                    {
                        g.setColor(Color.BLUE);
                        g.fillRect(j * board.getSizeCase() + 360,
                                i * board.getSizeCase() + 20,
                                board.getSizeCase(),
                                board.getSizeCase());
                    }
                }
                if (isDepPossible)
                    g.drawImage(deplacement.getImage(),
                            j * board.getSizeCase() + 360,
                            i * board.getSizeCase() + 20,
                            null);
                else if (isDepPossiblePiece)
                    g.drawImage(deplacementAttaque.getImage(),
                            j * board.getSizeCase() + 360,
                            i * board.getSizeCase() + 20,
                            null);

                isDepPossible = false;
                isDepPossiblePiece = false;
            }
        }
        Piece pieceQuiABouge = null;

        //vérifie si il y a eu un mouvement
        for (int i = 0; i < plateau.length; i++)
            for (int j = 0; j < plateau.length; j++)
                if (plateau[i][j].getPiece() != null
                        && !plateau[i][j].getPiece().equals(board.getPlateau()[i][j].getPiece()))
                    pieceQuiABouge = plateau[i][j].getPiece();

        //dessine les pieces
        for (int i = 0; i < board.getPlateau().length; i++)
            for (int j = 0; j < board.getPlateau().length; j++)
                if (board.getPlateau()[i][j].getPiece() != null)
                {
                    Piece p = board.getPlateau()[i][j].getPiece();
                    if (accueil.getPartie().getCaseSrc()!=null
                            && p.equals(pieceQuiABouge)
                            && deplacementEnCours)
                        g.drawImage(p.skin.getImage(),
                                accueil.getPartie().getCaseSrc().getColumn() * board.getSizeCase() + p.getRelX() + 360,
                                accueil.getPartie().getCaseSrc().getRow() * board.getSizeCase() + p.getRelY() + 20,
                                null);
                    else
                        g.drawImage(p.skin.getImage(),
                                j * board.getSizeCase() +360,
                                i * board.getSizeCase() +20,
                                null);
                }
        cimetiereBlanc.paintMe(g, 110, 150);
        cimetiereNoir.paintMe(g, 1060, 150);
        barreStatut.paintMe(g, 0, getHeight());
        chronoBlanc.paintMe(g, 160, 80, vue);
        chronoNoir.paintMe(g, 1110, 80, vue);
    }

    /**
     * deplacementAnimation
     * classe gérant le parcours de la pièce de la case de départ à la case d'arrivée
     *
     * @param depart (case de départ)
     * @param arrive (case d'arrivée)
     * @param piece (pièce qui a bougé)
     */
    void deplacementAnimation(Case depart, Case arrive, Piece piece)
    {
        //reinitialise le rel de piece
        piece.setRelX(0);
        piece.setRelY(0);

        piece.setIncX(((arrive.getColumn()-depart.getColumn())*board.getSizeCase())/(10));
        piece.setIncY(((arrive.getRow()-depart.getRow())*board.getSizeCase())/(10));

        pas = 0;

        timerAnim = new Timer(30, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //calcule nouvelle position
                piece.setRelX(piece.getRelX()+piece.getIncX());
                piece.setRelY(piece.getRelY()+piece.getIncY());
                repaint();

                pas++;
                if (pas >=10)
                {
                    piece.setRelX(0);
                    piece.setRelY(0);

                    stopTimerAnim();
                }
            }
        });


        initPlateauFictif();
        timerAnim.start();
        deplacementEnCours = true;

    }

    /**
     * stopTimerAnim
     * Arrête l'animation
     */
    private void stopTimerAnim()
    {
        timerAnim.stop();
        deplacementEnCours=false;
        initPlateauFictif();
    }

    /**
     * initPlateauFictif
     * création d'un plateau afin d'identifier la pièce qui a bougé en comparant avec le plateau du jeu
     */
    private void initPlateauFictif()
    {
        for (int i = 0; i < plateau.length; i++)
            for (int j = 0; j < plateau.length; j++)
            {
                this.plateau[i][j] = new Case();
                this.plateau[i][j].setPiece(board.getPlateau()[i][j].getPiece());
            }
    }

    Board getBoard() { return board; }
    VueBarreStatut getBarreStatut() { return barreStatut; }
    VueTimer getChronoBlanc() { return chronoBlanc; }
    VueTimer getChronoNoir() { return chronoNoir; }
}