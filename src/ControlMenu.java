import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
  Created by cladlink on 06/04/16.
 */
class ControlMenu implements ActionListener
{
    private Accueil accueil;
    private Vue vue;

    /**
     * ControlMenu todo
     * @param accueil ()
     * @param vue ()
     */
    ControlMenu(Accueil accueil, Vue vue)
    {
        this.accueil = accueil;
        this.vue = vue;
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
        if (e.getSource().equals(vue.getQuitter()))
        {
            boolean sauvegarde = vue.boolJOptionPane("Voulez-vous sauvegarder avant de quitter ?");
            if (sauvegarde)
            {
                accueil.getPartie().save();
                System.exit(0);
            }
            else
                System.exit(0);
        }
        else if (e.getSource().equals(vue.getUndo()))
        {
            boolean undo = vue.boolJOptionPane("voulez-vous annuler le dernier coup ?");
            if (undo)
            {
                accueil.getPartie().undo();
                vue.repaint();
            }
        }
        else if (e.getSource().equals(vue.getHistorique()))
        {
            vue.afficherHistoriqueLocal();
        }
        else if (e.getSource().equals(vue.getRetourMenuPrincipal()))
        {
            ChronoMode.setHorsJeu(true);
            vue.setJMenuBar(null);
            vue.afficherMenu();
        }
    }
}