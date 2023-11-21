package Interpretations;

import Minigames.MiniGame;

public interface InterpretationFactory {
    Interpretation createInterpretation();
    void accept(MiniGame miniGame);
}
