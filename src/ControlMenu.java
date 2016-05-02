import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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

            String pseudoAdv;
            String pseudo = vue.askJOption("Joueur 1");

            do
            {
                pseudoAdv = vue.askJOption("Joueur 2");
            }
            while (Objects.equals(pseudo, pseudoAdv));
            // todo Michael
            // le joueur 1 doit être différent du joueur 2 (logique)

            // interrogation de la BDD pour savoir si les pseudos existe
            // si oui on récupere les données pour les mettres à jour dans la classe joueur correspondante
            // si non on créé une fiche dans la bdd et on initie le joueur par défaut

            // gérer le cas ou la partie est normal (pas d'affichage des chronos
            // gérer le cas ou les coups sont en temps limités (chrono de 30sec par tour (ou laisser choix au joueur)
            // gérér le cas où la partie est en temps limités (chrono de 15 min de jeu par joueur)
            /*model.lancementPartie();
            model.majCasesAtteignable();
            vue.setEchiquier(new Echiquier(model.getPartie().getBoard(), model));
            vue.creerWidget();
            vue.setControlButton(new ControlButton(model, vue));
            vue.setVisible(true);*/
        }
        else if (e.getSource() == vue.getRejPart())
        {
            // partie en réseau voir à la fin
        }
        else if (e.getSource() == vue.getSvPart())
        {
            // todo Marie-Lucile
            // gérer avec la requete de sauvegarde de Marie Lucile
            model.getPartie().save();
        }
        else if (e.getSource() == vue.getLdPart())
        {
            // todo Kevin
            // gérer avec la requête de chargement de Kevin
            model.getPartie().load();
        }
        else if (e.getSource() == vue.getQuitter())
        {
            // sauvegarde de MarieLucile
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
                // todo code undo de Sylvain + baptiste
                // tu dois récupérer le dernier coup dans l'historique
                // faire le chemin arrière ce qui implique de sortir éventuellement une pièce du cimetiere
                // la remettre en jeu
                // et annuler un déplacement
                // le coup annulé doit bien sûr être enlevé de l'historique
            }
        }
        else if (e.getSource() == vue.getHistorique())
        {
            // todo Kevin
            // afficher un select (assez proche du load)
        }
        else if (e.getSource() == vue.getaPropos())
        {
            // todo gabriel
            // JOptionPane avec les personnes qui ont codé le jeu
        }
        else if (e.getSource() == vue.getAide())
        {
            // todo MarieLucile + Adonis
            // proposition d'un genre de petit tuto ? (à la toute fin)
        }
    }
}
