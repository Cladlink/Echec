import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 Created by cladlink on 26/05/16.
 */
public class MusiqueChess
{
    private static MediaPlayer ais;
    private static final String MEDIEVAL_THEME = "Musique/MedievalTheme.mp3";

    public static void playMedievalTheme()
    {
        JFXPanel jfxPanel = new JFXPanel();
        ais = new MediaPlayer(new Media(Paths.get(MEDIEVAL_THEME).toUri().toString()));
        ais.play();
    }
    public static void stopMedievalTheme()
    {
        ais.stop();
    }
}
