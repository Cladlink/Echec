import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 Created by cladlink on 26/05/16.
 */
class MusiqueChess
{
    private static MediaPlayer mp;
    private static final String PERCU = "Musique/Percu.mp3";
    private static final String MEDIEVAL_THEME = "Musique/MedievalTheme.mp3";

    static void playMedievalTheme()
    {
        JFXPanel jfxPanel = new JFXPanel();
        mp = new MediaPlayer(new Media(Paths.get(MEDIEVAL_THEME).toUri().toString()));
        mp.play();
        mp.setCycleCount(MediaPlayer.INDEFINITE);
    }
    static void stopMedievalTheme()
    {
        if (mp != null)
            mp.stop();
    }
}
