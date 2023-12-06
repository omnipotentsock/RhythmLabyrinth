package AdventureModel;

import AdventureModel.Interactions.Action;
import AdventureModel.Interactions.Choice;
import AdventureModel.Interactions.SingleDialogue;
import AdventureModel.Interactions.upgradeShop;
import AdventureModel.Minigames.*;
import AdventureModel.Minigames.Battle.Battle;
import AdventureModel.Minigames.Puzzle.Puzzle;
import AdventureModel.Movement.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class AdventureLoader. Loads an adventure from files.
 */
public class AdventureLoader {

    private AdventureGame game; //the game to return
    private String adventureName; //the name of the adventure

    /**
     * Adventure Loader Constructor
     * __________________________
     * Initializes attributes
     * @param game the game that is loaded
     * @param directoryName the directory in which game files live
     */
    public AdventureLoader(AdventureGame game, String directoryName) {
        this.game = game;
        this.adventureName = directoryName;
    }

     /**
     * Load game from directory
     */
    public void loadGame() throws IOException { // TODO: Parse Minigames and NPCs
        parseRooms();
        parseSynonyms();
        parseMinigames();
        this.game.setHelpText(parseOtherFile("help"));
    }

     /**
     * Parse Rooms File
     */
    private void parseRooms() throws IOException {

        int roomNumber;
        String roomFileName = this.adventureName + "/gameFiles/rooms.txt";
        BufferedReader buff = new BufferedReader(new FileReader(roomFileName));

        while (buff.ready()) {

            String currRoom = buff.readLine(); // first line is the number of a room

            roomNumber = Integer.parseInt(currRoom); //current room number

            // now need to get room name
            String roomName = buff.readLine();

            // now we need to get the description

            String line = buff.readLine();
            line = buff.readLine();
            String[] query = new String[2];
            ForcedQueue queue = new ForcedQueue();
            while (!line.equals("--passages")) {
                query = line.split(" ", 2);

                switch (query[0]){
                    case "SDialogue:":
                        queue.enqueue(new SingleDialogue(query[1]));
                        break;
                    case "Choice:":
                        queue.enqueue(new Choice(query[1]));
                        break;
                    case "Action:":
                        queue.enqueue(new Action(query[1]));
                        break;
                    case "Upgrade:":
                        queue.enqueue(new upgradeShop(query[1]));
                        break;
                }
                line = buff.readLine();
            }

            // now we make the room object
            Room room = new Room(roomName, roomNumber, "", adventureName, queue);

            // now we make the motion table
            line = buff.readLine(); // reads the line after "-----"
            while (line != null && !line.equals("")) {
                String[] part = line.split(" \s+"); // have to use regex \\s+ as we don't know how many spaces are between the direction and the room number
                String direction = part[0];
                String dest = part[1];
                if (dest.contains("/")) {
                    String[] blockedPath = dest.split("/");
                    String dest_part = blockedPath[0];
                    String object = blockedPath[1];
                    Passage entry = new Passage(direction, dest_part, object);
                    room.getMotionTable().addDirection(entry);
                } else {
                    Passage entry = new Passage(direction, dest);
                    room.getMotionTable().addDirection(entry);
                }
                line = buff.readLine();
            }
            this.game.getRooms().put(room.getRoomNumber(), room);
        }

    }

    /**
     * Parse Minigames File
     */
    private void parseMinigames() throws IOException {
        String roomFileName = this.adventureName + "/gameFiles/minigames.txt";
        BufferedReader buff = new BufferedReader(new FileReader(roomFileName));

        String line = buff.readLine();

        while (true){
            line = buff.readLine();
            if (line.equals("----")){line = buff.readLine(); break;}
        }

        String[] minigame, attributes, thresholds;
        String id;
        while (line != null && !line.equals("")){
            minigame = line.split(" ");
            id = minigame[0];

            if (minigame[1].equals("Battle")){ // TODO: ADD TO MINIGAME ARRAYLIST IN ADVENTUREGAME
                this.game.getMinigames().put(id, new Battle(id, minigame[2].split("/"),  minigame[3].split("/"))); // TODO: ADD PARAMETERS
            }
            else if (minigame[1].equals("Puzzle")){
                this.game.getMinigames().put(id, new Puzzle(id, minigame[2].split("/"),  minigame[3].split("/"))); // TODO: ADD PARAMETERS
            }
            else {throw new IOException("Minigame Parsing Error: Make sure correct minigame type has been given.");}

        line = buff.readLine();
        }
    }

     /**
     * Parse Synonyms File
     */
    public void parseSynonyms() throws IOException {
        String synonymsFileName = this.adventureName + "/gameFiles/synonyms.txt";
        BufferedReader buff = new BufferedReader(new FileReader(synonymsFileName));
        String line = buff.readLine();
        while(line != null){
            String[] commandAndSynonym = line.split("=");
            String command1 = commandAndSynonym[0];
            String command2 = commandAndSynonym[1];
            this.game.getSynonyms().put(command1,command2);
            line = buff.readLine();
        }

    }

    /**
     * Parse Files other than Rooms and Synonyms
     *
     * @param fileName the file to parse
     */
    public String parseOtherFile(String fileName) throws IOException {
        String text = "";
        fileName = this.adventureName + "/gameFiles/" + fileName + ".txt";
        BufferedReader buff = new BufferedReader(new FileReader(fileName));
        String line = buff.readLine();
        while (line != null) { // while not EOF
            text += line+"\n";
            line = buff.readLine();
        }
        return text;
    }

}
