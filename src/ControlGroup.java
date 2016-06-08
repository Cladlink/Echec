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
    private ControlButtonHistorique controlButtonHistorique;

    ControlGroup(Accueil accueil)
    {
        vue = new Vue(accueil);
        this.accueil = accueil;
        controlButton = new ControlButton(accueil, vue);
        controlButtonHistorique = new ControlButtonHistorique(accueil, vue, controlButton);
        controlButtonMenu = new ControlButtonMenu(accueil, vue, controlButton);
        controlMenu = new ControlMenu(accueil, vue);
        vue.display();
    }


}
