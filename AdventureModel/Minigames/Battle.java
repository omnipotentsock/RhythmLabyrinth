package AdventureModel.Minigames;

import AdventureModel.Interpretations.BattleInterpretationFactory;
import AdventureModel.Interpretations.Interpretation;

public class Battle implements MiniGame {
    private BattleInterpretationFactory battleInterpretationFactory;

    @Override
    public Interpretation formInterpretation() {
        this.battleInterpretationFactory.accept(this);
        return this.battleInterpretationFactory.createInterpretation();
    }

}
