/**
  Created by cladlink on 25/03/16.
 */
/*
todo Domas

todo * voir pour virer le model (si on le vire)
todo * voir si la méthode de JoptionPane est une bonne idée ou pas
todo * voir pour le réseau pour jeudi prochain
todo * voir pour le rafraichissement de la vue
 */
public class Launcher
{
    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Model model = new Model();
                ControlGroup controler = new ControlGroup(model);
            }
        });
    }
}
