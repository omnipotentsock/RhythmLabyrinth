package AdventureModel.Interpretations;

import AdventureModel.Minigames.Battle.Battle;
import jtuples.Pair;

import java.io.Serializable;

public class BattleInterpretation implements Interpretation, Serializable {
    private Double impact;
    private Battle target;
    @Override
    public Pair<String, Double> interpret() {
        throw new UnsupportedOperationException();
    }

    public BattleInterpretation(Battle target) {
        this.target = target;
        this.impact = 0D;
    }
}
