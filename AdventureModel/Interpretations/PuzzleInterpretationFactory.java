package AdventureModel.Interpretations;

import AdventureModel.Minigames.Minigame;
import AdventureModel.Minigames.Puzzle;

public class PuzzleInterpretationFactory implements InterpretationFactory {
    private Puzzle target;
    @Override
    public Interpretation createInterpretation() {
        return new PuzzleInterpretation(this.target);
    }

    public void accept(Minigame minigame) {
        this.target = (Puzzle) minigame;
    }
}
