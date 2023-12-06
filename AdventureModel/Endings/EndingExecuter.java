package AdventureModel.Endings;

import views.AdventureGameView;

import java.io.Serializable;

public class EndingExecuter implements Serializable {
    private Ending state;

    /**
     * This method calls the executeEnding() method of whichever Ending the state attribute
     * is currently referencing.
     */
    public Ending executeEnding() {
        this.state.executeEnding();
        return this.state;
    }

    /**
     * This method takes a type Ending and has the state attribute reference to it.
     * @param ending is a type Ending.
     */
    public void setEnding(Ending ending) {
        this.state = ending;
    }
    public EndingExecuter() {
        this.state = null;
    }
}
