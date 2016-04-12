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
    private Control controller = null;

    public ThreadPartie(Partie partie, Boolean isServer, ControlButton controller)
    {
        this.partie = partie;
        this.isServer = isServer;
        this.controller = controller;
    }

    @Override
    public void run()
    {
        if(isServer)
        {
            // créer conn + attendre connexion
        }
        else
        {
            // créer comm
        }
        // créer les objets flux
        // échanger les infos (pseudos, qui joue en premier, ...)
        boolean myturn;
        if (true)// mettre ici le teste si il y a une réponse du serveur
        {
            this.moi = partie.getJoueurBlanc();
            myturn = true;
        }
        else
        {
            this.moi = partie.getJoueurNoir();
            myturn = false;
        }
        boolean stop = false;
        partie.attenteDebutPartie();
        while(!stop)
        {
            if (myturn)
            {
                partie.attenteAction();// j'attends que le joueur ai joué;
                // envoyer à l'autre le coup joué
                if (partie.isFinPartie())
                    stop = true;
                else
                    myturn = false;
            }
            else
            {
                // attendre coup joué (sur la socket)
                // appel d'une méthode du controller qui met à jour la partie avec le coup joué
                partie.attenteAction();
                if (partie.isFinPartie())
                    stop = true;
                else
                    myturn = false;
            }
        }
    }
}
