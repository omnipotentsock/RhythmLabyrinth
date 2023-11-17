package CLG6Phase2;

import jtuples.Pair;

public class BattleInterpretation implements Interpretation {
    private double impact;
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
