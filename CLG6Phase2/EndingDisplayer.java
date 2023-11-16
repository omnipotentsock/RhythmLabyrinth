public class EndingDisplayer {
    private Ending state;
    public void displayEnding() {
        this.state.displayEnding();
    }

    public void changeEnding(Ending ending) {
        this.state = ending;
    }
}
