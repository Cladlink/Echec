import java.sql.*;
import java.util.ArrayList;
import java.io.*;
/**
  Created by cladlink on 12/03/16.
 */

class BDDManager
{
    private final String BDD_URL = "jdbc:mysql://bdd.midway.ovh/KfK3f9Z7rd4v467z?useSSL=false";
    private final String BDD_USER = "KfK3f9Z7rd4v467z";
    private final String BDD_PASSWORD =  "Cbup4w7aJ9hGSsJz";
    private Connection cn = null;
    private Statement  st = null;


    /**
     * start()
     * sert à initialiser la connexion à la BDD
     */
    public void start()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(BDD_URL, BDD_USER, BDD_PASSWORD);
            st = cn.createStatement();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * stop()
     * Sert à rompre la connexion avec le BDD
     */
    public void stop()
    {

        try
        {
            cn.close();
            st.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * edit
     * Sert pour l'envoie de toutes requêtes sauf les SELECT
     * @param requete
     */
    public void edit(String requete)
    {
        System.out.println(requete);
        try
        {
            st.executeUpdate(requete);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ask(String Requete)
     * Sert à envoyer au serveur toute requête de type SELECT
     * @param requete
     * @return
     */
    public ArrayList<ArrayList<String>> ask(String requete)
    {
        System.out.println(requete);
        ArrayList<ArrayList<String>> select = new ArrayList<>();

        try
        {
            ResultSet rs = st.executeQuery(requete);
            ResultSetMetaData rsmd = rs.getMetaData();
            int nbcols = rsmd.getColumnCount();

            System.out.println(rsmd.getColumnCount());
            System.out.println(rsmd.getColumnLabel(1));
            System.out.println(rsmd.getColumnLabel(2));
            int i=0;
            while(rs.next())
            {
                select.add(new ArrayList<>());
                for (int j = 1; j <= nbcols; j++)
                    select.get(i).add(rs.getString(j));
                i++;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return select;
    }

    /**
     * lire()
     * permet de lire un fichier sql et de l'exécuter
     */
    public void lire(String adressSQLFile)
    {
        BufferedReader lecture;
        String fichier = "", fichierTemp;
        String[] requete;
        try
        {
            lecture = new BufferedReader(new FileReader(adressSQLFile));
            try
            {
                while ((fichierTemp = lecture.readLine()) != null)
                {
                    fichier += fichierTemp;
                    fichier += " ";
                }
                requete = fichier.split(";");
                for (int i = 0; i<requete.length; i++)
                {
                    requete[i] += ";";
                    System.out.println(i);
                    System.out.println(requete[i]);
                    if (requete[i].contains("SELECT"))
                    {
                        this.ask(requete[i]);
                    }
                    else
                    {
                        this.edit(requete[i]);
                    }
                }
            }
            catch (EOFException e)
            {
                e.printStackTrace();
            }
            finally
            {
                lecture.close();
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.println("le fichier est introuvable");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * main
     * ce main n'est utiliser que pour créer les tables.
     */
    public static void main(String[] args)
    {
        BDDManager bdd = new BDDManager();
        bdd.start();
        //bdd.lire("src/BDDechec.sql");

        /*bdd.edit("INSERT INTO JOUEUR (pseudoJoueur, nbPartiesJoueur, nbPartiesGagneesJoueur," +
                " nbPartiesPerduesJoueur, nbPartiesAbandonneeJoueur, partieEnCoursJoueur, trophee1, trophee2, trophee3)" +
                " VALUES (\"toto\", 0, 0, 0, 0, 0, false, false, false);");
        bdd.edit("INSERT INTO JOUEUR (pseudoJoueur, nbPartiesJoueur, nbPartiesGagneesJoueur, " +
                "nbPartiesPerduesJoueur, nbPartiesAbandonneeJoueur, partieEnCoursJoueur, trophee1, trophee2, trophee3)" +
                " VALUES (\"titi\", 0, 0, 0, 0, 0, false, false, false);");
        bdd.edit("INSERT INTO HISTORIQUE VALUES (null, 1, 2, \"2015-12-12\", \"PB1213-PB1213-PB1213-PB1213-PB1213-PB1213-PB1213\");");*/
        bdd.edit("INSERT INTO SAUVEGARDE VALUES (null, 1, 2, null, true, \"PB12-PN13-RN65\", 6);");
        ArrayList<ArrayList<String>> test = bdd.ask("SELECT * FROM SAUVEGARDE;");
        System.out.println(test);
        bdd.stop();
    }
}
