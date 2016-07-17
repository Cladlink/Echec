import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 Created by cladlink on 16/05/16.
 */
class ChessButton extends JButton
{
    /**
     * ChessButton
     * Construit le bouton héritant de JButton (méthode factorisant les éléments personnalisés de chaque boutons).
     *
     * @param text (texte du bouton)
     */
    ChessButton(String text)
    {
        super(text, null);
        setBorderPainted(false);
        setSize(50, 20);
        setMaximumSize(new Dimension(150, 20));
        setBackground(Color.black);
        setForeground(Color.white);
        setFocusable(false);
        Font police = new Font("Cardinal", Font.BOLD, 27);
        setFont(police);

    }

    /**
     * paintComponent
     * paint le bouton
     *
     * @param g (pinceau graphique)
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isPressed())
        {
            g.setColor(g.getColor());
            g2.fillRect(3, 3, getWidth() - 6, getHeight() - 6);
        }
        super.paintComponent(g);
        g2.setColor(new Color(128, 0, 128));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1.2f));
        g2.draw(new RoundRectangle2D.Double(1, 1, (getWidth() - 3), (getHeight() - 3), 12, 8));
        g2.setStroke(new BasicStroke(1.5f));
        g2.drawLine(4, getHeight() - 3, getWidth() - 4, getHeight() - 3);
        g2.dispose();
    }
}