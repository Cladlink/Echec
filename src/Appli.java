/**
 * Created by mlucile on 30/03/16.
 */
class Appli {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                Model model = new Model();
                ControlGroup control = new ControlGroup(model);
            }
        });
    }
}