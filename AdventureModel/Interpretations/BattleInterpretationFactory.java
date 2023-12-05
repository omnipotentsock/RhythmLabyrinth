package AdventureModel.Interpretations;

import AdventureModel.Minigames.Battle.Battle;
import AdventureModel.Minigames.Minigame;

public class BattleInterpretationFactory implements InterpretationFactory {
    private Battle target;
    @Override
    public Interpretation createInterpretation() {
        return new BattleInterpretation(this.target);
    }
    @Override
    public void accept(Minigame miniGame) {
        this.target = (Battle) miniGame;
    }
}
