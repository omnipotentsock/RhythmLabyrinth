package AdventureModel.Outcomes;

import AdventureModel.Endings.Ending;
import AdventureModel.Endings.EndingExecuter;
import AdventureModel.Minigames.Minigame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class OutcomeExecuter implements Serializable {
    /**
     * This Progression object keeps track of the Player's progress throughout the AdventureGame.
     */
    private Progression record;
    /**
     * This is a List of Minigames that the Player has completed so far.
     */
    private HashMap<String,Minigame> minigames;
    /**
     * This is an EndingExecuter object that executes a specific ending, provided that it
     * is given as an input (via setEnding).
     */
    private EndingExecuter endingExecuter;

    /**
     * This is the constructor method.
     * @param record a Progression type
     * @param minigames a List<Minigame> type
     * @param endingExecuter an EndingExecuter type
     */
    public OutcomeExecuter(Progression record, HashMap<String,Minigame> minigames, EndingExecuter endingExecuter) {
        this.record = record;
        this.minigames = minigames;
        this.endingExecuter = endingExecuter;
    }
    /**
     * This method iterates through all the Minigames the Player has completed, determines
     * the appropriate ending, and executes the sequences associated with that ending.
     */
    public Ending execute() {
        for (Minigame minigame : minigames.values()) {
            this.record.update(minigame.formInterpretation().interpret());
        }
        this.endingExecuter.setEnding(this.record.getEnding());
        return this.endingExecuter.executeEnding();
    }

    /**
     * This returns the list of Minigames the Player has completed so far
     * @return the list of Minigames
     */
    public HashMap<String,Minigame> getMinigames() {
        return this.minigames;
    }

    public void addMinigame(Minigame minigame){this.minigames.put(minigame.minigameID, minigame);}

    /**
     * This returns the size of the list of Minigames the Player has completed so far
     * @return the size of the list of Minigames
     */
    public int miniGamesCompleted() {
        return this.minigames.size();
    }
    public EndingExecuter getEndingExecuter() {
        return this.endingExecuter;
    }
    public Progression getRecord() {
        return this.record;
    }
}
