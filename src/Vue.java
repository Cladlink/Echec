import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Vue extends JFrame
{
    Board board = null;
    Echiquier echiquier = null;
    /**
     * Vue (Constructeur)
     * construit la vue d'échec.
     *
     * @param model (...model...)
     */
    public Vue(Model model)
    {
        model.lancementPartie();
        board = model.getPartie().getBoard();
        creerWidget();
        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);
        setName("Chess");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
                int xsize = (int)tk.getScreenSize().getWidth();
                int ySize = (int)tk.getScreenSize().getHeight();
        setSize(xsize, ySize);
    }


    /**
     * initAttribut
     * Instancie les attributs
     */
    public void initAttribut()
    {
    }

    /**
     * creerWidget
     * dessine la vue
     */
    public void creerWidget()
    {
        echiquier = new Echiquier(board);
        setContentPane(echiquier);

    }

    /**
     * setControlButton
     * ecoute les evenements sur les cases du plateau
     *
     * @param e (ecouteur de type MouseListener)
     */
    public void setControlButton(MouseListener e)
    {

    }

    /**
     * setControlMenu
     * ecoute les evenements du menu
     *
     * @param e (ecouteur de type ActionListener)
     */
    public void setControlMenu(ActionListener e)
    {

    }
}
