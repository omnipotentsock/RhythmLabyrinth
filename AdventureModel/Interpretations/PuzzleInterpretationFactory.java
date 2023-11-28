package AdventureModel.Interpretations;

import AdventureModel.Minigames.MiniGame;
import AdventureModel.Minigames.Puzzle.Puzzle;

public class PuzzleInterpretationFactory implements InterpretationFactory {
    private Puzzle target;
    @Override
    public Interpretation createInterpretation() {
        return new PuzzleInterpretation(this.target);
    }

    public void accept(MiniGame miniGame) {
        this.target = (Puzzle) miniGame;
    }
}
