import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class Vue extends JFrame
{
    private Echiquier echiquier = null;
    private Model model  = null;
    /**
     * Vue (Constructeur)
     * construit la vue d'échec.
     *
     * @param model (...model...)
     */
    public Vue(Model model)
    {
        this.model = model;
        model.lancementPartie();
        model.majCasesAtteignable();
        initAttribut();
        creerWidget();
        setUndecorated(true);
        //setAlwaysOnTop(true);
        setResizable(false);
        setName("Chess");
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
    public void initAttribut()
    {
        echiquier = new Echiquier(model.getPartie().getBoard(), model);
    }

    /**
     * creerWidget
     * dessine la vue
     */
    public void creerWidget()
    {
        setContentPane(echiquier);
    }

    /**
     * setControlButton
     * ecoute les evenements sur les cases du plateau
     *
     * @param e (ecouteur de type MouseListener)
     */
    public void setControlButton(MouseListener e)
    {
        echiquier.addMouseListener(e);
    }

    /**
     * setControlMenu
     * ecoute les evenements du menu
     *
     * @param e (ecouteur de type ActionListener)
     */
    public void setControlMenu(ActionListener e)
    {

    }

    public void choixPiece(Pion pion)
    {
        ImageIcon[] piecesPossibles = new ImageIcon[4];
        if(pion.blanc)
        {
            piecesPossibles[0] = new ImageIcon("img/CavalierBlanc.png");
            piecesPossibles[1] = new ImageIcon("img/TourBlanc.png");
            piecesPossibles[2] = new ImageIcon("img/FouBlanc.png");
            piecesPossibles[3] = new ImageIcon("img/ReineBlanc.png");
        }
        else
        {
            piecesPossibles[0] = new ImageIcon("img/CavalierNoir.png");
            piecesPossibles[1] = new ImageIcon("img/TourNoir.png");
            piecesPossibles[2] = new ImageIcon("img/FouNoir.png");
            piecesPossibles[3] = new ImageIcon("img/ReineNoir.png");
        }


        int pieceSelected = JOptionPane.showOptionDialog(this, "Choisissez une pièce pour remplacer votre pion :",
                "Promotion d'un pion", JOptionPane.INFORMATION_MESSAGE, 2, null, piecesPossibles, null);

        System.out.println(pieceSelected);
        if(pieceSelected == 0)
            pion.promotion(1);
        else if(pieceSelected == 1)
            pion.promotion(2);
        else if(pieceSelected == 2)
            pion.promotion(3);
        else if(pieceSelected == 3)
            pion.promotion(4);
    }


    // getters & setters
    public Echiquier getEchiquier() {
        return echiquier;
    }
    public void setEchiquier(Echiquier echiquier) {
        this.echiquier = echiquier;
    }
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }
}
