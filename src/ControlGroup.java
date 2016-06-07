/**
 * Created by mlucile on 27/04/16.
 */
class ControlGroup
{
    private Accueil accueil;
    private Vue vue;
    private ControlButtonMenu controlButtonMenu;
    private ControlMenu controlMenu;
    private ControlButton controlButton;

    ControlGroup(Accueil accueil)
    {
        this.accueil = accueil;
        vue = new Vue(accueil);
        controlButton = new ControlButton(accueil, vue);
        controlButtonMenu = new ControlButtonMenu(accueil, vue, controlButton);
        controlMenu = new ControlMenu(accueil, vue);
        vue.display();
    }


}
