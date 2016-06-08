/**
  Created by cladlink on 25/03/16.
 */

/*
 todo caller Musique + sons
 todo reseau
 todo historique visuel (KEVIN ?!?)
 todo animation (Marie-Lucile)
 todo sauvegarde bug (ne pas permettre une deuxième save entre deux joueurs)
 todo relire tout
 */
public class Chess
{
    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Accueil accueil = new Accueil();
                ControlGroup controler = new ControlGroup(accueil);
                MusiqueChess.playMedievalTheme();
            }
        });
    }
}
