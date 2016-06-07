import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
  Created by cladlink on 12/04/16.
 */

class ThreadPartie extends Thread
{
    private String monPseudo, pseudoAdversaire;
    private int monSkin, skinAdversaire;
    private int modePartie;
    private boolean jeSuisBlanc;
    private Partie partie;
    private boolean isServer;
    private ServerSocket conn;
    private Socket comm;
    private ControlButton controller;
    private ControlButtonMenu cbm;
    private int port;

    // ajout SD
    private String ipServer;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int id; // =1 si joueur blanc et 2 si joueur noir

    /**
     * ThreadPartie
     *
     *
     * @param partie ()
     * @param controller ()
     * @param port ()
     * @param isServer ()
     * @param ipServer ()
     * @param choixJoueur ()
     * @param pseudo ()
     * @param cbm ()
     */
    ThreadPartie(Partie partie, ControlButton controller, int port, boolean isServer,
                        String ipServer, int choixJoueur, String pseudo, ControlButtonMenu cbm)
    {
        this.partie = partie;
        this.controller = controller;
        this.port = port;
        this.isServer = isServer;
        this.ipServer = ipServer;
        this.monSkin = choixJoueur;
        this.monPseudo = pseudo;
        this.cbm = cbm;
    }

    /**
     * ThreadPartie
     *
     *
     * @param partie ()
     * @param controller ()
     * @param port ()
     * @param isServer ()
     * @param ipServer ()
     * @param choixJoueur ()
     * @param pseudo ()
     * @param modePartie ()
     * @param cbm ()
     */
    ThreadPartie(Partie partie, ControlButton controller, int port, boolean isServer,
                 String ipServer, int choixJoueur, String pseudo, int modePartie, ControlButtonMenu cbm)
    {
        this.partie = partie;
        this.controller = controller;
        this.port = port;
        this.isServer = isServer;
        this.ipServer = ipServer;
        this.monSkin = choixJoueur;
        this.monPseudo = pseudo;
        this.modePartie = modePartie;
        this.jeSuisBlanc = partie.jeSuisBlanc();
        this.cbm = cbm;
    }

    /**
     * run
     *
     */
    @Override
    public void run()
    {
        boolean stop = false;

        if(isServer)
            try
            {
                initServer();
            }
            catch(ClassNotFoundException | IOException e)
            {
                e.printStackTrace();
            }
        else
            try
            {
                initClient();
            }
            catch (ClassNotFoundException | IOException e)
            {
                e.printStackTrace();
            }

        // ajout SD
        try
        {
            while (!stop)
            {
            // si je suis le joueur courant
                if ( id == partie.getIdCurrentPlayer() )
                {
                    System.out.println("C'est à moi de jouer");





                    /*todo
                    Rq: pour faciliter, on peut envoyer tout le temps les même infos qqs soit le mode
                    par exemple: rowsrc,colsrc,rowdest,coldest, typeroque, typepromo, chronojoueurblanc,chronojoueurnoir,partiefinie
                    les 4 premiers servent aux déplacements
                    typepromo = 0 si pas de promo, = 1,2,... (type pièce) si promo
                    */
                    controller.debutTour();
                    partie.waitFinTour();
                    int rowSrc = partie.getCaseSrc().getRow();
                    int colSrc = partie.getCaseSrc().getColumn();
                    int rowDest = partie.getCaseDest().getRow();
                    int colDest = partie.getCaseDest().getColumn();
                    int promote = partie.getPromote();
                    oos.writeInt(rowSrc);
                    oos.writeInt(colSrc);
                    oos.writeInt(rowDest);
                    oos.writeInt(colDest);
                    oos.writeInt(promote);
                    oos.writeDouble(0.0);
                    oos.writeDouble(0.0);
                    oos.writeBoolean(partie.isPartieFinie());
                    oos.flush();
                    partie.setPromote(0);
                }
                else
                {
                    System.out.println("C'est à l'adversaire de jouer");
                     /*
                       - invalider la vue (via controleur)
                       - recevoir les infos
                       - si partiFinie == true : l'autre joueur à dépassé son temps de jeu (partie ou tour)  ->
                       j'ai gagné
                       - sinon appeler control.updatePartie()
                     */
                    controller.enableView(false);
                    int srcX = ois.readInt();
                    int srcY = ois.readInt();
                    int destX = ois.readInt();
                    int destY = ois.readInt();

                    int promo = ois.readInt();
                    long chronoBlanc = ois.readLong();
                    long chronoNoir = ois.readLong();
                    boolean partieFinie = ois.readBoolean();
                    if (partieFinie)
                        stop = true;
                    else
                        controller.updatePartie(srcX, srcY, destX, destY, promo, chronoBlanc, chronoNoir);
                    partie.setTourBlanc(!partie.isTourBlanc());
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * initServer
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void initServer() throws IOException,ClassNotFoundException
    {
        conn = new ServerSocket(port);
        comm = conn.accept();
        oos = new ObjectOutputStream(comm.getOutputStream());
        oos.flush();
        ois = new ObjectInputStream(comm.getInputStream());

        // échanger les infos (pseudos, qui joue en premier, ...)
        // envoi : mon pseudo, mon skin, mode partie, qui est blanc
        // recoi : autre pseudo, autre skin,*
        oos.writeObject(monPseudo);
        oos.writeInt(monSkin);
        oos.writeInt(modePartie);
        oos.writeBoolean(!jeSuisBlanc);
        oos.flush();
        pseudoAdversaire = (String)ois.readObject();
        skinAdversaire = ois.readInt();
        partie.initPartie(monPseudo, pseudoAdversaire, modePartie, true, monSkin, skinAdversaire);
        setId();
        cbm.initPartie();
    }

    /**
     * initClient
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void initClient() throws IOException,ClassNotFoundException
    {
        comm = new Socket(ipServer, port);
        ois = new ObjectInputStream(comm.getInputStream());
        oos = new ObjectOutputStream(comm.getOutputStream());
        oos.flush();
        // échanger les infos (pseudos, qui joue en premier, ...)
        // recoi : autre pseudo, autre skin, mode partie, qui est blanc
        // envoi : mon pseudo, mon skin
        pseudoAdversaire = (String)ois.readObject();
        skinAdversaire = ois.readInt();
        modePartie = ois.readInt();
        jeSuisBlanc = ois.readBoolean();
        oos.writeObject(monPseudo);
        oos.writeInt(monSkin);
        oos.flush();
        setId();
        partie.initPartie(pseudoAdversaire, monPseudo, modePartie, true, monSkin, skinAdversaire);
        cbm.initPartie();
    }

    /**
     * setID
     * définit l'ID en fonction de si le joueur est blanc ou noir
     *
     */
    private void setId()
    {
        if (jeSuisBlanc)
            id = 1;
        else
            id = 2;
    }
}