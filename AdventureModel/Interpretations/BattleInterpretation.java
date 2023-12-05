package AdventureModel.Interpretations;

import AdventureModel.Minigames.Battle.Battle;
import jtuples.Pair;

public class BattleInterpretation implements Interpretation {
    private Double impact;
    private Battle target;
    @Override
    public Pair<String, Double> interpret() {
        String index1 = "";
        Double index2;
        Double accuracy = target.getAccuracy();
        if (accuracy.compareTo(target.getPerfSatCutoff()) >= 0) {
            index1 = performanceEnding;
        } else if (accuracy.compareTo(target.getSatMedCutoff()) >= 0) {
            index1 = satisfactoryEnding;
        } else {
            index1 = mediocreEnding;
        }
        index2 = target.consecutiveImpact();
        return new Pair<>(index1, index2);
    }

    public BattleInterpretation(Battle target) {
        this.target = target;
        this.impact = target.consecutiveImpact();
    }
}
