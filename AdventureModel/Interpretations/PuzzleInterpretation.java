package AdventureModel.Interpretations;

import AdventureModel.Minigames.Puzzle;
import jtuples.Pair;

public class PuzzleInterpretation implements Interpretation {
    private Double impact;
    private Puzzle target;

    /**
     * This method interprets the result of the Player's performance on the Puzzle, in the form
     * of a String and a Double. The String represents which Ending the Double value will contribute
     * towards, based on the Puzzle's unique accuracy cutoffs predetermined by the game file. The
     * impact value will be determined as the ratio between the largest number of consecutively
     * correct inputs to the number of correct inputs in the user input.
     * @return a Pair of standardized data to be used by OutcomeExecuter's update() method
     */
    @Override
    public Pair<String, Double> interpret() {
        String index1 = "";
        Double index2;
        Double accuracy = target.accuracy();
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

    public PuzzleInterpretation(Puzzle target) {
        this.target = target;
        this.impact = 0D;
    }
}
