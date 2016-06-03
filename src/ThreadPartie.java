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
    private Joueur moi;
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
    
    public ThreadPartie(Partie partie, ControlButton controller, int port, boolean isServer,
                        String ipServer)
    {
        this.partie = partie;
        this.controller = controller;
        this.port = port;
        this.isServer = isServer;
        this.ipServer = ipServer;
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
        /*try
        {
            while (!stop)
            {
            // si je suis le joueur courant
                if ( id == partie.getIdCurrentPlayer() )
                {
                   */ /*
                    todo :
                        - début du tour (via controleur)
                        - attendre fin tour
                        - envoyer les infos
                        Rq: pour faciliter, on peut envoyer tout le temps les même infos qqs soit le mode
                        par exemple: rowsrc,colsrc,rowdest,coldest,typeroque, typepromo, chronojoueurblanc,chronojoueurnoir,partiefinie
                        les 4 premiers servent aux déplacements (sauf roque)
                        typeroque = 0 si pas de roque, = 1 petit roque, = 2 grand roque
                        typepromo = 0 si pas de promo, = 1,2,... (type pièce) si promo
                    */
               /* }
                else
                {*/
                     /*
                     todo :
                       - invalider la vue (via controleur)
                       - recevoir les infos
                       - si partiFinie == true : l'autre joueur à dépassé son temps de jeu (partie ou tour)  -> j'ai gagné
                       - sinon appeler control.updatePartie()
                     */
                    
                /*}
            // - si partieFinie == true -> stop = true
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/

    }

    private void initServer() throws IOException,ClassNotFoundException
    {
        conn = new ServerSocket(port);
        comm = conn.accept();
        oos = new ObjectOutputStream(comm.getOutputStream());
        oos.flush();
        ois = new ObjectInputStream(comm.getInputStream());
        // échanger les infos (pseudos, qui joue en premier, ...)
        oos.writeObject(monPseudo);
        oos.writeInt(monSkin);
        oos.flush();
        // envoi : mon pseudo, mon skin, mode partie, qui est blanc
        // recoi : autre pseudo, autre skin,*

        oos.writeObject(pseudoAdversaire);
        oos.writeInt(monSkin);
        oos.writeInt(modePartie);
        oos.writeBoolean(jeSuisBlanc);
        pseudoAdversaire = (String)ois.readObject();
        skinAdversaire = ois.readInt();
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
        partie.initPartie(monPseudo, pseudoAdversaire, modePartie, true, monSkin, skinAdversaire);
    }
}
