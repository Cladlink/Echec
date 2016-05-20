import java.awt.event.*;
import java.util.ArrayList;

/**
  Created by Michael on 06/04/16.
 */
class ControlButton extends MouseAdapter
{

    private Accueil accueil;
    private Vue vue;

    ControlButton(Accueil accueil, Vue vue)
    {
        this.accueil = accueil;
        this.vue = vue;
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
        int row = (e.getY()-20)/80;
        int column = (e.getX()-360)/80;
        Case[][] plateau = accueil.getPartie().getBoard().getPlateau();
        if (e.getSource().equals(vue.getVueEchiquier()))
        {
            if( row >= 0
                    && row <=7
                    && column >=0
                    && column <=7)
            {
                if (accueil.getPartie().getBoard().getPlateau()[row][column].getPiece() != null)
                    System.out.println(accueil.getPartie().getBoard().getPlateau()[row][column].getPiece());
                // si je clique sur une pièce  qui est au joueur dont c'est le tour
                if ( plateau[row][column].getPiece() != null
                        && accueil.getPartie().isTourBlanc() == plateau[row][column].getPiece().isBlanc() )
                {
                    ArrayList<Case> casesAtteignables =
                            accueil.getPartie().getBoard().getPlateau()[row][column].getPiece().casesAtteignables;
                    accueil.setCasesAtteignables(casesAtteignables);
                    accueil.setCaseMemoire(accueil.getPartie().getBoard().getPlateau()[row][column]);
                    vue.repaint();
                }
                else if(accueil.getCasesAtteignables() != null
                        && accueil.getCasesAtteignables().contains(plateau[row][column])
                        && accueil.getPartie().isTourBlanc() == accueil.getCaseMemoire().getPiece().isBlanc() )
                {
                    accueil.getPartie().getBoard().deplacer(accueil.getCaseMemoire(), plateau[row][column], this.vue);
                    accueil.setCasesAtteignables(null);
                    accueil.getPartie().setTourBlanc(!accueil.getPartie().isTourBlanc());
                    accueil.getPartie().getBoard().majCasesAtteignable();
                    String joueurBlanc = accueil.getPartie().getJoueurBlanc().getPseudo();
                    String joueurNoir = accueil.getPartie().getJoueurNoir().getPseudo();
                    if (accueil.getPartie().isEchecEtMat())
                    {
                        vue.jOptionMessage("ECHEC ET MAT !");
                        if (accueil.getPartie().isTourBlanc())
                            Joueur.ajouteVictoire(joueurBlanc, joueurNoir);
                        else
                            Joueur.ajouteVictoire(joueurNoir, joueurBlanc);
                        accueil.getPartie().deleteSave(joueurBlanc, joueurNoir);
                        vue.setJMenuBar(null);
                        vue.afficherMenu();
                    }
                    else if (accueil.getPartie().isPat())
                    {
                        vue.jOptionMessage("PAT");
                        if (accueil.getPartie().isTourBlanc())
                            Joueur.ajoutePat(joueurBlanc, joueurNoir);
                        else
                            Joueur.ajoutePat(joueurNoir, joueurBlanc);
                        accueil.getPartie().deleteSave(joueurBlanc, joueurNoir);
                        vue.setJMenuBar(null);
                        vue.afficherMenu();
                    }
                    else if (accueil.getPartie().isEchec())
                    {
                        vue.jOptionMessage("ECHEC !");
                    }
                    vue.repaint();
                }
            }
        }
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