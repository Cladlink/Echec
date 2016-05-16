
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

/**
 *Created by mlucile on 12/05/16.
 */
public class Vue extends JFrame
{
    private VueEchiquier vueEchiquier;

    private JMenuItem nvlPart;
    private JMenuItem rejPart;
    private JMenuItem ldPart;
    private JMenuItem svPart;
    private JMenuItem quitter;
    private JMenuItem undo;
    private JMenuItem historique;
    private JMenuItem aide;
    private JMenuItem aPropos;

    private Model model;

    private GraphicsEnvironment fontLabel;
    private GraphicsEnvironment fontTitre;
    private GraphicsEnvironment fontChoix;

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
    private JLabel espace1;
    private JLabel espace2;
    private JLabel espace3;
    private JLabel espace4;
    private JLabel espace5;
    private JLabel espace6;
    private JLabel espace7;
    private JLabel espace8;
    private JLabel espace9;
    private JLabel espace10;
    private JLabel espace11;
    private JLabel espace12;
    private JLabel espace13;

    private JButton nouvellePartie;
    private JButton rejoindrePartie;
    private JButton nouveauJoueur;
    private JButton credit;
    private JButton chargerPartie;
    private JButton retourMenu;
    private JButton lancerPartie;

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

    private JComboBox listeJoueursBlancs;
    private JComboBox listeJoueursNoirs;

