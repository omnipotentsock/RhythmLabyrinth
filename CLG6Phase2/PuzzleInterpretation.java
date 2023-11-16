import java.org.javatuples.Pair;

public class PuzzleInterpretation implements Interpretation {
    private double impact;
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
