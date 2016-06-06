import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 *Created by mlucile on 12/05/16.
 */
class Vue extends JFrame
{
    private VueEchiquier vueEchiquier;
    private ControlButtonHistorique controlButtonHistorique;
    private Partie partieHisto;

    private JMenuItem quitter;
    private JMenuItem undo;
    private JMenuItem historique;
    private JMenuItem retourMenuPrincipal;

    private Accueil accueil;

    private int xSize;
    private int ySize;

    private JLabel titre;
    private JLabel joueur1;
    private JLabel joueur2;
    private JLabel typePartie;
    private JLabel skinBlanc;
    private JLabel skinNoir;
    private JLabel background;

    private ChessButton partieRandom;
    private ChessButton nouvellePartie;
    private ChessButton rejoindrePartie;
    private ChessButton nouveauJoueur;
    private ChessButton credit;
    private ChessButton chargerPartie;
    private ChessButton retourMenu;
    private ChessButton lancerPartie;
    private ChessButton quitterJeu;
    private ChessButton statsJoueur;
    private ChessButton creerPartieReseau;
    private ChessButton lancerPartieReseau;
    private ChessButton rejoindrePartieReseau;
    private ChessButton historiquePartie;

    private JRadioButton partieNormale;
    private JRadioButton partieTempsCoupsLimites;
    private JRadioButton partieTempsLimite;
    private JRadioButton skinBlancNormal;
    private JRadioButton skinBlancProfs;
    private JRadioButton skinBlancEleves;
    private JRadioButton skinNoirNormal;
    private JRadioButton skinNoirProfs;
    private JRadioButton skinNoirEleves;

    private ButtonGroup grTypePartie;
    private ButtonGroup grReseau;
    private ButtonGroup grSkinBlanc;
    private ButtonGroup grSkinNoir;

    private ChessComboBox listeJoueursBlancs;
    private ChessComboBox listeJoueursNoirs;

    private JButton suivant, precedent, retour;
    private JFrame vueHisto;

