import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
  Created by cladlink on 04/06/16.
 */
class ChronoMode
{
    private Accueil accueil;
    private ControlButton cb;
    private boolean isDateBlancDepart, isDateNoirDepart;
    private java.util.Timer tm;
    private Timer chronoBlanc;
    private Timer chronoNoir;
    private static boolean horsJeu;
    private int compteurB = 30, compteurN = 30;

    ChronoMode(Vue vue, Accueil accueil, ControlButton cb)
    {
        this.accueil = accueil;
        this.cb = cb;
        horsJeu = false;
        // ajout SD
        ActionListener alBlanc = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent exprLambda) {
                // décrementer le chrono du joueur blanc si > 0
                // si chrono > 0 vue.repaint(...);
                // sinon invalider vue + arrêter chrono

                //decremente de 1 la seconde
                    //maj des variables dans VueTimer
                    vue.getVueEchiquier().getChronoBlanc().setSeconde(compteurB%60);
                    vue.getVueEchiquier().getChronoBlanc().setMinute(compteurB/60);
                    vue.repaint();

            }
        };
        chronoBlanc = new Timer(1000, alBlanc);

        ActionListener alNoir = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent exprLambda)
            {
                // décrementer le chrono du joueur noir si > 0
                // si chrono > 0 vue.repaint(...);
                // sinon invalider vue + arrêter chrono
                //decremente de 1 la seconde
                    //maj des variables dans VueTimer
                    vue.getVueEchiquier().getChronoNoir().setSeconde(compteurN%60);
                    vue.getVueEchiquier().getChronoNoir().setMinute(compteurN/60);
                    vue.repaint();
            }
        };
        chronoNoir = new Timer(1000, alNoir);

    }

    // ajout SD : arrête le chrono du joueur courant

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
                compteurB = 30;
            else
                compteurN = 30;
    }

    /**
     *
     */
    synchronized void tourLimite()
    {
        if (tm != null)
            tm.cancel();
        // ajout SD : mettre tm en attribut sinon inaccessible par d'autres méthodes
        if (accueil.getPartie().isTourBlanc())
        {
            System.out.println(compteurB);
            if (tm != null)
                tm.cancel();
            compteurB--;
            if (!isDateBlancDepart)
                isDateBlancDepart = true;
            else if (compteurB == 0 && !horsJeu)
            {
                cb.finDeJeuTemps();
                horsJeu = true;
            }
        }
        else
        {
            System.out.println(compteurN);
            if (tm != null)
                tm.cancel();
            //tour noir
            compteurN--;
            if (!isDateNoirDepart)
            {
                isDateNoirDepart = true;
            }
            if (compteurN == 0 && !horsJeu)
            {
                cb.finDeJeuTemps();
                horsJeu = true;
            }
        }
        TimerTask tt2 = new TimerTask()
        {
            @Override
            public void run()
            {
                tourLimite();
            }
        };
        tm = new java.util.Timer();
        tm.schedule(tt2, 1000);
    }

    /**
     *
     */
    void tempsLimite()
    {
        //tour blanc
        if (accueil.getPartie().isTourBlanc())
        {
            if (tm != null)
                tm.cancel();


            compteurB--;
            if (!isDateBlancDepart)
                isDateBlancDepart = true;
            else if (compteurB == 0 && !horsJeu)
            {
                cb.finDeJeuTemps();
                horsJeu = true;
            }
        }
        else
        {
            if (tm != null)
                tm.cancel();
            else
            {
                compteurB = 300;
                compteurN = 300;
            }
            //tour noir
            compteurN--;
            if (!isDateNoirDepart)
            {
                isDateNoirDepart = true;
            }
            if (compteurN == 0 && !horsJeu)
            {
                cb.finDeJeuTemps();
                horsJeu = true;
            }
        }
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run()
            {
                tempsLimite();
            }
        };
        tm = new java.util.Timer();
        tm.schedule(tt, 1000);
    }

    static void setHorsJeu(boolean horsJeu) { ChronoMode.horsJeu = horsJeu; }
}