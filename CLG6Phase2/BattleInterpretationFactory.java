public class BattleInterpretationFactory implements InterpretationFactory {
    private Battle target;
    @Override
    public Interpretation createInterpretation() {
        return new BattleInterpretation(this.target);
    }
    @Override
    public void accept(MiniGame miniGame) {
        this.target = (Battle) miniGame;
    }
}
