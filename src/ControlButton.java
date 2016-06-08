
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 Created by Michael on 06/04/16.
 */
class ControlButton extends MouseAdapter implements MouseMotionListener
{
    private String joueurBlanc, joueurNoir;
    private Accueil accueil;
    private Vue vue;
    private ChronoMode chrono;

    /**
     * ControlButton
     * @param accueil ()
     * @param vue ()
     */
    ControlButton(Accueil accueil, Vue vue)
    {
        this.accueil = accueil;
        this.vue = vue;
        this.chrono = new ChronoMode(vue, accueil, this);
    }

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
            vue.jOptionMessage("vous avez perdu !");
            accueil.getPartie().saveHistorique();
            Partie.deleteSave(joueurBlanc, joueurNoir);
            vue.setJMenuBar(null);
               vue.afficherMenu();

        if (accueil.getPartie().isTourBlanc())
            Joueur.ajouteVictoire(joueurNoir, joueurBlanc);
        else
            Joueur.ajouteVictoire(joueurBlanc, joueurNoir);
    }

    // ajout SD : valide ou invalide (selon state) les élément de la vue qui ne doivent plus
    // être en interaction avec l'utilisateur quand ce n'est pas son tour et
    // que la partie est en réseau

    /**
     * enableView
     * invalide ou non les éléments de la vue qui doivent être invalidé lors de l'attente de la partie
     * en réseau ou du joueur si ce n'est pas son tour
     * UNIQUEMENT POUR LA PARTIE EN RESEAU
     *
     * @param state (si true la vue est disponible, sinon non)
     */
    void enableView(boolean state)
    {
        vue.setEnabled(state);
    }

    /**
     * debutTour
     * déclanche le début du tour pour la partie en réseau et le joueur courant
     *
     */
    void debutTour()
    {
	/*
	     - si mode temps par tour: appeler partie.tourLimite() + lancer le chrono visuel
	     - sinon si mode temps partie : (re)démarrer le chrono visuel
	     - valider la vue
	 */
        if(accueil.getPartie().getModePartie() == 2) //Temps par tour
            chrono.tourLimite();
        if (accueil.getPartie().getModePartie() == 3)
            chrono.tempsLimite();
        if (accueil.getPartie().getModePartie() == 2 || accueil.getPartie().getModePartie() == 3)
            chrono.stopChrono();
        enableView(true);
        accueil.setCasesAtteignables(null);
        accueil.getPartie().getBoard().majCasesAtteignable();
    }

    /**
     * updatePartie
     * met à jour le model + la vue en fonction des infos reçues
     * UNIQUEMENT POUR LA PARTIE EN RESEAU
     *
     * @param rowSrc (rang de la case source)
     * @param colSrc (colonne de la case source)
     * @param rowDest (rang de la case destination)
     * @param colDest (colonne de la case destination)
     * @param typePromo (promo = 0 si pas de promo,
     *                  promo = 1 si Tour,
     *                  promo = 2 si Cavalier,
     *                  promo = 3 si Fou,
     *                  promo = 4 si Reine
     * @param chronoJoueurBlanc (etat du chrono du joueur blanc)
     * @param chronoJoueurNoir (etat du chrono du joueur noir)
     */
    void updatePartie(int rowSrc, int colSrc, int rowDest,int colDest,
                             int typePromo, long chronoJoueurBlanc, long chronoJoueurNoir)
    {

        Case caseSrc = accueil.getPartie().getBoard().getPlateau()[rowSrc][colSrc];
        Case caseDest = accueil.getPartie().getBoard().getPlateau()[rowDest][colDest];

        if (typePromo == 0)
            accueil.getPartie().getBoard().deplacer(caseSrc, caseDest, vue);
        else
        {
            boolean blanc = caseSrc.getPiece().blanc;

            if (blanc)
            {
                accueil.getPartie().getCimetiereBlanc().add(caseSrc.getPiece());
                accueil.getPartie().getPiecesBlanchesPlateau().remove(caseSrc.getPiece());
            }
            else
            {
                accueil.getPartie().getCimetiereNoir().add(caseSrc.getPiece());
                accueil.getPartie().getPiecesNoiresPlateau().remove(caseSrc.getPiece());
            }
            caseSrc.setPiece(null);

            if(caseDest.getPiece() != null)
            {
                if(blanc)
                {
                    accueil.getPartie().getCimetiereNoir().add(caseDest.getPiece());
                    accueil.getPartie().getPiecesNoiresPlateau().remove(caseDest.getPiece());
                }
                else{
                    accueil.getPartie().getCimetiereBlanc().add(caseDest.getPiece());
                    accueil.getPartie().getPiecesBlanchesPlateau().remove(caseDest.getPiece());
                }
            }

            if (typePromo == 1)
            {
                caseDest.setPiece(new Cavalier(caseDest, blanc));
            }
            else if(typePromo == 2)
            {
                caseDest.setPiece(new Tour(caseDest, blanc));
            }
            else if(typePromo == 3)
            {
                caseDest.setPiece(new Fou(caseDest, blanc));
            }
            else if(typePromo == 4)
            {
                caseDest.setPiece(new Reine(caseDest, blanc));
            }
        }

	/*
	   - mettre à jour les chrono, todo
	   - tester si roque
	   - faire le dplt + éventuellement promo OK + todo

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
                // si je clique sur une pièce qui est au joueur dont c'est le tour
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
                    accueil.getPartie().setTourBlanc(!accueil.getPartie().isTourBlanc()); // changer joueur courant.
                    accueil.getPartie().getBoard().majCasesAtteignable();
                    /* ajout SD :
                       - arrêter chrono si besoin todo
                       - appeler coupFait()
                    */
                    accueil.getPartie().coupFait(accueil.getCaseMemoire(), plateau[row][column]);
                    /* ajout SD : test à ajouter en premier
                       - si partieFinie est déjà à true -> perdu à cause du temps -> message todo
                     */

                    if (accueil.getPartie().isEchecEtMat())
                    {
                        // ajout SD : mettre à jour partie.partieFinie
                        vue.jOptionMessage("ECHEC ET MAT !");

                        if (accueil.getPartie().isTourBlanc())
                            Joueur.ajouteVictoire(joueurNoir, joueurBlanc);
                        else
                            Joueur.ajouteVictoire(joueurBlanc, joueurNoir);

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
                        Joueur.ajoutePat(joueurBlanc, joueurNoir);
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

                    accueil.getPartie().finTour();
                    /* ajout SD :
                      - si mode réseau appeler partie.finTour()
                      - sinon si partie pas finie :
                          - si mode temps par tour : lancer le chrono visuel todo
                      - sinon si mode temps partie : (re)demarrer le chrono visuel todo
                    */

                    if (accueil.getPartie().getModePartie() == 2)
                        chrono.tourLimite();
                    else if (accueil.getPartie().getModePartie() == 3)
                        chrono.tempsLimite();
                    chrono.stopChrono();
                }
            }
        }
    }

    /**
     * mouseMoved
     * Au passage de la souris sur une case, met à jour
     * @param e ()
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        int row, column;
        Point point = e.getPoint();
        if ( (int)point.getY() - 20 > 0 )
            row = (int) ((point.getY() - 20) / 80) ;
        else
            row = -1;

        if ( (int)point.getX() - 360 > 0 )
            column = (int) ((point.getX() - 360 ) / 80);
        else
            column = -1;
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

            if (row != -1 && column != -1)
                barreStatutMessage += " " + (Math.abs(row-8));
            else
                barreStatutMessage = "     ";
            vue.getVueEchiquier().getBs().setStatutText(barreStatutMessage);
            vue.getVueEchiquier().repaint();
        }


    }
    @Override
    public void mouseDragged(MouseEvent e)
    {

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

    void setJoueurBlanc(String joueurBlanc) { this.joueurBlanc = joueurBlanc; }
    void setJoueurNoir(String joueurNoir) { this.joueurNoir = joueurNoir; }
}