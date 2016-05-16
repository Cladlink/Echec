/**
 * Created by mlucile on 27/04/16.
 */
public class ControlGroup
{
    private Model model;
    private VueMainMenu vueMainMenu;
    public ControlButtonMenu controlButtonMenu;

    private ControlMenu controlMenu;
    private ControlButton controlButton;

    public ControlGroup(Model model)
    {
        this.model = model;
        vueMainMenu = new VueMainMenu(model);
        controlButtonMenu = new ControlButtonMenu(model, vueMainMenu);
        controlMenu = new ControlMenu(model, vueMainMenu);
        controlButton = new ControlButton(model, vueMainMenu);
        vueMainMenu.display();
    }


}
