package AdventureModel;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This class contains the information about a 
 * room in the Adventure Game.
 */
public class Room implements Serializable {

    private final String adventureName;
    /**
     * The number of the room.
     */
    private final int roomNumber;

    /**
     * The name of the room.
     */
    private final String roomName;

    /**
     * The description of the room.
     */
    private final String roomDescription;

    /**
    * The image of the room.
    */
    private final Image roomImage = null; // TODO: Implement initialization of roomImage

    /**
     * The minigame in the room.
     */
//    private Minigame minigame = null;
    private final String minigame = ""; // TODO: Create/Locate Minigame abstract class and implement initialization of
                                        // minigame.

    /**
     * The status of completion of the minigame.
     */
    private boolean minigameCompleted = false;

    /**
     * The passage table for the room.
     */
    private PassageTable motionTable = new PassageTable();

    /**
     * A boolean to store if the room has been visited or not
     */
    private boolean isVisited;

    /**
     * A queue storing all the forced interactions to occur when the player enters the room
     */
    private ForcedQueue queue;


    /**
     * AdvGameRoom constructor.
     *
     * @param roomName: The name of the room.
     * @param roomNumber: The number of the room.
     * @param roomDescription: The description of the room.
     */
    public Room(String roomName, int roomNumber, String roomDescription, String adventureName){
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomDescription = roomDescription;
        this.adventureName = adventureName;
        this.isVisited = false;
    }
    public Room(String roomName, int roomNumber, String roomDescription, String adventureName, ForcedQueue queue){
        this.roomName = roomName;
        this.roomNumber = roomNumber;
        this.roomDescription = roomDescription;
        this.adventureName = adventureName;
        this.isVisited = false;
        this.queue = queue;
    }

    /**
     * Returns a comma delimited list of every
     * move that is possible from the given room,
     * e.g. "DOWN, UP, NORTH, SOUTH".
     *
     * @return delimited string of possible moves
     */
    public String getCommands() {
        List<String> directions = this.getAllDirections();
        return String.join(",", new ArrayList<>( new HashSet<>(directions)));
    }

    /**
     * This method gets all directions from the room.
     *
     * @return list of all possible directions
     */
    public List<String> getAllDirections(){
        List<String> directions = new ArrayList<>();
        for (Passage m: this.getMotionTable().passageTable) {
            directions.add(m.getDirection());
        }
        return directions;
    }

    /**
     * Sets the visit status of the room to true.
     */
    public void visit(){
        isVisited = true;
    }


    /**
     * Getter method for the number attribute.
     *
     * @return: number of the room
     */
    public int getRoomNumber(){
        return this.roomNumber;
    }

    /**throw new UnsupportedOperationException("showCommands is not implemented!");
     * Getter method for the description attribute.
     *
     * @return: description of the room
     */
    public String getRoomDescription(){
        return this.roomDescription.replace("\n", " ");
    }


    /**
     * Getter method for the name attribute.
     *
     * @return: name of the room
     */
    public String getRoomName(){
        return this.roomName;
    }


    /**
     * Getter method for the visit attribute.
     *
     * @return: visit status of the room
     */
    public boolean getVisited(){
        return this.isVisited;
    }


    /**
     * Getter method for the motionTable attribute.
     *
     * @return: motion table of the room
     */
    public PassageTable getMotionTable(){
        return this.motionTable;
    }

    /**
     * Method that returns if the minigame in the room is completed.
     * @return: minigameCompleted's value
     */
    public boolean isMinigameCompleted(){
        return minigameCompleted;
    }


    public String getAdventureName() {
        return adventureName;
    }

    public Image getRoomImage() {
        return roomImage;
    }

    public String getMinigame() {
        return minigame;
    }

    public ForcedQueue getQueue() {
        return queue;
    }
}
