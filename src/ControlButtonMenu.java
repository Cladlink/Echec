import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
class ControlButtonMenu implements ActionListener
{
    private ControlButton controlButton;
    private Vue vue;
    private Accueil accueil;

    /**
     *
     * @param accueil ()
     * @param vue ()
     */
    ControlButtonMenu(Accueil accueil, Vue vue, ControlButton controlButton)
    {
        this.accueil = accueil;
        this.vue = vue;
        vue.setButtonControl(this);
        this.controlButton = controlButton;
    }

    /**
     *
     * @param e ()
     */
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
        else if(e.getSource().equals(vue.getRejoindrePartieReseau()))
        {
            accueil.setAdresseIpReseau(vue.messagePop("Entrez l'adresse IP de l'adversaire :"));
            accueil.setPseudoReseau(vue.getListeJoueursNoirs().getSelectedItem().toString());
            accueil.setChoixSkinReseau(Integer.parseInt(vue.getGrSkinBlanc().getSelection().getActionCommand()));
            // ajout SD : à modifier, notamment sur les pseudos puisque à priori le joueur client
            // ne connait pas forcément le pseudo de l'autre.

            //accueil.rejoindrePartieReseau(pseudo, skin); //modePartie);
            // ajout SD : voir pour le choix de qui est blanc/noir (aléatoire)
            // + skins
            if(accueil.getAdresseIpReseau() == null)
                return;

            accueil.rejoindrePartieReseau(accueil.getPseudoReseau(), accueil.getChoixSkinReseau());
            vue.setVueEchiquier(new VueEchiquier(accueil.getPartie().getBoard(), accueil, vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlButton(accueil, vue));

            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenu(accueil, vue));
            vue.setVisible(true);

            vue.setEnabled(false);
            //ThreadPartie threadClient = new ThreadPartie(accueil.getPartie(), this.controlButton, 1234, false,
                    //accueil.getAdresseIpReseau());

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
        else if( e.getSource().equals(vue.getRetourMenu()) )
            vue.afficherMenu();
        else if(e.getSource().equals(vue.getRejoindrePartie()))
            vue.creerWidgetRejoindrePartieReseau();

        else if( e.getSource().equals(vue.getLancerPartie()) )
        {
            int modePartie = Integer.parseInt(vue.getGrTypePartie().getSelection().getActionCommand());
            boolean netPartie = false;
            int choixJoueurB = Integer.parseInt(vue.getGrSkinBlanc().getSelection().getActionCommand());
            int choixJOueurN = Integer.parseInt(vue.getGrSkinNoir().getSelection().getActionCommand());
            String pseudoB = vue.getListeJoueursBlancs().getSelectedItem().toString();
            String pseudoN = vue.getListeJoueursNoirs().getSelectedItem().toString();

            if( pseudoB.equals(pseudoN) )
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
                MusiqueChess.stopMedievalTheme();

	    // ajout SD 
	    /* TO DO:
	       si partie en réseau :
	          - invalider la vue (setEnable ??)
	          - créer un ThreadPartie serveur
	     */
        }
        else if(e.getSource().equals(vue.getLancerPartieReseau()))
        {
            int choixJoueur = Integer.parseInt(vue.getGrSkinBlanc().getSelection().getActionCommand());
            String pseudoJoueur = vue.getListeJoueursBlancs().getSelectedItem().toString();
            vue.setEnabled(false);

            //ThreadPartie tp = new ThreadPartie(accueil.getPartie(), controlButton, 1234, true, "127.0.0.1");

            /*accueil.lancementPartieReseau(pseudoJoueur, choixJoueur);
            vue.setVueEchiquier(new VueEchiquier(accueil.getPartie().getBoard(), accueil, vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlButton(accueil, vue));
            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenu(accueil, vue));
            vue.setVisible(true);*/
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
        else if(e.getSource().equals(vue.getCreerPartieReseau()))
        {
            vue.creerWidgetFormulaireReseau();
        }
    }
}