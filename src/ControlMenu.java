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
     * ControlMenu
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
            if (!(accueil.getPartie().getHistorique().size() == 0))
            {
                boolean sauvegarde = vue.boolJOptionPane("Voulez-vous sauvegarder avant de quitter ?");
                if (sauvegarde)
                {
                    if (!accueil.getPartie().save())
                        vue.jOptionMessage("Vous ne pouvez pas enregistrer car vous avez déjà une partie interrompue.");
                    System.exit(0);
                }
            }
            else
                System.exit(0);
        }
        else if (e.getSource().equals(vue.getRetourMenuPrincipal()))
        {
            ChronoMode.setHorsJeu(true);
            if (!(accueil.getPartie().getHistorique().size() == 0))
            {
                boolean sauvegarde = vue.boolJOptionPane("Voulez-vous sauvegarder avant de quitter ?");
                if (sauvegarde)
                {
                    if(!accueil.getPartie().save())
                        vue.jOptionMessage("Vous ne pouvez pas enregistrer car vous avez déjà une partie interrompue.");
                    System.exit(0);
                }

            }
            else
                System.exit(0);

            vue.setJMenuBar(null);
            vue.afficherMenu();
        }
        else if (e.getSource().equals(vue.getUndo()))
        {
            boolean undo = vue.boolJOptionPane("Voulez-vous annuler le dernier coup ?");
            if (undo)
            {
                if(!accueil.getPartie().undo())
                    vue.jOptionMessage("Il n'y a plus de coup à annuler.");
                vue.repaint();
            }
        }
        else if (e.getSource().equals(vue.getHistorique()))
        {
            vue.afficherHistoriqueLocal();
        }
    }
}