import javax.swing.*;
import java.awt.*;

/**
 Created by cladlink on 16/05/16.
 */
class transButton extends JButton
{
    String text;
    transButton(String text)
    {
        super(text, null);
        this.text = text;
        setBorderPainted(false);
        setSize(50, 20);
        setMaximumSize(new Dimension(150, 20));
        setBackground(Color.lightGray);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
    }
}
