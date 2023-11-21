package Interpretations;

import Minigames.Battle;
import jtuples.Pair;

public class BattleInterpretation implements Interpretation {
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
