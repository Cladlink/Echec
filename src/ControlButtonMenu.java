import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
class ControlButtonMenu implements ActionListener
{
    private Vue vue;
    private Accueil accueil;

    ControlButtonMenu(Accueil accueil, Vue vue)
    {
        this.accueil = accueil;
        this.vue = vue;
        vue.setButtonControl(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String pseudo ="";
        if(e.getSource().equals(vue.getNouveauJoueur()))
        {
            pseudo = vue.messagePop("Entrez un nouveau pseudo :");
            if (pseudo == null)
                return;

            Vector<String> listeJoueurs = Joueur.listeJoueurs();
            for(int i = 0; i < listeJoueurs.size(); i++)
            {
                if (listeJoueurs.get(i).equals(pseudo))
                {
                    vue.jOptionMessage("Ce pseudo n'est pas disponible");
                    return;
                }
            }
            Joueur.inscriptionJoueur(pseudo);
            vue.majListeJoueur();
            vue.afficherFormulaire();
        }
        else if(e.getSource().equals(vue.getRejoindrePartie()))
        {
            vue.messagePop("Entrez l'adresse IP de l'adversaire :");

	    // ajout SD : à modifier, notamment sur les pseudos puisque à priori le joueur client
	    // ne connait pas forcément le pseudo de l'autre.

	    accueil.rejoindrePartieReseau(pseudo, 1); //modePartie);
	    // ajout SD : voir pour le choix de qui est blanc/noir
	    // + skins
            vue.setVueEchiquier(new VueEchiquier(accueil.getPartie().getBoard(), accueil, vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlButton(accueil, vue));

            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenu(accueil, vue));
            vue.setVisible(true);

	    /* TO DO:
	       - invalider la vue (setEnable ??)
	       - créer un ThreadPartie client
	     */

	    
        }
        else if(e.getSource().equals(vue.getNouvellePartie()))
            vue.afficherFormulaire();
        else if(e.getSource().equals(vue.getPartieRandom()))
        {
            accueil.lancementPartie(
                    "anonymous", "anonymous",1 ,1 , 1, false);
            vue.setVueEchiquier(new VueEchiquier(accueil.getPartie().getBoard(), accueil, vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlButton(accueil, vue));

            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenu(accueil, vue));
            vue.setVisible(true);
        }
        else if(e.getSource().equals(vue.getRetourMenu()))
            vue.afficherMenu();
        else if(e.getSource().equals(vue.getLancerPartie()))
        {
            int modePartie = Integer.parseInt(vue.getGrTypePartie().getSelection().getActionCommand());
            boolean netPartie = Boolean.parseBoolean(vue.getGrReseau().getSelection().getActionCommand());
            int choixJoueurB = Integer.parseInt(vue.getGrSkinBlanc().getSelection().getActionCommand());
            int choixJOueurN = Integer.parseInt(vue.getGrSkinNoir().getSelection().getActionCommand());
            String pseudoB = vue.getListeJoueursBlancs().getSelectedItem().toString();
            String pseudoN = vue.getListeJoueursNoirs().getSelectedItem().toString();

            if(pseudoB.equals(pseudoN))
            {
                vue.jOptionMessage("Vous ne pouvez pas jouer contre vous-même !");
                return;
            }
            accueil.lancementPartie(pseudoB, pseudoN, choixJoueurB, choixJOueurN, modePartie, netPartie);
            vue.setVueEchiquier(new VueEchiquier(accueil.getPartie().getBoard(), accueil, vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlButton(accueil, vue));

            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenu(accueil, vue));
            vue.setVisible(true);

	    // ajout SD 
	    /* TO DO:
	       si partie en réseau :
	          - invalider la vue (setEnable ??)
	          - créer un ThreadPartie serveur
	     */
        }
        else if(e.getSource().equals(vue.getCredit()))
            vue.jOptionMessage("Jeu développé par : \n Michael BOUTBOUL\n Marie-Lucile CANIARD\n Sylvain GUYOT" +
                    "\n Kevin LIMACHER\n Gabriel MERCIER\n Adonis N'DOLO.");
        else if (e.getSource().equals(vue.getQuitterJeu()))
            System.exit(0);
        else if(e.getSource().equals(vue.getChargerPartie()))
        {
            vue.historiquePartie();
            if( accueil.getPartieSelectionneePourChargement() != null)
                accueil.load(accueil.getPartieSelectionneePourChargement().split(" ")[0]);
            else
            {
                System.err.println("escape");
                return;
            }
            vue.setVueEchiquier(new VueEchiquier( accueil.getPartie().getBoard(), accueil,
                    vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlButton(accueil, vue));

            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenu(accueil, vue));
            vue.setVisible(true);
        }
        else if(e.getSource().equals(vue.getStatsJoueur()))
        {
            vue.statistiquesJoueur();
            if (accueil.getPseudoChoisi() != null)
                vue.fenetreStatsJoueur(accueil.getPseudoChoisi());
            else
                System.err.println("escape");
        }
    }
}
