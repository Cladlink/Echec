import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 Created by cladlink on 03/05/16.
 */
public class VueGraveyard
{
    private Partie partie;
    private ImageIcon bgCimetiere;
    private boolean isBlanc;

    VueGraveyard(Partie partie, boolean isBlanc)
    {
        this.partie = partie;
        this.bgCimetiere = new ImageIcon("img/cimetiere2.png");
        this.isBlanc = isBlanc;
    }

    /**
     * PaintMe
     * Paint l'objet graphique
     *
     * @param g (boite à outil servant à peindre des éléments)
     * @param xBase (axe x de l'objet)
     * @param yBase (axe y de l'objet)
     */
    void paintMe(Graphics g, int xBase, int yBase)
    {
        int i, pion=0, tour=0, cavalier=0, fou=0, reine=0, ecartEntrePiece = 60, taillePiece = 60;
        Image imgPion=null, imgCavalier=null, imgTour=null, imgFou=null, imgReine=null;

        //cimetiere blanc
        g.drawImage(bgCimetiere.getImage(), xBase, yBase-50, 200, 650, null);//110 150
        ArrayList<Piece> cimetiere;
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));

        if (isBlanc)
            cimetiere = partie.getCimetiereBlanc();
        else
            cimetiere = partie.getCimetiereNoir();

        //fait d'abord le tour du cimetiere pour identifier les pieces et garder les img en mémoire pour les dessiner par la suite
        for(i = 0; i<cimetiere.size(); i++)
        {
            if (cimetiere.get(i) instanceof Pion)
            {
                pion++;
                imgPion = cimetiere.get(i).skin.getImage();
            }
            else if (cimetiere.get(i) instanceof Tour)
            {
                tour++;
                imgTour = cimetiere.get(i).skin.getImage();
            }
            else if (cimetiere.get(i) instanceof Cavalier)
            {
                cavalier++;
                imgCavalier = cimetiere.get(i).skin.getImage();
            }
            else if (cimetiere.get(i) instanceof Fou)
            {
                fou++;
                imgFou = cimetiere.get(i).skin.getImage();
            }
            else if (cimetiere.get(i) instanceof Reine)
            {
                reine++;
                imgReine = cimetiere.get(i).skin.getImage();
            }
        }

        //dessine
        if (pion!=0)
        {
            g.drawString(String.valueOf(pion), xBase + 60, yBase +150 + ecartEntrePiece);
            g.drawImage(imgPion, xBase + 80, yBase +100 + ecartEntrePiece, taillePiece, taillePiece, null);
        }
        if (tour!=0)
        {
            g.drawString(String.valueOf(tour), xBase + 60, yBase +150 + ecartEntrePiece * 2);
            g.drawImage(imgTour, xBase + 80, yBase +100 + ecartEntrePiece * 2, taillePiece, taillePiece, null);
        }
        if (cavalier!=0)
        {
            g.drawString(String.valueOf(cavalier), xBase + 60, yBase +150 + ecartEntrePiece * 3);
            g.drawImage(imgCavalier, xBase + 80, yBase +100 + ecartEntrePiece * 3, taillePiece, taillePiece, null);
        }
        if (fou!=0)
        {
            g.drawString(String.valueOf(fou), xBase + 60, yBase +150 + ecartEntrePiece * 4);
            g.drawImage(imgFou, xBase + 80, yBase +100 + ecartEntrePiece * 4, taillePiece, taillePiece, null);
        }
        if (reine!=0)
        {
            g.drawString(String.valueOf(reine), xBase + 60, yBase +150 + ecartEntrePiece * 5);
            g.drawImage(imgReine, xBase + 80, yBase +100 + ecartEntrePiece * 5, taillePiece, taillePiece, null);
        }

        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
    }
}
