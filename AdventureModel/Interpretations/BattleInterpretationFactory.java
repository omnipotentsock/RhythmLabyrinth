package AdventureModel.Interpretations;

import AdventureModel.Minigames.Battle.Battle;
import AdventureModel.Minigames.Minigame;

import java.io.Serializable;

public class BattleInterpretationFactory implements InterpretationFactory, Serializable {
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
