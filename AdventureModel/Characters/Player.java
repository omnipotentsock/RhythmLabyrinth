package AdventureModel.Characters;

import AdventureModel.Endings.Ending;
import AdventureModel.Endings.EndingExecuter;
import AdventureModel.Minigames.MiniGame;
import AdventureModel.Movement.Room;
import AdventureModel.Outcomes.OutcomeExecuter;
import AdventureModel.Outcomes.Progression;
import views.AdventureGameView;

import java.io.Serializable;
import java.util.ArrayList;
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
    private OutcomeExecuter outcome;

    /**
     * Adventure Game Player Constructor
     */
    public Player(Room currentRoom, AdventureGameView adventureGameView) {
        this.currentRoom = currentRoom;
        Progression progression = new Progression(adventureGameView);
        List<MiniGame> miniGames = new ArrayList<>();
        EndingExecuter endingExecuter = new EndingExecuter();
        this.outcome = new OutcomeExecuter(progression, miniGames, endingExecuter);
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


    public OutcomeExecuter getOutcomeExecuter() {
        return outcome;
    }
    public void executeEnding() {
        this.outcome.execute();
    }
}
