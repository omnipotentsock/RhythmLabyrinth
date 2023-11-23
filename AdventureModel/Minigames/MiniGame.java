package AdventureModel.Minigames;

import AdventureModel.Interpretations.Interpretation;

public interface MiniGame {

    /**
     * This method makes its corresponding InterpretationFactory create a respective
     * Interpretation object.
     * @return an Interpretation object
     */
    Interpretation formInterpretation();

}