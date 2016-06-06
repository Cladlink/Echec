import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by sakalypse on 05/06/16.
 */
public class ControlButtonHistorique implements ActionListener {

    Vue vue;
    ArrayList<String> ListhistoriqueLocal;
    Board board;
    int indice;
    JFrame vueHisto;

    ControlButtonHistorique(Vue vue, ArrayList<String> ListhistoriqueLocal, Board board, JFrame vueHisto){
        this.vue = vue;
        this.ListhistoriqueLocal = ListhistoriqueLocal;
        this.board = board;
        this.vueHisto = vueHisto;
        indice = 0;//ListhistoriqueLocal.size()-1;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        board.plateauDeBase();

        if (e.getSource().equals(vue.getSuivant()))
            if (indice+1<=ListhistoriqueLocal.size())
            {
                indice++;
            }
        else if (e.getSource().equals(vue.getPrecedent()))
            if (indice-1>=0)
            {
                indice--;
            }
        else if  (e.getSource().equals(vue.getRetour()))
        {
            vue.vueHistoExit();
            return;
        }
        for (int i=0; i<indice; i++)
        {
            int cDepart = Integer.parseInt(String.valueOf(ListhistoriqueLocal.get(i).charAt(2)));
            int rDepart = Integer.parseInt(String.valueOf(ListhistoriqueLocal.get(i).charAt(3)));
            Case caseDepart = board.getPlateau()[rDepart][cDepart];
            int cFinal = Integer.parseInt(String.valueOf(ListhistoriqueLocal.get(i).charAt(4)));
            int rFinal = Integer.parseInt(String.valueOf(ListhistoriqueLocal.get(i).charAt(5)));
            Case caseFinal = board.getPlateau()[rFinal][cFinal];
            board.deplacer(caseDepart, caseFinal, vue);
        }
        vueHisto.repaint();
    }
}