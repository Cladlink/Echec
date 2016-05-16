import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
  Created by cladlink on 06/04/16.
 */
public class ControlMenu implements ActionListener
{
    private Model model;
    private Vue vue;

    public ControlMenu(Model model, Vue vue)
    {
        this.model = model;
        this.vue = vue;
        //vue.setControlMenu(this);
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
            /*int modePartie = vue.choixMode();
            model.setModePartie(modePartie);

            String pseudoAdv;
            String pseudo;

            do
            {
                pseudo = vue.askJOption("Joueur 1");
            }
            while (pseudo.length()==0 || pseudo.equals("anonymous"));
            do
            {
                pseudoAdv = vue.askJOption("Joueur 2");
            }
            while (Objects.equals(pseudo, pseudoAdv) || pseudoAdv.length()==0 || pseudoAdv.equals("anonymous"));
            //todo voir avec Domas pour fermer le Model
            model.lancementPartie(pseudo, pseudoAdv);
            model.getPartie().getBoard().majCasesAtteignable();
            vue.setVueEchiquier(new VueEchiquier(model.getPartie().getBoard(), model, vue));
            vue.creerWidgetPartie();
            vue.setControlButtonMenu(new ControlButton(model, vue));
            vue.setVisible(true);*/
        }
        else if (e.getSource() == vue.getQuitter())
        {
            boolean sauvegarde = vue.boolJOptionPane("Voulez-vous sauvegarder avant de quitter ?");
            if (sauvegarde)
            {
                model.getPartie().save();
                System.exit(0);
            }
            else
                System.exit(0);
        }
        else if (e.getSource() == vue.getUndo())
        {
            boolean undo = vue.boolJOptionPane("voulez-vous annuler le dernier coup ?");
            if (undo)
            {
                model.getPartie().undo();
                vue.repaint();
            }
        }
        else if (e.getSource() == vue.getHistorique())
        {
            ArrayList<ArrayList<ArrayList<String>>> histo = model.getPartie().requeteHistorique();
            int choixFait = vue.choixHistorique(histo);
            ArrayList<ArrayList<String>> histoDesCoups = model.getPartie().requeteCoupsHistorique(choixFait);
            vue.afficherHistorique(histoDesCoups);
        }
        else if (e.getSource() == vue.getAide())
        {
            // todo Baptiste + Adonis
            // proposition d'un genre de petit tuto ? (à la toute fin)
        }
    }
}
