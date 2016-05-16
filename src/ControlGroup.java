/**
 * Created by mlucile on 27/04/16.
 */
public class ControlGroup
{
    private Accueil accueil;
    private Vue vue;
    public ControlButtonMenu controlButtonMenu;

    private ControlMenu controlMenu;
    private ControlButton controlButton;

    public ControlGroup(Accueil accueil)
    {
        this.accueil = accueil;
        vue = new Vue(accueil);
        controlButtonMenu = new ControlButtonMenu(accueil, vue);
        controlMenu = new ControlMenu(accueil, vue);
        controlButton = new ControlButton(accueil, vue);
        vue.display();
    }


}
