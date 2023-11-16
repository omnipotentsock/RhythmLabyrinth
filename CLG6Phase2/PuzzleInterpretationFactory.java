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
