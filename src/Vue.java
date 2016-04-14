import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Vue extends JFrame
{
    private Echiquier echiquier = null;
    private Model model  = null;
    /**
     * Vue (Constructeur)
     * construit la vue d'échec.
     *
     * @param model (...model...)
     */
    public Vue(Model model)
    {
        this.model = model;
        model.lancementPartie();
        initAttribut();
        creerWidget();
        setUndecorated(true);
        //setAlwaysOnTop(true);
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
        echiquier = new Echiquier(model.getPartie().getBoard(), model);
    }

    /**
     * creerWidget
     * dessine la vue
     */
    public void creerWidget()
    {
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
        echiquier.addMouseListener(e);
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


    // getters & setters
    public Echiquier getEchiquier() {
        return echiquier;
    }
    public void setEchiquier(Echiquier echiquier) {
        this.echiquier = echiquier;
    }
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }
}
