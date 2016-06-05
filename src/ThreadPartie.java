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
    private int port;

    // ajout SD
    private String ipServer;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int id; // =1 si joueur blanc et 2 si joueur noir
    
    ThreadPartie(Partie partie, ControlButton controller, int port, boolean isServer,
                        String ipServer, int choixJoueur, String pseudo)
    {
        this.partie = partie;
        this.controller = controller;
        this.port = port;
        this.isServer = isServer;
        this.ipServer = ipServer;
        this.monSkin = choixJoueur;
        this.monPseudo = pseudo;
    }

    ThreadPartie(Partie partie, ControlButton controller, int port, boolean isServer,
                 String ipServer, int choixJoueur, String pseudo, int modePartie)
    {
        this.partie = partie;
        this.controller = controller;
        this.port = port;
        this.isServer = isServer;
        this.ipServer = ipServer;
        this.monSkin = choixJoueur;
        this.monPseudo = pseudo;
        this.modePartie = 1;
        this.jeSuisBlanc = partie.jeSuisBlanc();
    }

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

                    /*todo :
                        - début du tour (via controleur)
                        - attendre fin tour
                        - envoyer les infos
                        Rq: pour faciliter, on peut envoyer tout le temps les même infos qqs soit le mode
                        par exemple: rowsrc,colsrc,rowdest,coldest, typeroque, typepromo, chronojoueurblanc,chronojoueurnoir,partiefinie
                        les 4 premiers servent aux déplacements (sauf roque)
                        typeroque = 0 si pas de roque, = 1 petit roque, = 2 grand roque
                        typepromo = 0 si pas de promo, = 1,2,... (type pièce) si promo
                    */
                    controller.debutTour();
                    partie.waitFinTour();

                    oos.writeObject(partie.getCaseSrc().getRow());
                    oos.writeObject(partie.getCaseSrc().getColumn());
                    oos.writeObject(partie.getCaseDest().getRow());
                    oos.writeObject(partie.getCaseDest().getColumn());
                    // on se casse pas la tête pour l'instant : on voit si ca marche en mode normal
                    oos.writeObject(0);
                    oos.writeObject(0);
                    oos.writeObject(0.0);
                    oos.writeObject(0.0);
                    oos.writeObject(partie.isPartieFinie());
                }
                else
                {
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
                    int roque = ois.readInt();
                    int promo = ois.readInt();
                    long chronoBlanc = ois.readLong();
                    long chronoNoir = ois.readLong();
                    boolean partieFinie = ois.readBoolean();

                    if (partieFinie)
                        stop = true;
                    else
                        controller.updatePartie(srcX, srcY, destX, destY, roque, promo, chronoBlanc, chronoNoir);
                }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

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
        System.out.println(monPseudo);
        System.out.println(pseudoAdversaire);
        partie.initPartie(monPseudo, pseudoAdversaire, modePartie, true, monSkin, skinAdversaire);
    }

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
        partie.initPartie(monPseudo, pseudoAdversaire, modePartie, true, monSkin, skinAdversaire);
    }
}