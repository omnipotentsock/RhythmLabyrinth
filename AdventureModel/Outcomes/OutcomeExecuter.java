package AdventureModel.Outcomes;

import AdventureModel.Endings.EndingExecuter;
import AdventureModel.Minigames.MiniGame;

import java.io.Serializable;
import java.util.List;

public class OutcomeExecuter implements Serializable {
    /**
     * This Progression object keeps track of the Player's progress throughout the AdventureGame.
     */
    private Progression record;
    /**
     * This is a List of MiniGames that the Player has completed so far.
     */
    private List<MiniGame> miniGames;
    /**
     * This is an EndingExecuter object that executes a specific ending, provided that it
     * is given as an input (via setEnding).
     */
    private EndingExecuter endingExecuter;

    /**
     * This is the constructor method.
     * @param record a Progression type
     * @param miniGames a List<MiniGame> type
     * @param endingExecuter an EndingExecuter type
     */
    public OutcomeExecuter(Progression record, List<MiniGame> miniGames, EndingExecuter endingExecuter) {
        this.record = record;
        this.miniGames = miniGames;
        this.endingExecuter = endingExecuter;
    }
    /**
     * This method iterates through all the MiniGames the Player has completed, determines
     * the appropriate ending, and executes the sequences associated with that ending.
     */
    public void execute() {
        for (MiniGame miniGame : miniGames) {
            this.record.update(miniGame.formInterpretation().interpret());
        }
        this.endingExecuter.setEnding(this.record.getEnding());
        this.endingExecuter.executeEnding();
    }

    /**
     * This returns the list of MiniGames the Player has completed so far
     * @return the list of MiniGames
     */
    public List<MiniGame> getMiniGames() {
        return this.miniGames;
    }

    /**
     * This returns the size of the list of MiniGames the Player has completed so far
     * @return the size of the list of MiniGames
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
