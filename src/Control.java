public abstract class Control
{

    Model model;
    Vue vue;

    public Control(Model model, Vue vue)
    {
        this.model = model;
        this.vue = vue;
    }
}

