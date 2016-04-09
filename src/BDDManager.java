import java.sql.*;
import java.util.ArrayList;
import java.io.*;
/**
 * Created by cladlink on 12/03/16.
 */

/*
 todo * cette classe a été écrite pour lire un fichier .sql (soit on garde ca, soit il faut envoyer
 todo * requete par requete ce qui n'est pas un soucis en soit mais il faut choisir !
 */
public class BDDManager
{
    private String url;
    private String login;
    private String passwd;
    private Connection cn = null;
    private Statement  st = null;

    public BDDManager(String url, String login, String passwd)
    {
        this.url = url;
        this.login = login;
        this.passwd = passwd;
    }

    /**
     * start()
     * sert à initialiser la connexion à la BDD
     */
    public void start()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, passwd);
            st = cn.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
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
                System.out.println(e);
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
            System.err.println(e);
        }
    }
}
