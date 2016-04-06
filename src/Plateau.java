/**
  Created by cladlink on 06/04/16.
 */
public class Plateau
{
    private final int raw = 8;
    private final int column = 8;
    private Case[][] plateau = null;
    private Case caseMemoire = null; //enregistre la case qui a été cliquée


    /**
     * Plateau (constructeur)
     *
     * initie le plateau
     * la case mémoire à un emplacement vide
     *
     */
    public Plateau()
    {

    }

    /**
     * plateauDeBae
     * "soulage" le constructeur sur le placement des pièces au début d'une partie
     */
    public void plateauDeBase()
    {

    }

    /**
     * deplacer
     *  test si la case cliquée est pleine ou non en prenant en compte la case mémoire qui
     *  stocke une case qui peut être déplacé au choix du joueur.
     *  Cette méthode doit prendre en compte le déplacement OU le fait d'afficher les deplacements possible
     *  d'une piece (ou rien du tout)
     *
     * @param caseCliquee (case qui a ... été cliquée)
     */
    public void deplacer(Case caseCliquee)
    {

    }

    /**
     * etat
     * Enregistre l'état du plateau à un instant T (pour la sauvegarde)
     *
     */
    public void etat()
    {

    }

    /**
     * save
     * envoie l'insert en base de donnée afin de sauvegarder l'état du plateau
     * utiliser la class BDDManager
     */
    public void save()
    {

    }
}
