import java.awt.event.*;
import java.util.ArrayList;

/**
  Created by cladlink on 06/04/16.
 */
public class ControlButton extends Control implements MouseListener
{

    public ControlButton(Model model, Vue vue)
    {
        super(model, vue);
        vue.setControlButton(this);
    }

    /**
     * mouseClicked
     * définir évenement lorsque cliqué
     *
     * @param e (contient l'évènement)
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {

        if (e.getSource() == vue.getEchiquier())
        {
            int row = (e.getY()-50)/80;
            int column = (e.getX()-360)/80;

            Case[][] plateau = model.getPartie().getBoard().getPlateau();


            if( row >= 0
                    && row <=7
                    && column >=0
                    && column <=7
                    && plateau[row][column].getPiece() !=null)
            {

                System.out.println(row + " " + column);
                model.setCasesAtteignables(plateau[row][column].getPiece().casesAtteignables());
                /*ArrayList<Case> casesAtteignables = model.getCasesAtteignables();
                if (casesAtteignables.size()>0)
                    for (int i = 0; i < casesAtteignables.size(); i++)
                    {
                        vue.getEchiquier().paintComponent(vue.getGraphics());
                        System.out.println(casesAtteignables.get(i).getRow() + " " + casesAtteignables.get(i).getColumn());
                    }*/
                vue.repaint();
            }
        }

    }


    @Override
    public void mousePressed(MouseEvent e)
    {
    // ne pas utiliser
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        // ne pas utiliser
    }

    /**
     * mouseEntered
     * Définit les évènements lorsque la souris entre dans la zone définit
     * @param e (contient l'evenement)
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    /**
     * mouseExited
     * Définit les évènements lorsque la souris sort de la zone définit
     * @param e (contient l'évènement)
     */
    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
