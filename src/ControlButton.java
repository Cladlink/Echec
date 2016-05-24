import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

/**
  Created by Michael on 06/04/16.
 */
class ControlButton extends MouseAdapter
{
    // ajout SD
    Timer chronoBlanc;
    Timer chronoNoir;

    private Accueil accueil;
    private Vue vue;

    ControlButton(Accueil accueil, Vue vue)
    {
        this.accueil = accueil;
        this.vue = vue;
	
	// ajout SD
	ActionListener alBlanc = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent exprLambda)
		{
		    // décrementer le chrono du joueur blanc si > 0
		    // si chrono > 0 vue.repaint(...);
		    // sinon invalider vue + arrêter chrono
		}
	    };
	chronoBlanc = new Timer(1000,alBlanc);
	ActionListener alNoir = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent exprLambda)
		{
		    // décrementer le chrono du joueur noir si > 0
		    // si chrono > 0 vue.repaint(...);
		    // sinon invalider vue + arrêter chrono
		}
	    };
	chronoNoir = new Timer(1000,alNoir);
	
    }

    // ajout SD : lance le chrono du joueur courant
    private void startChrono() {
	/* TO DO:
	   - si mode temps par tour : mettre à 30s chronoJoueurBlanc/Noir selon le joueur courant
	   - si joueur courant == blanc -> démarrer chronoBlanc sinon chronoNoir
	*/
    }

    // ajout SD : arrête le chrono du joueur courant
    private void stopChrono() {
	/* TO DO:

	*/
    }
    
    // ajout SD : valide ou invalide (selon state) les élément de la vue qui ne doivent plus
    // être en interaction avec l'utilisateur quand ce n'est pas son tour et
    // que la partie est en réseau
    public void enableView(boolean state) {
    }

    // ajout SD : déclenche le début du tour pour partie réseau et joueur courant
    public void debutTour() {
	/* TO DO :
	     - si mode temps par tour : appeler partie.tourLimite() + lancer le chrono visuel
	     - sinon si mode temps partie : (re)démarrer le chrono visuel
	     - valider la vue
	 */
    }

    // ajout SD : met à jour modèle+vue en fonction des infos recues, partie en
    // réseau uniquement
    public void updatePartie(int rowSrc, int colSrc, int rowDest, int colDest, int typeRoque, int typePromo, long chronoJoueurBlanc, long chronoJoueurNoir) {

	/* TO DO : quasi identique au cas normal
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

		    
                    String joueurBlanc = accueil.getPartie().getJoueurBlanc().getPseudo();
                    String joueurNoir = accueil.getPartie().getJoueurNoir().getPseudo();

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
