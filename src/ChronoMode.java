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
    private java.util.Timer timer;
    private Accueil accueil;
    private ControlButton cb;
    private boolean isDateBlancDepart, isDateNoirDepart;
    private Date dateBlancDepart, dateNoirDepart;
    private java.util.Timer tm;
    private Timer chronoBlanc;
    private Timer chronoNoir;
    private TimerTask tt;
    private int secondeBlanc=0, minuteBlanc=0, secondeNoir=0, minuteNoir=0;
    private static boolean horsJeu;

    ChronoMode(Vue vue, Accueil accueil, ControlButton cb)
    {
        this.accueil = accueil;
        this.cb = cb;
        horsJeu = false;
        // ajout SD
        ActionListener alBlanc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exprLambda) {
                // décrementer le chrono du joueur blanc si > 0
                // si chrono > 0 vue.repaint(...);
                // sinon invalider vue + arrêter chrono

                //decremente de 1 la seconde
                secondeBlanc--;
                if (secondeBlanc < 0) {
                    secondeBlanc = 59;
                    minuteBlanc--;
                }
                if (minuteBlanc >= 0 && secondeBlanc >= 0) {
                    //maj des variables dans VueTimer
                    vue.getVueEchiquier().getChronoBlanc().setSeconde(secondeBlanc);
                    vue.getVueEchiquier().getChronoBlanc().setMinute(minuteBlanc);
                    vue.repaint();
                }
            }
        };
        chronoBlanc = new Timer(1000, alBlanc);

        ActionListener alNoir = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent exprLambda) {
                // décrementer le chrono du joueur noir si > 0
                // si chrono > 0 vue.repaint(...);
                // sinon invalider vue + arrêter chrono
                //decremente de 1 la seconde
                secondeNoir--;
                if (secondeNoir < 0) {
                    secondeNoir = 59;
                    minuteNoir--;
                }
                if (minuteNoir >= 0 && secondeNoir >= 0) {
                    //maj des variables dans VueTimer
                    vue.getVueEchiquier().getChronoNoir().setSeconde(secondeNoir);
                    vue.getVueEchiquier().getChronoNoir().setMinute(minuteNoir);

                    vue.repaint();
                }
            }
        };
        chronoNoir = new Timer(1000, alNoir);

        startChrono();
    }
    void startChrono()
    {
	/* TO DO:
	   - si mode temps par tour : mettre à 30s chronoJoueurBlanc/Noir selon le joueur courant
	   - si joueur courant == blanc -> démarrer chronoBlanc sinon chronoNoir
	 */

        //initialisation du chrono
        if (accueil.getPartie() != null)
        {
            if (accueil.getPartie().getModePartie() == 2)
            {
                secondeBlanc = 30;
                minuteBlanc = 0;
                secondeNoir = 30;
                minuteNoir = 0;
            }
            else if (accueil.getPartie().getModePartie() == 3)
            {
                secondeBlanc = 0;
                minuteBlanc = 15;
                secondeNoir = 0;
                minuteNoir = 15;
            }
        }
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
                secondeBlanc = 30;
            else
                secondeNoir = 30;

    }

    /**
     *
     */
    synchronized void tourLimite()
    {
        if (timer != null)
            timer.cancel();
        // ajout SD : mettre tm en attribut sinon inaccessible par d'autres méthodes
        timer = new java.util.Timer();
        tt = new TimerTask()
        {
            @Override
            public void run()
            {
                if (!horsJeu)
                {
                    cb.finDeJeuTemps();
                    horsJeu = true;
                }
            }
        };

        timer.schedule(tt, 30000);
    }

    /**
     *
     */
    synchronized void tempsLimite()
    {
        Calendar calendar = Calendar.getInstance(); // creates calendar
        calendar.setTime(new Date()); // sets calendar time/date

        Date tempsActuel = calendar.getTime();
        //tour blanc
        if ( accueil.getPartie().isTourBlanc())
        {
            if (!isDateBlancDepart )
            {
                calendar.add(Calendar.MINUTE,15);
                tempsActuel = calendar.getTime();
                dateBlancDepart = tempsActuel;
                isDateBlancDepart = true;
            }
            else if (tempsActuel.after(dateBlancDepart) && !horsJeu)
            {
                cb.finDeJeuTemps();
                horsJeu = true;

            }
        }
        //tour noir
        else
        {
            if (!isDateNoirDepart)
            {
                calendar.add(Calendar.MINUTE,15);
                tempsActuel = calendar.getTime();
                dateNoirDepart = tempsActuel;
                isDateNoirDepart = true;
            }
            if (tempsActuel.after(dateNoirDepart))
            {
                cb.finDeJeuTemps();
                horsJeu = true;
            }
        }
        //verifie chaque seconde le temps
        TimerTask tt = new TimerTask()
        {
            @Override
            public void run() {
                tempsLimite();
            }
        };
        tm = new java.util.Timer();
        tm.schedule(tt, 1000);
    }

    static void setHorsJeu(boolean horsJeu) { ChronoMode.horsJeu = horsJeu; }
}