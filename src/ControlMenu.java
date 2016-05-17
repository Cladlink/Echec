import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
  Created by cladlink on 06/04/16.
 */
public class ControlMenu implements ActionListener
{
    private Accueil accueil;
    private Vue vue;

    public ControlMenu(Accueil accueil, Vue vue)
    {
        this.accueil = accueil;
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
        /*if (e.getSource() == vue.getNvlPart())
        {
            int modePartie = vue.choixMode();
            accueil.setModePartie(modePartie);

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
            //todo voir avec Domas pour fermer le Accueil
            accueil.lancementPartie(pseudo, pseudoAdv);
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setVueEchiquier(new VueEchiquier(accueil.getPartie().getBoard(), accueil, vue));
            vue.creerWidgetPartie();
            vue.setControlButtonMenu(new ControlButton(accueil, vue));
            vue.setVisible(true);
        }*/
        if (e.getSource() == vue.getQuitterJeu())
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
        else if (e.getSource() == vue.getUndo())
        {
            boolean undo = vue.boolJOptionPane("voulez-vous annuler le dernier coup ?");
            if (undo)
            {
                accueil.getPartie().undo();
                vue.repaint();
            }
        }
        else if (e.getSource() == vue.getHistorique())
        {
            ArrayList<String> ListhistoriqueLocal = accueil.getPartie().getHistorique();
            vue.afficherHistoriqueLocal(ListhistoriqueLocal);
        }
    }
}