import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

class Vue extends JFrame
{
    private VueEchiquier vueEchiquier;
    private Model model;

    private JMenuItem nvlPart;
    private JMenuItem rejPart;
    private JMenuItem ldPart;
    private JMenuItem svPart;
    private JMenuItem quitter;
    private JMenuItem undo;
    private JMenuItem historique;
    private JMenuItem aide;
    private JMenuItem aPropos;
    /**
     * Vue (Constructeur)
     * construit la vue d'échec.
     *
     * @param model (...model...)
     */
    Vue(Model model)
    {
        this.model = model;
        initAttribut();
        setUndecorated(true);
        //setAlwaysOnTop(true);
        setResizable(false);
        setName("Chess");
        model.lancementPartie("anonymous", "anonymous");
        model.getPartie().getBoard().majCasesAtteignable();
        vueEchiquier = new VueEchiquier(model.getPartie().getBoard(), model, this);
        creerWidget();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int)tk.getScreenSize().getWidth();
        int ySize = (int)tk.getScreenSize().getHeight();
        setSize(xsize, ySize);
    }

    /**
     * initAttribut
     * Instancie les attributs
     */
    private void initAttribut()
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
    public void creerWidget()
    {
        setContentPane(vueEchiquier);
    }

    /**
     * setControlButton
     * ecoute les evenements sur les cases du plateau
     *
     * @param e (ecouteur de type MouseListener)
     */
    void setControlButton(MouseListener e)
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
