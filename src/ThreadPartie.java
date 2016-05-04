import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
  Created by cladlink on 12/04/16.
 */
public class ThreadPartie extends Thread
{
    private Joueur moi;
    private Partie partie = null;
    private Boolean isServer;
    private ServerSocket conn = null;
    private Socket comm = null;
    private ControlButton controller = null;
    private int port;

    public ThreadPartie(Partie partie, Boolean isServer, ControlButton controller, int port)
    {
        this.partie = partie;
        this.isServer = isServer;
        this.controller = controller;
        this.port = port;
    }

    @Override
    public void run()
    {
        if(isServer)// todo y a pas un soucis à faire comme ca ? on va pas réinitialiser la connection à chaque fois ? si ?
        {
            try
            {
                conn = new ServerSocket(port);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            comm = new Socket();
        }

        // créer les objets flux
        // échanger les infos (pseudos, qui joue en premier, ...)
        boolean myTurn;
        if (true)// todo mettre ici le teste si il y a une réponse du serveur
        {
            this.moi = partie.getJoueurBlanc();
            myTurn = true;
        }
        else
        {
            this.moi = partie.getJoueurNoir();
            myTurn = false;
        }
        boolean stop = false;
        partie.attenteDebutPartie();
        while(!stop)
        {
            if (myTurn)
            {
                partie.attenteAction();// j'attends que le joueur ai joué;
                // envoyer à l'autre le coup joué
                if (partie.isFinPartie())
                    stop = true;
                else
                    myTurn = false;
            }
            else
            {
                // attendre coup joué (sur la socket)
                // appel d'une méthode du controller qui met à jour la partie avec le coup joué
                partie.attenteAction();
                if (partie.isFinPartie())
                    stop = true;
                else
                    myTurn = false;
            }
        }
    }
}
