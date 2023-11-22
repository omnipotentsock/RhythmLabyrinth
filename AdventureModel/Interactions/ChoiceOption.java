package AdventureModel.Interactions;

public class ChoiceOption {
    private String optionText;

    private Action action;

    public ChoiceOption(String s) {
        // s = "Yes>M0001"
        String[] optionSet = s.split(">");

        this.optionText = optionSet[0];
        String minigameID = optionSet[1]; // TODO: Use minigameID to find minigame and set attribute to Action
    }

    // NOTE!! Option instances are never in Room.forcedQueue! They are only executed after Option button is clicked!!
    public void execute() {
        this.action.execute();
    }

    public String getOptionText() {
        return optionText;
    }
}
