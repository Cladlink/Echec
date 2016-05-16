
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/*vue.jOptionMessage("Jeu développé par : \n Michael BOUTBOUL\n Marie-Lucile CANIARD\n Sylvain GUYOT" +
        "\n Kevin LIMACHER\n Gabriel MERCIER\n Adonis N'DOLO.");
*/
/**
 *Created by mlucile on 12/05/16.
 */
public class VueMainMenu extends JFrame
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

    private JButton lancerPartie;
    private JButton rejoindrePartie;
    private JButton nouveauJoueur;
    private JButton credit;
    private JButton chargerPartie;
    private JButton retourMenu;

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

    public VueMainMenu(Model model) {
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

        lancerPartie = new JButton(model.getLancerPartieTitre());
        rejoindrePartie = new JButton(model.getRejoindrePartieTitre());
        nouveauJoueur = new JButton(model.getNouveauJoueurTitre());
        credit = new JButton(model.getCreditTitre());
        chargerPartie = new JButton(model.getChargerPartieTitre());
        retourMenu = new JButton(model.getRetourMenuTitre());

        partieNormale = new JRadioButton(model.getPartieNormaleTitre(), true);
        partieTempsCoupsLimites = new JRadioButton(model.getPartieTempsCoupsLimitesTitre());
        partieTempsLimite = new JRadioButton(model.getPartieTempsLimiteTitre());
        reseauOui = new JRadioButton(model.getReseauOuiTitre(), true);
        reseauNon = new JRadioButton(model.getReseauNonTitre());
        skinBlancNormal = new JRadioButton(model.getSkinBlancNormalTitre(), true);
        skinBlancProfs = new JRadioButton(model.getSkinBlancProfsTitre());
        skinBlancEleves = new JRadioButton(model.getSkinBlancElevesTitre());
        skinNoirNormal = new JRadioButton(model.getSkinNoirNormalTitre(), true);
        skinNoirProfs = new JRadioButton(model.getSkinNoirProfsTitre());
        skinNoirEleves = new JRadioButton(model.getSkinNoirElevesTitre());

        grTypePartie = new ButtonGroup();
        grReseau = new ButtonGroup();
        grSkinBlanc = new ButtonGroup();
        grSkinNoir = new ButtonGroup();

        listeJoueursBlancs = new JComboBox();
        listeJoueursNoirs = new JComboBox();

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
        lancerPartie.setFont(police);
        rejoindrePartie.setFont(police);
        nouveauJoueur.setFont(police);
        credit.setFont(police);
        chargerPartie.setFont(police);
        retourMenu.setFont(police);

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
        formulaire.add(reseauOui);
        formulaire.add(partieTempsCoupsLimites);
        formulaire.add(reseauNon);
        formulaire.add(partieTempsLimite);
        formulaire.add(retourMenu);

        JPanel nouveauJ = new JPanel();
        nouveauJ.setOpaque(false);
        nouveauJ.add(nouveauJoueur);

        JPanel organisation = new JPanel(new BorderLayout());
        organisation.setOpaque(false);
        organisation.add(titreJeu, BorderLayout.NORTH);
        organisation.add(formulaire, BorderLayout.CENTER);
        organisation.add(nouveauJ, BorderLayout.EAST);

        // Mise en place du fond d'écran
        setLayout(new BorderLayout());
        background = new JLabel(new ImageIcon("img/fond2_2.jpg"));
        background.setSize(xSize, ySize);
        add(background);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
    }

    public void creerWidgetAccueil()
    {
        JPanel centre = new JPanel(new GridLayout(8,1,0,10));
        centre.setOpaque(false);
        centre.add(espace5);
        centre.add(lancerPartie);
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
        lancerPartie.addActionListener(listener);
        retourMenu.addActionListener(listener);
    }

    public void messagePop(String message)
    {
        JOptionPane fEnregistrerPseudo = new JOptionPane();
        fEnregistrerPseudo.showInputDialog(this, message, "Enregistrement", JOptionPane.QUESTION_MESSAGE);
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

    public JButton getLancerPartie() {
        return lancerPartie;
    }

    public JButton getRetourMenu() {
        return retourMenu;
    }


    /**
     * initAttribut
     * Instancie les attributs
     */
    private void initAttributPartie()
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
}
