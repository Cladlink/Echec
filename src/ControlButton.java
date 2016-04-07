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
