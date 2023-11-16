public class Battle implements MiniGame {
    private BattleInterpretationFactory battleInterpretationFactory;
    @Override
    public Object execute() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Interpretation formInterpretation() {
        this.battleInterpretationFactory.accept(this);
        return this.battleInterpretationFactory.createInterpretation();
    }

}
