package AdventureModel.Interpretations;

import AdventureModel.Minigames.Battle.Battle;
import jtuples.Pair;

public class BattleInterpretation implements Interpretation {
    private Double impact;
    private Battle target;
    @Override
    public Pair<String, Double> interpret() {
        String index0 = "";
        Double index1 = 1D;
        Double health = this.target.getPlayerHealth();
        Double pSC = this.target.getPerfSatCutoff();
        Double sMC = this.target.getSatMedCutoff();
        if (health.compareTo(pSC) <= 0) {
            index0 = performanceEnding;
        } else if (health.compareTo(sMC) <= 0) {
            index0 = satisfactoryEnding;
        } else {
            index0 = mediocreEnding;
        }
        return new Pair<String, Double>(index0, index1);
    }

    public BattleInterpretation(Battle target) {
        this.target = target;
        this.impact = 0D;
    }
}
