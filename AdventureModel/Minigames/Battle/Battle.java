package AdventureModel.Minigames.Battle;

import AdventureModel.Interpretations.BattleInterpretationFactory;
import AdventureModel.Interpretations.Interpretation;
import AdventureModel.Minigames.MiniGame;

public class Battle implements MiniGame {
    private BattleInterpretationFactory battleInterpretationFactory;

    @Override
    public Interpretation formInterpretation() {
        this.battleInterpretationFactory.accept(this);
        return this.battleInterpretationFactory.createInterpretation();
    }

}
