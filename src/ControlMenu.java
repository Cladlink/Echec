import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cladlink on 06/04/16.
 */
public class ControlMenu extends Control implements ActionListener
{

    public ControlMenu(Model model, Vue vue)
    {
        super(model, vue);
        vue.setControlMenu(this);
    }

    /**
     * actionPerformed
     * définir les actions de chaque élément du menu
     *
     * @param e (contient un evenement)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
