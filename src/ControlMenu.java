import javax.swing.*;
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

            String pseudoAdv;
            String pseudo = vue.askJOption("Joueur 1");

            do
            {
                pseudoAdv = vue.askJOption("Joueur 2");
            }
            while (Objects.equals(pseudo, pseudoAdv));
            //todo voir avec Domas pour fermer le Model
            model.lancementPartie();
            model.getPartie().getBoard().majCasesAtteignable();
            vue.setVueEchiquier(new VueEchiquier(model.getPartie().getBoard(), model));
            vue.creerWidget();
            vue.setControlButton(new ControlButton(model, vue));
            vue.setVisible(true);
        }
        else if (e.getSource() == vue.getRejPart())
        {
            // partie en réseau voir à la fin
        }
        else if (e.getSource() == vue.getSvPart())
        {
            model.getPartie().save();
        }
        else if (e.getSource() == vue.getLdPart())
        {
            model.getPartie().load();
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
                System.out.println("coucou");
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
        else if (e.getSource() == vue.getaPropos())
        {
            // done Gabriel
            vue.jOptionMessage("Jeu développé par : \n -Michael BOUTBOUL\n -Marie-Lucile CANIARD\n -Sylvain GUYOT" +
                    "\n -Kevin LIMACHER\n -Gabriel MERCIER\n -Adonis N'DOLO.");
        }
        else if (e.getSource() == vue.getAide())
        {
            // todo Baptiste + Adonis
            // proposition d'un genre de petit tuto ? (à la toute fin)
        }
    }
}
