import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 Created by sakalypse on 05/06/16.
 */
class ControlButtonHistorique implements ActionListener
{

    private ArrayList<String> histoCoups;
    private Vue vue;
    private Accueil accueil;
    private int indice;
    private ControlButton controlButton;

    ControlButtonHistorique(Accueil accueil, Vue vue, ControlButton controlButton)
    {
        this.controlButton = controlButton;
        this.vue = vue;
        this.accueil = accueil;
        indice = 0;
        vue.setButtonHistoControl(this);
        histoCoups = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        boolean indiceChange = false;
        boolean indiceSup = false;
        int typePromo;
        JFrame vueHisto = vue.getVueHisto();
        histoCoups = vue.recupererHistoCoupsPartie( accueil.getPartieAVisualiser() );
        histoCoups.add(0,null);
        if (e.getSource().equals(vue.getSuivant()))
        {
            if (indice + 1 < histoCoups.size())
            {
                indice++;
                indiceChange = true;
                indiceSup = true;
            }
        }
        else if (e.getSource().equals(vue.getPrecedent()))
        {
            if (indice > 0)
            {
                indice--;
                indiceChange=true;
            }
        }
        else if  (e.getSource().equals(vue.getRetour()))
        {
            indice = 0;
            vue.vueHistoExit();
            return;
        }
        if (indiceChange)
        {
            System.out.println(histoCoups.get(indice));
            if (indiceSup)
            {
                int cDepart = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(2)));
                int rDepart = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(3)));
                int cFinal = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(4)));
                int rFinal = Integer.parseInt(String.valueOf(histoCoups.get(indice).charAt(5)));

                typePromo = 0;
                // En cas de promotion
                if(histoCoups.get(indice).split("").length > 9
                        && histoCoups.get(indice).split("")[9].equals("?"))
                {
                    if (histoCoups.get(indice).split("")[10].equals("c"))
                        typePromo = 1;
                    else if (histoCoups.get(indice).split("")[10].equals("t"))
                        typePromo = 2;
                    else if (histoCoups.get(indice).split("")[10].equals("f"))
                        typePromo = 3;
                    else if (histoCoups.get(indice).split("")[10].equals("q"))
                        typePromo = 4;
                }
                else if(histoCoups.get(indice).split("").length > 6
                        && histoCoups.get(indice).split("")[6].equals("?"))
                {
                    if(histoCoups.get(indice).split("")[7].equals("c"))
                        typePromo = 1;
                    else if (histoCoups.get(indice).split("")[7].equals("t"))
                        typePromo = 2;
                    else if (histoCoups.get(indice).split("")[7].equals("f"))
                        typePromo = 3;
                    else if (histoCoups.get(indice).split("")[7].equals("q"))
                        typePromo = 4;
                }
                controlButton.updatePartie(rDepart, cDepart, rFinal, cFinal, typePromo, 0, 0);
            }
            else
                accueil.getPartie().undoHisto(histoCoups.get(indice+1));
            vueHisto.repaint();
        }
    }
}