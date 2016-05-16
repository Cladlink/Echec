/**
 * Created by mlucile on 27/04/16.
 */
public class ControlGroup
{
    private Model model;
    private Vue vue;
    public ControlButtonMenu controlButtonMenu;

    private ControlMenu controlMenu;
    private ControlButton controlButton;

    public ControlGroup(Model model)
    {
        this.model = model;
        vue = new Vue(model);
        controlButtonMenu = new ControlButtonMenu(model, vue);
        controlMenu = new ControlMenu(model, vue);
        controlButton = new ControlButton(model, vue);
        vue.display();
    }


}
