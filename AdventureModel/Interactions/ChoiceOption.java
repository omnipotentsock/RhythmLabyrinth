package AdventureModel.Interactions;

import java.io.Serializable;

public class ChoiceOption implements Serializable {
    private String optionText;

    private Action action;

    public ChoiceOption(String s) {
        // s = "Yes>M0001"
        String[] optionSet = s.split(">");

        this.optionText = optionSet[0];
        this.action = new Action(optionSet[1]);
    }

    // NOTE!! Option instances are never in Room.forcedQueue! They are only executed after Option button is clicked!!
    public void execute() {
        this.action.execute();
    }

    public String getOptionText() {
        return optionText;
    }
}
