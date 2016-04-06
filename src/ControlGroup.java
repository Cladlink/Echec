/*
 * Created by cladlink on 25/03/16.
 */
public class ControlGroup
{

    private Model model;
    private Vue vue;
    private ControlMenu controlMenu;
    private ControlButton controlButton;

    public ControlGroup(Model model)
    {
        this.model = model;
        vue = new Vue(model);
        controlMenu = new ControlMenu(model, vue);
        controlButton = new ControlButton(model, vue);
    }
}