    /**
     * Vue
     * @param accueil (MVC)
     */
    Vue(Accueil accueil)
    {
        this.accueil = accueil;

        initAttribut();
        creerWidgetAccueil();

        setUndecorated(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        xSize = (int) tk.getScreenSize().getWidth();
        ySize = (int) tk.getScreenSize().getHeight();
        setSize(xSize, ySize);
        setTitle("Echec");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     *
     */
    private void initAttribut()
    {
        // Initialisation des variables
        titre = new JLabel(accueil.getTitreLabel());
        joueur1 = new JLabel(accueil.getJoueurBlancLabel());
        joueur2 = new JLabel(accueil.getJoueurNoirLabel());
        typePartie = new JLabel(accueil.getTypePartieLabel());
        skinBlanc = new JLabel(accueil.getSkinLabel());
        skinNoir = new JLabel(accueil.getSkinLabel());

        nouvellePartie = new ChessButton(accueil.getNouvellePartieTitre());
        rejoindrePartie = new ChessButton(accueil.getRejoindrePartieTitre());
        nouveauJoueur = new ChessButton(accueil.getNouveauJoueurTitre());
        credit = new ChessButton(accueil.getCreditTitre());
        chargerPartie = new ChessButton(accueil.getChargerPartieTitre());
        retourMenu = new ChessButton(accueil.getRetourMenuTitre());
        lancerPartie = new ChessButton(accueil.getLancerPartieTitre());
        quitterJeu = new ChessButton(accueil.getQuitterJeuTitre());
        partieRandom = new ChessButton(accueil.getPartieRandomTitre());
        statsJoueur = new ChessButton(accueil.getStatsJoueurTitre());
        creerPartieReseau = new ChessButton(accueil.getCreerPartieReseauTitre());
        lancerPartieReseau = new ChessButton(accueil.getLancerPartieReseauTitre());
        rejoindrePartieReseau = new ChessButton(accueil.getRejoindrePartieReseauTitre());
        historiquePartie = new ChessButton(accueil.getHistoriquePartieTitre());

        partieNormale = new JRadioButton(accueil.getPartieNormaleTitre(), true);
        partieNormale.setActionCommand("1");
        partieTempsCoupsLimites = new JRadioButton(accueil.getPartieTempsCoupsLimitesTitre());
        partieTempsCoupsLimites.setActionCommand("2");
        partieTempsLimite = new JRadioButton(accueil.getPartieTempsLimiteTitre());
        partieTempsLimite.setActionCommand("3");

        skinBlancNormal = new JRadioButton(accueil.getSkinBlancNormalTitre(), true);
        skinBlancNormal.setActionCommand("1");
        skinBlancProfs = new JRadioButton(accueil.getSkinBlancProfsTitre());
        skinBlancProfs.setActionCommand("2");
        skinBlancEleves = new JRadioButton(accueil.getSkinBlancElevesTitre());
        skinBlancEleves.setActionCommand("3");

        skinNoirNormal = new JRadioButton(accueil.getSkinNoirNormalTitre(), true);
        skinNoirNormal.setActionCommand("1");
        skinNoirProfs = new JRadioButton(accueil.getSkinNoirProfsTitre());
        skinNoirProfs.setActionCommand("2");
        skinNoirEleves = new JRadioButton(accueil.getSkinNoirElevesTitre());
        skinNoirEleves.setActionCommand("3");

        grTypePartie = new ButtonGroup();
        grReseau = new ButtonGroup();
        grSkinBlanc = new ButtonGroup();
        grSkinNoir = new ButtonGroup();

        listeJoueursBlancs = new ChessComboBox(accueil.majListeJoueur());
        listeJoueursNoirs = new ChessComboBox(accueil.majListeJoueur());

        // Création des groupes de RadioButton
        grTypePartie.add(partieNormale);
        grTypePartie.add(partieTempsCoupsLimites);
        grTypePartie.add(partieTempsLimite);

        grSkinBlanc.add(skinBlancNormal);
        grSkinBlanc.add(skinBlancProfs);
        grSkinBlanc.add(skinBlancEleves);
        grSkinNoir.add(skinNoirNormal);
        grSkinNoir.add(skinNoirProfs);
        grSkinNoir.add(skinNoirEleves);

        // Importe une nouvelle police d'écriture
        GraphicsEnvironment fontLabel = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsEnvironment fontTitre = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsEnvironment fontChoix = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try
        {
            fontLabel.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Cardinal.ttf")));
            fontTitre.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Ace Records.ttf")));
            fontChoix.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/CalligraphyFLF.ttf")));
        }
        catch (FontFormatException fe)
        {
            jOptionMessage("pas le bon format !");
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
        // Change la couleur de la police
        Color couleurLabel = new Color(128, 0, 128);
        Color couleurChoix = new Color(255, 255, 255);
        Color couleurColonneGauche = new Color(0, 0, 0);

        joueur2.setForeground(couleurLabel);
        joueur1.setForeground(couleurColonneGauche);
        typePartie.setForeground(couleurColonneGauche);
        skinBlanc.setForeground(couleurColonneGauche);
        skinNoir.setForeground(couleurLabel);

        titre.setForeground(new Color(96, 0, 99));
        partieNormale.setForeground(couleurChoix);
        partieTempsCoupsLimites.setForeground(couleurChoix);
        partieTempsLimite.setForeground(couleurChoix);
        skinBlancNormal.setForeground(couleurChoix);
        skinBlancProfs.setForeground(couleurChoix);
        skinBlancEleves.setForeground(couleurChoix);
        skinNoirNormal.setForeground(couleurChoix);
        skinNoirProfs.setForeground(couleurChoix);
        skinNoirEleves.setForeground(couleurChoix);

        // Change la police d'écriture
        Font policeTitre = new Font("Ace Records", Font.BOLD, 150);
        Font police = new Font("Cardinal", Font.BOLD, 27);
        Font policeChoix = new Font("CalligraphyFLF", Font.TRUETYPE_FONT, 28);
        titre.setFont(policeTitre);
        joueur1.setFont(police);
        joueur2.setFont(police);
        typePartie.setFont(police);
        skinBlanc.setFont(police);
        skinNoir.setFont(police);

        partieNormale.setFont(policeChoix);
        partieTempsCoupsLimites.setFont(policeChoix);
        partieTempsLimite.setFont(policeChoix);
        skinNoirNormal.setFont(policeChoix);
        skinNoirProfs.setFont(policeChoix);
        skinNoirEleves.setFont(policeChoix);
        skinBlancProfs.setFont(policeChoix);
        skinBlancNormal.setFont(policeChoix);
        skinBlancEleves.setFont(policeChoix);

        // Le fond de chaque élément est transparent
        listeJoueursBlancs.setOpaque(false);
        partieNormale.setOpaque(false);
        partieTempsCoupsLimites.setOpaque(false);
        partieTempsLimite.setOpaque(false);
        skinBlancNormal.setOpaque(false);
        skinBlancProfs.setOpaque(false);
        skinBlancEleves.setOpaque(false);
        skinNoirNormal.setOpaque(false);
        skinNoirProfs.setOpaque(false);
        skinNoirEleves.setOpaque(false);
    }

    /**
     * majListeJoueur
     * met à jour la liste des joueurs pour le formulaire
     *
     */
    void majListeJoueur()
    {
        listeJoueursBlancs = new ChessComboBox(accueil.majListeJoueur());
        listeJoueursNoirs = new ChessComboBox(accueil.majListeJoueur());
    }

    /**
     *
     */
    void creerWidgetFormulaire()
    {
        JPanel titreJeu = new JPanel();
        titreJeu.setOpaque(false);
        titreJeu.add(titre);

        JPanel formulaire = new JPanel(new GridLayout(16, 2, 100, 0));
        formulaire.setOpaque(false);
        formulaire.add(joueur1);
        formulaire.add(joueur2);
        formulaire.add(listeJoueursBlancs);
        formulaire.add(listeJoueursNoirs);
        formulaire.add(skinBlanc);
        formulaire.add(skinNoir);
        formulaire.add(skinBlancNormal);
        formulaire.add(skinNoirNormal);
        formulaire.add(skinBlancProfs);
        formulaire.add(skinNoirProfs);
        formulaire.add(skinBlancEleves);
        formulaire.add(skinNoirEleves);
        formulaire.add(typePartie);
        formulaire.add(Box.createVerticalGlue());
        formulaire.add(partieNormale);
        formulaire.add(Box.createVerticalGlue());
        formulaire.add(partieTempsCoupsLimites);
        formulaire.add(Box.createVerticalGlue());
        formulaire.add(partieTempsLimite);

        JPanel nouveauJ = new JPanel( new GridLayout(9, 1, 0, 30) );
        nouveauJ.setOpaque(false);
        nouveauJ.add(nouveauJoueur);
        nouveauJ.add(lancerPartie);
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(retourMenu);

        JPanel nouveauJP = new JPanel();
        nouveauJP.add(Box.createHorizontalStrut(100));
        nouveauJP.setOpaque(false);
        nouveauJP.add(nouveauJ);

        JPanel organisation = new JPanel(new BorderLayout());
        organisation.setOpaque(false);
        organisation.add(titreJeu, BorderLayout.NORTH);
        organisation.add(formulaire, BorderLayout.CENTER);
        organisation.add(nouveauJP, BorderLayout.EAST);


        // Mise en place du fond d'écran
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("img/fond2_2.jpg"));
        background.setSize(xSize, ySize);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
    }

    void creerWidgetFormulaireReseau()
    {
        JPanel titreJeu = new JPanel();
        titreJeu.setOpaque(false);
        titreJeu.add(titre);

        JPanel formulaire = new JPanel(new GridLayout(13, 1, 100, 0));
        formulaire.setOpaque(false);
        formulaire.add(joueur1);
        formulaire.add(listeJoueursNoirs);
        formulaire.add(skinNoir);
        formulaire.add(skinBlancNormal);
        formulaire.add(skinBlancProfs);
        formulaire.add(skinBlancEleves);
        formulaire.add(typePartie);
        formulaire.add(partieNormale);
        formulaire.add(partieTempsCoupsLimites);
        formulaire.add(partieTempsLimite);

        JPanel nouveauJ = new JPanel(new GridLayout(6, 1, 0, 30));
        nouveauJ.setOpaque(false);
        nouveauJ.add(nouveauJoueur);
        nouveauJ.add(lancerPartieReseau);
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(retourMenu);

        JPanel nouveauJP = new JPanel();
        nouveauJP.add(Box.createHorizontalStrut(100));
        nouveauJP.setOpaque(false);
        nouveauJP.add(nouveauJ);

        JPanel organisation = new JPanel(new BorderLayout());
        organisation.setOpaque(false);
        organisation.add(titreJeu, BorderLayout.NORTH);
        organisation.add(formulaire, BorderLayout.CENTER);
        organisation.add(nouveauJP, BorderLayout.EAST);


        // Mise en place du fond d'écran
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("img/fond2_2.jpg"));
        background.setSize(xSize, ySize);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
    }

    /**
     * creerWidgetAccueil
     *
     */
    void creerWidgetAccueil()
    {
        JPanel centre = new JPanel(new GridLayout(11, 1, 0, 10));
        centre.setOpaque(false);
        centre.add(Box.createVerticalGlue());
        centre.add(nouvellePartie);
        centre.add(chargerPartie);
        centre.add(creerPartieReseau);
        centre.add(rejoindrePartie);
        centre.add(partieRandom);
        centre.add(statsJoueur);
        centre.add(historiquePartie);
        centre.add(credit);
        centre.add(quitterJeu);

        JPanel organisation = new JPanel(new BorderLayout());
        organisation.setOpaque(false);
        organisation.add(titre, BorderLayout.NORTH);

        organisation.add(centre, BorderLayout.SOUTH);

        // Mise en place du fond d'écran
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("img/fond2.jpg"));
        background.setSize(xSize, ySize);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
    }

