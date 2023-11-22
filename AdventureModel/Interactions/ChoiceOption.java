package AdventureModel.Interactions;

public class ChoiceOption {
    private String optionText;

    private Action action;

    // NOTE!! Option instances are never in Room.forcedQueue! They are only executed after Option button is clicked!!
    public void execute() {
        this.action.execute();
    }

    public String getOptionText() {
        return optionText;
    }
}