    public Vue(Model model)
    {
        this.model = model;

        initAttribut();
        creerWidgetAccueil();

        setUndecorated(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        xSize = (int)tk.getScreenSize().getWidth();
        ySize = (int)tk.getScreenSize().getHeight();
        setSize(xSize, ySize);
        setTitle("Echec");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void initAttribut()
    {
        // Initialisation des variables
        titre = new JLabel(model.getTitreLabel());
        joueurBlanc = new JLabel(model.getJoueurBlancLabel());
        joueurNoir = new JLabel(model.getJoueurNoirLabel());
        typePartie = new JLabel(model.getTypePartieLabel());
        skinBlanc = new JLabel(model.getSkinLabel());
        skinNoir = new JLabel(model.getSkinLabel());
        reseau = new JLabel(model.getReseauLabel());
        espace1 = new JLabel();
        espace2 = new JLabel();
        espace3 = new JLabel();
        espace4 = new JLabel();
        espace5 = new JLabel();
        espace6 = new JLabel();
        espace7 = new JLabel();
        espace8 = new JLabel();
        espace9 = new JLabel();
        espace10 = new JLabel();
        espace11 = new JLabel();
        espace12 = new JLabel();
        espace13 = new JLabel();

        nouvellePartie = new JButton(model.getNouvellePartieTitre());
        rejoindrePartie = new JButton(model.getRejoindrePartieTitre());
        nouveauJoueur = new JButton(model.getNouveauJoueurTitre());
        credit = new JButton(model.getCreditTitre());
        chargerPartie = new JButton(model.getChargerPartieTitre());
        retourMenu = new JButton(model.getRetourMenuTitre());
        lancerPartie = new JButton(model.getLancerPartieTitre());

        partieNormale = new JRadioButton(model.getPartieNormaleTitre(), true);
        partieNormale.setActionCommand("1");
        partieTempsCoupsLimites = new JRadioButton(model.getPartieTempsCoupsLimitesTitre());
        partieTempsCoupsLimites.setActionCommand("2");
        partieTempsLimite = new JRadioButton(model.getPartieTempsLimiteTitre());
        partieTempsLimite.setActionCommand("3");
        reseauOui = new JRadioButton(model.getReseauOuiTitre());
        reseauOui.setActionCommand("true");
        reseauNon = new JRadioButton(model.getReseauNonTitre(), true);
        reseauNon.setActionCommand("fasle");
        skinBlancNormal = new JRadioButton(model.getSkinBlancNormalTitre(), true);
        skinBlancNormal.setActionCommand("1");
        skinBlancProfs = new JRadioButton(model.getSkinBlancProfsTitre());
        skinBlancProfs.setActionCommand("2");
        skinBlancEleves = new JRadioButton(model.getSkinBlancElevesTitre());
        skinBlancEleves.setActionCommand("3");
        skinNoirNormal = new JRadioButton(model.getSkinNoirNormalTitre(), true);
        skinNoirNormal.setActionCommand("1");
        skinNoirProfs = new JRadioButton(model.getSkinNoirProfsTitre());
        skinNoirProfs.setActionCommand("2");
        skinNoirEleves = new JRadioButton(model.getSkinNoirElevesTitre());
        skinNoirEleves.setActionCommand("3");

        grTypePartie = new ButtonGroup();
        grReseau = new ButtonGroup();
        grSkinBlanc = new ButtonGroup();
        grSkinNoir = new ButtonGroup();

        listeJoueursBlancs = new JComboBox(model.majListeJoueur());
        listeJoueursNoirs = new JComboBox(model.majListeJoueur());

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
        fontLabel = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontTitre = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontChoix = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            fontLabel.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Cardinal.ttf")));
            fontTitre.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Ace Records.ttf")));
            fontChoix.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/CalligraphyFLF.ttf")));
        }
        catch (FontFormatException fe){
            System.out.println("Pas le bon format de police");
        }
        catch (IOException io){
            System.out.println("Je sais pas ce que c'est");
        }

        // Change la couleur de la police
        Color couleurLabel = new Color(242,145,0);
        Color couleurChoix = new Color(255,255,255);
        Color couleurColonneGauche = new Color(242,100,0);
        reseau.setForeground(couleurLabel);
        joueurNoir.setForeground(couleurLabel);
        joueurBlanc.setForeground(couleurColonneGauche);
        typePartie.setForeground(couleurColonneGauche);
        skinBlanc.setForeground(couleurColonneGauche);
        skinNoir.setForeground(couleurLabel);

        titre.setForeground(new Color(96,0,99));
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
        Font police = new Font("Cardinal", Font.BOLD, 25);
        Font policeChoix = new Font("CalligraphyFLF", Font.TRUETYPE_FONT, 28);
        titre.setFont(policeTitre);
        joueurBlanc.setFont(police);
        joueurNoir.setFont(police);
        typePartie.setFont(police);
        skinBlanc.setFont(police);
        reseau.setFont(police);
        skinNoir.setFont(police);
        nouvellePartie.setFont(police);
        rejoindrePartie.setFont(police);
        nouveauJoueur.setFont(police);
        credit.setFont(police);
        chargerPartie.setFont(police);
        retourMenu.setFont(police);
        lancerPartie.setFont(police);

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

    public void majListeJoueur()
    {
        listeJoueursBlancs = new JComboBox(model.majListeJoueur());
        listeJoueursNoirs = new JComboBox(model.majListeJoueur());
    }
    public void creerWidgetFormulaire()
    {
        JPanel titreJeu = new JPanel();
        titreJeu.setOpaque(false);
        titreJeu.add(titre);

        JPanel formulaire = new JPanel(new GridLayout(13,2,100,0));
        formulaire.setOpaque(false);
        formulaire.add(joueurBlanc);
        formulaire.add(joueurNoir);
        formulaire.add(listeJoueursBlancs);
        formulaire.add(listeJoueursNoirs);
        formulaire.add(espace1);
        formulaire.add(espace2);
        formulaire.add(skinBlanc);
        formulaire.add(skinNoir);
        formulaire.add(skinBlancNormal);
        formulaire.add(skinNoirNormal);
        formulaire.add(skinBlancProfs);
        formulaire.add(skinNoirProfs);
        formulaire.add(skinBlancEleves);
        formulaire.add(skinNoirEleves);
        formulaire.add(espace3);
        formulaire.add(espace4);
        formulaire.add(typePartie);
        formulaire.add(reseau);
        formulaire.add(partieNormale);
        formulaire.add(reseauNon);
        formulaire.add(partieTempsCoupsLimites);
        formulaire.add(reseauOui);
        formulaire.add(partieTempsLimite);

        JPanel nouveauJ = new JPanel(new GridLayout(6,1,0,10));
        nouveauJ.setOpaque(false);
        nouveauJ.add(nouveauJoueur);
        nouveauJ.add(Box.createVerticalGlue());
        nouveauJ.add(lancerPartie);
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

    public void creerWidgetAccueil()
    {
        JPanel centre = new JPanel(new GridLayout(8,1,0,10));
        centre.setOpaque(false);
        centre.add(espace5);
        centre.add(nouvellePartie);
        centre.add(chargerPartie);
        centre.add(rejoindrePartie);
        centre.add(credit);

        JPanel organisation = new JPanel(new BorderLayout());
        organisation.setOpaque(false);
        organisation.add(titre, BorderLayout.NORTH);
        organisation.add(centre, BorderLayout.CENTER);

        // Mise en place du fond d'écran
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("img/fond2.jpg"));
        background.setSize(xSize, ySize);
        add(background);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
    }

    public void display()
    {
        setVisible(true);
    }

    public void setButtonControl(ActionListener listener)
    {
        nouveauJoueur.addActionListener(listener);
        rejoindrePartie.addActionListener(listener);
        nouvellePartie.addActionListener(listener);
        retourMenu.addActionListener(listener);
        lancerPartie.addActionListener(listener);
        credit.addActionListener(listener);
    }

    public String messagePop(String message)
    {
        return JOptionPane.showInputDialog(this, message, "Enregistrement", JOptionPane.QUESTION_MESSAGE);
    }

    public void afficherFormulaire()
    {
        creerWidgetFormulaire();
        setVisible(true);
    }

    public void afficherMenu()
    {
        creerWidgetAccueil();
        setVisible(true);
    }

    //Getters
    public JButton getNouveauJoueur() {
        return nouveauJoueur;
    }

    public JButton getRejoindrePartie() {
        return rejoindrePartie;
    }

    public JButton getNouvellePartie() {
        return nouvellePartie;
    }

    public JButton getRetourMenu() {
        return retourMenu;
    }


    /**
     * initAttribut
     * Instancie les attributs
     */
    void initAttributPartie()
    {
        JMenuBar barMenu = new JMenuBar();
        JMenu optionPartie = new JMenu("Fichier");
        JMenu parametres = new JMenu("Options");
        JMenu autres = new JMenu("?");

        nvlPart = new JMenuItem("Nouvelle Partie");
        rejPart = new JMenuItem("Rejoindre Partie");
        ldPart = new JMenuItem("Charger Partie");
        svPart = new JMenuItem("Sauver Partie");
        quitter = new JMenuItem("Quitter");

        undo = new JMenuItem("Undo");
        historique = new JMenuItem("Historique");

        aide = new JMenuItem("Aide");
        aPropos = new JMenuItem("A propos");

        optionPartie.add(nvlPart);
        optionPartie.add(rejPart);
        optionPartie.addSeparator();
        optionPartie.add(ldPart);
        optionPartie.add(svPart);
        optionPartie.addSeparator();
        optionPartie.add(quitter);

        parametres.add(undo);
        parametres.add(historique);

        autres.add(aide);
        autres.add(aPropos);

        barMenu.add(optionPartie);
        barMenu.add(parametres);
        barMenu.add(autres);
        setJMenuBar(barMenu);
    }

    /**
     * creerWidget
     * dessine la vue
     */
    public void creerWidgetPartie()
    {
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
        nvlPart.addActionListener(e);
        rejPart.addActionListener(e);
        svPart.addActionListener(e);
        ldPart.addActionListener(e);
        quitter.addActionListener(e);
        undo.addActionListener(e);
        historique.addActionListener(e);
        aPropos.addActionListener(e);
        aide.addActionListener(e);
    }

    void choixPiece(Pion pion)
    {
        ImageIcon[] piecesPossibles = new ImageIcon[4];
        if(pion.blanc)
        {
            piecesPossibles[0] = new ImageIcon("img/BlancEleve/CavalierBlanc.png");
            piecesPossibles[1] = new ImageIcon("img/BlancEleve/TourBlanc.png");
            piecesPossibles[2] = new ImageIcon("img/BlancEleve/FouBlanc.png");
            piecesPossibles[3] = new ImageIcon("img/BlancEleve/ReineBlanc.png");
        }
        else
        {
            piecesPossibles[0] = new ImageIcon("img/NoirEleve/CavalierNoir.png");
            piecesPossibles[1] = new ImageIcon("img/NoirEleve/TourNoir.png");
            piecesPossibles[2] = new ImageIcon("img/NoirEleve/FouNoir.png");
            piecesPossibles[3] = new ImageIcon("img/NoirEleve/ReineNoir.png");
        }
        int pieceSelected;
        do
        {
            pieceSelected = JOptionPane.showOptionDialog(this, "Choisissez une pièce pour remplacer votre pion :",
                    "Promotion d'un pion", JOptionPane.INFORMATION_MESSAGE, 2, null, piecesPossibles, null);
        }
        while (pieceSelected >3 || pieceSelected < 0);

        if(pieceSelected == 0)
            pion.promotion(1);
        else if(pieceSelected == 1)
            pion.promotion(2);
        else if(pieceSelected == 2)
            pion.promotion(3);
        else if(pieceSelected == 3)
            pion.promotion(4);
    }

    String askJOption(String message)
    {
        String pseudo = JOptionPane.showInputDialog(null, "Pseuso " + message + ":");
        if (Objects.equals(pseudo, ""))
            return "anonymous";
        return pseudo;
    }

    int choixMode()
    {
        Object[] options = {1,2 ,3 };
        return JOptionPane.showOptionDialog(this,
                "Choisissez le mode de votre partie :\n" +
                        "1) partie normale\n" +
                        "2) coups chronométrée\n" +
                        "3) partie chronométrée\n",
                "Mode Partie", JOptionPane.INFORMATION_MESSAGE, 2, null, options, null);
    }
    boolean boolJOptionPane(String message)
    {
        int answer = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);
        return (answer == JOptionPane.YES_OPTION);
    }
    void jOptionMessage (String message)
    {
        JOptionPane.showMessageDialog(this, message, "A propos", JOptionPane.INFORMATION_MESSAGE);
    }
    /*
        * nettoye la fenetre puis affiche l'historique
    */
    int choixHistorique(ArrayList<ArrayList<ArrayList<String>>> historiqueRecup)
    {
        int nombreDePartie = historiqueRecup.get(0).size();
        // 5 partie affiché en largeur : on regarde combien il faudra de ligne
        int nombreLigne = nombreDePartie/5;

        JPanel pHistorique = new JPanel();
        pHistorique.setLayout(new BoxLayout(pHistorique, BoxLayout.Y_AXIS));

        JScrollPane listHistoriquePane = new JScrollPane(pHistorique);

        JPanel pGlobal = new JPanel();
        pGlobal.setLayout(new BoxLayout(pGlobal, BoxLayout.Y_AXIS));

        JPanel pTitreColonne = new JPanel(new GridLayout(0,4));
        pTitreColonne.add(new JLabel("n°"));
        pTitreColonne.add(new JLabel("Joueur blanc"));
        pTitreColonne.add(new JLabel("Date"));
        pTitreColonne.add(new JLabel("Joueur noir"));

        for (int i = 0; i<nombreDePartie; i++) {
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
            JPanel pPartie = new JPanel(new GridLayout(0,4));
            pPartie.add(new JLabel(String.valueOf(i)));
            pPartie.add(joueurBlanc);
            pPartie.add(date);
            pPartie.add(joueurNoir);

            pHistorique.add(pPartie);
        }

        pGlobal.add(pTitreColonne);
        pGlobal.add(listHistoriquePane);

        Object[] choixHisto = new Object[nombreDePartie];
        for (int i = 0; i<nombreDePartie; i++){
            choixHisto[i] = i;
        }

        return JOptionPane.showOptionDialog(this, pGlobal,
                "Choix de l'historique", JOptionPane.INFORMATION_MESSAGE, 2, null, choixHisto, null);
    }

    void afficherHistorique(ArrayList<ArrayList<String>> histoCoups)
    {
        JOptionPane d = new JOptionPane();
        JPanel titreColonne = new JPanel(new GridLayout(0,3));
        titreColonne.add(new JLabel("n° coups"));
        titreColonne.add(new JLabel("Joueur blanc"));
        titreColonne.add(new JLabel("Joueur noir"));

        JPanel tableauCoup = new JPanel(new GridLayout(0,3));
        for (int i = 0;i<histoCoups.size();i++)
        {
            //ajoute le numéro
            tableauCoup.add(new JLabel(String.valueOf(i)));
            if (i%2 == 0)
            {//si coups blanc
                tableauCoup.add(new JLabel(histoCoups.get(0).get(i)));
                tableauCoup.add(new JLabel());
            }
            else{//sinon coup noir
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


        d.showMessageDialog( this, pGlobal, "Historique", JOptionPane.INFORMATION_MESSAGE );
    }


    // getters & setters
    public VueEchiquier getVueEchiquier() {
        return vueEchiquier;
    }
    public void setVueEchiquier(VueEchiquier vueEchiquier) {
        this.vueEchiquier = vueEchiquier;
    }
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }
    public JMenuItem getaPropos() {
        return aPropos;
    }
    public void setaPropos(JMenuItem aPropos) {
        this.aPropos = aPropos;
    }
    public JMenuItem getHistorique() {
        return historique;
    }
    public void setHistorique(JMenuItem historique) {
        this.historique = historique;
    }
    public JMenuItem getAide() {
        return aide;
    }
    public void setAide(JMenuItem aide) {
        this.aide = aide;
    }
    public JMenuItem getUndo() {
        return undo;
    }
    public void setUndo(JMenuItem undo) {
        this.undo = undo;
    }
    public JMenuItem getQuitter() {
        return quitter;
    }
    public void setQuitter(JMenuItem quitter) {
        this.quitter = quitter;
    }
    public JMenuItem getSvPart() {
        return svPart;
    }
    public void setSvPart(JMenuItem svPart) {
        this.svPart = svPart;
    }
    public JMenuItem getLdPart() {
        return ldPart;
    }
    public void setLdPart(JMenuItem ldPart) {
        this.ldPart = ldPart;
    }
    public JMenuItem getRejPart() {
        return rejPart;
    }
    public void setRejPart(JMenuItem rejPart) {
        this.rejPart = rejPart;
    }
    public JMenuItem getNvlPart() {
        return nvlPart;
    }
    public void setNvlPart(JMenuItem nvlPart) {
        this.nvlPart = nvlPart;
    }

    public JButton getLancerPartie() {
        return lancerPartie;
    }

    public GraphicsEnvironment getFontLabel() {
        return fontLabel;
    }

    public void setFontLabel(GraphicsEnvironment fontLabel) {
        this.fontLabel = fontLabel;
    }

    public GraphicsEnvironment getFontTitre() {
        return fontTitre;
    }

    public void setFontTitre(GraphicsEnvironment fontTitre) {
        this.fontTitre = fontTitre;
    }

    public GraphicsEnvironment getFontChoix() {
        return fontChoix;
    }

    public void setFontChoix(GraphicsEnvironment fontChoix) {
        this.fontChoix = fontChoix;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public JLabel getTitre() {
        return titre;
    }

    public void setTitre(JLabel titre) {
        this.titre = titre;
    }

    public JLabel getJoueurBlanc() {
        return joueurBlanc;
    }

    public void setJoueurBlanc(JLabel joueurBlanc) {
        this.joueurBlanc = joueurBlanc;
    }

    public JLabel getJoueurNoir() {
        return joueurNoir;
    }

    public void setJoueurNoir(JLabel joueurNoir) {
        this.joueurNoir = joueurNoir;
    }

    public JLabel getTypePartie() {
        return typePartie;
    }

    public void setTypePartie(JLabel typePartie) {
        this.typePartie = typePartie;
    }

    public JLabel getSkinBlanc() {
        return skinBlanc;
    }

    public void setSkinBlanc(JLabel skinBlanc) {
        this.skinBlanc = skinBlanc;
    }

    public JLabel getSkinNoir() {
        return skinNoir;
    }

    public void setSkinNoir(JLabel skinNoir) {
        this.skinNoir = skinNoir;
    }

    public JLabel getReseau() {
        return reseau;
    }

    public void setReseau(JLabel reseau) {
        this.reseau = reseau;
    }


    public void setBackground(JLabel background) {
        this.background = background;
    }

    public JLabel getEspace1() {
        return espace1;
    }

    public void setEspace1(JLabel espace1) {
        this.espace1 = espace1;
    }

    public JLabel getEspace2() {
        return espace2;
    }

    public void setEspace2(JLabel espace2) {
        this.espace2 = espace2;
    }

    public JLabel getEspace3() {
        return espace3;
    }

    public void setEspace3(JLabel espace3) {
        this.espace3 = espace3;
    }

    public JLabel getEspace4() {
        return espace4;
    }

    public void setEspace4(JLabel espace4) {
        this.espace4 = espace4;
    }

    public JLabel getEspace5() {
        return espace5;
    }

    public void setEspace5(JLabel espace5) {
        this.espace5 = espace5;
    }

    public JLabel getEspace6() {
        return espace6;
    }

    public void setEspace6(JLabel espace6) {
        this.espace6 = espace6;
    }

    public JLabel getEspace7() {
        return espace7;
    }

    public void setEspace7(JLabel espace7) {
        this.espace7 = espace7;
    }

    public JLabel getEspace8() {
        return espace8;
    }

    public void setEspace8(JLabel espace8) {
        this.espace8 = espace8;
    }

    public JLabel getEspace9() {
        return espace9;
    }

    public void setEspace9(JLabel espace9) {
        this.espace9 = espace9;
    }

    public JLabel getEspace10() {
        return espace10;
    }

    public void setEspace10(JLabel espace10) {
        this.espace10 = espace10;
    }

    public JLabel getEspace11() {
        return espace11;
    }

    public void setEspace11(JLabel espace11) {
        this.espace11 = espace11;
    }

    public JLabel getEspace12() {
        return espace12;
    }

    public void setEspace12(JLabel espace12) {
        this.espace12 = espace12;
    }

    public JLabel getEspace13() {
        return espace13;
    }

    public void setEspace13(JLabel espace13) {
        this.espace13 = espace13;
    }

    public void setNouvellePartie(JButton nouvellePartie) {
        this.nouvellePartie = nouvellePartie;
    }

    public void setRejoindrePartie(JButton rejoindrePartie) {
        this.rejoindrePartie = rejoindrePartie;
    }

    public void setNouveauJoueur(JButton nouveauJoueur) {
        this.nouveauJoueur = nouveauJoueur;
    }

    public JButton getCredit() {
        return credit;
    }

    public void setCredit(JButton credit) {
        this.credit = credit;
    }

    public JButton getChargerPartie() {
        return chargerPartie;
    }

    public void setChargerPartie(JButton chargerPartie) {
        this.chargerPartie = chargerPartie;
    }

    public void setRetourMenu(JButton retourMenu) {
        this.retourMenu = retourMenu;
    }

    public void setLancerPartie(JButton lancerPartie) {
        this.lancerPartie = lancerPartie;
    }

    public JRadioButton getPartieNormale() {
        return partieNormale;
    }

    public void setPartieNormale(JRadioButton partieNormale) {
        this.partieNormale = partieNormale;
    }

    public JRadioButton getPartieTempsCoupsLimites() {
        return partieTempsCoupsLimites;
    }

    public void setPartieTempsCoupsLimites(JRadioButton partieTempsCoupsLimites) {
        this.partieTempsCoupsLimites = partieTempsCoupsLimites;
    }

    public JRadioButton getPartieTempsLimite() {
        return partieTempsLimite;
    }

    public void setPartieTempsLimite(JRadioButton partieTempsLimite) {
        this.partieTempsLimite = partieTempsLimite;
    }

    public JRadioButton getReseauOui() {
        return reseauOui;
    }

    public void setReseauOui(JRadioButton reseauOui) {
        this.reseauOui = reseauOui;
    }

    public JRadioButton getReseauNon() {
        return reseauNon;
    }

    public void setReseauNon(JRadioButton reseauNon) {
        this.reseauNon = reseauNon;
    }

    public JRadioButton getSkinBlancNormal() {
        return skinBlancNormal;
    }

    public void setSkinBlancNormal(JRadioButton skinBlancNormal) {
        this.skinBlancNormal = skinBlancNormal;
    }

    public JRadioButton getSkinBlancProfs() {
        return skinBlancProfs;
    }

    public void setSkinBlancProfs(JRadioButton skinBlancProfs) {
        this.skinBlancProfs = skinBlancProfs;
    }

    public JRadioButton getSkinBlancEleves() {
        return skinBlancEleves;
    }

    public void setSkinBlancEleves(JRadioButton skinBlancEleves) {
        this.skinBlancEleves = skinBlancEleves;
    }

    public JRadioButton getSkinNoirNormal() {
        return skinNoirNormal;
    }

    public void setSkinNoirNormal(JRadioButton skinNoirNormal) {
        this.skinNoirNormal = skinNoirNormal;
    }

    public JRadioButton getSkinNoirProfs() {
        return skinNoirProfs;
    }

    public void setSkinNoirProfs(JRadioButton skinNoirProfs) {
        this.skinNoirProfs = skinNoirProfs;
    }

    public JRadioButton getSkinNoirEleves() {
        return skinNoirEleves;
    }

    public void setSkinNoirEleves(JRadioButton skinNoirEleves) {
        this.skinNoirEleves = skinNoirEleves;
    }

    public ButtonGroup getGrTypePartie() {
        return grTypePartie;
    }

    public void setGrTypePartie(ButtonGroup grTypePartie) {
        this.grTypePartie = grTypePartie;
    }

    public ButtonGroup getGrReseau() {
        return grReseau;
    }

    public void setGrReseau(ButtonGroup grReseau) {
        this.grReseau = grReseau;
    }

    public ButtonGroup getGrSkinBlanc() {
        return grSkinBlanc;
    }

    public void setGrSkinBlanc(ButtonGroup grSkinBlanc) {
        this.grSkinBlanc = grSkinBlanc;
    }

    public ButtonGroup getGrSkinNoir() {
        return grSkinNoir;
    }

    public void setGrSkinNoir(ButtonGroup grSkinNoir) {
        this.grSkinNoir = grSkinNoir;
    }

    public JComboBox getListeJoueursBlancs() {
        return listeJoueursBlancs;
    }

    public void setListeJoueursBlancs(JComboBox listeJoueursBlancs) {
        this.listeJoueursBlancs = listeJoueursBlancs;
    }

    public JComboBox getListeJoueursNoirs() {
        return listeJoueursNoirs;
    }

    public void setListeJoueursNoirs(JComboBox listeJoueursNoirs) {
        this.listeJoueursNoirs = listeJoueursNoirs;
    }
}