    /**
     *
     */
    void display() {
        setVisible(true);
    }


    /**
     *
     * @param listener
     */
    void setButtonControl(ActionListener listener)
    {
        nouveauJoueur.addActionListener(listener);
        partieRandom.addActionListener(listener);
        rejoindrePartie.addActionListener(listener);
        nouvellePartie.addActionListener(listener);
        retourMenu.addActionListener(listener);
        lancerPartie.addActionListener(listener);
        credit.addActionListener(listener);
        quitterJeu.addActionListener(listener);
        chargerPartie.addActionListener(listener);
        statsJoueur.addActionListener(listener);
        creerPartieReseau.addActionListener(listener);
        lancerPartieReseau.addActionListener(listener);
        rejoindrePartieReseau.addActionListener(listener);
        historiquePartie.addActionListener(listener);
    }

    void creerWidgetRejoindrePartieReseau()
    {
        JPanel titreJeu = new JPanel();
        titreJeu.setOpaque(false);
        titreJeu.add(titre);

        JPanel formulaire = new JPanel(new GridLayout(13, 1, 100, 0));
        formulaire.setOpaque(false);
        formulaire.add(Box.createVerticalGlue());
        formulaire.add(Box.createVerticalGlue());
        formulaire.add(joueur1);
        formulaire.add(listeJoueursNoirs);
        formulaire.add(Box.createVerticalGlue());
        formulaire.add(skinNoir);
        formulaire.add(skinBlancNormal);
        formulaire.add(skinBlancProfs);
        formulaire.add(skinBlancEleves);

        JPanel nouveauJ = new JPanel(new GridLayout(6, 1, 0, 30));
        nouveauJ.setOpaque(false);
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(nouveauJoueur);
        nouveauJ.add(rejoindrePartieReseau);
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(retourMenu);

        JPanel nouveauJP = new JPanel();
        nouveauJP.add(Box.createHorizontalStrut(100));
        nouveauJP.setOpaque(false);
        nouveauJP.add(nouveauJ);

        JPanel organisation = new JPanel(new BorderLayout());
        organisation.setOpaque(false);
        organisation.add(titreJeu, BorderLayout.NORTH);
        organisation.add(formulaire, BorderLayout.CENTER);
        organisation.add(nouveauJP, BorderLayout.EAST);


        // Mise en place du fond d'écran
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("img/fond2_2.jpg"));
        background.setSize(xSize, ySize);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
    }

    /**
     *
     * @param message
     * @return
     */
    String messagePop(String message)
    {
        return JOptionPane.showInputDialog(this, message, "ChessMaster", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     *
     */
    void afficherFormulaire()
    {
        creerWidgetFormulaire();
        setVisible(true);
    }

    /**
     *
     */
    void afficherMenu()
    {
        creerWidgetAccueil();
        setVisible(true);
    }


    /**
     * initAttribut
     * Instancie les attributs
     */
    void initMenuPartie()
    {
        JMenuBar barMenu = new JMenuBar();

        JMenu optionPartie = new JMenu("Fichier");
        JMenu parametres = new JMenu("Options");

        retourMenuPrincipal = new JMenuItem("Menu Principal");
        quitter = new JMenuItem("Quitter");

        undo = new JMenuItem("Undo");
        historique = new JMenuItem("Historique");

        optionPartie.add(retourMenuPrincipal);
        optionPartie.addSeparator();
        optionPartie.add(quitter);

        parametres.add(undo);
        parametres.add(historique);

        barMenu.add(optionPartie);
        barMenu.add(parametres);

        setJMenuBar(barMenu);
    }

    /**
     * creerWidget
     * dessine la vue
     */
    void creerWidgetPartie() {
        setContentPane(vueEchiquier);
    }

    /**
     * setControlButtonMenu
     * ecoute les evenements sur les cases du plateau
     *
     * @param e (ecouteur de type MouseListener)
     */
    void setControlButtonMenu(MouseListener e)
    {
        if (vueEchiquier != null)
            vueEchiquier.addMouseListener(e);
    }

    /**
     * setControlMotion
     */
    void setControlMotion(MouseMotionListener e)
    {
        if (vueEchiquier != null)
            vueEchiquier.addMouseMotionListener(e);
    }

    /**
     * setControlMenu
     * ecoute les evenements du menu
     *
     * @param e (ecouteur de type ActionListener)
     */
    void setControlMenu(ActionListener e)
    {
        retourMenuPrincipal.addActionListener(e);
        undo.addActionListener(e);
        historique.addActionListener(e);
        quitter.addActionListener(e);
    }

    /**
     *
     * @param pion
     */
    void choixPiece(Pion pion)
    {
        ImageIcon[] piecesPossibles = new ImageIcon[4];
        if (pion.blanc)
        {// todo revoir promotion
            piecesPossibles[0] = new ImageIcon("Cavalier");
            piecesPossibles[1] = new ImageIcon("Tour");
            piecesPossibles[2] = new ImageIcon("Fou");
            piecesPossibles[3] = new ImageIcon("Reine");
        }
        else
        {
            piecesPossibles[0] = new ImageIcon("Cavalier");
            piecesPossibles[1] = new ImageIcon("Tour");
            piecesPossibles[2] = new ImageIcon("Fou");
            piecesPossibles[3] = new ImageIcon("Reine");
        }
        int pieceSelected;
        do
        {
            pieceSelected = JOptionPane.showOptionDialog(this, "Choisissez une pièce pour remplacer votre pion :",
                    "Promotion d'un pion", JOptionPane.INFORMATION_MESSAGE, 2, null, piecesPossibles, null);
        }
        while (pieceSelected > 3 || pieceSelected < 0);
        if (pieceSelected == 0)
            pion.promotion(1);
        else if (pieceSelected == 1)
            pion.promotion(2);
        else if (pieceSelected == 2)
            pion.promotion(3);
        else if (pieceSelected == 3)
            pion.promotion(4);
    }

    /**
     *
     * @param message
     * @return
     */
    boolean boolJOptionPane(String message)
    {
        int answer = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);
        return (answer == JOptionPane.YES_OPTION);
    }

    /**
     *
     * @param message
     */
    void jOptionMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message, "A propos", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param historiqueRecup
     * @return
     */
    int choixHistoriqueAConsulter(ArrayList<ArrayList<ArrayList<String>>> historiqueRecup) {
        int nombreDePartie = historiqueRecup.get(0).size();
        // 5 partie affiché en largeur : on regarde combien il faudra de ligne
        int nombreLigne = nombreDePartie / 5;

        JPanel pHistorique = new JPanel();
        pHistorique.setLayout(new BoxLayout(pHistorique, BoxLayout.Y_AXIS));

        JScrollPane listHistoriquePane = new JScrollPane(pHistorique);

        JPanel pGlobal = new JPanel();
        pGlobal.setLayout(new BoxLayout(pGlobal, BoxLayout.Y_AXIS));

        JPanel pTitreColonne = new JPanel(new GridLayout(0, 4));
        pTitreColonne.add(new JLabel("n°"));
        pTitreColonne.add(new JLabel("Joueur blanc"));
        pTitreColonne.add(new JLabel("Date"));
        pTitreColonne.add(new JLabel("Joueur noir"));

        for (int i = 0; i < nombreDePartie; i++)
        {
            //joueur1
            JPanel joueurBlanc = new JPanel();
            joueurBlanc.add(new Label(historiqueRecup.get(0).get(i).get(0)));
            //joueur2
            JPanel joueurNoir = new JPanel();
            joueurNoir.add(new Label(historiqueRecup.get(1).get(i).get(0)));
            //date
            JPanel date = new JPanel();
            date.add(new Label(historiqueRecup.get(0).get(i).get(1)));

            //créer un jPanel puis l'ajoute dans pHistorique
            JPanel pPartie = new JPanel(new GridLayout(0, 4));
            pPartie.add(new JLabel(String.valueOf(i)));
            pPartie.add(joueurBlanc);
            pPartie.add(date);
            pPartie.add(joueurNoir);

            pHistorique.add(pPartie);
        }

        pGlobal.add(pTitreColonne);
        pGlobal.add(listHistoriquePane);

        Object[] choixHisto = new Object[nombreDePartie];
        for (int i = 0; i < nombreDePartie; i++)
            choixHisto[i] = i;

        return JOptionPane.showOptionDialog(this, pGlobal,
                "Choix de l'historique", JOptionPane.INFORMATION_MESSAGE, 2, null, choixHisto, null);
    }

    /**
     * Affiche toutes les parties sauvegardées pour que l'utilisateur puisse en choisir une pour la continuer
     */
    void historiquePartie()
    {
        int i,j;
        BDDManager bdd = new BDDManager();
        bdd.start();

        // On récupère les id des joueurs ayants sauvegardé une partie
        ArrayList<ArrayList<String>> idJoueursSauvegarde = bdd.ask("SELECT SAUVEGARDE.joueurBlancSave, " +
                "" +
                "joueurNoirSave FROM SAUVEGARDE;");

        if(idJoueursSauvegarde.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Il n'y a pas de partie enregistrée.", "Charger une partie interrompue",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[][] pseudosJoueurs = new String[idJoueursSauvegarde.size()][2];

        String[] joueursBlancs = new String[idJoueursSauvegarde.size()];
        String[] joueursNoirs = new String[idJoueursSauvegarde.size()];


        // On récupère les pseudos corespondants à chaque id récupérés précédamment
        for (i = 0; i < idJoueursSauvegarde.size(); i++)
        {
            for(j=0; j<idJoueursSauvegarde.get(i).size(); j++)
            {
                pseudosJoueurs[i][j] = bdd.ask("SELECT JOUEUR.pseudoJoueur FROM JOUEUR WHERE JOUEUR.idJoueur = "
                        + idJoueursSauvegarde.get(i).get(j) + ";").get(0).get(0) + "";

            }
            joueursBlancs[i] = pseudosJoueurs[i][0];
            joueursNoirs[i] = pseudosJoueurs[i][1];
        }

        // On crée une liste qui va être affichée dans une fenetre popup pour que l'utilisateur choisisse
        // quelle sauvegarde il veut reprendre
        String[] possibilitesParties = new String[idJoueursSauvegarde.size()];
        for(i=0; i<possibilitesParties.length; i++)
            possibilitesParties[i] = joueursBlancs[i] + " VS " + joueursNoirs[i];

        // On crée la boite de dialogue
        String partieSelect = (String) JOptionPane.showInputDialog(null, "Quelle partie voulez-vous charger ?",
                "Charger une partie interrompue", JOptionPane.QUESTION_MESSAGE, null, possibilitesParties,
                possibilitesParties[0]);
        accueil.setPartieSelectionneePourChargement(partieSelect);

        bdd.stop();
    }

    void choixHistoriqueAConsulter()
    {
        int i,j;
        BDDManager bdd = new BDDManager();
        bdd.start();

        // On récupère les id des joueurs ayants sauvegardé une partie
        ArrayList<ArrayList<String>> idJoueursHistorique = bdd.ask("SELECT joueurBlancPartie, joueurNoirPartie" +
                " FROM HISTORIQUE;");

        if(idJoueursHistorique.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Il n'y a pas d'historique disponible.", "Voir l'historique d'une partie"
                    , JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        ArrayList<ArrayList<String>> datesParties = bdd.ask("SELECT dateHistorique FROM HISTORIQUE;");

        String[][] pseudosJoueurs = new String[idJoueursHistorique.size()][2];

        String[] joueursBlancs = new String[idJoueursHistorique.size()];
        String[] joueursNoirs = new String[idJoueursHistorique.size()];
        String[] date = new String[datesParties.size()];

        // On récupère les pseudos corespondants à chaque id récupérés précédamment
        for (i = 0; i < idJoueursHistorique.size(); i++)
        {
            for(j=0; j<idJoueursHistorique.get(i).size(); j++)
            {
                pseudosJoueurs[i][j] = bdd.ask("SELECT JOUEUR.pseudoJoueur FROM JOUEUR WHERE JOUEUR.idJoueur = "
                        + idJoueursHistorique.get(i).get(j) + ";").get(0).get(0) + "";
            }
            joueursBlancs[i] = pseudosJoueurs[i][0];
            joueursNoirs[i] = pseudosJoueurs[i][1];
        }
        for (i = 0; i < datesParties.size(); i++)
        {
            for(j=0; j<datesParties.get(i).size(); j++)
            {
                date[i] = datesParties.get(i).get(j);
            }
        }

        // On crée une liste qui va être affichée dans une fenetre popup pour que l'utilisateur choisisse
        // quelle sauvegarde il veut reprendre
        String[] possibilitesParties = new String[idJoueursHistorique.size()];
        for(i=0; i<possibilitesParties.length; i++)
            possibilitesParties[i] = joueursBlancs[i] + " VS " + joueursNoirs[i] + " le " + date[i];

        // On crée la boite de dialogue
        accueil.setPartieAVisualiser((String) JOptionPane.showInputDialog(null, "De quelle partie voulez-vous charger l'historique ?",
                "Voir l'historique d'une partie", JOptionPane.QUESTION_MESSAGE, null, possibilitesParties,
                possibilitesParties[0]));

        bdd.stop();
    }

    ArrayList<String> recupererHistoCoupsPartie(String joueursEtDate)
    {
        BDDManager bdd = new BDDManager();
        bdd.start();

        // joueurEtDate au format : "pseudo1 VS pseudo2 le date"
        String joueurBlanc = joueursEtDate.split(" ")[0];
        String joueurNoir = joueursEtDate.split(" ")[2];
        String date = joueursEtDate.split(" ")[4] + " " + joueursEtDate.split(" ")[5];

        String idJB = bdd.ask("SELECT idJoueur FROM JOUEUR WHERE pseudoJoueur LIKE '" + joueurBlanc + "';").get(0).get(0);
        String idJN = bdd.ask("SELECT idJoueur FROM JOUEUR WHERE pseudoJoueur LIKE '" + joueurNoir + "';").get(0).get(0);
        String requete = "SELECT coupsJouee FROM HISTORIQUE "
                + "WHERE joueurBlancPartie = " + idJB
                + " AND joueurNoirPartie = " + idJN
                + " AND dateHistorique = '" + date
                + "';";
        ArrayList<ArrayList<String>> histo = bdd.ask(requete);
        bdd.stop();
        return histo.get(0);
    }

    void statistiquesJoueur()
    {
        int i;
        BDDManager bdd = new BDDManager();
        bdd.start();

        ArrayList<ArrayList<String>> listeJoueur = bdd.ask("SELECT * FROM JOUEUR;");

        String[] pseudoJoueurs = new String[listeJoueur.size()];
        for(i=0;i<listeJoueur.size(); i++)
            pseudoJoueurs[i] = listeJoueur.get(i).get(1);

        accueil.setPseudoChoisi((String) JOptionPane.showInputDialog(null, "Afficher les statistique du joueur :",
                "Statistiques", JOptionPane.QUESTION_MESSAGE, null, pseudoJoueurs,
                pseudoJoueurs[0]));

        bdd.stop();
    }

    void fenetreStatsJoueur(String pseudo)
    {
        BDDManager bdd = new BDDManager();
        bdd.start();

        ArrayList<String> caracteristique = bdd.ask("SELECT * FROM JOUEUR WHERE pseudoJoueur = '" + pseudo + "';").get(0);

        int partiesJouees = Integer.parseInt(caracteristique.get(3)) +
                Integer.parseInt(caracteristique.get(4)) +
                Integer.parseInt(caracteristique.get(5));

                String stats = "\n\nPseudo : " + caracteristique.get(1) + "\n" +
                "Nombre de parties jouées : " + partiesJouees + "\n" +
                "Nombre de parties gagnées : " + caracteristique.get(3) + "\n" +
                "Nombre de parties perdues : " + caracteristique.get(4) + "\n" +
                "Nombre de parties abandonnées : " + caracteristique.get(5);

        JOptionPane.showMessageDialog(this, "Statistiques :" + stats, "Statistiques d'un joueur", JOptionPane.INFORMATION_MESSAGE);

        bdd.stop();
    }


    /**
     *
     * @param histoCoups
     */
    void afficherHistoriqueLocal(ArrayList<String> histoCoups)
    {
        //todo : faire un getter pour isNetPartie dans Partie.java
        //todo : copier la classe ControlButtonHistorique.java
        //todo : mettre JButton suivant et precedent et retour en attribut de classe + faire les getters
        //todo : mettre plateauDeBase public
        //todo : mettre 'VueEchiquer vueHisto' en attribut de la classe

        //on recupere la partie en cours pour avoir les infos

        //On créer une nouvelle partie
        Accueil accueilHisto = new Accueil();
        accueilHisto.lancementPartie("joueurBlanc", "joueurNoir", 1, 1, 1, false);
        partieHisto = accueilHisto.getPartie();
        VueEchiquier vueEchiquierHisto = new VueEchiquier(partieHisto.getBoard(), accueilHisto, this);
        vueHisto = new JFrame();

        //on créer le controlButton
        controlButtonHistorique = new ControlButtonHistorique(this, histoCoups, partieHisto.getBoard(), vueHisto);
        precedent = new JButton("Precedent");
        precedent.addActionListener(controlButtonHistorique);
        suivant = new JButton("Suivant");
        suivant.addActionListener(controlButtonHistorique);
        retour = new JButton("retour");
        retour.addActionListener(controlButtonHistorique);

        JPanel panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(1,1));
        panelBoard.add(vueEchiquierHisto);

        JPanel panelButton = new JPanel();
        panelButton.add(precedent);
        panelButton.add(suivant);
        panelButton.add(retour);

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.add(panelBoard);
        panelGeneral.add(panelButton);

        //creation de la vue
        vueHisto.setUndecorated(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        xSize = (int) tk.getScreenSize().getWidth();
        ySize = (int) tk.getScreenSize().getHeight();
        vueHisto.setSize(xSize, ySize);
        vueHisto.setTitle("Historique");
        vueHisto.setResizable(false);
        vueHisto.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        vueHisto.setVisible(true);
        vueHisto.setContentPane(panelGeneral);

        vueHisto.repaint();
    }

    public void vueHistoExit(){
        vueHisto.dispose();
    }

    String lectureHumaineDeHistorique(String stringHistorique)
    {
        String reponse;

        //type piece
        if (stringHistorique.charAt(0) == 'p')
            reponse = "Pion ";
        else if (stringHistorique.charAt(0) == 't')
            reponse = "Tour ";
        else if (stringHistorique.charAt(0) == 'f')
            reponse = "Fou ";
        else if (stringHistorique.charAt(0) == 'c')
            reponse = "Cavalier ";
        else if (stringHistorique.charAt(0) == 'r')
            reponse = "Roi ";
        else
            reponse = "Reine ";
        //position départ
        reponse += positionEnChar(String.valueOf(Math.abs(Integer.parseInt(String.valueOf(stringHistorique.charAt(2)))-8)).charAt(0));
        reponse += Math.abs(Integer.parseInt(String.valueOf(stringHistorique.charAt(3)))-8);
        //position final
        reponse += positionEnChar(String.valueOf(Math.abs(Integer.parseInt(String.valueOf(stringHistorique.charAt(4)))-8)).charAt(0));
        reponse += Math.abs(Integer.parseInt(String.valueOf(stringHistorique.charAt(5)))-8);
        //si la une piece a été bouffé
        if (stringHistorique.length() >= 7)
        {
            reponse += " en tuant ";
            if (stringHistorique.charAt(7) == 'p')
                reponse += "Pion ";
            else if (stringHistorique.charAt(7) == 't')
                reponse += "Tour ";
            else if (stringHistorique.charAt(7) == 'f')
                reponse += "Fou ";
            else if (stringHistorique.charAt(7) == 'c')
                reponse += "Cavalier ";
            else if (stringHistorique.charAt(7) == 'r')
                reponse += "Roi ";
            else
                reponse += "Reine ";
        }
        System.out.println(reponse);
        return reponse;
    }

    private String positionEnChar(char position)
    {
        if (position == '8')
            return  "A";
        else if (position == '7')
            return "B";
        else if (position == '6')
            return "C";
        else if (position == '5')
            return "D";
        else if (position == '4')
            return "E";
        else if (position == '3')
            return "F";
        else if (position == '2')
            return "G";
        else if (position == '1')
            return "H";
        return " ";
    }

    /**
     * afficherHistorique
     * 
     * @param histoCoups ()
     */
    void afficherHistorique(ArrayList<ArrayList<String>> histoCoups)
    {
        JPanel titreColonne = new JPanel(new GridLayout(0, 3));
        titreColonne.add(new JLabel("n° coups"));
        titreColonne.add(new JLabel("Joueur blanc"));
        titreColonne.add(new JLabel("Joueur noir"));

        JPanel tableauCoup = new JPanel(new GridLayout(0, 3));
        for (int i = 0; i < histoCoups.size(); i++) {
            //ajoute le numéro
            tableauCoup.add(new JLabel(String.valueOf(i)));
            if (i % 2 == 0)
            {//si coups blanc
                tableauCoup.add(new JLabel(histoCoups.get(0).get(i)));
                tableauCoup.add(new JLabel());
            }
            else
            {//sinon coup noir
                tableauCoup.add(new JLabel());
                tableauCoup.add(new JLabel(histoCoups.get(0).get(i)));
            }
        }
        JScrollPane pScroll = new JScrollPane();
        pScroll.add(tableauCoup);

        JPanel pGlobal = new JPanel();
        pGlobal.setLayout(new BoxLayout(pGlobal, BoxLayout.Y_AXIS));

        pGlobal.add(titreColonne);
        pGlobal.add(pScroll);

        JOptionPane.showMessageDialog(this, pGlobal, "Historique", JOptionPane.INFORMATION_MESSAGE);
    }

    // getters & setters
    VueEchiquier getVueEchiquier() { return vueEchiquier; }
    void setVueEchiquier(VueEchiquier vueEchiquier) { this.vueEchiquier = vueEchiquier; }
    Accueil getAccueil() { return accueil; }
    void setAccueil(Accueil accueil) { this.accueil = accueil; }
    JMenuItem getHistorique() { return historique; }
    JMenuItem getUndo() { return undo; }
    JMenuItem getQuitter() { return quitter; }
    ChessButton getLancerPartie() { return lancerPartie; }
    ChessButton getCredit() { return credit; }
    ButtonGroup getGrTypePartie() { return grTypePartie; }
    ButtonGroup getGrReseau() { return grReseau; }
    ButtonGroup getGrSkinBlanc() { return grSkinBlanc; }
    ButtonGroup getGrSkinNoir() { return grSkinNoir; }
    JComboBox<String> getListeJoueursBlancs() { return listeJoueursBlancs; }
    JComboBox<String> getListeJoueursNoirs() { return listeJoueursNoirs; }
    ChessButton getNouveauJoueur() { return nouveauJoueur; }
    ChessButton getRejoindrePartie() { return rejoindrePartie; }
    ChessButton getNouvellePartie() {return nouvellePartie; }
    ChessButton getRetourMenu() { return retourMenu; }
    ChessButton getQuitterJeu() { return quitterJeu; }
    ChessButton getPartieRandom() { return partieRandom; }
    ChessButton getChargerPartie() {
        return chargerPartie;
    }
    ChessButton getStatsJoueur() {
        return statsJoueur;
    }
    JMenuItem getRetourMenuPrincipal() {
        return retourMenuPrincipal;
    }
    ChessButton getCreerPartieReseau() {
        return creerPartieReseau;
    }
    ChessButton getLancerPartieReseau() {
        return lancerPartieReseau;
    }
    ChessButton getRejoindrePartieReseau() {
        return rejoindrePartieReseau;
    }
    JButton getSuivant(){
        return suivant;
    }
    JButton getPrecedent(){
        return precedent;
    }
    JButton getRetour(){
        return retour;
    }
    ChessButton getHistoriquePartie(){
        return historiquePartie;
    }
}
