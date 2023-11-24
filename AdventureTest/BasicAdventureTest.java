
import java.io.*;

import AdventureModel.AdventureGame;
import AdventureModel.AdventureLoader;
import AdventureModel.Interactions.Action;
import AdventureModel.Interactions.Choice;
import AdventureModel.Interactions.NPCDialogue;
import AdventureModel.Interactions.SingleDialogue;
import AdventureModel.Movement.ForcedQueue;
import AdventureModel.Movement.Passage;
import AdventureModel.Movement.Room;
import org.junit.jupiter.api.Test;

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

    @Test
    void generalTest(){
        String str = "SDialogue: You enter the cave, lit dimly by glowing ashes.-true";
        String[] st = str.split(" ", 2);

        for (String s : st){
            System.out.println("yuh: " + s);
        }

        System.out.println(st.length);
    }

}
