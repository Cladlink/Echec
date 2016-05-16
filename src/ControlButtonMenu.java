import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mlucile on 12/05/16.
 */
public class ControlButtonMenu implements ActionListener
{
    private VueMainMenu vueMainMenu;
    private Model model;

    public ControlButtonMenu(Model model, VueMainMenu vueMainMenu)
    {
        this.model = model;
        this.vueMainMenu = vueMainMenu;
        vueMainMenu.setButtonControl(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(vueMainMenu.getNouveauJoueur()))
        {
            vueMainMenu.messagePop("Entrez un nouveau pseudo :");
        }
        else if(e.getSource().equals(vueMainMenu.getRejoindrePartie()))
        {
            vueMainMenu.messagePop("Entrez l'adresse IP de l'adversaire :");
        }
        else if(e.getSource().equals(vueMainMenu.getLancerPartie()))
            vueMainMenu.afficherFormulaire();
        else if(e.getSource().equals(vueMainMenu.getRetourMenu()))
        {
            vueMainMenu.afficherMenu();
        }

    }
}
