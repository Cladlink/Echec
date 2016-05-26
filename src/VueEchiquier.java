import javax.swing.*;
import java.awt.*;

/**
 Created by cladlink on 06/04/16.
 */
class VueEchiquier extends JPanel
{
    private Vue vue;
    private Accueil accueil;
    private Board board;

    private ImageIcon bg;
    private ImageIcon deplacement;
    private ImageIcon deplacementAttaque;

    private VueGraveyard gyBlanc, gyNoir;
    private VueBarreStatut bs;
    private VueTimer chronoBlanc, chronoNoir;

    private Graphics g;

    public VueEchiquier(Board board, Accueil accueil, Vue vue)
    {
        this.vue = vue;
        this.accueil = accueil;
        this.board = board;

        bg = new ImageIcon("img/echiquier.png");
        deplacement = new ImageIcon("img/deplacement.png");
        deplacementAttaque = new ImageIcon("img/deplacementAttaque.png");

        setBorder(BorderFactory.createLineBorder(Color.black));
        gyBlanc = new VueGraveyard(accueil.getPartie(), true);
        gyNoir = new VueGraveyard(accueil.getPartie(), false);
        bs = new VueBarreStatut(accueil.getPartie(), this);
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
     *
     * @param g (boite à outil servant à peindre des éléments)
     */
    @Override
    public void paintComponent(Graphics g)
    {
        boolean isDepPossible = false, isDepPossiblePiece = false;
        super.paintComponent(g);
        g.drawImage(bg.getImage(), 0, 0, 1380, 768, null);
        this.g = g;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (board.getPlateau()[i][j].isWhite())
                    g.setColor(Color.WHITE);
                else
                    g.setColor(Color.BLUE);

                if (accueil.getCasesAtteignables() != null)
                {
                    for (int k = 0; k < accueil.getCasesAtteignables().size(); k++)
                    {
                        if ( accueil.getCasesAtteignables().get(k) == board.getPlateau()[i][j]
                                && accueil.getCasesAtteignables().get(k).getPiece() == null)
                           isDepPossible = true;
                        else if ( accueil.getCasesAtteignables().get(k) == board.getPlateau()[i][j]
                                && accueil.getCasesAtteignables().get(k).getPiece() != null)
                            isDepPossiblePiece = true;
                    }
                }
                if (board.getPlateau()[i][j].isWhite())
                    g.fillRect(j * board.getSizeCase() + 360,
                            i * board.getSizeCase() + 20,
                            board.getSizeCase(),
                            board.getSizeCase());
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


        // on dessine toutes les pièces
        for (int i = 0; i < board.getPlateau().length; i++)
        {
            for (int j = 0; j < board.getPlateau().length; j++)
            {
                if (board.getPlateau()[i][j].getPiece() != null)
                {
                    g.drawImage(board.getPlateau()[i][j].getPiece().skin.getImage(),
                            j * board.getSizeCase() + 360,
                            i * board.getSizeCase() + 20,
                            null);
                }
            }
        }
        gyBlanc.paintMe(g, 110, 150);
        gyNoir.paintMe(g, 1060, 150);
        bs.paintMe(g, 0, getHeight());
        chronoBlanc.paintMe(g, 160, 80, vue);
        chronoNoir.paintMe(g, 1110, 80, vue);
    }

    public Vue getVue() {
        return vue;
    }

    public void setVue(Vue vue) {
        this.vue = vue;
    }

    public Accueil getAccueil() {
        return accueil;
    }

    public void setAccueil(Accueil accueil) {
        this.accueil = accueil;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ImageIcon getBg() {
        return bg;
    }

    public void setBg(ImageIcon bg) {
        this.bg = bg;
    }

    public ImageIcon getDeplacement() {
        return deplacement;
    }

    public void setDeplacement(ImageIcon deplacement) {
        this.deplacement = deplacement;
    }

    public ImageIcon getDeplacementAttaque() {
        return deplacementAttaque;
    }

    public void setDeplacementAttaque(ImageIcon deplacementAttaque) {
        this.deplacementAttaque = deplacementAttaque;
    }

    public VueGraveyard getGyBlanc() {
        return gyBlanc;
    }

    public void setGyBlanc(VueGraveyard gyBlanc) {
        this.gyBlanc = gyBlanc;
    }

    public VueGraveyard getGyNoir() {
        return gyNoir;
    }

    public void setGyNoir(VueGraveyard gyNoir) {
        this.gyNoir = gyNoir;
    }

    public VueBarreStatut getBs() {
        return bs;
    }

    public void setBs(VueBarreStatut bs) {
        this.bs = bs;
    }

    public VueTimer getChronoBlanc() {
        return chronoBlanc;
    }

    public void setChronoBlanc(VueTimer chronoBlanc) {
        this.chronoBlanc = chronoBlanc;
    }

    public VueTimer getChronoNoir() {
        return chronoNoir;
    }

    public void setChronoNoir(VueTimer chronoNoir) {
        this.chronoNoir = chronoNoir;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }
}