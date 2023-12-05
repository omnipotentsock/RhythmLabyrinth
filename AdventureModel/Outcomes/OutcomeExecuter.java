package AdventureModel.Outcomes;

import AdventureModel.Endings.Ending;
import AdventureModel.Endings.EndingExecuter;
import AdventureModel.Minigames.Minigame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OutcomeExecuter implements Serializable {
    /**
     * This Progression object keeps track of the Player's progress throughout the AdventureGame.
     */
    private Progression record;
    /**
     * This is a List of Minigames that the Player has completed so far.
     */
    private List<Minigame> miniGames;
    /**
     * This is an EndingExecuter object that executes a specific ending, provided that it
     * is given as an input (via setEnding).
     */
    private EndingExecuter endingExecuter;

    /**
     * This is the constructor method.
     */
    public OutcomeExecuter() {
        this.record = new Progression();
        this.miniGames = new ArrayList<>();
        this.endingExecuter = new EndingExecuter();
    }
    /**
     * This method iterates through all the Minigames the Player has completed, determines
     * the appropriate ending, and executes the sequences associated with that ending.
     */
    public Ending execute() {
        for (Minigame miniGame : miniGames) {
            this.record.update(miniGame.formInterpretation().interpret());
        }
        this.endingExecuter.setEnding(this.record.getEnding());
        return this.endingExecuter.executeEnding();
    }

    /**
     * This returns the list of Minigames the Player has completed so far
     * @return the list of Minigames
     */
    public List<Minigame> getMinigames() {
        return this.miniGames;
    }

    /**
     * This returns the size of the list of Minigames the Player has completed so far
     * @return the size of the list of Minigames
     */
    public int miniGamesCompleted() {
        return this.miniGames.size();
    }
    public EndingExecuter getEndingExecuter() {
        return this.endingExecuter;
    }
    public Progression getRecord() {
        return this.record;
    }
}
