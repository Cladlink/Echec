/*
 * Created by cladlink on 25/03/16.
 */
public class ControlGroup
{

    private Model model;
    private Vue vue;

    public ControlGroup(Model model)
    {
        this.model = model;
        vue = new Vue(model);
    }
}
