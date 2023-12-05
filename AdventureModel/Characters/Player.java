package AdventureModel.Characters;

import AdventureModel.Movement.Room;
import AdventureModel.Outcomes.OutcomeDeterminer;

import java.io.Serializable;

/**
 * This class keeps track of the progress
 * of the player in the game.
 */
public class Player implements Serializable {
    /**
     * The current room that the player is located in.
     */
    private Room currentRoom;

    private OutcomeDeterminer outcome = new OutcomeDeterminer();
    public double playerHealth = 100;
    public double totalHealth = 100;

    /**
     * Adventure Game Player Constructor
     */
    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
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


    public OutcomeDeterminer getOutcome() {
        return outcome;
    }

    public double getPlayerHealth() { return this.playerHealth; }
    public double loseHealth(int damage) { return this.playerHealth -= damage; }

    public void refreshHealth(){this.playerHealth = 100.0;}
}
