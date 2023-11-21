package Outcomes;

import Endings.EndingDisplayer;
import Minigames.MiniGame;

import java.util.List;

public class OutcomeExecuter {
    private Progression record;
    private List<MiniGame> miniGames;

    private EndingDisplayer endingDisplayer;
    public void execute() {
        for (MiniGame miniGame : miniGames) {
            this.record.update(miniGame.formInterpretation().interpret());
        }
        this.endingDisplayer.changeEnding(this.record.getEnding());
        this.endingDisplayer.executeEnding();
    }
    public List<MiniGame> getMiniGames() {
        return miniGames;
    }
}
