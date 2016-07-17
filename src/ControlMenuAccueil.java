import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 Created by mlucile on 12/05/16.
 */
class ControlMenuAccueil implements ActionListener
{
    private ControlPartie controlPartie;
    private Vue vue;
    private Accueil accueil;

    /**
     * ControlMenuAccueil
     * Controlleur du menu d'accueil
     *
     * @param accueil (model)
     * @param vue (vue)
     */
    ControlMenuAccueil(Accueil accueil, Vue vue, ControlPartie controlPartie)
    {
        this.accueil = accueil;
        this.vue = vue;
        vue.setButtonControl(this);
        this.controlPartie = controlPartie;
    }

    /**
     * actionPerformed
     * définit l'action de chaque bouton du menu d'accueil
     *
     * @param e (event de type clic)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String pseudo;
        if(e.getSource().equals(vue.getNouveauJoueur()))
        {
            pseudo = vue.messagePop("Entrez un nouveau pseudo :");
            if (pseudo == null)
                return;
            Vector<String> listeJoueurs = Joueur.listeJoueurs();
            for (String listeJoueur : listeJoueurs)
                if (listeJoueur.equals(pseudo))
                {
                    vue.jOptionMessage("Ce pseudo n'est pas disponible");
                    return;
                }
            Joueur.inscriptionJoueur(pseudo);
            vue.majListeJoueur();
            vue.afficherFormulaire();
        }
        else if(e.getSource().equals(vue.getNouveauJoueurRejoindreReseau()))
        {
            pseudo = vue.messagePop("Entrez un nouveau pseudo :");
            if (pseudo == null)
                return;
            Vector<String> listeJoueurs = Joueur.listeJoueurs();
            for (String listeJoueur : listeJoueurs)
                if (listeJoueur.equals(pseudo))
                {
                    vue.jOptionMessage("Ce pseudo n'est pas disponible");
                    return;
                }
            Joueur.inscriptionJoueur(pseudo);
            vue.majListeJoueur();
            vue.creerWidgetRejoindrePartieReseau();
        }
        else if(e.getSource().equals(vue.getNouveauJoueurLancerReseau()))
        {
            pseudo = vue.messagePop("Entrez un nouveau pseudo :");
            if (pseudo == null)
                return;
            Vector<String> listeJoueurs = Joueur.listeJoueurs();
            for (String listeJoueur : listeJoueurs)
                if (listeJoueur.equals(pseudo))
                {
                    vue.jOptionMessage("Ce pseudo n'est pas disponible");
                    return;
                }
            Joueur.inscriptionJoueur(pseudo);
            vue.majListeJoueur();
            vue.creerWidgetFormulaireReseau();
        }
        else if( e.getSource().equals(vue.getRetourMenu()) )
            vue.afficherMenu();
        else if(e.getSource().equals(vue.getRejoindrePartie()))
            vue.creerWidgetRejoindrePartieReseau();
        else if(e.getSource().equals(vue.getNouvellePartie()))
            vue.afficherFormulaire();
        else if(e.getSource().equals(vue.getPartieRandom()))
        {
            accueil.lancementPartie("anonymous", "anonymous",1 ,1 , 1, false);
            vue.setVueEchiquier(new VueEchiquier(accueil, vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlPartie(accueil, vue));
            vue.setControlMotion(new ControlPartie(accueil, vue));
            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenuPartie(accueil, vue));
            vue.setVisible(true);
        }
        else if( e.getSource().equals(vue.getLancerPartie()) )
        {
            int modePartie = Integer.parseInt(vue.getGrTypePartie().getSelection().getActionCommand());
            int choixJoueurB = Integer.parseInt(vue.getGrSkinBlanc().getSelection().getActionCommand());
            int choixJOueurN = Integer.parseInt(vue.getGrSkinNoir().getSelection().getActionCommand());
            String pseudoB = vue.getListeJoueursBlancs().getSelectedItem().toString();
            String pseudoN = vue.getListeJoueursNoirs().getSelectedItem().toString();

            if( pseudoB.equals(pseudoN) )
            {
                vue.jOptionMessage("Vous ne pouvez pas jouer contre vous-même !");
                return;
            }
            accueil.lancementPartie(pseudoB, pseudoN, choixJoueurB, choixJOueurN, modePartie, false);
            initPartie();

        }
        else if(e.getSource().equals(vue.getLancerPartieReseau()))
        {
            int choixJoueur = Integer.parseInt(vue.getGrSkinBlanc().getSelection().getActionCommand());
            String pseudoJoueur = vue.getListeJoueursBlancs().getSelectedItem().toString();
            vue.setEnabled(false);
            accueil.initPartieReseau();

            ThreadPartie tp = new ThreadPartie(
                    accueil.getPartie(), controlPartie, 1234, true, "127.0.0.1", choixJoueur, pseudoJoueur, 1, this);
            tp.start();
        }
        else if(e.getSource().equals(vue.getRejoindrePartieReseau()))
        {
            int choixJoueur = Integer.parseInt(vue.getGrSkinBlanc().getSelection().getActionCommand());
            String pseudoJoueur = vue.getListeJoueursNoirs().getSelectedItem().toString();
            accueil.setAdresseIpReseau(vue.messagePop("Entrez l'adresse IP de l'adversaire :"));
            if(accueil.getAdresseIpReseau() == null)
                return;
            vue.jOptionMessage("Veuillez patienter...");
            vue.setEnabled(false);
            accueil.initPartieReseau();
            ThreadPartie threadClient = new ThreadPartie(accueil.getPartie(), this.controlPartie, 1234, false,
                    accueil.getAdresseIpReseau(), choixJoueur, pseudoJoueur, this);
            threadClient.start();
        }
        else if(e.getSource().equals(vue.getCredit()))
            vue.jOptionMessage("Jeu développé par : \n" +
                    "Michael BOUTBOUL\n" +
                    "Marie-Lucile CANIARD\n" +
                    "Sylvain GUYOT \n" +
                    "Kevin LIMACHER\n" +
                    "Gabriel MERCIER\n" +
                    "Adonis N'DOLO\n" +
                    "Baptiste SORIN.");
        else if (e.getSource().equals(vue.getQuitterJeu()))
            System.exit(0);
        else if(e.getSource().equals(vue.getChargerPartie()))
        {
            vue.historiquePartie();

            if(accueil.getPartieSelectionneePourChargement() == null
                    || accueil.getPartieSelectionneePourChargement().equals(""))
            {
                System.err.println("escape");
                return;
            }
            else
                accueil.load(accueil.getPartieSelectionneePourChargement().split(" ")[0]);

            vue.setVueEchiquier(new VueEchiquier(accueil, vue));
            vue.creerWidgetPartie();
            accueil.getPartie().getBoard().majCasesAtteignable();
            vue.setControlButtonMenu(new ControlPartie(accueil, vue));

            vue.initMenuPartie();
            vue.setControlMenu(new ControlMenuPartie(accueil, vue));
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
            vue.creerWidgetFormulaireReseau();
        else if (e.getSource().equals(vue.getHistoriquePartie()))
        {
            vue.choixHistoriqueAConsulter();
            if(accueil.getPartieAVisualiser() == null || accueil.getPartieAVisualiser().equals(""))
            {
                System.err.println("escape");
                return;
            }
            accueil.lancementPartie("joueurBlanc", "joueurNoir", 1, 1, 1, false);
            vue.afficherHistoriqueLocal();
        }
    }

    /**
     * initPartie
     * ensemble d'éléments nécessaire à la création d'une partie
     *
     */
    void initPartie()
    {
        controlPartie.setJoueurBlanc(accueil.getPartie().getJoueurBlanc().getPseudo());
        controlPartie.setJoueurNoir(accueil.getPartie().getJoueurNoir().getPseudo());
        vue.setVueEchiquier(new VueEchiquier(accueil, vue));
        vue.creerWidgetPartie();
        accueil.getPartie().getBoard().majCasesAtteignable();
        vue.setControlButtonMenu(controlPartie);
        vue.setControlMotion(controlPartie);
        vue.initMenuPartie();
        vue.setControlMenu(new ControlMenuPartie(accueil, vue));
        vue.setVisible(true);
        MusiqueChess.stopMedievalTheme();
    }
}