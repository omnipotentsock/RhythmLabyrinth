package Minigames;

import Interpretations.BattleInterpretationFactory;
import Interpretations.Interpretation;

public class Battle implements MiniGame {
    private BattleInterpretationFactory battleInterpretationFactory;

    @Override
    public Interpretation formInterpretation() {
        this.battleInterpretationFactory.accept(this);
        return this.battleInterpretationFactory.createInterpretation();
    }

}
