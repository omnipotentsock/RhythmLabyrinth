package AdventureTest;

import java.io.File;
import java.io.IOException;

import AdventureModel.AdventureGame;
import AdventureModel.AdventureLoader;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import views.AdventureGameView;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAdventureTest {
    @Test
    void getCommandsTest() throws IOException {
        AdventureGame game = new AdventureGame("TinyGame");
        String commands = game.player.getCurrentRoom().getCommands();
        assertEquals("WEST,UP,NORTH,IN,SOUTH,DOWN", commands);
    }

    @Test
    void getSavedDir() {
        String separator = System.getProperty("file.separator");

        String currDir = new File("").getAbsolutePath();
        String relativePath = separator + "Games" + separator + "Saved" + separator;
        String savedPath = new File(currDir).getAbsolutePath() + relativePath;

        System.out.println(savedPath);
    }


}
