package AdventureTest;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import AdventureModel.AdventureGame;
import AdventureModel.Minigames.Minigame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAdventureTest {

    @Test
    void getSavedDir() {
        String separator = System.getProperty("file.separator");

        String currDir = new File("").getAbsolutePath();
        String relativePath = separator + "Games" + separator + "Saved" + separator;
        String savedPath = new File(currDir).getAbsolutePath() + relativePath;

        System.out.println(savedPath);
    }

    @Test
    void randomNumTest() {
        int maxSequence = 5;
        Random random = new Random();
        int randomNum = ThreadLocalRandom.current().nextInt(1, maxSequence + 1);
        System.out.println(randomNum);
    }

    @Test
    void minigameReaderTest() {
        AdventureGame game = new AdventureGame("RhythmLabyrinth");
        for (Minigame m : game.getMinigames().values()){
            System.out.println(m);
        }
    }


}
