public interface InterpretationFactory {
    Interpretation createInterpretation();
    void accept(MiniGame miniGame);
}
