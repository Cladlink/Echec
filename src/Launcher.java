/**
  Created by cladlink on 25/03/16.
 */

public class Launcher
{
    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Accueil accueil = new Accueil();
                ControlGroup controler = new ControlGroup(accueil);
            }
        });
    }
}
