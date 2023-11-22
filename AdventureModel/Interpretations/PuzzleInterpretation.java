package AdventureModel.Interpretations;

import AdventureModel.Minigames.Puzzle;
import AdventureModel.jtuples.Pair;

public class PuzzleInterpretation implements Interpretation {
    private Double impact;
    private Puzzle target;
    @Override
    public Pair<String, Double> interpret() {
        throw new UnsupportedOperationException();
    }

    public PuzzleInterpretation(Puzzle target) {
        this.target = target;
        this.impact = 0D;
    }
}
