import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 *Created by mlucile on 12/05/16.
 */
class Vue extends JFrame
{
    private VueEchiquier vueEchiquier;

    private JMenuItem quitter;
    private JMenuItem undo;
    private JMenuItem historique;
    private JMenuItem retourMenuPrincipal;

    private Accueil accueil;

    private int xSize;
    private int ySize;

    private JLabel titre;
    private JLabel joueurBlanc;
    private JLabel joueurNoir;
    private JLabel typePartie;
    private JLabel skinBlanc;
    private JLabel skinNoir;
    private JLabel reseau;
    private JLabel background;

    private chessButton partieRandom;
    private chessButton nouvellePartie;
    private chessButton rejoindrePartie;
    private chessButton nouveauJoueur;
    private chessButton credit;
    private chessButton chargerPartie;
    private chessButton retourMenu;
    private chessButton lancerPartie;
    private chessButton quitterJeu;
    private chessButton statsJoueur;

    private JRadioButton partieNormale;
    private JRadioButton partieTempsCoupsLimites;
    private JRadioButton partieTempsLimite;
    private JRadioButton reseauOui;
    private JRadioButton reseauNon;
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

    private chessComboBox listeJoueursBlancs;
    private chessComboBox listeJoueursNoirs;

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
        joueurBlanc = new JLabel(accueil.getJoueurBlancLabel());
        joueurNoir = new JLabel(accueil.getJoueurNoirLabel());
        typePartie = new JLabel(accueil.getTypePartieLabel());
        skinBlanc = new JLabel(accueil.getSkinLabel());
        skinNoir = new JLabel(accueil.getSkinLabel());
        reseau = new JLabel(accueil.getReseauLabel());

        nouvellePartie = new chessButton(accueil.getNouvellePartieTitre());
        rejoindrePartie = new chessButton(accueil.getRejoindrePartieTitre());
        nouveauJoueur = new chessButton(accueil.getNouveauJoueurTitre());
        credit = new chessButton(accueil.getCreditTitre());
        chargerPartie = new chessButton(accueil.getChargerPartieTitre());
        retourMenu = new chessButton(accueil.getRetourMenuTitre());
        lancerPartie = new chessButton(accueil.getLancerPartieTitre());
        quitterJeu = new chessButton(accueil.getQuitterJeuTitre());
        partieRandom = new chessButton(accueil.getPartieRandomTitre());
        statsJoueur = new chessButton(accueil.getStatsJoueurTitre());

        partieNormale = new JRadioButton(accueil.getPartieNormaleTitre(), true);
        partieNormale.setActionCommand("1");
        partieTempsCoupsLimites = new JRadioButton(accueil.getPartieTempsCoupsLimitesTitre());
        partieTempsCoupsLimites.setActionCommand("2");
        partieTempsLimite = new JRadioButton(accueil.getPartieTempsLimiteTitre());
        partieTempsLimite.setActionCommand("3");

        reseauOui = new JRadioButton(accueil.getReseauOuiTitre());
        reseauOui.setActionCommand("true");
        reseauNon = new JRadioButton(accueil.getReseauNonTitre(), true);
        reseauNon.setActionCommand("fasle");

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

        listeJoueursBlancs = new chessComboBox(accueil.majListeJoueur());
        listeJoueursNoirs = new chessComboBox(accueil.majListeJoueur());

        // Création des groupes de RadioButton
        grTypePartie.add(partieNormale);
        grTypePartie.add(partieTempsCoupsLimites);
        grTypePartie.add(partieTempsLimite);

        grReseau.add(reseauOui);
        grReseau.add(reseauNon);

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

        reseau.setForeground(couleurLabel);
        joueurNoir.setForeground(couleurLabel);
        joueurBlanc.setForeground(couleurColonneGauche);
        typePartie.setForeground(couleurColonneGauche);
        skinBlanc.setForeground(couleurColonneGauche);
        skinNoir.setForeground(couleurLabel);

