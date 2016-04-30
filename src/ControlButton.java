import java.awt.event.*;
import java.util.ArrayList;

/**
  Created by Michael on 06/04/16.
 */
public class ControlButton extends Control implements MouseListener
{

    public ControlButton(Model model, Vue vue)
    {
        super(model, vue);
        vue.setControlButton(this);
    }

    /**
     * mouseClicked todo
     * définir évenement lorsque cliqué
     *
     * @param e (contient l'évènement)
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        int row = (e.getY()-50)/80;
        int column = (e.getX()-360)/80;
        Case[][] plateau = model.getPartie().getBoard().getPlateau();
        if (e.getSource() == vue.getEchiquier())
        {
            if( row >= 0
                    && row <=7
                    && column >=0
                    && column <=7)
            {
                if (model.getPartie().getBoard().getPlateau()[row][column].getPiece() != null)
                System.out.println(model.getPartie().getBoard().getPlateau()[row][column].getPiece());
                // si je clique sur une pièce  qui est au joueur dont c'est le tour
                if ( plateau[row][column].getPiece() != null
                        && model.getPartie().isTourBlanc() == plateau[row][column].getPiece().isBlanc() )
                {
                    ArrayList<Case> casesAtteignables =
                            model.getPartie().getBoard().getPlateau()[row][column].getPiece().casesAtteignables;
                    model.setCasesAtteignables(casesAtteignables);
                    model.setCaseMemoire(model.getPartie().getBoard().getPlateau()[row][column]);


                    if (casesAtteignables.size() > 0)
                        for (int i = 0; i < casesAtteignables.size(); i++)
                        {
                            vue.getEchiquier().paintComponent(vue.getGraphics());
                        }
                    vue.repaint();
                }
                else if(model.getCasesAtteignables() != null
                        && model.getCasesAtteignables().contains(plateau[row][column])
                        && model.getPartie().isTourBlanc() == model.getCaseMemoire().getPiece().isBlanc() )
                {
                    model.getPartie().getBoard().deplacer(model.getCaseMemoire(), plateau[row][column], this.vue);
                    model.setCasesAtteignables(null);
                    model.getPartie().setTourBlanc(!model.getPartie().isTourBlanc());
                    model.majCasesAtteignable();
                    vue.repaint();
                }
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
