import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
  Created by cladlink on 06/04/16.
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
        if (e.getSource() == vue.getNvlPart())
        {

            int modePartie = vue.choixMode();
            System.out.println(modePartie);
            model.setModePartie(modePartie);


            String pseudo = vue.nouvellePartie();
            // interrogation de la BDD pour savoir si le pseudo existe
            // si oui on récupere les données pour les mettres à jour dans la classe joueur correspondante
            // si non on créé une fiche dans la bdd et on initie le joueur par défaut



        }
    }
}
