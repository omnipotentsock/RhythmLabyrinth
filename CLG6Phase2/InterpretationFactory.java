package CLG6Phase2;

public interface InterpretationFactory {
    Interpretation createInterpretation();
    void accept(MiniGame miniGame);
}
