import javax.swing.*;
import java.awt.*;

/**
 *Created by mlucile on 08/06/16.
 */
class ChessRadioButton extends JRadioButton
{
    private Font police = new Font("CalligraphyFLF", Font.TRUETYPE_FONT, 28);

    /**
     * ChessRadioButton
     * défini le radio bouton héritant de JradioButton
     * @param text (définit le texte de l'objet)
     *
     */
    ChessRadioButton(String text)
    {
        super(text);
        setOpaque(false);
        setFont(police);
    }

    /**
     * ChessRadioButton
     * défini le radio bouton héritant de JradioButton
     * @param text (text lié au radio button)
     * @param isSelelcted (true si selectionné de base)
     *
     */
    ChessRadioButton(String text, boolean isSelelcted)
    {
        super(text, isSelelcted);
        setOpaque(false);
        setFont(police);
    }
}
