package AdventureModel.Minigames;

import AdventureModel.Interpretations.Interpretation;
import AdventureModel.Interpretations.PuzzleInterpretationFactory;

public class Puzzle implements MiniGame {
    private PuzzleInterpretationFactory puzzleInterpretationFactory;

    @Override
    public Interpretation formInterpretation() {
        this.puzzleInterpretationFactory.accept(this);
        return this.puzzleInterpretationFactory.createInterpretation();
    }
}
