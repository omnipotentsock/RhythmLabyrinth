package AdventureModel.Interpretations;

import AdventureModel.Minigames.Minigame;
import AdventureModel.Minigames.Puzzle.Puzzle;

import java.io.Serializable;

public class PuzzleInterpretationFactory implements InterpretationFactory, Serializable {
    private Puzzle target;
    @Override
    public Interpretation createInterpretation() {
        return new PuzzleInterpretation(this.target);
    }

    public void accept(Minigame minigame) {
        this.target = (Puzzle) minigame;
    }
}
