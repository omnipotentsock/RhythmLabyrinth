package Minigames;

import Interpretations.Interpretation;
import Interpretations.PuzzleInterpretationFactory;

public class Puzzle implements MiniGame {
    private PuzzleInterpretationFactory puzzleInterpretationFactory;

    @Override
    public Interpretation formInterpretation() {
        this.puzzleInterpretationFactory.accept(this);
        return this.puzzleInterpretationFactory.createInterpretation();
    }
}