        titre.setForeground(new Color(96, 0, 99));
        partieNormale.setForeground(couleurChoix);
        partieTempsCoupsLimites.setForeground(couleurChoix);
        partieTempsLimite.setForeground(couleurChoix);
        reseauNon.setForeground(couleurChoix);
        reseauOui.setForeground(couleurChoix);
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
        joueurBlanc.setFont(police);
        joueurNoir.setFont(police);
        typePartie.setFont(police);
        skinBlanc.setFont(police);
        reseau.setFont(police);
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
        reseauOui.setFont(policeChoix);
        reseauNon.setFont(policeChoix);

        // Le fond de chaque élément est transparent
        listeJoueursBlancs.setOpaque(false);
        partieNormale.setOpaque(false);
        partieTempsCoupsLimites.setOpaque(false);
        partieTempsLimite.setOpaque(false);
        skinBlancNormal.setOpaque(false);
        skinBlancProfs.setOpaque(false);
        skinBlancEleves.setOpaque(false);
        reseauOui.setOpaque(false);
        reseauNon.setOpaque(false);
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
        listeJoueursBlancs = new chessComboBox(accueil.majListeJoueur());
        listeJoueursNoirs = new chessComboBox(accueil.majListeJoueur());
    }

    /**
     *
     */
    void creerWidgetFormulaire()
    {
        JPanel titreJeu = new JPanel();
        titreJeu.setOpaque(false);
        titreJeu.add(titre);

        JPanel formulaire = new JPanel(new GridLayout(13, 2, 100, 0));
        formulaire.setOpaque(false);
        formulaire.add(joueurBlanc);
        formulaire.add(joueurNoir);
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
        formulaire.add(reseau);
        formulaire.add(partieNormale);
        formulaire.add(reseauNon);
        formulaire.add(partieTempsCoupsLimites);
        formulaire.add(reseauOui);
        formulaire.add(partieTempsLimite);

        JPanel nouveauJ = new JPanel(new GridLayout(6, 1, 0, 30));
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

    /**
     * creerWidgetAccueil
     *
     */
    void creerWidgetAccueil()
    {
        JPanel centre = new JPanel(new GridLayout(11, 1, 0, 10));
        centre.setOpaque(false);
        centre.add(Box.createVerticalGlue());
        centre.add(Box.createVerticalGlue());
        centre.add(Box.createVerticalGlue());
        centre.add(nouvellePartie);
        centre.add(partieRandom);
        centre.add(chargerPartie);
        centre.add(rejoindrePartie);
        centre.add(statsJoueur);
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
    public void afficherFormulaire()
    {
        creerWidgetFormulaire();
        setVisible(true);
    }

    /**
     *
     */
    public void afficherMenu()
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
    public void creerWidgetPartie() {
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
    int choixHistorique(ArrayList<ArrayList<ArrayList<String>>> historiqueRecup) {
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
            //joueurBlanc
            JPanel joueurBlanc = new JPanel();
            joueurBlanc.add(new Label(historiqueRecup.get(0).get(i).get(0)));
            //joueurNoir
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
    public void historiquePartie()
    {
        int i,j;
        BDDManager bdd = new BDDManager();
        bdd.start();

        // On récupère les id des joueurs ayants sauvegardé une partie
        ArrayList<ArrayList<String>> idJoueursSauvegarde = bdd.ask("SELECT SAUVEGARDE.joueurBlancSave, joueurNoirSave FROM SAUVEGARDE;");

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


    public void statistiquesJoueur()
    {
        int i, j;
        BDDManager bdd = new BDDManager();
        bdd.start();

        ArrayList<ArrayList<String>> listeJoueur = bdd.ask("SELECT * FROM JOUEUR;");

        String[] pseudoJoueurs = new String[listeJoueur.size()];
        for(i=0;i<listeJoueur.size(); i++)
            pseudoJoueurs[i] = listeJoueur.get(i).get(1);

        accueil.setPseudoChoisi((String) JOptionPane.showInputDialog(null, "Afficher les statistique du joueur :",
                "XXXXXXXXXXXXXXXXXX", JOptionPane.QUESTION_MESSAGE, null, pseudoJoueurs,
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

        JLabel numero = new JLabel("n°");
        JLabel jb = new JLabel("Joueur blanc");
        JLabel jn = new JLabel("Joueur noir");

        JPanel colonneNum = new JPanel();
        colonneNum.setLayout(new BoxLayout(colonneNum, BoxLayout.Y_AXIS));
        colonneNum.add(numero);
        colonneNum.add(Box.createVerticalStrut(15));

        JPanel colonneJB = new JPanel();
        colonneJB.setLayout(new BoxLayout(colonneJB, BoxLayout.Y_AXIS));
        colonneJB.add(jb);
        colonneJB.add(Box.createVerticalStrut(15));

        JPanel colonneJN = new JPanel();
        colonneJN.setLayout(new BoxLayout(colonneJN, BoxLayout.Y_AXIS));
        colonneJN.add(jn);
        colonneJN.add(Box.createVerticalStrut(15));

        for (int i = 0;i<histoCoups.size();i++)
        {
            //ajoute le numéro
            colonneNum.add(new JLabel(String.valueOf(i+1)));
            colonneNum.add(Box.createVerticalStrut(6));
            if (i%2 == 0)
            {//si coups blanc
                colonneJB.add(new JLabel(lectureHumaineDeHistorique(histoCoups.get(i))));
                colonneJB.add(Box.createVerticalStrut(6));
                colonneJN.add(Box.createVerticalStrut(21));
            }
            else
            {//sinon coup noir
                colonneJB.add(Box.createVerticalStrut(21));
                colonneJN.add(new JLabel(lectureHumaineDeHistorique(histoCoups.get(i))));
                colonneJN.add(Box.createVerticalStrut(6));
            }
        }

        JPanel tableau = new JPanel();
        tableau.add(colonneNum);
        tableau.add(colonneJB);
        tableau.add(colonneJN);

        JScrollPane pScroll = new JScrollPane(tableau,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pScroll.setPreferredSize(new Dimension(500,200));

        JPanel pGlobal = new JPanel();
        pGlobal.setLayout(new BoxLayout(pGlobal, BoxLayout.Y_AXIS));
        pGlobal.add(pScroll);

        JOptionPane.showMessageDialog( this, pGlobal, "Historique des coups jouées", JOptionPane.INFORMATION_MESSAGE );
    }

    public String lectureHumaineDeHistorique(String stringHistorique)
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
     *
     * @param histoCoups
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
    chessButton getLancerPartie() { return lancerPartie; }
    chessButton getCredit() { return credit; }
    ButtonGroup getGrTypePartie() { return grTypePartie; }
    ButtonGroup getGrReseau() { return grReseau; }
    ButtonGroup getGrSkinBlanc() { return grSkinBlanc; }
    ButtonGroup getGrSkinNoir() { return grSkinNoir; }
    JComboBox<String> getListeJoueursBlancs() { return listeJoueursBlancs; }
    JComboBox<String> getListeJoueursNoirs() { return listeJoueursNoirs; }
    chessButton getNouveauJoueur() { return nouveauJoueur; }
    chessButton getRejoindrePartie() { return rejoindrePartie; }
    chessButton getNouvellePartie() {return nouvellePartie; }
    chessButton getRetourMenu() { return retourMenu; }
    chessButton getQuitterJeu() { return quitterJeu; }
    chessButton getPartieRandom() { return partieRandom; }

    public chessButton getChargerPartie() {
        return chargerPartie;
    }

    public chessButton getStatsJoueur() {
        return statsJoueur;
    }

    public JMenuItem getRetourMenuPrincipal() {
        return retourMenuPrincipal;
    }

}
