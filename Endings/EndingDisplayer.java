package Endings;

public class EndingDisplayer {
    private Ending state;

    /**
     * This method calls the executeEnding() method of whichever Ending the state attribute
     * is currently referencing.
     */
    public void executeEnding() {
        this.state.executeEnding();
    }

    /**
     * This method takes a type Ending and has the state attribute reference to it.
     * @param ending is a type Ending.
     */
    public void changeEnding(Ending ending) {
        this.state = ending;
    }
}
