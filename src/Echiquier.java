import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
  Created by cladlink on 06/04/16.
 */
public class Echiquier extends JPanel
{
    private Board board;

    public Echiquier(Board board) {
        this.board = board;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(8*board.getSizeCase(),8*board.getSizeCase());
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPlateau()[i][j].isWhite())
                    g.setColor(Color.WHITE);
                else
                    g.setColor(Color.BLUE);
                g.fillRect(i * board.getSizeCase() + 360,
                        j * board.getSizeCase() + 50,
                        board.getSizeCase(),
                        board.getSizeCase());
            }
        }
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
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
        g.setColor(Color.RED);
        g.drawRect(210, 60, 100, 50);
        g.drawRect(1060, 60, 100, 50);

        //cimetiere
        g.drawRect(210, 150, 100, 500);
        g.drawRect(1060, 150, 100, 500);
    }
}