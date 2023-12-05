package AdventureModel.Interpretations;

import AdventureModel.Minigames.Minigame;

public interface InterpretationFactory {
    /**
     * This method, in the context of a targeted Mini-game, creates a corresponding Interpretation object
     * that is capable of interpreting its execute() result into a standardized data format suitable for
     * the Progression class.
     * @return Interpretation
     */
    Interpretation createInterpretation();

    /**
     * This method accepts a Mini-game so that it can create a corresponding Interpretation object.
     * @param miniGame is a type MiniGame
     */
    void accept(Minigame miniGame);
}
