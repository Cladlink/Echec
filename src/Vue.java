import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

class Vue extends JFrame
{

    /**
     * Vue (Constructeur)
     * construit la vue d'échec.
     *
     * @param model (...model...)
     */
    Vue(Model model)
    {
       /* this.model = model;
        initAttribut();
        setUndecorated(true);
        //setAlwaysOnTop(true);
        setResizable(false);
        setName("Chess");
        model.lancementPartie("anonymous", "anonymous");
        model.getPartie().getBoard().majCasesAtteignable();
        vueEchiquier = new VueEchiquier(model.getPartie().getBoard(), model, this);
        creerWidget();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int)tk.getScreenSize().getWidth();
        int ySize = (int)tk.getScreenSize().getHeight();
        setSize(xsize, ySize);*/
    }

}
