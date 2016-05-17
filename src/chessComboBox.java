import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 Created by cladlink on 17/05/16.
 */
class chessComboBox extends JComboBox
{

    chessComboBox(Vector<String> liste)
    {
        super(liste);

        Font policeChoix = new Font("CalligraphyFLF", Font.TRUETYPE_FONT, 22);
        setFont(policeChoix);
        setBackground(new Color(0,0,0));

        setFocusable(false);
        setForeground(new Color(255, 255, 255));
    }
}
