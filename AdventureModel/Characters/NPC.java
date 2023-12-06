package AdventureModel.Characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;


public class NPC implements Serializable {

    private String identifier;
    private Image image;

    public NPC(String name){
        this.identifier = name;
    }

    public static void main(String[] cheese) throws FileNotFoundException {
        String roomFileName = "Games/RhythmLabyrinth/npc-images/";
        try {
            BufferedReader buff = new BufferedReader(new FileReader(roomFileName + ".gitkeep"));
            System.out.println("Image found");
        } catch (Exception e){
            System.out.println("Image not found");
        }
    }
}
