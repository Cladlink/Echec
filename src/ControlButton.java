import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 Created by Michael on 06/04/16.
 */
class ControlButton extends MouseAdapter implements MouseMotionListener
{

    //variable pour mode de partie

    private String joueurBlanc, joueurNoir;
    private Accueil accueil;
    private Vue vue;
    private ChronoMode chrono;

    ControlButton(Accueil accueil, Vue vue)
    {
        this.accueil = accueil;
        this.vue = vue;
        this.chrono = new ChronoMode(vue, accueil, this);
    }

    // ajout SD : lance le chrono du joueur courant


    /**
     * finDeJeuTemps
     * Met fin à la partie si la variable partieFinie est à true dans Partie.
     *
     */
    void finDeJeuTemps()
    {
        accueil.getPartie().setPartieFinie(true);
        if (accueil.getPartie().getModePartie() == 2
                || accueil.getPartie().getModePartie() == 3)
            if (accueil.getPartie().isPartieFinie())
            {
                vue.jOptionMessage("vous avez perdu !");
                accueil.getPartie().saveHistorique();
                Partie.deleteSave(joueurBlanc, joueurNoir);
                vue.setJMenuBar(null);
                vue.afficherMenu();
            }
    }

    // ajout SD : valide ou invalide (selon state) les élément de la vue qui ne doivent plus
    // être en interaction avec l'utilisateur quand ce n'est pas son tour et
    // que la partie est en réseau

    /**
     *
     * @param state ()
     */
    public void enableView(boolean state)
    {

    }

    // ajout SD : déclenche le début du tour pour partie réseau et joueur courant

    /**
     *
     */
    public void debutTour()
    {
	/* todo:
	     - si mode temps par tour: appeler partie.tourLimite() + lancer le chrono visuel
	     - sinon si mode temps partie : (re)démarrer le chrono visuel
	     - valider la vue
	 */
    }

    // ajout SD : met à jour modèle+vue en fonction des infos recues, partie en
    // réseau uniquement
    public void updatePartie(int rowSrc, int colSrc, int rowDest, int colDest, int typeRoque, int typePromo, long chronoJoueurBlanc, long chronoJoueurNoir) {

	/* todo: quasi identique au cas normal
	   - mettre à jour les chrono,
	   - tester si roque
	   - faire le dplt + éventuellement promo

	   - reste quasi identique au cas normal excepté appel à finTour() qu'il ne faut pas faire
	 */
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
                    ArrayList<Case> casesAtteignables = accueil.getPartie().getBoard().getPlateau()[row][column].getPiece().casesAtteignables;
                    accueil.setCasesAtteignables(casesAtteignables);
                    accueil.setCaseMemoire(accueil.getPartie().getBoard().getPlateau()[row][column]);
                    vue.repaint();
                }
                else if(accueil.getCasesAtteignables() != null
                        && accueil.getCasesAtteignables().contains(plateau[row][column])
                        && accueil.getPartie().isTourBlanc() == accueil.getCaseMemoire().getPiece().isBlanc() )
                {
                    // ajout SD : à modifier pour ne pas passer la vue en param -> ajouter un attribut is Promotion
                    // pour tester ce cas là.
                    accueil.getPartie().getBoard().deplacer(accueil.getCaseMemoire(), plateau[row][column], this.vue);
                    accueil.setCasesAtteignables(null);
                    accueil.getPartie().setTourBlanc(!accueil.getPartie().isTourBlanc());// changer joueur courant.
                    accueil.getPartie().getBoard().majCasesAtteignable();
                    /* ajout SD :
                       - arrêter chrono si besoin
                       - appeler coupFait()
                    */
                    /* ajout SD : test à ajouter en premier
                       - si partieFinie est déjà à true -> perdu à cause du temps -> message
                     */
                    if (accueil.getPartie().isEchecEtMat())
                    {
                        // ajout SD : mettre à jour partie.partieFinie
                        vue.jOptionMessage("ECHEC ET MAT !");

                        if (accueil.getPartie().isTourBlanc())
                            Joueur.ajouteVictoire(joueurBlanc, joueurNoir);
                        else
                            Joueur.ajouteVictoire(joueurNoir, joueurBlanc);

                        // ajout SD : à ne faire que si la partie n'est pas en réseau
                        // ou si je suis le serveur
                        Partie.deleteSave(joueurBlanc, joueurNoir);
                        accueil.getPartie().saveHistorique();
                        vue.setJMenuBar(null);
                        vue.afficherMenu();
                    }
                    else if (accueil.getPartie().isPat())
                    {
                        // ajout SD : mettre à jour partie.partieFinie
                        vue.jOptionMessage("PAT");

                        if (accueil.getPartie().isTourBlanc())
                            Joueur.ajoutePat(joueurBlanc, joueurNoir);
                        else
                            Joueur.ajoutePat(joueurNoir, joueurBlanc);

                        // ajout SD : à ne faire que si la partie n'est pas en réseau
                        // ou si je suis le serveur
                        accueil.getPartie().saveHistorique();
                        Partie.deleteSave(joueurBlanc, joueurNoir);
                        vue.setJMenuBar(null);
                        vue.afficherMenu();
                    }
                    else if (accueil.getPartie().isEchec())
                    {
                        vue.jOptionMessage("ECHEC !");
                    }
                    vue.repaint();
                    /* ajout SD :
                      - si mode réseau appeler partie.finTour()
                      - sinon si partie pas finie :
                      - si mode temps par tour : lancer le chrono visuel
                      - sinon si mode temps partie : (re)demarrer le chrono visuel
                    */
                    //kevin : appele l'algo pour savoir si partie fini ou pas

                    if (accueil.getPartie().getModePartie() == 2)
                        chrono.tourLimite();
                    else if (accueil.getPartie().getModePartie() == 3)
                        chrono.tempsLimite();

                    //change de joueur donc chrono inversé
                    chrono.stopChrono();
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        Point point = e.getPoint();
        /**
         * Modifier ça : s'amuser avec les coordonnées pour trouver le bon calibrage
         */
        // int row = (int) ((point.getY() - 100) / 80) ;
        // int column = (int) ((point.getX() - 450 ) / 80);
        int row = (int) ((point.getY() - 20) / 80) ;
        int column = (int) ((point.getX() - 360 ) / 80);
        // pour rester dans le plateau
        String barreStatutMessage = "";
        if (row >= 0
                && row <= 7
                && column >= 0
                && column <= 7)
        {
            switch (column)
            {
                case 0 :
                    barreStatutMessage += 'A';
                    break;
                case 1 :
                    barreStatutMessage += 'B';
                    break;
                case 2 :
                    barreStatutMessage += 'C';
                    break;
                case 3 :
                    barreStatutMessage += 'D';
                    break;
                case 4 :
                    barreStatutMessage += 'E';
                    break;
                case 5 :
                    barreStatutMessage += 'F';
                    break;
                case 6 :
                    barreStatutMessage += 'G';
                    break;
                case 7 :
                    barreStatutMessage += 'H';
                    break;
            }
            barreStatutMessage += " " + (Math.abs(row-8));
            vue.getVueEchiquier().getBs().setStatutText(barreStatutMessage);
            vue.getVueEchiquier().repaint();
        }


    }

    public void mouseDragged(MouseEvent e){

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
