import java.awt.event.*;

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
        for (int i = 0; i < vue.plateau.length; i++)// juste l'algo qui est important
        {
            for (int j = 0; j < vue.plateau[i].length; j++)// juste l'algo qui est important
            {
                if (e.getSource() == /*todo une case*/)
                {
                    // tests pour le mouvement
                    if (model.getPartie().getBoard().estDispo(model.getPlateauDeCase()[i][j])
                            && model.getPartie().getBoard().getCaseMemoire() == null)
                    {
                        model.getPartie().getBoard().deplacer(
                                model.getPartie().getBoard().getCaseMemoire(),
                                model.getPlateau()[i][j]
                        );
                    }
                    // tests pour choisir sa pièce
                    // si la pièce est de la couleur de la personne qui doit jouer
                    else if (model.getPartie().getBoard().getPlateau()[i][j].getPiece().isBlanc
                            == model.getPartie().isTourBlanc())
                    {
                        model.getPartie().getBoard().setCaseMemoire(
                                model.getPartie().getBoard().getPlateau()[i][j]);
                        model.casesJouables(i, j);
                    }
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
