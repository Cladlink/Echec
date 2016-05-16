import javax.swing.*;
import java.awt.*;

/**
 Created by cladlink on 06/04/16.
 */
public class VueEchiquier extends JPanel
{
    private Vue vue;
    private Model model;
    private Board board;

    private ImageIcon bg;
    private ImageIcon deplacement;
    private ImageIcon deplacementAttaque;

    private VueGraveyard gyBlanc, gyNoir;
    private VueBarreStatut bs;
    private VueTimer chronoBlanc, chronoNoir;

    private Graphics g;

    public VueEchiquier(Board board, Model model, Vue vue)
    {
        this.vue = vue;
        this.model = model;
        this.board = board;

        bg = new ImageIcon("img/echec.jpg");
        deplacement = new ImageIcon("img/deplacement.png");
        deplacementAttaque = new ImageIcon("img/deplacementAttaque.png");

        setBorder(BorderFactory.createLineBorder(Color.black));
        gyBlanc = new VueGraveyard(model.getPartie(), true);
        gyNoir = new VueGraveyard(model.getPartie(), false);
        bs = new VueBarreStatut(model.getPartie(), this);
        chronoBlanc = new VueTimer(model.getPartie(), true);
        chronoNoir = new VueTimer(model.getPartie(), false);

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

                if (model.getCasesAtteignables() != null)
                {
                    for (int k = 0; k < model.getCasesAtteignables().size(); k++)
                    {
                        if ( model.getCasesAtteignables().get(k) == board.getPlateau()[i][j]
                                && model.getCasesAtteignables().get(k).getPiece() == null)
                           isDepPossible = true;
                        else if ( model.getCasesAtteignables().get(k) == board.getPlateau()[i][j]
                                && model.getCasesAtteignables().get(k).getPiece() != null)
                            isDepPossiblePiece = true;
                    }
                }
                // on peint la case, puis, on dessine l'image, noire si la case est vide, rouge si elle est pleine.
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
       // chronoBlanc.paintMe(g, 160, 80, vue);
       // chronoNoir.paintMe(g, 1110, 80, vue);
    }
}