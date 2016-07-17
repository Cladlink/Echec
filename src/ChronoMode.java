import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

/**
  Created by cladlink on 04/06/16.
 */
class ChronoMode
{
    private Accueil accueil;
    private ControlPartie controlPartie;
    private boolean isDateBlancDepart, isDateNoirDepart;
    private java.util.Timer jTimer;
    private Timer chronoBlanc, chronoNoir;
    private static boolean horsJeu;
    private int compteurBlanc = 30, compteurNoir = 30;

    /**
     * ChronoMode
     * Si une partie contient un mode de jeu nécessitant des timers, cette classe est utilisée.
     *
     * @param vue (La vue pour mettre à jour les chronomètres)
     * @param accueil (base du model)
     * @param controlPartie (controlButton, nécessaire pour accéder à la méthode finDeTempsPartie)
     */
    ChronoMode(Vue vue, Accueil accueil, ControlPartie controlPartie)
    {
        this.accueil = accueil;
        this.controlPartie = controlPartie;
        horsJeu = false;

        ActionListener alBlanc = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent exprLambda)
            {
                vue.getVueEchiquier().getChronoBlanc().setSeconde(compteurBlanc %60);
                vue.getVueEchiquier().getChronoBlanc().setMinute(compteurBlanc /60);
                vue.repaint();
            }
        };
        chronoBlanc = new Timer(1000, alBlanc);

        ActionListener alNoir = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent exprLambda)
            {
                vue.getVueEchiquier().getChronoNoir().setSeconde(compteurNoir %60);
                vue.getVueEchiquier().getChronoNoir().setMinute(compteurNoir /60);
                vue.repaint();
            }
        };
        chronoNoir = new Timer(1000, alNoir);
    }

    /**
     * stopChrono
     * arrete le chrono et demarre celui d'après
     *
     */
    void stopChrono()
    {
        if (accueil.getPartie().isTourBlanc())
        {
            chronoNoir.stop();
            chronoBlanc.start();
        }
        else
        {
            chronoBlanc.stop();
            chronoNoir.start();
        }
        if (accueil.getPartie().getModePartie() == 2)
            if (accueil.getPartie().isTourBlanc())
                compteurBlanc = 30;
            else
                compteurNoir = 30;
    }

    /**
     * tourLimite
     * méthode définissant le chronomètre avec un temps limité par tour
     *
     */
    synchronized void tourLimite()
    {
        if (jTimer != null)
            jTimer.cancel();
        // ajout SD : mettre jTimer en attribut sinon inaccessible par d'autres méthodes
        if (accueil.getPartie().isTourBlanc())
        {
            if (jTimer != null)
                jTimer.cancel();
            compteurBlanc--;
            if (!isDateBlancDepart)
                isDateBlancDepart = true;
            else if (compteurBlanc == 0 && !horsJeu)
            {
                controlPartie.finDeJeuTemps();
                horsJeu = true;
            }
        }
        else
        {
            if (jTimer != null)
                jTimer.cancel();
            //tour noir
            compteurNoir--;
            if (!isDateNoirDepart)
            {
                isDateNoirDepart = true;
            }
            if (compteurNoir == 0 && !horsJeu)
            {
                controlPartie.finDeJeuTemps();
                horsJeu = true;
            }
        }
        TimerTask timerTaskTourLimite = new TimerTask()
        {
            @Override
            public void run()
            {
                tourLimite();
            }
        };
        jTimer = new java.util.Timer();
        jTimer.schedule(timerTaskTourLimite, 1000);
    }

    /**
     * tempsLimite
     * méthode définissant le chronomètre avec un temps limité par partie
     *
     */
    void tempsLimite()
    {
        //tour blanc
        if (accueil.getPartie().isTourBlanc())
        {
            if (jTimer != null)
                jTimer.cancel();
            compteurBlanc--;
            if (!isDateBlancDepart)
                isDateBlancDepart = true;
            else if (compteurBlanc == 0 && !horsJeu)
            {
                controlPartie.finDeJeuTemps();
                horsJeu = true;
            }
        }
        else
        {
            if (jTimer != null)
                jTimer.cancel();
            else
            {
                compteurBlanc = 300;
                compteurNoir = 300;
            }
            //tour noir
            compteurNoir--;
            if (!isDateNoirDepart)
            {
                isDateNoirDepart = true;
            }
            if (compteurNoir == 0 && !horsJeu)
            {
                controlPartie.finDeJeuTemps();
                horsJeu = true;
            }
        }
        TimerTask timerTaskTempsLimite = new TimerTask()
        {
            @Override
            public void run()
            {
                tempsLimite();
            }
        };
        jTimer = new java.util.Timer();
        jTimer.schedule(timerTaskTempsLimite, 1000);
    }

    /**
     * setHorsJeu
     * définit si la partie est hors jeu ou non
     *
     * @param horsJeu (variable définissant que la partie est terminée)
     */
    static void setHorsJeu(boolean horsJeu) { ChronoMode.horsJeu = horsJeu; }
}