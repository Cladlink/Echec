import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/**
 Created by cladlink on 06/04/16.
 */
public class Echiquier extends JPanel
{
    private Model model = null;
    private Board board = null;
    private Graphics g = null;
    private ImageIcon bg = new ImageIcon("img/echec.jpg");
    private ImageIcon bgCimetiere = new ImageIcon("img/cimetiere.png");
    private int minuteBlanc, secondeBlanc, minuteNoir, secondeNoir;

    public Echiquier(Board board, Model model)
    {
        this.model = model;
        this.board = board;
        setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public Dimension getPreferredSize()
    {
        return new Dimension(8*board.getSizeCase(),8*board.getSizeCase());
    }

    @Override
    public void paintComponent(Graphics g)
    {
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
                        if ( model.getCasesAtteignables().get(k) == board.getPlateau()[i][j])
                        {
                            g.setColor(Color.GREEN);
                        }
                    }
                }
                g.fillRect(j * board.getSizeCase() + 360,
                        i * board.getSizeCase() + 50,
                        board.getSizeCase(),
                        board.getSizeCase());
            }
        }
        for (int i = 0; i < board.getPlateau().length; i++)
        {
            for (int j = 0; j < board.getPlateau().length; j++)
            {

                if (board.getPlateau()[i][j].getPiece() != null)
                {
                    g.drawImage(board.getPlateau()[i][j].getPiece().skin.getImage(),
                            j * board.getSizeCase() + 360,
                            i * board.getSizeCase() + 50,
                            null);
                }
            }
        }
        //timer
        g.setColor(Color.BLUE);
        g.fillRect(160, 80, 100, 50);
        g.fillRect(1110, 80, 100, 50);

        g.setColor(Color.BLACK);
        g.drawString(minuteBlanc + " : " + secondeBlanc, 190, 110);
        g.drawString(minuteNoir + " : " + secondeNoir, 1150, 110);

        //cimetiere

        //on met le fond d'ecran du cimetiere



        Partie partie = model.getPartie();
        int i, pionBlanc=0, pasPionBlanc=0, pionNoir=0, pasPionNoir=0;

        //cimetiere pute blanc
        g.drawImage(bgCimetiere.getImage(),110, 150, 200, 500, null);
        for(i = 0; i<partie.getCimetiereBlanc().size(); i++){
            if (partie.getCimetiereBlanc().get(i) instanceof Pion) {
                g.drawImage(partie.getCimetiereBlanc().get(i).skin.getImage(), 120, 200 + 50 * pionBlanc, 100, 100, null);
                pionBlanc++;
            } else {
                g.drawImage(partie.getCimetiereBlanc().get(i).skin.getImage(), 210, 200 + 50 * pasPionBlanc, 100, 100, null);
                pasPionBlanc++;
            }
        }

        //cimetiere noir
        g.drawImage(bgCimetiere.getImage(),1060, 150, 200, 500,null);
        for(i = 0; i<partie.getCimetiereNoir().size(); i++)
        {
            if (partie.getCimetiereNoir().get(i) instanceof Pion)
            {
                g.drawImage(partie.getCimetiereNoir().get(i).skin.getImage(), 1070, 200 + 50 * pionNoir, 100, 100, null);
                pionNoir++;
            }
            else
            {
                g.drawImage(partie.getCimetiereNoir().get(i).skin.getImage(), 1160, 200 + 50 * pasPionNoir, 100, 100, null);
                pasPionNoir++;
            }
        }
    }
}
