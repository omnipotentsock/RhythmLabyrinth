package AdventureModel.Characters;

import AdventureModel.Endings.EndingExecuter;
import AdventureModel.Minigames.Minigame;
import AdventureModel.Movement.Room;
import AdventureModel.Outcomes.OutcomeExecuter;
import AdventureModel.Outcomes.Progression;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class Player implements Serializable {
    /**
     * The current room that the player is located in.
     */
    private Room currentRoom;

    private OutcomeExecuter outcome = null;
    public double playerHealth = 100;
    public double totalHealth = 100;

    /**
     * Adventure Game Player Constructor
     */
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
        this.outcome = new OutcomeExecuter(new Progression(), new HashMap<String,Minigame>(), new EndingExecuter());
    }

    /**
     * Setter method for the current room attribute.
     *
     * @param currentRoom The location of the player in the game.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Getter method for the current room attribute.
     *
     * @return current room of the player.
     */
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public OutcomeExecuter getOutcome() {
        return outcome;
    }

    public double getPlayerHealth() { return this.playerHealth; }
    public double loseHealth(int damage) { return this.playerHealth -= damage; }

    public void refreshHealth(){this.playerHealth = (double) this.totalHealth;}
}
