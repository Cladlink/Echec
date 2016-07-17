import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 Created by sakalypse on 05/06/16.
 */
class ControlHistorique implements ActionListener
{
    private ArrayList<String> histoCoups;
    private Vue vue;
    private Accueil accueil;
    private int indice;
    private ControlPartie controlPartie;

    /**
     * ControlHistorique
     * Controller spécifique à la navigation dans l'historique
     *
     * @param accueil (model)
     * @param vue (vue)
     * @param controlPartie controller de la partie)
     */
    ControlHistorique(Accueil accueil, Vue vue, ControlPartie controlPartie)
    {
        this.controlPartie = controlPartie;
        this.vue = vue;
        this.accueil = accueil;
        indice = 0;
        vue.setButtonHistoControl(this);
        histoCoups = new ArrayList<>();
    }

    /**
     * actionPerformed
     * Ce qui se passe à chaque clic
     *
     * @param e (event de type clic)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean isIndiceChanged = false;
        boolean isElementSuivant = false;
        int typePromo;
        JFrame vueHisto = vue.getVueHisto();
        if (histoCoups.size() == 0)
        {
            histoCoups = vue.recupererHistoCoupsPartie( accueil.getPartieAVisualiser() );
            histoCoups.add(0,null);
        }

        if (e.getSource().equals(vue.getSuivant()))
        {
            if (indice + 1 < histoCoups.size())
            {
                indice++;
                isIndiceChanged = true;
                isElementSuivant = true;
            }
        }
        else if (e.getSource().equals(vue.getPrecedent()))
        {
            if (indice > 0)
            {
                indice--;
                isIndiceChanged=true;
            }
        }
        else if  (e.getSource().equals(vue.getRetour()))
        {
            indice = 0;
            histoCoups.clear();
            vue.vueHistoExit();
            return;
        }
        if (isIndiceChanged)
        {
            if (isElementSuivant)
            {
                int cDepart = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(2)));
                int rDepart = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(3)));
                int cFinal = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(4)));
                int rFinal = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(5)));

                typePromo = 0;
                // En cas de promotion
                if(histoCoups.get(indice).split("").length > 9
                        && histoCoups.get(indice).split("")[9].equals("?"))
                    if (histoCoups.get(indice).split("")[10].equals("c"))
                        typePromo = 1;
                    else if (histoCoups.get(indice).split("")[10].equals("t"))
                        typePromo = 2;
                    else if (histoCoups.get(indice).split("")[10].equals("f"))
                        typePromo = 3;
                    else if (histoCoups.get(indice).split("")[10].equals("q"))
                        typePromo = 4;
                else if(histoCoups.get(indice).split("").length > 6
                        && histoCoups.get(indice).split("")[6].equals("?"))
                    if(histoCoups.get(indice).split("")[7].equals("c"))
                        typePromo = 1;
                    else if (histoCoups.get(indice).split("")[7].equals("t"))
                        typePromo = 2;
                    else if (histoCoups.get(indice).split("")[7].equals("f"))
                        typePromo = 3;
                    else if (histoCoups.get(indice).split("")[7].equals("q"))
                        typePromo = 4;
                controlPartie.updatePartie(rDepart, cDepart, rFinal, cFinal, typePromo, 0, 0);
            }
            else
                accueil.getPartie().undoHisto(histoCoups.get(indice+1));
            vueHisto.repaint();
        }
    }
}