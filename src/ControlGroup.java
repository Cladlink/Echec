/**
 Created by mlucile on 27/04/16.
 */
class ControlGroup
{
    private Accueil accueil;
    private Vue vue;
    private ControlMenuAccueil controlMenuAccueil;
    private ControlMenuPartie controlMenuPartie;
    private ControlPartie controlPartie;
    private ControlHistorique controlHistorique;

    /**
     * ControlGroup
     * (créé l'ensmeble des éléments du model MVC
     *
     * @param accueil (Base du Model)
     */
    ControlGroup(Accueil accueil)
    {
        vue = new Vue(accueil);
        this.accueil = accueil;
        controlPartie = new ControlPartie(accueil, vue);
        controlHistorique = new ControlHistorique(accueil, vue, controlPartie);
        controlMenuAccueil = new ControlMenuAccueil(accueil, vue, controlPartie);
        controlMenuPartie = new ControlMenuPartie(accueil, vue);
        vue.display();
    }
}
